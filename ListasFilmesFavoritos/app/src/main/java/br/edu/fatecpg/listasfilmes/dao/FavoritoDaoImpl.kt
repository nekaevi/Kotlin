package br.edu.fatecpg.listasfilmes.dao

import br.edu.fatecpg.listasfilmes.model.Favorito

class FavoritoDaoImpl : FavoritoDao {
    companion object {
        private val favoritos = mutableListOf<Favorito>()
    }

    override fun adicionarFavorito(filme: String, diretor: String) {
        val favorito = Favorito(filme, diretor)
        favoritos.add(favorito)
    }

    override fun obterFavoritos(): List<Favorito> {
        return favoritos
    }
}


