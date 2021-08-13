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

    public int getGolsmandante() {
        return golsmandante;
    }

    public void setGolsmandante(int golsmandante) {
        this.golsmandante = golsmandante;
    }

    public int getGolsvisitante() {
        return golsvisitante;
    }

    public void setGolsvisitante(int golsvisitante) {
        this.golsvisitante = golsvisitante;
    }

    int golsmandante;
    int golsvisitante;

    public Jogador() {

    }

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
