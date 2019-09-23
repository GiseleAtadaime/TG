package com.trabalho.tg.View.Dialogs

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.trabalho.tg.Controller.C_Area
import com.trabalho.tg.Helper.DBHelper
import com.trabalho.tg.Model.Area

import com.trabalho.tg.R
import kotlinx.android.synthetic.main.fragment_area_alter_dialog.*
import java.io.Serializable

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [AreaAlterDialogFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [AreaAlterDialogFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class AreaAlterDialogFragment : DialogFragment() {
    // TODO: Rename and change types of parameters
    private var param1: Area? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getSerializable(ARG_PARAM1) as Area
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_area_alter_dialog, container, false)
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
         * @return A new instance of fragment AreaAlterDialogFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: Area) =
            AreaAlterDialogFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1 as Serializable)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var dbHelper = DBHelper(context)


        btnCriarAlt_AreaDialog.setOnClickListener {
            var nome : String = edtTxtNome_AreaDialog.text.toString()
            //var imgLocation = imgArea_DialogFragment.
            var contagem : String = edtTxtNome_AreaDialog.text.toString()

            var area = param1!!

            if (area.ar_nome.equals(nome)){
                area.ar_nome = nome
            }
            if (area.ar_lote_cont.equals(contagem)){
                area.ar_lote_cont = contagem
            }

            if(C_Area().updateArea(dbHelper,param1!!)){
                Toast.makeText(context, "Alterado com sucesso!", Toast.LENGTH_SHORT).show()
                param1 = area
            }
            else{
                Toast.makeText(context, "Erro ao alterar as informações da área!", Toast.LENGTH_SHORT).show()
            }
        }

        btnDismiss_AreaDialog.setOnClickListener({
            dismiss()
        })
    }
}
