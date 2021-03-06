package com.trabalho.tg.Helper;

import android.provider.BaseColumns;
import android.widget.ArrayAdapter;

import java.util.WeakHashMap;

public final class Contrato {

    public static class Usuario implements BaseColumns {
        public static final String TABLENAME = "USUARIO";
        public static final String COLUMN_ID = "usr_id";
        public static final String COLUMN_NOME = "usr_nome";
        public static final String COLUMN_EMAIL = "usr_email";
        public static final String COLUMN_SENHA = "usr_senha";
        public static final String COLUMN_FOTOPERFIL = "usr_fotoperfil";

    }

    public static class Usuario_Info implements BaseColumns{
        public static final String TABLENAME = "USUARIO_INFO";
        public static final String COLUMN_INFO_ID = "info_id";
        public static final String COLUMN_USR_ID = "info_usr_id";
        public static final String COLUMN_NOMEFANTASIA = "info_nomefantasia";
        public static final String COLUMN_CNPJ = "info_cnpj";
        public static final String COLUMN_SITE = "info_site";
        public static final String COLUMN_TELEFONE = "info_telefone";
        public static final String COLUMN_RZSOCIAL = "info_rzsocial";



    }

    public static class Endereco implements BaseColumns{
        public static final String TABLENAME = "ENDERECO";
        public static final String COLUMN_ID = "end_id";
        public static final String COLUMN_INFO_ID = "end_info_id";
        public static final String COLUMN_LOGRADOURO = "end_logradouro";
        public static final String COLUMN_CARTX = "end_cartx";
        public static final String COLUMN_CARTY = "end_carty";
        public static final String COLUMN_CEP = "end_cep";
        public static final String COLUMN_BAIRRO = "end_bairro";
        public static final String COLUMN_CIDADE = "end_cidade";
        public static final String COLUMN_UF = "end_uf";
        public static final String COLUMN_USR_ID = "end_usr_id";

    }

    public static class Reg_Agrotoxico implements BaseColumns{
        public static final String TABLENAME = "REG_AGROTOXICO";
        public static final String COLUMN_NUMERO = "reg_num";
        public static final String COLUMN_NOMECOM = "reg_nomecom";
        public static final String COLUMN_EMPRESA = "reg_empresa";
        public static final String COLUMN_ING_ATIVO = "reg_ing_ativo";

    }

    public static class Tipo_Entrada implements BaseColumns{
        public static final String TABLENAME = "TIPO_ENTRADA";
        public static final String COLUMN_TIPO_NUMERO = "tipo_numero";
        public static final String COLUMN_DESCRICAO = "tipo_descricao";

        public static final String AGROTOXICO = "agrotoxico";
        public static final String IRRIGACAO = "irrigacao";
        public static final String PLANTIO = "plantio";
        public static final String ADUBACAO = "adubacao";
        public static final String PREJUIZO = "prejuizo";
        public static final String MAO_DE_OBRA = "mao de obra";
        public static final String COLHEITA = "colheita";

    }

    public static class Area implements BaseColumns{
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

    public static class Lote implements BaseColumns{
        public static final String TABLENAME = "LOTE";
        public static final String COLUMN_ID = "lote_id";
        public static final String COLUMN_NOME = "lote_nome";
        public static final String COLUMN_PLANTA = "lote_planta";
        public static final String COLUMN_IMAGEM = "lote_imagem";
        public static final String COLUMN_AREA_ID = "lote_area_id";
        public static final String COLUMN_USR_ID = "lote_usr_id";

    }
    public static class Entrada implements BaseColumns{
        public static final String TABLENAME = "ENTRADA";
        public static final String COLUMN_NUMERO = "ent_num";
        public static final String COLUMN_LOTE_ID = "ent_lote_id";
        public static final String COLUMN_DATA = "ent_data";
        public static final String COLUMN_DETALHE = "ent_det_numero";
        public static final String COLUMN_USR_ID = "ent_usr_id";

    }

    public static class Entrada_Detalhe implements BaseColumns{
        public static final String TABLENAME = "ENTRADA_DETALHE";
        public static final String COLUMN_DETALHE_NUMERO = "det_numero";
        public static final String COLUMN_TIPO_NUM = "det_tipo_numero";
        public static final String COLUMN_TEMPO = "det_tempo";
        public static final String COLUMN_TPUN = "det_tpun";
        public static final String COLUMN_QTDE = "det_qtde";
        public static final String COLUMN_QTUN = "det_qtun";
        public static final String COLUMN_MUDAS_BANDEJA = "det_mudas_bandeja";
        public static final String COLUMN_VALOR = "det_valor";
        public static final String COLUMN_REG_LOTE = "det_reg_lote";
        public static final String COLUMN_REG_NUM = "det_reg_num";
        public static final String COLUMN_USR_ID = "det_usr_id";

        public static final String TEMPO_TIPO_AGROTOX = "Tempo de carência";
        public static final String TEMPO_TIPO_IRRIG = "Tempo";
        public static final String TEMPO_TIPO_MAO_DE_OBRA = "Tempo";
        public static final String QTDE_TIPO_AGROTOX = "Dose";
        public static final String QTDE_TIPO_PLANTIO = "Mudas";
        public static final String QTDE_TIPO_ADUBACAO = "Kg";
        public static final String QTDE_TIPO_PREJUIZO = "Prejuízo";
        public static final String QTDE_TIPO_MAO_DE_OBRA = "Recursos";
        public static final String QTDE_TIPO_COLHEITA = "Unidades";


    }

    public static class Fec_lote implements BaseColumns{
        public static final String TABLENAME = "LOTE_HIST";
        public static final String COLUMN_ID = "lote_id";
        public static final String COLUMN_NOME = "lote_nome";
        public static final String COLUMN_PLANTA = "lote_planta";
        public static final String COLUMN_IMAGEM = "lote_imagem";
        public static final String COLUMN_PDF_LINK = "lote_pdf_link";
        public static final String COLUMN_LOT_USERPDF_LINK = "lote_lote_userpdf_link";
        public static final String COLUMN_QRCODE_LINK = "lote_qrcode_link";
        public static final String COLUMN_AREA_ID = "lote_area_id";
        public static final String COLUMN_USR_ID = "lote_usr_id";
    }

    public static class Fec_entrada implements BaseColumns{
        public static final String TABLENAME = "ENTRADA_HIST";
        public static final String COLUMN_NUMERO = "ent_num";
        public static final String COLUMN_LOTE_ID = "ent_lote_id";
        public static final String COLUMN_DATA = "ent_data";
        public static final String COLUMN_DETALHE = "ent_det_numero";
        public static final String COLUMN_USR_ID = "ent_usr_id";

    }

    public static class Fec_Entrada_Detalhe implements BaseColumns{
        public static final String TABLENAME = "ENTRADA_DETALHE_HIST";
        public static final String COLUMN_DETALHE_NUMERO = "det_numero";
        public static final String COLUMN_TIPO_NUM = "det_tipo_numero";
        public static final String COLUMN_TEMPO = "det_tempo";
        public static final String COLUMN_TPUN = "det_tpun";
        public static final String COLUMN_QTDE = "det_qtde";
        public static final String COLUMN_QTUN = "det_qtun";
        public static final String COLUMN_MUDAS_BANDEJA = "det_mudas_bandeja";
        public static final String COLUMN_VALOR = "det_valor";
        public static final String COLUMN_REG_LOTE = "det_reg_lote";
        public static final String COLUMN_REG_NUM = "det_reg_num";
        public static final String COLUMN_USR_ID = "det_usr_id";

        public static final String TEMPO_TIPO_AGROTOX = "Tempo de carência";
        public static final String TEMPO_TIPO_IRRIG = "Tempo";
        public static final String TEMPO_TIPO_MAO_DE_OBRA = "Tempo";
        public static final String QTDE_TIPO_AGROTOX = "Dose";
        public static final String QTDE_TIPO_PLANTIO = "Bandejas";
        public static final String QTDE_TIPO_ADUBACAO = "Kg";
        public static final String QTDE_TIPO_PREJUIZO = "Prejuízo";
        public static final String QTDE_TIPO_MAO_DE_OBRA = "Recursos";
        public static final String QTDE_TIPO_COLHEITA = "Unidades";
    }

    //Table creations

    public static final String CREATE_TABLE_USUARIO  = "CREATE TABLE " + Usuario.TABLENAME +
            " ( " + Usuario.COLUMN_ID + " INTEGER CONSTRAINT pk_usuario PRIMARY KEY AUTOINCREMENT, " +
                    Usuario.COLUMN_NOME + " VARCHAR(60), " +
                    Usuario.COLUMN_SENHA + " VARCHAR(20), " +
                    Usuario.COLUMN_EMAIL + " VARCHAR(60), " +
                    Usuario.COLUMN_FOTOPERFIL + " VARCHAR(60)); ";

    public static final String CREATE_TABLE_USUARIO_INFO = "CREATE TABLE " + Usuario_Info.TABLENAME +
            " ( " + Usuario_Info.COLUMN_INFO_ID + " INTEGER CONSTRAINT pk_info PRIMARY KEY AUTOINCREMENT, " +
                    Usuario_Info.COLUMN_USR_ID + " INTEGER, " +
                    Usuario_Info.COLUMN_NOMEFANTASIA + " VARCHAR(60), " +
                    Usuario_Info.COLUMN_CNPJ + " VARCHAR(14), " +
                    Usuario_Info.COLUMN_SITE + " VARCHAR(60), " +
                    Usuario_Info.COLUMN_TELEFONE + " VARCHAR(20), " +
                    Usuario_Info.COLUMN_RZSOCIAL + " VARCHAR(100), " +
            " CONSTRAINT fk_info_usuario FOREIGN KEY ( " + Usuario_Info.COLUMN_USR_ID + ") REFERENCES " + Usuario.TABLENAME + " (  " +  Usuario.COLUMN_ID + " ));";

    public static final String CREATE_TABLE_ENDERECO = "CREATE TABLE " + Endereco.TABLENAME +
            " ( " + Endereco.COLUMN_ID + " INTEGER CONSTRAINT pk_endereco PRIMARY KEY AUTOINCREMENT,  " +
                    Endereco.COLUMN_INFO_ID + " INTEGER, " +
                    Endereco.COLUMN_LOGRADOURO + " VARCHAR(60), " +
                    Endereco.COLUMN_CARTX + " NUMERIC(3,2), " +
                    Endereco.COLUMN_CARTY + " NUMERIC(3,2), " +
                    Endereco.COLUMN_CEP + " INTEGER, " +
                    Endereco.COLUMN_BAIRRO + " VARCHAR(60), " +
                    Endereco.COLUMN_CIDADE + " VARCHAR(60), " +
                    Endereco.COLUMN_UF + " VARCHAR(2), " +
                    Endereco.COLUMN_USR_ID + " INTEGER, " +
            " CONSTRAINT fk_end_info_id FOREIGN KEY ( " + Endereco.COLUMN_INFO_ID  + ") REFERENCES " + Usuario_Info.TABLENAME + " (  " + Usuario_Info.COLUMN_INFO_ID + " ), " +
            " CONSTRAINT fk_end_usuario FOREIGN KEY ( " + Endereco.COLUMN_USR_ID  + ") REFERENCES " + Usuario.TABLENAME + " (  " + Usuario.COLUMN_ID + " ));";

    public static final String CREATE_TABLE_REG_AGROTOXICO = "CREATE TABLE " + Reg_Agrotoxico.TABLENAME +
            " ( " + Reg_Agrotoxico.COLUMN_NUMERO + " INTEGER CONSTRAINT pk_agrotox PRIMARY KEY AUTOINCREMENT, " +
                    Reg_Agrotoxico.COLUMN_NOMECOM + " VARCHAR(60), " +
                    Reg_Agrotoxico.COLUMN_EMPRESA + " VARCHAR(60), " +
                    Reg_Agrotoxico.COLUMN_ING_ATIVO + " VARCHAR(60))";

    public static final String CREATE_TABLE_TIPO_ENTRADA = "CREATE TABLE " + Tipo_Entrada.TABLENAME +
            " ( " + Tipo_Entrada.COLUMN_TIPO_NUMERO + " INTEGER CONSTRAINT pk_tipo_entrada PRIMARY KEY AUTOINCREMENT, " +
                    Tipo_Entrada.COLUMN_DESCRICAO + " VARCHAR(60)); ";

    public static final String CREATE_TABLE_AREA = "CREATE TABLE " + Area.TABLENAME +
            " ( " + Area.COLUMN_ID + " INTEGER CONSTRAINT pk_area PRIMARY KEY AUTOINCREMENT, " +
                    Area.COLUMN_LOTE_CONT + " VARCHAR(20), " +
                    Area.COLUMN_IMAGEM + " VARCHAR(60), " +
                    Area.COLUMN_NOME + " VARCHAR(60), " +
                    Area.COLUMN_DEL + " INTEGER, " +
                    Area.COLUMN_USR_ID + " INTEGER, " +
            " CONSTRAINT fk_ar_usuario FOREIGN KEY ( " + Area.COLUMN_USR_ID  + ") REFERENCES " + Usuario.TABLENAME + " (  " + Usuario.COLUMN_ID + " ));";

    public static final String CREATE_TABLE_LOTE = "CREATE TABLE " + Lote.TABLENAME +
            " ( " + Lote.COLUMN_ID + " INTEGER CONSTRAINT pk_lote PRIMARY KEY AUTOINCREMENT,  " +
                    Lote.COLUMN_NOME + " VARCHAR(60), " +
                    Lote.COLUMN_PLANTA + " VARCHAR(60), " +
                    Lote.COLUMN_IMAGEM + " VARCHAR(60), " +
                    Lote.COLUMN_AREA_ID + " INTEGER, " +
                    Lote.COLUMN_USR_ID + " INTEGER, " +
            " CONSTRAINT fk_lot_area FOREIGN KEY ( " + Lote.COLUMN_AREA_ID + " ) REFERENCES " + Area.COLUMN_ID  + " ( "    + Area.COLUMN_ID + " ), " +
            " CONSTRAINT fk_lot_usuario FOREIGN KEY ( " + Lote.COLUMN_USR_ID  + ") REFERENCES " + Usuario.TABLENAME + " (  " + Usuario.COLUMN_ID + " ));";

    public static final String CREATE_TABLE_ENTRADA = "CREATE TABLE " + Entrada.TABLENAME +
            " ( " + Entrada.COLUMN_NUMERO + " INTEGER CONSTRAINT pk_ent PRIMARY KEY AUTOINCREMENT,  " +
                    Entrada.COLUMN_LOTE_ID + " INTEGER, " +
                    Entrada.COLUMN_DATA + " DATETIME, " +
                    Entrada.COLUMN_DETALHE + " INTEGER, " +
                    Entrada.COLUMN_USR_ID + " INTEGER, " +
            " CONSTRAINT fk_ent_lote FOREIGN KEY ( " + Entrada.COLUMN_LOTE_ID + " ) REFERENCES  " + Lote.TABLENAME + "( " + Lote.COLUMN_ID + " ), " +
            " CONSTRAINT fk_ent_detalhe FOREIGN KEY ( " + Entrada.COLUMN_DETALHE + " ) REFERENCES  " + Entrada_Detalhe.TABLENAME + "( " + Entrada_Detalhe.COLUMN_DETALHE_NUMERO + " ), " +
            " CONSTRAINT fk_ent_usuario FOREIGN KEY ( " + Entrada.COLUMN_USR_ID + ") REFERENCES " + Usuario.TABLENAME + " (  " + Usuario.COLUMN_ID + " ));";

    public static final String CREATE_TABLE_ENTRADA_DETALHE = "CREATE TABLE " + Entrada_Detalhe.TABLENAME +
            " ( " + Entrada_Detalhe.COLUMN_DETALHE_NUMERO + " INTEGER CONSTRAINT pk_tp PRIMARY KEY AUTOINCREMENT,  " +
                    Entrada_Detalhe.COLUMN_TIPO_NUM + " INTEGER, " +
                    Entrada_Detalhe.COLUMN_TEMPO + " NUMERIC(10,2), " +
                    Entrada_Detalhe.COLUMN_TPUN + " VARCHAR(20), " +
                    Entrada_Detalhe.COLUMN_QTDE + " NUMERIC(10,2), " +
                    Entrada_Detalhe.COLUMN_QTUN + " VARCHAR(20), " +
                    Entrada_Detalhe.COLUMN_MUDAS_BANDEJA + " INTEGER, " +
                    Entrada_Detalhe.COLUMN_VALOR + " NUMERIC(10,2), " +
                    Entrada_Detalhe.COLUMN_REG_LOTE + " VARCHAR(60), " +
                    Entrada_Detalhe.COLUMN_REG_NUM + " INTEGER, " +
                    Entrada_Detalhe.COLUMN_USR_ID + " INTEGER, " +
            " CONSTRAINT fk_det_reg FOREIGN KEY ( " + Entrada_Detalhe.COLUMN_REG_NUM + " ) REFERENCES  " + Reg_Agrotoxico.TABLENAME + "( " + Reg_Agrotoxico.COLUMN_NUMERO + " ), " +
            " CONSTRAINT fk_det_tipo FOREIGN KEY ( " + Entrada_Detalhe.COLUMN_TIPO_NUM + " ) REFERENCES  " + Tipo_Entrada.TABLENAME + "( " + Tipo_Entrada.COLUMN_TIPO_NUMERO + " ), " +
            " CONSTRAINT fk_det_usuario FOREIGN KEY ( " + Entrada_Detalhe.COLUMN_USR_ID + ") REFERENCES " + Usuario.TABLENAME + " (  " + Usuario.COLUMN_ID + " ));";

    public static final String CREATE_TABLE_LOTE_FECHADO = "CREATE TABLE " + Fec_lote.TABLENAME +
            " ( " + Fec_lote.COLUMN_ID + " INTEGER CONSTRAINT pk_fec_lote PRIMARY KEY AUTOINCREMENT,  " +
                    Fec_lote.COLUMN_NOME + " VARCHAR(60), " +
                    Fec_lote.COLUMN_PLANTA + " VARCHAR(60), " +
                    Fec_lote.COLUMN_IMAGEM + " VARCHAR(60), " +
                    Fec_lote.COLUMN_PDF_LINK + " VARCHAR(60), " +
                    Fec_lote.COLUMN_LOT_USERPDF_LINK + " VARCHAR(60), "  +
                    Fec_lote.COLUMN_QRCODE_LINK + " VARCHAR(60), " +
                    Fec_lote.COLUMN_AREA_ID + " INTEGER, " +
                    Fec_lote.COLUMN_USR_ID + " INTEGER, " +
            " CONSTRAINT fk_fec_lot_area FOREIGN KEY ( "  + Fec_lote.COLUMN_AREA_ID + " ) REFERENCES " + Area.TABLENAME + " (" + Area.COLUMN_ID + " ), " +
            " CONSTRAINT fk_fec_lot_usuario FOREIGN KEY ( " + Fec_lote.COLUMN_USR_ID  + ") REFERENCES " + Usuario.TABLENAME + " (  " + Usuario.COLUMN_ID + " ));";

    public static final String CREATE_TABLE_ENTRADA_FECHADA = "CREATE TABLE " + Fec_entrada.TABLENAME +
            " ( " + Fec_entrada.COLUMN_NUMERO + " INTEGER CONSTRAINT pk_ent PRIMARY KEY AUTOINCREMENT,  " +
                    Fec_entrada.COLUMN_LOTE_ID + " INTEGER, " +
                    Fec_entrada.COLUMN_DATA + " DATETIME, " +
                    Fec_entrada.COLUMN_DETALHE + " INTEGER, " +
                    Fec_entrada.COLUMN_USR_ID + " INTEGER, " +
            " CONSTRAINT fk_fec_ent_lote FOREIGN KEY ( " + Fec_entrada.COLUMN_LOTE_ID + " ) REFERENCES " + Lote.TABLENAME + " (" + Lote.COLUMN_ID + " ), " +
            " CONSTRAINT fk_fec_ent_detalhe FOREIGN KEY ( " + Fec_entrada.COLUMN_DETALHE + " ) REFERENCES " + Fec_Entrada_Detalhe.TABLENAME + " (" + Fec_Entrada_Detalhe.COLUMN_DETALHE_NUMERO + " ), " +
            " CONSTRAINT fk_fec_ent_usuario FOREIGN KEY ( " + Fec_entrada.COLUMN_USR_ID  + ") REFERENCES " + Usuario.TABLENAME + " (  " + Usuario.COLUMN_ID + " ));";



    public static final String CREATE_TABLE_FEC_ENTRADA_DETALHE = "CREATE TABLE " + Fec_Entrada_Detalhe.TABLENAME +
            " ( " + Fec_Entrada_Detalhe.COLUMN_DETALHE_NUMERO + " INTEGER CONSTRAINT pk_tp PRIMARY KEY AUTOINCREMENT,  " +
                    Fec_Entrada_Detalhe.COLUMN_TIPO_NUM + " INTEGER, " +
                    Fec_Entrada_Detalhe.COLUMN_TEMPO + " NUMERIC(10,2), " +
                    Fec_Entrada_Detalhe.COLUMN_TPUN + " VARCHAR(20), " +
                    Fec_Entrada_Detalhe.COLUMN_QTDE + " NUMERIC(10,2), " +
                    Fec_Entrada_Detalhe.COLUMN_QTUN + " VARCHAR(20), " +
                    Fec_Entrada_Detalhe.COLUMN_MUDAS_BANDEJA + " INTEGER, " +
                    Fec_Entrada_Detalhe.COLUMN_VALOR + " NUMERIC(10,2), " +
                    Fec_Entrada_Detalhe.COLUMN_REG_LOTE + " VARCHAR(60), " +
                    Fec_Entrada_Detalhe.COLUMN_REG_NUM + " INTEGER, " +
                    Fec_Entrada_Detalhe.COLUMN_USR_ID + " INTEGER, " +
            " CONSTRAINT fk_fec_det_reg FOREIGN KEY ( " + Fec_Entrada_Detalhe.COLUMN_REG_NUM + " ) REFERENCES  " + Reg_Agrotoxico.TABLENAME + "( " + Reg_Agrotoxico.COLUMN_NUMERO + " ), " +
            " CONSTRAINT fk_fec_det_tipo FOREIGN KEY ( " + Fec_Entrada_Detalhe.COLUMN_TIPO_NUM + " ) REFERENCES  " + Tipo_Entrada.TABLENAME + "( " + Tipo_Entrada.COLUMN_TIPO_NUMERO + " ), " +
            " CONSTRAINT fk_fec_det_usuario FOREIGN KEY ( " + Fec_Entrada_Detalhe.COLUMN_USR_ID + ") REFERENCES " + Usuario.TABLENAME + " (  " + Usuario.COLUMN_ID + " ));";

}
