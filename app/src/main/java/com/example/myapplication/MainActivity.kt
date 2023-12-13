package com.example.myapplication
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.myapplication.R
import kotlin.random.Random
import com.example.myapplication.TelaVerdeActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : AppCompatActivity() {

    private lateinit var numberPickerMin: NumberPicker
    private lateinit var numberPickerMax: NumberPicker
    private lateinit var numberPickerTotal: NumberPicker
    private lateinit var buttonSortear: Button
    private lateinit var buttonLimpar: Button
    private lateinit var buttonQuadrado: Button
    private lateinit var resultText: TextView
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numberPickerMin = findViewById(R.id.numberPickerMin)
        numberPickerMax = findViewById(R.id.numberPickerMax)

        buttonSortear = findViewById(R.id.buttonSortear)
        buttonLimpar = findViewById(R.id.buttonLimpar)
        //buttonQuadrado = findViewById(R.id.imageButtonQuadrado)
        resultText = findViewById(R.id.resultText)
        FirebaseApp.initializeApp(this)


        val toolbar: Toolbar = findViewById(R.id.toolbar)
        toolbar.setBackgroundColor(getColor(R.color.colorPrimary))
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = "Dados"
        }

        // Configurar limites para NumberPickers
        val maxValue = 99

        numberPickerMin.minValue = 0
        numberPickerMin.maxValue = maxValue
        numberPickerMin.setOnValueChangedListener { _, _, newVal ->
            // Atualizar o valor máximo do numberPickerMax ao alterar o valor de numberPickerMin
            numberPickerMax.minValue = newVal + 1
            numberPickerMax.maxValue = maxValue
            numberPickerMax.value = newVal + 1
        }

        // Configurar numberPickerMax inicialmente
        numberPickerMax.minValue = numberPickerMin.value + 1
        numberPickerMax.maxValue = maxValue
        numberPickerMax.value = numberPickerMin.value + 1 //






        // Inicialmente, o botão Limpar é invisível
        buttonLimpar.visibility = View.GONE

    }



    fun sortearNumeros(view: View) {
        val min = numberPickerMin.value
        val max = numberPickerMax.value


        // Lógica para sortear e exibir números aqui


        //resultText.text = "Números sorteados: ${numerosSorteados.joinToString(", ")}"



        val testeMap = hashMapOf(
            "min" to min.toString(),
            "max" to max.toString(),
            "vezes" to "3"

        )

        db.collection("teste").document("valores")
            .set(testeMap).addOnCompleteListener {
                Log.d("db", "sucesso")
            }

        buttonSortear.setBackgroundColor(Color.parseColor("#00BCD4"))
        // Muda o texto para "Salvo"
        buttonSortear.text = "Salvo!"


    }



    fun limparDados(view: View) {
        // Limpar os dados aqui
        resultText.text = ""

        // Alterar a visibilidade dos botões após limpar
        buttonSortear.visibility = View.VISIBLE
        buttonLimpar.visibility = View.GONE
    }

    fun abrirTelaVerde(view: View) {
        Log.d("MeuApp", "Clicou no botão para abrir TelaVerdeActivity")
        val intent = Intent(this@MainActivity, TelaVerdeActivity::class.java)
        startActivity(intent)
    }

    fun abrirTelaAzul(view: View) {
        Log.d("MeuApp", "Clicou no botão para abrir TelaVerdeActivity")
        val intent = Intent(this@MainActivity, TelaAzulActivity::class.java)
        startActivity(intent)
    }

}
