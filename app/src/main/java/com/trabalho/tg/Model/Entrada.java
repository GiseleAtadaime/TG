package com.trabalho.tg.Model;

import java.util.Date;

public class Entrada {

    private Integer ent_numero;
    private Date ent_data;
    private Integer ent_tipo;
    private String ent_desc;
    private Integer ent_valor_1;
    private Double ent_valor_2;
    private Double ent_custo;
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

    public void setEnt_data(Date ent_data) {
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

    public Integer getEnt_valor_1() {
        return ent_valor_1;
    }

    public void setEnt_valor_1(Integer ent_valor_1) {
        this.ent_valor_1 = ent_valor_1;
    }

    public Double getEnt_valor_2() {
        return ent_valor_2;
    }

    public void setEnt_valor_2(Double ent_valor_2) {
        this.ent_valor_2 = ent_valor_2;
    }

    public Double getEnt_custo() {
        return ent_custo;
    }

    public void setEnt_custo(Double ent_custo) {
        this.ent_custo = ent_custo;
    }

    public Reg_Agrotoxico getEnt_reg() {
        return ent_reg;
    }

    public void setEnt_reg(Reg_Agrotoxico ent_reg) {
        this.ent_reg = ent_reg;
    }

}
