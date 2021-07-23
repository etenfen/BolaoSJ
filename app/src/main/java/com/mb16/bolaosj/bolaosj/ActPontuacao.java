package com.mb16.bolaosj.bolaosj;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ShareCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.util.LruCache;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
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

        atualizarPontuacao(porrodada, false);

        cbRodadaPontos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                porrodada = position + 1;
                atualizarPontuacao(porrodada, false);
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

        Bitmap bitmapRecycler = fotoDoRecyclerView(mRecyclerViewPontuacao);
        File pasta = new File(this.getFilesDir(), "/mypdf/");
        int id = item.getItemId();
        if (id == R.id.total) {
            cbRodadaPontos.setVisibility(View.GONE);
            porrodada = db.retornaRodada();
            atualizarPontuacao(porrodada, true);
            return true;
        }
        if (id == R.id.porrodada) {
            cbRodadaPontos.setVisibility(View.VISIBLE);

            porrodada = cbRodadaPontos.getSelectedItemPosition() + 1;
            atualizarPontuacao(porrodada, false);
            return true;
        }
        if (id == R.id.gerar_pdf) {
            Criaarquivo criaarquivo = new Criaarquivo(pasta, getApplicationContext());
            String criando = criaarquivo.SalvarPDF(bitmapRecycler, "pontuacao");
            if (criando.equals("sucesso")) {
                Toast.makeText(this, "Arquivo criado com sucesso", Toast.LENGTH_LONG).show();

/*                String path = "/data/data/com.mb16.bolaosj.bolaosj/files/PDF/pontuacao.pdf";
                Uri uri = Uri.parse(path);
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                //sharingIntent.setType("text/plain");

                sharingIntent.setType("application/pdf");
                //Uri uri = Uri.fromFile(pasta);
                //sharingIntent.setDataAndType(uri, "application/pdf");
                //sharingIntent.setData();
                //String shareBody = "Here is the share content body";
                //sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                //sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                sharingIntent.putExtra(Intent.EXTRA_STREAM, uri);
                startActivity(Intent.createChooser(sharingIntent, "Compartilhar PDF"));*/


                /*final Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("application/pdf");
                final File pdfFile = new File(getFilesDir(), "pontuacao.pdf");
                shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(pdfFile));
                startActivity(Intent.createChooser(shareIntent, "Share image using"));*/

                //ERRO DE PERMISSÃO
                File documentsPath = new File(this.getFilesDir(), "mypdf");
                File file = new File(documentsPath, "pontuacao.pdf");
                Uri uri = FileProvider.getUriForFile(this, "com.mb16.bolaosj.bolaosj.fileprovider", file);

                Intent intent = ShareCompat.IntentBuilder.from(this)
                        .setType("application/pdf")
                        .setStream(uri)
                        .setChooserTitle("Choose bar")
                        .createChooserIntent()
                        .addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
                        .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                this.grantUriPermission("com.mb16.bolaosj.bolaosj", uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
                this.startActivity(intent);

            } else {
                Toast.makeText(this, criando, Toast.LENGTH_LONG).show();
            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void atualizarPontuacao(int _rodada, boolean _total) {
        mRecyclerViewPontuacao.setHasFixedSize(true);
        mLayoutManagertodosjogadores = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerViewPontuacao.setLayoutManager(mLayoutManagertodosjogadores);
        final ArrayList<Jogador> jogadores = new ArrayList<>(db.allJogadores(_rodada, _total));
        adapter = new PontuacaoAdapter(jogadores, this, _rodada, _total);
        mRecyclerViewPontuacao.setAdapter(adapter);
    }

    private Bitmap fotoDoRecyclerView(RecyclerView view) {
        RecyclerView.Adapter adapter = view.getAdapter();

        Bitmap bitmapPronto = null;

        if (adapter != null) {
            Paint paint = new Paint();
            int tamanhodalista = adapter.getItemCount();
            int altura = 0;
            int alturaVolatil = 0;
            final int tamanhoMaximoDoArquivo = (int) (Runtime.getRuntime().maxMemory() / 1024);
            final int tamanhoDoCache = tamanhoMaximoDoArquivo / 8;
            LruCache<String, Bitmap> bitmapCache = new LruCache<>(tamanhoDoCache);

            for (int x = 0; x < tamanhodalista; x++) {

                RecyclerView.ViewHolder holder = adapter.createViewHolder(view, adapter.getItemViewType(x));
                adapter.onBindViewHolder(holder, x);
                holder.itemView.measure(View.MeasureSpec.makeMeasureSpec(view.getWidth(), View.MeasureSpec.EXACTLY), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                holder.itemView.layout(0, 0, holder.itemView.getMeasuredWidth(), holder.itemView.getMeasuredHeight());
                holder.itemView.setDrawingCacheEnabled(true);
                holder.itemView.buildDrawingCache();

                Bitmap cacheDoBitmap = holder.itemView.getDrawingCache();
                if (cacheDoBitmap != null) {
                    bitmapCache.put(String.valueOf(x), cacheDoBitmap);
                }
                altura += holder.itemView.getMeasuredHeight();
            }//for
            bitmapPronto = Bitmap.createBitmap(view.getMeasuredWidth(), altura, Bitmap.Config.ARGB_8888);
            Canvas pagina = new Canvas(bitmapPronto);
            pagina.drawColor(Color.WHITE);

            for (int x = 0; x < tamanhodalista; x++) {

                Bitmap bitmap = bitmapCache.get(String.valueOf(x));
                pagina.drawBitmap(bitmap, 0, alturaVolatil, paint);
                alturaVolatil += bitmap.getHeight();
                bitmap.recycle();
            }

        }//if adapter != null

        return bitmapPronto;
    }

}
