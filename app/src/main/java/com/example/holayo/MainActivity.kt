package com.example.holayo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

// Tu modelo de datos: una línea, y ya tiene igualdad, copia y representación.
data class Perfil(
    val nombre: String,
    val dato: String,
    val apodo: String?   // el ? declara: "puede no haber apodo" — y el compilador lo vigila
)

class MainActivity : AppCompatActivity() {

    private val perfil = Perfil(
        nombre = "Matías Benítez",
        dato = "Estoy cursando Aplicaciones Móviles",
        apodo = "Mati"       // probá también con un apodo real: "Rama"
    )

    private var saludoFormal = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("VIDA", "Main → onCreate")
        setContentView(R.layout.activity_main)

        val tvSaludo = findViewById<TextView>(R.id.tvSaludo)
        val tvDato = findViewById<TextView>(R.id.tvDato)
        val btnSaludar = findViewById<Button>(R.id.btnSaludar)

        // Si hay apodo se usa; si es null, el nombre. El operador ?: es el "plan B".
        //val comoLlamarme = perfil.apodo ?: perfil.nombre

        //Tercer dato opcional, probando el ?.let
        var comoLlamarme = perfil.nombre

        perfil.apodo?.let{ apodoTrue ->
            comoLlamarme = apodoTrue
        }


        tvSaludo.text = "Hola, soy $comoLlamarme"
        tvDato.text = perfil.dato

        // Una lambda: la función que se ejecuta cuando el botón se toca.
        btnSaludar.setOnClickListener {
            saludoFormal = !saludoFormal
            tvSaludo.text = if (saludoFormal)
                "Hola, soy $comoLlamarme"
            else
                "¡Buenas! Acá $comoLlamarme"
        }

        val btnIrSegunda = findViewById<Button>(R.id.btnIrSegunda)
        btnIrSegunda.setOnClickListener {
            // Intent EXPLÍCITO: nombra al destino por su clase.
            val intent = Intent(this, SegundaActivity::class.java)
            // El equipaje: un dato que viaja con el mensaje.
            intent.putExtra("nombre", perfil.apodo ?: perfil.nombre)
            startActivity(intent)
        }
    }

    override fun onStart() { super.onStart(); Log.d("VIDA", "Main → onStart") }
    override fun onResume() { super.onResume(); Log.d("VIDA", "Main → onResume") }
    override fun onPause() { super.onPause(); Log.d("VIDA", "Main → onPause") }
    override fun onStop() { super.onStop(); Log.d("VIDA", "Main → onStop") }
    override fun onDestroy() { super.onDestroy(); Log.d("VIDA", "Main → onDestroy") }
}
