import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.fatecpg.clinica.dao.ConsultaDaoImpl
import br.edu.fatecpg.clinica.databinding.ActivityAgendaMedicoBinding
import br.edu.fatecpg.clinica.model.Consulta
import com.google.firebase.auth.FirebaseAuth

class AgendaMedicoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAgendaMedicoBinding
    private val consultaDao = ConsultaDaoImpl()
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgendaMedicoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val medicoId = auth.currentUser?.uid ?: ""

        // Buscando as consultas do médico
        consultaDao.obterConsultasPorMedico(medicoId) { consultas ->
            configurarRecyclerView(consultas)
        }
    }

    private fun configurarRecyclerView(consultas: List<Consulta>) {
        binding.recyclerViewConsultas.layoutManager = LinearLayoutManager(this)

        // Passando os parâmetros necessários para o Adapter
        binding.recyclerViewConsultas.adapter = ConsultaAdapter(
            consultas,
            isMedico = true, // Como esta é a tela do médico, passamos 'true'
            onAgendarConsulta = { consulta ->
                // Ação para agendar a consulta
                // Aqui você pode implementar a lógica para o médico agendar a consulta
            },
            onCancelarConsulta = { consulta ->
                // Ação para cancelar a consulta
                // Aqui você pode implementar a lógica para o médico cancelar a consulta
            }
        )
    }
}
