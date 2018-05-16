package turbine;

public class Ponto {
    // atributos públicos para facilitar a codificação
    public Double x;
    public Double y;
    public Double z;
    
    public Ponto(Double x, Double y, Double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    // retorna a distância desse ponto até um outro ponto recebido
    public Double getDistancia(Ponto p){
        return Math.sqrt((Math.pow(x - p.x, 2)) + (Math.pow(y - p.y, 2)) + (Math.pow(z - p.z, 2)));
    }
}
