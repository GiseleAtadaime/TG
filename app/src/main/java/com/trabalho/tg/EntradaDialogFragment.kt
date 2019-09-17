package com.trabalho.tg

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text

class EntradaDialogFragment : DialogFragment(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view : View = inflater.inflate(R.layout.entrada_dialog,container,false)

        var imgLabel = view.findViewById<ImageView>(R.id.imgLabel_EntradaDialog)
        var imgBtnAlter = view.findViewById<ImageButton>(R.id.imgBtnAlter_EntradaDialog)
        var imgBtnExc = view.findViewById<ImageButton>(R.id.imgBtnDelete_EntradaDialog)
        var txtTitulo = view.findViewById<TextView>(R.id.txtTitulo_EntradaDialog)
        var txtData = view.findViewById<TextView>(R.id.txtData_EntradaDialog)
        var txtTipo = view.findViewById<TextView>(R.id.txtTipo_EntradaDialog)
        var mtxtConteudo = view.findViewById<TextView>(R.id.txtConteudo_EntradaFragment)

        return view
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {





        return super.onCreateDialog(savedInstanceState)
    }


}