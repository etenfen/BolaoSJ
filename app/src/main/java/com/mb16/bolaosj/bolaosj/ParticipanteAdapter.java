package com.mb16.bolaosj.bolaosj;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ParticipanteAdapter extends ArrayAdapter<Jogador> {

    Context context;
    ArrayList<Jogador> mjogador;

    public ParticipanteAdapter(Context context, ArrayList<Jogador> jogador){
        super(context, R.layout.support_simple_spinner_dropdown_item, jogador);
        this.context=context;
        this.mjogador=jogador;
    }

    public class Holder{
        TextView tvposicao;
        TextView tvnome;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Jogador data = getItem(position);

        Holder viewHolder;

        if (convertView == null) {

            viewHolder = new Holder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.layout_lista_participantes, parent, false);

            viewHolder.tvposicao = (TextView) convertView.findViewById(R.id.tvposicao);
            viewHolder.tvnome = (TextView) convertView.findViewById(R.id.tvnome);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (Holder) convertView.getTag();
        }

        viewHolder.tvposicao.setText(String.valueOf(data.getNroparticipante()));
        viewHolder.tvnome.setText(data.getNome());

        // Return the completed view to render on screen
        return convertView;

    }//getView
}