package com.example.elementosbasicos.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.elementosbasicos.R
import com.example.elementosbasicos.databinding.FragmentSelectionBinding

class SelectionFragment : Fragment() {

    private var _binding: FragmentSelectionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnVerificarHobbies.setOnClickListener {
            val seleccionados = mutableListOf<String>()
            if (binding.cbProgramacion.isChecked) seleccionados.add("Programación")
            if (binding.cbMusica.isChecked)       seleccionados.add("Música")
            if (binding.cbDeporte.isChecked)      seleccionados.add("Anime")
            if (binding.cbLectura.isChecked)      seleccionados.add("Lectura")

            binding.tvResultadoHobbies.text = if (seleccionados.isEmpty()) {
                "No seleccionaste ningún hobby"
            } else {
                "Tus hobbies: ${seleccionados.joinToString(", ")}"
            }
        }

        binding.rgNivelExperiencia.setOnCheckedChangeListener { _, checkedId ->
            val nivel = when (checkedId) {
                R.id.rb_principiante -> "Novato, buuh."
                R.id.rb_intermedio   -> "Meh, te defiendes."
                R.id.rb_avanzado     -> "Bien, pero no lo suficiente."
                R.id.rb_experto      -> "Experto, pro."
                else                -> "No seleccionado"
            }
            binding.tvNivelSeleccionado.text = "Nivel seleccionado: $nivel"
        }

        binding.switchNotificaciones.setOnCheckedChangeListener { _, isChecked ->
            val estado = if (isChecked) "activadas" else "desactivadas"
            Toast.makeText(requireContext(), "Notificaciones $estado", Toast.LENGTH_SHORT).show()
        }

        binding.switchModoOscuro.setOnCheckedChangeListener { _, isChecked ->
            val modo = if (isChecked) "Modo oscuro activado" else "Modo claro activado"
            binding.tvEstadoModo.text = modo
        }

        binding.switchUbicacion.setOnCheckedChangeListener { _, isChecked ->
            val ubicacion = if (isChecked) "Ubicación activada" else "Ubicación desactivada"
            Toast.makeText(requireContext(), ubicacion, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
