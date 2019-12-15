package com.trabalho.tg.View

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.trabalho.tg.Adapters.LoteAdapter
import com.trabalho.tg.Controller.C_Lote
import com.trabalho.tg.Helper.DBHelper
import com.trabalho.tg.Model.Area
import com.trabalho.tg.Model.Lote
import com.trabalho.tg.R
import java.io.Serializable


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val AREAPAM = "areaPam"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [LoteFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [LoteFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class LoteFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var areaPam: Area? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            areaPam = it.getSerializable(AREAPAM) as Area

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lote, container, false)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onLoteSelected(lote : List<Lote>, pos : Int, tipo : Int, areaId: Int) {
        listener?.onLoteSelected(lote, pos, tipo, areaId)
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
        fun onLoteSelected(lote : List<Lote>, pos : Int, tipo : Int, areaId : Int)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoteFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(areaPam: Area) =
            LoteFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(AREAPAM, areaPam as Serializable)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mlistener = fun(view : View, position: Int, tipo : Int) {
            Toast.makeText(context, "Position $position", Toast.LENGTH_SHORT).show();
            onLoteSelected(areaPam!!.ar_lote, position, tipo, areaPam!!.ar_id)
        }


        var recyclerView = view.findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recView_LoteFrag)
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)

        recyclerView.adapter = LoteAdapter(areaPam!!.ar_lote, context, mlistener)

    }
}
