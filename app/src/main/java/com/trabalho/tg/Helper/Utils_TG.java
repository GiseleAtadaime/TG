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




}
