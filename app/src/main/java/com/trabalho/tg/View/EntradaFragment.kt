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
import com.trabalho.tg.R


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

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
        fun newInstance(param1: String, param2: String) =
            EntradaFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var entrada : ArrayList<Entrada> = ArrayList<Entrada>()
        entrada.add(Entrada(1))
        entrada[0].ent_desc = "Entrada 1"

        entrada.add(Entrada(2))
        entrada[1].ent_desc = "Entrada 2"

        entrada.add(Entrada(3))
        entrada[2].ent_desc = "Entrada 3"

        entrada.add(Entrada(4))
        entrada[3].ent_desc = "Entrada 4"

        val mlistener = fun(view : View, position: Int) {
            Toast.makeText(context, "Entrada position:  $position", Toast.LENGTH_SHORT).show()
            onEntradaSelected(entrada[position])
        }


        var recyclerView = view.findViewById<RecyclerView>(R.id.recView_Entrada)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        recyclerView.adapter = EntradaAdapter(entrada, context, mlistener)





    }
}
