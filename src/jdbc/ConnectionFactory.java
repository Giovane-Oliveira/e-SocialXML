/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import java.sql.DriverManager;
import java.sql.*;

/**
 *
 * @author giovane.oliveira
 */
public class ConnectionFactory {
   
   public static java.sql.Connection getConnection(){
try{

return DriverManager.getConnection("jdbc:sqlserver://192.168.200.30:1433;databaseName=protheusPRD;integratedSecurity=false;encrypt=true;trustServerCertificate=true;user=siga;password=Totvs2010");


}catch(Exception erro){
throw new RuntimeException(erro);
}
    }

  
} 