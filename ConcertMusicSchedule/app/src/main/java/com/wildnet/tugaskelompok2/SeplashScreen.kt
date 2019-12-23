package com.wildnet.tugaskelompok2

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.wildnet.tugaskelompok2.UserInterface.Login.LoginController

class SeplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.swipe_gettingstarted)

        Handler().postDelayed({
            startActivity(Intent(this, LoginController::class.java))
            finish()
        }, 5000)

    }
}