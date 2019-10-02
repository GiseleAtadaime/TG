package com.trabalho.tg.Model;

import com.trabalho.tg.R;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Entrada implements Serializable {

    private Integer ent_numero;
    private Date ent_data;
    private Integer ent_tipo;
    private String ent_desc;
    private Double ent_tempo;
    private String ent_tpun;
    private Double ent_qtde;
    private String ent_qtun;
    private Integer ent_mudas_bandeja;
    private Double ent_valor;
    private Reg_Agrotoxico ent_reg;

    public Entrada(Integer ent_numero) {
        this.ent_numero = ent_numero;
    }

    public Integer getEnt_numero() {
        return ent_numero;
    }

    public Date getEnt_data() {
        return ent_data;
    }

    public void setEnt_data(Integer dia, Integer mes, Integer ano) {

        Calendar c = Calendar.getInstance();
        Date date = new Date();
        c.clear();
        c.set(ano, mes, dia);
        date = c.getTime();

        this.ent_data = date;
    }

    public void setEnt_data(Date ent_data){
        this.ent_data = ent_data;
    }

    public Integer getEnt_tipo() {
        return ent_tipo;
    }

    public void setEnt_tipo(Integer ent_tipo) {
        this.ent_tipo = ent_tipo;
    }

    public String getEnt_desc() {
        return ent_desc;
    }

    public void setEnt_desc(String ent_desc) {
        this.ent_desc = ent_desc;
    }

    public Double getEnt_tempo() {
        return ent_tempo;
    }

    public void setEnt_tempo(Double ent_tempo) {
        this.ent_tempo = ent_tempo;
    }

    public String getEnt_tpun() {
        return ent_tpun;
    }

    public void setEnt_tpun(String ent_tpun) {
        this.ent_tpun = ent_tpun;
    }

    public Double getEnt_qtde() {
        return ent_qtde;
    }

    public void setEnt_qtde(Double ent_qtde) {
        this.ent_qtde = ent_qtde;
    }

    public String getEnt_qtun() {
        return ent_qtun;
    }

    public void setEnt_qtun(String ent_qtun) {
        this.ent_qtun = ent_qtun;
    }

    public Integer getEnt_mudas_bandeja() {
        return ent_mudas_bandeja;
    }

    public void setEnt_mudas_bandeja(Integer ent_mudas_bandeja) {
        this.ent_mudas_bandeja = ent_mudas_bandeja;
    }

    public Double getEnt_valor() {
        return ent_valor;
    }

    public void setEnt_valor(Double ent_valor) {
        this.ent_valor = ent_valor;
    }

    public Reg_Agrotoxico getEnt_reg() {
        return ent_reg;
    }

    public void setEnt_reg(Reg_Agrotoxico ent_reg) {
        this.ent_reg = ent_reg;
    }

    public Integer getEntradaColor(){

        return R.color.agrotoxico;
    }

}
