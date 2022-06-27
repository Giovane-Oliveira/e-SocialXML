/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.awt.Toolkit;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import jdbc.ConnectionFactory;
import model.Dadoss12;
import java.util.ArrayList;
import java.util.Iterator;
import model.Dadoss1210;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static jdk.nashorn.internal.objects.NativeString.substring;
import java.util.Random;


/**
 *
 * @author giovane.oliveira
 */
public class ReqDAO {
    
     private Connection con;
 static String fornecedor, item1, item2, item3, item4, item5, item6, item7, item8, item9, item10;
 static int forx = 123456;
 static int xcount;
 

    public ReqDAO() {
        this.con = new ConnectionFactory().getConnection();
    }
    
    
        public List<Dadoss12> consultarDadosS12(String dataInicial, String dataFinal) {   
        try {
            
            dataInicial = arrumarData(dataInicial);
            dataFinal = arrumarData(dataFinal);
            
            List<Dadoss12> lista = new ArrayList<>();
            String sql = "SELECT E2_FORNECE, E2_NOMFOR, E2_VALOR, F1_DOC, F1_VALMERC, F1_BASEINS, F1_VALSEST, F1_INSS, E2_EMISSAO, E2_VENCREA, C9V_ID, E2_FORNECE+F1_DOC ideDmDev, F1_FILIAL, C9V_NIS, C9V_CPF, SUBSTRING(E2_EMISSAO,1,4)+'-'+SUBSTRING(E2_EMISSAO,5,2) AS PERAPUR, SUBSTRING(C9V_DTNASC,1,4)+'-'+SUBSTRING(C9V_DTNASC,5,2)+'-'+SUBSTRING(C9V_DTNASC,7,2) DT_NASC,\n" +
"\n" +
"\n" +
"\n" +
"CASE E2_FORNECE WHEN '201793' THEN '715615'\n" +
"WHEN '202066' THEN '715615'\n" +
"WHEN '201827' THEN '914410'\n" +
"WHEN '202304' THEN '914410'\n" +
"WHEN '201709' THEN '914405'\n" +
"ELSE A2.A2_CBO\n" +
"END CBO,\n" +
"\n" +
"\n" +
"\n" +
"\n" +
"SUBSTRING(A2.A2_DTNASC,1,4)+'-'+SUBSTRING(A2.A2_DTNASC,5,2)+'-'+SUBSTRING(A2.A2_DTNASC,7,2) DT_NASC_A2,\n" +
"A2.A2_CODINSS PIS_A2,\n" +
"A2.A2_CGC CPF_A2,\n" +
"A2.A2_NOME\n" +
"FROM SE2010\n" +
"LEFT OUTER JOIN SF1010 ON F1_FILIAL = E2_FILIAL AND F1_FORNECE = E2_FORNECE AND F1_LOJA = E2_LOJA AND E2_NUM = F1_DOC AND SF1010.D_E_L_E_T_ = ''\n" +
"LEFT JOIN SA2010 A2 ON E2_FORNECE=A2.A2_COD AND E2_LOJA=A2.A2_LOJA AND A2.D_E_L_E_T_ = ''\n" +
"LEFT OUTER JOIN C9V010 ON (C9V_CPF = CASE E2_FORNECE\n" +
"WHEN '201827' THEN '01411974042'\n" +
"WHEN '201793' THEN '29959446034'\n" +
"WHEN '202066' THEN '37697170078'\n" +
"WHEN '202304' THEN '10873237978'\n" +
"WHEN '201709' THEN '05908963933'\n" +
"ELSE A2_CGC END) AND C9V010.D_E_L_E_T_ = '' AND C9V_NOMEVE = 'TAUTO' WHERE E2_TIPO='NF'\n" +
"AND E2_NATUREZ in('703','705','720','721','722','725','726')\n" +
"--AND E2_PREFIXO LIKE '%U'\n" +
"AND SE2010.D_E_L_E_T_ = ''\n" +
"AND ( A2.A2_CODINSS <> '' OR A2.A2_CODNIT <> '' )\n" +
"AND E2_EMISSAO BETWEEN ? AND ? \n" +
"AND E2_FORNECE NOT IN('202118','202072')\n" +
"ORDER BY 1";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, dataInicial);
            stmt.setString(2, dataFinal);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Dadoss12 obj = new Dadoss12();
                obj.setE2_FORNECE(rs.getString("E2_FORNECE"));
                obj.setE2_NOMFOR(rs.getString("E2_NOMFOR"));
                obj.setE2_VALOR(rs.getString("E2_VALOR"));
                obj.setF1_DOC(rs.getString("F1_DOC"));
                obj.setF1_VALMERC(rs.getString("F1_VALMERC"));
                obj.setF1_BASEINS(rs.getString("F1_BASEINS"));
                obj.setF1_VALSEST(rs.getString("F1_VALSEST"));
                obj.setF1_INSS(rs.getString("F1_INSS"));
                obj.setE2_EMISSAO(rs.getString("E2_EMISSAO"));
                obj.setE2_VENCREA(rs.getString("E2_VENCREA"));
                obj.setC9V_ID(rs.getString("C9V_ID"));
                obj.setF1_FILIAL(rs.getString("F1_FILIAL"));
                obj.setIdeDmDev(rs.getString("ideDmDev"));
                obj.setC9_NIS(rs.getString("C9V_NIS"));
                obj.setC9V_CPF(rs.getString("C9V_CPF"));
                obj.setPERAPUR(rs.getString("PERAPUR"));
                obj.setDT_NASC(rs.getString("DT_NASC"));
                obj.setCBO(rs.getString("CBO"));
                obj.setDT_NASC_A2(rs.getString("DT_NASC_A2"));
                obj.setPIS_A2(rs.getString("PIS_A2"));
                obj.setCPF_A2(rs.getString("CPF_A2"));
                obj.setA2_NOME(rs.getString("A2_NOME"));
              //  obj.setCPF_A3(rs.getString("CPF_A3"));
                

                lista.add(obj);

            }
            return lista;

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "" + e);

        }
        return null;

    }
    
    public String arrumarData(String data){
    String ano, mes, dia;
    
    ano = data.substring(6,10);
    mes = data.substring(3,5);
    dia = data.substring(0,2);
    
   //JOptionPane.showMessageDialog(null, "ano: " + ano + " mes: " + mes + " dia: " + dia);
    
    return ano + mes + dia;
    
    }
    
    
    public List<Dadoss1210> consultarDadosS1210(String dataInicial, String dataFinal) {   
   
        try {
            
            dataInicial = arrumarData(dataInicial);
            dataFinal = arrumarData(dataFinal);
            
            List<Dadoss1210> lista = new ArrayList<>();
            String sql = "SELECT SUBSTRING(E5_DATA,1,4)+'-'+SUBSTRING(E5_DATA,5,2) PERIODO, C9V_CPF, SUBSTRING(E5_DATA ,1,4)+'-'+SUBSTRING(E5_DATA ,5,2)+'-'+SUBSTRING(E5_DATA ,7,2) DT_BAIXA ,E5_VALOR, SUBSTRING(E2_EMISSAO ,1,4)+'-'+SUBSTRING(E2_EMISSAO ,5,2)+'-'+SUBSTRING(E2_EMISSAO ,7,2) EMISSAO ,F1_INSS,E2_FORNECE, E2_NOMFOR,F1_DOC NUMDOC, E2_FORNECE+F1_DOC ideDmDev\n" +
"FROM SE5010 INNER JOIN SE2010 ON E2_NUM = E5_NUMERO AND E2_FORNECE = E5_CLIFOR AND E2_LOJA = E5_LOJA AND SE2010.D_E_L_E_T_ = ''\n" +
"LEFT OUTER JOIN SF1010 ON F1_FILIAL = E2_FILIAL AND F1_FORNECE = E2_FORNECE AND F1_LOJA = E2_LOJA AND E2_NUM = F1_DOC AND SF1010.D_E_L_E_T_ = ''\n" +
"LEFT OUTER JOIN SA2010 ON A2_COD = F1_FORNECE AND A2_LOJA = F1_LOJA AND SA2010.D_E_L_E_T_ = ''\n" +
"LEFT OUTER JOIN C9V010 ON (C9V_CPF = CASE E2_FORNECE WHEN '201827' THEN '01411974042'\n" +
"WHEN '201793' THEN '29959446034'\n" +
"WHEN '202066' THEN '37697170078'\n" +
"WHEN '202304' THEN '10873237978'\n" +
"WHEN '201709' THEN '05908963933'\n" +
"ELSE A2_CGC END) AND C9V010.D_E_L_E_T_ = '' AND C9V_NOMEVE = 'TAUTO' WHERE E2_TIPO='NF'\n" +
"AND E5_NATUREZ in ('703','705','720','721','722','725','726')\n" +
"AND E5_DATA BETWEEN ? AND ? \n" +
"--AND E5_PREFIXO LIKE '%U'\n" +
"AND SE5010.D_E_L_E_T_ = ''\n" +
"AND (A2_CODINSS <> '' OR A2_CODNIT <> '')\n" +
"AND E2_FORNECE NOT IN('202118','202072')\n" +
"-- AND A2_CBO IN ('622010','514320','782310','715615','252215')\n" +
"\n" +
"\n" +
"\n" +
"ORDER BY 1,7;";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, dataInicial);
            stmt.setString(2, dataFinal);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Dadoss1210 obj = new Dadoss1210();
                obj.setPERIODO(rs.getString("PERIODO"));
                obj.setC9V_CPF(rs.getString("C9V_CPF"));
                obj.setDT_BAIXA(rs.getString("DT_BAIXA"));
                obj.setE5_VALOR(rs.getString("E5_VALOR"));
                obj.setEMISSAO(rs.getString("EMISSAO"));
                obj.setF1_INSS(rs.getString("F1_INSS"));
                obj.setE2_FORNECE(rs.getString("E2_FORNECE"));
                obj.setE2_NOMFOR(rs.getString("E2_NOMFOR"));
                obj.setNUMDOC(rs.getString("NUMDOC"));
                obj.setIdeDmDev(rs.getString("ideDmDev"));
                

                lista.add(obj);

            }
            return lista;

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "" + e);

        }
        return null;

    }
    
    public void verificarPasta(){
    if (!new File("C:\\Totvs").exists()) {
          new File("C:\\Totvs").mkdir();
           new File("C:\\Totvs\\e-Social").mkdir();
            new File("C:\\Totvs\\e-Social\\S-1200").mkdir();
             new File("C:\\Totvs\\e-Social\\S-1210").mkdir();
    }else if(!new File("C:\\Totvs\\e-Social").exists()){
     new File("C:\\Totvs\\e-Social").mkdir();
            new File("C:\\Totvs\\e-Social\\S-1200").mkdir();
             new File("C:\\Totvs\\e-Social\\S-1210").mkdir();
    }else if (!new File("C:\\Totvs\\e-Social\\S-1200").exists()) {
            new File("C:\\Totvs\\e-Social\\S-1200").mkdir();
             new File("C:\\Totvs\\e-Social\\S-1210").mkdir();
        }else {
            System.out.println("Pasta caminho já existe ...");
        }
    
    }
    
    public void gerarxml12(List<Dadoss12> lista, String IndicadorRetificacao, String TipoAmbiente) throws IOException{  
        
  int x;
            Random random = new Random();
     int numero = random.nextInt(9);  
     verificarPasta();

 
String codCategoria, nrInsc, codLotacao, codigo,  arquivo = null;    
 DecimalFormat df = new DecimalFormat("0.00");
// valores obtidos no protheus;
    String RautNTributado = "8349";
    String RpagTribu = "8345";
    String Rinss = "8346";
    String Rsest = "8347";
    String RSenat = "8352";
  
     for(x = 0; x < lista.size(); x++){
         
       int  w = x + 1;
     
         if(forx == x && !lista.get(x).getE2_FORNECE().toString().equals(lista.get(w).getE2_FORNECE().toString()) ){
         
         
         }else{
         
         if(lista.get(x).getCBO().replaceAll("\\s+","").contains("782310")){
    codCategoria = "712";
    
    }else if(lista.get(x).getCBO().replaceAll("\\s+","").contains("715615") || lista.get(x).getCBO().replaceAll("\\s+","").contains("914410") || lista.get(x).getCBO().replaceAll("\\s+","").contains("914405")){
    
        codCategoria = "741";
    
    }else if(lista.get(x).getCBO().replaceAll("\\s+","").contains("782315")){
    
        codCategoria = "711";
    
    }else{
    
    codCategoria = "701";
    
    }
    
    if("00".equals(lista.get(x).getF1_FILIAL().replaceAll("\\s+",""))){
     nrInsc = "00350387000106";
     codLotacao = "01.01";
    }else{   
      nrInsc = "00350387000459";
     codLotacao = "01.04"; 
    }
    
if("741".equals(codCategoria.replaceAll("\\s+",""))){
    
       item1 =  "<itensRemun>\n" +
              "<codRubr>" + "8351" + "</codRubr> \n" +
                "<ideTabRubr>1</ideTabRubr>\n" +
               //  ' colocar               Codigo = Codigo & "<qtdRubr>1</qtdRubr>"
             "<vrRubr>"+String.valueOf(df.format(Double.parseDouble(lista.get(x).getE2_VALOR()))).replace(",", ".")+"</vrRubr>\n" +
             "<indApurIR>0</indApurIR>\n" +
              "</itensRemun>";
    

}else if("701".equals(codCategoria.replaceAll("\\s+",""))){

    
    if(214420 == (Integer.parseInt(lista.get(x).getCBO().replaceAll("\\s+",""))) || 223208 == (Integer.parseInt(lista.get(x).getCBO().replaceAll("\\s+","")))){
    item2 =  "<itensRemun>\n" +
              "<codRubr>" + RpagTribu + "</codRubr> \n" +
                "<ideTabRubr>1</ideTabRubr>\n" +
               //  ' colocar               Codigo = Codigo & "<qtdRubr>1</qtdRubr>"
             "<vrRubr>"+lista.get(x).getF1_BASEINS()+"</vrRubr>\n" +
             "<indApurIR>0</indApurIR>\n" +
              "</itensRemun>\n";

 if(!"0.0".equals(lista.get(x).getF1_INSS().replaceAll("\\s+",""))){
    
      item3 =  "<itensRemun>\n" +
              "<codRubr>" + Rinss + "</codRubr> \n" +
                "<ideTabRubr>1</ideTabRubr>\n" +
               //  ' colocar               Codigo = Codigo & "<qtdRubr>1</qtdRubr>"
             "<vrRubr>"+lista.get(x).getF1_INSS()+"</vrRubr>\n" +
             "<indApurIR>0</indApurIR>\n" +
              "</itensRemun>\n";

}
    }else{
    
         item4 =  "<itensRemun>\n" +
              "<codRubr>" + RpagTribu + "</codRubr> \n" +
                "<ideTabRubr>1</ideTabRubr>\n" +
               //  ' colocar               Codigo = Codigo & "<qtdRubr>1</qtdRubr>"
             "<vrRubr>" + String.valueOf(df.format(Double.parseDouble(lista.get(x).getE2_VALOR()))).replace(",", ".") + "</vrRubr>\n" +
             "<indApurIR>0</indApurIR>\n" +
              "</itensRemun>\n";
    
    }
       
}else{

  item5 =  "<itensRemun>\n" +
              "<codRubr>" + RpagTribu + "</codRubr> \n" +
                "<ideTabRubr>1</ideTabRubr>\n" +
               //  ' colocar               Codigo = Codigo & "<qtdRubr>1</qtdRubr>"
             "<vrRubr>"+lista.get(x).getF1_BASEINS()+"</vrRubr>\n" +
             "<indApurIR>0</indApurIR>\n" +
              "</itensRemun>\n";
  
  if(!"0.0".equals(lista.get(x).getF1_INSS().replaceAll("\\s+",""))){
  
    item6 =  "<itensRemun>\n" +
              "<codRubr>" + Rinss+ "</codRubr> \n" +
                "<ideTabRubr>1</ideTabRubr>\n" +
               //  ' colocar               Codigo = Codigo & "<qtdRubr>1</qtdRubr>"
             "<vrRubr>"+lista.get(x).getF1_INSS()+"</vrRubr>\n" +
             "<indApurIR>0</indApurIR>\n" +
              "</itensRemun>\n";
  }
  
  
item7 =  "<itensRemun>\n" +
              "<codRubr>" +RautNTributado+ "</codRubr> \n" +
                "<ideTabRubr>1</ideTabRubr>\n" +
               //  ' colocar               Codigo = Codigo & "<qtdRubr>1</qtdRubr>"
             "<vrRubr>"+String.valueOf(df.format((Double.parseDouble(lista.get(x).getF1_VALMERC().replaceAll("\\s+","")) - Double.parseDouble(lista.get(x).getF1_BASEINS().replaceAll("\\s+",""))))).replace(",", ".") +"</vrRubr>\n" +
             "<indApurIR>0</indApurIR>\n" +
              "</itensRemun>\n";
      
  item8 =  "<itensRemun>\n" +
              "<codRubr>" +Rsest+ "</codRubr> \n" +
                "<ideTabRubr>1</ideTabRubr>\n" +
               //  ' colocar               Codigo = Codigo & "<qtdRubr>1</qtdRubr>"
             "<vrRubr>"+String.valueOf(df.format((Double.parseDouble(lista.get(x).getF1_BASEINS().replaceAll("\\s+","")) * 0.015))).replace(",", ".")+"</vrRubr>\n" +
             "<indApurIR>0</indApurIR>\n" +
              "</itensRemun>\n";
  
  item9 =  "<itensRemun>\n" +
              "<codRubr>" +RSenat+ "</codRubr> \n" +
                "<ideTabRubr>1</ideTabRubr>\n" +
               //  ' colocar               Codigo = Codigo & "<qtdRubr>1</qtdRubr>"
             "<vrRubr>"+String.valueOf(df.format((Double.parseDouble(lista.get(x).getF1_VALSEST().replaceAll("\\s+","")) - (Double.parseDouble(lista.get(x).getF1_BASEINS().replaceAll("\\s+","")) * 0.015)))).replace(",", ".")+"</vrRubr>\n" +
             "<indApurIR>0</indApurIR>\n" +
              "</itensRemun>\n";


}

 item10 +=
"<dmDev>\n" +
"<ideDmDev>"+ lista.get(x).getIdeDmDev().replace("/", "").replaceAll("\\s+","") +"</ideDmDev>\n" +        
                //condicional
"<codCateg>"+ codCategoria.replaceAll("\\s+","") +"</codCateg>\n" +
"<infoPerApur>\n" +
"<ideEstabLot>\n" +
"<tpInsc>1</tpInsc>\n" +
"<nrInsc>"+nrInsc.replaceAll("\\s+","")+"</nrInsc>\n" +
"<codLotacao>"+codLotacao.replaceAll("\\s+","")+"</codLotacao>\n" +
"<remunPerApur>\n" + //aqui comeca verificao dos dados
  item1 + item2 + item3 + item4 + item5 + item6 + item7 + item8 + item9 +      
        
        
/*"<itensRemun>\n" +
"<codRubr>"+RpagTribu+"</codRubr>\n" +
"<ideTabRubr>000001</ideTabRubr>\n" +
"<vrRubr>"+dados12.getF1_BASEINS()+"</vrRubr>\n" +
"<indApurIR>0</indApurIR>\n" +
"</itensRemun>\n" +
"<itensRemun>\n" +
"<codRubr>"+Rinss+"</codRubr>\n" +
"<ideTabRubr>000001</ideTabRubr>\n" +
"<vrRubr>"+dados12.getF1_INSS()+"</vrRubr>\n" +
"<indApurIR>0</indApurIR>\n" +
"</itensRemun>\n" +
"<itensRemun>\n" +
"<codRubr>"+RautNTributado+"</codRubr>\n" +
"<ideTabRubr>000001</ideTabRubr>\n" +
"<vrRubr>"+String.valueOf(df.format((Double.parseDouble(dados12.getF1_VALMERC()) - Double.parseDouble(dados12.getF1_BASEINS())))).replace(",", ".") +"</vrRubr>\n" +
"<indApurIR>0</indApurIR>\n" +
"</itensRemun>\n" +
"<itensRemun>\n" +
"<codRubr>"+Rsest+"</codRubr>\n" +
"<ideTabRubr>000001</ideTabRubr>\n" +
"<vrRubr>" + String.valueOf(df.format((Double.parseDouble(dados12.getF1_BASEINS()) * 0.015))).replace(",", ".") +"</vrRubr>\n" +
"<indApurIR>0</indApurIR>\n" +
"</itensRemun>\n" +
"<itensRemun>\n" +
"<codRubr>"+RSenat+"</codRubr>\n" +
"<ideTabRubr>000001</ideTabRubr>\n" +
"<vrRubr>" + String.valueOf(df.format((Double.parseDouble(dados12.getF1_VALSEST()) - (Double.parseDouble(dados12.getF1_BASEINS()) * 0.015)))).replace(",", ".")+"</vrRubr>\n" +
"<indApurIR>0</indApurIR>\n" +
"</itensRemun>\n" +*/
"</remunPerApur>\n" +
"</ideEstabLot>\n" +
"</infoPerApur>\n" +
"<infoComplCont>\n" +
"<codCBO>"+lista.get(x).getCBO().replaceAll("\\s+","")+"</codCBO>\n" +
"</infoComplCont>\n" +
"</dmDev>\n";
    


    

 
    
 

int y = x +1;

if(y < lista.size()){


    if(lista.get(x).getE2_FORNECE().toString().equals(lista.get(y).getE2_FORNECE().toString())){
        
        forx = y;
        
        if(lista.get(y).getCBO().replaceAll("\\s+","").contains("782310")){
    codCategoria = "712";
    
    }else if(lista.get(y).getCBO().replaceAll("\\s+","").contains("715615") || lista.get(y).getCBO().replaceAll("\\s+","").contains("914410") || lista.get(y).getCBO().replaceAll("\\s+","").contains("914405")){
    
        codCategoria = "741";
    
    }else if(lista.get(y).getCBO().replaceAll("\\s+","").contains("782315")){
    
        codCategoria = "711";
    
    }else{
    
    codCategoria = "701";
    
    }
    
    if("00".equals(lista.get(y).getF1_FILIAL().replaceAll("\\s+",""))){
     nrInsc = "00350387000106";
     codLotacao = "01.01";
    }else{   
      nrInsc = "00350387000459";
     codLotacao = "01.04"; 
    }
    
if("741".equals(codCategoria.replaceAll("\\s+",""))){
    
       item1 =  "<itensRemun>\n" +
              "<codRubr>" + "8351" + "</codRubr> \n" +
                "<ideTabRubr>1</ideTabRubr>\n" +
               //  ' colocar               Codigo = Codigo & "<qtdRubr>1</qtdRubr>"
             "<vrRubr>"+String.valueOf(df.format(Double.parseDouble(lista.get(y).getE2_VALOR()))).replace(",", ".")+"</vrRubr>\n" +
             "<indApurIR>0</indApurIR>\n" +
              "</itensRemun>";
    

}else if("701".equals(codCategoria.replaceAll("\\s+",""))){

    
    if(214420 == (Integer.parseInt(lista.get(y).getCBO().replaceAll("\\s+",""))) || 223208 == (Integer.parseInt(lista.get(y).getCBO().replaceAll("\\s+","")))){
    item2 =  "<itensRemun>\n" +
              "<codRubr>" + RpagTribu + "</codRubr> \n" +
                "<ideTabRubr>1</ideTabRubr>\n" +
               //  ' colocar               Codigo = Codigo & "<qtdRubr>1</qtdRubr>"
             "<vrRubr>"+lista.get(y).getF1_BASEINS()+"</vrRubr>\n" +
             "<indApurIR>0</indApurIR>\n" +
              "</itensRemun>\n";

 if(!"0.0".equals(lista.get(y).getF1_INSS().replaceAll("\\s+",""))){
    
      item3 =  "<itensRemun>\n" +
              "<codRubr>" + Rinss + "</codRubr> \n" +
                "<ideTabRubr>1</ideTabRubr>\n" +
               //  ' colocar               Codigo = Codigo & "<qtdRubr>1</qtdRubr>"
             "<vrRubr>"+lista.get(y).getF1_INSS()+"</vrRubr>\n" +
             "<indApurIR>0</indApurIR>\n" +
              "</itensRemun>\n";

}
    }else{
    
         item4 =  "<itensRemun>\n" +
              "<codRubr>" + RpagTribu + "</codRubr> \n" +
                "<ideTabRubr>1</ideTabRubr>\n" +
               //  ' colocar               Codigo = Codigo & "<qtdRubr>1</qtdRubr>"
             "<vrRubr>" + String.valueOf(df.format(Double.parseDouble(lista.get(y).getE2_VALOR()))).replace(",", ".") + "</vrRubr>\n" +
             "<indApurIR>0</indApurIR>\n" +
              "</itensRemun>\n";
    
    }
       
}else{

  item5 =  "<itensRemun>\n" +
              "<codRubr>" + RpagTribu + "</codRubr> \n" +
                "<ideTabRubr>1</ideTabRubr>\n" +
               //  ' colocar               Codigo = Codigo & "<qtdRubr>1</qtdRubr>"
             "<vrRubr>"+lista.get(y).getF1_BASEINS()+"</vrRubr>\n" +
             "<indApurIR>0</indApurIR>\n" +
              "</itensRemun>\n";
  
  if(!"0.0".equals(lista.get(y).getF1_INSS().replaceAll("\\s+",""))){
  
    item6 =  "<itensRemun>\n" +
              "<codRubr>" + Rinss+ "</codRubr> \n" +
                "<ideTabRubr>1</ideTabRubr>\n" +
               //  ' colocar               Codigo = Codigo & "<qtdRubr>1</qtdRubr>"
             "<vrRubr>"+lista.get(y).getF1_INSS()+"</vrRubr>\n" +
             "<indApurIR>0</indApurIR>\n" +
              "</itensRemun>\n";
  }
  
  
item7 =  "<itensRemun>\n" +
              "<codRubr>" +RautNTributado+ "</codRubr> \n" +
                "<ideTabRubr>1</ideTabRubr>\n" +
               //  ' colocar               Codigo = Codigo & "<qtdRubr>1</qtdRubr>"
             "<vrRubr>"+String.valueOf(df.format((Double.parseDouble(lista.get(y).getF1_VALMERC().replaceAll("\\s+","")) - Double.parseDouble(lista.get(y).getF1_BASEINS().replaceAll("\\s+",""))))).replace(",", ".") +"</vrRubr>\n" +
             "<indApurIR>0</indApurIR>\n" +
              "</itensRemun>\n";
      
  item8 =  "<itensRemun>\n" +
              "<codRubr>" +Rsest+ "</codRubr> \n" +
                "<ideTabRubr>1</ideTabRubr>\n" +
               //  ' colocar               Codigo = Codigo & "<qtdRubr>1</qtdRubr>"
             "<vrRubr>"+String.valueOf(df.format((Double.parseDouble(lista.get(y).getF1_BASEINS().replaceAll("\\s+","")) * 0.015))).replace(",", ".")+"</vrRubr>\n" +
             "<indApurIR>0</indApurIR>\n" +
              "</itensRemun>\n";
  
  item9 =  "<itensRemun>\n" +
              "<codRubr>" +RSenat+ "</codRubr> \n" +
                "<ideTabRubr>1</ideTabRubr>\n" +
               //  ' colocar               Codigo = Codigo & "<qtdRubr>1</qtdRubr>"
             "<vrRubr>"+String.valueOf(df.format((Double.parseDouble(lista.get(y).getF1_VALSEST().replaceAll("\\s+","")) - (Double.parseDouble(lista.get(y).getF1_BASEINS().replaceAll("\\s+","")) * 0.015)))).replace(",", ".")+"</vrRubr>\n" +
             "<indApurIR>0</indApurIR>\n" +
              "</itensRemun>\n";


}  
        
        item10 +=
"<dmDev>\n" +
"<ideDmDev>"+ lista.get(y).getIdeDmDev().replace("/", "").replaceAll("\\s+","") +"</ideDmDev>\n" +        
                //condicional
"<codCateg>"+ codCategoria.replaceAll("\\s+","") +"</codCateg>\n" +
"<infoPerApur>\n" +
"<ideEstabLot>\n" +
"<tpInsc>1</tpInsc>\n" +
"<nrInsc>"+nrInsc.replaceAll("\\s+","")+"</nrInsc>\n" +
"<codLotacao>"+codLotacao.replaceAll("\\s+","")+"</codLotacao>\n" +
"<remunPerApur>\n" + //aqui comeca verificao dos dados
  item1 + item2 + item3 + item4 + item5 + item6 + item7 + item8 + item9 +      
        
        
/*"<itensRemun>\n" +
"<codRubr>"+RpagTribu+"</codRubr>\n" +
"<ideTabRubr>000001</ideTabRubr>\n" +
"<vrRubr>"+dados12.getF1_BASEINS()+"</vrRubr>\n" +
"<indApurIR>0</indApurIR>\n" +
"</itensRemun>\n" +
"<itensRemun>\n" +
"<codRubr>"+Rinss+"</codRubr>\n" +
"<ideTabRubr>000001</ideTabRubr>\n" +
"<vrRubr>"+dados12.getF1_INSS()+"</vrRubr>\n" +
"<indApurIR>0</indApurIR>\n" +
"</itensRemun>\n" +
"<itensRemun>\n" +
"<codRubr>"+RautNTributado+"</codRubr>\n" +
"<ideTabRubr>000001</ideTabRubr>\n" +
"<vrRubr>"+String.valueOf(df.format((Double.parseDouble(dados12.getF1_VALMERC()) - Double.parseDouble(dados12.getF1_BASEINS())))).replace(",", ".") +"</vrRubr>\n" +
"<indApurIR>0</indApurIR>\n" +
"</itensRemun>\n" +
"<itensRemun>\n" +
"<codRubr>"+Rsest+"</codRubr>\n" +
"<ideTabRubr>000001</ideTabRubr>\n" +
"<vrRubr>" + String.valueOf(df.format((Double.parseDouble(dados12.getF1_BASEINS()) * 0.015))).replace(",", ".") +"</vrRubr>\n" +
"<indApurIR>0</indApurIR>\n" +
"</itensRemun>\n" +
"<itensRemun>\n" +
"<codRubr>"+RSenat+"</codRubr>\n" +
"<ideTabRubr>000001</ideTabRubr>\n" +
"<vrRubr>" + String.valueOf(df.format((Double.parseDouble(dados12.getF1_VALSEST()) - (Double.parseDouble(dados12.getF1_BASEINS()) * 0.015)))).replace(",", ".")+"</vrRubr>\n" +
"<indApurIR>0</indApurIR>\n" +
"</itensRemun>\n" +*/
"</remunPerApur>\n" +
"</ideEstabLot>\n" +
"</infoPerApur>\n" +
"<infoComplCont>\n" +
"<codCBO>"+lista.get(y).getCBO().replaceAll("\\s+","")+"</codCBO>\n" +
"</infoComplCont>\n" +
"</dmDev>\n";
        
    
    
    }
    
}





 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
 
arquivo = "<eSocial xmlns=\"http://www.esocial.gov.br/schema/evt/evtRemun/v_S_01_00_00\">\n" +
"<evtRemun Id=\"ID144519748000000" + dtf.format(LocalDateTime.now()).replace("/", "").replace(":", "").replaceAll("\\s+","") + numero + "2022" + "\">\n" +
"<ideEvento>\n" +
"<indRetif>" + IndicadorRetificacao.replaceAll("\\s+","") + "</indRetif>\n" +
"<indApuracao>1</indApuracao>\n" +
"<perApur>"+ lista.get(x).getPERAPUR().replaceAll("\\s+","") +"</perApur>\n" +
"<tpAmb>"+ TipoAmbiente.replaceAll("\\s+","") +"</tpAmb>\n" +
"<procEmi>1</procEmi>\n" +
"<verProc>1.0</verProc>\n" +
"</ideEvento>\n" +
"<ideEmpregador>\n" +
"<tpInsc>1</tpInsc>\n" +
"<nrInsc>00350387</nrInsc>\n" +
"</ideEmpregador>\n" +
"<ideTrabalhador>\n" +
"<cpfTrab>"+lista.get(x).getC9V_CPF().replaceAll("\\s+","")+"</cpfTrab>\n" +
"<infoComplem>\n" +
"<nmTrab>"+ lista.get(x).getA2_NOME().trim() +"</nmTrab>\n" +
"<dtNascto>"+ lista.get(x).getDT_NASC().replaceAll("\\s+","") +"</dtNascto>\n" +
"</infoComplem>\n" +
"</ideTrabalhador>\n" +
item10 +
"</evtRemun>\n" +
"</eSocial>";

    
      if(forx == x){
         
         
         }else{
      
          FileWriter arq = new FileWriter("C:\\Totvs\\e-Social\\S-1200\\s-1200-" + dtf.format(LocalDateTime.now()).replace("/", "-").replace(":", "-").replaceAll("\\s+","")  + numero +" " + lista.get(x).getA2_NOME()  + ".xml".trim().replaceAll("\\s+",""));
        PrintWriter gravarArq = new PrintWriter(arq);
        
        gravarArq.printf(arquivo.replace("null", ""));

        arq.close();
        
        item1 = "";
         item2 = "";
          item3 = "";
           item4 = "";
            item5 = "";
             item6 = "";
              item7 = "";
               item8 = "";
                item9 = "";
                item10 = "";
   
      
      }
     

   

 
   
   

   
           
             
    
   
   
    
       
    
    

          

      //  JOptionPane.showMessageDialog(null, "Arquivo gerado com sucesso!");
    
         
         
     }
         
     }
        
    
    
    
    }
    
    public void gerarxml1210(Dadoss1210 dados1210, String IndicadorRetificacao, String TipoAmbiente) throws IOException{
     Random random = new Random();
     int numero = random.nextInt(9);
           verificarPasta();
     DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
     
   // JOptionPane.showMessageDialog(null, "" + dtf.format(LocalDateTime.now()).replace("/", "-").replace(":", "-"));
    
       FileWriter arq = new FileWriter("C:\\Totvs\\e-Social\\S-1210\\s-1210-" +  dtf.format(LocalDateTime.now()).replace("/", "-").replace(":", "-").replaceAll("\\s+","") + numero + " " + dados1210.getE2_NOMFOR() + ".xml".trim().replaceAll("\\s+",""));
        PrintWriter gravarArq = new PrintWriter(arq);

        gravarArq.printf("<eSocial xmlns=\"http://www.esocial.gov.br/schema/evt/evtPgtos/v_S_01_00_00\">\n" +
"<evtPgtos Id=\"ID100350387000000" + dtf.format(LocalDateTime.now()).replace("/", "").replace(":", "").replaceAll("\\s+","") + numero + "2022" + "\">\n" +
"<ideEvento>\n" +
"<indRetif>1</indRetif>\n" +
//"<indApuracao>1</indApuracao>\n" +
"<perApur>"+dados1210.getPERIODO().replaceAll("\\s+","")+"</perApur>\n" +
"<tpAmb>1</tpAmb>\n" +
"<procEmi>1</procEmi>\n" +
"<verProc>1.0</verProc>\n" +
"</ideEvento>\n" +
"<ideEmpregador>\n" +
"<tpInsc>1</tpInsc>\n" +
"<nrInsc>00350387</nrInsc>\n" +
"</ideEmpregador>\n" +
"<ideBenef>\n" +
"<cpfBenef>"+dados1210.getC9V_CPF().replaceAll("\\s+","") +"</cpfBenef>\n" +
"<infoPgto>\n" +
"<dtPgto>"+dados1210.getDT_BAIXA().replaceAll("\\s+","") +"</dtPgto>\n" +
"<tpPgto>1</tpPgto>\n" +
//"<indResBr>S</indResBr>\n" +
//"<detPgtoFl>\n" +
"<perRef>"+dados1210.getEMISSAO().replaceAll("\\s+","").substring(0,7)+"</perRef>\n" +
"<ideDmDev>"+dados1210.getIdeDmDev().replace("/", "").replaceAll("\\s+","")+"</ideDmDev>\n" +
//"<indPgtoTt>S</indPgtoTt>\n" +
"<vrLiq>"+dados1210.getE5_VALOR().replaceAll("\\s+","")+"</vrLiq>\n" +
//"</detPgtoFl>\n" +
"</infoPgto>\n" +
"</ideBenef>\n" +
"</evtPgtos>\n" +
"</eSocial>");

        arq.close();

        
    
    
    
    }
    
    
    
    
    
}


