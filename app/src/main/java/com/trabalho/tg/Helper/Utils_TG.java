package com.trabalho.tg.Helper;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import com.trabalho.tg.R;

import java.io.File;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;


public class Utils_TG {

//    public final String APP_TAG = "MyCustomApp";
//    public final static int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1034;
//    public String photoFileName = "photo.jpg";
//    File photoFile;
//
//    public void onLaunchCamera(View view, Context context) {
//        // create Intent to take a picture and return control to the calling application
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        // Create a File reference for future access
//        photoFile = getPhotoFileUri(photoFileName);
//
//        // wrap File object into a content provider
//        // required for API >= 24
//        // See https://guides.codepath.com/android/Sharing-Content-with-Intents#sharing-files-with-api-24-or-higher
//        Uri fileProvider = FileProvider.getUriForFile(context, "com.codepath.fileprovider", photoFile);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider);
//
//        // If you call startActivityForResult() using an intent that no app can handle, your app will crash.
//        // So as long as the result is not null, it's safe to use the intent.
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            // Start the image capture intent to take photo
//            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
//        }
//    }
//
//    // Returns the File for a photo stored on disk given the fileName
//    public File getPhotoFileUri(String fileName) {
//        // Get safe storage directory for photos
//        // Use `getExternalFilesDir` on Context to access package-specific directories.
//        // This way, we don't need to request external read/write runtime permissions.
//        File mediaStorageDir = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), APP_TAG);
//
//        // Create the storage directory if it does not exist
//        if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()){
//            Log.d(APP_TAG, "failed to create directory");
//        }
//
//        // Return the file target for the photo based on filename
//        File file = new File(mediaStorageDir.getPath() + File.separator + fileName);
//
//        return file;
//    }
//
//
//    public void onActivityResult(int requestCode, int resultCode, Intent data, Context context, Integer rID) {
//        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
//            if (resultCode == RESULT_OK) {
//                // by this point we have the camera photo on disk
//                Bitmap takenImage = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
//                // RESIZE BITMAP, see section below
//                // Load the taken image into a preview
//                ImageView ivPreview = (ImageView) findViewById(rID);
//                ivPreview.setImageBitmap(takenImage);
//            } else { // Result was a failure
//                Toast.makeText(context, "Picture wasn't taken!", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }

    public int getColorFromNameForGraph(String nome){
        int ret = 0;


        switch(nome){
            case Contrato.Tipo_Entrada.PLANTIO:
                ret = R.color.plantio;
                break;
            case Contrato.Tipo_Entrada.COLHEITA:
                ret = R.color.colheita;
                break;
            case Contrato.Tipo_Entrada.ADUBACAO:
                ret = R.color.adubacao;
                break;
            case Contrato.Tipo_Entrada.AGROTOXICO:
                ret = R.color.agrotoxico;
                break;
            case Contrato.Tipo_Entrada.MAO_DE_OBRA:
                ret = R.color.mao_de_obra;
                break;
            case Contrato.Tipo_Entrada.IRRIGACAO:
                ret = R.color.irrigacao;
                break;
            case Contrato.Tipo_Entrada.PREJUIZO:
                ret = R.color.prejuizo;
                break;

        }

        return ret;
    }

    public String getColorName(String nome, Context context){
        String ret = "";
        switch(nome){
            case Contrato.Tipo_Entrada.PLANTIO:
                ret = "#" + Integer.
                    toHexString(ContextCompat.getColor(context, R.color.plantio) & 0x00ffffff);
                break;
            case Contrato.Tipo_Entrada.COLHEITA:
                ret = "#" + Integer.
                        toHexString(ContextCompat.getColor(context, R.color.colheita) & 0x00ffffff);
                break;
            case Contrato.Tipo_Entrada.ADUBACAO:
                ret = "#" + Integer.
                        toHexString(ContextCompat.getColor(context, R.color.adubacao) & 0x00ffffff);
                break;
            case Contrato.Tipo_Entrada.AGROTOXICO:
                ret = "#" + Integer.
                        toHexString(ContextCompat.getColor(context, R.color.agrotoxico) & 0x00ffffff);
                break;
            case Contrato.Tipo_Entrada.MAO_DE_OBRA:
                ret = "#" + Integer.
                        toHexString(ContextCompat.getColor(context, R.color.mao_de_obra) & 0x00ffffff);
                break;
            case Contrato.Tipo_Entrada.IRRIGACAO:
                ret = "#" + Integer.
                        toHexString(ContextCompat.getColor(context, R.color.irrigacao) & 0x00ffffff);
                break;
            case Contrato.Tipo_Entrada.PREJUIZO:
                ret = "#" + Integer.
                        toHexString(ContextCompat.getColor(context, R.color.prejuizo) & 0x00ffffff);
                break;

        }
        return ret;
    }


    public Boolean checkDouble(String num){
        try{
            Double ret = Double.parseDouble(num);
            return true;
        }
        catch(NumberFormatException e){
            return false;
        }
    }

    public String formatDate(Date data, Boolean type){
        SimpleDateFormat dateFormat;
        if(type){
            dateFormat = new SimpleDateFormat(
                    "dd/MM/yyyy", Locale.getDefault());
        }
        else{
            dateFormat = new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        }

        return dateFormat.format(data);
    }

    public Date getStringToDate(String sdata){

        Integer dia = Integer.parseInt(sdata.subSequence(8,10).toString());
        Integer mes = Integer.parseInt(sdata.subSequence(5,7).toString());
        Integer ano = Integer.parseInt(sdata.subSequence(0,4).toString());

        mes = new Utils_TG().getMonth(mes);

        Calendar c = Calendar.getInstance();
        c.clear();
        c.set(ano, mes, dia, 0,0,0);

        return c.getTime();
    }

    public String reverseString(String string){
        return new StringBuilder(string).reverse().toString();
    }

    public Integer getMonth(Integer mes){
        switch (mes){
            case 1:
                mes = Calendar.JANUARY;
                break;
            case 2:
                mes =Calendar.FEBRUARY;
                break;
            case 3:
                mes =Calendar.MARCH;
                break;
            case 4:
                mes =Calendar.APRIL;
                break;
            case 5:
                mes =Calendar.MAY;
                break;
            case 6:
                mes =Calendar.JUNE;
                break;
            case 7:
                mes =Calendar.JULY;
                break;
            case 8:
                mes =Calendar.AUGUST;
                break;
            case 9:
                mes =Calendar.SEPTEMBER;
                break;
            case 10:
                mes =Calendar.OCTOBER;
                break;
            case 11:
                mes =Calendar.NOVEMBER;
                break;
            case 12:
                mes =Calendar.DECEMBER;
                break;
        }

        return mes;
    }

    public void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public Integer stringToInteger(String string){
        Integer ret;
        try{
            ret = Integer.parseInt(string);
        }
        catch(Exception e){
            if (string.compareTo("") == 0){
                ret = null;
            }
            else{
                ret = 0;
            }
        }

        return ret;
    }

    public Double stringToDouble(String string){
        Double ret = null;
        try{
            ret = Double.parseDouble(string);
        }
        catch(Exception e){
            if (string.compareTo("") == 0){
               ret = null;
            }
            else{
                ret = 0.0;
            }

        }

        return ret;
    }

    public String removerMascaraMonetaria(String str){
        str = str.replaceAll("[R$]", "")
                .replaceAll("[.]", "").replaceAll("\\s+","");

        str = str.replaceAll("[,]", ".");


        return str;
    }

    public String doubleToString(Double d){
        String str = d.toString();

        int i = str.indexOf(".");

        if (str.substring(i+2).compareTo("") == 0){
            str = str.concat("0");
        }
        return str;
    }


    public String formatMonetario(String string){
        Locale ptBr = new Locale("pt","BR");
        NumberFormat nf = NumberFormat.getCurrencyInstance(ptBr);

        try {
            string = string.replaceAll("[^\\d]", "");
            string = nf.format(Double.parseDouble(string) / 100);

        } catch (NumberFormatException e) {
            string = "";
        }
        return string;
    }

    public class MascaraMonetaria implements TextWatcher {
        Locale ptBr = new Locale("pt","BR");
        final EditText campo;

        public MascaraMonetaria(EditText campo) {
            super();
            this.campo = campo;
        }

        private boolean isUpdating = false;
        // Pega a formatacao do sistema, se for brasil R$ se EUA US$
        private NumberFormat nf = NumberFormat.getCurrencyInstance(ptBr);

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int after) {
            // Evita que o método seja executado varias vezes.
            // Se tirar ele entre em loop
            if (isUpdating) {
                isUpdating = false;
                return;
            }

            isUpdating = true;
            String str = s.toString();
            str = str.replaceAll("[^\\d]", "");

            try {
                // Transformamos o número que está escrito no EditText em
                // monetário.
                str = nf.format(Double.parseDouble(str) / 100);
                campo.setText(str);
                campo.setSelection(campo.getText().length());
            } catch (NumberFormatException e) {
                s = "";
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            // Não utilizado
        }

        @Override
        public void afterTextChanged(Editable s) {
            // Não utilizado
        }
    }

    public String removerMascaraNumeroVirgula(String str){

        str = str.replaceAll("[,]", ".");

        return str;
    }

    public String formatNumeroVirgula(Double number){
        Locale ptBr = new Locale("pt","BR");
        NumberFormat nf = NumberFormat.getNumberInstance(ptBr);
        String string;
        try {

            string = doubleToString(number);
            string = string.replaceAll("[.]", ",");


        } catch (NumberFormatException e) {
            string = "";
        }
        return string;
    }

}
