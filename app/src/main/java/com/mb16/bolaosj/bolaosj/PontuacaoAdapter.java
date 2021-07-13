package com.mb16.bolaosj.bolaosj;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

public class PontuacaoAdapter extends RecyclerView.Adapter<PontuacaoAdapter.ViewHolder> {

    private Context context;
    private List<Jogador> mJogadorList;
    private List<Jogador> jogadores;
    private List<Jogador> todosJogadores;
    private Jogador dataModel;
    private int porrodada;
    BancoDados db;

    public PontuacaoAdapter(List<Jogador> l, Context c, Integer _rodada) {
        context = c;
        mJogadorList = l;
        porrodada = _rodada;
        db =new BancoDados(c);
    }

    @NonNull
    @Override
    public PontuacaoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_lista_pontos,viewGroup,false);

        return new PontuacaoAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PontuacaoAdapter.ViewHolder holder, int position) {

        //jogadores = new ArrayList<>();
//        final ArrayList<Jogador> jogadores = new ArrayList<>(db.allJogadores(porrodada));
        final Jogador item = mJogadorList.get(position);

        holder.tvposicao.setText(String.valueOf(position + 1));
        holder.tvnome.setText(item.getNome());
        holder.tvpontos.setText(String.valueOf(item.getPontos()));
        holder.tvnaveia.setText(String.valueOf(item.getNaveia()));
        holder.tvnaveiavisitante.setText(String.valueOf(item.getNaveiavisitante()));

        holder.linearLayoutRegistros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context mContext = view.getContext();
                //Toast.makeText(view.getContext(), "inside viewholder position = " + holder.getAdapterPosition(), Toast.LENGTH_SHORT).show();
                //dataModel = jogadores.get(position);
                dataModel = mJogadorList.get(position);
                String nrojogador = String.valueOf(dataModel.getNroparticipante());
                String nomejogador = dataModel.getNome();
                Intent intent = new Intent(view.getContext(), ActTabela.class);
                intent.putExtra("nrojogador", nrojogador);
                intent.putExtra("nomejogador", nomejogador);

                if (porrodada == 0) {
                    porrodada = 1;
                }
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

        protected TextView tvposicao;
        protected TextView tvnome;
        protected TextView tvpontos;
        protected TextView tvnaveia;
        protected TextView tvnaveiavisitante;
        protected LinearLayout linearLayoutRegistros;

        public ViewHolder(View itemView) {
            super(itemView);

            tvposicao = (TextView) itemView.findViewById(R.id.tvposicao);
            tvnome = (TextView) itemView.findViewById(R.id.tvnome);
            tvpontos = (TextView) itemView.findViewById(R.id.tvpontos);
            tvnaveia = (TextView) itemView.findViewById(R.id.tvnaveia);
            tvnaveiavisitante = (TextView) itemView.findViewById(R.id.tvnaveiavisitante);
            linearLayoutRegistros = (LinearLayout) itemView.findViewById((R.id.layoutregistros));

        }


    }
}
