package com.example.minimundo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import config.RetrofitConfig
import kotlinx.android.synthetic.main.activity_home.*
import model.ReceitasModel
import retrofit2.Call
import retrofit2.Response
import utils.Endpoints

class CadastrarReceitaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar_receita)


        btnCadastrarReceita.setOnClickListener {
            var nomeReceita = findViewById<EditText>(R.id.txtNomeReceitaEditar)
            var tempoPreparo = findViewById<EditText>(R.id.txtTempoPreparoCadastro)
            var rendimento = findViewById<EditText>(R.id.txtRendimentoCadastro)
            var ingredientes = findViewById<EditText>(R.id.txtIngredientesCadastro)
            var modoPreparo = findViewById<EditText>(R.id.txtModoPreparoCadastro)

            if (nomeReceita.text.toString() == "" || tempoPreparo.text.toString() == "" || rendimento.text.toString() == "" || ingredientes.text.toString() == "" || modoPreparo.text.toString() == ""){
                Toast.makeText(baseContext, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            } else {
                val retrofit = RetrofitConfig.getRetrofitInstance("http://10.0.0.101:3000/");

                val endpoint = retrofit.create(Endpoints::class.java);
                val callback = endpoint.cadastroReceita(
                    nomeReceita.text.toString(),
                    tempoPreparo.text.toString(),
                    rendimento.text.toString(),
                    ingredientes.text.toString(),
                    modoPreparo.text.toString()
                )
                callback.enqueue(object : retrofit2.Callback<ReceitasModel> {

                    override fun onFailure(call: Call<ReceitasModel>, t: Throwable) {
                        Toast.makeText(baseContext, "Dados inválidos!", Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(
                        call: Call<ReceitasModel>,
                        response: Response<ReceitasModel>
                    ) {
                        val i = Intent(this@CadastrarReceitaActivity, HomeActivity::class.java)
                        startActivity(i)
                    }
                })
            }
        }
    }
}



