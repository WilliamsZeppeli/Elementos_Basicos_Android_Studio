package com.example.elementosbasicos.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.elementosbasicos.databinding.FragmentTextFieldsBinding

class TextFieldsFragment : Fragment() {

    private var _binding: FragmentTextFieldsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTextFieldsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnMostrarTexto.setOnClickListener {
            val nombre = binding.etNombre.text.toString()
            val email  = binding.etEmail.text.toString()
            val pass   = binding.etPassword.text.toString()

            when {
                nombre.isEmpty() -> mostrarToast("Ingresa tu nombre")
                email.isEmpty()  -> mostrarToast("Ingresa tu email")
                pass.isEmpty()   -> mostrarToast("Ingresa tu contraseña")
                else -> {
                    binding.tvResultado.visibility = View.VISIBLE
                    binding.tvResultado.text =
                        "> Nombre: $nombre\n> Email: $email\n> Contraseña: ${"*".repeat(pass.length)}"
                }
            }
        }

        binding.btnLimpiar.setOnClickListener {
            binding.etNombre.text?.clear()
            binding.etEmail.text?.clear()
            binding.etPassword.text?.clear()
            binding.tvResultado.visibility = View.GONE
            mostrarToast("Campos eliminados")
        }
    }

    private fun mostrarToast(mensaje: String) {
        Toast.makeText(requireContext(), mensaje, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
