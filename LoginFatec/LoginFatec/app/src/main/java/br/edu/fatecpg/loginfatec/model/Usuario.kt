package br.edu.fatecpg.loginfatec.model

data class Usuario(
    val login: String,
    private val senha: String,
    var bloqueado: Boolean = false,
    var tentativasFalhas: Int = 0
) {
    fun verificarSenha(senhaTentativa: String): Boolean {
        if (bloqueado) {
            return false
        }
        return if (senhaTentativa == senha) {
            tentativasFalhas = 0  // Reseta tentativas em caso de sucesso
            true
        } else {
            tentativasFalhas++
            if (tentativasFalhas >= 3) {
                bloqueado = true
            }
            false
        }
    }

    companion object {
        // Método para verificar se o usuário já existe na lista
        fun existe(usuarios: List<Usuario>, login: String): Boolean {
            return usuarios.any { it.login == login }
        }
    }
}
