package br.edu.fatecpg.listasfilmes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.edu.fatecpg.listasfilmes.R
import br.edu.fatecpg.listasfilmes.model.Favorito

class FavoritoAdapter(private val favoritos: List<Favorito>, diretor: List<Favorito>) :
    RecyclerView.Adapter<FavoritoAdapter.ViewHolder>(){
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val txvFilme:TextView = itemView.findViewById(R.id.txv_filme)
        val txvDiretor:TextView = itemView.findViewById(R.id.txv_diretor)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_usuario, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val favorito = favoritos[position]
        holder.txvFilme.text = favorito.filme
        holder.txvDiretor.text = favorito.diretor
    }

    override fun getItemCount(): Int {
        return favoritos.size

    }


}