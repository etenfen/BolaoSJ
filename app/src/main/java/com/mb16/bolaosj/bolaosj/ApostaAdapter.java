package com.mb16.bolaosj.bolaosj;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class ApostaAdapter extends ArrayAdapter<JogoAposta> {

    Context context;
    ArrayList<JogoAposta> mjogoaposta;

    public ApostaAdapter(Context context, ArrayList<JogoAposta> jogoaposta){
        super(context, R.layout.layout_lista_jogos_aposta, jogoaposta);
        this.context=context;
        this.mjogoaposta=jogoaposta;
    }

    public class Holder{
        TextView tvmandante;
        TextView tvgolsmandante;
        TextView tvgolsvisitante;
        TextView tvvisitante;
        ImageView imgmandante;
        ImageView imgvisitante;
        TextView tvnaveia;
        TextView tvnaveiavisitante;
        RatingBar rbpontos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        JogoAposta data = getItem(position);

        Holder viewHolder;

        if (convertView == null) {

            viewHolder = new Holder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.layout_lista_jogos_aposta, parent, false);

            viewHolder.tvmandante = (TextView) convertView.findViewById(R.id.tvmandante);
            viewHolder.tvgolsmandante = (TextView) convertView.findViewById(R.id.tvgolsmandante);
            viewHolder.tvgolsvisitante = (TextView) convertView.findViewById(R.id.tvgolsvisitante);
            viewHolder.tvvisitante = (TextView) convertView.findViewById(R.id.tvvisitante);
            viewHolder.imgmandante = (ImageView) convertView.findViewById(R.id.imgmandante);
            viewHolder.imgvisitante = (ImageView) convertView.findViewById(R.id.imgvisitante);
            viewHolder.tvnaveia = (TextView) convertView.findViewById(R.id.tvnaveia);
            viewHolder.tvnaveiavisitante = (TextView) convertView.findViewById(R.id.tvnaveiavisitante);
            viewHolder.rbpontos = (RatingBar) convertView.findViewById(R.id.rbpontos);
            viewHolder.rbpontos.setNumStars(5);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (Holder) convertView.getTag();
        }

        viewHolder.tvgolsmandante.setText(String.valueOf(data.getGolsmandante()));
        viewHolder.tvgolsvisitante.setText(String.valueOf(data.getGolsvisitante()));
        viewHolder.tvmandante.setText(data.getMandante());
        viewHolder.tvvisitante.setText(data.getVisitante());
        viewHolder.imgmandante.setImageBitmap(convertToBitmap(data.getEscudomandante()));
        viewHolder.imgvisitante.setImageBitmap(convertToBitmap(data.getEscudovisitante()));
        viewHolder.rbpontos.setRating(data.getPontos());

        if (data.getNaveia() == 1) {
            viewHolder.tvnaveia.setText("NA VEIA");
        } else {
            viewHolder.tvnaveia.setText("");
        }
        if (data.getNaveiavisitante() == 1)
            viewHolder.tvnaveiavisitante.setText("NA VEIA VISITANTE");
        else {
            viewHolder.tvnaveiavisitante.setText("");
        }

        return convertView;

    }//getView

    private Bitmap convertToBitmap(byte[] b){

        return BitmapFactory.decodeByteArray(b, 0, b.length);
    }
}