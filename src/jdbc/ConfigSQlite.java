package jdbc;


import dao.ReqDAO;
import jdbc.SQLiteJDBCDriverConnection;

import static java.lang.System.exit;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Giovane Oliveira
 */
public class ConfigSQlite {
  private Connection sqlite;  

  
   public ConfigSQlite(){
       
       new ReqDAO().verificarPasta();
    
    this.sqlite = new SQLiteJDBCDriverConnection().getConnection();
 
      try {
           Statement stmt = sqlite.createStatement();;
           stmt.execute("CREATE TABLE IF NOT EXISTS CONFIG(ID INTEGER,SQL TEXT)");
           
           boolean f = verifica();
           
           if(f ==  false){ 
               
                 stmt.execute("INSERT INTO CONFIG(ID, SQL) VALUES (1,'teste');");
                    stmt.execute("INSERT INTO CONFIG(ID, SQL) VALUES (2, 'teste');");
               
        
       //    JOptionPane.showMessageDialog(null, "Configuração do servidor efetuada!\n Clique em ok e inicie o software novamente");
           }
       
           stmt.close();
        
           
          

          
      } catch (SQLException ex) {
          Logger.getLogger(ConfigSQlite.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
   
   public boolean verifica() throws SQLException{
   boolean x;
      // lendo os registros
            PreparedStatement stmt = sqlite.prepareStatement("select * from CONFIG");
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
               /*Integer id = resultSet.getInt("ID");
                String ip = resultSet.getString("IP");
                String base = resultSet.getString("BASE");
                String usuario = resultSet.getString("USUARIO");
                String senha = resultSet.getString("SENHA");
            
               System.out.println( "ID - " + id + "\nIP - " +ip + "\n"
                       + "BASE -" + base + "\nUsuario - " + usuario + "\n senha - " + senha);*/
                x = true;
            }else{
            
                x = false;
                
            }
            
            stmt.close();
   return x;
   
   }
   
   
      public String buscarConfig(String id) throws SQLException{
   
      String sql = "select sql from CONFIG where id=?";
                 PreparedStatement stmt = sqlite.prepareStatement(sql);
                 String resultado = null;
           
            stmt.setString(1, id);
          
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                
              resultado = rs.getString("SQL");
            
            }
            stmt.close();
            
            return resultado;
    
   }
   
  
   public void alterarConfig(String texto, String id) {
        try {

            String sql = "UPDATE CONFIG set SQL=? WHERE ID=?" ; 

            PreparedStatement stmt = sqlite.prepareStatement(sql);
            stmt.setString(1, texto);
            stmt.setString(2, id);

            stmt.executeUpdate();
            stmt.close();
            
            
            JOptionPane.showMessageDialog(null, "Dados alterados com sucesso");
         

           

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "" + e);

        }

    }
    
}
