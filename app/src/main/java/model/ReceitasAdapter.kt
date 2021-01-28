package model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.minimundo.R
import kotlinx.android.synthetic.main.receita_item.view.*

internal class ReceitasAdapter(private val receitas: List<ReceitasModel>) :
    RecyclerView.Adapter<ReceitasAdapter.ViewHolder>() {
    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceitasAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.receita_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return receitas.size
    }

    override fun onBindViewHolder(holder: ReceitasAdapter.ViewHolder, position: Int) {
        val receita = receitas[position]

        holder.nomeReceita.text = receita.nomeReceita
        holder.tempoPreparo.text = receita.tempoPreparo
        holder.rendimento.text = receita.rendimento
        holder.ingredientes.text = receita.ingredientes
        holder.modoPreparo.text = receita.modoPreparo

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nomeReceita: TextView = itemView.findViewById(R.id.txtTituloReceita)
        val tempoPreparo: TextView = itemView.findViewById(R.id.txtTempoPreparo)
        val rendimento: TextView = itemView.findViewById(R.id.txtRendimento)
        val ingredientes: TextView = itemView.findViewById(R.id.txtIngredientes)
        val modoPreparo: TextView = itemView.findViewById(R.id.txtModoPreparo)
    }
}