package com.mb16.bolaosj.bolaosj;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class JogadorAdapter extends ArrayAdapter<Jogador> {

    Context context;
    ArrayList<Jogador> mjogador;

    public JogadorAdapter(Context context, ArrayList<Jogador> jogador) {
        super(context, R.layout.layout_lista_pontos, jogador);
        this.context = context;
        this.mjogador = jogador;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Jogador data = getItem(position);

        Holder viewHolder;
       // String pos1 = "1";
       // String pos2 = "2";
       // String pos3 = "3";
       // String pos4 = "4";

        if (convertView == null) {

            viewHolder = new Holder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.layout_lista_pontos, parent, false);

            viewHolder.tvposicao = (TextView) convertView.findViewById(R.id.tvposicao);
            viewHolder.tvnome = (TextView) convertView.findViewById(R.id.tvnome);
            viewHolder.tvpontos = (TextView) convertView.findViewById(R.id.tvpontos);
            viewHolder.tvnaveia = (TextView) convertView.findViewById(R.id.tvnaveia);
            viewHolder.tvnaveiavisitante = (TextView) convertView.findViewById(R.id.tvnaveiavisitante);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (Holder) convertView.getTag();
        }

        viewHolder.tvposicao.setText(String.valueOf(position + 1));
        viewHolder.tvnome.setText(data.getNome());
        viewHolder.tvpontos.setText(String.valueOf(data.getPontos()));
        viewHolder.tvnaveia.setText(String.valueOf(data.getNaveia()));
        viewHolder.tvnaveiavisitante.setText(String.valueOf(data.getNaveiavisitante()));

/*        if (pos1.equals(viewHolder.tvposicao)){

            // Altera a cor de fundo da lista:
            viewHolder.tvposicao.setBackgroundColor(convertView.getResources().getColor(R.color.colorPrimaryDark));
            viewHolder.tvnome.setBackgroundColor(convertView.getResources().getColor(R.color.colorPrimaryDark));
            viewHolder.tvnaveia.setBackgroundColor(convertView.getResources().getColor(R.color.colorPrimaryDark));
            viewHolder.tvnaveiavisitante.setBackgroundColor(convertView.getResources().getColor(R.color.colorPrimaryDark));
            viewHolder.tvpontos.setBackgroundColor(convertView.getResources().getColor(R.color.colorPrimaryDark));
        } else {
            viewHolder.tvposicao.setBackgroundColor(convertView.getResources().getColor(R.color.colorAccent));
            viewHolder.tvnome.setBackgroundColor(convertView.getResources().getColor(R.color.colorAccent));
            viewHolder.tvnaveia.setBackgroundColor(convertView.getResources().getColor(R.color.colorAccent));
            viewHolder.tvnaveiavisitante.setBackgroundColor(convertView.getResources().getColor(R.color.colorAccent));
            viewHolder.tvpontos.setBackgroundColor(convertView.getResources().getColor(R.color.colorAccent));
        }*/


        // Return the completed view to render on screen
        return convertView;

    }//getView

    public class Holder {
        TextView tvposicao;
        TextView tvnome;
        TextView tvpontos;
        TextView tvnaveia;
        TextView tvnaveiavisitante;
    }
}