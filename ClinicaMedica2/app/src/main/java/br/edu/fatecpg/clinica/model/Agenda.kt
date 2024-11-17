package br.edu.fatecpg.clinica.model

data class Agenda(
    val meddicoId: String = "",
    val listaConsultas: List<Consulta> = emptyList()
)
