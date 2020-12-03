package com.trabalho.tg.Model;

import android.graphics.Bitmap;
import com.trabalho.tg.R;

import java.io.Serializable;
import java.util.ArrayList;


public class Area implements Serializable {
    private Integer ar_id;
    private String ar_lote_cont;
    private String ar_imagem;
    private Bitmap ar_imagem_cached;
    private String ar_nome;
    private String ar_del;
    private ArrayList<Lote> ar_lote = new ArrayList<>();
    private ArrayList<Lote_Fechado> ar_lote_fechado = new ArrayList<>();

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
        this.ar_lote.add(lote);
    }

    public ArrayList<Lote_Fechado> getAr_lote_Fechado() {
        return ar_lote_fechado;
    }

    public void setAr_lote_Fechado(ArrayList<Lote_Fechado> ar_lote_fechado) {
        this.ar_lote_fechado = ar_lote_fechado;
    }

    public void addLote_Fechado(Lote_Fechado ar_lote_fechado){
        this.ar_lote_fechado.add(ar_lote_fechado);
    }

    public Integer getLoteContID(){
        Integer ret = 0;
        if (ar_lote_cont.compareTo("Data + Numero") == 0){
            ret = 1;
        }
        return ret;
    }

    public Bitmap getAr_imagem_cached() {
        return ar_imagem_cached;
    }

    public void setAr_imagem_cached(Bitmap ar_imagem_cached) {
        this.ar_imagem_cached = ar_imagem_cached;
    }
}
