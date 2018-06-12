package Turbine;

import Fases.*;

// classe de controle de fase ativa
public class EscolhaFase {
    private boolean mudar; // define se é necessário mudar a fase
    private Fase fase; // guarda a nova fase que será carregada quando mudar for true
    
    // contrutor padrão, ativa o menu do jogo
    public EscolhaFase() { 
        this.mudar = false;
        this.fase = new Menu(this);
    }

    // retorna se é necessário mudar a fase ativa
    public boolean isMudar() {
        return mudar;
    }

    // define se é necessário mudar a fase ativa
    public void setMudar(boolean mudar) {
        this.mudar = mudar;
    }

    // retorna a nova fase que será exibida
    public Fase getFase() {
        return fase;
    }

    // retorna a nova fase que será exibida
    public void setFase(Fase fase) {
        this.fase = fase;
    }
}
