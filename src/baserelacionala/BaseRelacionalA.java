package baserelacionala;

import java.beans.Statement;
import java.sql.*;

/**
 *
 * @author oracle
 */
public class BaseRelacionalA {
    
    String user = "hr";
    String password = "hr";
    String driver = "jdbc:oracle:thin:";
    String host = "localhost.localdomain"; //String host = "1982.168.1.14";
    String porto = "1521";
    String sid = "orcl"; 
    String url = driver + user + "/" + password + "@" + host + ":" +porto + ":" + sid;
    Connection conn;
    Statement st;
    
    public Connection conexion() {
        try{
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection(url);
        } catch (Exception e) {}
        if(conn!=null){
            System.out.println("Conexion exitosa");
        }else{
            System.out.println("Conexion fallida");
        }
        return conn;
    }
    
    public void crearTabla(){
        
    }
    
    public void insireProduto (String codigo, String descricion, int prezo) {
        try{
        String insert = "INSERT INTO produtos (codigo,descricion,prezo) VALUES(?,?,?)";
        PreparedStatement ps = conn.prepareStatement(insert);
        ps.setString(1, codigo);
        ps.setString(2, descricion);
        ps.setInt(3, prezo);
        ps.executeQuery();
        
        }catch (SQLException ex){}
    }
    
    public void listaProduto(){
        try {
            PreparedStatement st = conn.prepareStatement("SELECT * FROM produtos");
            ResultSet rs = st.executeQuery();
          
            while (rs.next()) {
                System.out.println("Codigo: " + rs.getString(1) + "\n" +
                                   "Descricion: " + rs.getString(2) + "\n" +
                                   "Prezo: " + rs.getInt(3));
            }
        } catch (SQLException ex) {}
    }
    
    public void actualizaPre(String codigo, int prezo){
        String update =  "UPDATE produtos SET prezo =" 
                + prezo + "WHERE codigo =" + codigo;
        
        
        
    }
    
    public void eliminaTaboa (){
        
    }
}
