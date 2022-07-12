/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import javax.swing.JOptionPane;

/**
 *
 * @author giovane.oliveira
 */
public class TesteConexao {

    public static void main(String[] args) {

        try {

            new ConnectionFactory().getConnection();
            JOptionPane.showMessageDialog(null, "conectado com sucesso");
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro " + e);
        }

    }
}
