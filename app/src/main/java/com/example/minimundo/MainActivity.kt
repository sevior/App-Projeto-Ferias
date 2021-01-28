package com.example.minimundo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import config.RetrofitConfig
import kotlinx.android.synthetic.main.activity_main.*
import model.UsuarioModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import utils.Endpoints

class MainActivity : AppCompatActivity() {

    public lateinit var usuario: UsuarioModel;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var email = findViewById<EditText>(R.id.txtEmail);
        var senha = findViewById<EditText>(R.id.txtSenha);

        email.setText("");
        senha.setText("");

        btnCadastrarse.setOnClickListener {
            Cadastrar();
        }

        var btnLogin = findViewById(R.id.btnLogin) as Button
        btnLogin.setOnClickListener {
            if (email.text.toString() == "" || senha.text.toString() == "") {
                Toast.makeText(baseContext, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            } else {
                val retrofit = RetrofitConfig.getRetrofitInstance("http://10.0.0.101:3000/");

                val endpoint = retrofit.create(Endpoints::class.java);
                val callback = endpoint.auth(email.text.toString(), senha.text.toString());
                callback.enqueue(object : Callback<UsuarioModel> {

                    override fun onFailure(call: Call<UsuarioModel>, t: Throwable) {
                        Toast.makeText(baseContext, "Dados inválidos!", Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(
                        call: Call<UsuarioModel>,
                        response: Response<UsuarioModel>
                    ) {
                        response?.body()?.let {

                            usuario = UsuarioModel(it._id, it.email, it.nome, it.senha)
                        }
                        usuario?.let { it1 -> usuario = it1 };

                        val home = Intent(this@MainActivity, HomeActivity::class.java)
                        home.putExtra("Id", response.body()?._id)
                        home.putExtra("Email", response.body()?.email)
                        home.putExtra("Nome", response.body()?.nome)
                        home.putExtra("Senha", response.body()?.senha)
                        startActivity(home)
                    }
                })
            }
        }
    }

    private fun Cadastrar() {
        val cadastro = Intent(this, CadastroActivity::class.java)
        startActivity(cadastro)
    }
}