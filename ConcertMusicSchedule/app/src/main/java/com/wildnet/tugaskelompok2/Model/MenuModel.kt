package com.johnathan.tugaskelompok2.Model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class MenuModel : Serializable {

    @SerializedName("id")
    lateinit var ids: String
    @SerializedName("nama")
    lateinit var nama1: String

    @SerializedName("tempat")
    lateinit var tempat1: String

    @SerializedName("tanggal")
    lateinit var tanggal1: String

    @SerializedName("jam")
    lateinit var jam1: String

    @SerializedName("foto")
    lateinit var foto1: String


//id//////////////////////////////////
    fun setId(Ids: String){
        this.ids = ids
    }

    fun getId() : String{
        return ids
    }
//Nama Band//////////////////////////////
    fun setNama(nama1: String){
        this.nama1 = nama1
    }

    fun getNama() : String {
        return nama1
    }
//Tempat /////////////////////////////
    fun setTempat(tempat1: String){
        this.tempat1 = tempat1
    }

    fun getTempat() : String {
        return tempat1
    }
 //////////////////////////////////////////////////////
    fun setTanggal(tanggal1: String){
        this.tanggal1 = tanggal1
    }

    fun getTanggal() : String {
        return tanggal1
    }
///////////////////////////////
    fun setJam(jam1: String){
        this.jam1 = jam1
    }

    fun getJam() : String {
        return jam1
    }

    //////////////////////////////////////////////
    fun setFoto(foto1: String){
        this.foto1 = foto1
    }

    fun getfoto() : String {
        return foto1
    }





}