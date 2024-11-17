import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.edu.fatecpg.clinica.R
import br.edu.fatecpg.clinica.model.Consulta

class ConsultaAdapter(
    private val consultas: List<Consulta>,
    private val isMedico: Boolean,
    private val onCancelarConsulta: (Consulta) -> Unit,
    private val onAgendarConsulta: (Consulta) -> Unit
) : RecyclerView.Adapter<ConsultaAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txvNomePaciente: TextView = itemView.findViewById(R.id.txv_nome_paciente)
        val txvDataHora: TextView = itemView.findViewById(R.id.txv_data_hora)
        val btnCancelar: Button = itemView.findViewById(R.id.btn_cancelar)
        val btnAgendar: Button = itemView.findViewById(R.id.btn_agendar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_consulta, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val consulta = consultas[position]
        holder.txvNomePaciente.text = consulta.pacienteNome
        holder.txvDataHora.text = consulta.data.toString() // Ou o formato que você preferir

        // Se for paciente, exibe os botões
        if (isMedico) {
            holder.btnCancelar.visibility = View.GONE
            holder.btnAgendar.visibility = View.GONE
        } else {
            holder.btnCancelar.visibility = View.VISIBLE
            holder.btnAgendar.visibility = View.VISIBLE

            holder.btnCancelar.setOnClickListener {
                onCancelarConsulta(consulta)
            }

            holder.btnAgendar.setOnClickListener {
                onAgendarConsulta(consulta)
            }
        }
    }

    override fun getItemCount(): Int = consultas.size
}
