import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.fatecpg.clinica.dao.ConsultaDaoImpl
import br.edu.fatecpg.clinica.databinding.ActivityAgendaPacienteBinding
import br.edu.fatecpg.clinica.model.Consulta
import com.google.firebase.auth.FirebaseAuth
import java.text.SimpleDateFormat
import java.util.*

class AgendaPacientesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAgendaPacienteBinding
    private val pacienteId = FirebaseAuth.getInstance().currentUser?.uid ?: ""
    private val consultaDao = ConsultaDaoImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgendaPacienteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Quando o paciente clicar no botão para confirmar o agendamento
        binding.btnConfirmarAgendamento.setOnClickListener {
            val dataHora = binding.edtDataHora.text.toString()

            if (dataHora.isNotEmpty()) {
                // Converter a string de data e hora para um objeto Date
                val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
                val data = sdf.parse(dataHora)

                val consulta = Consulta(
                    consultaId = "",  // Firestore irá gerar o ID automaticamente
                    pacienteId = pacienteId,
                    medicoId = "medicoId",  // Aqui você pode pegar o ID do médico ou deixá-lo fixo
                    data = data,  // Passa o objeto Date
                    pacienteNome = FirebaseAuth.getInstance().currentUser?.displayName ?: "Desconhecido",
                    tipoConsulta = "Consulta Geral"  // Aqui você pode personalizar o tipo de consulta
                )

                // Salvar a consulta no Firestore
                consultaDao.criarConsulta(consulta) { sucesso ->
                    if (sucesso) {
                        Toast.makeText(this, "Consulta agendada com sucesso!", Toast.LENGTH_SHORT).show()
                        finish() // Volta para a tela anterior
                    } else {
                        Toast.makeText(this, "Erro ao agendar consulta. Tente novamente.", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Preencha todos os campos.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
