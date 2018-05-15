package turbine;

public class Ponto {
    // atributos públicos para facilitar a codificação
    public int x;
    public int y;
    
    public Ponto(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    // retorna a distância desse ponto até um outro ponto recebido
    public Double getDistancia(Ponto p){
        return Math.sqrt((Math.pow(x - p.x, 2)) + (Math.pow(y - p.y, 2)));
    }
}
