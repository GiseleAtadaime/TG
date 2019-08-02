package com.example.tg

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_login.*

class LoginActivity : AppCompatActivity() , LoginFragment.OnFragmentInteractionListener {
    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogEntrar.setOnClickListener {
            supportFragmentManager.inTransaction {
                add(R.id.frmLoginContainer, LoginFragment())
            }
            btnLogEntrar.visibility = View.INVISIBLE
        }
    }
}
