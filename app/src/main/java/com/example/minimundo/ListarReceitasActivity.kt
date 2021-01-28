package com.example.minimundo

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import config.RetrofitConfig
import kotlinx.android.synthetic.main.activity_listar_receitas.*
import model.ReceitasAdapter
import model.ReceitasModel
import retrofit2.Call
import retrofit2.Response
import utils.Endpoints

class ListarReceitasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar_receitas)

        val retrofit = RetrofitConfig.getRetrofitInstance("http://10.0.0.101/3000/");
        val endpoint = retrofit.create(Endpoints::class.java);
        val callback = endpoint.listAll();
        callback.enqueue(object : retrofit2.Callback<List<ReceitasModel>> {
            override fun onFailure(call: Call<List<ReceitasModel>>, t: Throwable) {
                Toast.makeText(baseContext, "Erro ao listar Receitas!", Toast.LENGTH_SHORT).show()
                Log.d("junior", "deu ruim")
            }

            override fun onResponse(
                call: Call<List<ReceitasModel>>,
                response: Response<List<ReceitasModel>>

            ) {
                Log.d("junior", "deu bom")
                listarReceitas(response.body()!!);
            }

        })
    }

    private fun listarReceitas(receitas: List<ReceitasModel>) {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@ListarReceitasActivity)
            adapter = ReceitasAdapter(receitas)
        }
    }
}