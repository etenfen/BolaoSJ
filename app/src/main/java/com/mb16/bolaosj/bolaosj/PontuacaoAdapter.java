package com.mb16.bolaosj.bolaosj;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class PontuacaoAdapter extends RecyclerView.Adapter<PontuacaoAdapter.ViewHolder> {

    final Context context;
    final List<Jogador> mJogadorList;
    private Jogador dataModel;
    private static int porrodada;
    private static boolean total;
    BancoDados db;

    public PontuacaoAdapter(List<Jogador> l, Context c, Integer _rodada, boolean _total) {
        context = c;
        mJogadorList = l;
        porrodada = _rodada;
        total = _total;
        db = new BancoDados(c);
    }

    @NonNull
    @Override
    public PontuacaoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_lista_pontos, viewGroup, false);

        return new PontuacaoAdapter.ViewHolder(itemView);
    }

    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull PontuacaoAdapter.ViewHolder holder, int position) {

        final Jogador item = mJogadorList.get(position);
        int posant = item.getPosant();
        int posatual = item.getPosatual();
        //int difpos = posant - (position +1);
        int difpos = posant - posatual;

        if (total) {
            //difpos = posatual - posant;
            int tamanho = mJogadorList.size();
            if (position == 0) {
                holder.itemView.setBackgroundColor(Color.parseColor("#add8e6"));
            } else {
                if ((position == 1) || (position == 2) || (position == 3) || (position == 4) || (position == 5) || (position == 6) || (position == 7)) {
                    holder.itemView.setBackgroundColor(Color.parseColor("#00ff7f"));
                } else {
                    if ((position == (tamanho - 1)) || (position == (tamanho - 2)) || (position == (tamanho - 3)) || (position == (tamanho - 4))) {
                        holder.itemView.setBackgroundColor(Color.parseColor("#ff6b00"));
                    } else {
                        holder.itemView.setBackgroundColor(Color.parseColor("#f0fff0"));
                    }
                }
            }
        }

        if (difpos == 0) {
            holder.tvdifpos.setText(String.valueOf(difpos));
            holder.imgdifpos.setImageDrawable(context.getDrawable(R.drawable.neutroge));
        } else {
            if (difpos > 0) {
                holder.imgdifpos.setImageDrawable(context.getDrawable(R.drawable.setacimage));
                holder.tvdifpos.setText("+" + difpos);
            } else {
                holder.imgdifpos.setImageDrawable(context.getDrawable(R.drawable.setabaixoge));
                holder.tvdifpos.setText(String.valueOf(difpos));
            }
        }
        if (total){
            holder.tvposicao.setText(String.valueOf(posatual));
        } else {
            holder.tvposicao.setText(String.valueOf(position + 1));
        }
        holder.tvnome.setText(item.getNome());
        holder.tvpontos.setText(String.valueOf(item.getPontos()));
        holder.tvnaveia.setText(String.valueOf(item.getNaveia()));
        holder.tvnaveiavisitante.setText(String.valueOf(item.getNaveiavisitante()));
        holder.linearLayoutRegistros.setOnClickListener((View view) -> {
            Context mContext = view.getContext();
            dataModel = mJogadorList.get(position);
            String nrojogador = String.valueOf(dataModel.getNroparticipante());
            String nomejogador = dataModel.getNome();
            Intent intent = new Intent(view.getContext(), ActTabela.class);
            intent.putExtra("nrojogador", nrojogador);
            intent.putExtra("nomejogador", nomejogador);
            intent.putExtra("rodada", String.valueOf(porrodada));
            mContext.startActivity(intent);
        });

/*        holder.linearLayoutRegistros.setOnLongClickListener(view -> {
            Toast.makeText(view.getContext(), "Clique longo", Toast.LENGTH_SHORT).show();
            return true;
        });*/
    }

    @Override
    public int getItemCount() {
        return mJogadorList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        protected ImageView imgdifpos;
        protected TextView tvdifpos;
        protected TextView tvposicao;
        protected TextView tvnome;
        protected TextView tvpontos;
        protected TextView tvnaveia;
        protected TextView tvnaveiavisitante;
        protected LinearLayout linearLayoutRegistros;

        public ViewHolder(View itemView) {
            super(itemView);

            imgdifpos = itemView.findViewById(R.id.imgdifpos);
            tvdifpos = itemView.findViewById(R.id.tvdifpos);
            tvposicao = itemView.findViewById(R.id.tvposicao);
            tvnome = itemView.findViewById(R.id.tvnome);
            tvpontos = itemView.findViewById(R.id.tvpontos);
            tvnaveia = itemView.findViewById(R.id.tvnaveia);
            tvnaveiavisitante = itemView.findViewById(R.id.tvnaveiavisitante);
            linearLayoutRegistros = itemView.findViewById((R.id.layoutregistros));

            if (!total) {
                tvdifpos.setVisibility(View.INVISIBLE);
                imgdifpos.setVisibility(View.INVISIBLE);
            } else {
                tvdifpos.setVisibility(View.VISIBLE);
                imgdifpos.setVisibility(View.VISIBLE);
            }

        }
    }
}