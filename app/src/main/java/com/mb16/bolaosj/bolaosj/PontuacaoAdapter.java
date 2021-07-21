package com.mb16.bolaosj.bolaosj;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class PontuacaoAdapter extends RecyclerView.Adapter<PontuacaoAdapter.ViewHolder> {

    private Context context;
    private List<Jogador> mJogadorList;
    private List<Jogador> jogadores;
    private List<Jogador> todosJogadores;
    private Jogador dataModel;
    private static int porrodada;
    BancoDados db;

    public PontuacaoAdapter(List<Jogador> l, Context c, Integer _rodada) {
        context = c;
        mJogadorList = l;
        porrodada = _rodada;
        db = new BancoDados(c);
    }

    @NonNull
    @Override
    public PontuacaoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_lista_pontos, viewGroup, false);

        return new PontuacaoAdapter.ViewHolder(itemView);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull PontuacaoAdapter.ViewHolder holder, int position) {

        final Jogador item = mJogadorList.get(position);
        Integer posant = item.getPosant();
        Integer difpos = posant - (position +1);

        if (difpos == 0) {
            holder.imgdifpos.setImageDrawable(context.getDrawable(R.drawable.neutro));
        } else {
            if (difpos > 0) {
                holder.imgdifpos.setImageDrawable(context.getDrawable(R.drawable.setacima));
            } else {
                holder.imgdifpos.setImageDrawable(context.getDrawable(R.drawable.setabaixo));
            }
        }
        holder.tvdifpos.setText(String.valueOf(difpos));
        holder.tvposicao.setText(String.valueOf(position + 1));
        holder.tvnome.setText(item.getNome());
        holder.tvpontos.setText(String.valueOf(item.getPontos()));
        holder.tvnaveia.setText(String.valueOf(item.getNaveia()));
        holder.tvnaveiavisitante.setText(String.valueOf(item.getNaveiavisitante()));
        holder.linearLayoutRegistros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context mContext = view.getContext();
                dataModel = mJogadorList.get(position);
                String nrojogador = String.valueOf(dataModel.getNroparticipante());
                String nomejogador = dataModel.getNome();
                Intent intent = new Intent(view.getContext(), ActTabela.class);
                intent.putExtra("nrojogador", nrojogador);
                intent.putExtra("nomejogador", nomejogador);

                if (porrodada == 0) { porrodada = 1; }
                intent.putExtra("rodada", String.valueOf(porrodada));
                mContext.startActivity(intent);
            }
        });
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

            imgdifpos = (ImageView) itemView.findViewById(R.id.imgdifpos);
            tvdifpos = (TextView) itemView.findViewById(R.id.tvdifpos);
            tvposicao = (TextView) itemView.findViewById(R.id.tvposicao);
            tvnome = (TextView) itemView.findViewById(R.id.tvnome);
            tvpontos = (TextView) itemView.findViewById(R.id.tvpontos);
            tvnaveia = (TextView) itemView.findViewById(R.id.tvnaveia);
            tvnaveiavisitante = (TextView) itemView.findViewById(R.id.tvnaveiavisitante);
            linearLayoutRegistros = (LinearLayout) itemView.findViewById((R.id.layoutregistros));

            if (porrodada != 0) {
                tvdifpos.setVisibility(View.INVISIBLE);
                imgdifpos.setVisibility(View.INVISIBLE);
            } else {
                tvdifpos.setVisibility(View.VISIBLE);
                imgdifpos.setVisibility(View.VISIBLE);
            }

        }
    }
}