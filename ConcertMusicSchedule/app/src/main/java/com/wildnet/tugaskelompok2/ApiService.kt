package com.johnathan.tugaskelompok2




import com.johnathan.tugaskelompok2.Model.MenuModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("coba")
    fun getProdukList(@Query("page") page : String) : Call<List<MenuModel>>

}