package com.example.retrofitteste.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.retrofitteste.R
import com.example.retrofitteste.model.Receita
import com.example.retrofitteste.retrofit.RetrofitInitializer
import kotlinx.android.synthetic.main.activity_editar_receita.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditarReceitaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_receita)

        val receita = intent.getParcelableExtra<Receita>(ReceitaListActivity.INTENT_PARCELABLE)

        val nomeReceita = findViewById<TextView>(R.id.txtNomeReceitaEditar)
        val tempoPreparo = findViewById<TextView>(R.id.txtTempoPreparoEditar)
        val rendimento = findViewById<TextView>(R.id.txtRendimentoEditar)
        val ingredientes = findViewById<TextView>(R.id.txtIngredientesEditar)
        val modoPreparo = findViewById<TextView>(R.id.txtModoPreparoEditar)

        val id: String? = receita?._id
        nomeReceita.text = receita?.nomeReceita.toString()
        tempoPreparo.text = receita?.tempoPreparo.toString()
        rendimento.text = receita?.rendimento.toString()
        ingredientes.text = receita?.ingredientes.toString()
        modoPreparo.text = receita?.modoPreparo.toString()

       Log.d("junior","ID:? "+id)

        btnEditar.setOnClickListener {
            RetrofitInitializer().receitaService().alterarReceita(
                nomeReceita.text.toString(),
                tempoPreparo.text.toString(),
                rendimento.text.toString(),
                ingredientes.text.toString(),
                modoPreparo.text.toString(),
                id.toString()
            )
            val call = RetrofitInitializer().receitaService().alterarReceita(
                nomeReceita.text.toString(),
                tempoPreparo.text.toString(),
                rendimento.text.toString(),
                ingredientes.text.toString(),
                modoPreparo.text.toString(),
                id.toString()
            )

            call.enqueue(object : Callback<Receita> {
                override fun onFailure(call: Call<Receita>, t: Throwable) {
                    Toast.makeText(
                        baseContext,
                        "Falha ao alterar dados da Receita!",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onResponse(call: Call<Receita>, response: Response<Receita>) {
                    Toast.makeText(baseContext, "Receita alterada com sucesso!", Toast.LENGTH_SHORT)
                        .show()
                    val i = Intent(this@EditarReceitaActivity, HomeActivity::class.java)

                    startActivity(i)
                }
            })
        }

        btnExcluir.setOnClickListener(){
            RetrofitInitializer().receitaService().deletarReceita(id.toString())
            val call = RetrofitInitializer().receitaService().deletarReceita(id.toString())
            call.enqueue(object : Callback<Unit>{

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    Toast.makeText(baseContext, "Erro ao excluir receita!", Toast.LENGTH_SHORT)
                }

                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    Toast.makeText(baseContext, "Receita excluida com sucesso!", Toast.LENGTH_SHORT)
                        .show()
                   val i = Intent(this@EditarReceitaActivity, HomeActivity::class.java)

                    startActivity(i)
                }

            })


        }
    }
}