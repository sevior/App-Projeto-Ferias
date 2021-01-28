package com.example.minimundo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import config.RetrofitConfig
import kotlinx.android.synthetic.*
import model.UsuarioModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import utils.Endpoints

class CadastroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        var btnCadastro = findViewById<EditText>(R.id.btnCadastrar) as Button

        btnCadastro.setOnClickListener {
            var email = findViewById<EditText>(R.id.txtEmail)
            var nome = findViewById<EditText>(R.id.txtNome_Cadastro)
            var senha = findViewById<EditText>(R.id.txtSenha_Cadastro)


            if (email.text.toString() == "" || nome.text.toString() == "" || senha.text.toString() == "") {
                Toast.makeText(baseContext, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            } else {
                val retrofit = RetrofitConfig.getRetrofitInstance("http://10.0.0.101:3000/");

                val endpoint = retrofit.create(Endpoints::class.java);
                val callback = endpoint.cadastro(
                    email.text.toString(),
                    nome.text.toString(),
                    senha.text.toString()
                )
                callback.enqueue(object : Callback<UsuarioModel> {

                    override fun onFailure(call: Call<UsuarioModel>, t: Throwable) {
                        Toast.makeText(baseContext, "Dados inválidos!", Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(
                        call: Call<UsuarioModel>,
                        response: Response<UsuarioModel>
                    ) {
                        val i = Intent(this@CadastroActivity, MainActivity::class.java)
                        startActivity(i)
                    }
                })
            }
        }
    }
}