package com.mpd.pmdm.dicerollerconstraintlayout.ui


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mpd.pmdm.dicerollerconstraintlayout.data.database.tirada.Tirada
import com.mpd.pmdm.dicerollerconstraintlayout.databinding.FragmentTiradaBinding



class TiradasRecyclerViewAdapter() : ListAdapter<Tirada, TiradasRecyclerViewAdapter.ViewHolder>
    (TiradaDiffCallback) {
    companion object {
        private val TiradaDiffCallback = object : DiffUtil.ItemCallback<Tirada>() {
            override fun areContentsTheSame(oldItem: Tirada, newItem: Tirada): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: Tirada, newItem: Tirada): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentTiradaBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }


    inner class ViewHolder(val binding: FragmentTiradaBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tirada: Tirada) {
            binding.tiradaID.text = tirada.id.toString()
            binding.tiradaFecha.text = tirada.fecha
            binding.tiradaDado1.text = tirada.dado1.toString()
            binding.tiradaDado2.text = tirada.dado2.toString()
        }


    }
}
