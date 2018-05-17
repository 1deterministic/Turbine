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
    
    public Ponto(Ponto p) {
        this.x = p.x;
        this.y = p.y;
        this.z = p.z;
    }
    
    // soma esse ponto com outro ponto
    public void somar(Ponto p) {
        this.x += p.x;
        this.y += p.y;
        this.z += p.z;
    }
    
    public void multiplicar(Double v) {
        this.x *= v;
        this.y *= v;
        this.z *= v;
    }
    
    public Double norma() {
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2));
    }
    
    public Ponto versor() {
        Double n = this.norma();
        return new Ponto(this.x / n, this.y / n, this.z / n);
    }
    
    // retorna a distância desse ponto até um outro ponto recebido
    public Double getDistancia(Ponto p){
        return Math.sqrt((Math.pow(x - p.x, 2)) + (Math.pow(y - p.y, 2)) + (Math.pow(z - p.z, 2)));
    }
}
