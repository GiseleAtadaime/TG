package com.trabalho.tg.Model;

import java.io.Serializable;

public class Endereco implements Serializable {

    private Integer end_id;
    private String end_logradouro;
    private Double end_catx;
    private Double end_carty;
    private Integer end_cep;
    private String end_bairro;
    private String end_Cidade;
    private String end_uf;

    public Endereco(Integer end_id) {
        this.end_id = end_id;
    }

    public Integer getEnd_id() {
        return end_id;
    }

    public String getEnd_logradouro() {
        return end_logradouro;
    }

    public void setEnd_logradouro(String end_logradouro) {
        this.end_logradouro = end_logradouro;
    }

    public Double getEnd_catx() {
        return end_catx;
    }

    public void setEnd_catx(Double end_catx) {
        this.end_catx = end_catx;
    }

    public Double getEnd_carty() {
        return end_carty;
    }

    public void setEnd_carty(Double end_carty) {
        this.end_carty = end_carty;
    }

    public Integer getEnd_cep() {
        return end_cep;
    }

    public void setEnd_cep(Integer end_cep) {
        this.end_cep = end_cep;
    }

    public String getEnd_bairro() {
        return end_bairro;
    }

    public void setEnd_bairro(String end_bairro) {
        this.end_bairro = end_bairro;
    }

    public String getEnd_Cidade() {
        return end_Cidade;
    }

    public void setEnd_Cidade(String end_Cidade) {
        this.end_Cidade = end_Cidade;
    }

    public String getEnd_uf() {
        return end_uf;
    }

    public void setEnd_uf(String end_uf) {
        this.end_uf = end_uf;
    }
}
