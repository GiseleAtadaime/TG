package com.trabalho.tg.View.Usuario

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trabalho.tg.Model.Usuario
import com.trabalho.tg.R
import kotlinx.android.synthetic.main.fragment_usuario_info_geral.*
import java.io.Serializable


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val USUARIO = "usuario"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [usuarioInfoGeral.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [usuarioInfoGeral.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class UsuarioInfoGeral : Fragment() {
    // TODO: Rename and change types of parameters
    private var usuario: Usuario? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            usuario = it.getSerializable(USUARIO) as Usuario
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_usuario_info_geral, container, false)
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
         * @return A new instance of fragment usuarioInfoGeral.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(usuario: Usuario) =
            UsuarioInfoGeral().apply {
                arguments = Bundle().apply {
                    putSerializable(USUARIO, usuario as Serializable)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        txtNome_UsuarioGeral.text = usuario!!.usr_nome
        txtEmail_UsuarioGeral.text = usuario!!.usr_email
        //TODO set foto de perfil

        if(usuario!!.usr_user_info != null){

            txtNF_UsuarioGeral.text = usuario!!.usr_user_info.info_nomefantasia
            txtCNPJ_UsuarioGeral.text = usuario!!.usr_user_info.info_cnpj
            txtRS_UsuarioGeral.text = usuario!!.usr_user_info.info_rzsocial
            txtTelefone_UsuarioGeral.text = usuario!!.usr_user_info.info_telefone.toString()
            txtSite_UsuarioGeral.text = usuario!!.usr_user_info.info_site


        }
        else{
            txtNF_UsuarioGeral.text = "Nome Fantasia: --"
            txtCNPJ_UsuarioGeral.text = "CNPJ: --"
            txtRS_UsuarioGeral.text = "Razão Social: --"
            txtTelefone_UsuarioGeral.text = "Telefone: --"
            txtSite_UsuarioGeral.text = "Site: --"
        }

    }
}
