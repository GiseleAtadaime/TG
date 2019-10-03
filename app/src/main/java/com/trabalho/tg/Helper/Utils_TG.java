package com.trabalho.tg.Helper;

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
                    "dd-MM-yyyy HH:mm:ss", Locale.getDefault());
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




}
