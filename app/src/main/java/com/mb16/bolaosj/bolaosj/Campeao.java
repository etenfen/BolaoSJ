package com.mb16.bolaosj.bolaosj;

public class Campeao {

    int nro;
    int nrojogador;
    String nometime;
    byte[] escudotime;
    int pontos;
    int jogos;
    int vitorias;
    int empates;
    int derrotas;
    int golspro;
    int golscontra;
    int saldo;

    public Campeao() {

    }

    public Campeao(int _nro, int _nrojogador, String _nometime, byte[] _escudotime, int _pontos, int _jogos,
                   int _vitorias, int _empates, int _derrotas, int _golspro, int _golscontra, int _saldo) {

        this.nro = _nro;
        this.nrojogador = _nrojogador;
        this.nometime = _nometime;
        this.escudotime= _escudotime;
        this.pontos = _pontos;
        this.jogos = _jogos;
        this.vitorias= _vitorias;
        this.empates= _empates;
        this.derrotas= _derrotas;
        this.golspro= _golspro;
        this.golscontra= _golscontra;
        this.saldo= _saldo;

    }

    public int getNro() { return nro; }

    public void setNro(int nro) { this.nro = nro; }

    public int getNrojogador() {  return nrojogador; }

    public void setNrojogador(int nrojogador) { this.nrojogador = nrojogador; }

    public String getNometime() {  return nometime;  }

    public void setNometime(String nometime) {  this.nometime = nometime;  }

    public byte[] getEscudotime() {  return escudotime; }

    public void setEscudotime(byte[] escudotime) {  this.escudotime = escudotime; }

    public int getPontos() {  return pontos; }

    public void setPontos(int pontos) {  this.pontos = pontos; }

    public int getJogos() {  return jogos; }

    public void setJogos(int jogos) {  this.jogos = jogos;  }

    public int getVitorias() {  return vitorias; }

    public void setVitorias(int vitorias) {  this.vitorias = vitorias; }

    public int getEmpates() {  return empates; }

    public void setEmpates(int empates) {  this.empates = empates; }

    public int getDerrotas() {  return derrotas; }

    public void setDerrotas(int derrotas) {  this.derrotas = derrotas; }

    public int getGolspro() {  return golspro; }

    public void setGolspro(int golspro) {  this.golspro = golspro; }

    public int getGolscontra() {  return golscontra; }

    public void setGolscontra(int golscontra) {  this.golscontra = golscontra; }

    public int getSaldo() {  return saldo; }

    public void setSaldo(int saldo) {  this.saldo = saldo;}
}