package com.trabalho.tg

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.trabalho.tg.Controller.C_Usuario
import com.trabalho.tg.Helper.CognitoHelper
import com.trabalho.tg.Helper.DBHelper
import com.trabalho.tg.Model.Usuario
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login_new.*
import java.io.Serializable


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [LoginNewFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [LoginNewFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class LoginNewFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_login_new, container, false)
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
         * @return A new instance of fragment LoginNewFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginNewFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        edtVerifCode_LoginNew.visibility = GONE
        btnValidar_LoginNew.visibility = GONE


        btnCria_LoginNew.setOnClickListener {
/*
                var helper = CognitoHelper(it.context)

                println(edtSenha_LoginNew.text.toString())
                helper.registerUser(edtNome_LoginNew.text.toString(),edtEmail_LoginNew.text.toString(),edtEmail_LoginNew.text.toString())

                edtVerifCode_LoginNew.visibility = VISIBLE
                btnValidar_LoginNew.visibility = VISIBLE
*/
            //val intent = Intent (activity, MainActivity::class.java)
            //startActivity(intent)
            val dbHelper = DBHelper(context)
            val cUsuario = C_Usuario()

            var u = Usuario(0)
            u.usr_nome = edtNome_LoginNew.text.toString()
            u.usr_email = edtEmail_LoginNew.text.toString()
            u.usr_senha = edtSenha_LoginNew.text.toString()
            if(cUsuario.insertUsuario(dbHelper,u)){
                println("Inserido com sucesso!!")
            }

            var user = cUsuario.selectUsuario(dbHelper)
            if (user.size != 0) {
                val intent = Intent (activity, MainActivity::class.java)
                intent.putExtra("usuario", user as Serializable)
                startActivity(intent)
                activity?.finish()

            }

        }

    }
}
