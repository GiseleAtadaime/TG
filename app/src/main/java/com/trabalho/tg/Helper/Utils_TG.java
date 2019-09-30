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

    public Date formatDate(Integer dia, Integer mes, Integer ano){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd-mm-yyyy HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        c.clear();
        c.set(dia, mes, ano);
        date = c.getTime();

        return date;
    }



}
