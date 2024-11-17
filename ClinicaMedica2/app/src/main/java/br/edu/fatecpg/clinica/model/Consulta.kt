package br.edu.fatecpg.clinica.model


import com.google.firebase.firestore.ServerTimestamp
import java.util.*

data class Consulta(
        var consultaId: String = "",
        var medicoId: String = "",
        var pacienteId: String = "",
        var pacienteNome: String = "",
        @ServerTimestamp var data: Date? = null, // Firebase lida com Timestamp de data
        var tipoConsulta: String = ""  // Tipo de consulta, por exemplo, "Rotina"
)

