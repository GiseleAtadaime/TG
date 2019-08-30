package com.trabalho.tg.Model;

import java.util.ArrayList;

public class Lote {
    private Integer lot_id;
    private String lot_nome;
    private String lot_imagem;
    private ArrayList<Entrada> lot_ent = new ArrayList<>();

    public Lote(Integer lot_id) {
        this.lot_id = lot_id;
    }

    public String getLot_nome() {
        return lot_nome;
    }

    public void setLot_nome(String lot_nome) {
        this.lot_nome = lot_nome;
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

    public void setLot_ent(ArrayList<Entrada> lot_ent) {
        this.lot_ent = lot_ent;
    }

    public void addEntrada(Entrada ent){
        lot_ent.add(ent);
    }
}
