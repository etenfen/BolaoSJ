package com.mb16.bolaosj.bolaosj;

public class JogoAposta {

    int nrojogo;
    int rodada;
    String mandante;
    int golsmandante;
    int golsvisitante;
    int golsmandantetabela;
    int golsvisitantetabela;
    String visitante;
    int pontos;
    int naveia;
    int naveiavisitante;
    byte[] escudomandante;
    byte[] escudovisitante;

    public JogoAposta() {

    }

    public void setNrojogo(int nrojogo) {
        this.nrojogo = nrojogo;
    }

    public void setRodada(int rodada) {
        this.rodada = rodada;
    }

    public String getMandante() {
        return mandante;
    }

    public void setMandante(String mandante) {
        this.mandante = mandante;
    }

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


    public int getGolsmandantetabela() {
        return golsmandantetabela;
    }

    public void setGolsmandantetabela(int golsmandantetabela) {
        this.golsmandantetabela = golsmandantetabela;
    }

    public int getGolsvisitantetabela() {
        return golsvisitantetabela;
    }

    public void setGolsvisitantetabela(int golsvisitantetabela) {
        this.golsvisitantetabela = golsvisitantetabela;
    }

    public String getVisitante() {
        return visitante;
    }

    public void setVisitante(String visitante) {
        this.visitante = visitante;
    }

    public int getPontos() { return pontos; }

    public void setPontos(int pontos) { this.pontos = pontos;}

    public int getNaveia() { return naveia; }

    public void setNaveia(int naveia) {        this.naveia = naveia; }

    public int getNaveiavisitante() { return naveiavisitante; }

    public void setNaveiavisitante(int naveiavisitante) { this.naveiavisitante = naveiavisitante; }

    public byte[] getEscudomandante() {
        return escudomandante;
    }

    public void setEscudomandante(byte[] escudomandante) { this.escudomandante = escudomandante; }

    public byte[] getEscudovisitante() { return escudovisitante; }

    public void setEscudovisitante(byte[] escudovisitante) { this.escudovisitante = escudovisitante; }
}
