package com.trabalho.tg.Model;

import android.content.Context;
import com.trabalho.tg.Controller.*;
import com.trabalho.tg.Helper.DBHelper;

import java.io.Serializable;
import java.util.ArrayList;

public class Usuario implements Serializable {
    private Integer usr_id;
    private String usr_nome;
    private String usr_email;
    private String usr_senha;
    private String usr_fotoperfil;
    private User_Info usr_user_info;
    private ArrayList<Area> usr_area = new ArrayList<>();

    public Usuario(Integer usr_id) {
        this.usr_id = usr_id;
    }

    public Integer getUsr_id() {
        return usr_id;
    }

    public String getUsr_nome() {
        return usr_nome;
    }

    public void setUsr_nome(String usr_nome) {
        this.usr_nome = usr_nome;
    }

    public String getUsr_email() {
        return usr_email;
    }

    public void setUsr_email(String usr_email) {
        this.usr_email = usr_email;
    }

    public String getUsr_senha() {
        return usr_senha;
    }

    public void setUsr_senha(String usr_senha) {
        this.usr_senha = usr_senha;
    }

    public String getUsr_fotoperfil() {
        return usr_fotoperfil;
    }

    public void setUsr_fotoperfil(String usr_fotoperfil) {
        this.usr_fotoperfil = usr_fotoperfil;
    }

    public User_Info getUsr_user_info() {
        return usr_user_info;
    }

    public void setUsr_user_info(User_Info usr_user_info) {
        this.usr_user_info = usr_user_info;
    }

    public ArrayList<Area> getUsr_area() {
        return usr_area;
    }

    public void setUsr_area(ArrayList<Area> usr_area) {
        this.usr_area = usr_area;
    }

    public void addArea(Area a){
        usr_area.add(a);
    }

    public void loadUsuario(Context context){
        DBHelper helper = new DBHelper(context);
        Usuario u = new C_Usuario().selectUsuario(helper);

        this.usr_nome = (u.getUsr_nome());
        this.usr_fotoperfil = (u.getUsr_fotoperfil());
        this.usr_email = (u.getUsr_email());
        this.usr_senha = (u.getUsr_senha());

        this.usr_user_info = new C_User_Info().selectUser_Info(helper);

        if(this.usr_user_info != null){
            this.usr_user_info.setInfo_endereco(new C_Endereco().selectEndereco(helper, this.usr_user_info.getInfo_id()));
        }

        this.usr_area = new C_Area().selectArea(helper,true);

        if(this.usr_area != null){
            for (Area area : this.usr_area){
                area.setAr_lote( new C_Lote().selectLote(helper, area.getAr_id()));
                for(Lote lote : area.getAr_lote()){
                    lote.setLot_ent(new C_Entrada().selectEntrada(helper, lote.getLot_id()));
                }
            }
        }


    }
}
