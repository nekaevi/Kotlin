package br.edu.fatecpg.trancicaotelas.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.fatecpg.trancicaotelas.R
import br.edu.fatecpg.trancicaotelas.dao.ContatoDao
import br.edu.fatecpg.trancicaotelas.model.Resultado
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.DecimalFormat

class CalculoActivity : AppCompatActivity() {
    val dao = ContatoDao()
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)
        //edt_nome, edt_whats, btn_salvar, fab_lista
        val edtPeso = findViewById<EditText>(R.id.peso)
        val edtAltura = findViewById<EditText>(R.id.altura)
        val btnSalvar = findViewById<Button>(R.id.btn_salvar)
        val fabLista = findViewById<FloatingActionButton>(R.id.fab_lista)

        btnSalvar.setOnClickListener {
            val peso = edtPeso.text.toString().toDoubleOrNull() ?: 0.0
            val altura = edtAltura.text.toString().toDoubleOrNull() ?: 0.0

            if (peso > 0 && altura > 0) {
                val resultado = Resultado(peso, altura)
                dao.calculoimc(resultado)

                Toast.makeText(this, "Cálculo realizado com sucesso", Toast.LENGTH_LONG).show()

                // Passar os dados para a ResultadoActivity
                val intent = Intent(this, ResultadoActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Peso e altura devem ser maiores que zero.", Toast.LENGTH_LONG).show()
            }
        }

        fabLista.setOnClickListener {
            val intent = Intent(this, ResultadoActivity::class.java)
            startActivity(intent)
        }
    }
}