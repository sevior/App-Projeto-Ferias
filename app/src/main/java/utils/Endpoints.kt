package utils

import model.ReceitasModel
import model.UsuarioModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface Endpoints {
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


    //Cadastrar Receita
    @FormUrlEncoded
    @POST("/receitas")
    fun cadastroReceita(
        @Field("nomeReceita") nomeReceita: String,
        @Field("tempoPreparo") tempoPreparo: String,
        @Field("rendimento") rendimento: String,
        @Field("ingredientes") ingredientes: String,
        @Field("modoPreparo") modoPreparo: String
    ): Call<ReceitasModel>

    //Listar Receitas
    @GET("/receitas")
    fun listAll(): Call<List<ReceitasModel>>

}