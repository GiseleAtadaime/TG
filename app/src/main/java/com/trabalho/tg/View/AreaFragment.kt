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
import com.trabalho.tg.Adapters.AreaAdapter
import com.trabalho.tg.Controller.C_Area
import com.trabalho.tg.Helper.Contrato
import com.trabalho.tg.Helper.DBHelper
import com.trabalho.tg.Model.Area
import com.trabalho.tg.R
import java.io.Serializable


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "area"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [AreaFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [AreaFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class AreaFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var area: ArrayList<Area>? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            area = it.getSerializable(ARG_PARAM1) as ArrayList<Area>
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view  = inflater.inflate(R.layout.fragment_area, container, false)

        return view
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onAreaSelected(area: List<Area>, pos : Int) {
        listener?.onAreaSelected(area, pos)
    }

    fun showAreaDialog(area : Area){
        listener?.showAreaDialog(area)
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
        fun onAreaSelected(area: List<Area>, pos : Int)
        fun showAreaDialog(area : Area)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param area Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AreaFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(area: ArrayList<Area>) =
            AreaFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, area as Serializable)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var dbHelper = DBHelper(context)
        var c_area = C_Area()

        val mlistener = fun(view : View, position: Int, aread : Area) {
            Toast.makeText(context, "Position $position", Toast.LENGTH_SHORT).show();
            onAreaSelected(area!!, position)
            showAreaDialog(aread)
        }

        var recyclerView = view.findViewById<RecyclerView>(R.id.recView_AreaFrag)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        recyclerView.adapter = AreaAdapter(area!!, context, mlistener)
    }

}

