package com.example.elementosbasicos.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.elementosbasicos.adapters.ItemAdapter
import com.example.elementosbasicos.databinding.FragmentListsBinding

class ListsFragment : Fragment() {

    private var _binding: FragmentListsBinding? = null
    private val binding get() = _binding!!

    private val listaItems = mutableListOf(
        "🍎 Manzana",
        "🍌 Banana",
        "🍇 Uvas",
        "🍓 Fresa",
        "🍊 Naranja",
        "🥭 Mango",
        "🍑 Durazno",
        "🍋 Limón"
    )

    private lateinit var adapter: ItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ItemAdapter(listaItems) { item, position ->
            Toast.makeText(requireContext(),
                "Seleccionaste: $item (posición $position)",
                Toast.LENGTH_SHORT).show()
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        binding.btnAgregarItem.setOnClickListener {
            val nuevoItem = binding.etNuevoItem.text.toString().trim()
            if (nuevoItem.isNotEmpty()) {
                listaItems.add("📌 $nuevoItem")
                adapter.notifyItemInserted(listaItems.size - 1)
                binding.recyclerView.scrollToPosition(listaItems.size - 1)
                binding.etNuevoItem.text?.clear()
                binding.tvContadorItems.text = "Total de objetos: ${listaItems.size}"
                Toast.makeText(requireContext(),
                    "Objeto agregado exitosamente: $nuevoItem",
                    Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(),
                    "Escribe un objeto para agregar",
                    Toast.LENGTH_SHORT).show()
            }
        }

        binding.tvContadorItems.text = "Total de objetos: ${listaItems.size}"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}