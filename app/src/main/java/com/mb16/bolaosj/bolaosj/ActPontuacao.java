package com.mb16.bolaosj.bolaosj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class ActPontuacao extends AppCompatActivity {
    private RecyclerView mRecyclerViewPontuacao;
    private PontuacaoAdapter adapter;
    private List<Jogador> jogadores;
    private int porrodada;
    Spinner cbRodadaPontos;
    BancoDados db = new BancoDados(this);
    private Jogador todosjogadores;
    private LinearLayoutManager mLayoutManagertodosjogadores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_pontuacao);
        Intent it = getIntent();
        porrodada = Integer.parseInt(it.getStringExtra("rodada"));
        setTitle("Pontuação");

        mRecyclerViewPontuacao = (RecyclerView) findViewById(R.id.mRecyclerViewPontuacao);
        cbRodadaPontos = (Spinner) findViewById(R.id.cbRodadaPontos);
        cbRodadaPontos.setSelection(porrodada - 1);

        atualizarPontuacao(porrodada);

        cbRodadaPontos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                porrodada = position + 1;
                atualizarPontuacao(porrodada);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_pontuacao, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.total) {
            cbRodadaPontos.setVisibility(View.GONE);
            porrodada = 0;
            atualizarPontuacao(porrodada);
            return true;
        }
        if (id == R.id.porrodada) {
            cbRodadaPontos.setVisibility(View.VISIBLE);

            porrodada = cbRodadaPontos.getSelectedItemPosition() + 1;
            atualizarPontuacao(porrodada);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void atualizarPontuacao(int _rodada) {
        mRecyclerViewPontuacao.setHasFixedSize(true);
        mLayoutManagertodosjogadores = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerViewPontuacao.setLayoutManager(mLayoutManagertodosjogadores);
        final ArrayList<Jogador> jogadores = new ArrayList<>(db.allJogadores(_rodada));
        adapter = new PontuacaoAdapter(jogadores, this, _rodada);
        mRecyclerViewPontuacao.setAdapter(adapter);
    }
}
