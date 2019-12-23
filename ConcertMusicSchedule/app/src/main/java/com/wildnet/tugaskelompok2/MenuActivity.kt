package com.johnathan.tugaskelompok2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.jakewharton.rxbinding2.view.RxView
import com.johnathan.tugaskelompok2.Model.MenuModel
import com.wildnet.tugaskelompok2.R
import com.wildnet.tugaskelompok2.UserInterface.Login.LoginController
import kotlinx.android.synthetic.main.lay_menu.*
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class MenuActivity: AppCompatActivity() {

    lateinit var recyclerView : RecyclerView
    var list : MutableList<MenuModel> = mutableListOf()
    lateinit var adapter: MenuAdapter
    lateinit var layoutManager : RecyclerView.LayoutManager
    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    lateinit var prograssBar: ProgressBar
    var visibleItemCount: Int = 0
    var totalItemCount: Int = 0
    var pastVisibleItemCount: Int = 0
    var loading: Boolean = false
    var pageId: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lay_menu)

//        btn_logout= findViewById(R.id.btn_logout)
//
//        RxView.clicks(btn_logout)
//            .subscribe{
//                val intentLogout = Intent(this,Login::class.java)
//                startActivity(intentLogout)
//                Toast.makeText(this, " Anda telah keluar 1 detik yg lalu", Toast.LENGTH_SHORT).show()
//
//            }










        prograssBar = findViewById(R.id.progressBar)
        recyclerView = findViewById(R.id.recyclerView)
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        getProduk(pageId.toString())

       btnLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(intentFor<LoginController>().clearTask().clearTop())
            toast("Anda Telah Logout")
            finish()
        }


    }

    private fun getProduk(pageId : String) {

        prograssBar.visibility = View.VISIBLE
        var apiService : ApiService = RetrofitClient.getClient()!!.create(ApiService::class.java)
        var call : Call<List<MenuModel>> = apiService.getProdukList(pageId)
        call.enqueue(object : Callback<List<MenuModel>>{
            override fun onFailure(call: Call<List<MenuModel>>?, t: Throwable?) {

                try {
                    prograssBar.visibility = View.GONE
                    Log.d("tag", t!!.message)

                }catch (e : Exception){

                }

            }

            override fun onResponse(call: Call<List<MenuModel>>?, response: Response<List<MenuModel>>?) {

                if (response!!.code()==200) {
                    prograssBar.visibility = View.GONE
                    loading = true
                    setUpAdapter(response.body())
                }else{
                    prograssBar.visibility = View.GONE
                }

            }
        })

    }

    private fun setUpAdapter(body: List<MenuModel>?){
        if (list.size==0) {
            list = body as MutableList<MenuModel>
            adapter = MenuAdapter(list)
            recyclerView.adapter = adapter
        }else{
            var currentPosition = (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
            list.addAll(body!!)
            adapter.notifyDataSetChanged()
            recyclerView.scrollToPosition(currentPosition)
        }
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if ( dy > 0){
                    visibleItemCount = layoutManager.childCount
                    totalItemCount = layoutManager.itemCount
                    pastVisibleItemCount = (recyclerView!!.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                    if (loading){
                        if ((visibleItemCount + pastVisibleItemCount) >= totalItemCount)
                            loading = false
                            pageId++
                            getProduk(pageId.toString())
                    }
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }
        })
    }

}