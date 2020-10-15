package com.trabalho.tg.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

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
}
