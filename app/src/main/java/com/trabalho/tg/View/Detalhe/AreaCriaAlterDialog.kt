package com.trabalho.tg.View.Detalhe

import android.content.Context
import android.content.res.Resources
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.trabalho.tg.Model.Area
import com.trabalho.tg.R
import kotlinx.android.synthetic.main.fragment_area_alter_dialog.*
import java.io.Serializable

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val AREAPAM = "param1"
private const val TIPOPAM = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [AreaCriaAlterDialog.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [AreaCriaAlterDialog.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class AreaCriaAlterDialog : Fragment() {
    // TODO: Rename and change types of parameters
    private var areaPam: Area? = null
    private var tipoPam: Int? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            areaPam = it.getSerializable(AREAPAM) as Area
            tipoPam = it.getInt(TIPOPAM)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
         * @return A new instance of fragment AreaCriaAlterDialog.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(areaPam: Area, tipoPam: Int) =
            AreaCriaAlterDialog().apply {
                arguments = Bundle().apply {
                    putSerializable(AREAPAM, areaPam as Serializable)
                    putInt(TIPOPAM, tipoPam)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        edtTxtNome_AreaDialog.setText(areaPam!!.ar_nome)



        if (tipoPam == 1){
            btnCriarAlt_AreaDialog.text = getString(R.string.alter_area)
        }
        else{
            btnCriarAlt_AreaDialog.text = getString(R.string.cria_area)
        }
    }
}
