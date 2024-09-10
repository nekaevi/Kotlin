package br.edu.fatecpg.listasfilmes.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.edu.fatecpg.listasfilmes.R
import br.edu.fatecpg.listasfilmes.adapter.FavoritoAdapter
import br.edu.fatecpg.listasfilmes.dao.FavoritoDaoImpl

class ListaActivity : AppCompatActivity() {

    private val dao = FavoritoDaoImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)


        val rvFavoritos = findViewById<RecyclerView>(R.id.rv_favoritos)
        rvFavoritos.layoutManager = LinearLayoutManager(this)

        val filme = dao.obterFavoritos()
        val diretor = dao.obterFavoritos()
        rvFavoritos.adapter = FavoritoAdapter(filme, diretor)

        // Log para depuração
        Log.i("Filmes", filme.toString())
        Log.i("Diretor", diretor.toString())
    }
}