package com.trabalho.tg.View

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trabalho.tg.Model.Entrada
import com.trabalho.tg.Model.Lote
import com.trabalho.tg.R
import kotlinx.android.synthetic.main.fragment_lote_detalhe.*
import java.io.Serializable
import kotlin.collections.ArrayList


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val LOTEPAM = "param1"
private const val USERID = "userid"
private const val AREAID = "areaid"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [LoteDetalheFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [LoteDetalheFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class LoteDetalheFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var lotePam: Lote? = null
    private var userid: Int? = null
    private var areaId: Int? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            lotePam = it.getSerializable(LOTEPAM) as Lote
            userid = it.getInt(USERID)
            areaId = it.getInt(AREAID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lote_detalhe, container, false)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onAlterButtonClick(lote : Lote,  tipo : Int,areaId :Int, userId : Int, loteId : Int) {
        listener?.onAlterButtonClick(lote, tipo, areaId, userId, loteId)
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
        fun onAlterButtonClick(lote : Lote, tipo : Int, areaId :Int, userId : Int, loteId : Int)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoteDetalheFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(lotePam: Lote, user : Int, areaId : Int) =
            LoteDetalheFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(LOTEPAM, lotePam as Serializable)
                    putInt(USERID, user)
                    putInt(AREAID, areaId)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        txtNome_LoteDetFrag.text = lotePam!!.lot_nome

        fBtnAdd_LoteDetFrag.setOnClickListener{
            if (menu_LoteDetFrag.visibility == View.VISIBLE){
                menu_LoteDetFrag.visibility = View.GONE

            }
            else{
                menu_LoteDetFrag.visibility = View.VISIBLE

            }
        }


        imgBtnAlt_LoteDetFrag.setOnClickListener{
            onAlterButtonClick(lotePam!!,1, areaId!!,userid!!,lotePam!!.lot_id)
        }

        imgBtnExc_LoteDetFrag.setOnClickListener{
            onAlterButtonClick(lotePam!!,2, areaId!!,userid!!,lotePam!!.lot_id)
        }

        btnFech_LoteDetFrag.setOnClickListener{
            onAlterButtonClick(lotePam!!,3, areaId!!,userid!!,lotePam!!.lot_id)
        }

        btnAdd_LoteDetFrag.setOnClickListener{
            onAlterButtonClick(lotePam!!,4, areaId!!,userid!!,lotePam!!.lot_id)
        }

        btnAlt_LoteDetFrag.setOnClickListener{
            onAlterButtonClick(lotePam!!,5, areaId!!,userid!!,lotePam!!.lot_id)
        }

    }
}
