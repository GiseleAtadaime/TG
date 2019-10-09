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
import com.trabalho.tg.Controller.C_Entrada
import com.trabalho.tg.Controller.C_Lote
import com.trabalho.tg.Helper.Contrato
import com.trabalho.tg.Helper.DBHelper
import com.trabalho.tg.Helper.Utils_TG
import com.trabalho.tg.Model.Area
import com.trabalho.tg.Model.Entrada
import com.trabalho.tg.Model.Lote
import com.trabalho.tg.Model.Usuario
import com.trabalho.tg.R
import com.trabalho.tg.View.Detalhe.AreaCriaAlterDialog
import com.trabalho.tg.View.Detalhe.EntradaCriaAlterDialog
import com.trabalho.tg.View.Detalhe.EntradaDetalheDialog
import com.trabalho.tg.View.Detalhe.LoteCriaAlterDialog
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
    AreaCriaAlterDialog.OnFragmentInteractionListener,
    LoteCriaAlterDialog.OnFragmentInteractionListener,
    EntradaDetalheDialog.OnFragmentInteractionListener,
    EntradaCriaAlterDialog.OnFragmentInteractionListener
{
    override fun onAlterDetalheClick(entrada: Entrada, userId: Int, areaId: Int, loteId: Int, fecha : Boolean) {
        var indexArea = usuario.usr_area.indexOfFirst { it.ar_id == areaId }
        var indexLote = usuario.usr_area[indexArea].ar_lote.indexOfFirst { it.lot_id ==  loteId}

        if (fecha){



            usuario.usr_area[indexArea].ar_lote[indexLote].lot_ent = C_Entrada().selectEntrada(DBHelper(this),loteId)

            removeFragment("ENTRADA_DETALHE_DIALOG")

            changeFragment(EntradaFragment.newInstance(
                usuario.usr_area[indexArea].ar_lote[indexLote],
                usuario.usr_id,areaId), true, "ENTRADA_FRAGMENT")
        }
        else{
            changeFragment(EntradaCriaAlterDialog.newInstance( entrada,usuario.usr_id,areaId,loteId),
                true, "ENTRADA_CRIA_ALTER_DIALOG")
        }

    }

    override fun onCloseLoteDialog(areaID: Int) {
        var indexArea = usuario.usr_area.indexOfFirst { it.ar_id == areaID }

        usuario.usr_area[indexArea].ar_lote = C_Lote().selectLote(DBHelper(this), areaID)

        removeFragment("LOTE_ALTER_FRAGMENT")

        changeFragment(LoteFragment.newInstance(usuario.usr_area[usuario.usr_area.indexOfFirst { it.ar_id == areaID }]),
            true, "LOTE_FRAGMENT")
    }

    override fun onCloseAreaDialog() {
        usuario.usr_area = C_Area().selectArea(DBHelper(this), true)
        removeFragment("AREA_ALTER_FRAGMENT")
        changeFragment(AreaFragment.newInstance(usuario.usr_area), true, "AREA_FRAGMENT")
    }
    override fun onEntradaSelected(entrada: Entrada, userId : Int, areaId : Int, loteId : Int) {
        changeFragment(EntradaDetalheDialog.newInstance(entrada, userId,areaId,loteId), true, "ENTRADA_DETALHE_DIALOG")
    }


    //Alter button for lote fragment
    override fun onAlterButtonClick(lote : Lote,  tipo : Int, areaId :Int, userId : Int, loteId : Int) {
        var indexArea = usuario.usr_area.indexOfFirst { it.ar_id == areaId }
        var indexLote = usuario.usr_area[indexArea].ar_lote.indexOfFirst { it.lot_id ==  loteId}

        if(tipo == 1){//Alter lote
            changeFragment(LoteCriaAlterDialog.newInstance(
                usuario.usr_area[indexArea].ar_lote[indexLote]
                ,tipo,usuario.usr_id,areaId),true, "LOTE_ALTER_FRAGMENT")
        }
        else if(tipo == 2){//Del lote
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Deletar lote")
            builder.setMessage("Tem certeza que deseja apagar o lote " +
                    "${usuario.usr_area[indexArea].ar_lote[indexLote].lot_nome}? Esta operação não pode ser desfeita!")

            builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                if(usuario.usr_area[indexArea].ar_lote.size > 1){
                    if (C_Lote().deleteLote(DBHelper(this), usuario.usr_area[indexArea].ar_lote[indexLote])){
                        dialog.dismiss()
                        onCloseLoteDialog(areaId)
                    }
                    else {
                        Toast.makeText(this, "Não foi possível apagar o lote selecionado!", Toast.LENGTH_SHORT).show()
                        dialog.dismiss()
                    }
                }
                else{
                        Toast.makeText(this, "Não é possível apagar pois só há este lote cadastrado!", Toast.LENGTH_SHORT).show()
                        dialog.dismiss()
                }
            }

            builder.setNegativeButton(android.R.string.no){dialog, which ->
                dialog.dismiss()
            }

            builder.show()
        }
        else if(tipo == 3){//Fechar lote

        }
        else if(tipo == 4){//Novo entrada



        }
        else if(tipo == 5){//Ver entradas
            usuario.usr_area[indexArea].ar_lote[indexLote].lot_ent = C_Entrada().selectEntrada(DBHelper(this),loteId)

            if (usuario.usr_area[indexArea].ar_lote[indexLote].lot_ent.size == 0){
                createEntrada(areaId, loteId)
            }
            usuario.usr_area[indexArea].ar_lote[indexLote].lot_ent = C_Entrada().selectEntrada(DBHelper(this),loteId)

            changeFragment(EntradaFragment.newInstance(
                usuario.usr_area[indexArea].ar_lote[indexLote],
                usuario.usr_id,areaId), true, "ENTRADA_FRAGMENT")
        }
    }

    override fun onLoteSelected(lote : List<Lote>, pos : Int, tipo : Int, areaId : Int){
        var indexArea = usuario.usr_area.indexOfFirst { it.ar_id == areaId }
        if (tipo == 0) { //lote
            changeFragment(LoteDetalheFragment.newInstance(usuario.
                usr_area[indexArea].ar_lote[pos]
                ,usuario.usr_id, areaId), true, "LOTE_DETALHE_FRAGMENT")
        }
        else if(tipo == 1 || tipo == 3) { //alterar
            changeFragment(LoteCriaAlterDialog.newInstance(lote[pos],tipo,usuario.usr_id,areaId),true, "LOTE_ALTER_FRAGMENT")
        }
        else if (tipo == 2){ // deletar

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Deletar lote")
            builder.setMessage("Tem certeza que deseja apagar o lote ${lote[pos].lot_nome}? Esta operação não pode ser desfeita!")

            builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                if(usuario.usr_area[indexArea].ar_lote.size > 1){
                    if (C_Lote().deleteLote(DBHelper(this), usuario.usr_area[indexArea].ar_lote[pos])){
                        dialog.dismiss()
                        onCloseLoteDialog(areaId)
                    }
                    else {
                        Toast.makeText(this, "Não foi possível apagar o lote selecionado!", Toast.LENGTH_SHORT).show()
                        dialog.dismiss()
                    }
                }
                else{
                    Toast.makeText(this, "Não é possível apagar pois só há este lote cadastrado!", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }

            }
            builder.setNegativeButton(android.R.string.no){dialog, which ->
                dialog.dismiss()
            }

            builder.show()

        }

    }

    override fun onAreaSelected(area: List<Area>, pos : Int, tipo : Int) {
        if (tipo == 0) { //lote
            usuario.usr_area[pos].ar_lote = C_Lote().selectLote(DBHelper(this),usuario.usr_area[pos].ar_id)
            if (usuario.usr_area[pos].ar_lote.size == 0){
                createLote(pos)
            }
            usuario.usr_area[pos].ar_lote = C_Lote().selectLote(DBHelper(this),usuario.usr_area[pos].ar_id)
            changeFragment(LoteFragment.newInstance(usuario.usr_area[pos]), true, "LOTE_FRAGMENT")
        }
        else if(tipo == 1 || tipo == 3) { //alterar
            changeFragment(AreaCriaAlterDialog.newInstance(area[pos], tipo, usuario.usr_id), true, "AREA_ALTER_FRAGMENT")
        }
        else if (tipo == 2){ // deletar

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Deletar área")
            builder.setMessage("Tem certeza que deseja apagar a área ${area[pos].ar_nome}? Esta operação não pode ser desfeita!")

            builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                if(usuario.usr_area.size > 1){
                    area[pos].ar_del = Contrato.Area.STATUS_DELETADO
                    if (C_Area().updateArea(DBHelper(this), area[pos])){
                        dialog.dismiss()
                        onCloseAreaDialog()
                    }
                    else{
                        Toast.makeText(this, "Não foi possível apagar a área selecionada!", Toast.LENGTH_SHORT).show()
                        area[pos].ar_del = Contrato.Area.STATUS_ATIVO
                        dialog.dismiss()
                    }
                }
                else{
                    Toast.makeText(this, "Não é possível apagar pois só há esta área cadastrada!", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }


            }
            builder.setNegativeButton(android.R.string.no){dialog, which ->
                dialog.dismiss()
            }

            builder.show()

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
        usuario.usr_area = C_Area().selectArea(DBHelper(this), true)
        if (usuario.usr_area.size == 0){
            createArea()
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

    fun createArea(){
        var dbHelper : DBHelper = DBHelper(this)
        var area = Area(0)
        area.ar_imagem = null
        area.ar_nome = "area normal 1"
        area.setAr_lote_cont(0)
        area.ar_del = Contrato.Area.STATUS_ATIVO
        if (C_Area().insertArea(dbHelper, area, usuario.usr_id)){
            usuario.usr_area = C_Area().selectArea(dbHelper, true)
        }

    }
    fun createLote(pos : Int){
        var dbHelper : DBHelper = DBHelper(this)
        var lote = Lote(0)
        lote.lot_nome = "lote normal 1"
        lote.lot_planta = "repolho"
        lote.lot_imagem = null
        if(C_Lote().insertLote(dbHelper,lote,usuario.usr_area[pos].ar_id,usuario.usr_id)){
            usuario.usr_area[pos].ar_lote = C_Lote().selectLote(dbHelper,usuario.usr_area[pos].ar_id)
        }
    }
    fun createEntrada(areaID : Int, loteID : Int){
        var dbHelper : DBHelper = DBHelper(this)
        var entrada = Entrada(0)
        entrada.ent_desc = Contrato.Tipo_Entrada.PLANTIO
        entrada.setEnt_data(10,10,2018)
        entrada.ent_tipo = 0
        entrada.ent_tempo = null
        entrada.ent_tpun = null
        entrada.ent_qtde = 50.0
        entrada.ent_qtun = Contrato.Tipo_Entrada.QTDE_TIPO_PLANTIO
        entrada.ent_mudas_bandeja = 40
        entrada.ent_valor = 4500.00
        entrada.ent_reg = null

        if (C_Entrada().insertEntrada(dbHelper,entrada,loteID, usuario.usr_id)){
            usuario.usr_area[usuario.usr_area.indexOfFirst { it.ar_id == areaID }]
                .ar_lote[usuario.usr_area[usuario.usr_area.indexOfFirst { it.ar_id == areaID }]
                .ar_lote.indexOfFirst { it.lot_id ==  loteID}]
                .lot_ent = C_Entrada().selectEntrada(dbHelper,loteID)
        }
    }



}
