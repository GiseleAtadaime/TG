package com.trabalho.tg.Model;

import com.trabalho.tg.Helper.Utils_TG;
import com.trabalho.tg.R;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Entrada implements Serializable {

    private Integer ent_numero;
    private Date ent_data = new Date();
    private Integer ent_detalhe_num;
    private Integer ent_tipo;
    private String ent_desc;
    private Double ent_tempo;
    private String ent_tpun;
    private Double ent_qtde;
    private String ent_qtun;
    private Integer ent_mudas_bandeja;
    private Double ent_valor;
    private String ent_reg_lote;
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

        mes = new Utils_TG().getMonth(mes);

        Calendar c = Calendar.getInstance();
        c.clear();
        c.set(ano, mes, dia, 0,0,0);
        this.ent_data = c.getTime();
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

    public String getEnt_reg_lote() {
        return ent_reg_lote;
    }

    public void setEnt_reg_lote(String ent_reg_lote) {
        this.ent_reg_lote = ent_reg_lote;
    }

    public Integer getEnt_detalhe_num() {
        return ent_detalhe_num;
    }

    public void setEnt_detalhe_num(Integer ent_detalhe_num) {
        this.ent_detalhe_num = ent_detalhe_num;
    }

    public Integer getEntradaColor(){

        Integer tipo = null;

        switch(ent_tipo){
            case 1:
                tipo = R.color.plantio;
                break;
            case 2:
                tipo = R.color.adubacao;
                break;
            case 3:
                tipo = R.color.agrotoxico;
                break;
            case 4:
                tipo = R.color.colheita;
                break;
            case 5:
                tipo = R.color.mao_de_obra;
                break;
            case 6:
                tipo = R.color.prejuizo;
                break;
            case 7:
                tipo = R.color.irrigacao;
                break;
        }

        return tipo;
    }

}
