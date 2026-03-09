package com.example.elementosbasicos.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.elementosbasicos.databinding.ItemListBinding

class ItemAdapter(
    private val items: MutableList<String>,
    private val onItemClick: (String, Int) -> Unit
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.binding.tvItemNombre.text = item
        holder.binding.tvItemPosicion.text = "#${position + 1}"

        holder.binding.root.setOnClickListener {
            onItemClick(item, position)
        }

        holder.binding.btnEliminar.setOnClickListener {
            val pos = holder.adapterPosition
            if (pos != RecyclerView.NO_ID.toInt()) {
                items.removeAt(pos)
                notifyItemRemoved(pos)
                notifyItemRangeChanged(pos, items.size)
            }
        }
    }

    override fun getItemCount(): Int = items.size
}