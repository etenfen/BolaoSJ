package com.mb16.bolaosj.bolaosj;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CampeaoAdapter extends ArrayAdapter<Campeao> {

    Context context;
    ArrayList<Campeao> mcampeao;

    public CampeaoAdapter(Context context, ArrayList<Campeao> campeao){
        super(context, R.layout.layout_lista_campeao, campeao);
        this.context=context;
        this.mcampeao=campeao;
    }

    static class Holder{
        ImageView imgtime;
        TextView tvpontos;
        TextView tvjogos;
        TextView tvvitorias;
        TextView tvempates;
        TextView tvderrotas;
        TextView tvgolspro;
        TextView tvgolscontra;
        TextView tvsaldo;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Campeao data = getItem(position);

        Holder viewHolder;

        if (convertView == null) {

            viewHolder = new Holder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.layout_lista_campeao, parent, false);

            viewHolder.imgtime = convertView.findViewById(R.id.imgtime);
            viewHolder.tvpontos = convertView.findViewById(R.id.tvpontos);
            viewHolder.tvjogos = convertView.findViewById(R.id.tvjogos);
            viewHolder.tvvitorias = convertView.findViewById(R.id.tvvitorias);
            viewHolder.tvempates = convertView.findViewById(R.id.tvempates);
            viewHolder.tvderrotas = convertView.findViewById(R.id.tvderrotas);
            viewHolder.tvgolspro = convertView.findViewById(R.id.tvgolspro);
            viewHolder.tvgolscontra = convertView.findViewById(R.id.tvgolscontra);
            viewHolder.tvsaldo = convertView.findViewById(R.id.tvsaldo);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (Holder) convertView.getTag();
        }
        viewHolder.imgtime.setImageBitmap(convertToBitmap(data.getEscudotime()));
        viewHolder.tvpontos.setText(String.valueOf(data.getPontos()));
        viewHolder.tvjogos.setText(String.valueOf(data.getJogos()));
        viewHolder.tvvitorias.setText(String.valueOf(data.getVitorias()));
        viewHolder.tvempates.setText(String.valueOf(data.getEmpates()));
        viewHolder.tvderrotas.setText(String.valueOf(data.getDerrotas()));
        viewHolder.tvgolspro.setText(String.valueOf(data.getGolspro()));
        viewHolder.tvgolscontra.setText(String.valueOf(data.getGolscontra()));
        viewHolder.tvsaldo.setText(String.valueOf(data.getSaldo()));
        return convertView;

    }//getView

    private Bitmap convertToBitmap(byte[] b){

        return BitmapFactory.decodeByteArray(b, 0, b.length);
    }
}