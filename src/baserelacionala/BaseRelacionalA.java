package baserelacionala;

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

    /**
     * Conexion a BD
     * @return Retorna a conexion
     */
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
    
    /**
     * Crea a taboa produtos 
     */
    public void crearTabla(){
        try{
        String tabla = "CREATE TABLE produtos (codigo VARCHAR2(3) PRIMARY KEY,"
                                               + "descricion VARCHAR(15),"
                                               + "prezo INTEGER)";
        PreparedStatement stCr = conn.prepareStatement(tabla);
        stCr.executeQuery(tabla);
        conn.setAutoCommit(true);
        System.out.println("Taboa creada");

        }catch(Exception e){
            System.out.println("Non se pode crear a taboa");
        }
    }
    
    /**
     * Insire campos na taboa produtos
     * @param codigo do produto
     * @param descricion do produto
     * @param prezo do produto
     */
    public void insireProduto (String codigo, String descricion, int prezo) {
        try{
            String insert = "INSERT INTO produtos  VALUES(?,?,?)";
            PreparedStatement ps = conn.prepareStatement(insert);
            ps.setString(1, codigo);
            ps.setString(2, descricion);
            ps.setInt(3, prezo);
            ps.executeUpdate();
            conn.setAutoCommit(true);
        
            System.out.println("Produto inserido");
        }catch (SQLException ex){
            System.out.println("Non se pode inserir o produto");
        }
    }
    
    /**
     * Mostra os produtos que se encontran na taboa
     */
    public void listaProduto(){
        try {
            PreparedStatement st = conn.prepareStatement("SELECT * FROM produtos");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                System.out.println("Codigo: " + rs.getString(1) + 
                                   ", Descricion: " + rs.getString(2) + 
                                   ", Prezo: " + rs.getInt(3));
            }
        } catch (SQLException ex) {
            System.out.println("Non se pode mostrar a taboa produtos");
        }
    }
    
    /**
     * Cambia o prezo dun produto 
     * @param codigo do produto o que se lle quere modificar o prezo
     * @param prezo novo do produto
     */
    public void actualizaPre(String codigo, int prezo){
        try{
            String update =  "UPDATE produtos SET prezo =" 
                             + prezo + " WHERE codigo ='" + codigo+"'";
            PreparedStatement stUp =  conn.prepareStatement(update);
            stUp.executeUpdate();
            System.out.println("Taboa modificada");
            conn.setAutoCommit(true);
            
        }catch(Exception e){
            System.out.println("Non se pode modificar a taboa");
        }
    }
    
    /**
     * Elimina a taboa produto e cerra a conexion a BD
     */
    public void eliminaTaboa (){
        try{
            String drop = "DROP TABLE produtos CASCADE CONSTRAINTS";
            PreparedStatement psDr = conn.prepareStatement(drop);
            psDr.executeUpdate();
            conn.setAutoCommit(true);
            System.out.println("Taboa eliminada");
            conn.close();
            
        }catch(Exception e){
                System.out.println("Non se pode eliminar a taboa");
        }
    }
}
