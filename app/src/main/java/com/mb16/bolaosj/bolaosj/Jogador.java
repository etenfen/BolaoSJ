package com.mb16.bolaosj.bolaosj;

import android.support.annotation.NonNull;

public class Jogador {

    int nroparticipante;
    String nome;
    int pontos;
    int naveia;
    int naveiavisitante;
    int posant;
    int posatual;

    public Jogador() {

    }

/*    public Jogador(int _nroparticipante, String _nome, int _pontos, int _naveia, int _naveiavisitante, int _posant){
        this.nroparticipante = _nroparticipante;
        this.nome = _nome;
        this.pontos = _pontos;
        this.naveia = _naveia;
        this.naveiavisitante = _naveiavisitante;
        this.posant = _posant;
    }*/

    /*public Jogador(String _nome, int _pontos, int _naveia, int _naveiavisitante){
        this.nome = _nome;
        this.pontos = _pontos;
        this.naveia = _naveia;
        this.naveiavisitante = _naveiavisitante;
    }*/

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

    public int getPosant() { return posant; }

    public void setPosant(int posant) { this.posant = posant; }

    public int getPosatual() { return posatual; }

    public void setPosatual(int posatual) { this.posatual = posatual; }


    @NonNull
    @Override
    public String toString()
    {
        return nome;
    }

}
