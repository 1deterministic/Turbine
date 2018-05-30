package Fases;

import turbine.*;

public abstract class Fase {
    // carrega todos os elementos da fase
    public abstract void carregar(String diretorioRaiz);
    
    // roda a física e a lógica
    public abstract void atualizar(Double deltaTempo, Controle controle);
    
    // desenha todos os elementos na tela
    public abstract void desenhar(OGL ogl);
}
