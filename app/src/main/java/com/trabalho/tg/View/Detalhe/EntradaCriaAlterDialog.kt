package com.trabalho.tg.View.Detalhe

import android.app.DatePickerDialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
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
private const val DATAPLANT = "dataplantio"

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
    private var dataplant: Date? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            entrada = it.getSerializable(ENTRADA) as Entrada?
            userid = it.getInt(USERID)
            areaId = it.getInt(AREAID)
            loteId = it.getInt(LOTEID)
            dataplant = it.getSerializable(DATAPLANT) as Date?
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
        fun newInstance(entrada: Entrada?, user : Int, areaId : Int, loteId : Int, dataplant : Date?) =
            EntradaCriaAlterDialog().apply {
                arguments = Bundle().apply {
                    putSerializable(ENTRADA, entrada as Serializable?)
                    putInt(USERID, user)
                    putInt(AREAID, areaId)
                    putInt(LOTEID,loteId)
                    putSerializable(DATAPLANT, dataplant as Serializable?)

                }
            }


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        val utils = Utils_TG()
        super.onViewCreated(view, savedInstanceState)
        var cal = Calendar.getInstance()
        var reg_agro_selected : Reg_Agrotoxico? = null
        var reg_agro_adapter : AgrotoxicoAdapter? = null
        var data_ant : Date? = null


        var entTipo = C_Entrada().selectTipoDescricao(DBHelper(context))
        var entTipos = arrayOf<String>("Escolha um tipo",
            entTipo[0],
            entTipo[1],
            entTipo[2],
            entTipo[3],
            entTipo[4],
            entTipo[5],
            entTipo[6])


        var adapter = ArrayAdapter<String>(context,android.R.layout.simple_spinner_dropdown_item,entTipos )
        var adapterTempo = ArrayAdapter.createFromResource(context,R.array.EntradaTipoTempo, android.R.layout.simple_spinner_dropdown_item)
        var adapterQtde = ArrayAdapter.createFromResource(context,R.array.EntradaTipoQuantidade, android.R.layout.simple_spinner_dropdown_item)
        var adapterDose = ArrayAdapter.createFromResource(context,R.array.EntradaTipoDose, android.R.layout.simple_spinner_dropdown_item)
        var adapterTempoCarencia = ArrayAdapter.createFromResource(context,R.array.EntradaTempoCarencia, android.R.layout.simple_spinner_dropdown_item)


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
            data_ant = entrada!!.ent_data
            txtDataEscolhida_EntradaNewDialog.text = utils.formatDate(entrada!!.ent_data, true)
        }

        spiTipo_EntradaNewDialog.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position == 0) {
                    imgTipoCor_EntradaNewDialog.setImageResource(R.color.white)
                } else {
                    entrada?.ent_tipo = position
                    imgTipoCor_EntradaNewDialog.setImageResource(entrada!!.entradaColor)
                }


                txtTempo_EntradaNewDialog.visibility = View.GONE
                linTempo_EntradaNewDialog.visibility = View.GONE

                txtQtde_EntradaNewDialog.visibility = View.GONE
                linQtde_EntradaNewDialog.visibility = View.GONE
                txtValor_EntradaNewDialog.visibility = View.GONE
                edtValor_EntradaNewDialog.visibility = View.GONE
                linDados_EntradaNewDialog.isEnabled = true
                btnCriar_EntradaNewDialog.isEnabled = true

                spiQtde_EntradaNewDialog.visibility = View.GONE
                spiTempo_EntradaNewDialog.visibility = View.GONE

                txtMudasB_EntradaNewDialog.visibility = View.GONE
                edtMudasB_EntradaNewDialog.visibility = View.GONE
                linAgr_EntradaNewDialog.visibility = View.GONE

                when (position) {

                    1 -> {

                        txtQtde_EntradaNewDialog.visibility = View.VISIBLE
                        linQtde_EntradaNewDialog.visibility = View.VISIBLE
                        txtValor_EntradaNewDialog.visibility = View.VISIBLE
                        edtValor_EntradaNewDialog.visibility = View.VISIBLE

                        linDados_EntradaNewDialog.isEnabled = true
                        btnCriar_EntradaNewDialog.isEnabled = true

                        txtQtde_EntradaNewDialog.text = "Bandejas"

                        txtValor_EntradaNewDialog.text = "Valor total"

                        edtValor_EntradaNewDialog.addTextChangedListener(
                            utils.MascaraMonetaria(
                                edtValor_EntradaNewDialog
                            )
                        )

                        txtMudasB_EntradaNewDialog.visibility = View.VISIBLE
                        edtMudasB_EntradaNewDialog.visibility = View.VISIBLE

                        txtMudasB_EntradaNewDialog.text = "Mudas por bandeja"

                        if (!tipo) {
                            edtQtde_EntradaNewDialog.setText(entrada!!.ent_qtde.toString())
                            edtValor_EntradaNewDialog.setText(utils.formatMonetario(utils.doubleToString(entrada!!.ent_valor)))
                            edtMudasB_EntradaNewDialog.setText(entrada!!.ent_mudas_bandeja.toString())
                        }

                    }
                    2 -> {
                        spiTempo_EntradaNewDialog.adapter = adapterTempo
                        txtTempo_EntradaNewDialog.visibility = View.VISIBLE
                        linTempo_EntradaNewDialog.visibility = View.VISIBLE

                        txtTempo_EntradaNewDialog.text = "Tempo gasto"

                        spiQtde_EntradaNewDialog.adapter = adapterQtde
                        spiQtde_EntradaNewDialog.visibility = View.VISIBLE
                        txtQtde_EntradaNewDialog.visibility = View.VISIBLE
                        linQtde_EntradaNewDialog.visibility = View.VISIBLE
                        txtValor_EntradaNewDialog.visibility = View.VISIBLE
                        edtValor_EntradaNewDialog.visibility = View.VISIBLE

                        linDados_EntradaNewDialog.isEnabled = true
                        btnCriar_EntradaNewDialog.isEnabled = true
                        txtQtde_EntradaNewDialog.text = "Quantidade"


                        txtValor_EntradaNewDialog.text = "Valor total"

                        if (!tipo) {
                            edtQtde_EntradaNewDialog.setText(entrada!!.ent_qtde.toString())
                            edtValor_EntradaNewDialog.setText(utils.formatMonetario(utils.doubleToString(entrada!!.ent_valor)))
                            spiQtde_EntradaNewDialog.setSelection(
                                (spiQtde_EntradaNewDialog.adapter as ArrayAdapter<CharSequence>).getPosition(
                                    entrada!!.ent_qtun
                                )
                            )
                            edtTempo_EntradaNewDialog.setText(entrada!!.ent_tempo.toString())
                            spiTempo_EntradaNewDialog.setSelection(adapterTempo.getPosition(entrada!!.ent_tpun))
                        }

                        edtValor_EntradaNewDialog.addTextChangedListener(
                            utils.MascaraMonetaria(
                                edtValor_EntradaNewDialog
                            )
                        )
                    }

                    3 -> {

                        spiTempo_EntradaNewDialog.adapter = adapterTempoCarencia
                        txtTempo_EntradaNewDialog.visibility = View.VISIBLE
                        linTempo_EntradaNewDialog.visibility = View.VISIBLE
                        txtTempo_EntradaNewDialog.text = "Tempo de carência"

                        spiQtde_EntradaNewDialog.adapter = adapterQtde
                        txtQtde_EntradaNewDialog.visibility = View.VISIBLE
                        linQtde_EntradaNewDialog.visibility = View.VISIBLE
                        txtValor_EntradaNewDialog.visibility = View.VISIBLE
                        edtValor_EntradaNewDialog.visibility = View.VISIBLE

                        linDados_EntradaNewDialog.isEnabled = true
                        btnCriar_EntradaNewDialog.isEnabled = true


                        txtQtde_EntradaNewDialog.text = "Dose"
                        spiQtde_EntradaNewDialog.adapter = adapterDose
                        spiQtde_EntradaNewDialog.visibility = View.VISIBLE

                        txtValor_EntradaNewDialog.text = "Valor total"

                        if (!tipo) {
                            edtQtde_EntradaNewDialog.setText(entrada!!.ent_qtde.toString())
                            edtValor_EntradaNewDialog.setText(utils.formatMonetario(utils.doubleToString(entrada!!.ent_valor)))
                            spiQtde_EntradaNewDialog.setSelection(
                                (spiQtde_EntradaNewDialog.adapter as ArrayAdapter<CharSequence>).getPosition(
                                    entrada!!.ent_qtun
                                )
                            )
                            edtTempo_EntradaNewDialog.setText(entrada!!.ent_tempo.toString())
                            spiTempo_EntradaNewDialog.setSelection(adapterTempo.getPosition(entrada!!.ent_tpun))

                            auTxtNome_EntradaNewDialog.setText(entrada!!.ent_reg.reg_nomecom)
                            edtEmpresa_EntradaNewDialog.setText(entrada!!.ent_reg.reg_empresa)
                            edtIng_EntradaNewDialog.setText(entrada!!.ent_reg.reg_ing_ativo)
                            edtLoteDef_EntradaNewDialog.setText(entrada!!.ent_reg_lote)
                        }

                        edtValor_EntradaNewDialog.addTextChangedListener(
                            utils.MascaraMonetaria(
                                edtValor_EntradaNewDialog
                            )
                        )


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

                                if (tipo) {
                                    entrada!!.ent_reg = Reg_Agrotoxico(0)
                                }
                                entrada!!.ent_reg.reg_numero = reg_agro_selected!!.reg_numero
                                auTxtNome_EntradaNewDialog.setText(reg_agro_selected!!.reg_nomecom)
                                edtEmpresa_EntradaNewDialog.setText(reg_agro_selected!!.reg_empresa)
                                edtIng_EntradaNewDialog.setText(reg_agro_selected!!.reg_ing_ativo)
                            }

                    }
                    4 -> {

                        spiQtde_EntradaNewDialog.adapter = adapterQtde
                        spiQtde_EntradaNewDialog.visibility = View.VISIBLE
                        txtQtde_EntradaNewDialog.visibility = View.VISIBLE
                        linQtde_EntradaNewDialog.visibility = View.VISIBLE
                        txtValor_EntradaNewDialog.visibility = View.VISIBLE
                        edtValor_EntradaNewDialog.visibility = View.VISIBLE

                        linDados_EntradaNewDialog.isEnabled = true
                        btnCriar_EntradaNewDialog.isEnabled = true


                        txtQtde_EntradaNewDialog.text = "Quantidade"


                        txtValor_EntradaNewDialog.text = "Valor total"

                        if (!tipo) {
                            edtQtde_EntradaNewDialog.setText(entrada!!.ent_qtde.toString())
                            edtValor_EntradaNewDialog.setText(utils.formatMonetario(utils.doubleToString(entrada!!.ent_valor)))
                            spiQtde_EntradaNewDialog.setSelection(
                                (spiQtde_EntradaNewDialog.adapter as ArrayAdapter<CharSequence>).getPosition(
                                    entrada!!.ent_qtun
                                )
                            )
                            edtTempo_EntradaNewDialog.setText(entrada!!.ent_tempo.toString())
                            spiTempo_EntradaNewDialog.setSelection(adapterTempo.getPosition(entrada!!.ent_tpun))
                        }

                        edtValor_EntradaNewDialog.addTextChangedListener(
                            utils.MascaraMonetaria(
                                edtValor_EntradaNewDialog
                            )
                        )


                    }
                    5 -> {


                        spiQtde_EntradaNewDialog.adapter = adapterQtde
                        txtQtde_EntradaNewDialog.visibility = View.VISIBLE
                        linQtde_EntradaNewDialog.visibility = View.VISIBLE
                        txtValor_EntradaNewDialog.visibility = View.VISIBLE
                        edtValor_EntradaNewDialog.visibility = View.VISIBLE

                        linDados_EntradaNewDialog.isEnabled = true
                        btnCriar_EntradaNewDialog.isEnabled = true



                        txtQtde_EntradaNewDialog.text = "Pessoas"


                        txtValor_EntradaNewDialog.text = "Valor total"

                        if (!tipo) {
                            edtQtde_EntradaNewDialog.setText(entrada!!.ent_qtde.toString())
                            edtValor_EntradaNewDialog.setText(utils.formatMonetario(utils.doubleToString(entrada!!.ent_valor)))
                            spiQtde_EntradaNewDialog.setSelection(
                                (spiQtde_EntradaNewDialog.adapter as ArrayAdapter<CharSequence>).getPosition(
                                    entrada!!.ent_qtun
                                )
                            )
                            edtTempo_EntradaNewDialog.setText(entrada!!.ent_tempo.toString())
                            spiTempo_EntradaNewDialog.setSelection(adapterTempo.getPosition(entrada!!.ent_tpun))
                        }

                        edtValor_EntradaNewDialog.addTextChangedListener(
                            utils.MascaraMonetaria(
                                edtValor_EntradaNewDialog
                            )
                        )

                    }
                    6 -> {


                        spiQtde_EntradaNewDialog.adapter = adapterQtde
                        spiQtde_EntradaNewDialog.visibility = View.VISIBLE
                        txtQtde_EntradaNewDialog.visibility = View.VISIBLE
                        linQtde_EntradaNewDialog.visibility = View.VISIBLE
                        txtValor_EntradaNewDialog.visibility = View.VISIBLE
                        edtValor_EntradaNewDialog.visibility = View.VISIBLE

                        linDados_EntradaNewDialog.isEnabled = true
                        btnCriar_EntradaNewDialog.isEnabled = true


                        txtQtde_EntradaNewDialog.text = "Quantidade"


                        txtValor_EntradaNewDialog.text = "Valor total"

                        if (!tipo) {
                            edtQtde_EntradaNewDialog.setText(entrada!!.ent_qtde.toString())
                            edtValor_EntradaNewDialog.setText(utils.formatMonetario(utils.doubleToString(entrada!!.ent_valor)))
                            spiQtde_EntradaNewDialog.setSelection(
                                (spiQtde_EntradaNewDialog.adapter as ArrayAdapter<CharSequence>).getPosition(
                                    entrada!!.ent_qtun
                                )
                            )
                            edtTempo_EntradaNewDialog.setText(entrada!!.ent_tempo.toString())
                            spiTempo_EntradaNewDialog.setSelection(adapterTempo.getPosition(entrada!!.ent_tpun))
                        }

                        edtValor_EntradaNewDialog.addTextChangedListener(
                            utils.MascaraMonetaria(
                                edtValor_EntradaNewDialog
                            )
                        )

                    }
                    7 -> {
                        spiTempo_EntradaNewDialog.adapter = adapterTempo
                        txtTempo_EntradaNewDialog.visibility = View.VISIBLE
                        linTempo_EntradaNewDialog.visibility = View.VISIBLE


                        txtTempo_EntradaNewDialog.text = "Tempo gasto"

                        if (!tipo) {
                            edtTempo_EntradaNewDialog.setText(entrada!!.ent_tempo.toString())
                            spiTempo_EntradaNewDialog.setSelection(adapterTempo.getPosition(entrada!!.ent_tpun))
                        }

                    }
                    else -> {
                        //TODO alguma coisa
                        linAgr_EntradaNewDialog.visibility = View.GONE
                    }
                }
            }
        }



        btnCriar_EntradaNewDialog.setOnClickListener(){

            if(spiTipo_EntradaNewDialog.selectedItemPosition > 0){

                if(spiTipo_EntradaNewDialog.selectedItemPosition > 1){
                    entrada!!.ent_tipo = spiTipo_EntradaNewDialog.selectedItemPosition

                }
                if(spiTipo_EntradaNewDialog.selectedItemPosition == 3){
                    if(tipo){
                        entrada!!.ent_reg = Reg_Agrotoxico(0)
                    }

                    entrada!!.ent_reg.reg_nomecom = auTxtNome_EntradaNewDialog.text.toString()
                    entrada!!.ent_reg.reg_empresa = edtEmpresa_EntradaNewDialog.text.toString()
                    entrada!!.ent_reg.reg_ing_ativo = edtIng_EntradaNewDialog.text.toString()
                    entrada!!.ent_reg_lote = edtLoteDef_EntradaNewDialog.text.toString()

                    if(entrada!!.ent_reg != reg_agro_selected){
                        if (C_Reg_Agrotoxico().insertReg_Agrotoxico(DBHelper(context), entrada!!.ent_reg)) {
                            entrada!!.ent_reg.reg_numero =
                                C_Reg_Agrotoxico().selectReg_Agrotoxico_LastID(DBHelper(context))
                        } else {
                            Toast.makeText(context, "Erro ao salvar registro do agrotóxico!", Toast.LENGTH_SHORT).show()
                        }
                    }

                }

                if(spiTempo_EntradaNewDialog.visibility == View.VISIBLE){
                    entrada!!.ent_tpun = spiTempo_EntradaNewDialog.selectedItem.toString()
                }
                else{
                    entrada!!.ent_tpun = null
                }
                if(spiQtde_EntradaNewDialog.visibility == View.VISIBLE){
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
                entrada!!.setEnt_data(dayOfMonth, (monthOfYear) + 1, year)

                if(data_ant != null){
                    if(Utils_TG().formatDate(data_ant, true).compareTo(Utils_TG().formatDate(entrada!!.ent_data,true)) == 0){
                        entrada!!.ent_data = data_ant
                    }
                }
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
