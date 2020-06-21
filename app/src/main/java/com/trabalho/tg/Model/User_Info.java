package com.trabalho.tg.Model;

import java.util.ArrayList;

public class User_Info {

    private Integer info_id;
    private String info_nomefantasia;
    private String info_cnpj;
    private Integer info_telefone;
    private String info_rzsocial;
    private String info_site;
    private ArrayList<Endereco> info_endereco = new ArrayList<Endereco>();


    public User_Info(Integer info_id) {
        this.info_id = info_id;
    }

    public Integer getInfo_id() {
        return info_id;
    }

    public void setInfo_id(Integer info_id) {
        this.info_id = info_id;
    }

    public String getInfo_nomefantasia() {
        return info_nomefantasia;
    }

    public void setInfo_nomefantasia(String info_nomefantasia) {
        this.info_nomefantasia = info_nomefantasia;
    }

    public String getInfo_cnpj() {
        return info_cnpj;
    }

    public void setInfo_cnpj(String info_cnpj) {
        this.info_cnpj = info_cnpj;
    }

    public Integer getInfo_telefone() {
        return info_telefone;
    }

    public void setInfo_telefone(Integer info_telefone) {
        this.info_telefone = info_telefone;
    }

    public String getInfo_rzsocial() {
        return info_rzsocial;
    }

    public void setInfo_rzsocial(String info_rzsocial) {
        this.info_rzsocial = info_rzsocial;
    }

    public String getInfo_site() {
        return info_site;
    }

    public void setInfo_site(String info_site) {
        this.info_site = info_site;
    }

    public ArrayList<Endereco> getInfo_endereco() {
        return info_endereco;
    }

    public void setInfo_endereco(ArrayList<Endereco> info_endereco) {
        this.info_endereco = info_endereco;
    }

    public void addEndereco(Endereco end){
            info_endereco.add(end);
    }



}
