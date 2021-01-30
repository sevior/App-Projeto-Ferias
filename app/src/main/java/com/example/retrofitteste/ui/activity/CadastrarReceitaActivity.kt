package com.example.retrofitteste.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.example.retrofitteste.R
import com.example.retrofitteste.model.Receita
import com.example.retrofitteste.retrofit.RetrofitInitializer
import kotlinx.android.synthetic.main.activity_cadastrar_receita.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
                RetrofitInitializer().receitaService().cadastroReceita(nomeReceita.text.toString(),
                    tempoPreparo.text.toString(),
                    rendimento.text.toString(),
                    ingredientes.text.toString(),
                    modoPreparo.text.toString())
                val call = RetrofitInitializer().receitaService().cadastroReceita(nomeReceita.text.toString(),
                    tempoPreparo.text.toString(),
                    rendimento.text.toString(),
                    ingredientes.text.toString(),
                    modoPreparo.text.toString())

                call.enqueue(object : Callback<Receita>{
                    override fun onFailure(call: Call<Receita>, t: Throwable) {
                        Toast.makeText(baseContext, "Dados inválidos!", Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(call: Call<Receita>, response: Response<Receita>) {
                        Toast.makeText(baseContext, "Receita Cadastrada com sucesso!", Toast.LENGTH_SHORT).show()
                        val i = Intent(this@CadastrarReceitaActivity, HomeActivity::class.java)
                        startActivity(i)
                    }

                })

            }
        }
    }
}