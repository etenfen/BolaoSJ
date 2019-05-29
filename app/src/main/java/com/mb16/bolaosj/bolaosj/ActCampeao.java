package com.mb16.bolaosj.bolaosj;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class ActCampeao extends AppCompatActivity {

    private ListView lstCampeao;
    private int nrojogador;
    private ParticipanteAdapter jadapter;
    Spinner cbRodadaCampeao;
    CampeaoAdapter data;
    BancoDados db = new BancoDados(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_campeao);
        setTitle("Campeão cfe Tabela");

        lstCampeao = (ListView) findViewById(R.id.lstCampeao);
        cbRodadaCampeao = (Spinner) findViewById(R.id.cbRodadaCampeao);

        ArrayList<Jogador> listajogadores = new ArrayList<>(db.allParticipantes());
        jadapter = new ParticipanteAdapter(this, listajogadores);
        cbRodadaCampeao.setAdapter(jadapter);

        nrojogador = 1;
        atualizarCampeao(nrojogador);

        cbRodadaCampeao.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Jogador participanteselecinado = ((Jogador)cbRodadaCampeao.getSelectedItem());
                nrojogador = participanteselecinado.getNroparticipante();
                atualizarCampeao(nrojogador);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void atualizarCampeao(int _nrojogador) {

        final ArrayList<Campeao> campeoes = new ArrayList<>(db.allCampeoes(_nrojogador));
        data = new CampeaoAdapter(this, campeoes);
        lstCampeao.setAdapter(data);

    }
}
