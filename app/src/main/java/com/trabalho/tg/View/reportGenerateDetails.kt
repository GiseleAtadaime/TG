package com.trabalho.tg.View

import android.content.Context
import android.content.ContextWrapper
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.trabalho.tg.Adapters.EnderecoAdapter
import com.trabalho.tg.Helper.Report_Helper
import com.trabalho.tg.Helper.cameraUtils
import com.trabalho.tg.Model.Usuario

import com.trabalho.tg.R
import kotlinx.android.synthetic.main.fragment_report_generate_details.*
import java.io.File
import java.io.Serializable

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val USUARIO = "usuario"
private const val INDEXAREA = "indexArea"
private const val INDEXLOTE = "indexLote"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [reportGenerateDetails.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [reportGenerateDetails.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class reportGenerateDetails : Fragment() {
    // TODO: Rename and change types of parameters
    private var usuario: Usuario? = null
    private var indexArea: Int? = null
    private var indexLote: Int? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            usuario = it.getSerializable(USUARIO) as Usuario
            indexArea = it.getInt(INDEXAREA)
            indexLote = it.getInt(INDEXLOTE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_report_generate_details, container, false)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(areaID: Int, lotID : Int?, type : Boolean) {
        listener?.onEndClick(areaID, lotID,type)
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
        fun onEndClick(areaID: Int, lotID : Int?, type : Boolean)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment reportGenerateDetails.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(usuario: Usuario,indexArea: Int, indexLote:Int) =
            reportGenerateDetails().apply {
                arguments = Bundle().apply {
                    putSerializable(USUARIO, usuario)
                    putInt(INDEXAREA, indexArea)
                    putInt(INDEXLOTE, indexLote)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var end : Int = 0


        if(usuario != null){


            if(usuario!!.usr_area!!.get(indexArea!!).ar_lote!!.get(indexLote!!).lot_imagem != null) {
                val cw = ContextWrapper(context)
                val file = File(usuario!!.usr_area!!.get(indexArea!!).ar_lote!!.get(indexLote!!).lot_imagem)
                if (file.exists()) {
                    imgLoteImg_RelatFrag.setImageBitmap(BitmapFactory.decodeFile(file.toString()))
                }
            }


            txtName_RelatFrag.text = usuario!!.usr_area!!.get(indexArea!!).ar_lote!!.get(indexLote!!).lot_nome
            txtCultivo_RelatFrag.text = usuario!!.usr_area!!.get(indexArea!!).ar_lote!!.get(indexLote!!).lot_planta


            val mlistener = fun(view : View, position: Int , tipo : Int) {
                Toast.makeText(context, "Position:  $position e tipo : $tipo", Toast.LENGTH_SHORT).show();

                if(tipo == 2){
                    //onButtonPressed(usuario!!, 3, usuario!!.usr_user_info.info_endereco[position].end_id)
                    end = position
                }

            }


            var recyclerView = view.findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recview_RelatFrag)
            recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)

            recyclerView.adapter = EnderecoAdapter(usuario!!.usr_user_info.info_endereco, context, mlistener, false)


            swtFiscal_RelatFrag.setOnClickListener(){
                btnGerar_RelatFrag.isEnabled = (swtFiscal_RelatFrag.isChecked || swtGeral_RelatFrag.isChecked)
            }

            swtGeral_RelatFrag.setOnClickListener(){
                btnGerar_RelatFrag.isEnabled = (swtFiscal_RelatFrag.isChecked || swtGeral_RelatFrag.isChecked)
            }



            btnGerar_RelatFrag.setOnClickListener(){

                if(swtFiscal_RelatFrag.isChecked){
                    Report_Helper().generateRelatorioFiscal(usuario,context,indexArea,indexLote, end)
                }

                if(swtGeral_RelatFrag.isChecked){
                    Report_Helper().generateRelatorioGeral(usuario,context,indexArea,indexLote)
                }

                if(chkbxEmail_RelatFrag.isChecked){

                    Toast.makeText(context, "Enviado por e-mail!", Toast.LENGTH_SHORT).show()

                }

                Toast.makeText(context, "Relatório gerado!", Toast.LENGTH_SHORT).show()
                onButtonPressed(indexArea!!,indexLote!!, true)

            }

            btnCancelar_RelatFrag.setOnClickListener(){
                onButtonPressed( indexArea!!,indexLote!!, false)
            }


        }



    }
}
