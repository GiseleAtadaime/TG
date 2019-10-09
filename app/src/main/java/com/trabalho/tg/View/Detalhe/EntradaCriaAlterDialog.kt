package com.trabalho.tg.View.Detalhe

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.trabalho.tg.Helper.Utils_TG
import com.trabalho.tg.Model.Entrada
import com.trabalho.tg.R
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
        return inflater.inflate(R.layout.fragment_entrada_new_dialog, container, false)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
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
        fun onFragmentInteraction(uri: Uri)
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
        fun newInstance(entrada: Entrada, user : Int, areaId : Int, loteId : Int) =
            EntradaCriaAlterDialog().apply {
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

        txtTipo_EntradaNewDialog.text = "Tipo"
        var adapter = ArrayAdapter.createFromResource(context,R.array.EntradaTipo, android.R.layout.simple_spinner_dropdown_item)
        spiTipo_EntradaNewDialog.adapter = adapter

        spiTipo_EntradaNewDialog.setSelection(0)

        if(entrada!!.ent_desc.contentEquals("plantio")){
            spiTipo_EntradaNewDialog.isEnabled = false
        }

        txtData_EntradaNewDialog.text = "Data do registro"
        edtData_EntradaNewDialog.setText(Utils_TG().formatDate(entrada!!.ent_data, true))

        if(entrada!!.ent_tempo != null){
            txtTempo_EntradaNewDialog.text = "Tempo gasto"
            edtTempo_EntradaNewDialog.setText(entrada!!.ent_tempo.toString())
        }
        else{
            txtTempo_EntradaNewDialog.visibility = View.GONE
            edtTempo_EntradaNewDialog.visibility = View.GONE
        }

        if(entrada!!.ent_qtde != null){
            txtQtde_EntradaNewDialog.text = "Quantidade"
            edtQtde_EntradaNewDialog.setText(entrada!!.ent_qtde.toString())
        }
        else{
            txtQtde_EntradaNewDialog.visibility = View.GONE
            edtQtde_EntradaNewDialog.visibility = View.GONE
        }
        if(entrada!!.ent_mudas_bandeja != null){
            txtMudasB_EntradaNewDialog.text = "Mudas por bandeja"
            edtMudasB_EntradaNewDialog.setText(entrada!!.ent_mudas_bandeja.toString())
        }
        else{
            txtMudasB_EntradaNewDialog.visibility = View.GONE
            edtMudasB_EntradaNewDialog.visibility = View.GONE
        }
        if(entrada!!.ent_valor != null){
            txtValor_EntradaNewDialog.text = "Valor total"
            edtValor_EntradaNewDialog.setText(entrada!!.ent_valor.toString())
        }
        else{
            txtValor_EntradaNewDialog.visibility = View.GONE
            edtValor_EntradaNewDialog.visibility = View.GONE
        }

        if(entrada!!.ent_reg != null){

        }
        else{
            linAgr_EntradaNewDialog.visibility = View.GONE
        }


    }
}
