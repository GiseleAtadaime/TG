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
import com.trabalho.tg.Adapters.LoteAdapter
import com.trabalho.tg.Model.Lote
import com.trabalho.tg.R


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

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
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
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
    fun onLoteSelected(lote : List<Lote>, pos : Int) {
        listener?.onLoteSelected(lote, pos)
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
        fun onLoteSelected(lote : List<Lote>, pos : Int)
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
        fun newInstance(param1: String, param2: String) =
            LoteFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var lote : ArrayList<Lote> = ArrayList<Lote>()
        lote.add(Lote(1))
        lote[0].lot_nome = "Lote 1"

        lote.add(Lote(2))
        lote[1].lot_nome = "Lote 2"

        lote.add(Lote(3))
        lote[2].lot_nome = "Lote 3"

        lote.add(Lote(4))
        lote[3].lot_nome = "Lote 4"

        lote.add(Lote(5))
        lote[4].lot_nome = "Lote 5"


        val mlistener = fun(view : View, position: Int) {
            Toast.makeText(context, "Position $position", Toast.LENGTH_SHORT).show();
            onLoteSelected(lote, position)
        }


        var recyclerView = view.findViewById<RecyclerView>(R.id.recView_LoteFrag)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        recyclerView.adapter = LoteAdapter(lote, context, mlistener)

    }
}
