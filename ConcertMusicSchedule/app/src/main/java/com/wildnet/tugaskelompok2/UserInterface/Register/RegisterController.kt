package com.wildnet.tugaskelompok2.UserInterface.Register

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.wildnet.tugaskelompok2.R
import com.wildnet.tugaskelompok2.UserInterface.Login.LoginController
import kotlinx.android.synthetic.main.layout_register.*
import org.jetbrains.anko.*

class RegisterController : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_register)

        btn_to_login.setOnClickListener {
            startActivity(intentFor<LoginController>().clearTask().clearTop())
            toast("You Are Now Entering the Login Form")
        }

        button_registerasi.setOnClickListener {
            val email = register_email.text.toString()
            val password = register_password.text.toString()
            val conf_password = register_conf_password.text.toString()

            if (email.isEmpty() && password.isEmpty() && conf_password.isEmpty()) {
                toast("Please Fill All The Form")
                return@setOnClickListener
            }

            if (conf_password.equals(password)) {
                val progressDialog = ProgressDialog(this)
                progressDialog.isIndeterminate = true
                progressDialog.setMessage("Registration in Process...")
                progressDialog.show()
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener {
                        task : Task<AuthResult> ->
                        if (task.isSuccessful) {
                            toast("Register Successfull")
                            startActivity(intentFor<LoginController>().clearTop().clearTask())
                            progressDialog.hide()
                        } else {
                            toast("Register Failure")
                            startActivity(intentFor<RegisterController>())
                            progressDialog.hide()
                        }
                    }
            } else {
                toast("Confirm Password Not Match With Password")
                return@setOnClickListener
            }
        }
    }
}