package com.example.manuelroa_20240909_programacionmovil

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView

class EntradasAdaptador(
    private var entradas : List<Entrada>,
    context: Context
) : RecyclerView.Adapter<EntradasAdaptador.EntradaViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntradaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_entrada, parent, false)
        return EntradaViewHolder(view)
    }

    override fun getItemCount(): Int {
        return entradas.size
    }

    override fun onBindViewHolder(holder: EntradaViewHolder, position: Int) {
        val entrada = entradas[position]
        holder.itemResponsable.text = entrada.responsable
        holder.itemNumeroPersonas.text = entrada.numero
        holder.itemFecha.text = entrada.fecha
    }

    class EntradaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val itemResponsable : TextView = itemView.findViewById(R.id.tvResponsable)
        val itemNumeroPersonas : TextView = itemView.findViewById(R.id.tvNumeroPersonas)
        val itemFecha : TextView = itemView.findViewById(R.id.tvFecha)


    }

    fun refrescarLista( nuevaEntrada : List<Entrada>){
        entradas = nuevaEntrada
        notifyDataSetChanged()
    }




}