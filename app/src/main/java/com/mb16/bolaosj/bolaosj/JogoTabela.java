package com.mb16.bolaosj.bolaosj;

public class JogoTabela {

    int nrojogo;
    int rodada;
    String mandante;
    int golsmandante;
    int golsvisitante;
    String visitante;
    String vencedor;
    String empate;
    byte[] escudomandante;
    byte[] escudovisitante;
    int pto0;
    int pto1;
    int pto2;
    int pto3;
    int pto5;


    public JogoTabela() {

    }

    public JogoTabela(int _nrojogo, int _rodada, String _mandante, byte[] _escudomandante,
                      int _golsmandante, int _golsvisitante, String _visitante, byte[] _escudovisitante) {

        this.nrojogo = _nrojogo;
        this.rodada = _rodada;
        this.mandante = _mandante;
        this.golsmandante = _golsmandante;
        this.golsvisitante = _golsvisitante;
        this.visitante = _visitante;
        this.escudomandante = _escudomandante;
        this.escudovisitante = _escudovisitante;
    }

    public int getNrojogo() {
        return nrojogo;
    }

    public void setNrojogo(int nrojogo) {
        this.nrojogo = nrojogo;
    }

    public int getRodada() {
        return rodada;
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

    public String getVisitante() {
        return visitante;
    }

    public void setVisitante(String visitante) {
        this.visitante = visitante;
    }

    public String getVencedor() { return vencedor; }

    public void setVencedor(String vencedor) { this.vencedor = vencedor; }

    public String getEmpate() { return empate; }

    public void setEmpate(String empate) { this.empate = empate; }

    public byte[] getEscudomandante() {
        return escudomandante;
    }

    public void setEscudomandante(byte[] escudomandante) {
        this.escudomandante = escudomandante;
    }

    public byte[] getEscudovisitante() {
        return escudovisitante;
    }

    public void setEscudovisitante(byte[] escudovisitante) {
        this.escudovisitante = escudovisitante;
    }

    public int getPto0() { return pto0; }

    public void setPto0(int pto0) { this.pto0 = pto0; }

    public int getPto1() { return pto1; }

    public void setPto1(int pto1) {  this.pto1 = pto1; }

    public int getPto2() { return pto2; }

    public void setPto2(int pto2) { this.pto2 = pto2; }

    public int getPto3() {  return pto3; }

    public void setPto3(int pto3) {this.pto3 = pto3; }

    public int getPto5() { return pto5;}

    public void setPto5(int pto5) { this.pto5 = pto5; }
}