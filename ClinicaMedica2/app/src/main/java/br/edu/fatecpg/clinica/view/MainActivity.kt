import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.fatecpg.clinica.dao.ConsultaDaoImpl
import br.edu.fatecpg.clinica.databinding.ActivityMainBinding
import br.edu.fatecpg.clinica.model.Consulta
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var consultaAdapter: ConsultaAdapter
    private val pacienteId = FirebaseAuth.getInstance().currentUser?.uid ?: ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Carregar as consultas agendadas do Firestore
        carregarConsultasAgendadas()

        // Ao clicar no botão "Agendar Nova Consulta", abrir a tela para agendar
        binding.btnAgendarConsulta.setOnClickListener {
            val intent = Intent(this, AgendaPacientesActivity::class.java)
            startActivity(intent)
        }
    }

    private fun carregarConsultasAgendadas() {
        // Utiliza o ConsultaDao para buscar as consultas agendadas pelo paciente
        ConsultaDaoImpl().obterConsultasPorPaciente(pacienteId) { consultas ->
            if (consultas.isNotEmpty()) {
                // Configura o adapter para exibir as consultas na RecyclerView
                consultaAdapter = ConsultaAdapter(
                    consultas = consultas,
                    isMedico = false, // Aqui é paciente, então isMedico será false
                    onCancelarConsulta = { consulta ->
                        // Lógica para cancelar a consulta
                        cancelarConsulta(consulta)
                    },
                    onAgendarConsulta = { consulta ->
                        // Lógica para agendar uma nova consulta (já existe um método para isso)
                    }
                )
                binding.recyclerViewConsultas.layoutManager = LinearLayoutManager(this)
                binding.recyclerViewConsultas.adapter = consultaAdapter
            } else {
                // Exibe mensagem de que o paciente ainda não tem consultas agendadas
                Toast.makeText(this, "Você ainda não tem consultas agendadas.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun cancelarConsulta(consulta: Consulta) {
        // Cancelar a consulta no Firestore
        ConsultaDaoImpl().cancelarConsulta(consulta.consultaId) { sucesso ->
            if (sucesso) {
                Toast.makeText(this, "Consulta cancelada com sucesso!", Toast.LENGTH_SHORT).show()
                carregarConsultasAgendadas() // Atualiza a lista de consultas
            } else {
                Toast.makeText(this, "Erro ao cancelar consulta. Tente novamente.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
