package br.edu.fatecpg.trancicaotelas.view

import android.annotation.SuppressLint
import android.icu.text.DecimalFormat
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.edu.fatecpg.trancicaotelas.R
import br.edu.fatecpg.trancicaotelas.dao.ContatoDao
import br.edu.fatecpg.trancicaotelas.model.Resultado
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ResultadoActivity : AppCompatActivity() {
    val dao = ContatoDao()
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)
        //txv_nome, txv_whats, fab_volta
        val txvIMC = findViewById<TextView>(R.id.txv_imc)
        val txvClassificacao = findViewById<TextView>(R.id.txv_classificacao)
        val fabVolta = findViewById<FloatingActionButton>(R.id.fab_volta)

        // Recupera o resultado do IMC armazenado no ContatoDao
        val resultado = dao.exibirImc()
        val imc = resultado.calcularIMC()
        val classificacao = resultado.classificarIMC()

        // Formata o IMC para duas casas decimais
        val decimalFormat = DecimalFormat("#.00")
        val imcFormatted = decimalFormat.format(imc)

        txvIMC.text = "IMC: $imcFormatted"
        txvClassificacao.text = classificacao

        fabVolta.setOnClickListener {
            finish()
        }
    }
}