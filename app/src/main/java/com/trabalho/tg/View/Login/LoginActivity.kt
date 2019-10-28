package com.trabalho.tg.View.Login

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.view.View
import com.trabalho.tg.Controller.C_Usuario
import com.trabalho.tg.Helper.DBHelper
import com.trabalho.tg.R
import com.trabalho.tg.View.MainActivity
import kotlinx.android.synthetic.main.activity_login.*
import java.io.Serializable

class LoginActivity : AppCompatActivity() , LoginFragment.OnFragmentInteractionListener,
    LoginNewFragment.OnFragmentInteractionListener {
    override fun onLoginButtonPressed(tipo: Int) {
        if(tipo == 0){
            var usuario = C_Usuario().selectUsuario(DBHelper(this))

            val intent = Intent (this, MainActivity::class.java)
            intent.putExtra("usuario", usuario as Serializable)
            startActivity(intent)
        }
        else if(tipo == 1){
            changeFragment(LoginNewFragment(), true)
        }
    }

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        btnLogEntrar.setOnClickListener {
            changeFragment(LoginFragment(), false)
            //changeFragment(LoginNewFragment(), false)

           // val intent = Intent (this, MainActivity::class.java)
           // startActivity(intent)
        }


    }

    fun changeFragment(fragmentName: Fragment, change : Boolean){

        if (!this.isFinishing && !this.isDestroyed){
            var transaction : FragmentTransaction = supportFragmentManager.beginTransaction()
            if (change) {
                transaction.replace(R.id.frmLoginContainer,fragmentName).addToBackStack(null).commit()      }
            else{
                transaction.add(R.id.frmLoginContainer,fragmentName).commit()
            }
            btnLogEntrar.visibility = View.INVISIBLE
        }

    }

    fun removeFragment(fragmentName : Fragment){
        var transaction : FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.remove(fragmentName).commit()
    }


}
