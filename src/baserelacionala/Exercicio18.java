package baserelacionala;

/**
 *
 * @author oracle
 */
public class Exercicio18 {

    public static void main(String[] args) {
        
        BaseRelacionalA a = new BaseRelacionalA();
        
        a.conexionBD();
        a.crearTabla();
        a.insireProduto("p1", "parafusos", 3);
        a.insireProduto("p2", "cravos", 4);
        a.insireProduto("p3", "tachas", 6);
        a.listaProduto();
    } 
}
