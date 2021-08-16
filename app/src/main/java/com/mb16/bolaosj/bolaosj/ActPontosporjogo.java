package com.mb16.bolaosj.bolaosj;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class ActPontosporjogo extends AppCompatActivity {
    TextView tvmandante, tvvisitante, tvgolsmandante, tvgolsvisitante;
    ImageView imgmandante, imgvisitante;

    BancoDados db = new BancoDados(this);
    List<Jogador> jogadores = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_pontosporjogo);
        Intent it = getIntent();
        int nrojogo = Integer.parseInt(it.getStringExtra("nrojogo"));
        RecyclerView mRecyclerViewPtosporjogo;
        setTitle("Pontos por jogo");

        tvmandante = findViewById(R.id.tvmandante);
        tvgolsmandante = findViewById(R.id.tvgolsmandante);
        tvgolsvisitante = findViewById(R.id.tvgolsvisitante);
        tvvisitante = findViewById(R.id.tvvisitante);
        imgmandante = findViewById(R.id.imgmandante);
        imgvisitante = findViewById(R.id.imgvisitante);
        mRecyclerViewPtosporjogo = findViewById(R.id.mRecyclerViewPtosporjogo);

        JogoTabela jogotabela = db.selecionarJogoTabela(nrojogo);
        tvmandante.setText(jogotabela.getMandante());
        tvgolsmandante.setText(String.valueOf(jogotabela.getGolsmandante()));
        tvgolsvisitante.setText(String.valueOf(jogotabela.getGolsvisitante()));
        tvvisitante.setText(jogotabela.getVisitante());
        imgmandante.setImageBitmap(convertToBitmap(jogotabela.getEscudomandante()));
        imgvisitante.setImageBitmap(convertToBitmap(jogotabela.getEscudovisitante()));

        PontuacaoporjogoAdapter adapter;
        LinearLayoutManager mLayoutManagertodosjogadores;

        mRecyclerViewPtosporjogo.setHasFixedSize(true);
        mLayoutManagertodosjogadores = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerViewPtosporjogo.setLayoutManager(mLayoutManagertodosjogadores);
        jogadores = db.allPontosporjogo(nrojogo);

        adapter = new PontuacaoporjogoAdapter(jogadores, this);
        mRecyclerViewPtosporjogo.setAdapter(adapter);
    }
    private Bitmap convertToBitmap(byte[] b){
        return BitmapFactory.decodeByteArray(b, 0, b.length);
    }

}
