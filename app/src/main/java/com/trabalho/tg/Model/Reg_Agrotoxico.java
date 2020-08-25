package com.trabalho.tg.Model;

import java.io.Serializable;

public class Reg_Agrotoxico implements Serializable {

    private Integer reg_numero;
    private String reg_nomecom;
    private String reg_empresa;
    private String reg_ing_ativo;

    public Reg_Agrotoxico(Integer reg_numero) {
        this.reg_numero = reg_numero;
    }

    public void setReg_numero(Integer reg_numero) {
        this.reg_numero = reg_numero;
    }

    public Integer getReg_numero() {
        return reg_numero;
    }

    public String getReg_nomecom() {
        return reg_nomecom;
    }

    public void setReg_nomecom(String reg_nomecom) {
        this.reg_nomecom = reg_nomecom;
    }

    public String getReg_empresa() {
        return reg_empresa;
    }

    public void setReg_empresa(String reg_empresa) {
        this.reg_empresa = reg_empresa;
    }

    public String getReg_ing_ativo() {
        return reg_ing_ativo;
    }

    public void setReg_ing_ativo(String reg_ing_ativo) {
        this.reg_ing_ativo = reg_ing_ativo;
    }
}
