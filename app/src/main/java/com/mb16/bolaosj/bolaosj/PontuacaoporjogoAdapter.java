package com.mb16.bolaosj.bolaosj;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;

public class PontuacaoporjogoAdapter extends RecyclerView.Adapter<PontuacaoporjogoAdapter.ViewHolder> {

    final Context context;
    final List<Jogador> mJogadorList;
    BancoDados db;

    public PontuacaoporjogoAdapter(List<Jogador> l, Context c) {
        context = c;
        mJogadorList = l;
        db = new BancoDados(c);
    }

    @NonNull
    @Override
    public PontuacaoporjogoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_lista_pontosporjogo, viewGroup, false);

        return new PontuacaoporjogoAdapter.ViewHolder(itemView);
    }

    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull PontuacaoporjogoAdapter.ViewHolder holder, int position) {

        final Jogador item = mJogadorList.get(position);

        holder.tvplacar.setText(item.getGolsmandante() + " X " + item.getGolsvisitante());
        holder.tvnome.setText(item.getNome());
        holder.tvpontos.setText(String.valueOf(item.getPontos()));
        holder.tvnaveia.setText(String.valueOf(item.getNaveia()));
        holder.tvnaveiavisitante.setText(String.valueOf(item.getNaveiavisitante()));
    }

    @Override
    public int getItemCount() {
        return mJogadorList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        protected TextView tvplacar;
        protected TextView tvnome;
        protected TextView tvpontos;
        protected TextView tvnaveia;
        protected TextView tvnaveiavisitante;
        protected LinearLayout linearLayoutRegistros;

        public ViewHolder(View itemView) {
            super(itemView);

            tvplacar = itemView.findViewById(R.id.tvplacar);
            tvnome = itemView.findViewById(R.id.tvnome);
            tvpontos = itemView.findViewById(R.id.tvpontos);
            tvnaveia = itemView.findViewById(R.id.tvnaveia);
            tvnaveiavisitante = itemView.findViewById(R.id.tvnaveiavisitante);
            linearLayoutRegistros = itemView.findViewById((R.id.layoutregistros));

        }
    }
}