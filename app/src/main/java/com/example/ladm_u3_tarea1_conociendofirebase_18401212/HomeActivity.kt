package com.example.ladm_u3_tarea1_conociendofirebase_18401212

import android.app.ProgressDialog
import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.ladm_u3_tarea1_conociendofirebase_18401212.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import java.io.File
import kotlin.math.sign

class HomeActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title="Home"

        binding.btnCerrarSesion.setOnClickListener {
            cerrarSesion()
        }

        binding.btnBorrar.setOnClickListener {
           binding.etNombreImagen.setText("")
        }

        binding.btnDescargar.setOnClickListener {
            val barraProgreso=ProgressDialog(this)
            barraProgreso.setMessage("Cargando Imagen...")
            barraProgreso.setCancelable(false)
            barraProgreso.show()

            val nombreImagen=binding.etNombreImagen.text.toString()
            val storageRef=FirebaseStorage.getInstance().reference.child("imagenes/$nombreImagen.png")

            val localArchivo= File.createTempFile("imagenTemp","jpg")
            storageRef.getFile(localArchivo).addOnSuccessListener {

                if(barraProgreso.isShowing)
                    barraProgreso.dismiss()
                val bitmap=BitmapFactory.decodeFile(localArchivo.absolutePath)
                binding.imageView.setImageBitmap(bitmap)




            }.addOnFailureListener(){
                if(barraProgreso.isShowing)
                    barraProgreso.dismiss()
                Toast.makeText(this,"Error al Descargar Imagen",Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun cerrarSesion(){
        Firebase.auth.signOut()
        val Intent=Intent(this,LoginActivity::class.java)
        this.startActivity(Intent)
    }
}