package com.example.retrofitteste.retrofit.service

import com.example.retrofitteste.model.Receita
import com.example.retrofitteste.model.UsuarioModel
import retrofit2.Call
import retrofit2.http.*

interface Endpoints {

    //Listar Receitas
    @GET("/receitas")
    fun list(): Call<List<Receita>>

    //Cadastrar Receita
    @FormUrlEncoded
    @POST("/receitas")
    fun cadastroReceita(
        @Field("nomeReceita") nomeReceita: String,
        @Field("tempoPreparo") tempoPreparo: String,
        @Field("rendimento") rendimento: String,
        @Field("ingredientes") ingredientes: String,
        @Field("modoPreparo") modoPreparo: String
    ): Call<Receita>

    //Login
    @FormUrlEncoded
    @POST("/usuario")
    fun auth(@Field("email") email: String, @Field("senha") senha: String): Call<UsuarioModel>


    //Cadastrar Usuário
    @FormUrlEncoded
    @POST("/usuarios")
    fun cadastro(
        @Field("email") nome: String,
        @Field("nome") email: String,
        @Field("senha") senha: String
    ): Call<UsuarioModel>

    @FormUrlEncoded
    @PUT("receitas/{id}")
    fun alterarReceita(
        @Field("nomeReceita") nomeReceita: String,
        @Field("tempoPreparo") tempoPreparo: String,
        @Field("redimento") rendimento: String,
        @Field("ingredientes") ingredientes: String,
        @Field("modoPreparo") modoPreparo: String,
        @Path("id") id: String
    ): Call<Receita>


    @DELETE("receitas/{id})")
    fun deletarReceita(@Path("id") id: String): Call<Unit>

}