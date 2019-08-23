package com.trabalho.tg.Helper;

import android.provider.BaseColumns;
import android.widget.ArrayAdapter;

import java.util.WeakHashMap;

public final class Contrato {

    private static class Usuario implements BaseColumns {
        public static final String TABLENAME = "USUARIO";
        public static final String COLUMN_ID = "usr_id";
        public static final String COLUMN_APELIDO = "usr_apelido";
        public static final String COLUMN_NOME = "usr_nome";
        public static final String COLUMN_EMAIL = "usr_email";
        public static final String COLUMN_SENHA = "usr_senha";
        public static final String COLUMN_FOTOPERFIL = "usr_fotoperfil";

    }

    private static class Usuario_Info implements BaseColumns{
        public static final String TABLENAME = "USUARIO_INFO";
        public static final String COLUMN_INFO_ID = "info_id";
        public static final String COLUMN_USR_ID = "info_usr_id";
        public static final String COLUMN_NOMEFANTASIA = "info_nomefantasia";
        public static final String COLUMN_CNPJ = "info_cnpj";
        public static final String COLUMN_SITE = "info_site";
        public static final String COLUMN_TELEFONE = "info_telefone";
        public static final String COLUMN_RZSOCIAL = "info_rzsocial";
        public static final String COLUMN_END_ID = "info_end_id";


    }

    private static class Endereco implements BaseColumns{
        public static final String TABLENAME = "ENDERECO";
        public static final String COLUMN_ID = "end_id";
        public static final String COLUMN_LOGRADOURO = "end_logradouro";
        public static final String COLUMN_CARTX = "end_cartx";
        public static final String COLUMN_CARTY = "end_carty";
        public static final String COLUMN_CEP = "end_cep";
        public static final String COLUMN_BAIRRO = "end_bairro";
        public static final String COLUMN_CIDADE = "end_cidade";
        public static final String COLUMN_UF = "end_uf";
        public static final String COLUMN_USR_ID = "end_usr_id";

    }

    private static class Reg_Agrotoxico implements BaseColumns{
        public static final String TABLENAME = "REG_AGROTOXICO";
        public static final String COLUMN_NUMERO = "reg_num";
        public static final String COLUMN_NOMECOM = "reg_nomecom";
        public static final String COLUMN_EMPRESA = "reg_empresa";
        public static final String COLUMN_ING_ATIVO = "reg_ing_ativo";

    }

    private static class Area implements BaseColumns{
        public static final String TABLENAME = "AREA";
        public static final String COLUMN_ID = "ar_id";
        public static final String COLUMN_LOTE_CONT = "ar_lote_cont";
        public static final String COLUMN_IMAGEM = "ar_imagem";
        public static final String COLUMN_NOME = "ar_nome";
        public static final String COLUMN_DEL = "ar_del";
        public static final String COLUMN_USR_ID = "ar_usr_id";



        public static final String STATUS_DELETADO = "D";
        public static final String STATUS_ATIVO = "A";

    }

    private static class Lote implements BaseColumns{
        public static final String TABLENAME = "LOTE";
        public static final String COLUMN_ID = "lot_id";
        public static final String COLUMN_NOME = "lot_nome";
        public static final String COLUMN_IMAGEM = "lot_imagem";
        public static final String COLUMN_AREA_ID = "lot_area_id";
        public static final String COLUMN_USR_ID = "lot_usr_id";

    }
    private static class Entrada implements BaseColumns{
        public static final String TABLENAME = "ENTRADA";
        public static final String COLUMN_NUMERO = "ent_num";
        public static final String COLUMN_LOTE_ID = "ent_lot_id";
        public static final String COLUMN_DATA = "ent_data";
        public static final String COLUMN_TIPO = "ent_tipo";
        public static final String COLUMN_DESC = "ent_desc";
        public static final String COLUMN_VALOR_1 = "ent_valor_1";
        public static final String COLUMN_VALOR_2 = "ent_valor_2";
        public static final String COLUMN_CUSTO = "ent_custo";
        public static final String COLUMN_USR_ID = "ent_usr_id";
        public static final String COLUMN_REG_NUM =  "ent_reg_num";


    }

    private static class Fec_lote implements BaseColumns{
        public static final String TABLENAME = "FEC_LOTE";
        public static final String COLUMN_ID = "fec_id";
        public static final String COLUMN_NOME = "fec_nome";
        public static final String COLUMN_IMAGEM = "fec_imagem";
        public static final String COLUMN_PDF_LINK = "fec_pdf_link";
        public static final String COLUMN_LOT_USERPDF_LINK = "fec_lot_userpdf_link";
        public static final String COLUMN_QRCODE_LINK = "fec_qrcode_link";
        public static final String COLUMN_AREA_ID = "fec_area_id";
        public static final String COLUMN_USR_ID = "fec_usr_id";
    }

    private static class Fec_entrada implements BaseColumns{
        public static final String TABLENAME = "FEC_ENTRADA";
        public static final String COLUMN_ID = "fent_id";
        public static final String COLUMN_LOTE_ID = "fent_lot_id";
        public static final String COLUMN_DATA = "fent_data";
        public static final String COLUMN_TIPO = "fent_tipo";
        public static final String COLUMN_DESC = "fent_desc";
        public static final String COLUMN_VALOR_1 = "fent_valor_1";
        public static final String COLUMN_VALOR_2 = "fent_valor_2";
        public static final String COLUMN_CUSTO = "fent_custo";
        public static final String COLUMN_REG_NUM = "fent_reg_num";
        public static final String COLUMN_USR_ID = "fent_usr_id";

    }

    //Table creations

    public static final String CREATE_TABLE_USUARIO  = "CREATE TABLE " + Usuario.TABLENAME +
            " ( " + Usuario.COLUMN_ID + " INTEGER, " +
                    Usuario.COLUMN_APELIDO + " VARCHAR(20), " +
                    Usuario.COLUMN_NOME + " VARCHAR(60), " +
                    Usuario.COLUMN_SENHA + " VARCHAR(20), " +
                    Usuario.COLUMN_EMAIL + " VARCHAR(60), " +
                    Usuario.COLUMN_FOTOPERFIL + " VARCHAR(60), " +
            " CONSTRAINT pk_usuario PRIMARY KEY ( " + Usuario.COLUMN_ID + " )); ";

    public static final String CREATE_TABLE_USUARIO_INFO = "CREATE TABLE " + Usuario_Info.TABLENAME +
            " ( " + Usuario_Info.COLUMN_INFO_ID + " INTEGER, " +
                    Usuario_Info.COLUMN_USR_ID + " INTEGER, " +
                    Usuario_Info.COLUMN_NOMEFANTASIA + " VARCHAR(60), " +
                    Usuario_Info.COLUMN_CNPJ + " VARCHAR(14), " +
                    Usuario_Info.COLUMN_SITE + " VARCHAR(60), " +
                    Usuario_Info.COLUMN_TELEFONE + " INTEGER, " +
                    Usuario_Info.COLUMN_RZSOCIAL + " VARCHAR(100), " +
                    Usuario_Info.COLUMN_END_ID + " INTEGER, " +
            " CONSTRAINT pk_info PRIMARY KEY ( " + Usuario_Info.COLUMN_INFO_ID + " ), " +
            " CONSTRAINT fk_info_usuario FOREIGN KEY ( " + Usuario_Info.COLUMN_USR_ID + "));";

    public static final String CREATE_TABLE_ENDERECO = "CREATE TABLE " + Endereco.TABLENAME +
            " ( " + Endereco.COLUMN_ID + " INTEGER, " +
                    Endereco.COLUMN_LOGRADOURO + " VARCHAR(60), " +
                    Endereco.COLUMN_CARTX + " NUMERIC(3,2), " +
                    Endereco.COLUMN_CARTY + " NUMERIC(3,2), " +
                    Endereco.COLUMN_CEP + " INTEGER, " +
                    Endereco.COLUMN_BAIRRO + " VARCHAR(60), " +
                    Endereco.COLUMN_CIDADE + " VARCHAR(60), " +
                    Endereco.COLUMN_UF + " VARCHAR(2), " +
                    Endereco.COLUMN_USR_ID + " INTEGER, " +
            " CONSTRAINT pk_endereco PRIMARY KEY ( " + Endereco.COLUMN_ID + " ), " +
            " CONSTRAINT fk_end_usuario FOREIGN KEY ( " + Usuario_Info.COLUMN_USR_ID + ")); ";

    public static final String CREATE_TABLE_REG_AGROTOXICO = "CREATE TABLE " + Reg_Agrotoxico.TABLENAME +
            " ( " + Reg_Agrotoxico.COLUMN_NUMERO + " INTEGER, " +
                    Reg_Agrotoxico.COLUMN_NOMECOM + " VARCHAR(60), " +
                    Reg_Agrotoxico.COLUMN_EMPRESA + " VARCHAR(60), " +
                    Reg_Agrotoxico.COLUMN_ING_ATIVO + " VARCHAR(60), " +
            " CONSTRAINT pk_agrotox PRIMARY KEY ( " + Reg_Agrotoxico.COLUMN_NUMERO + " ))";

    public static final String CREATE_TABLE_AREA = "CREATE TABLE " + Area.TABLENAME +
            " ( " + Area.COLUMN_ID + " INTEGER, " +
                    Area.COLUMN_LOTE_CONT + " VARCHAR(20), " +
                    Area.COLUMN_IMAGEM + " VARCHAR(60), " +
                    Area.COLUMN_NOME + " VARCHAR(60), " +
                    Area.COLUMN_DEL + " INTEGER, " +
                    Area.COLUMN_USR_ID + " INTEGER, " +
            " CONSTRAINT pk_area PRIMARY KEY ( " + Area.COLUMN_ID + " ), " +
            " CONSTRAINT fk_ar_usuario FOREIGN KEY ( " + Usuario_Info.COLUMN_USR_ID + " )); ";

    public static final String CREATE_TABLE_LOTE = "CREATE TABLE " + Lote.TABLENAME +
            " ( " + Lote.COLUMN_ID + " INTEGER, " +
                    Lote.COLUMN_NOME + " VARCHAR(60), " +
                    Lote.COLUMN_IMAGEM + " VARCHAR(60), " +
                    Lote.COLUMN_AREA_ID + " INTEGER, " +
                    Lote.COLUMN_USR_ID + " INTEGER, " +
            " CONSTRAINT pk_lote PRIMARY KEY ( " + Lote.COLUMN_ID + " ), " +
            " CONSTRAINT fk_lot_area FOREIGN KEY ( " + Area.COLUMN_ID + " ), " +
            " CONSTRAINT fk_lot_usuario FOREIGN KEY ( " + Usuario_Info.COLUMN_USR_ID + " )); ";

    public static final String CREATE_TABLE_ENTRADA = "CREATE TABLE " + Entrada.TABLENAME +
            " ( " + Entrada.COLUMN_NUMERO + " INTEGER, " +
                    Entrada.COLUMN_LOTE_ID + " INTEGER, " +
                    Entrada.COLUMN_DATA + " DATETIME, " +
                    Entrada.COLUMN_TIPO + " VARCHAR(10), " +
                    Entrada.COLUMN_DESC + " VARCHAR(20), " +
                    Entrada.COLUMN_VALOR_1 + " INTEGER, " +
                    Entrada.COLUMN_VALOR_2 + " NUMERIC(10,2), " +
                    Entrada.COLUMN_CUSTO + " NUMERIC(10,2), " +
                    Entrada.COLUMN_USR_ID + " INTEGER, " +
                    Entrada.COLUMN_REG_NUM + " INTEGER, " +
            " CONSTRAINT pk_ent PRIMARY KEY ( " + Entrada.COLUMN_NUMERO + " ), " +
            " CONSTRAINT fk_ent_lote FOREIGN KEY ( " + Lote.COLUMN_ID + " ), " +
            " CONSTRAINT fk_ent_usuario FOREIGN KEY ( " + Usuario_Info.COLUMN_USR_ID + " )); ";

    public static final String CREATE_TABLE_LOTE_FECHADO = "CREATE TABLE " + Fec_lote.TABLENAME +
            " ( " + Fec_lote.COLUMN_ID + " INTEGER, " +
                    Fec_lote.COLUMN_NOME + " VARCHAR(60), " +
                    Fec_lote.COLUMN_IMAGEM + " VARCHAR(60), " +
                    Fec_lote.COLUMN_PDF_LINK + " VARCHAR(60), " +
                    Fec_lote.COLUMN_LOT_USERPDF_LINK + " VARCHAR(60), "  +
                    Fec_lote.COLUMN_QRCODE_LINK + " VARCHAR(60), " +
                    Fec_lote.COLUMN_AREA_ID + " INTEGER, " +
                    Fec_lote.COLUMN_USR_ID + " INTEGER, " +
            " CONSTRAINT pk_fec_lote PRIMARY KEY ( " + Fec_lote.COLUMN_ID + " ), " +
            " CONSTRAINT fk_fec_lot_area FOREIGN KEY ( " + Area.COLUMN_ID + " ), " +
            " CONSTRAINT fk_fec_lot_usuario FOREIGN KEY ( " + Usuario_Info.COLUMN_USR_ID + " )); ";

    public static final String CREATE_TABLE_ENTRADA_FECHADA = "CREATE TABLE " + Fec_entrada.TABLENAME +
            " ( " + Fec_entrada.COLUMN_ID + " INTEGER, " +
            Fec_entrada.COLUMN_LOTE_ID + " INTEGER, " +
            Fec_entrada.COLUMN_DATA + " DATETIME, " +
            Fec_entrada.COLUMN_TIPO + " VARCHAR(10), " +
            Fec_entrada.COLUMN_DESC + " VARCHAR(20), " +
            Fec_entrada.COLUMN_VALOR_1 + " INTEGER, " +
            Fec_entrada.COLUMN_VALOR_2 + " NUMERIC(10,2), " +
            Fec_entrada.COLUMN_CUSTO + " NUMERIC(10,2), " +
            Fec_entrada.COLUMN_USR_ID + " INTEGER, " +
            Fec_entrada.COLUMN_REG_NUM + " INTEGER, " +
            " CONSTRAINT pk_fec_ent PRIMARY KEY ( " + Fec_entrada.COLUMN_ID + " ), " +
            " CONSTRAINT fk_fec_ent_lote FOREIGN KEY ( " + Lote.COLUMN_ID + " ), " +
            " CONSTRAINT fk_fec_ent_usuario FOREIGN KEY ( " + Usuario_Info.COLUMN_USR_ID + " )); ";

}