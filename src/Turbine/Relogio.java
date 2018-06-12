package Turbine;

// classe que roda o relógio do jogo
// a ideia é guardar a diferença de tempo entre os frames
// isso evita que a velocidade do jogo dependa do valor de quadros por segundo
public class Relogio {
    private Double tempoUltimoFrame; // guarda o tempo em que o último frame foi exibido
    private Double deltaTempo; // guarda a variação de tempo desde o último frame até agora
    
    // construtor padrão, cria um relógio zerado
    public Relogio() {
        this.tempoUltimoFrame = 0d;
        this.deltaTempo = 0d;
    }

    // atualiza o relógio
    public void update() {
        long agora = System.currentTimeMillis(); // guarda o tempo atual
        this.deltaTempo = (Double) (agora / 1000d) - this.tempoUltimoFrame; // calcula e guarda a diferença do útlimo frame até agora
        this.tempoUltimoFrame = (Double) (agora / 1000d); // atualiza o tempo do último frame para agora (para ser usado no próximo update)
    }
    
    // retorna a diferença de tempo para o último tempo em segundos
    // útil para usar na física em cálculos que dependem de tempo
    public Double getDeltaTempo() {
        return this.deltaTempo;
    }
}
