package com.trabalho.tg.View.Usuario

import android.app.Activity.RESULT_OK
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
import com.trabalho.tg.Adapters.EnderecoAdapter
import com.trabalho.tg.Controller.C_User_Info
import com.trabalho.tg.Controller.C_Usuario
import com.trabalho.tg.Helper.DBHelper
import com.trabalho.tg.Helper.cameraUtils
import com.trabalho.tg.Model.Usuario
import com.trabalho.tg.R
import com.trabalho.tg.View.MainActivity
import kotlinx.android.synthetic.main.fragment_usuario_info_geral.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.Serializable
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*


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
    val REQUEST_IMAGE_CAPTURE = 1
    val REQUEST_GALLERY_IMAGE = 2
    private var bitmap : Bitmap? = null
    private var image_path : String? = null
    private var image_big : String? = null

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
    fun onButtonPressed(usuario: Usuario, tipo: Int, endID : Int?) {
        listener?.onInfoClick(usuario, tipo, endID)
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
        fun onInfoClick(usuario : Usuario, tipo : Int, endID : Int?)
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

    lateinit var currentPhotoPath: String
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if(resultCode == RESULT_OK) {
            if (requestCode == REQUEST_IMAGE_CAPTURE) {


//            val imageBitmap = data?.extras?.get("data") as Bitmap
                val imageBitmap = BitmapFactory.decodeFile(currentPhotoPath)
                cameraUtils().setPic(imgFoto_UsuarioGeral, currentPhotoPath)
                bitmap = imageBitmap
            }
            else if(requestCode == REQUEST_GALLERY_IMAGE){
                var selectedImage : Uri =  data!!.getData()
                imgFoto_UsuarioGeral.setImageURI(selectedImage)
                val imageBitmap = (imgFoto_UsuarioGeral.drawable as BitmapDrawable).bitmap
                bitmap = imageBitmap

            }

            usuarioInfoBar.visibility = View.VISIBLE
            saveBitmap()
            usuarioInfoBar.visibility = View.GONE
        }
    }

    fun saveBitmap() {
        val cw = ContextWrapper(context)
        val directory = cw.getDir("imageDir", Context.MODE_PRIVATE)
        val file = File(directory, usuario!!.usr_nome.replace(" ","_") + ".jpg")
        //if (!file.exists()) {
        Log.d("path", file.toString())
        image_path = file.toString()
        var fos: FileOutputStream? = null
        usuario!!.usr_fotoperfil = image_path
        if(C_Usuario().updateUsuario(DBHelper(context),usuario!!)){
            try {
                fos = FileOutputStream(file)
                bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, fos)
                fos!!.flush()
                fos!!.close()

            } catch (e: java.io.IOException) {
                e.printStackTrace()
            }
        }
        else{
            usuario!!.usr_fotoperfil = null
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val con = requireActivity()
        val frag = con.supportFragmentManager.findFragmentByTag("USUARIOGERAL_FRAGMENT")

//        if(frag != null && frag.isVisible){
//            usuario!!.usr_user_info = C_User_Info().selectUser_Info(DBHelper(context))
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

            }
            catch(e : Exception){
                System.out.println("ERRO CRIANDO FOTO : " + e.message)
            }

        }


        txtNome_UsuarioGeral.text = usuario!!.usr_nome
        txtEmail_UsuarioGeral.text = usuario!!.usr_email
        //TODO set foto de perfil


        imgFoto_UsuarioGeral.setOnClickListener {

            fotoLayout.visibility = View.VISIBLE


        }

        btnTakePhoto_UsuarioGeral.setOnClickListener {
            dispatchTakePictureIntent()
        }

        btnSelectPhoto_UsuarioGeral.setOnClickListener{
            var pickPhoto : Intent =  Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(pickPhoto , REQUEST_GALLERY_IMAGE)

        }


        imgFoto_UsuarioGeral.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus){
                fotoLayout.visibility = View.VISIBLE
            }
            else{
                fotoLayout.visibility = View.GONE
            }
        }


        usuarioInfoBar.visibility = View.GONE
        if(usuario!!.usr_user_info != null){

            txtNF_UsuarioGeral.text = usuario!!.usr_user_info.info_nomefantasia
            txtCNPJ_UsuarioGeral.text = usuario!!.usr_user_info.info_cnpj
            txtRS_UsuarioGeral.text = usuario!!.usr_user_info.info_rzsocial
            txtTelefone_UsuarioGeral.text = usuario!!.usr_user_info.info_telefone.toString()
            txtSite_UsuarioGeral.text = usuario!!.usr_user_info.info_site

            if(usuario!!.usr_fotoperfil != null){
                val cw = ContextWrapper(context)
                val file = File(usuario!!.usr_fotoperfil)
                if (file.exists()) {
                    cameraUtils().setPic(imgFoto_UsuarioGeral, usuario!!.usr_fotoperfil)
                }
            }



            val mlistener = fun(view : View, position: Int , tipo : Int) {
                Toast.makeText(context, "Position $position e tipo : $tipo", Toast.LENGTH_SHORT).show();

                if(tipo == 1){
                    onButtonPressed(usuario!!, 3, usuario!!.usr_user_info.info_endereco[position].end_id)
                }
                else{
                    onButtonPressed(usuario!!, 4, position)
                }

            }


            var recyclerView = view.findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recViewEndereco_UsuarioGeral)
            recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)

            recyclerView.adapter = EnderecoAdapter(usuario!!.usr_user_info.info_endereco, context, mlistener)


        }
        else{
            txtNF_UsuarioGeral.text = "Nome Fantasia: --"
            txtCNPJ_UsuarioGeral.text = "CNPJ: --"
            txtRS_UsuarioGeral.text = "Razão Social: --"
            txtTelefone_UsuarioGeral.text = "Telefone: --"
            txtSite_UsuarioGeral.text = "Site: --"
        }

        imgbtnAlterarInfo_UsuarioGeral.setOnClickListener {
            onButtonPressed(usuario!!, 1, null)
        }

        btnAddEnd_UsuarioGeral.setOnClickListener{
            onButtonPressed(usuario!!, 2, 0)
        }

    }

}
