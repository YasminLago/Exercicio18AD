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

    
    public Connection conexionBD() {
        try{
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection(url);
        } catch (Exception e) {}
        if(conn!=null){
            System.out.println("Abierta la conexión a la BD");
        }else{
            System.out.println("Conexion fallida");
        }
        return conn;
    }
    
    public void crearTabla(){
        try{
        String tabla = "CREATE TABLE produtos (codigo VARCHAR2(3), "
                                               + "PRIMARY KEY(codigo),"
                                               + "descricion VARCHAR(15),"
                                               + "prezo (INTEGER))";
        PreparedStatement stCr = conn.prepareStatement(tabla);
        conn.setAutoCommit(true);
        System.out.println("Taboa creada");
        
        conn.close();
        }catch(Exception e){
            System.out.println("Non se pode crear a taboa");
        }
    }
    
    public void insireProduto (String codigo, String descricion, int prezo) {
        try{
            String insert = "INSERT INTO produtos (codigo,descricion,prezo) VALUES(?,?,?)";
            PreparedStatement ps = conn.prepareStatement(insert);
            ps.setString(1, codigo);
            ps.setString(2, descricion);
            ps.setInt(3, prezo);
            ps.executeUpdate();
            conn.setAutoCommit(true);
        
            System.out.println("Produto inserido");
            
            conn.close();
        }catch (SQLException ex){
            System.out.println("Non se pode inserir o produto");
        }
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
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Non se pode mostrar a taboa produtos");
        }
    }
    
    public void actualizaPre(String codigo, int prezo){
        try{
            String update =  "UPDATE produtos SET prezo =" 
                             + prezo + "WHERE codigo =" + codigo;
            PreparedStatement stUp =  conn.prepareStatement(update);
            
            System.out.println("Taboa modificada");
            conn.setAutoCommit(true);
            conn.close();
        }catch(Exception e){
            System.out.println("Non se pode modificar a taboa");
        }
    }
    
    public void eliminaTaboa (){
        
    }
}
