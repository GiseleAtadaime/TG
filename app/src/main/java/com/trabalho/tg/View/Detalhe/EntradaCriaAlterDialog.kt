package com.trabalho.tg.View.Detalhe

import android.app.DatePickerDialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.trabalho.tg.Controller.C_Entrada
import com.trabalho.tg.Controller.C_Reg_Agrotoxico
import com.trabalho.tg.Helper.DBHelper
import com.trabalho.tg.Helper.Utils_TG
import com.trabalho.tg.Model.Entrada
import com.trabalho.tg.Model.Reg_Agrotoxico
import com.trabalho.tg.R
import kotlinx.android.synthetic.main.fragment_entrada_detalhe_dialog.*
import kotlinx.android.synthetic.main.fragment_entrada_new_dialog.*
import java.io.Serializable
import java.util.*
import android.widget.AdapterView
import android.widget.AutoCompleteTextView
import com.trabalho.tg.Adapters.AgrotoxicoAdapter




// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ENTRADA = "param1"
private const val USERID = "userid"
private const val AREAID = "areaid"
private const val LOTEID = "loteid"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [EntradaCriaAlterDialog.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [EntradaCriaAlterDialog.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class EntradaCriaAlterDialog : Fragment() {
    // TODO: Rename and change types of parameters
    private var entrada: Entrada? = null
    private var userid: Int? = null
    private var areaId: Int? = null
    private var loteId: Int? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            entrada = it.getSerializable(ENTRADA) as Entrada?
            userid = it.getInt(USERID)
            areaId = it.getInt(AREAID)
            loteId = it.getInt(LOTEID)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_entrada_new_dialog, container, false)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onCriaAlterDialog(userId : Int, areaId : Int, loteId : Int) {
        listener?.onCriaAlterDialog(userId,areaId,loteId)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onCriaAlterDialog(userId : Int, areaId : Int, loteId : Int)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment EntradaCriaAlterDialog.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(entrada: Entrada?, user : Int, areaId : Int, loteId : Int) =
            EntradaCriaAlterDialog().apply {
                arguments = Bundle().apply {
                    putSerializable(ENTRADA, entrada as Serializable?)
                    putInt(USERID, user)
                    putInt(AREAID, areaId)
                    putInt(LOTEID,loteId)
                }
            }


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val utils = Utils_TG()
        super.onViewCreated(view, savedInstanceState)
        var cal = Calendar.getInstance()
        var reg_agro_selected : Reg_Agrotoxico? = null
        var reg_agro_adapter : AgrotoxicoAdapter? = null

        var adapter = ArrayAdapter.createFromResource(context,R.array.EntradaTipo, android.R.layout.simple_spinner_dropdown_item)
        var adapterTempo = ArrayAdapter.createFromResource(context,R.array.EntradaTipoTempo, android.R.layout.simple_spinner_dropdown_item)
        var adapterQtde = ArrayAdapter.createFromResource(context,R.array.EntradaTipoQuantidade, android.R.layout.simple_spinner_dropdown_item)
        var adapterDose = ArrayAdapter.createFromResource(context,R.array.EntradaTipoDose, android.R.layout.simple_spinner_dropdown_item)

        val tipo = entrada == null //if true = Cria

        spiTipo_EntradaNewDialog.adapter = adapter

        txtTipo_EntradaNewDialog.text = "Tipo"
        txtData_EntradaNewDialog.text = "Data do registro"



        if (tipo){
            entrada = Entrada(0)
            spiTipo_EntradaNewDialog.setSelection(0)
            cal.time = Date()

            txtDataEscolhida_EntradaNewDialog.text = utils.formatDate(cal.time, true)
        }
        else{

            spiTipo_EntradaNewDialog.setSelection(entrada!!.ent_tipo)
            imgTipoCor_EntradaNewDialog.setImageResource(entrada!!.entradaColor)
            cal.time = entrada!!.ent_data

            txtDataEscolhida_EntradaNewDialog.text = utils.formatDate(entrada!!.ent_data, true)
        }

        spiTipo_EntradaNewDialog.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position == 0){
                    imgTipoCor_EntradaNewDialog.setImageResource(R.color.white)
                }else{
                    entrada?.ent_tipo = position
                    imgTipoCor_EntradaNewDialog.setImageResource(entrada!!.entradaColor)
                }

                if(position != 2 &&
                    position != 3 &&
                    position != 7 ||
                    position == 0){

                    txtTempo_EntradaNewDialog.visibility = View.GONE
                    linTempo_EntradaNewDialog.visibility = View.GONE
                }
                else{
                    spiTempo_EntradaNewDialog.adapter = adapterTempo
                    txtTempo_EntradaNewDialog.visibility = View.VISIBLE
                    linTempo_EntradaNewDialog.visibility = View.VISIBLE

                    if(entrada!!.ent_tipo == 3){
                        txtTempo_EntradaNewDialog.text = "Tempo de carência"
                    }
                    else{
                        txtTempo_EntradaNewDialog.text = "Tempo gasto"
                    }
                    if(!tipo){
                        edtTempo_EntradaNewDialog.setText(entrada!!.ent_tempo.toString())
                    }

                }

                if(position == 7 ||
                    position == 0){

                    txtQtde_EntradaNewDialog.visibility = View.GONE
                    linQtde_EntradaNewDialog.visibility = View.GONE
                    txtValor_EntradaNewDialog.visibility = View.GONE
                    edtValor_EntradaNewDialog.visibility = View.GONE
                }
                else{
                    spiQtde_EntradaNewDialog.adapter = adapterQtde
                    txtQtde_EntradaNewDialog.visibility = View.VISIBLE
                    linQtde_EntradaNewDialog.visibility = View.VISIBLE
                    txtValor_EntradaNewDialog.visibility = View.VISIBLE
                    edtValor_EntradaNewDialog.visibility = View.VISIBLE

                    if(position == 1){
                        if(C_Entrada().plantioExists(DBHelper(context),loteId)){
                            Toast.makeText(context, "Já existe um registro de plantio!", Toast.LENGTH_SHORT).show()
                            linDados_EntradaNewDialog.isEnabled = false
                            btnCriar_EntradaNewDialog.isEnabled = false

                        }
                        else{
                            linDados_EntradaNewDialog.isEnabled = true
                            btnCriar_EntradaNewDialog.isEnabled = true
                            txtQtde_EntradaNewDialog.text = "Bandejas"
                            spiQtde_EntradaNewDialog.visibility = View.GONE
                        }

                    }else{
                        linDados_EntradaNewDialog.isEnabled = true
                        btnCriar_EntradaNewDialog.isEnabled = true
                    }
                    if(position == 5){
                        txtQtde_EntradaNewDialog.text = "Pessoas"
                        spiQtde_EntradaNewDialog.visibility = View.GONE
                    }
                    else if(position == 2 || position == 6 || position == 4){
                        txtQtde_EntradaNewDialog.text = "Quantidade"
                    }
                    else if(position == 3){
                        txtQtde_EntradaNewDialog.text = "Dose"
                        spiQtde_EntradaNewDialog.adapter = adapterDose
                    }

                    txtValor_EntradaNewDialog.text = "Valor total"

                    if(!tipo){
                        edtQtde_EntradaNewDialog.setText(entrada!!.ent_qtde.toString())
                        edtValor_EntradaNewDialog.setText(utils.formatMonetario(utils.doubleToString(entrada!!.ent_valor)))
                    }

                    edtValor_EntradaNewDialog.addTextChangedListener(utils.MascaraMonetaria(edtValor_EntradaNewDialog))
                }
                if(position != 1 ||
                    position == 0){

                    txtMudasB_EntradaNewDialog.visibility = View.GONE
                    edtMudasB_EntradaNewDialog.visibility = View.GONE

                }
                else{
                    txtMudasB_EntradaNewDialog.visibility = View.VISIBLE
                    edtMudasB_EntradaNewDialog.visibility = View.VISIBLE

                    txtMudasB_EntradaNewDialog.text = "Mudas por bandeja"
                    if(!tipo){
                        edtMudasB_EntradaNewDialog.setText(entrada!!.ent_mudas_bandeja.toString())
                    }
                }

                if(position == 3) {

                    if(!tipo){
                        auTxtNome_EntradaNewDialog.setText(entrada!!.ent_reg.reg_nomecom)
                        edtEmpresa_EntradaNewDialog.setText(entrada!!.ent_reg.reg_empresa)
                        edtIng_EntradaNewDialog.setText(entrada!!.ent_reg.reg_ing_ativo)
                        edtLoteDef_EntradaNewDialog.setText(entrada!!.ent_reg_lote)
                    }

                    linAgr_EntradaNewDialog.visibility = View.VISIBLE

                    var mList: List<Reg_Agrotoxico> = C_Reg_Agrotoxico().selectReg_Agrotoxico(DBHelper(context))

                    reg_agro_adapter = AgrotoxicoAdapter(
                        context,
                        R.layout.fragment_entrada_new_dialog,
                        R.id.txtAgrNome_RowLayout,
                        mList
                    )
                    auTxtNome_EntradaNewDialog.setAdapter(reg_agro_adapter)
                    auTxtNome_EntradaNewDialog.onItemClickListener =
                        AdapterView.OnItemClickListener { adapterView, _, pos, _ ->
                            //this is the way to find selected object/item
                            reg_agro_selected = adapterView.getItemAtPosition(pos) as Reg_Agrotoxico

                            if(tipo){
                                entrada!!.ent_reg = Reg_Agrotoxico(0)
                            }
                            entrada!!.ent_reg.reg_numero = reg_agro_selected!!.reg_numero
                            auTxtNome_EntradaNewDialog.setText(reg_agro_selected!!.reg_nomecom)
                            edtEmpresa_EntradaNewDialog.setText(reg_agro_selected!!.reg_empresa)
                            edtIng_EntradaNewDialog.setText(reg_agro_selected!!.reg_ing_ativo)
                        }
                }
                else{
                    linAgr_EntradaNewDialog.visibility = View.GONE
                }
            }
        }

        btnCriar_EntradaNewDialog.setOnClickListener(){

            if(spiTipo_EntradaNewDialog.selectedItemPosition > 0){

                if(spiTipo_EntradaNewDialog.selectedItemPosition > 1){
                    entrada!!.ent_tipo = spiTipo_EntradaNewDialog.selectedItemPosition

                }
                if(spiTipo_EntradaNewDialog.selectedItemPosition == 3){


                    entrada!!.ent_reg.reg_nomecom = auTxtNome_EntradaNewDialog.text.toString()
                    entrada!!.ent_reg.reg_empresa = edtEmpresa_EntradaNewDialog.text.toString()
                    entrada!!.ent_reg.reg_ing_ativo = edtIng_EntradaNewDialog.text.toString()
                    entrada!!.ent_reg_lote = edtLoteDef_EntradaNewDialog.text.toString()

                    if(entrada!!.ent_reg.reg_nomecom.compareTo(auTxtNome_EntradaNewDialog.text.toString()) != 0) {

                        entrada!!.ent_reg = Reg_Agrotoxico(0)
                        if (C_Reg_Agrotoxico().insertReg_Agrotoxico(DBHelper(context), entrada!!.ent_reg)) {
                            entrada!!.ent_reg.reg_numero =
                                C_Reg_Agrotoxico().selectReg_Agrotoxico_LastID(DBHelper(context))
                        } else {
                            Toast.makeText(context, "Erro ao salvar registro do agrotóxico!", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                if(linTempo_EntradaNewDialog.visibility == View.VISIBLE){
                    entrada!!.ent_tpun = spiTempo_EntradaNewDialog.selectedItem.toString()
                }
                else{
                    entrada!!.ent_tpun = null
                }
                if(linQtde_EntradaNewDialog.visibility == View.VISIBLE){
                    entrada!!.ent_qtun = spiQtde_EntradaNewDialog.selectedItem.toString()
                }
                else{
                    entrada!!.ent_qtun = null
                }

                entrada!!.ent_tempo = utils.stringToDouble(edtTempo_EntradaNewDialog.text.toString())
                entrada!!.ent_qtde = utils.stringToDouble(edtQtde_EntradaNewDialog.text.toString())
                entrada!!.ent_mudas_bandeja =  utils.stringToInteger(edtMudasB_EntradaNewDialog.text.toString())
                entrada!!.ent_valor = utils.stringToDouble(utils.removerMascaraMonetaria(edtValor_EntradaNewDialog.text.toString()))


                if(tipo){
                    if(C_Entrada().insertEntrada(DBHelper(context),entrada!!, loteId,userid)){
                        onCriaAlterDialog(userid!!,areaId!!,loteId!!)
                    }
                    else{
                        Toast.makeText(context, "Erro ao salvar entrada!", Toast.LENGTH_SHORT).show()
                    }
                }
                else{
                    if(C_Entrada().updateEntrada(DBHelper(context),entrada!!)){
                        onCriaAlterDialog(userid!!,areaId!!,loteId!!)
                    }
                    else{
                        Toast.makeText(context, "Erro ao atualizar entrada!", Toast.LENGTH_SHORT).show()
                    }
                }


            }
            else{
                Toast.makeText(context, "Selecione um tipo de entrada!", Toast.LENGTH_SHORT).show()
            }
        }

        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                                   dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                entrada!!.setEnt_data(dayOfMonth, monthOfYear, year)
                txtDataEscolhida_EntradaNewDialog.setText(Utils_TG().formatDate(entrada!!.ent_data, true))
            }
        }

        txtDataEscolhida_EntradaNewDialog.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                Utils_TG().hideKeyboardFrom(context, view)
                DatePickerDialog(context,
                    dateSetListener,
                    // set DatePickerDialog to point to today's date when it loads up
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
            }

        })





    }
}
