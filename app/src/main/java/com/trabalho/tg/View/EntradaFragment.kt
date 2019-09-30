package com.trabalho.tg.View

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.trabalho.tg.Adapters.EntradaAdapter
import com.trabalho.tg.Model.Entrada
import com.trabalho.tg.Model.Lote
import com.trabalho.tg.R
import java.io.Serializable


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val LOTEPAM = "param1"
private const val USERID = "userid"
private const val AREAID = "areaid"

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
        return inflater.inflate(R.layout.fragment_entrada, container, false)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onEntradaSelected(entrada : Entrada) {
        listener?.onEntradaSelected(entrada)
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
        fun onEntradaSelected(entrada : Entrada)
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
        fun newInstance(lotePam: Lote, user : Int, areaId : Int) =
            EntradaFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(LOTEPAM, lotePam as Serializable)
                    putInt(USERID, user)
                    putInt(AREAID, areaId)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mlistener = fun(view : View, position: Int) {
            Toast.makeText(context, "Entrada position:  $position", Toast.LENGTH_SHORT).show()
            onEntradaSelected(lotePam!!.lot_ent[position])
        }

        var recyclerView = view.findViewById<RecyclerView>(R.id.recView_Entrada)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        recyclerView.adapter = EntradaAdapter(lotePam!!.lot_ent, context, mlistener)

    }
}
