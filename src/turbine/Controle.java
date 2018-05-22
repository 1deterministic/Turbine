package turbine;

public class Controle {
    public boolean direita;
    public boolean esquerda;
    public boolean cima;
    public boolean baixo;
    
    public Controle(){
        this.direita = false;
        this.esquerda = false;
        this.cima = false;
        this.baixo = false;
    }
    
    public String toString(){
        return "dir: " + this.direita + "\n" +
               "esq: " + this.esquerda + "\n" +
               "cim: " + this.cima + "\n" +
               "bai: " + this.baixo + "\n";
    }
}
