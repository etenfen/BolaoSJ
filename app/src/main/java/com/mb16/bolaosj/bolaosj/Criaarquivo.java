package com.mb16.bolaosj.bolaosj;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.pdf.PdfDocument;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Criaarquivo {

    private File pasta;
    private File arquivo;
    private Context context;

    public Criaarquivo(File pasta, Context context){
        this.context = context;
        this.pasta = pasta;

        if(!pasta.exists()) {
            pasta.mkdirs();
        }
    }

    public String SalvarPDF(Bitmap bitmap, String nomeDoArquivo) {

        int A4Altura = 842;
        int A4Largura = 595;
        int numerodePaginas = (bitmap.getHeight()/A4Altura)+1;

        Bitmap[] bitmaps = new Bitmap[numerodePaginas];
        int alturaBitmap = bitmap.getHeight()/numerodePaginas;

        arquivo = new File(pasta,nomeDoArquivo + ".pdf");
        PdfDocument arquivoPDF = new PdfDocument();
        for(int p=0; p < numerodePaginas; p++){

            bitmaps[p] = Bitmap.createBitmap(bitmap,0,p*alturaBitmap,bitmap.getWidth(),alturaBitmap);
            PdfDocument.PageInfo info = new PdfDocument.PageInfo.Builder(bitmaps[p].getWidth(), A4Altura,p).create();
            PdfDocument.Page pagina = arquivoPDF.startPage(info);

            Canvas canvas = pagina.getCanvas();

            canvas.drawBitmap(bitmaps[p],null, new Rect(0, 0, bitmaps[p].getWidth(), bitmaps[p].getHeight()), null);
            arquivoPDF.finishPage(pagina);

        }
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
