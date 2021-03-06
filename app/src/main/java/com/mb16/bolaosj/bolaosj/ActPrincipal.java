package com.mb16.bolaosj.bolaosj;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ActPrincipal extends AppCompatActivity {

    private String[] listarodadas = new String[]{"Rodada 1", "Rodada 2", "Rodada 3", "Rodada 4", "Rodada 5", "Rodada 6", "Rodada 7",
            "Rodada 8", "Rodada 9", "Rodada 10"};

    private JogoTabela dataModel;
    EditText tvgolsmandante, tvgolsvisitante;
    TextView edNroJogo, tvmandante, tvvisitante;
    ImageView imgmandante, imgvisitante;
    BootstrapButton btnLimpar, btnSalvar, btnLimparPto;
    ListView lstTabela;
    Spinner cbRodada;
    BancoDados db = new BancoDados(this);
    dataAdapter data;
    InputMethodManager inputmethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_principal);
        setTitle("BolãoSJ - Jogos");
        edNroJogo = (EditText) findViewById(R.id.edNroJogo);
        edNroJogo.setVisibility(View.INVISIBLE);
        cbRodada = (Spinner) findViewById(R.id.cbRodada);
        tvmandante = (TextView) findViewById(R.id.tvmandante);
        tvgolsmandante = (EditText) findViewById(R.id.tvgolsmandante);
        tvgolsvisitante = (EditText) findViewById(R.id.tvgolsvisitante);
        tvvisitante = (TextView) findViewById(R.id.tvvisitante);
        imgmandante = (ImageView) findViewById(R.id.imgmandante);
        imgvisitante = (ImageView) findViewById(R.id.imgvisitante);
        btnLimpar = (BootstrapButton) findViewById(R.id.btnLimpar);
        btnLimparPto = (BootstrapButton) findViewById(R.id.btnLimparPto);
        btnLimparPto.setEnabled(false);
        btnSalvar = (BootstrapButton) findViewById(R.id.btnSalvar);
        btnSalvar.setEnabled(false);
        lstTabela = (ListView) findViewById(R.id.lstTabela);
        inputmethod = (InputMethodManager) this.getSystemService(Service.INPUT_METHOD_SERVICE);

        inicializarBancoDados();

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limparCamposGols();
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nrojogo = edNroJogo.getText().toString();
                if (nrojogo.isEmpty()) {

                    AlertDialog.Builder mensagem = new AlertDialog.Builder(ActPrincipal.this);
                    mensagem.setTitle("Informação");
                    mensagem.setMessage("Selecione um jogo válido");
                    mensagem.setNeutralButton("OK", null);
                    mensagem.show();
                    return;

                }
                String golsmandante = tvgolsmandante.getText().toString();
                if (golsmandante == null || golsmandante.equals("")) {
                    tvgolsmandante.setError("Campo obrigatório");
                    return;
                }

                String golsvisitante = tvgolsvisitante.getText().toString();
                if (golsvisitante == null || golsvisitante.equals("")) {
                    tvgolsvisitante.setError("Campo obrigatório");
                    return;
                }

                String empate = "N";
                String vencedor = "";
                if (Integer.parseInt(golsmandante) == Integer.parseInt(golsvisitante)) {
                    empate = "S";
                    vencedor = "N";
                } else if (Integer.parseInt(golsmandante) > Integer.parseInt(golsvisitante)) {
                    vencedor = "M";
                } else {
                    vencedor = "V";
                }

                JogoTabela jogotabela = new JogoTabela();
                jogotabela.setNrojogo(Integer.parseInt(nrojogo));
                jogotabela.setGolsmandante(Integer.parseInt(golsmandante));
                jogotabela.setGolsvisitante(Integer.parseInt(golsvisitante));
                jogotabela.setEmpate(empate);
                jogotabela.setVencedor(vencedor);

                db.atualizaJogoTabela(jogotabela, true);

                //Toast.makeText(ActPrincipal.this, "Alterado com sucesso", Toast.LENGTH_SHORT).show();

                JogoTabela jogotabela1 = db.selecionarJogoTabela(jogotabela.getNrojogo());
                listarjogotabela(jogotabela1.getRodada());
                limpartodosCampos();
                popularCampos(jogotabela1.getNrojogo());
                escondeTeclado();
                Toast.makeText(ActPrincipal.this, "Alterado com sucesso", Toast.LENGTH_SHORT).show();
            }
        });

        btnLimparPto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nrojogo = edNroJogo.getText().toString();
                if (nrojogo.isEmpty()) {

                    AlertDialog.Builder mensagem = new AlertDialog.Builder(ActPrincipal.this);
                    mensagem.setTitle("Informação");
                    mensagem.setMessage("Selecione um jogo válido");
                    mensagem.setNeutralButton("OK", null);
                    mensagem.show();
                    return;

                }

                db.limpaPontosJogo(Integer.parseInt(nrojogo));

                JogoTabela jogotabela = new JogoTabela();
                jogotabela.setNrojogo(Integer.parseInt(nrojogo));
                jogotabela.setGolsmandante(0);
                jogotabela.setGolsvisitante(0);
                jogotabela.setEmpate("N");
                jogotabela.setVencedor("N");

                db.atualizaJogoTabela(jogotabela, false);
                JogoTabela jogotabela1 = db.selecionarJogoTabela(jogotabela.getNrojogo());
                listarjogotabela(jogotabela1.getRodada());
                limpartodosCampos();
                popularCampos(jogotabela1.getNrojogo());
                escondeTeclado();

                Toast.makeText(ActPrincipal.this, "Alterado com sucesso", Toast.LENGTH_SHORT).show();

            }
        });

        cbRodada.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                listarjogotabela(position + 1);
                limpartodosCampos();
                escondeTeclado();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.pontuacao) {
            String rodada = String.valueOf(cbRodada.getSelectedItemPosition()+1);
            Intent intent = new Intent(getApplicationContext(), ActPontuacao.class);
            intent.putExtra("rodada",rodada);
            startActivity(intent);
            return true;
        }
        if (id == R.id.campeao) {
            //String rodada = String.valueOf(cbRodada.getSelectedItemPosition()+1);
            Intent intent = new Intent(getApplicationContext(), ActCampeao.class);
            //intent.putExtra("rodada",rodada);
            startActivity(intent);
            return true;
        }
        if (id == R.id.sair) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void escondeTeclado() {
        inputmethod.hideSoftInputFromWindow(tvgolsmandante.getWindowToken(), 0);
    }

    void mostraTeclado() {
        inputmethod.showSoftInput(tvgolsmandante, 0);
    }

    private void limpartodosCampos() {
        edNroJogo.setText("");
        tvmandante.setText("");
        tvgolsmandante.setText("");
        tvgolsvisitante.setText("");
        tvvisitante.setText("");
        imgmandante.setImageResource(R.drawable.bolao);
        imgvisitante.setImageResource(R.drawable.bolao);
        tvgolsmandante.requestFocus();
        btnSalvar.setEnabled(false);
        btnLimparPto.setEnabled(false);
    }

    void limparCamposGols() {
        tvgolsmandante.setText("");
        tvgolsvisitante.setText("");
        tvgolsmandante.requestFocus();
    }

    private void inicializarBancoDados() {
        db = new BancoDados(this);
        File database;
        database = getApplicationContext().getDatabasePath(BancoDados.NOMEBANCO);
        if (database.exists() == false) {
            db.getReadableDatabase();
            if (db.copiaBanco(this)) {
                Toast.makeText(this, "Banco copiado com sucesso", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Erro ao copiar o Banco", Toast.LENGTH_LONG).show();
            }
        }
    }

    void popularCampos(int _nrojogo) {
        JogoTabela jogotabela = db.selecionarJogoTabela(_nrojogo);
        edNroJogo.setText(String.valueOf(jogotabela.getNrojogo()));

        tvmandante.setText(jogotabela.getMandante());
        tvgolsmandante.setText(String.valueOf(jogotabela.getGolsmandante()));
        tvgolsvisitante.setText(String.valueOf(jogotabela.getGolsvisitante()));
        tvvisitante.setText(jogotabela.getVisitante());
        imgmandante.setImageBitmap(convertToBitmap(jogotabela.getEscudomandante()));
        imgvisitante.setImageBitmap(convertToBitmap(jogotabela.getEscudovisitante()));
    }

    public void listarjogotabela(int _rodada) {
        final ArrayList<JogoTabela> jogostabela = new ArrayList<>(db.allJogoTabela(_rodada));

        data = new dataAdapter(this, jogostabela);

        lstTabela.setAdapter(data);

        lstTabela.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                dataModel = jogostabela.get(position);
                popularCampos(dataModel.getNrojogo());
                btnSalvar.setEnabled(true);
                btnLimparPto.setEnabled(true);
                mostraTeclado();
            }
        });


        //TODO - VER PARA INSERIR 2 LINHAS NO TITULO DA ACTIVITY
        //TODO - VER BUTERKNIFE
        //TODO - VER BOOTSTRAP ANDROID
        //TODO - VER MATERIAL DESIGN - HTTPS://DESIGN.GOOGLE.COM/ICONS - HTTPS://MATERIALDESGINICONS.COM
        //TODO - https://www.youtube.com/watch?v=90G20Z1n7ag
    }

    private Bitmap convertToBitmap(byte[] b){

        return BitmapFactory.decodeByteArray(b, 0, b.length);
    }
}