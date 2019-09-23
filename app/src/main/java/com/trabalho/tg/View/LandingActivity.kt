package com.trabalho.tg.View

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.trabalho.tg.R
import com.trabalho.tg.View.Login.LoginActivity

class LandingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)

        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}
