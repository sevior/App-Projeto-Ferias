package com.example.minimundo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        btnCadastrarReceita.setOnClickListener {
            CadastrarReceita();
        }

        btnListarReceitas.setOnClickListener {
            ListarReceitas();
        }
        btnEditarReceita.setOnClickListener {
            EditarReceita();
        }
    }

    private fun CadastrarReceita(){
        val cadastrarReceita = Intent(this, CadastrarReceitaActivity::class.java)
        startActivity(cadastrarReceita)
    }

    private fun ListarReceitas(){
        val listarReceitas = Intent(this, ListarReceitasActivity::class.java)
        startActivity(listarReceitas)
    }

    private fun EditarReceita(){
        val editarReceita = Intent(this, EditarReceitaActivity::class.java)
        startActivity(editarReceita)
    }
}