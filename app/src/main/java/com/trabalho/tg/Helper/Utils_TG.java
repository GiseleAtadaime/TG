package com.trabalho.tg.Helper;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Utils_TG {

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
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
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

}
