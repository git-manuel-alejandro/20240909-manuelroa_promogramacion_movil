package com.example.manuelroa_20240909_programacionmovil

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.manuelroa_20240909_programacionmovil.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var bindig : ActivityMainBinding
    private lateinit var db : EntradasDataBaseHelper
    private lateinit var entradasAdaptador: EntradasAdaptador


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)


        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        db = EntradasDataBaseHelper(this)
        entradasAdaptador = EntradasAdaptador(db.getAllEntry() , this)
        // bindig.rvEntradas.layoutManager = LinearLayoutManager(this)
        // bindig.rvEntradas.adapter = entradasAdaptador









        Log.i("ciclo","********** onCreate, se inicia la aplicación ********")

        val boton1:Button = findViewById(R.id.button2)
        boton1.setOnClickListener{guardarDatos()}


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    private fun guardarDatos() {
        // val responsable : String = findViewById<View?>(R.id.editResponsable).toString()
        val responsable : String = findViewById<EditText>(R.id.editResponsable).text.toString()
        val numero : String = findViewById<EditText>(R.id.editNumero).text.toString()
        val fecha : String = findViewById<EditText>(R.id.editFecha).text.toString()




        val entrada = Entrada(0 , responsable , numero , fecha)

        db.insertEntrada(entrada)

        Toast.makeText(this , "Datos registrados" , Toast.LENGTH_LONG ).show()
    }
    override fun onStop() {
        super.onStop()
        Log.i("ciclo","********** onStop, se detiene la aplicación ********")

    }
}