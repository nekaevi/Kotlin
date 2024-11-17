package br.edu.fatecpg.clinica.dao

import br.edu.fatecpg.clinica.model.Consulta

interface ConsultaDao {
    // Método para criar uma nova consulta
    fun criarConsulta(consulta: Consulta, aoCompletar: (Boolean) -> Unit)

    // Método para obter todas as consultas de um médico específico
    fun obterConsultasPorMedico(medicoId: String, aoCompletar: (List<Consulta>) -> Unit)

    // Método para obter todas as consultas de um paciente específico
    fun obterConsultasPorPaciente(pacienteId: String, aoCompletar: (List<Consulta>) -> Unit)

    // Método para cancelar uma consulta
    fun cancelarConsulta(consultaId: String, aoCompletar: (Boolean) -> Unit)
}
