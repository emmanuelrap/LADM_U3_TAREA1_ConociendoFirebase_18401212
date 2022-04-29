package com.example.ladm_u3_tarea1_conociendofirebase_18401212

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PatternMatcher
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.example.ladm_u3_tarea1_conociendofirebase_18401212.databinding.ActivityMainBinding
import com.example.ladm_u3_tarea1_conociendofirebase_18401212.databinding.ActivityRegistroBinding
import com.google.firebase.auth.FirebaseAuth
import kotlin.concurrent.thread

class RegistroActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityRegistroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title="Registro"


        binding.btnRegister.setOnClickListener {
            val correo=binding.txtMail.text.toString()
            val clave=binding.txtPassword.text.toString()

            if( correo.isEmpty() || clave.isEmpty()){ //!Patterns.EMAIL_ADDRESS.matcher(correo).matches() || telefono.isEmpty()  || usuario.isEmpty()  ){
                     Toast.makeText(this, "Faltan Valores en los Campos", Toast.LENGTH_SHORT).show()
            }else{
                crearUsuario(correo,clave)
            }
        }
    }

    private fun crearUsuario(email:String, clave:String){


        auth.createUserWithEmailAndPassword(email, clave)/*
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(baseContext, "2", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(baseContext, "3", Toast.LENGTH_SHORT).show()
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }*/
    }
}