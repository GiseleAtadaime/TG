package com.trabalho.tg.Helper;

import android.content.Context;
import android.os.Environment;
import com.trabalho.tg.Model.Area;
import com.trabalho.tg.Model.Entrada;
import com.trabalho.tg.Model.Lote;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


/**
 *     Uses:
 *     HTMLhtmlBuilderBuilder htmlBuilder = new HTMLhtmlBuilderBuilder(null, true, 2, 3);
 *     htmlBuilder.addhtmlBuilderHeader("1H", "2H", "3H");
 *     htmlBuilder.addRowValues("1", "2", "3");
 *     htmlBuilder.addRowValues("4", "5", "6");
 *     htmlBuilder.addRowValues("9", "8", "7");
 *     String htmlBuilder = htmlBuilder.build();
 *     System.out.println(htmlBuilder.toString());
 */

public class Report_Helper {


    private Utils_TG utils = new Utils_TG();
    private ReportTemplate template = new ReportTemplate();
    private int columns;
    private final StringBuilder htmlBuilder = new StringBuilder();

    public static Boolean NEGATIVE = false;
    public static Boolean POSITIVE = true;

    public static String H1_START = "<h1>";
    public static String H1_END = "</h1>";
    public static String H2_START = "<h2>";
    public static String H2_END = "</h2>";

    public Report_Helper() {
        this.columns = columns;
        htmlBuilder.append(template.HTMLSTART);
        htmlBuilder.append(template.STYLES);
        htmlBuilder.append(template.BODYSTART);
    }


    /**
     * @param values
     */
    public void addHeaderGeral(String... values) {
        // if (values.length != columns) {
        //     System.out.println("Error column lenth");
        // } else {
        int lastIndex = htmlBuilder.lastIndexOf(template.BODYSTART);
        if (lastIndex > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(template.HEADERGERALSTART);
            sb.append(H2_START + "Area: " + values[0] + H2_END);
            sb.append(H2_START + "Lote: " + values[1] + H2_END);
            sb.append(H2_START + "Cultura: " + values[2] + H2_END);
            sb.append(template.HEADERGERALEND);
            htmlBuilder.insert(lastIndex, sb.toString());
        }
        // }
    }

    public void addHeaderFiscal(String... values) {
        // if (values.length != columns) {
        //     System.out.println("Error column lenth");
        // } else {
        int lastIndex = htmlBuilder.lastIndexOf(template.BODYSTART);
        if (lastIndex > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(template.HEADERFISCALSTART);
            sb.append(template.HEADERNOME + values[0]);
            sb.append(template.HEADERNOMEFANT + values[0]);
            sb.append(template.HEADERRAZAO + values[0]);
            sb.append(template.HEADERCNPJ + values[0]);
            sb.append(template.HEADEREMAIL + values[0]);
            sb.append(template.HEADERTELEFONE + values[0]);
            sb.append(template.HEADERLOGRADOURO + values[0]);
            sb.append(template.HEADERCIDADE + values[0]);
            sb.append(template.HEADERBAIRRO + values[0]);
            sb.append(template.HEADERCEP + values[0]);
            sb.append(template.HEADERLATITUDE + values[0]);
            sb.append(template.HEADERLONGITUDE + values[0]);
            sb.append(template.HEADERFISCALEND);
            htmlBuilder.insert(lastIndex, sb.toString());
        }
        // }
    }

    public void addInfoHeaderFiscal(String... values) {
        // if (values.length != columns) {
        //     System.out.println("Error column lenth");
        // } else {
        int lastIndex = htmlBuilder.lastIndexOf(template.BODYSTART);
        if (lastIndex > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(template.SEGUNDOHEADERFISCALSTART);
            sb.append(H2_START + "Area: " + values[0] + H2_END);
            sb.append(H2_START + "Lote: " + values[1] + H2_END);
            sb.append(H2_START + "Cultura: " + values[2] + H2_END);
            sb.append(template.SEGUNDOHEADERFISCALEND);
            htmlBuilder.insert(lastIndex, sb.toString());
        }
        // }
    }

    /**
     * @param values
     */
    public void addRowValuesFiscal(String... values) {
        // if (values.length != columns) {
        //     System.out.println("Error column lenth");
        // } else {
        int lastIndex = htmlBuilder.lastIndexOf(template.SEGUNDOHEADERFISCALEND);
        if (lastIndex > 0) {
            int index = lastIndex + template.SEGUNDOHEADERFISCALEND.length();
            StringBuilder sb = new StringBuilder();
            sb.append(template.TABLEFISCALSTART);
            for (String value : values) {
                sb.append(template.ROW_START);

                sb.append(template.DATA_ROW_START);
                sb.append(values[0]);
                sb.append(template.DATA_ROW_END);
                sb.append(template.NOME_ROW_START);
                sb.append(values[0]);
                sb.append(template.NOME_ROW_END);
                sb.append(template.CARENCIA_ROW_START);
                sb.append(values[0]);
                sb.append(template.CARENCIA_ROW_END);
                sb.append(template.DOSE_ROW_START);
                sb.append(values[0]);
                sb.append(template.DOSE_ROW_END);

                sb.append(template.ROW_END);
            }
            sb.append(template.TABLEFISCALEND);
            sb.append(template.BODYEND);
            sb.append(template.HTMLEND);
            htmlBuilder.insert(index, sb.toString());
        }
        // }
    }


    /**
     * @param values
     */
    public void addRowValuesGeral(List<List<String>> values, String total) {
        // if (values.length != columns) {
        //     System.out.println("Error column lenth");
        // } else {
        int lastIndex = htmlBuilder.lastIndexOf(template.HEADERGERALEND);
        if (lastIndex > 0) {
            int index = lastIndex + template.HEADERGERALEND.length();
            StringBuilder sb = new StringBuilder();
            sb.append(template.TABLEGERALSTART);
            for (List<String> value : values) {
                sb.append(template.ROW_START);

                sb.append(template.DATA_ROW_START);
                sb.append(value.get(0));
                sb.append(template.DATA_ROW_END);
                sb.append(template.DESCRICAO_ROW_START);
                sb.append(value.get(1));
                sb.append(template.DESCRICAO_ROW_END);
                sb.append(template.TEMPO_ROW_START);
                sb.append(value.get(2));
                sb.append(template.TEMPO_ROW_END);
                sb.append(template.QUANTIDADE_ROW_START);
                sb.append(value.get(3));
                sb.append(template.QUANTIDADE_ROW_END);
                sb.append(template.MUDAS_ROW_START);
                sb.append(value.get(4));
                sb.append(template.MUDAS_ROW_END);
                sb.append(template.VALOR_ROW_START);
                sb.append(value.get(5));
                sb.append(template.VALOR_ROW_END);

                sb.append(template.ROW_END);
            }

            sb.append(template.TOTAL_ROW_START);
            sb.append(total);
            sb.append(template.TOTAL_ROW_END);


            sb.append(template.TABLEGERALEND);
            sb.append(template.BODYEND);
            sb.append(template.HTMLEND);
            htmlBuilder.insert(index, sb.toString());
        }
        // }
    }


    /**
     * @return
     */
    public String build() {
        return htmlBuilder.toString();
    }

    public static void whenWriteStringUsingBufferedWritter_thenCorrect(String str, Context context)
            throws IOException {
        File dir = new File(context.getFilesDir(),"reports");
        File file = new File(dir,"testereport1.html");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(str);
        writer.close();
    }


    public List<List<String>> treatEntradas(Lote argLote){

        List<List<String>> lote = null;
        List<String> entrada = null;

        for(Entrada ent : argLote.getLot_ent()){

            entrada.add(utils.formatDate(ent.getEnt_data(),true));
            entrada.add(ent.getEnt_desc());


            switch (ent.getEnt_tipo()){
                case 1://plantio

                    entrada.add("-");
                    entrada.add(utils.doubleToString(ent.getEnt_qtde()) + " " + ent.getEnt_qtun());
                    entrada.add(ent.getEnt_mudas_bandeja().toString());
                    entrada.add("-" + utils.formatMonetario(utils.doubleToString(ent.getEnt_valor())));
                    break;
                case 2://adubação
                    entrada.add(utils.doubleToString(ent.getEnt_tempo()) + " " + ent.getEnt_tpun());
                    entrada.add(utils.doubleToString(ent.getEnt_qtde()) + " " + ent.getEnt_qtun());
                    entrada.add("-");
                    entrada.add("-" + utils.formatMonetario(utils.doubleToString(ent.getEnt_valor())));
                    break;
                case 3://agrotóxico
                    entrada.add(utils.doubleToString(ent.getEnt_tempo()) + " " + ent.getEnt_tpun() + " (carência)");
                    entrada.add(utils.doubleToString(ent.getEnt_qtde()) + " " + ent.getEnt_qtun() + "/100L");
                    entrada.add("-");
                    entrada.add("-" + utils.formatMonetario(utils.doubleToString(ent.getEnt_valor())));
                    break;
                case 4://colheita
                    entrada.add("-");
                    entrada.add(utils.doubleToString(ent.getEnt_qtde()) + " " + ent.getEnt_qtun());
                    entrada.add("-");
                    entrada.add(utils.formatMonetario(utils.doubleToString(ent.getEnt_valor())));
                    break;
                case 5://mão de obra
                    entrada.add(utils.doubleToString(ent.getEnt_tempo()) + " " + ent.getEnt_tpun());
                    entrada.add(utils.doubleToString(ent.getEnt_qtde()) + " " + ent.getEnt_qtun());
                    entrada.add("-");
                    entrada.add("-" + utils.formatMonetario(utils.doubleToString(ent.getEnt_valor())));
                    break;
                case 6://prejuízo
                    entrada.add("-");
                    entrada.add(utils.doubleToString(ent.getEnt_qtde()) + " " + ent.getEnt_qtun());
                    entrada.add("-");
                    entrada.add("-" + utils.formatMonetario(utils.doubleToString(ent.getEnt_valor())));
                    break;
                case 7://irrigação
                    entrada.add(utils.doubleToString(ent.getEnt_tempo()) + " " + ent.getEnt_tpun());
                    entrada.add("-");
                    entrada.add("-");
                    entrada.add("-" + utils.formatMonetario(utils.doubleToString(ent.getEnt_valor())));
                    break;
            }


            lote.add(entrada);

            entrada.clear();
        }

        return lote;
    }

    public Double calcTotal(Lote lote){

        Double total = 0.0;

        for(Entrada ent : lote.getLot_ent()){
            if(ent.getEnt_tipo() == 4){
                total += ent.getEnt_valor();
            }
            else{
                total -= ent.getEnt_valor();
            }
        }

        return total;
    }

    public void generateRelatorioGeral(Area area, Context context, Integer loteID){

        Report_Helper htmlBuilder = new Report_Helper();
        htmlBuilder.addHeaderGeral(area.getAr_nome(),area.getAr_lote().get(loteID).getLot_nome(),area.getAr_lote().get(loteID).getLot_planta());

        Double total = calcTotal(area.getAr_lote().get(loteID));
        String totalString = "";

        if(total < 0.0){
            total =  Math.abs(total);
            totalString = "-" + utils.formatMonetario(utils.doubleToString(total));
        }
        else{
            totalString = utils.formatMonetario(utils.doubleToString(total));
        }

        htmlBuilder.addRowValuesGeral(treatEntradas(area.getAr_lote().get(loteID)), totalString);

        String html = htmlBuilder.build();
        //System.out.println(htmlBuilder.toString());
        try{
            whenWriteStringUsingBufferedWritter_thenCorrect(html, context);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

//    public static void main() {
//        Report_Helper htmlBuilder = new Report_Helper();
//        htmlBuilder.addHeaderFiscal("aaaaaaaaaaaaaaaaaaa", "2H", "3H");
//        htmlBuilder.addInfoHeaderFiscal("aaaaaaa", "aaaaaaaaa", "aaaaaaaaaaa");
//        htmlBuilder.addRowValuesFiscal("1","1", "2", "3");
//        String html = htmlBuilder.build();
//        //System.out.println(htmlBuilder.toString());
//        try{
//            whenWriteStringUsingBufferedWritter_thenCorrect(html.toString());
//        }
//        catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//    }


}