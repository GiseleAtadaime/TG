package com.trabalho.tg.Model;

import com.trabalho.tg.R;

import java.io.Serializable;
import java.util.ArrayList;


public class Area implements Serializable {
    private Integer ar_id;
    private String ar_lote_cont;
    private String ar_imagem;
    private String ar_nome;
    private String ar_del;
    private ArrayList<Lote> ar_lote = new ArrayList<>();

    public Area(Integer ar_id) {
        this.ar_id = ar_id;
    }

    public Integer getAr_id() {
        return ar_id;
    }

    public String getAr_lote_cont() {
        return ar_lote_cont;
    }

    public void setAr_lote_cont(Integer id_lote) {
        if (id_lote == 0){
            this.ar_lote_cont = "Data + Letra";
        }
        else{
            this.ar_lote_cont = "Data + Numero";

        }
    }

    public void setAr_lote_cont(String loteCont){
        this.ar_lote_cont = loteCont;
    }

    public String getAr_imagem() {
        return ar_imagem;
    }

    public void setAr_imagem(String ar_imagem) {
        this.ar_imagem = ar_imagem;
    }

    public String getAr_nome() {
        return ar_nome;
    }

    public void setAr_nome(String ar_nome) {
        this.ar_nome = ar_nome;
    }

    public String getAr_del() {
        return ar_del;
    }

    public void setAr_del(String ar_del) {
        this.ar_del = ar_del;
    }

    public ArrayList<Lote> getAr_lote() {
        return ar_lote;
    }

    public void setAr_lote(ArrayList<Lote> ar_lote) {
        this.ar_lote = ar_lote;
    }

    public void addLote(Lote lote){
        ar_lote.add(lote);
    }

    public Integer getLoteContID(){
        Integer ret = 0;
        if (ar_lote_cont.compareTo("Data + Numero") == 0){
            ret = 1;
        }
        return ret;
    }


}
