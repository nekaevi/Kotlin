import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.edu.fatecpg.loginfatec.databinding.ActivityLoginBinding
import br.edu.fatecpg.loginfatec.model.Usuario
import br.edu.fatecpg.loginfatec.view.AdminActivity
import br.edu.fatecpg.loginfatec.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {
    // Corrigido para usar by viewModels()
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnEntar.setOnClickListener {
            val login = binding.edtLogin.text.toString()
            val senha = binding.edtSenha.text.toString()
            // Logar
            val user = Usuario(login, senha)
            val retorno = viewModel.logar(user, senha)
            Toast.makeText(this, retorno, Toast.LENGTH_SHORT).show()

            // Verifica se é admin
            if (login == "admin" && retorno == "Login realizado com sucesso!") {
                startActivity(Intent(this, AdminActivity::class.java))
            }
        }

        binding.btnCad.setOnClickListener {
            val login = binding.edtLogin.text.toString()
            val senha = binding.edtSenha.text.toString()
            // Cadastrar
            val user = Usuario(login, senha)
            val retorno = viewModel.cadastrar(user)
            Toast.makeText(this, retorno, Toast.LENGTH_SHORT).show()
        }
    }
}


