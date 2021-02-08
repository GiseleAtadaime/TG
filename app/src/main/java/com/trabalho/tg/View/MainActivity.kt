package com.trabalho.tg.View

import android.content.Context
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import android.net.Uri
import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.widget.Toast
import com.trabalho.tg.Controller.*
import com.trabalho.tg.Helper.Contrato
import com.trabalho.tg.Helper.DBHelper
import com.trabalho.tg.Helper.Report_Helper
import com.trabalho.tg.Model.*
import com.trabalho.tg.R
import com.trabalho.tg.View.Detalhe.AreaCriaAlterDialog
import com.trabalho.tg.View.Detalhe.EntradaCriaAlterDialog
import com.trabalho.tg.View.Detalhe.EntradaDetalheDialog
import com.trabalho.tg.View.Detalhe.LoteCriaAlterDialog
import com.trabalho.tg.View.Usuario.UsuarioInfoAlter
import com.trabalho.tg.View.Usuario.UsuarioInfoGeral
import com.trabalho.tg.View.Usuario.Usuario_Endereco_Alter
import java.util.*


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
    EntradaCriaAlterDialog.OnFragmentInteractionListener,
    UsuarioInfoGeral.OnFragmentInteractionListener,
    UsuarioInfoAlter.OnFragmentInteractionListener,
    Usuario_Endereco_Alter.OnFragmentInteractionListener,
    reportGenerateDetails.OnFragmentInteractionListener,
    LoteFechadoFragment.OnFragmentInteractionListener
{

    val FECHADO : Boolean = false
    val ABERTO : Boolean = true

    override fun onLoteFechadoSelected(lote : List<Lote_Fechado>, pos : Int, tipo : Int, areaId : Int) {

        changeFragment(LoteDetalheFragment.newInstance(null, lote[pos],usuario.usr_id,areaId), true, "LOTE_FECHADO_DETALHE_FRAGMENT")
    }

    override fun onAreaFechadosSelected(area: List<Area>, pos: Int) {
        //newInstance(usuario.usr_area[pos]
        changeFragment(LoteFechadoFragment.newInstance(area[pos]), true, "LOTE_FECHADO_FRAGMENT")
    }

    override fun onEndClick(areaID: Int, lotID : Int?, type : Boolean) {
        var aID =  usuario.usr_area[areaID].ar_id
        var lID = usuario.usr_area[areaID].ar_lote[lotID!!].lot_id

        removeFragment("GENERATE_REPORT_FRAGMENT")
        if(type){
            usuario.usr_area[areaID].ar_lote[lotID].fecharLote(this,aID,lID)
            usuario.reloadAreas(this)
            usuario.loadFechados(this)
            clearBackStack()
            changeFragment(FechadoFragment.newInstance(usuario.usr_area), true, "FECHADO_FRAGMENT")
        }

    }

    override fun onEnderecoClick(usuario: Usuario) {
        clearBackStack()
        usuario.usr_user_info.info_endereco = C_Endereco().selectEndereco(DBHelper(this), usuario.usr_user_info.info_id)
        changeFragment(UsuarioInfoGeral.newInstance(usuario), true, "USUARIOGERAL_FRAGMENT")
    }

    override fun onSaveUsuarioInfo() {
        usuario.usr_user_info = C_User_Info().selectUser_Info(DBHelper(this))
        returnFragment(2)

    }

    override fun onInfoClick(usuario: Usuario, tipo: Int, endID : Int?) {
        if(tipo == 1){
            changeFragment( UsuarioInfoAlter.newInstance(usuario), true, "USUARIO_INFO_ALTER")
        }
        else if(tipo == 2){

            changeFragment( Usuario_Endereco_Alter.newInstance(usuario, endID!!,false), true, "USUARIO_ENDERECO_ADD")
        }
        else if(tipo == 3){

            if(C_Endereco().deleteEndereco(DBHelper(this), endID!!)){
                Toast.makeText(this, "Endereço removido!", Toast.LENGTH_SHORT).show()
                clearBackStack()
                usuario.usr_user_info.info_endereco = C_Endereco().selectEndereco(DBHelper(this), usuario.usr_user_info.info_id)
                changeFragment(UsuarioInfoGeral.newInstance(usuario), true, "USUARIOGERAL_FRAGMENT")
            }
        }
        else if(tipo == 4){
            changeFragment( Usuario_Endereco_Alter.newInstance(usuario, endID!!, true), true, "USUARIO_ENDERECO_ALTER")
        }

    }

    override fun onCriaAlterDialog(userId: Int, areaId: Int, loteId: Int) {
        var indexArea = usuario.usr_area.indexOfFirst { it.ar_id == areaId }
        var indexLote = usuario.usr_area[indexArea].ar_lote.indexOfFirst { it.lot_id ==  loteId}

        usuario.usr_area[indexArea].ar_lote[indexLote].lot_ent = C_Entrada().selectEntrada(DBHelper(this),loteId)
        removeFragment("ENTRADA_CRIA_ALTER_DIALOG")

        changeFragment(EntradaFragment.newInstance(
            usuario.usr_area[indexArea].ar_lote[indexLote],
            usuario.usr_id,areaId, ABERTO), true, "ENTRADA_FRAGMENT")

    }

    override fun onAlterDetalheClick(entrada: Entrada, userId: Int, areaId: Int, loteId: Int, fecha : Boolean) {
        var indexArea = usuario.usr_area.indexOfFirst { it.ar_id == areaId }
        var indexLote = usuario.usr_area[indexArea].ar_lote.indexOfFirst { it.lot_id ==  loteId}

        if (fecha){

            usuario.usr_area[indexArea].ar_lote[indexLote].lot_ent = C_Entrada().selectEntrada(DBHelper(this),loteId)

            removeFragment("ENTRADA_DETALHE_DIALOG")

            changeFragment(EntradaFragment.newInstance(
                usuario.usr_area[indexArea].ar_lote[indexLote],
                usuario.usr_id,areaId, ABERTO), true, "ENTRADA_FRAGMENT")
        }
        else{
            var datamin : Date? = usuario.usr_area[indexArea].ar_lote[indexLote].lot_ent[0].ent_data
            changeFragment(EntradaCriaAlterDialog.newInstance(entrada,usuario.usr_id,areaId,loteId, datamin),
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

    override fun onEntradaSelected(entrada: Entrada, userId : Int, areaId : Int, loteId : Int, new : Boolean, tipolote : Boolean) {
        var indexArea = usuario.usr_area.indexOfFirst { it.ar_id == areaId }
        var indexLote = usuario.usr_area[indexArea].ar_lote.indexOfFirst { it.lot_id ==  loteId}
        if (new){
            var datamin : Date? = null
            usuario.usr_area[indexArea].ar_lote[indexLote].lot_ent = C_Entrada().selectEntrada(DBHelper(this),loteId)
            if(usuario.usr_area[indexArea].ar_lote[indexLote].lot_ent.size == 0){
                datamin = null
            }
            else{
                datamin = usuario.usr_area[indexArea].ar_lote[indexLote].lot_ent[0].ent_data
            }
            changeFragment(EntradaCriaAlterDialog.newInstance(null,usuario.usr_id,areaId,loteId, datamin),
                true, "ENTRADA_CRIA_ALTER_DIALOG")
        }
        else{
            changeFragment(EntradaDetalheDialog.newInstance(entrada, userId,areaId,loteId, tipolote), true, "ENTRADA_DETALHE_DIALOG")
        }

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
                    if (C_Lote().deleteLote(DBHelper(this), usuario.usr_area[indexArea].ar_lote[indexLote].lot_id)){
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
            var incompleto = true;

            if(usuario.usr_user_info != null){
                if(usuario.usr_user_info.info_endereco.size > 0){

                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Fechar lote")
                    builder.setMessage("Tem certeza que gostaria de finalizar as operações do lote ${usuario.usr_area[indexArea].ar_lote[indexLote].lot_nome}? Esta operação não pode ser desfeita!")

                    builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                        if(usuario.usr_area[indexArea].ar_lote[indexLote].lot_ent.size > 0){

                            Toast.makeText(this, "Gerar relatório!", Toast.LENGTH_SHORT).show()

                            usuario.loadUsuario(this)


                            Toast.makeText(this, "Relatório gerado!", Toast.LENGTH_SHORT).show()
                            dialog.dismiss()
                            //onCloseLoteDialog(areaId)


                            changeFragment(reportGenerateDetails.newInstance(usuario, indexArea,indexLote),
                                true, "GENERATE_REPORT_FRAGMENT")

                        }
                        else{
                            Toast.makeText(this, "Este lote não possui entradas para que um relatório seja gerado!", Toast.LENGTH_SHORT).show()
                            dialog.dismiss()
                        }

                    }
                    builder.setNegativeButton(android.R.string.no){dialog, which ->
                        dialog.dismiss()
                    }

                    builder.show()

                    incompleto = false;
                }
                else{
                    incompleto = true;
                }
            }
            else{
                incompleto = true;
            }

            if(incompleto){
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Erro!")
                builder.setMessage("Não é possível fechar o lote ${usuario.usr_area[indexArea].ar_lote[indexLote].lot_nome} pois as suas informações/endereços não foram preenchidos!")


                builder.setNeutralButton(android.R.string.ok){dialog, which ->
                    dialog.dismiss()
                }

                builder.show()
            }
        }
        else if(tipo == 4){//Novo entrada
            var datamin : Date? = null
            usuario.usr_area[indexArea].ar_lote[indexLote].lot_ent = C_Entrada().selectEntrada(DBHelper(this),loteId)
            if(usuario.usr_area[indexArea].ar_lote[indexLote].lot_ent.size == 0){
                datamin = null
            }
            else{
                datamin = usuario.usr_area[indexArea].ar_lote[indexLote].lot_ent[0].ent_data
            }
            changeFragment(EntradaCriaAlterDialog.newInstance(null,usuario.usr_id,areaId,loteId, datamin),
                true, "ENTRADA_CRIA_ALTER_DIALOG")

        }
        else if(tipo == 5){//Ver entradas

            if(indexLote != -1){
                usuario.reloadAreas(this)

                changeFragment(
                    EntradaFragment.newInstance(
                        usuario.usr_area[indexArea].ar_lote[indexLote],
                        usuario.usr_id, areaId,
                        ABERTO
                    ), true, "ENTRADA_FRAGMENT"
                )
            }
            else {
                usuario.loadFechados(this)

                indexLote = usuario.usr_area[indexArea].ar_lote_Fechado.indexOfFirst { it.lot_id ==  loteId}
                changeFragment(
                    EntradaFragment.newInstance(
                        usuario.usr_area[indexArea].ar_lote_Fechado[indexLote],
                        usuario.usr_id, areaId,
                        FECHADO

                    ), true, "ENTRADA_FRAGMENT"
                )
            }
        }
    }

    override fun onLoteSelected(lote : List<Lote>, pos : Int, tipo : Int, areaId : Int){
        var indexArea = usuario.usr_area.indexOfFirst { it.ar_id == areaId }

        if (tipo == 0) { //lote

            usuario.usr_area[indexArea].ar_lote[pos].lot_ent = C_Entrada().selectEntrada(DBHelper(this),lote[pos].lot_id)

            changeFragment(LoteDetalheFragment.newInstance(usuario.
                usr_area[indexArea].ar_lote[pos],
                null
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
                    if (C_Lote().deleteLote(DBHelper(this), usuario.usr_area[indexArea].ar_lote[pos].lot_id)){
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
        else if(tipo == 1) { //alterar
            changeFragment(AreaCriaAlterDialog.newInstance(area[pos], tipo, usuario.usr_id), true, "AREA_ALTER_FRAGMENT")
        }
        else if(tipo == 3){
            changeFragment(AreaCriaAlterDialog.newInstance(Area(0), tipo, usuario.usr_id), true, "AREA_ALTER_FRAGMENT")
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




        usuario = intent.extras.get("usuario") as Usuario
        usuario.loadUsuario(this)

        if (usuario.usr_area.size == 0){
            createArea()
            createLote(0)
            createEntrada(usuario.usr_area[0].ar_id,usuario.usr_area[0].ar_lote[0].lot_id)
        }


        if (!C_Entrada().tipoIsInserted(DBHelper(this))){
            C_Entrada().insertTipoEntrada(DBHelper(this))
        }

        supportFragmentManager.inTransaction {
            add(R.id.frmMainContainer, MainFragment.newInstance(usuario))
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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_main -> {
                if (!MainFragment().isVisible){
                    usuario.reloadAreas(this)
                    changeFragment(MainFragment.newInstance(usuario), true, "MAIN_FRAGMENT")
                }

            }
            R.id.nav_area -> {
                clearBackStack()
                changeFragment(AreaFragment.newInstance(usuario.usr_area), true, "AREA_FRAGMENT")
            }
            R.id.nav_fechado -> {
                clearBackStack()
                if(!usuario.checkLoteFechado()){
                    usuario.loadFechados(this)
                }
                changeFragment(FechadoFragment.newInstance(usuario.usr_area), true, "FECHADO_FRAGMENT")
            }
            R.id.nav_qrcode -> {
                clearBackStack()
                changeFragment(QRFragment(), true, "QRCODE_FRAGMENT")
            }
            R.id.nav_opt -> {
                clearBackStack()

                usuario.usr_user_info = C_User_Info().selectUser_Info(DBHelper(this))
                if(usuario.usr_user_info != null){
                    usuario.usr_user_info.info_endereco = C_Endereco().selectEndereco(DBHelper(this), usuario.usr_user_info.info_id)
                }
                changeFragment(UsuarioInfoGeral.newInstance(usuario), true, "USUARIOGERAL_FRAGMENT")
            }
            R.id.nav_sair -> {
                clearBackStack()
                moveTaskToBack(true)
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    fun clearBackStack(){
        val fragment = supportFragmentManager
        fragment.popBackStack(null,POP_BACK_STACK_INCLUSIVE)
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

    fun returnFragment(i : Int){
        var y : Int = 0
        while(y < i){
            supportFragmentManager.popBackStackImmediate()
            y++
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
        entrada.ent_tipo = 1
        entrada.ent_detalhe_num = 1
        entrada.ent_tempo = null
        entrada.ent_tpun = null
        entrada.ent_qtde = 50.0
        entrada.ent_qtun = Contrato.Entrada_Detalhe.QTDE_TIPO_PLANTIO
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
