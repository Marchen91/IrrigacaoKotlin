package com.example.myapplication
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

class TelaAzulActivity : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        FirebaseApp.initializeApp(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_azul)

        // Obtém a referência para o TextView e Button no layout

            var textViewData3: TextView = findViewById(R.id.textViewMeioAzul)
            var textViewData4: TextView = findViewById(R.id.textViewMeioAltoAzul)
            var textViewData5: TextView = findViewById(R.id.MeioAzulBaixo)
            val textViewData: TextView = findViewById(R.id.textViewCentroAzul)
            var textViewData6: TextView = findViewById(R.id.MinDB)
            var textViewData7: TextView = findViewById(R.id.MaxDB)
            var progressBar2: ProgressBar = findViewById(R.id.progressBar2)
            var progressValue = 0
            val imageViewIrrigacao: ImageView = findViewById(R.id.imageViewIrrigacao)




        // Obtém a data e hora atuais e formata para exibição
        val currentDateTime = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault()).format(Date())
        val toolbar: Toolbar = findViewById(R.id.toolbar2)
        toolbar.setBackgroundColor(getColor(R.color.colorPrimary))
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = "Informações"
        }



        db.collection("nodemcu").document("valores").addSnapshotListener { documento, error ->
            if (documento != null) {
                val valorHumidade = documento.getLong("humidade")
                val valorIrriga = documento.getBoolean("irrigacao")
                val textoIrriga = if (valorIrriga == true) "Irrigação Ligada" else "Irrigação desligada"

                if (valorIrriga == true) {
                    textViewData5.setTextColor(ContextCompat.getColor(this, android.R.color.holo_green_dark))
                    //imageViewIrrigacao.setImageResource(R.drawable.poweron)

                } else {
                    textViewData5.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_dark))
                    //imageViewIrrigacao.setImageResource(R.drawable.poweroff)

                }

                textViewData3.text = valorHumidade.toString() + "%"  // Adiciona "%" ao valor de valorHumidade
                textViewData5.text = textoIrriga

                val valorHumidadeInt = valorHumidade?.toInt()

                if (valorHumidadeInt != null) {
                    progressValue = valorHumidadeInt
                    val clampedProgress = if (progressValue < 0) 0 else if (progressValue > 100) 100 else progressValue
                    progressBar2.progress = clampedProgress
                }
            }
        }

        db.collection("nodemcu").document("limites").addSnapshotListener { documento2, error ->
            if(documento2 != null){
                val valorMaxDB = documento2.getLong("max")
                textViewData7.text = valorMaxDB.toString() + "%"
                val valorMinDB = documento2.getLong("min")
                textViewData6.text = valorMinDB.toString() + "%"


            }

        }


        // Define a data e hora no TextView

        // Configura o clique do botão conforme necessário
       // buttonVoltar.setOnClickListener {
       //     finish()
       // }


    }


    fun abrirTelaVerde(view: View) {
        Log.d("MeuApp", "Clicou no botão para abrir TelaVerdeActivity")
        val intent = Intent(this@TelaAzulActivity, TelaVerdeActivity::class.java)
        startActivity(intent)
    }

    fun abrirMAin(view: View) {
        Log.d("MeuApp", "Clicou no botão para abrir TelaVerdeActivity")
        val intent = Intent(this@TelaAzulActivity, MainActivity::class.java)
        startActivity(intent)
    }
}
