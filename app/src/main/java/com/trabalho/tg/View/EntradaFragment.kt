package com.trabalho.tg.View

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.trabalho.tg.Adapters.EntradaAdapter
import com.trabalho.tg.Model.Entrada
import com.trabalho.tg.Model.Lote
import com.trabalho.tg.Model.Reg_Agrotoxico
import com.trabalho.tg.R
import kotlinx.android.synthetic.main.fragment_entrada.*
import java.io.Serializable


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val LOTEPAM = "param1"
private const val USERID = "userid"
private const val AREAID = "areaid"
private const val TIPOLOTE = "tipolote"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [EntradaFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [EntradaFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class EntradaFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var lotePam: Lote? = null
    private var userid: Int? = null
    private var areaId: Int? = null
    private var tipolote: Boolean? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            lotePam = it.getSerializable(LOTEPAM) as Lote
            userid = it.getInt(USERID)
            areaId = it.getInt(AREAID)
            tipolote = it.getBoolean(TIPOLOTE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_entrada, container, false)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onEntradaSelected(entrada : Entrada, userId : Int, areaId : Int, loteId : Int, new : Boolean, tipolote : Boolean) {
        listener?.onEntradaSelected(entrada, userId, areaId, loteId, new, tipolote)
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
        fun onEntradaSelected(entrada : Entrada,userId : Int, areaId : Int, loteId : Int, new : Boolean, tipolote : Boolean)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment EntradaFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(lotePam: Lote, user : Int, areaId : Int, tipolote : Boolean) =
            EntradaFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(LOTEPAM, lotePam as Serializable)
                    putInt(USERID, user)
                    putInt(AREAID, areaId)
                    putBoolean(TIPOLOTE, tipolote)
                }
            }
    }

    @SuppressLint("RestrictedApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mlistener = fun(view : View, position: Int) {
            Toast.makeText(context, "Entrada position:  $position", Toast.LENGTH_SHORT).show()
            onEntradaSelected(lotePam!!.lot_ent[position], userid!!, areaId!!, lotePam!!.lot_id, false, tipolote!!)
        }

        var recyclerView = view.findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recView_Entrada)
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity) as androidx.recyclerview.widget.RecyclerView.LayoutManager?

        recyclerView.adapter = EntradaAdapter(lotePam!!.lot_ent, context, mlistener)

        var i : Int? = lotePam!!.lot_ent.find { it.ent_tipo == 4 }?.ent_numero

        if ( i != null ){
            btnFechar_EntradaFragment.visibility = View.VISIBLE
            btnFechar_EntradaFragment.text = "Fechar lote"

        }
        else{
            btnFechar_EntradaFragment.visibility = View.INVISIBLE
        }

        if(!tipolote!!){
            fBtnAdd_EntradaFragment.visibility = View.GONE
        }

        fBtnAdd_EntradaFragment.setOnClickListener {
                onEntradaSelected(Entrada(0), userid!!, areaId!!, lotePam!!.lot_id, true, tipolote!!)

        }

    }
}
