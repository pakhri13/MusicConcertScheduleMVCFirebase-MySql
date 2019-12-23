package com.johnathan.tugaskelompok2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.bumptech.glide.Glide
import com.jakewharton.rxbinding2.view.RxView
import com.wildnet.tugaskelompok2.R
import kotlinx.android.synthetic.main.detail_activity.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {
    lateinit var btn_back: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)

         btn_back = findViewById(R.id.btn_back)

        RxView.clicks(btn_back)
            .subscribe{
                val intentLogout = Intent(this,MenuActivity::class.java)
                startActivity(intentLogout)
                finish()
            }

        val Nama : String = intent.getStringExtra("Nama")
        val Tempat : String = intent.getStringExtra("Tempat")
        val Tanggal : String = intent.getStringExtra("Tanggal")
        val Jam : String = intent.getStringExtra("Jam")
        val foto : String = intent.getStringExtra("Foto")

        tvNama.text=Nama
        tvTanggal.text=Tanggal
        tvTempat.text=Tempat
        tvJam.text=Jam

        Glide.with(this).load(foto).into(imgItem)

    }
}
