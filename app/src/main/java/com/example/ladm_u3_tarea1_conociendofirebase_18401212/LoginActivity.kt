package com.example.ladm_u3_tarea1_conociendofirebase_18401212

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ladm_u3_tarea1_conociendofirebase_18401212.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title="Autenticación"

        auth = Firebase.auth

        binding.btnLogin.setOnClickListener {
            val correo=binding.txtMail.text.toString()
            val clave=binding.txtPassword.text.toString()


            if( correo.isEmpty() || clave.isEmpty()){
                Toast.makeText(baseContext, "Faltan Valores en los Campos",
                    Toast.LENGTH_SHORT).show()
            }else{
                iniciarsesion(correo,clave)
            }
        }

        binding.lblRegistro.setOnClickListener{
            val Intent=Intent(this,RegistroActivity::class.java)
            this.startActivity(Intent)
        }
    }



    private fun iniciarsesion(email:String, clave:String){
        auth.signInWithEmailAndPassword(email, clave)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("TAG", "signInWithEmail:success")
                    val Intent=Intent(this,HomeActivity::class.java)
                    this.startActivity(Intent)

                } else {
                    Log.w("TAG", "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()

                }
            }
    }
}