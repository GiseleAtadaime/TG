package com.trabalho.tg.View.Detalhe

import android.app.Activity.RESULT_OK
import androidx.appcompat.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.trabalho.tg.Controller.C_Area
import com.trabalho.tg.Helper.DBHelper
import com.trabalho.tg.Model.Area
import com.trabalho.tg.R
import kotlinx.android.synthetic.main.fragment_area_alter_dialog.*
import java.io.Serializable
import android.content.ContextWrapper
import android.graphics.BitmapFactory
import android.graphics.Camera
import android.net.Uri
import android.os.Environment
import android.util.Log
import androidx.core.content.FileProvider
import com.trabalho.tg.Helper.cameraUtils
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val AREAPAM = "param1"
private const val TIPOPAM = "param2"
private const val USERID = "userid"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [AreaCriaAlterDialog.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [AreaCriaAlterDialog.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class AreaCriaAlterDialog : Fragment() {
    // TODO: Rename and change types of parameters
    private var areaPam: Area? = null
    private var tipoPam: Int? = null
    private var userid: Int? = null
    private var listener: OnFragmentInteractionListener? = null
    val REQUEST_IMAGE_CAPTURE = 1
    private var bitmap : Bitmap? = null
    private var image_path : String? = null
    private var image_big : String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            areaPam = it.getSerializable(AREAPAM) as Area
            tipoPam = it.getInt(TIPOPAM)
            userid = it.getInt(USERID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_area_alter_dialog, container, false)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onCloseAreaDialog() {
        listener?.onCloseAreaDialog()
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
        fun onCloseAreaDialog()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AreaCriaAlterDialog.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(areaPam: Area, tipoPam: Int, user : Int) =
            AreaCriaAlterDialog().apply {
                arguments = Bundle().apply {
                    putSerializable(AREAPAM, areaPam as Serializable)
                    putInt(TIPOPAM, tipoPam)
                    putInt(USERID, user)
                }
            }
    }

    lateinit var currentPhotoPath: String

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
//            val imageBitmap = data?.extras?.get("data") as Bitmap
            val imageBitmap = BitmapFactory.decodeFile(currentPhotoPath)
            imgArea_DialogFragment.setImageBitmap(imageBitmap)
            bitmap = imageBitmap
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//        fun dispatchTakePictureIntent() {
//            val pm = this!!.context!!.getPackageManager()
//            Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
//                takePictureIntent.resolveActivity(pm)?.also {
//                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
//                }
//            }
//        }



        @Throws(IOException::class)
        fun createImageFile(context: Context): File {
            // Create an image file name
            val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            val storageDir: File = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)

            return File(storageDir, "$timeStamp.jpg")
        }

        fun dispatchTakePictureIntent() {
            try{
                val file = createImageFile(context!!)
                val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
                val intentCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                currentPhotoPath = file.toString()
                intentCamera.putExtra(
                    MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(
                        this!!.context!!,
                        "com.trabalho.tg.fileprovider",
                        file
                    )
                )

                if(intentCamera.resolveActivity(context!!.packageManager) != null){
                    startActivityForResult(intentCamera, REQUEST_IMAGE_CAPTURE)
                }
                else{

                }

            }
            catch(e : Exception){
                System.out.println("ERRO CRIANDO FOTO : " + e.message)
            }

        }





        var adapter = ArrayAdapter.createFromResource(context,R.array.LoteContArray, android.R.layout.simple_spinner_dropdown_item)

        btnDismiss_AreaDialog.setOnClickListener(){
            listener?.onCloseAreaDialog()
        }

        spiContagem_AreaDialog.adapter = adapter

        spiContagem_AreaDialog.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if(position == 0){
                    txtExemplo_AlterAreaFragment.setText("Exemplo: 12122019_A")
                }
                else{
                    txtExemplo_AlterAreaFragment.setText("Exemplo: 12122019_01")
                }
            }
        }

//        imgArea_DialogFragment.setOnClickListener {
//            dispatchTakePictureIntent()
//        }
        imgArea_DialogFragment.setOnClickListener {

            dispatchTakePictureIntent()

        }

        //TODO set a function to determine if an image file can be converted to bitmap from gallery
        fun saveBitmap(area : Area) {
            val cw = ContextWrapper(context)
            val directory = cw.getDir("imageDir", Context.MODE_PRIVATE)
            val file = File(directory, area.ar_nome.replace(" ","_") + ".jpg")
            if (!file.exists()) {
                Log.d("path", file.toString())
                image_path = file.toString()
                var fos: FileOutputStream? = null
                try {
                    fos = FileOutputStream(file)
                    bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, fos)
                    fos!!.flush()
                    fos!!.close()
                } catch (e: java.io.IOException) {
                    e.printStackTrace()
                }
            }
        }





        if (tipoPam == 1){//Alterar
            edtTxtNome_AreaDialog.setText(areaPam!!.ar_nome)

            btnCriarAlt_AreaDialog.text = getString(R.string.alter_area)

            spiContagem_AreaDialog.setSelection(areaPam!!.getLoteContID())

            if(areaPam!!.ar_imagem != null){
                val cw = ContextWrapper(context)
                val file = File(areaPam!!.ar_imagem)
                if (file.exists()) {
                    imgArea_DialogFragment.setImageBitmap(BitmapFactory.decodeFile(file.toString()))
                }
            }

            btnCriarAlt_AreaDialog.setOnClickListener{
                if (edtTxtNome_AreaDialog.text.isNullOrBlank()){
                    Toast.makeText(context, "Informe o nome para a área!", Toast.LENGTH_SHORT).show()
                }
                else{
                    var area = Area(areaPam!!.ar_id)
                    area.setAr_lote_cont(spiContagem_AreaDialog.selectedItemId.toInt())
                    area.ar_nome = edtTxtNome_AreaDialog.text.toString()
                    area.ar_del = "A"
                    saveBitmap(area)
                    area.ar_imagem = image_path
                    if (C_Area().updateArea(DBHelper(context), area)){
                        val builder = AlertDialog.Builder(this!!.context!!)
                        builder.setTitle("Alterar área")
                        builder.setMessage("A ${area.ar_nome} foi alterada com successo!")

                        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                            dialog.dismiss()
                            listener?.onCloseAreaDialog()
                        }

                        builder.show()
                    }
                }
            }
        }
        else{//Criar
            edtTxtNome_AreaDialog.setText("")
            btnCriarAlt_AreaDialog.text = getString(R.string.cria_area)

            btnCriarAlt_AreaDialog.setOnClickListener{
                if (edtTxtNome_AreaDialog.text.isNullOrBlank()){
                    Toast.makeText(context, "Informe o nome para a área!", Toast.LENGTH_SHORT).show()
                }
                else{

                    var area = Area(0)
                    area.setAr_lote_cont(spiContagem_AreaDialog.selectedItemId.toInt())
                    area.ar_nome = edtTxtNome_AreaDialog.text.toString()
                    area.ar_del = "A"
                    saveBitmap(area)
                    area.ar_imagem = image_path

                    if (C_Area().insertArea(DBHelper(context), area , userid)){
                        val builder = AlertDialog.Builder(this!!.context!!)
                        builder.setTitle("Criação de área")
                        builder.setMessage("A área foi criada com successo!")

                        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                            dialog.dismiss()
                            listener?.onCloseAreaDialog()
                        }

                        builder.show()
                    }
                }
            }
        }

    }


}
