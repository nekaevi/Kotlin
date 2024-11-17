package br.edu.fatecpg.clinica.dao

import br.edu.fatecpg.clinica.model.Consulta
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.DocumentSnapshot

class ConsultaDaoImpl {

    private val db = FirebaseFirestore.getInstance()
    private val clinicaCollection = db.collection("clinica") // A coleção 'clinica'

    // Método para criar uma consulta
    fun criarConsulta(consulta: Consulta, aoCompletar: (Boolean) -> Unit) {
        clinicaCollection.document("consultas") // Agora criamos uma subcoleção ou documento dentro da coleção 'clinica'
            .collection("consultas") // Subcoleção 'consultas'
            .add(consulta) // Adiciona a consulta
            .addOnSuccessListener {
                aoCompletar(true)  // Chama o callback de sucesso
            }
            .addOnFailureListener {
                aoCompletar(false)  // Chama o callback de falha
            }
    }

    // Método para obter as consultas de um paciente específico
    fun obterConsultasPorPaciente(pacienteId: String, aoCompletar: (List<Consulta>) -> Unit) {
        clinicaCollection.document("consultas") // Documento da subcoleção de consultas
            .collection("consultas") // Subcoleção 'consultas'
            .whereEqualTo("pacienteId", pacienteId) // Filtra pelas consultas do paciente
            .orderBy("data", Query.Direction.ASCENDING) // Ordena pela data
            .get()
            .addOnSuccessListener { snapshot ->
                val consultas = snapshot.documents.mapNotNull { document ->
                    document.toObject(Consulta::class.java)?.apply {
                        consultaId = document.id // Adiciona o ID do documento como consultaId
                    }
                }
                aoCompletar(consultas) // Retorna as consultas
            }
            .addOnFailureListener {
                aoCompletar(emptyList()) // Em caso de erro, retorna lista vazia
            }
    }

    // Método para obter as consultas de um médico específico
    fun obterConsultasPorMedico(medicoId: String, aoCompletar: (List<Consulta>) -> Unit) {
        clinicaCollection.document("consultas") // Documento de consultas
            .collection("consultas") // Subcoleção de consultas
            .whereEqualTo("medicoId", medicoId) // Filtra consultas por médico
            .orderBy("data", Query.Direction.ASCENDING) // Ordena pela data
            .get()
            .addOnSuccessListener { snapshot ->
                val consultas = snapshot.documents.mapNotNull { document ->
                    document.toObject(Consulta::class.java)?.apply {
                        consultaId = document.id // Atribui o ID do documento à consulta
                    }
                }
                aoCompletar(consultas) // Retorna a lista de consultas
            }
            .addOnFailureListener {
                aoCompletar(emptyList()) // Retorna lista vazia em caso de erro
            }
    }
    fun cancelarConsulta(consultaId: String, aoCompletar: (Boolean) -> Unit) {
        db.collection("Clinica")
            .document("Consultas")
            .collection("ConsultasAgendadas")
            .document(consultaId)
            .delete()
            .addOnSuccessListener {
                aoCompletar(true)
            }
            .addOnFailureListener {
                aoCompletar(false)
            }
    }
}
