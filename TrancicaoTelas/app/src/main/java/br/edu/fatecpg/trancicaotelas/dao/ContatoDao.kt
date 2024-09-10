package br.edu.fatecpg.trancicaotelas.dao

import br.edu.fatecpg.trancicaotelas.model.Resultado

class ContatoDao {
    companion object {
        private var imc: Resultado? = null
    }

    fun calculoimc(imc: Resultado) {
        Companion.imc = imc
    }
    fun exibirImc():Resultado{
        return Companion.imc?:Resultado()
    }

}