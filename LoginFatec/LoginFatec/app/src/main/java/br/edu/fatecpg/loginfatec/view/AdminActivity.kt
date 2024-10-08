package br.edu.fatecpg.loginfatec.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.fatecpg.loginfatec.R
import br.edu.fatecpg.loginfatec.databinding.ActivityAdminBinding
import br.edu.fatecpg.loginfatec.viewmodel.LoginViewModel
class AdminActivity : AppCompatActivity() {
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var binding: ActivityAdminBinding // Certifique-se de que você tem um layout chamado activity_admin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Exibir lista de usuários
        exibirUsuarios()
    }

    private fun exibirUsuarios() {
        val usuarios = viewModel.listarUsuarios()
        val listaUsuarios = usuarios.joinToString(separator = "\n") {
            "${it.login} - ${if (it.bloqueado) "Bloqueado" else "Ativo"}"
        }

        binding.textViewUsuarios.text = if (listaUsuarios.isEmpty()) {
            "Nenhum usuário cadastrado."
        } else {
            listaUsuarios
        }
    }
}

