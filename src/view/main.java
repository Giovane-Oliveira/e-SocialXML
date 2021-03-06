/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import jdbc.ConfigSQlite;
import dao.ReqDAO;
import java.awt.Color;
import java.awt.Toolkit;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.Dadoss12;
import model.Dadoss1210;

/**
 *
 * @author giovane.oliveira
 */
public class main extends javax.swing.JFrame {

    static boolean checkbox12, checkbox1210;
    static List listaSelected = new ArrayList();
    static List listaSelected2 = new ArrayList();

    /**
     * Creates new form main
     */
    public main() throws SQLException {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/logo.png")));
        new ConfigSQlite();
        String resultado = new ConfigSQlite().buscarConfig("1");
        String resultado2 = new ConfigSQlite().buscarConfig("2");
        if (resultado.equals("teste")) {

            sqldb.setText("SELECT E2_FORNECE, E2_NOMFOR, E2_VALOR, F1_DOC, F1_VALMERC, F1_BASEINS, F1_VALSEST, F1_INSS, E2_EMISSAO, E2_VENCREA, C9V_ID, E2_FORNECE+F1_DOC ideDmDev, F1_FILIAL, C9V_NIS, C9V_CPF, SUBSTRING(E2_EMISSAO,1,4)+'-'+SUBSTRING(E2_EMISSAO,5,2) AS PERAPUR, SUBSTRING(C9V_DTNASC,1,4)+'-'+SUBSTRING(C9V_DTNASC,5,2)+'-'+SUBSTRING(C9V_DTNASC,7,2) DT_NASC,\n"
                    + "CASE E2_FORNECE WHEN '201793' THEN '715615'\n"
                    + "WHEN '202066' THEN '715615'\n"
                    + "WHEN '201827' THEN '914410'\n"
                    + "WHEN '202304' THEN '914410'\n"
                    + "WHEN '201709' THEN '914405'\n"
                    + "ELSE A2.A2_CBO\n"
                    + "END CBO,\n"
                    + "SUBSTRING(A2.A2_DTNASC,1,4)+'-'+SUBSTRING(A2.A2_DTNASC,5,2)+'-'+SUBSTRING(A2.A2_DTNASC,7,2) DT_NASC_A2,\n"
                    + "A2.A2_CODINSS PIS_A2,\n"
                    + "A2.A2_CGC CPF_A2,\n"
                    + "A2.A2_NOME\n"
                    + "FROM SE2010\n"
                    + "LEFT OUTER JOIN SF1010 ON F1_FILIAL = E2_FILIAL AND F1_FORNECE = E2_FORNECE AND F1_LOJA = E2_LOJA AND E2_NUM = F1_DOC AND SF1010.D_E_L_E_T_ = ''\n"
                    + "LEFT JOIN SA2010 A2 ON E2_FORNECE=A2.A2_COD AND E2_LOJA=A2.A2_LOJA AND A2.D_E_L_E_T_ = ''\n"
                    + "LEFT OUTER JOIN C9V010 ON (C9V_CPF = CASE E2_FORNECE\n"
                    + "WHEN '201827' THEN '01411974042'\n"
                    + "WHEN '201793' THEN '29959446034'\n"
                    + "WHEN '202066' THEN '37697170078'\n"
                    + "WHEN '202304' THEN '10873237978'\n"
                    + "WHEN '201709' THEN '05908963933'\n"
                    + "ELSE A2_CGC END) AND C9V010.D_E_L_E_T_ = '' AND C9V_NOMEVE = 'TAUTO' WHERE E2_TIPO='NF'\n"
                    + "AND E2_NATUREZ in('703','705','720','721','722','725','726')\n"
                    + "--AND E2_PREFIXO LIKE '%U'\n"
                    + "AND SE2010.D_E_L_E_T_ = ''\n"
                    + "AND ( A2.A2_CODINSS <> '' OR A2.A2_CODNIT <> '' )\n"
                    + "AND E2_EMISSAO BETWEEN ? AND ?\n"
                    + "AND E2_FORNECE NOT IN('202118','202072')\n"
                    + "ORDER BY 1");

        }

        if (resultado2.equals("teste")) {

            sqldb1.setText("SELECT SUBSTRING(E5_DATA,1,4)+'-'+SUBSTRING(E5_DATA,5,2) PERIODO, C9V_CPF, SUBSTRING(E5_DATA ,1,4)+'-'+SUBSTRING(E5_DATA ,5,2)+'-'+SUBSTRING(E5_DATA ,7,2) DT_BAIXA ,E5_VALOR, SUBSTRING(E2_EMISSAO ,1,4)+'-'+SUBSTRING(E2_EMISSAO ,5,2)+'-'+SUBSTRING(E2_EMISSAO ,7,2) EMISSAO ,F1_INSS,E2_FORNECE, E2_NOMFOR,F1_DOC NUMDOC, E2_FORNECE+F1_DOC ideDmDev\n"
                    + "FROM SE5010 INNER JOIN SE2010 ON E2_NUM = E5_NUMERO AND E2_FORNECE = E5_CLIFOR AND E2_LOJA = E5_LOJA AND SE2010.D_E_L_E_T_ = ''\n"
                    + "LEFT OUTER JOIN SF1010 ON F1_FILIAL = E2_FILIAL AND F1_FORNECE = E2_FORNECE AND F1_LOJA = E2_LOJA AND E2_NUM = F1_DOC AND SF1010.D_E_L_E_T_ = ''\n"
                    + "LEFT OUTER JOIN SA2010 ON A2_COD = F1_FORNECE AND A2_LOJA = F1_LOJA AND SA2010.D_E_L_E_T_ = ''\n"
                    + "LEFT OUTER JOIN C9V010 ON (C9V_CPF = CASE E2_FORNECE WHEN '201827' THEN '01411974042'\n"
                    + "WHEN '201793' THEN '29959446034'\n"
                    + "WHEN '202066' THEN '37697170078'\n"
                    + "WHEN '202304' THEN '10873237978'\n"
                    + "WHEN '201709' THEN '05908963933'\n"
                    + "ELSE A2_CGC END) AND C9V010.D_E_L_E_T_ = '' AND C9V_NOMEVE = 'TAUTO' WHERE E2_TIPO='NF'\n"
                    + "AND E5_NATUREZ in ('703','705','720','721','722','725','726')\n"
                    + "AND E5_DATA BETWEEN ? AND ?\n"
                    + "--AND E5_PREFIXO LIKE '%U'\n"
                    + "AND SE5010.D_E_L_E_T_ = ''\n"
                    + "AND (A2_CODINSS <> '' OR A2_CODNIT <> '')\n"
                    + "AND E2_FORNECE NOT IN('202118','202072')\n"
                    + "-- AND A2_CBO IN ('622010','514320','782310','715615','252215')\n"
                    + "ORDER BY 1,7;");

        } else {

            sqldb.setText(new ConfigSQlite().buscarConfig("1"));
            sqldb1.setText(new ConfigSQlite().buscarConfig("2"));

        }

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(Color.LIGHT_GRAY);

        for (int i = 0; i < TabelaS12.getModel().getColumnCount(); i++) {
            TabelaS12.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }

        for (int i = 0; i < tabela1210.getModel().getColumnCount(); i++) {
            tabela1210.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        dataInicial = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        dataFinal = new javax.swing.JFormattedTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        te2valor = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        tf1valmerc = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        tf1baseins = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        tf1inss = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        tf1valsest = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabelaS12 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        te5valor = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        tf1inss2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabela1210 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        dataInicial2 = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        dataFinal2 = new javax.swing.JFormattedTextField();
        Gerarxml2 = new javax.swing.JButton();
        Consultar2 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        sqldb = new javax.swing.JTextArea();
        jButton3 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        sqldb1 = new javax.swing.JTextArea();
        btncon1210 = new javax.swing.JButton();
        panel1 = new java.awt.Panel();
        jLabel16 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setText("XML e-Social vers??o S-1.0 (Aut??nomos)");

        jLabel2.setText("Data Inicial");

        try {
            dataInicial.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel3.setText("Data Final");

        try {
            dataFinal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jButton1.setText("Consultar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Gerar XML");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel6.setText("M??DIA:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("E2_VALOR:");

        te2valor.setText("00.00");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("F1_VALMERC:");

        tf1valmerc.setText("00.00");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("F1_BASEINS:");

        tf1baseins.setText("00.00");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("F1_INSS:");

        tf1inss.setText("00.00");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("F1_VALSEST:");

        tf1valsest.setText("00.00");

        TabelaS12.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "?", "E2_FORNECE", "E2_NOMFOR", "E2_VALOR", "F1_DOC", "F1_VALMERC", "F1_BASEINS", "F1_VALSEST", "FI_INSS", "E2_EMISSAO", "E2_VENCREA", "C9V_ID", "FI_FILIAL", "ideDmDev", "C9V_NIS", "C9V_CPF", "PERAPUR", "DT_NASC", "CBO", "DT_NASC_A2", "PIS_A2", "CPF_A2", "A2_NOME"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TabelaS12.getTableHeader().setReorderingAllowed(false);
        TabelaS12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelaS12MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TabelaS12);
        if (TabelaS12.getColumnModel().getColumnCount() > 0) {
            TabelaS12.getColumnModel().getColumn(1).setPreferredWidth(100);
            TabelaS12.getColumnModel().getColumn(2).setPreferredWidth(100);
            TabelaS12.getColumnModel().getColumn(3).setPreferredWidth(100);
            TabelaS12.getColumnModel().getColumn(4).setPreferredWidth(100);
            TabelaS12.getColumnModel().getColumn(5).setPreferredWidth(100);
            TabelaS12.getColumnModel().getColumn(6).setPreferredWidth(100);
            TabelaS12.getColumnModel().getColumn(7).setPreferredWidth(100);
            TabelaS12.getColumnModel().getColumn(8).setPreferredWidth(100);
            TabelaS12.getColumnModel().getColumn(9).setPreferredWidth(100);
            TabelaS12.getColumnModel().getColumn(10).setPreferredWidth(100);
            TabelaS12.getColumnModel().getColumn(11).setPreferredWidth(100);
            TabelaS12.getColumnModel().getColumn(13).setPreferredWidth(100);
            TabelaS12.getColumnModel().getColumn(14).setPreferredWidth(100);
            TabelaS12.getColumnModel().getColumn(15).setPreferredWidth(100);
            TabelaS12.getColumnModel().getColumn(16).setPreferredWidth(100);
            TabelaS12.getColumnModel().getColumn(17).setPreferredWidth(100);
            TabelaS12.getColumnModel().getColumn(18).setPreferredWidth(100);
            TabelaS12.getColumnModel().getColumn(19).setPreferredWidth(100);
            TabelaS12.getColumnModel().getColumn(20).setPreferredWidth(100);
            TabelaS12.getColumnModel().getColumn(21).setPreferredWidth(100);
            TabelaS12.getColumnModel().getColumn(22).setPreferredWidth(200);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 95, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(te2valor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tf1valmerc)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel11))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton1)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton2))))
                        .addGap(4, 4, 4)
                        .addComponent(tf1baseins)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf1inss)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf1valsest)))
                .addContainerGap(659, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(dataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(dataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(26, 26, 26)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(te2valor)
                    .addComponent(jLabel9)
                    .addComponent(tf1valmerc)
                    .addComponent(jLabel11)
                    .addComponent(tf1baseins)
                    .addComponent(jLabel13)
                    .addComponent(tf1inss)
                    .addComponent(jLabel15)
                    .addComponent(tf1valsest))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("S-1200", jPanel3);

        jLabel8.setText("M??DIA:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("E5_VALOR:");

        te5valor.setText("00.00");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("F1_INSS:");

        tf1inss2.setText("00.00");

        tabela1210.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "?", "PERIODO", "C9V_CPF", "DT_BAIXA", "E5_VALOR", "EMISSAO", "F1_INSS", "E2_FORNECE", "E2_NOMFOR", "NUMDOC", "ideDmDev"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabela1210.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabela1210MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabela1210);
        if (tabela1210.getColumnModel().getColumnCount() > 0) {
            tabela1210.getColumnModel().getColumn(1).setPreferredWidth(100);
            tabela1210.getColumnModel().getColumn(2).setPreferredWidth(100);
            tabela1210.getColumnModel().getColumn(3).setPreferredWidth(100);
            tabela1210.getColumnModel().getColumn(4).setPreferredWidth(100);
            tabela1210.getColumnModel().getColumn(5).setPreferredWidth(100);
            tabela1210.getColumnModel().getColumn(6).setPreferredWidth(100);
            tabela1210.getColumnModel().getColumn(7).setPreferredWidth(100);
            tabela1210.getColumnModel().getColumn(8).setPreferredWidth(200);
            tabela1210.getColumnModel().getColumn(9).setPreferredWidth(100);
            tabela1210.getColumnModel().getColumn(10).setPreferredWidth(200);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        jLabel4.setText("Data Inicial");

        try {
            dataInicial2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel5.setText("Data Final");

        try {
            dataFinal2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        Gerarxml2.setText("Gerar XML");
        Gerarxml2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Gerarxml2ActionPerformed(evt);
            }
        });

        Consultar2.setText("Consultar");
        Consultar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Consultar2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(Consultar2)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dataInicial2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(10, 10, 10)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dataFinal2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Gerarxml2)))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(te5valor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf1inss2)))
                .addContainerGap(880, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(dataInicial2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(dataFinal2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Gerarxml2)
                    .addComponent(Consultar2))
                .addGap(35, 35, 35)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(te5valor)
                    .addComponent(jLabel14)
                    .addComponent(tf1inss2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("S-1210", jPanel4);

        sqldb.setColumns(20);
        sqldb.setRows(5);
        jScrollPane3.setViewportView(sqldb);

        jButton3.setText("Atualizar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel12.setText("SQL 1200");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1228, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addContainerGap(98, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Config S-1200", jPanel5);

        jLabel17.setText("SQL 1210");

        sqldb1.setColumns(20);
        sqldb1.setRows(5);
        jScrollPane4.setViewportView(sqldb1);

        btncon1210.setText("Atualizar");
        btncon1210.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncon1210ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1228, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btncon1210)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btncon1210)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Config S-1210", jPanel6);

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo2.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addGap(505, 505, 505))
            .addComponent(jTabbedPane1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Double e2valor = 0.0, f1valmerc = 0.0, f1baseins = 0.0, f1inss = 0.0, e5valor = 0.0, f1inss2 = 0.0, f1valsest = 0.0; //totalizadores
        int count = 0;
        DecimalFormat df = new DecimalFormat("0.00");

        if (dataInicial.getText().replace("/", "").trim().isEmpty() || dataFinal.getText().replace("/", "").trim().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Informe a data inicial e final");

        } else {

            ReqDAO dao = new ReqDAO();
            List<Dadoss12> lista = dao.consultarDadosS12(dataInicial.getText(), dataFinal.getText());

            for (int x = 0; x < lista.size(); x++) {

                if (lista.get(x).getC9V_ID() == null) {

                    lista.get(x).setMarcado(false);

                }

            }

            DefaultTableModel dadoss12 = (DefaultTableModel) TabelaS12.getModel();
            TabelaS12.setAutoResizeMode(TabelaS12.AUTO_RESIZE_OFF);
            dadoss12.setNumRows(0);
            for (Dadoss12 c : lista) {

                e2valor = e2valor + Double.parseDouble(c.getE2_VALOR().trim());
                f1valmerc = f1valmerc + Double.parseDouble(c.getF1_VALMERC());
                f1baseins = f1baseins + Double.parseDouble(c.getF1_BASEINS().trim());
                f1inss = f1inss + Double.parseDouble(c.getF1_INSS().trim());
                f1valsest = f1valsest + Double.parseDouble(c.getF1_VALSEST().trim());
                count = count + 1;
                dadoss12.addRow(new Object[]{
                    c.getMarcado(),
                    c.getE2_FORNECE(),
                    c.getE2_NOMFOR(),
                    c.getE2_VALOR().replace('.', ','),
                    c.getF1_DOC(),
                    c.getF1_VALMERC().replace('.', ','),
                    c.getF1_BASEINS().replace('.', ','),
                    c.getF1_VALSEST().replace('.', ','),
                    c.getF1_INSS().replace('.', ','),
                    c.getE2_EMISSAO(),
                    c.getE2_VENCREA(),
                    c.getC9V_ID(),
                    c.getF1_FILIAL(),
                    c.getIdeDmDev(),
                    c.getC9_NIS(),
                    c.getC9V_CPF(),
                    c.getPERAPUR(),
                    c.getDT_NASC(),
                    c.getCBO(),
                    c.getDT_NASC_A2(),
                    c.getPIS_A2(),
                    c.getCPF_A2(),
                    c.getA2_NOME()
                });
            }

            te2valor.setText("" + String.valueOf(df.format(e2valor / count)).replace('.', ','));
            tf1valmerc.setText("" + String.valueOf(df.format(f1valmerc / count)).replace('.', ','));
            tf1baseins.setText("" + String.valueOf(df.format(f1baseins / count)).replace('.', ','));
            tf1inss.setText("" + String.valueOf(df.format(f1inss / count)).replace('.', ','));
            tf1valsest.setText("" + String.valueOf(df.format(f1valsest / count)).replace('.', ','));

        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int n = 1;
        String TipoAmbiente;
        String IndicadorRetificacao;
        if (dataInicial.getText().replace("/", "").trim().isEmpty() || dataFinal.getText().replace("/", "").trim().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Informe a data inicial e final");
            n = 0;

        } else {

            ReqDAO obj = new ReqDAO();
            List<Dadoss12> lista = obj.consultarDadosS12(dataInicial.getText(), dataFinal.getText());

            do {

                IndicadorRetificacao = JOptionPane.showInputDialog("Informe 1 para arquivo original ou 2 para arquivo de retifica????o", "1");
                TipoAmbiente = JOptionPane.showInputDialog("	Identifica????o do ambiente.\n"
                        + "Valores v??lidos:\n"
                        + "1 - Produ????o\n"
                        + "2 - Produ????o restrita\n"
                        + "7 - Valida????o (uso interno)\n"
                        + "8 - Teste (uso interno)\n"
                        + "9 - Desenvolvimento (uso interno)", "1");
            } while (Integer.parseInt(TipoAmbiente) < 1 || Integer.parseInt(TipoAmbiente) > 9  || Integer.parseInt(IndicadorRetificacao) != 1 && Integer.parseInt(IndicadorRetificacao) != 2);

            try {
                for (int x = 0; x < lista.size(); x++) {

                    for (int y = 0; y < listaSelected.size(); y++) {

                        if (listaSelected.get(y).toString().equals(lista.get(x).getF1_DOC())) {

                            lista.remove(x);

                        }

                    }

                }

                obj.gerarxml12(lista, IndicadorRetificacao, TipoAmbiente);

                /* for (Dadoss12 c : lista) {
                
                try {
                obj.gerarxml12(c, IndicadorRetificacao, TipoAmbiente);
                
                } catch (IOException ex) {
                n = 0;
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
                }
                }*/
 /*  try {
                obj.gerarxml("teste");
                
                }
                
                
                } catch (IOException ex) {
                JOptionPane.showMessageDialog(null,"Erro: " + ex);
            }*/ } catch (IOException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (n == 1) {

            JOptionPane.showMessageDialog(null, "Arquivo gerado com sucesso");

        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void Gerarxml2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Gerarxml2ActionPerformed
        // TODO add your handling code here:
        int n = 1;
        String TipoAmbiente;
        String IndicadorRetificacao;
        if (dataInicial2.getText().replace("/", "").trim().isEmpty() || dataFinal2.getText().replace("/", "").trim().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Informe a data inicial e final");
            n = 0;

        } else {

            ReqDAO obj = new ReqDAO();
            List<Dadoss1210> lista2 = obj.consultarDadosS1210(dataInicial2.getText(), dataFinal2.getText());

            do {
                IndicadorRetificacao = JOptionPane.showInputDialog("Informe 1 para arquivo original ou 2 para arquivo de retifica????o", "1");
                TipoAmbiente = JOptionPane.showInputDialog("	Identifica????o do ambiente.\n"
                        + "Valores v??lidos:\n"
                        + "1 - Produ????o\n"
                        + "2 - Produ????o restrita\n"
                        + "7 - Valida????o (uso interno)\n"
                        + "8 - Teste (uso interno)\n"
                        + "9 - Desenvolvimento (uso interno)", "1");
            } while (Integer.parseInt(TipoAmbiente) < 1 || Integer.parseInt(TipoAmbiente) > 9  || Integer.parseInt(IndicadorRetificacao) != 1 && Integer.parseInt(IndicadorRetificacao) != 2);

            try {

                for (int x = 0; x < lista2.size(); x++) {

                    for (int y = 0; y < listaSelected2.size(); y++) {

                        if (listaSelected2.get(y).toString().equals(lista2.get(x).getNUMDOC())) {

                            lista2.remove(x);

                        }

                    }

                }

                obj.gerarxml1210(lista2, IndicadorRetificacao, TipoAmbiente);

            } catch (IOException ex) {
                n = 0;
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }

            /*  try {
            obj.gerarxml("teste");
        
        }

       
        } catch (IOException ex) {
          JOptionPane.showMessageDialog(null,"Erro: " + ex);
        }*/
        }

        if (n == 1) {

            JOptionPane.showMessageDialog(null, "Arquivo gerado com sucesso");

        }


    }//GEN-LAST:event_Gerarxml2ActionPerformed

    private void Consultar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Consultar2ActionPerformed
        Double e2valor = 0.0, f1valmerc = 0.0, f1baseins = 0.0, f1inss = 0.0, e5valor = 0.0, f1inss2 = 0.0, f1valsest = 0.0;
        int count = 0; //totalizadores
        DecimalFormat df = new DecimalFormat("0.00");

        if (dataInicial2.getText().replace("/", "").trim().isEmpty() || dataFinal2.getText().replace("/", "").trim().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Informe a data inicial e final");

        } else {

            ReqDAO obj = new ReqDAO();
            List<Dadoss1210> lista2 = obj.consultarDadosS1210(dataInicial2.getText(), dataFinal2.getText());

            for (int x = 0; x < lista2.size(); x++) {

                if (lista2.get(x).getC9V_CPF() == null) {

                    lista2.get(x).setMarcado(false);

                }

            }

            DefaultTableModel dadoss1210 = (DefaultTableModel) tabela1210.getModel();
            tabela1210.setAutoResizeMode(tabela1210.AUTO_RESIZE_OFF);
            dadoss1210.setNumRows(0);
            for (Dadoss1210 c : lista2) {
                count = count + 1;
                e5valor = e5valor + Double.parseDouble(c.getE5_VALOR().trim());
                f1inss2 = f1inss2 + Double.parseDouble(c.getF1_INSS().trim());
                dadoss1210.addRow(new Object[]{
                    c.getMarcado(),
                    c.getPERIODO(),
                    c.getC9V_CPF(),
                    c.getDT_BAIXA(),
                    c.getE5_VALOR().replace('.', ','),
                    c.getEMISSAO(),
                    c.getF1_INSS().replace('.', ','),
                    c.getE2_FORNECE(),
                    c.getE2_NOMFOR(),
                    c.getNUMDOC(),
                    c.getIdeDmDev()

                });
            }
            te5valor.setText("" + String.valueOf(df.format(e5valor / count)).replace('.', ','));
            tf1inss2.setText("" + String.valueOf(df.format(f1inss2 / count)).replace('.', ','));
        }


    }//GEN-LAST:event_Consultar2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:

        if (sqldb.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Preencha o campo");

        } else {

            new ConfigSQlite().alterarConfig(sqldb.getText(), "1");
        }


    }//GEN-LAST:event_jButton3ActionPerformed

    private void btncon1210ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncon1210ActionPerformed
        // TODO add your handling code here:

        if (sqldb1.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Preencha o campo");

        } else {

            new ConfigSQlite().alterarConfig(sqldb1.getText(), "2");
        }

    }//GEN-LAST:event_btncon1210ActionPerformed

    private void TabelaS12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelaS12MouseClicked
        // TODO add your handling code here:
        String checked = TabelaS12.getValueAt(TabelaS12.getSelectedRow(), 0).toString();
        String pessoa = TabelaS12.getValueAt(TabelaS12.getSelectedRow(), 2).toString();
        String doc = TabelaS12.getValueAt(TabelaS12.getSelectedRow(), 4).toString();
        int pos = TabelaS12.getSelectedRow();

        if (TabelaS12.getValueAt(TabelaS12.getSelectedRow(), 11) == null && TabelaS12.getValueAt(TabelaS12.getSelectedRow(), 0).equals(true) ) {

            TabelaS12.setValueAt(false, TabelaS12.getSelectedRow(), 0);
            JOptionPane.showMessageDialog(null, "O documento de " + TabelaS12.getValueAt(TabelaS12.getSelectedRow(), 2).toString()
                    + "est?? bloqueado para marcar. Pois o arquivo xml do mesmo, n??o ser?? gerado por falta de informa????es.");
          

        } else if (TabelaS12.getValueAt(TabelaS12.getSelectedRow(), 0).toString().equals("false") && TabelaS12.getValueAt(TabelaS12.getSelectedRow(), 11) != null) {

            listaSelected.add(TabelaS12.getValueAt(TabelaS12.getSelectedRow(), 4).toString());
            TabelaS12.setValueAt(false, TabelaS12.getSelectedRow(), 0);

            System.out.println("Inserido " + TabelaS12.getValueAt(TabelaS12.getSelectedRow(), 4).toString());

        } else {

            for (int x = 0; x < listaSelected.size(); x++) {

                if (listaSelected.size() == 1) {

                    if (TabelaS12.getValueAt(TabelaS12.getSelectedRow(), 4).toString().equals(listaSelected.get(x).toString())) {

                        System.out.println("Removido " + listaSelected.get(x));
                        listaSelected.clear();

                    }

                } else {

                    if (TabelaS12.getValueAt(TabelaS12.getSelectedRow(), 4).toString().equals(listaSelected.get(x).toString())) {

                        System.out.println("Removido " + listaSelected.get(x));
                        listaSelected.remove(x);

                    }

                }

            }

        }

        //  System.out.println("Aqui " + checked + " " + fornecedor + " " + doc);
    }//GEN-LAST:event_TabelaS12MouseClicked

    private void tabela1210MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabela1210MouseClicked
        // TODO add your handling code here:

        String checked = tabela1210.getValueAt(tabela1210.getSelectedRow(), 0).toString();
        String doc = tabela1210.getValueAt(tabela1210.getSelectedRow(), 9).toString();
        int pos = tabela1210.getSelectedRow();

        if (tabela1210.getValueAt(tabela1210.getSelectedRow(), 2) == null && tabela1210.getValueAt(tabela1210.getSelectedRow(), 0).equals(true)) {

            // System.out.println("CPF:  " + tabela1210.getValueAt(tabela1210.getSelectedRow(), 2));  
            tabela1210.setValueAt(false, tabela1210.getSelectedRow(), 0);
            JOptionPane.showMessageDialog(null, "O documento de " + tabela1210.getValueAt(tabela1210.getSelectedRow(), 8).toString()
                    + "est?? bloqueado para marcar. Pois o arquivo xml do mesmo, n??o ser?? gerado por falta de informa????es.");

        } // System.out.println("dsadad " + TabelaS12.getRowCount());
        else if (tabela1210.getValueAt(tabela1210.getSelectedRow(), 0).toString().equals("false") && tabela1210.getValueAt(tabela1210.getSelectedRow(), 2) != null) {

            listaSelected2.add(tabela1210.getValueAt(tabela1210.getSelectedRow(), 9).toString());
            tabela1210.setValueAt(false, tabela1210.getSelectedRow(), 0);

            System.out.println("Inserido " + tabela1210.getValueAt(tabela1210.getSelectedRow(), 9).toString());

        } else {

            for (int x = 0; x < listaSelected2.size(); x++) {

                if (listaSelected2.size() == 1) {

                    if (tabela1210.getValueAt(tabela1210.getSelectedRow(), 9).toString().equals(listaSelected2.get(x).toString())) {

                        System.out.println("Removido " + listaSelected2.get(x));
                        listaSelected2.clear();

                    }

                } else {

                    if (tabela1210.getValueAt(tabela1210.getSelectedRow(), 9).toString().equals(listaSelected2.get(x).toString())) {

                        System.out.println("Removido " + listaSelected2.get(x));
                        listaSelected2.remove(x);

                    }

                }

            }

        }


    }//GEN-LAST:event_tabela1210MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new main().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Consultar2;
    private javax.swing.JButton Gerarxml2;
    private javax.swing.JTable TabelaS12;
    private javax.swing.JButton btncon1210;
    private javax.swing.JFormattedTextField dataFinal;
    private javax.swing.JFormattedTextField dataFinal2;
    private javax.swing.JFormattedTextField dataInicial;
    private javax.swing.JFormattedTextField dataInicial2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private java.awt.Panel panel1;
    private javax.swing.JTextArea sqldb;
    private javax.swing.JTextArea sqldb1;
    private javax.swing.JTable tabela1210;
    private javax.swing.JLabel te2valor;
    private javax.swing.JLabel te5valor;
    private javax.swing.JLabel tf1baseins;
    private javax.swing.JLabel tf1inss;
    private javax.swing.JLabel tf1inss2;
    private javax.swing.JLabel tf1valmerc;
    private javax.swing.JLabel tf1valsest;
    // End of variables declaration//GEN-END:variables
}
