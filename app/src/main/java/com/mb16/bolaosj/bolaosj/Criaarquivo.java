package com.mb16.bolaosj.bolaosj;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.pdf.PdfDocument;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Criaarquivo {

    final private File pasta;
    //final private Context context;

//    public Criaarquivo(File pasta, Context context){
    public Criaarquivo(File pasta){
        //this.context = context;
        this.pasta = pasta;

        if(!pasta.exists()) {
            pasta.mkdirs();
        }
    }

    public String SalvarPDF(Bitmap bitmap, String nomeDoArquivo) {

        File arquivo = new File(pasta, nomeDoArquivo + ".pdf");
        PdfDocument arquivoPDF = new PdfDocument();

        PdfDocument.PageInfo info = new PdfDocument.PageInfo.Builder(bitmap.getWidth(), bitmap.getHeight(),1).create();
        PdfDocument.Page pagina = arquivoPDF.startPage(info);
        Canvas canvas = pagina.getCanvas();
        canvas.drawBitmap(bitmap,null, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()), null);
        arquivoPDF.finishPage(pagina);
        try {
            arquivo.createNewFile();
            OutputStream streamDeSaida = new FileOutputStream(arquivo);
            arquivoPDF.writeTo(streamDeSaida);
            streamDeSaida.close();
            arquivoPDF.close();
        } catch (IOException e) {
            return "Erro " + e;
        }
        return "sucesso";
    }
}
