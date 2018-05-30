package Turbine;

// classe que representa o estado dos controles ativados
public class Controle {
    // atributos públicos para mais fácil acesso
    public boolean direita;
    public boolean esquerda;
    public boolean cima;
    public boolean baixo;
    
    // construtor padrão, seta todos os controles para falso
    public Controle(){
        this.direita = false;
        this.esquerda = false;
        this.cima = false;
        this.baixo = false;
    }
    
    // método de debug (apenas para saber se todos os controles estão registrando)
    public String toString(){
        return "dir: " + this.direita + "\n" +
               "esq: " + this.esquerda + "\n" +
               "cim: " + this.cima + "\n" +
               "bai: " + this.baixo + "\n";
    }
}
