package com.mb16.bolaosj.bolaosj;

public class JogoAposta {

    int nrojogo;
    int rodada;
    String mandante;
    int golsmandante;
    int golsvisitante;
    String visitante;
    int pontos;
    int naveia;
    int naveiavisitante;
    //String vencedor;
    //String empate;
    byte[] escudomandante;
    byte[] escudovisitante;

    public JogoAposta() {

    }

    /*public JogoAposta(int _nrojogo, int _rodada, String _mandante, int _golsmandante, int _golsvisitante, String _visitante,
                      int _pontos, int _naveia, int _naveiavisitante, String _vencedor, String _empate,
                      byte[] _escudomandante, byte[] _escudovisitante) {

        this.nrojogo = _nrojogo;
        this.rodada = _rodada;
        this.mandante = _mandante;
        this.golsmandante = _golsmandante;
        this.golsvisitante = _golsvisitante;
        this.visitante = _visitante;
        this.pontos = _pontos;
        this.naveia = _naveia;
        this.naveiavisitante = _naveiavisitante;
        this.vencedor = _vencedor;
        this.empate = _empate;
        this.escudomandante = _escudomandante;
        this.escudovisitante = _escudovisitante;

    }*/

    //public int getNrojogo() { return nrojogo; }

    public void setNrojogo(int nrojogo) {
        this.nrojogo = nrojogo;
    }

 /*   public int getRodada() {
        return rodada;
    }*/

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

    //public String getVencedor() { return vencedor; }

    //public void setVencedor(String vencedor) { this.vencedor = vencedor; }

    //public String getEmpate() { return empate; }

    //public void setEmpate(String empate) { this.empate = empate; }

    public byte[] getEscudomandante() {
        return escudomandante;
    }

    public void setEscudomandante(byte[] escudomandante) { this.escudomandante = escudomandante; }

    public byte[] getEscudovisitante() { return escudovisitante; }

    public void setEscudovisitante(byte[] escudovisitante) { this.escudovisitante = escudovisitante; }
}
