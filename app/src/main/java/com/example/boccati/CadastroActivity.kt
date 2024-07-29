package com.example.boccati

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText

@Suppress("NAME_SHADOWING")
class CadastroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cadastro)
        val edtPass = findViewById<TextInputEditText>(R.id.edPass)
        val edtUser = findViewById<TextInputEditText>(R.id.edtUser)
        val btnEnter = findViewById<Button>(R.id.btn_enter)

        btnEnter.setOnClickListener {

            btnEnter.setOnClickListener {
                if (edtUser.text.toString() == "" || edtPass.text.toString() == "") {
                    Snackbar
                        .make(
                            it,
                            "Preencha todos os campos",
                            Snackbar.LENGTH_LONG
                        )
                        .show()
                } else {
                    val intent = Intent(this, FirstActivity::class.java)

                    startActivity(intent)
                }
            }

        }
        val btnDesejo = findViewById<TextView>(R.id.nao_desejo)
        btnDesejo.setOnClickListener {
            val intent = Intent(this, FirstActivity::class.java)
            startActivity(intent)
        }

        val btnReg = findViewById<Button>(R.id.btn_Registrar)
        btnReg.setOnClickListener {
            val intent = Intent(this, RegActivity::class.java)
            startActivity(intent)
        }
    }
}
