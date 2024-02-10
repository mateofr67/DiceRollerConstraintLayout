package com.mpd.pmdm.dicerollerconstraintlayout.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mpd.pmdm.dicerollerconstraintlayout.data.database.tirada.Tirada
import com.mpd.pmdm.dicerollerconstraintlayout.databinding.FragmentTiradaBinding



class TiradasRecyclerViewAdapter(
    private var tiradaList: List<Tirada> = emptyList()
) : RecyclerView.Adapter<TiradasRecyclerViewAdapter.ViewHolder>() {

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
        val item = tiradaList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = tiradaList.size

    inner class ViewHolder(val binding: FragmentTiradaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tirada: Tirada){
            binding.tiradaID.text =  tirada.id.toString()
            binding.tiradaFecha.text = tirada.fecha
            binding.tiradaDado1.text = tirada.dado1.toString()
            binding.tiradaDado2.text = tirada.dado2.toString()
        }


    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newTiradaList: List<Tirada>){
        tiradaList = newTiradaList
        notifyDataSetChanged()

    }

}