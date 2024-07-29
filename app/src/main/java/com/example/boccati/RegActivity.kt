package com.example.boccati

import android.app.Activity
import android.content.Intent
import android.content.IntentSender
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.identity.SignInCredential
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.firebase.auth.GoogleAuthProvider
import kotlin.Suppress
import kotlin.Suppress as KotlinSuppress

@Suppress("DEPRECATION")
class RegActivity : AppCompatActivity() {
    val callbackManager = CallbackManager.Factory.create()
    private lateinit var oneTapClient: SignInClient
    private lateinit var signInRequest: BeginSignInRequest

    private val REQ_ONE_TAP = 2
    private var showOneTapUI = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reg)

        val fb = findViewById<Button>(R.id.btn_face)
        val loginButton = findViewById<LoginButton>(R.id.login_button)

        fb.setOnClickListener {
            loginButton.performClick()
        }

        loginButton.setOnClickListener {
            LoginManager.getInstance().logInWithReadPermissions(this, listOf("public_profile"))
        }

        loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult) {
                Log.d("letsSee", "Facebook token: " + result.accessToken.token)
            }

            override fun onCancel() {
                Log.d("letsSee", "Facebook onCancel")
            }

            override fun onError(error: FacebookException) {
                Log.d("letsSee", "Facebook onError")
            }
        })

        val BtnGoogle = findViewById<Button>(R.id.btn_google)

        oneTapClient = Identity.getSignInClient(this)
        signInRequest = BeginSignInRequest.builder()
            .setPasswordRequestOptions(
                BeginSignInRequest.PasswordRequestOptions.builder()
                    .setSupported(true)
                    .build()
            )
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setServerClientId(getString(R.string.web_client_id))
                    .setFilterByAuthorizedAccounts(true)
                    .build()
            )
            .setAutoSelectEnabled(true)
            .build()

        val intentSender = registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                try {
                    val credential = oneTapClient.getSignInCredentialFromIntent(result.data)
                    val idToken = credential.googleIdToken
                    if (idToken != null) {
                        val email = credential.id
                        Toast.makeText(applicationContext, "Email: $email", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: ApiException) {
                    e.printStackTrace()
                }
            }
        }

        BtnGoogle.setOnClickListener { view ->
            oneTapClient.beginSignIn(signInRequest)
                .addOnSuccessListener(this) { result ->
                    try {
                        val intentSenderRequest = IntentSenderRequest.Builder(result.pendingIntent.intentSender).build()
                        intentSender.launch(intentSenderRequest)
                    } catch (e: IntentSender.SendIntentException) {
                        Log.e("TAG", "Não foi possível iniciar a interface One Tap: ${e.localizedMessage}")
                    }
                }
                .addOnFailureListener(this) { e ->
                    Log.d("TAG", e.localizedMessage)
                }
        }
    }
}





