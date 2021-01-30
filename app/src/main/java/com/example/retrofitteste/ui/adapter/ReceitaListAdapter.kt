package com.example.retrofitteste.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitteste.R.id
import com.example.retrofitteste.R.layout
import com.example.retrofitteste.model.Receita

class ReceitaListAdapter(
    private val receitas: List<Receita>,
    private val context: Context,
    val listener: (Receita) -> Unit
) : RecyclerView.Adapter<ReceitaListAdapter.ViewHolder>() {


    override fun getItemCount(): Int {
        return receitas.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val nomeReceita = itemView.findViewById<TextView>(id.nomeReceita)
        val tempoPreparo = itemView.findViewById<TextView>(id.tempoPreparo)
        val rendimento = itemView.findViewById<TextView>(id.rendimento)
        val ingredientes = itemView.findViewById<TextView>(id.ingredientes)
        val modoPreparo = itemView.findViewById<TextView>(id.modoPreparo)
        fun binView(receita: Receita, listener: (Receita) -> Unit){
            nomeReceita.text = receita.nomeReceita
            tempoPreparo.text = receita.tempoPreparo
            rendimento.text = receita.rendimento
            ingredientes.text = receita.ingredientes
            modoPreparo.text = receita.modoPreparo
            itemView.setOnClickListener { listener(receita) }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binView(receitas[position], listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(layout.receita_item, parent, false)
        return ViewHolder(view)
    }

}



