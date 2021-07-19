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

public class dataAdapter extends ArrayAdapter<JogoTabela> {

    Context context;
    ArrayList<JogoTabela> mjogotabela;

    public dataAdapter(Context context, ArrayList<JogoTabela> jogotabela){
        super(context, R.layout.layout_lista_jogos_tabela, jogotabela);
        this.context=context;
        this.mjogotabela=jogotabela;
    }

    public class Holder{
        TextView tvmandante;
        TextView tvgolsmandante;
        TextView tvgolsvisitante;
        TextView tvvisitante;
        ImageView imgmandante;
        ImageView imgvisitante;
        TextView tvpto5;
        TextView tvpto3;
        TextView tvpto2;
        TextView tvpto1;
        TextView tvpto0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        JogoTabela data = getItem(position);

        Holder viewHolder;

        if (convertView == null) {

            viewHolder = new Holder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.layout_lista_jogos_tabela, parent, false);

            viewHolder.tvmandante = (TextView) convertView.findViewById(R.id.tvmandante);
            viewHolder.tvgolsmandante = (TextView) convertView.findViewById(R.id.tvgolsmandante);
            viewHolder.tvgolsvisitante = (TextView) convertView.findViewById(R.id.tvgolsvisitante);
            viewHolder.tvvisitante = (TextView) convertView.findViewById(R.id.tvvisitante);
            viewHolder.imgmandante = (ImageView) convertView.findViewById(R.id.imgmandante);
            viewHolder.imgvisitante = (ImageView) convertView.findViewById(R.id.imgvisitante);
            viewHolder.tvpto5 = (TextView) convertView.findViewById(R.id.tvpto5);
            viewHolder.tvpto3 = (TextView) convertView.findViewById(R.id.tvpto3);
            viewHolder.tvpto2 = (TextView) convertView.findViewById(R.id.tvpto2);
            viewHolder.tvpto1 = (TextView) convertView.findViewById(R.id.tvpto1);
            viewHolder.tvpto0 = (TextView) convertView.findViewById(R.id.tvpto0);

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
        viewHolder.tvpto5.setText(String.valueOf(data.getPto5()));
        viewHolder.tvpto3.setText(String.valueOf(data.getPto3()));
        viewHolder.tvpto2.setText(String.valueOf(data.getPto2()));
        viewHolder.tvpto1.setText(String.valueOf(data.getPto1()));
        viewHolder.tvpto0.setText(String.valueOf(data.getPto0()));

        // Return the completed view to render on screen
        return convertView;

    }//getView

    private Bitmap convertToBitmap(byte[] b){

        return BitmapFactory.decodeByteArray(b, 0, b.length);
    }



}
