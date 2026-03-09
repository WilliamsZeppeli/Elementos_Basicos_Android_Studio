package com.example.elementosbasicos.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.elementosbasicos.databinding.FragmentButtonsBinding

class ButtonsFragment : Fragment() {

    private var _binding: FragmentButtonsBinding? = null
    private val binding get() = _binding!!
    private var contadorClics = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentButtonsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnNormal.setOnClickListener {
            contadorClics++
            binding.tvContadorClics.text = "Clicks en botón normal: $contadorClics"
            Toast.makeText(requireContext(),
                "¡Botón presionado! Total de clicks: $contadorClics",
                Toast.LENGTH_SHORT).show()
        }

        binding.imgBtnCamara.setOnClickListener {
            Toast.makeText(requireContext(),
                "Botón presionado: ImageButton (Cámara)",
                Toast.LENGTH_SHORT).show()
        }

        binding.imgBtnCompartir.setOnClickListener {
            Toast.makeText(requireContext(),
                "Botón presionado: ImageButton (Compartir)",
                Toast.LENGTH_SHORT).show()
        }

        binding.btnOutline.setOnClickListener {
            Toast.makeText(requireContext(),
                "Botón 'Outline' presionado",
                Toast.LENGTH_SHORT).show()
        }

        binding.fabAgregar.setOnClickListener {
            Toast.makeText(requireContext(),
                "Botón presionado: FloatingActionButton",
                Toast.LENGTH_SHORT).show()
        }

        binding.btnHabilitar.setOnClickListener {
            val deshabilitado = binding.btnDeshabilitado
            if (!deshabilitado.isEnabled) {
                deshabilitado.isEnabled = true
                deshabilitado.text = "¡Acabas de habilitar el botón!"
                binding.btnHabilitar.text = "Deshabilitar botón"
            } else {
                deshabilitado.isEnabled = false
                deshabilitado.text = "Botón deshabilitado"
                binding.btnHabilitar.text = "Habilitar botón"
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}