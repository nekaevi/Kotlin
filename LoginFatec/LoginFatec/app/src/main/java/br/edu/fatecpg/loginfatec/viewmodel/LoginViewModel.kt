package br.edu.fatecpg.loginfatec.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import br.edu.fatecpg.loginfatec.model.Usuario
class LoginViewModel : ViewModel() {
    private val usuarios = mutableListOf<Usuario>()

    fun logar(user: Usuario, senhaTentativa: String): String {
        val usuarioEncontrado = usuarios.find { it.login == user.login }
        return if (usuarioEncontrado != null && usuarioEncontrado.verificarSenha(senhaTentativa)) {
            "Login realizado com sucesso!"
        } else {
            "Login inválido, verifique suas credenciais."
        }
    }

    fun cadastrar(user: Usuario): String {
        return if (Usuario.existe(usuarios, user.login)) {
            "Usuário já cadastrado!"
        } else {
            usuarios.add(user)
            "Cadastrado com sucesso!"
        }
    }

    fun listarUsuarios(): List<Usuario> {
        return usuarios
    }
}
