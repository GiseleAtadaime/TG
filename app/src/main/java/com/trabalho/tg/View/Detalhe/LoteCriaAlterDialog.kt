package com.trabalho.tg.View.Detalhe

import androidx.appcompat.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.trabalho.tg.Controller.C_Lote
import com.trabalho.tg.Helper.DBHelper
import com.trabalho.tg.Model.Lote
import com.trabalho.tg.R
import kotlinx.android.synthetic.main.fragment_lote_alter_dialog.*
import java.io.Serializable

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val LOTEPAM = "param1"
private const val TIPOPAM = "param2"
private const val USERID = "userid"
private const val AREAID = "areaid"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [LoteCriaAlterDialog.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [LoteCriaAlterDialog.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class LoteCriaAlterDialog : Fragment() {
    // TODO: Rename and change types of parameters
    private var lotePam: Lote? = null
    private var tipoPam: Int? = null
    private var userid: Int? = null
    private var areaId: Int? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            lotePam = it.getSerializable(LOTEPAM) as Lote
            tipoPam = it.getInt(TIPOPAM)
            userid = it.getInt(USERID)
            areaId = it.getInt(AREAID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lote_alter_dialog, container, false)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onCloseLoteDialog(areaId : Int) {
        listener?.onCloseLoteDialog(areaId)
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
        fun onCloseLoteDialog(areaId: Int)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoteCriaAlterDialog.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(lotePam: Lote, tipoPam: Int, user : Int, areaId : Int) =
            LoteCriaAlterDialog().apply {
                arguments = Bundle().apply {
                    putSerializable(LOTEPAM, lotePam as Serializable)
                    putInt(TIPOPAM, tipoPam)
                    putInt(USERID, user)
                    putInt(AREAID, areaId)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnDismiss_LoteDialog.text = getString(R.string.cancelar)

        btnDismiss_LoteDialog.setOnClickListener(){
            listener?.onCloseLoteDialog(areaId!!)
        }

        if (tipoPam == 1){//Alterar
            edtTxtNome_LoteDialog.setText(lotePam!!.lot_nome)
            edtPlanta_LoteDialog.setText(lotePam!!.lot_planta)
            btnCriaAlter_LoteDialog.text = getString(R.string.alter_area)


            btnCriaAlter_LoteDialog.setOnClickListener{
                if (edtPlanta_LoteDialog.text.isNullOrBlank()){
                    Toast.makeText(context, "Informe o produto cultivado!", Toast.LENGTH_SHORT).show()
                }
                else{
                    var lote = Lote(lotePam!!.lot_id)
                    lote.lot_nome = edtTxtNome_LoteDialog.text.toString()
                    lote.lot_planta = edtPlanta_LoteDialog.text.toString()
                    lote.lot_imagem = null

                    if (C_Lote().updateLote(DBHelper(context), lote)){
                        val builder = AlertDialog.Builder(this!!.context!!)
                        builder.setTitle("Alterar lote")
                        builder.setMessage("O ${lote.lot_nome} foi alterado com successo!")

                        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                            dialog.dismiss()
                            listener?.onCloseLoteDialog(areaId!!)
                        }

                        builder.show()
                    }
                }
            }
        }
        else{//Criar
            edtTxtNome_LoteDialog.setText("")
            btnCriaAlter_LoteDialog.text = getString(R.string.cria_area)

            btnCriaAlter_LoteDialog.setOnClickListener{
                if (edtPlanta_LoteDialog.text.isNullOrBlank()){
                    Toast.makeText(context, "Informe o produto cultivado!", Toast.LENGTH_SHORT).show()
                }
                else{

                    var lote = Lote(0)
                    lote.lot_imagem = null
                    lote.lot_planta = edtPlanta_LoteDialog.text.toString()


                    if(edtTxtNome_LoteDialog.text.isNullOrBlank()){
                        lote.lot_nome = "20191026A"//TODO mudar essa lógica
                    }
                    else{
                        lote.lot_nome = edtTxtNome_LoteDialog.text.toString()
                    }

                    if (C_Lote().insertLote(DBHelper(context), lote ,areaId, userid)){
                        val builder = AlertDialog.Builder(this!!.context!!)
                        builder.setTitle("Criação de lote")
                        builder.setMessage("O lote foi criado com successo!")

                        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                            dialog.dismiss()
                            listener?.onCloseLoteDialog(areaId!!)
                        }

                        builder.show()
                    }
                }
            }
        }
    }
}
