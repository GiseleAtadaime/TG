package com.trabalho.tg.View

import android.app.AlertDialog
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
import android.widget.Toast
import com.trabalho.tg.Controller.C_Area
import com.trabalho.tg.Helper.Contrato
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

    override fun onCloseAreaDialog() {
        usuario.usr_area = C_Area().selectArea(DBHelper(this), true)
        removeFragment("AREA_ALTER_FRAGMENT")
        changeFragment(AreaFragment.newInstance(usuario.usr_area), true, "AREA_FRAGMENT")
    }
    override fun onEntradaSelected(entrada: Entrada) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onAlterButtonClick(entrada: ArrayList<Entrada>) {
        changeFragment(EntradaFragment(), true, "ENTRADA_FRAGMENT")
    }

    override fun onLoteSelected(lote : List<Lote>, pos : Int){
        //Toast.makeText(this, "Escolhido $pos", Toast.LENGTH_SHORT).show()
        changeFragment(LoteDetalheFragment(), true, "LOTE_DETALHE_FRAGMENT")
    }
    override fun onAreaSelected(area: List<Area>, pos : Int, tipo : Int) {
        Toast.makeText(this, "Escolhido $pos", Toast.LENGTH_SHORT).show()
        if (tipo == 0) { //lote
            changeFragment(LoteFragment(), true, "LOTE_FRAGMENT")
        }
        else if(tipo == 1) { //alterar
            changeFragment(AreaCriaAlterDialog.newInstance(area.get(pos -1), tipo, usuario.usr_id), true, "AREA_ALTER_FRAGMENT")
        }
        else if (tipo == 2){ // deletar
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Deletar área")
            builder.setMessage("Tem certeza que deseja apagar a área ${area.get(pos).ar_nome}? Esta operação não pode ser desfeita!")

            builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                area.get(pos).ar_del = Contrato.Area.STATUS_DELETADO
                if (C_Area().updateArea(DBHelper(this),area.get(pos -1))){
                    dialog.dismiss()
                    onCloseAreaDialog()
                }
                else{
                    Toast.makeText(this, "Não foi possível apagar a área selecionada!", Toast.LENGTH_SHORT).show()
                    area.get(pos).ar_del = Contrato.Area.STATUS_ATIVO
                    dialog.dismiss()
                }

            }
            builder.setNegativeButton(android.R.string.no){dialog, which ->
                dialog.dismiss()
            }

            builder.show()

        }
        else if(tipo == 3){ //adicionar
            changeFragment(AreaCriaAlterDialog.newInstance(area.get(pos), tipo, usuario.usr_id), true , "AREA_ALTER_FRAGMENT")
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
            var area = Area(0)
            area.ar_imagem = null
            area.ar_nome = "area normal 1"
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
                    changeFragment(MainFragment(), true, "MAIN_FRAGMENT")
                }

            }
            R.id.nav_area -> {
                changeFragment(AreaFragment.newInstance(usuario.usr_area), true, "AREA_FRAGMENT")
            }
            R.id.nav_fechado -> {
                changeFragment(FechadoFragment(), true, "FECHADO_FRAGMENT")
            }
            R.id.nav_qrcode -> {
                changeFragment(QRFragment(), true, "QRCODE_FRAGMENT")
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

    fun changeFragment(fragmentName: Fragment, change : Boolean, tag : String){

        if (!this.isFinishing && !this.isDestroyed){
            var transaction : FragmentTransaction = supportFragmentManager.beginTransaction()
            if (change) {
                transaction.replace(R.id.frmMainContainer,fragmentName, tag).addToBackStack(null).commit()      }
            else{
                transaction.add(R.id.frmMainContainer,fragmentName, tag).commit()
            }
        }
    }
    fun removeFragment(tag : String){
        val fragment = supportFragmentManager.findFragmentByTag(tag)
        if (fragment != null)
            supportFragmentManager.beginTransaction().remove(fragment).commit()
    }





}
