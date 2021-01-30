package com.example.retrofitteste.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.retrofitteste.R
import com.example.retrofitteste.model.Receita
import com.example.retrofitteste.retrofit.RetrofitInitializer
import com.example.retrofitteste.retrofit.service.CellClickListener
import com.example.retrofitteste.ui.adapter.ReceitaListAdapter
import kotlinx.android.synthetic.main.activity_receita_list.*
import kotlinx.android.synthetic.main.receita_item.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReceitaListActivity : AppCompatActivity() {
    companion object{
        val INTENT_PARCELABLE = "OBJECT_INTENT"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receita_list)

        RetrofitInitializer().receitaService().list()
        val call = RetrofitInitializer().receitaService().list()
        call.enqueue(object : Callback<List<Receita>?> {
            override fun onFailure(call: Call<List<Receita>?>?, t: Throwable?) {
                t?.message?.let { Log.e("onFailure error", it) }
            }

            override fun onResponse(
                call: Call<List<Receita>?>?,
                response: Response<List<Receita>?>?
            ) {
                response?.body()?.let {
                    val receitas: List<Receita> = it
                    configureList(receitas)
                }
            }
        })
    }

    private fun configureList(receitas: List<Receita>) {
        val recyclerView = receita_list_recyclerview
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ReceitaListAdapter(receitas,this){
            val intent = Intent(this, EditarReceitaActivity::class.java)
            intent.putExtra(INTENT_PARCELABLE,it)
            startActivity(intent)
        }



        /*recyclerView.adapter = ReceitaListAdapter(receitas, this@ReceitaListActivity, this)
        val layoutManager = StaggeredGridLayoutManager(
            1, StaggeredGridLayoutManager.VERTICAL
        )
        recyclerView.layoutManager = layoutManager*/

    }

   /* override fun onReceitaClickListener(position: Int) {
        val intent = Intent(this, EditarReceitaActivity::class.java)
            startActivity(intent)

    }*/
}