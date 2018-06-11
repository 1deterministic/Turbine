package Turbine;

import Fases.*;

public class EscolhaFase {
    private boolean mudar;
    private Fase fase;
    
    public EscolhaFase() { 
        this.mudar = false;
        this.fase = new Menu(this);
    }

    public boolean isMudar() {
        return mudar;
    }

    public void setMudar(boolean mudar) {
        this.mudar = mudar;
    }

    public Fase getFase() {
        return fase;
    }

    public void setFase(Fase fase) {
        this.fase = fase;
    }
}
