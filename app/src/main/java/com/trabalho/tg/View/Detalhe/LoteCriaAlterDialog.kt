package com.trabalho.tg.View.Detalhe

import android.app.Activity
import androidx.appcompat.app.AlertDialog
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.FileProvider
import com.trabalho.tg.Controller.C_Lote
import com.trabalho.tg.Helper.DBHelper
import com.trabalho.tg.Helper.cameraUtils
import com.trabalho.tg.Model.Area
import com.trabalho.tg.Model.Lote
import com.trabalho.tg.R
import kotlinx.android.synthetic.main.fragment_area_alter_dialog.*
import kotlinx.android.synthetic.main.fragment_lote_alter_dialog.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.Serializable
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val LOTEPAM = "param1"
private const val TIPOPAM = "param2"
private const val USERID = "userid"
private const val AREAID = "areaid"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [LoteCriaAlterDialog.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [LoteCriaAlterDialog.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class LoteCriaAlterDialog : Fragment() {
    // TODO: Rename and change types of parameters
    private var lotePam: Lote? = null
    private var tipoPam: Int? = null
    private var userid: Int? = null
    private var areaId: Int? = null
    private var listener: OnFragmentInteractionListener? = null
    val REQUEST_IMAGE_CAPTURE = 1
    val REQUEST_GALLERY_IMAGE = 2
    private var bitmap : Bitmap? = null
    private var image_path : String? = null
    private var image_big : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            lotePam = it.getSerializable(LOTEPAM) as Lote
            tipoPam = it.getInt(TIPOPAM)
            userid = it.getInt(USERID)
            areaId = it.getInt(AREAID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lote_alter_dialog, container, false)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onCloseLoteDialog(areaId : Int) {
        listener?.onCloseLoteDialog(areaId)
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
        fun onCloseLoteDialog(areaId: Int)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoteCriaAlterDialog.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(lotePam: Lote, tipoPam: Int, user : Int, areaId : Int) =
            LoteCriaAlterDialog().apply {
                arguments = Bundle().apply {
                    putSerializable(LOTEPAM, lotePam as Serializable)
                    putInt(TIPOPAM, tipoPam)
                    putInt(USERID, user)
                    putInt(AREAID, areaId)
                }
            }
    }

    lateinit var currentPhotoPath: String

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if(resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_IMAGE_CAPTURE) {


//            val imageBitmap = data?.extras?.get("data") as Bitmap
                val imageBitmap = BitmapFactory.decodeFile(currentPhotoPath)
                cameraUtils().setPic(imgLote_LoteDialog, currentPhotoPath)
                bitmap = imageBitmap
            }
            else if(requestCode == REQUEST_GALLERY_IMAGE){
                var selectedImage : Uri =  data!!.getData()
                imgLote_LoteDialog.setImageURI(selectedImage)
                val imageBitmap = (imgLote_LoteDialog.drawable as BitmapDrawable).bitmap
                bitmap = imageBitmap

            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

            }
            catch(e : Exception){
                System.out.println("ERRO CRIANDO FOTO : " + e.message)
            }

        }

        btnDismiss_LoteDialog.text = getString(R.string.cancelar)

        btnDismiss_LoteDialog.setOnClickListener(){
            listener?.onCloseLoteDialog(areaId!!)
        }

        imgLote_LoteDialog.setOnClickListener {

            layourFoto_LoteDialog.visibility = View.VISIBLE


        }

        btnTirarFoto_LoteDialog.setOnClickListener {
            dispatchTakePictureIntent()
        }

        btnGaleria_LoteDialog.setOnClickListener{
            var pickPhoto : Intent =  Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(pickPhoto , REQUEST_GALLERY_IMAGE)
        }


        imgLote_LoteDialog.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus){
                layourFoto_LoteDialog.visibility = View.VISIBLE
            }
            else{
                layourFoto_LoteDialog.visibility = View.GONE
            }
        }

        //TODO set a function to determine if an image file can be converted to bitmap from gallery
        fun saveBitmap(lote : Lote) {
            val cw = ContextWrapper(context)
            val directory = cw.getDir("imageDir", Context.MODE_PRIVATE)
            val file = File(directory, lote.lot_nome.replace(" ","_") + ".jpg")
            //if (!file.exists()) {
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

        if (tipoPam == 1){//Alterar
            edtTxtNome_LoteDialog.setText(lotePam!!.lot_nome)
            edtPlanta_LoteDialog.setText(lotePam!!.lot_planta)
            btnCriaAlter_LoteDialog.text = getString(R.string.alter_area)

            if(lotePam!!.lot_imagem != null){
                val cw = ContextWrapper(context)
                val file = File(lotePam!!.lot_imagem)
                if (file.exists()) {
                    cameraUtils().setPic(imgLote_LoteDialog, lotePam!!.lot_imagem)
                }
            }

            btnCriaAlter_LoteDialog.setOnClickListener{
                if (edtPlanta_LoteDialog.text.isNullOrBlank()){
                    Toast.makeText(context, "Informe o produto cultivado!", Toast.LENGTH_SHORT).show()
                }

                else{
                    var lote = Lote(lotePam!!.lot_id)
                    lote.lot_nome = edtTxtNome_LoteDialog.text.toString()
                    lote.lot_planta = edtPlanta_LoteDialog.text.toString()

                    if(!lote.lot_nome.equals(lotePam!!.lot_nome) && lotePam!!.lot_imagem != null){
                        bitmap = (imgLote_LoteDialog.drawable as BitmapDrawable).bitmap
                        var file = File(lote.lot_imagem)
                        if(file.exists()){
                            try{
                                file.delete()
                            }
                            catch(e : Exception){
                                System.out.println("File not deleted" + e.message)
                            }
                        }
                    }
                    saveBitmap(lote)
                    lote.lot_imagem = image_path

                    if (C_Lote().updateLote(DBHelper(context), lote)){
                        val builder = AlertDialog.Builder(this!!.context!!)
                        builder.setTitle("Alterar lote")
                        builder.setMessage("O ${lote.lot_nome} foi alterado com successo!")
                        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                            dialog.dismiss()
                            listener?.onCloseLoteDialog(areaId!!)
                        }
                        builder.show()
                    }
                }
            }
        }
        else{//Criar
            edtTxtNome_LoteDialog.setText("")
            btnCriaAlter_LoteDialog.text = getString(R.string.cria_area)

            btnCriaAlter_LoteDialog.setOnClickListener{
                if (edtPlanta_LoteDialog.text.isNullOrBlank()){
                    Toast.makeText(context, "Informe o produto cultivado!", Toast.LENGTH_SHORT).show()
                }
                else{

                    var lote = Lote(0)
                    lote.lot_planta = edtPlanta_LoteDialog.text.toString()

                    saveBitmap(lote)
                    lote.lot_imagem = image_path

                    if(edtTxtNome_LoteDialog.text.isNullOrBlank()){
                        lote.lot_nome = "20191026A"//TODO mudar essa lógica
                    }
                    else{
                        lote.lot_nome = edtTxtNome_LoteDialog.text.toString()
                    }

                    if (C_Lote().insertLote(DBHelper(context), lote ,areaId, userid)){
                        val builder = AlertDialog.Builder(this!!.context!!)
                        builder.setTitle("Criação de lote")
                        builder.setMessage("O lote foi criado com successo!")

                        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                            dialog.dismiss()
                            listener?.onCloseLoteDialog(areaId!!)
                        }

                        builder.show()
                    }
                }
            }
        }
    }
}
