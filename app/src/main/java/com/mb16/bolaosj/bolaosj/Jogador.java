package com.mb16.bolaosj.bolaosj;

public class Jogador {

    int nroparticipante;
    String nome;
    int pontos;
    int naveia;
    int naveiavisitante;

    public Jogador() {

    }

    public Jogador(int _nroparticipante, String _nome, int _pontos, int _naveia, int _naveiavisitante){
        this.nroparticipante = _nroparticipante;
        this.nome = _nome;
        this.pontos = _pontos;
        this.naveia = _naveia;
        this.naveiavisitante = _naveiavisitante;
    }

    public Jogador(String _nome, int _pontos, int _naveia, int _naveiavisitante){
        this.nome = _nome;
        this.pontos = _pontos;
        this.naveia = _naveia;
        this.naveiavisitante = _naveiavisitante;
    }

    //=========================================================================
    public int getNroparticipante() {
        return nroparticipante;
    }

    public void setNroparticipante(int nroparticipante) {
        this.nroparticipante = nroparticipante;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public int getNaveia() {
        return naveia;
    }

    public void setNaveia(int naveia) {
        this.naveia = naveia;
    }

    public int getNaveiavisitante() {
        return naveiavisitante;
    }

    public void setNaveiavisitante(int naveiavisitante) {
        this.naveiavisitante = naveiavisitante;
    }

    @Override
    public String toString()
    {
        return nome;
    }

}
