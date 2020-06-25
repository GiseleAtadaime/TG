package com.trabalho.tg.View.Usuario

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.trabalho.tg.Controller.C_Endereco
import com.trabalho.tg.Helper.DBHelper
import com.trabalho.tg.Helper.Utils_TG
import com.trabalho.tg.Model.Endereco
import com.trabalho.tg.Model.Usuario

import com.trabalho.tg.R
import kotlinx.android.synthetic.main.fragment_usuario__endereco__alter.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val USUARIO = "usuario"
private const val ENDID = "endid"
private const val TIPO = "tipo"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [Usuario_Endereco_Alter.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [Usuario_Endereco_Alter.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class Usuario_Endereco_Alter : Fragment() {
    // TODO: Rename and change types of parameters
    private var usuario: Usuario? = null
    private var endid: Int? = null
    private var tipo: Boolean? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            usuario = it.getSerializable(USUARIO) as Usuario
            endid = it.getInt(ENDID)
            tipo = it.getBoolean(TIPO)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_usuario__endereco__alter, container, false)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(usuario : Usuario) {
        listener?.onEnderecoClick(usuario)
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
        fun onEnderecoClick(usuario : Usuario)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Usuario_Endereco_Alter.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(usuario : Usuario, endid : Int, tipo : Boolean) =
            Usuario_Endereco_Alter().apply {
                arguments = Bundle().apply {
                    putSerializable(USUARIO, usuario)
                    putInt(ENDID, endid)
                    putBoolean(TIPO, tipo)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var endNum : Int = 1
        var utils  =  Utils_TG()
        edtEndereco_EndAlter.isEnabled = false


        if(usuario!!.usr_user_info.info_endereco.size > 0 && tipo!!){
            endNum = endid!! + 1

            edtLog_EndAlter.setText(usuario!!.usr_user_info.info_endereco.get(endid!!).end_logradouro)
            edtBairro_EndAlter.setText(usuario!!.usr_user_info.info_endereco.get(endid!!).end_bairro)
            edtCidade_EndAlter.setText(usuario!!.usr_user_info.info_endereco.get(endid!!).end_Cidade)
            edtCEP_EndAlter.setText(usuario!!.usr_user_info.info_endereco.get(endid!!).end_cep.toString())
            edtUF_EndAlter.setText(usuario!!.usr_user_info.info_endereco.get(endid!!).end_uf)
            edtLat_EndAlter.setText(utils.doubleToString(usuario!!.usr_user_info.info_endereco.get(endid!!).end_catx))
            edtLong_EndAlter.setText(utils.doubleToString(usuario!!.usr_user_info.info_endereco.get(endid!!).end_carty))
        }
        else{

            endNum = usuario!!.usr_user_info.info_endereco.size + 1

            usuario!!.usr_user_info.info_endereco.add(Endereco(endNum))
        }


        edtEndereco_EndAlter.setText("Endereço " + (endNum).toString())

        btnSalvar_EndAlter.setOnClickListener {
            usuario!!.usr_user_info.info_endereco.get(endid!!).end_logradouro = edtLog_EndAlter.text.toString()
            usuario!!.usr_user_info.info_endereco.get(endid!!).end_bairro = edtBairro_EndAlter.text.toString()
            usuario!!.usr_user_info.info_endereco.get(endid!!).end_Cidade = edtCidade_EndAlter.text.toString()
            usuario!!.usr_user_info.info_endereco.get(endid!!).end_cep = utils.stringToInteger(edtCEP_EndAlter.text.toString())
            usuario!!.usr_user_info.info_endereco.get(endid!!).end_uf = edtUF_EndAlter.text.toString()
            usuario!!.usr_user_info.info_endereco.get(endid!!).end_catx =  utils.stringToDouble(edtLat_EndAlter.text.toString())
            usuario!!.usr_user_info.info_endereco.get(endid!!).end_carty =  utils.stringToDouble(edtLong_EndAlter.text.toString())



            if(tipo!!){
                if(C_Endereco().updateArea(DBHelper(context),usuario!!.usr_user_info.info_endereco.get(endid!!))){
                    Toast.makeText(context, "Endereço atualizado com sucesso!", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                if(C_Endereco().insertEndereco(DBHelper(context),usuario!!.usr_user_info.info_endereco.get(endid!!),usuario!!.usr_user_info.info_id,usuario!!.usr_id)){
                    Toast.makeText(context, "Endereço inserido com sucesso!", Toast.LENGTH_SHORT).show()
                }
            }

            onButtonPressed(usuario!!)

        }




    }
}
