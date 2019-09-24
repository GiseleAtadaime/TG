package com.trabalho.tg.View

import android.net.Uri
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.support.v4.widget.DrawerLayout
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import com.trabalho.tg.Controller.C_Area
import com.trabalho.tg.Helper.DBHelper
import com.trabalho.tg.Model.Area
import com.trabalho.tg.Model.Entrada
import com.trabalho.tg.Model.Lote
import com.trabalho.tg.Model.Usuario
import com.trabalho.tg.R
import com.trabalho.tg.View.Detalhe.AreaCriaAlterDialog
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener,
    MainFragment.OnFragmentInteractionListener,
    AreaFragment.OnFragmentInteractionListener,
    FechadoFragment.OnFragmentInteractionListener,
    QRFragment.OnFragmentInteractionListener,
    LoteFragment.OnFragmentInteractionListener,
    LoteDetalheFragment.OnFragmentInteractionListener,
    EntradaFragment.OnFragmentInteractionListener,
    AreaCriaAlterDialog.OnFragmentInteractionListener
{

    override fun onEntradaSelected(entrada: Entrada) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onAlterButtonClick(entrada: ArrayList<Entrada>) {
        changeFragment(EntradaFragment(), true)
    }

    override fun onLoteSelected(lote : List<Lote>, pos : Int){
        //Toast.makeText(this, "Escolhido $pos", Toast.LENGTH_SHORT).show()
        changeFragment(LoteDetalheFragment(), true)
    }
    override fun onAreaSelected(area: List<Area>, pos : Int, tipo : Int) {
        //Toast.makeText(this, "Escolhido $pos", Toast.LENGTH_SHORT).show()
        if (tipo == 0) { //lote
            changeFragment(LoteFragment(), true)
        }
        else if(tipo == 1) { //alterar
            changeFragment(AreaCriaAlterDialog.newInstance(area.get(pos -1), tipo), true)
        }
        else if (tipo == 2){ // deletar
            changeFragment(AreaCriaAlterDialog.newInstance(area.get(pos -1), tipo), true)
        }
        else if(tipo == 3){ //adicionar
            changeFragment(AreaCriaAlterDialog.newInstance(area.get(pos -1), tipo), true)
        }

    }

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    lateinit var usuario : Usuario

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)

        supportFragmentManager.inTransaction {
            add(R.id.frmMainContainer, MainFragment())
        }
        usuario = intent.extras.get("usuario") as Usuario
        var dbHelper : DBHelper = DBHelper(this)
        usuario.usr_area = C_Area().selectArea(dbHelper, true)
        if (usuario.usr_area.size == 0){
            var area = Area(1)
            area.ar_imagem = null
            area.ar_nome = "area 1"
            area.ar_lote_cont = "A"
            if (C_Area().insertArea(dbHelper, area, usuario.usr_id)){
                usuario.usr_area = C_Area().selectArea(dbHelper, true)
            }
        }

    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_main -> {
                if (!MainFragment().isVisible){
                    changeFragment(MainFragment(), true)
                }

            }
            R.id.nav_area -> {
                changeFragment(AreaFragment.newInstance(usuario.usr_area), true)
            }
            R.id.nav_fechado -> {
                changeFragment(FechadoFragment(), true)
            }
            R.id.nav_qrcode -> {
                changeFragment(QRFragment(), true)
            }
            R.id.nav_opt -> {

            }
            R.id.nav_sair -> {

            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    fun changeFragment(fragmentName: Fragment, change : Boolean){

        if (!this.isFinishing && !this.isDestroyed){
            var transaction : FragmentTransaction = supportFragmentManager.beginTransaction()
            if (change) {
                transaction.replace(R.id.frmMainContainer,fragmentName).addToBackStack(null).commit()      }
            else{
                transaction.add(R.id.frmMainContainer,fragmentName).commit()
            }
        }
    }





}
