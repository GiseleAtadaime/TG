package com.trabalho.tg.View.Detalhe

import android.app.AlertDialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.trabalho.tg.Controller.C_Entrada
import com.trabalho.tg.Helper.DBHelper
import com.trabalho.tg.Helper.Utils_TG
import com.trabalho.tg.Model.Entrada

import com.trabalho.tg.R
import kotlinx.android.synthetic.main.fragment_entrada_detalhe_dialog.*
import kotlinx.android.synthetic.main.fragment_entrada_new_dialog.*
import java.io.Serializable

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ENTRADA = "param1"
private const val USERID = "userid"
private const val AREAID = "areaid"
private const val LOTEID = "loteid"
/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [EntradaDetalheDialog.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [EntradaDetalheDialog.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class EntradaDetalheDialog : Fragment() {
    // TODO: Rename and change types of parameters
    private var entrada: Entrada? = null
    private var userid: Int? = null
    private var areaId: Int? = null
    private var loteId: Int? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            entrada = it.getSerializable(ENTRADA) as Entrada
            userid = it.getInt(USERID)
            areaId = it.getInt(AREAID)
            loteId = it.getInt(LOTEID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_entrada_detalhe_dialog, container, false)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onAlterDetalheClick(entrada : Entrada, userId : Int, areaId : Int, loteId : Int, fecha : Boolean) {
        listener?.onAlterDetalheClick(entrada, userId, areaId, loteId,fecha)
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
        fun onAlterDetalheClick(entrada : Entrada, userId : Int, areaId : Int, loteId : Int, fecha : Boolean)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment EntradaDetalheDialog.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(entrada: Entrada, user : Int, areaId : Int, loteId : Int) =
            EntradaDetalheDialog().apply {
                arguments = Bundle().apply {
                    putSerializable(ENTRADA, entrada as Serializable)
                    putInt(USERID, user)
                    putInt(AREAID, areaId)
                    putInt(LOTEID,loteId)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val utils = Utils_TG()

        imgColor_EntradaDetalhe.setImageResource(entrada!!.entradaColor)
        txtTipo_EntradaDetalhe.text = entrada!!.ent_desc
        txtData_EntradaDetalhe.text = Utils_TG().formatDate(entrada!!.ent_data, true)

        if(entrada!!.ent_tipo != 2 &&
            entrada!!.ent_tipo != 3 &&
            entrada!!.ent_tipo != 7){

            txtTipo1_EntradaDetalhe.visibility = View.GONE
            txtTipoValor1_EntradaDetalhe.visibility = View.GONE
        }
        else{

            txtTipo1_EntradaDetalhe.visibility = View.VISIBLE
            txtTipoValor1_EntradaDetalhe.visibility = View.VISIBLE

            if(entrada!!.ent_tipo == 3){
                txtTipo1_EntradaDetalhe.text = "Tempo de carência"

            }
            else{
                txtTipo1_EntradaDetalhe.text = "Tempo gasto"
            }
            txtTipoValor1_EntradaDetalhe.text = entrada!!.ent_tempo.toString() + " " + entrada!!.ent_tpun

        }
        if(entrada!!.ent_tipo == 7){

            txtTipo2_EntradaDetalhe.visibility = View.GONE
            txtTipoValor2_EntradaDetalhe.visibility = View.GONE
            txtValor_EntradaDetalhe.visibility = View.GONE
        }
        else{

            txtTipo2_EntradaDetalhe.visibility = View.VISIBLE
            txtTipoValor2_EntradaDetalhe.visibility = View.VISIBLE
            txtValor_EntradaDetalhe.visibility = View.VISIBLE

            txtTipoValor2_EntradaDetalhe.text = entrada!!.ent_qtde.toString() + " " +  entrada!!.ent_qtun

            if(entrada!!.ent_tipo == 1){
                txtTipo2_EntradaDetalhe.text = "Bandejas"
                txtTipoValor2_EntradaDetalhe.text = entrada!!.ent_qtde.toString()
            }
            else if(entrada!!.ent_tipo == 5){
                txtTipo2_EntradaDetalhe.text = "Pessoas"
            }
            else if(entrada!!.ent_tipo == 2 || entrada!!.ent_tipo == 6 || entrada!!.ent_tipo == 4){
                txtTipo2_EntradaDetalhe.text = "Quantidade"
            }
            else if(entrada!!.ent_tipo == 3){
                txtTipo2_EntradaDetalhe.text = "Dose"
            }


            txtValor_EntradaDetalhe.text = "Valor total: " + utils.formatMonetario(utils.doubleToString(entrada!!.ent_valor))
        }
        if(entrada!!.ent_tipo != 1){

            txtMudas_EntradaDetalhe.visibility = View.GONE
        }
        else{
            txtMudas_EntradaDetalhe.visibility = View.VISIBLE

            txtMudas_EntradaDetalhe.text = "Mudas por bandeja: " + entrada!!.ent_mudas_bandeja.toString()
        }

        if(entrada!!.ent_tipo == 3){
            txtAgrot_EntradaNewDialog.visibility = View.VISIBLE
            txtNomeCom_EntradaDetalhe.visibility = View.VISIBLE
            txtEmpresa_EntradaDetalhe.visibility = View.VISIBLE
            txtIng_EntradaDetalhe.visibility = View.VISIBLE
            txtLoteAgr_EntradaDetalheDialog.visibility = View.VISIBLE

            txtAgrot_EntradaNewDialog.text = "Agrotóxico utilizado:"
            txtNomeCom_EntradaDetalhe.text = "Nome comercial: " + entrada!!.ent_reg.reg_nomecom
            txtEmpresa_EntradaDetalhe.text = "Fabricante: " + entrada!!.ent_reg.reg_empresa
            txtIng_EntradaDetalhe.text = "Ingrediente ativo: " + entrada!!.ent_reg.reg_ing_ativo
            txtLoteAgr_EntradaDetalheDialog.text = "Lote: " + entrada!!.ent_reg_lote

        }
        else{
            txtAgrot_EntradaNewDialog.visibility = View.GONE
            txtNomeCom_EntradaDetalhe.visibility = View.GONE
            txtEmpresa_EntradaDetalhe.visibility = View.GONE
            txtIng_EntradaDetalhe.visibility = View.GONE
            txtLoteAgr_EntradaDetalheDialog.visibility = View.GONE
        }




        imgBtnAlter_EntradaDetalhe.setOnClickListener(){
            onAlterDetalheClick(entrada!!,userid!!,areaId!!, loteId!!, false)
        }


        imgBtnDelete_EntradaDetalhe.setOnClickListener(){
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Deletar registro")
            builder.setMessage("Tem certeza que deseja o registro de  ${entrada!!.ent_desc}" +
                    " do dia ${Utils_TG().formatDate(entrada!!.ent_data,true)}? Esta operação não pode ser desfeita!")

            builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                if(!entrada!!.ent_desc.toString().contentEquals("plantio")){
                    if (C_Entrada().deleteEntrada(DBHelper(context), entrada!!)){
                        dialog.dismiss()
                        onAlterDetalheClick(entrada!!,userid!!,areaId!!, loteId!!, true)
                    }
                    else {
                        Toast.makeText(context, "Não foi possível apagar o registro selecionado!", Toast.LENGTH_SHORT).show()
                        dialog.dismiss()
                    }
                }
                else{
                    Toast.makeText(context, "Não é possível apagar a primeira entrada referente a plantio!", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }

            }
            builder.setNegativeButton(android.R.string.no){dialog, which ->
                dialog.dismiss()
            }

            builder.show()

        }

    }
}
