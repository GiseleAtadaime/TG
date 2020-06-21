package com.trabalho.tg.View.Usuario

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.trabalho.tg.Controller.C_User_Info
import com.trabalho.tg.Helper.DBHelper
import com.trabalho.tg.Helper.Utils_TG
import com.trabalho.tg.Model.User_Info
import com.trabalho.tg.Model.Usuario

import com.trabalho.tg.R
import kotlinx.android.synthetic.main.fragment_usuario_info_alter.*
import java.io.Serializable

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val USUARIO = "usuario"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [UsuarioInfoAlter.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [UsuarioInfoAlter.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class UsuarioInfoAlter : Fragment() {
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
        return inflater.inflate(R.layout.fragment_usuario_info_alter, container, false)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onSaveUsuarioInfo() {
        listener?.onSaveUsuarioInfo()
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
        fun onSaveUsuarioInfo()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment UsuarioInfoAlter.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(u: Usuario) =
            UsuarioInfoAlter().apply {
                arguments = Bundle().apply {
                    putSerializable(USUARIO, u as Serializable)
                }
            }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(usuario!!.usr_user_info == null){
            txtRS_AlterUserInfo.setTextColor(resources.getColor(R.color.required_label))
            txtCNPJ_AlterUserInfo.setTextColor(resources.getColor(R.color.required_label))
        }
        else{
            edtNF_AlterUserInfo.setText(usuario!!.usr_user_info.info_nomefantasia)
            edtRS_AlterUserInfo.setText(usuario!!.usr_user_info.info_rzsocial)
            edtCNPJ_AlterUserInfo.setText(usuario!!.usr_user_info.info_cnpj)
            edtTel_AlterUserInfo.setText(usuario!!.usr_user_info.info_telefone.toString())
            edtSite_AlterUserInfo.setText(usuario!!.usr_user_info.info_site)

        }

        btnSave_AlterUserInfo.setOnClickListener{
            var usr_user_info = User_Info(0)
            usr_user_info.info_nomefantasia = edtNF_AlterUserInfo.text.toString()
            usr_user_info.info_rzsocial = edtRS_AlterUserInfo.text.toString()
            usr_user_info.info_cnpj = edtCNPJ_AlterUserInfo.text.toString()
            usr_user_info.info_telefone = Utils_TG().stringToInteger(edtTel_AlterUserInfo.text.toString())
            usr_user_info.info_site = edtSite_AlterUserInfo.text.toString()
            try{
                if(usuario!!.usr_user_info == null){
                    if (C_User_Info().insertUser_Info(DBHelper(context),usr_user_info,usuario!!.usr_id)){
                        Toast.makeText(context, "Seus dados foram incluídos com sucesso!", Toast.LENGTH_SHORT).show()
                    }
                }
                else{
                    usr_user_info.info_id = usuario!!.usr_user_info.info_id
                    if(C_User_Info().updateUser_Info(DBHelper(context),usr_user_info)){
                        Toast.makeText(context, "Seus dados foram atualizados com sucesso!", Toast.LENGTH_SHORT).show()
                    }
                }
                onSaveUsuarioInfo()
            }
            catch(e : Exception){
                Toast.makeText(context, "Não foi possível inserir os seus dados. Tente novamente mais tarde!", Toast.LENGTH_SHORT).show()
            }
        }


    }

}
