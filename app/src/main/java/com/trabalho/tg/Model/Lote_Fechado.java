package com.trabalho.tg.Model;

public class Lote_Fechado extends Lote{
    private String lot_pdf_link;
    private String lot_userpdf_link;
    private String lot_qrcode_link;

    public Lote_Fechado(Integer id){
        super(id);
    }

    public String getLot_pdf_link() {
        return lot_pdf_link;
    }

    public void setLot_pdf_link(String lot_pdf_link) {
        this.lot_pdf_link = lot_pdf_link;
    }

    public String getLot_userpdf_link() {
        return lot_userpdf_link;
    }

    public void setLot_userpdf_link(String lot_userpdf_link) {
        this.lot_userpdf_link = lot_userpdf_link;
    }

    public String getLot_qrcode_link() {
        return lot_qrcode_link;
    }

    public void setLot_qrcode_link(String lot_qrcode_link) {
        this.lot_qrcode_link = lot_qrcode_link;
    }
}
