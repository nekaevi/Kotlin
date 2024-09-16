package br.edu.fatecpg.listasfilmes.dao

import br.edu.fatecpg.listasfilmes.model.Favorito

interface FavoritoDao {
    fun adicionarFavorito(filme: String, diretor: String)
    fun obterFavoritos(): List<Favorito>
}