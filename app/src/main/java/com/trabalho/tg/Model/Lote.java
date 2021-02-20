package com.trabalho.tg.Model;

import android.content.Context;
import com.trabalho.tg.Controller.C_Entrada;
import com.trabalho.tg.Controller.C_Entrada_Fechado;
import com.trabalho.tg.Controller.C_Lote;
import com.trabalho.tg.Controller.C_Lote_Fechado;
import com.trabalho.tg.Helper.DBHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lote implements Serializable {
    private Integer lot_id;
    private String lot_nome;
    private String lot_planta;
    private String lot_imagem;
    private ArrayList<Entrada> lot_ent = new ArrayList<>();

    public Lote(Integer lot_id) {
        this.lot_id = lot_id;
    }

    public Integer getLot_id() {
        return lot_id;
    }

    public String getLot_nome() {
        return lot_nome;
    }

    public void setLot_nome(String lot_nome) {
        this.lot_nome = lot_nome;
    }

    public String getLot_planta() {
        return lot_planta;
    }

    public void setLot_planta(String lot_planta) {
        this.lot_planta = lot_planta;
    }

    public String getLot_imagem() {
        return lot_imagem;
    }

    public void setLot_imagem(String lot_imagem) {
        this.lot_imagem = lot_imagem;
    }

    public ArrayList<Entrada> getLot_ent() {
        return lot_ent;
    }

    public ArrayList<Entrada> getLot_EntAgro() {
        ArrayList<Entrada> agro = new ArrayList<>();
        for(Entrada ent : lot_ent){
            if(ent.getEnt_tipo() == 3){
                agro.add(ent);
            }
        }
        return agro;
    }

    public void setLot_ent(ArrayList<Entrada> lot_ent) {
        this.lot_ent = lot_ent;
    }

    public void addEntrada(Entrada ent){
        lot_ent.add(ent);
    }

    public List<Integer> todosTipos(){
        ArrayList<Integer> tipos = new ArrayList<>();
        for (Entrada e : lot_ent
        ) {
            if(!tipos.contains(e.getEnt_tipo())){
                tipos.add(e.getEnt_tipo());
            }
        }

        return tipos;
    }

    public int totalTipoEntrada(int tipo){
        int total = 0;
        for (Entrada e : lot_ent
             ) {
            if(e.getEnt_tipo() == tipo){
                total ++;
            }
        }
        return total;
    }

    public Double valorTotal(int tipo){
        Double total = 0.00;
        for (Entrada e : lot_ent
        ) {
            if(e.getEnt_tipo() == tipo){
                total += e.getEnt_valor();
            }
        }
        return total;
    }

    public Double maiorDespesa(){
        Double total = 0.00;
        Double atual = 0.00;

        List<Integer> tipos = todosTipos();
        if(tipos.contains(4)){
            tipos.remove(tipos.indexOf(4));
        }
        if(tipos.contains(7)){
            tipos.remove(tipos.indexOf(7));
        }

        for(Integer tipo : tipos){
            atual = valorTotal(tipo);
            if(atual > total){
                total = atual;
            }
        }
        return total;
    }


    public void fecharLote(Context context, Integer aID, Integer uID){


        Lote_Fechado l = new Lote_Fechado(this.lot_id);
        l.setLot_imagem(this.lot_imagem);
        l.setLot_nome(this.lot_nome);
        l.setLot_planta(this.lot_planta);
        l.setLot_ent(this.lot_ent);


        if(new C_Lote_Fechado().insertLote_Fechado(new DBHelper(context),l,aID,uID)){
            if(new C_Entrada_Fechado().insertListEntrada(new DBHelper(context),this.lot_ent,this.lot_id,uID)){
                new C_Entrada().deleteAllEntradasByLote(new DBHelper(context),this.lot_id, this.lot_ent);
                new C_Lote().deleteLote(new DBHelper(context),this.lot_id);
            }
        }
    }
}
