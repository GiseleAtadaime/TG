package com.trabalho.tg

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class LandingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)

        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}
