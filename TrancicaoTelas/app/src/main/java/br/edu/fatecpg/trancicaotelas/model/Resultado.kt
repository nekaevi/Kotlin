package br.edu.fatecpg.trancicaotelas.model

data class Resultado(
    val peso: Double = 0.0,
    val altura: Double = 0.0
) {
    fun calcularIMC(): Double {
        return peso / (altura * altura)
    }

    fun classificarIMC(): String {
        val imc = calcularIMC()
        return when {
            imc < 18.5 -> "Abaixo do peso"
            imc in 18.5..24.9 -> "Peso ideal"
            imc in 25.0..29.9 -> "Sobrepeso"
            else -> "Obesidade"
        }
    }
}