package com.example.retrofitteste.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.retrofitteste.R
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
    }
    private fun CadastrarReceita(){
        val cadastrarReceita = Intent(this, CadastrarReceitaActivity::class.java)
        startActivity(cadastrarReceita)
    }

    private fun ListarReceitas(){
        val listarReceitas = Intent(this, ReceitaListActivity::class.java)
        startActivity(listarReceitas)
    }
}