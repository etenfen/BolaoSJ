package com.mb16.bolaosj.bolaosj;

import android.annotation.SuppressLint;
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

    static class Holder{
        TextView tvmandante;
        TextView tvgolsmandanteaposta;
        TextView tvgolsvisitanteaposta;
        TextView tvgolsmandantetabela;
        TextView tvgolsvisitantetabela;
        TextView tvvisitante;
        ImageView imgmandante;
        ImageView imgvisitante;
        TextView tvnaveia;
        TextView tvnaveiavisitante;
        RatingBar rbpontos;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        JogoAposta data = getItem(position);

        Holder viewHolder;

        if (convertView == null) {

            viewHolder = new Holder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.layout_lista_jogos_aposta, parent, false);

            viewHolder.tvmandante = convertView.findViewById(R.id.tvmandante);
            viewHolder.tvgolsmandanteaposta = convertView.findViewById(R.id.tvgolsmandanteaposta);
            viewHolder.tvgolsvisitanteaposta = convertView.findViewById(R.id.tvgolsvisitanteaposta);
            viewHolder.tvgolsmandantetabela = convertView.findViewById(R.id.tvgolsmandantetabela);
            viewHolder.tvgolsvisitantetabela = convertView.findViewById(R.id.tvgolsvisitantetabela);
            viewHolder.tvvisitante = convertView.findViewById(R.id.tvvisitante);
            viewHolder.imgmandante = convertView.findViewById(R.id.imgmandante);
            viewHolder.imgvisitante = convertView.findViewById(R.id.imgvisitante);
            viewHolder.tvnaveia = convertView.findViewById(R.id.tvnaveia);
            viewHolder.tvnaveiavisitante = convertView.findViewById(R.id.tvnaveiavisitante);
            viewHolder.rbpontos = convertView.findViewById(R.id.rbpontos);
            viewHolder.rbpontos.setNumStars(5);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (Holder) convertView.getTag();
        }

        viewHolder.tvgolsmandanteaposta.setText(String.valueOf(data.getGolsmandante()));
        viewHolder.tvgolsvisitanteaposta.setText(String.valueOf(data.getGolsvisitante()));
        viewHolder.tvgolsmandantetabela.setText(String.valueOf(data.getGolsmandantetabela()));
        viewHolder.tvgolsvisitantetabela.setText(String.valueOf(data.getGolsvisitantetabela()));
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