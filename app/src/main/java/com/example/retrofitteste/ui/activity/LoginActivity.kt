package com.example.retrofitteste.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.retrofitteste.R
import com.example.retrofitteste.model.UsuarioModel
import com.example.retrofitteste.retrofit.RetrofitInitializer
import kotlinx.android.synthetic.main.activity_login.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    public lateinit var usuario: UsuarioModel;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var email = findViewById<EditText>(R.id.txtEmail);
        var senha = findViewById<EditText>(R.id.txtSenha);

        email.setText("");
        senha.setText("");

        btnCadastrarse.setOnClickListener{
            Cadastrar();
        }

        btnLogin.setOnClickListener {
            if (email.text.toString() == "" || senha.text.toString() == "") {
                Toast.makeText(baseContext, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            } else {
                RetrofitInitializer().receitaService().auth(email.text.toString(), senha.text.toString())
                val call = RetrofitInitializer().receitaService().auth(email.text.toString(), senha.text.toString())
                call.enqueue(object : Callback<UsuarioModel>{
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

                        val home = Intent(this@LoginActivity, HomeActivity::class.java)
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

    private fun Cadastrar(){
        val cadastro = Intent(this, CadastroActivity::class.java)
        startActivity(cadastro)
    }
}