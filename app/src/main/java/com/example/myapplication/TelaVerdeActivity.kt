package com.example.myapplication
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class TelaVerdeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_verde)

        // Obtém a referência para o TextView e Button no layout
        val textViewData2: TextView = findViewById(R.id.textViewTopo)
        val textViewData3: TextView = findViewById(R.id.textViewMeio)
        val textViewData: TextView = findViewById(R.id.textViewCentro)

        val buttonVoltar: Button = findViewById(R.id.buttonVoltar) // Certifique-se de usar o ID correto

        // Obtém a data e hora atuais e formata para exibição
        val currentDateTime = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault()).format(Date())

        // Define a data e hora no TextView
        textViewData.text = "Criado em Catanduva em: $currentDateTime"

        // Configura o clique do botão conforme necessário
        buttonVoltar.setOnClickListener {
            finish()
        }


    }
}
