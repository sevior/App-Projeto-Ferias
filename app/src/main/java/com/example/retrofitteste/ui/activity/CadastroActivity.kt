package com.example.retrofitteste.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.retrofitteste.R
import com.example.retrofitteste.model.UsuarioModel
import com.example.retrofitteste.retrofit.RetrofitInitializer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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
                RetrofitInitializer().receitaService().cadastro(email.text.toString(),
                    nome.text.toString(),
                    senha.text.toString())
                val call = RetrofitInitializer().receitaService().cadastro(email.text.toString(),
                    nome.text.toString(),
                    senha.text.toString())

                call.enqueue(object : Callback<UsuarioModel>{
                    override fun onFailure(call: Call<UsuarioModel>, t: Throwable) {
                        Toast.makeText(baseContext, "Dados inválidos!", Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(
                        call: Call<UsuarioModel>,
                        response: Response<UsuarioModel>
                    ) {
                        Toast.makeText(baseContext, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show()
                        val i = Intent(this@CadastroActivity, LoginActivity::class.java)
                        startActivity(i)
                    }

                })
            }
        }
    }
}