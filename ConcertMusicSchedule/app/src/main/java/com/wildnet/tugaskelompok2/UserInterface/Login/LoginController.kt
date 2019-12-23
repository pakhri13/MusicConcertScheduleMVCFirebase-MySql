package com.wildnet.tugaskelompok2.UserInterface.Login

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.johnathan.tugaskelompok2.MenuActivity
import com.wildnet.tugaskelompok2.R
import com.wildnet.tugaskelompok2.UserInterface.Register.RegisterController
import kotlinx.android.synthetic.main.layout_login.*
import org.jetbrains.anko.*

class LoginController : AppCompatActivity() {

    lateinit var mGoogleSignInClient: GoogleSignInClient
    lateinit var gso: GoogleSignInOptions
    val RC_SIGN_IN: Int = 1
    private var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_login)
        auth = FirebaseAuth.getInstance()



        btn_to_register.setOnClickListener {
            startActivity(intentFor<RegisterController>().clearTask().clearTop())
            toast("You Are Now Entering the Registration Form")
        }


        btn_login.setOnClickListener {
            val email = logn_email.text.toString()
            val password = logn_password.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                toast("Please Fill Form Email dan Form Password")
                return@setOnClickListener
            } else {
                Log.e("fungsi login", "Login Failure : ${it}")
            }
            if (email == "admin@mail.com" && password == "admin123") {
                toast("Hai Admin, Welcome to Admin Site")
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val progressDialog = ProgressDialog(this)
                progressDialog.isIndeterminate = true
                progressDialog.setMessage("Logiing in Proccess")
                progressDialog.show()

                FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        if (!it.isSuccessful) {
                            return@addOnCompleteListener
                        } else {
                            toast("Login Successful, Welcome : " + email)
                            startActivity(intentFor<MenuActivity>().clearTask().clearTop())
                            progressDialog.hide()
                            finish()
                        }
                    }
                    .addOnFailureListener {
                        Log.e("fungsi login","Login Failure : ${it.message}")
                        toast("Invalid Email or Password")
                        progressDialog.hide()
                    }
            }
        }

        val signin = findViewById<View>(R.id.sign_in_google) as SignInButton
        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        signin.setOnClickListener {
                v: View? -> signInGoogle()
        }
    }

    private fun signInGoogle() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN){
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleResult(task)
        }
    }

    private fun handleResult(completeTask: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount? = completeTask.getResult(ApiException::class.java)
            updateUI(account)
        }catch (e: ApiException){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
        }

    }
    private fun updateUI(account: GoogleSignInAccount?) {
        val target =  Intent(this, MenuActivity::class.java)
        startActivity(target)
        toast("Login Successful, Welcome User")
        finish()
    }



}