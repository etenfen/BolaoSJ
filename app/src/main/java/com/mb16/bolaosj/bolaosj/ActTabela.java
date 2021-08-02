package com.mb16.bolaosj.bolaosj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import java.util.ArrayList;

public class ActTabela extends AppCompatActivity {

    private ListView lstAposta;
    ApostaAdapter data;
    private String nrojogadortabela;
    //private String nomejogador;
    //private int rodada;
    Spinner cbRodadaTabelas;
    BancoDados db = new BancoDados(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int rodada;
        String nomejogador;
        setContentView(R.layout.act_tabela);
        cbRodadaTabelas = findViewById(R.id.cbRodadaTabelas);
        lstAposta = findViewById(R.id.lstAposta);
        Intent it = getIntent();
        nrojogadortabela = it.getStringExtra("nrojogador");
        nomejogador = it.getStringExtra("nomejogador");
        rodada = Integer.parseInt(it.getStringExtra("rodada"));
        setTitle("Apostas:" + nomejogador);
        cbRodadaTabelas.setSelection(rodada-1);
        atualizarApostas(rodada, Integer.parseInt(nrojogadortabela));

        cbRodadaTabelas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                atualizarApostas(position + 1, Integer.parseInt(nrojogadortabela));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void atualizarApostas(int _rodada, int _nrojogador) {

        final ArrayList<JogoAposta> jogosaposta = new ArrayList<>(db.allJogoAposta(_rodada,_nrojogador));
        data = new ApostaAdapter(this, jogosaposta);
        lstAposta.setAdapter(data);
    }
}