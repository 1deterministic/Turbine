package turbine;


// classe que roda o relógio do jogo
// a ideia é guardar a diferença de tempo entre os frames
// isso evita que a velocidade do jogo dependa do valor de quadros por segundo
public class Relogio {
    private Double tempoUltimoFrame;
    private Double deltaTempo;
    
    // construtor padrão, cria um relógio zerado
    public Relogio() {
        this.tempoUltimoFrame = 0d;
        this.deltaTempo = 0d;
    }

    // atualiza o relógio
    public void update() {
        long agora = System.currentTimeMillis();
        this.deltaTempo = (Double) (agora / 1000d) - this.tempoUltimoFrame;
        this.tempoUltimoFrame = (Double) (agora / 1000d);
    }
    
    // retorna a diferença de tempo para o último tempo em segundos
    // útil para usar na física em cálculos que dependem de tempo
    public Double getDeltaTempo() {
        return this.deltaTempo;
    }
}
