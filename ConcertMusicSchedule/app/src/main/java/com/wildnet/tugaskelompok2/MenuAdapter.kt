package com.johnathan.tugaskelompok2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import android.content.Intent

import android.app.Activity

import android.widget.Button

import com.johnathan.tugaskelompok2.Model.MenuModel
import com.wildnet.tugaskelompok2.R


class MenuAdapter : RecyclerView.Adapter<MenuAdapter.Companion.Holder> {

    lateinit var mactivity : Activity
    lateinit var context: Context
    lateinit var rv:View
    lateinit var rd:View
    lateinit var list: List<MenuModel>

    constructor(list: List<MenuModel>) : super() {
        this.list = list
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

        var holder : Holder
        context = parent!!.context
        rv = LayoutInflater.from(parent.context).inflate(R.layout.lay_item_menu,parent,false)
        holder = Holder(rv)

        return holder



    }


    override fun getItemCount(): Int {

        return list.size

    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder!!.tvNama.setText(list.get(position).getNama())
        holder!!.tvTempat.setText(list.get(position).getTempat())
        holder!!.tvTanggal.setText(list.get(position).getTanggal())
        holder!!.tvJam.setText(list.get(position).getJam())

        val img_url="http://172.16.10.238/konser/public/storage/" +list.get(position).getfoto()
        Glide.with(context).load(img_url).into(holder.foto)

        holder.tmbl_detail.setOnClickListener{
            val tvNama : String = list.get(position).getNama()
            val tvTempat : String = list.get(position).getTempat()
            val tvTanggal : String = list.get(position).getTanggal()
            val tvJam : String = list.get(position).getJam()
            val foto : String =  img_url


            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra("Nama", tvNama)
            intent.putExtra("Tempat", tvTempat)
            intent.putExtra("Tanggal", tvTanggal)
            intent.putExtra("Jam", tvJam)
            intent.putExtra("Foto", foto)
            context.startActivity(intent)



        }
//        holder.tmbl_detail.setOnClickListener {
//            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(list.get(position).getIsi())))
//        }
    }

    companion object {

        class Holder : RecyclerView.ViewHolder{
            lateinit var tvNama: TextView
            lateinit var tvTanggal: TextView
            lateinit var tvTempat: TextView
            lateinit var tvJam: TextView
            lateinit var foto: ImageView
            lateinit var tmbl_detail:Button


            constructor(itemView: View) : super(itemView) {
                tvNama = itemView!!.findViewById(R.id.tvNama)
                tvTempat = itemView!!.findViewById(R.id.tvTempat)
                tvTanggal = itemView!!.findViewById(R.id.tvTanggal)
                tvJam = itemView!!.findViewById(R.id.tvJam)
                foto = itemView.findViewById(R.id.imgItem)
                tmbl_detail=itemView.findViewById(R.id.btn_detail)
            }
        }

    }



}