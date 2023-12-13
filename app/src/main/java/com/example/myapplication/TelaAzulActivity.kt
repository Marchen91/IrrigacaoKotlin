package com.example.myapplication
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
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

        //val buttonVoltar: Button = findViewById(R.id.buttonVoltar) // Certifique-se de usar o ID correto

        // Obtém a data e hora atuais e formata para exibição
        val currentDateTime = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault()).format(Date())
        val toolbar: Toolbar = findViewById(R.id.toolbar2)
        toolbar.setBackgroundColor(getColor(R.color.colorPrimary))
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = "Informações"
        }


        db.collection("teste").document("teste2").addSnapshotListener { documento, error ->
            if(documento != null){

                val valorHumidade = documento.getLong("teste3")
                val valorIrriga = documento.getBoolean("teste4")
                val textoIrriga = if (valorIrriga == true) "Irrigação Ligada" else "Irrigação desligada"
                if (valorIrriga == true){
                    textViewData5.setTextColor(ContextCompat.getColor(this, android.R.color.holo_green_dark))

                }
                else{
                    textViewData5.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_dark))

                }
                textViewData3.text = valorHumidade.toString()
                textViewData5.text = textoIrriga



            }


        }

        db.collection("teste").document("valores").addSnapshotListener { documento2, error ->
            if(documento2 != null){
                val valorMaxDB = documento2.getString("max")
                textViewData7.text = valorMaxDB
                val valorMinDB = documento2.getString("min")
                textViewData6.text = valorMinDB


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
