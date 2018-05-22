package turbine;

// Não é necessariamente um ponto, pense como uma estrutura de dados de 3 valores
// Foi usada até agora como um ponto, um vetor ou como um vetor de ângulos de rotação
public class Ponto {
    // atributos públicos para facilitar a codificação
    public Double x;
    public Double y;
    public Double z;
    
    public Ponto() {
        this.x = 0d;
        this.y = 0d;
        this.z = 0d;
    }
    
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
    
    // multiplica os valores por um outro valor qualquer 
    public void multiplicar(Double v) {
        this.x *= v;
        this.y *= v;
        this.z *= v;
    }
    
    public Ponto escalar(Double v) {
        return new Ponto(this.x * v, this.y * v, this.z * v);
    }
    
    // caso seja usado como um vetor, retorna a norma desse vetor
    public Double norma() {
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2));
    }
    
    // caso seja usado como um vetor, retorna o versor desse vetor
    public Ponto versor() {
        Double n = this.norma();
        return new Ponto(this.x / n, this.y / n, this.z / n);
    }
    
    // retorna a distância desse ponto até um outro ponto recebido
    public Double getDistancia(Ponto p){
        return Math.sqrt((Math.pow(x - p.x, 2)) + (Math.pow(y - p.y, 2)) + (Math.pow(z - p.z, 2)));
    }
    
    // método de debug
    public String toString() {
        return "x: " + this.x + ", y: " + this.y + ", z: " + this.z;
    }
}
