package com.example.elementosbasicos.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.elementosbasicos.databinding.FragmentInfoBinding

class InfoFragment : Fragment() {

    private var _binding: FragmentInfoBinding? = null
    private val binding get() = _binding!!
    private val handler = Handler(Looper.getMainLooper())
    private var progresoActual = 0
    private var runnable: Runnable? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCambiarTexto.setOnClickListener {
            val textos = listOf(
                "¡Hola, Android!",
                "El TextView muestra texto estático y dinámico.",
                "Puedes cambiar color, tamaño y estilo.",
                "Es muy útil para mostrar información de diferentes maneras"
            )
            val aleatorio = textos.random()
            binding.tvDemostracion.animate().alpha(0f).setDuration(200).withEndAction {
                binding.tvDemostracion.text = aleatorio
                binding.tvDemostracion.animate().alpha(1f).setDuration(200).start()
            }.start()
        }

        val imagenes = listOf(
            android.R.drawable.ic_dialog_info,
            android.R.drawable.ic_dialog_alert,
            android.R.drawable.ic_dialog_email,
            android.R.drawable.ic_dialog_map,
            android.R.drawable.ic_menu_camera
        )
        var indiceImagen = 0

        binding.btnCambiarImagen.setOnClickListener {
            indiceImagen = (indiceImagen + 1) % imagenes.size
            binding.ivDemostracion.setImageResource(imagenes[indiceImagen])
            binding.ivDemostracion.animate()
                .rotationBy(360f).setDuration(500).start()
            Toast.makeText(requireContext(),
                "Imagen cambiada (${indiceImagen + 1}/${imagenes.size})",
                Toast.LENGTH_SHORT).show()
        }

        binding.btnIniciarProgreso.setOnClickListener {
            iniciarProgreso()
        }

        binding.btnReiniciarProgreso.setOnClickListener {
            runnable?.let { handler.removeCallbacks(it) }
            progresoActual = 0
            binding.progressBarDeterminada.progress = 0
            binding.tvPorcentaje.text = "0%"
            binding.tvEstadoCarga.text = "Presiona 'Iniciar' para comenzar"
            binding.btnIniciarProgreso.isEnabled = true
        }

        binding.switchCargando.setOnCheckedChangeListener { _, isChecked ->
            binding.progressBarIndeterminada.visibility =
                if (isChecked) View.VISIBLE else View.GONE
            binding.tvEstadoIndeterminado.text =
                if (isChecked) "Cargando datos..." else "Sin actividad"
        }
    }

    private fun iniciarProgreso() {
        binding.btnIniciarProgreso.isEnabled = false
        progresoActual = 0

        runnable = object : Runnable {
            override fun run() {
                if (progresoActual <= 100) {
                    binding.progressBarDeterminada.progress = progresoActual
                    binding.tvPorcentaje.text = "$progresoActual%"
                    binding.tvEstadoCarga.text = when {
                        progresoActual < 30  -> "Iniciando proceso..."
                        progresoActual < 60  -> "Procesando datos..."
                        progresoActual < 90  -> "Casi listo..."
                        progresoActual < 100 -> "Finalizando..."
                        else                 -> "¡Completado! ✅"
                    }
                    progresoActual += 2
                    handler.postDelayed(this, 60)
                } else {
                    binding.btnIniciarProgreso.isEnabled = true
                }
            }
        }
        handler.post(runnable!!)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        runnable?.let { handler.removeCallbacks(it) }
        _binding = null
    }
}