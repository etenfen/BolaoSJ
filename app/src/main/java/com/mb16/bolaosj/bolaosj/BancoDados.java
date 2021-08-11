package com.mb16.bolaosj.bolaosj;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import static java.lang.Integer.*;

public class BancoDados extends SQLiteOpenHelper {

    public static final int VERSAOBANCO = 1;
    public static final String NOMEBANCO = "bolao.sqlite";
    public static String DBPATH = "/data/data/com.mb16.bolaosj.bolaosj/databases/";
    private SQLiteDatabase mDB;
    Context context;

    public BancoDados(Context context) {
        super(context, NOMEBANCO, null, VERSAOBANCO);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

 /*   public void openDatabase() {
        String dbPath = context.getDatabasePath(NOMEBANCO).getPath();
        if (mDB != null && mDB.isOpen()) return;
        mDB = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDatabase() {
        if (mDB != null) mDB.close();
    }*/

    public boolean copiaBanco(Context context) {
        try {
            InputStream inputStream = context.getAssets().open(NOMEBANCO);
            String outFile = DBPATH + NOMEBANCO;
            OutputStream outputStream = new FileOutputStream(outFile);
            byte[] buff = new byte[1024];
            int length;
            while ((length = inputStream.read(buff)) > 0) outputStream.write(buff, 0, length);
            outputStream.flush();
            outputStream.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<JogoTabela> allJogoTabela(int _rodada) {
        List<JogoTabela> listJogoTabela = new ArrayList<>();
        mDB = this.getWritableDatabase();
        String sql;
        if (_rodada != 0) {
            sql = "SELECT " +
                    "   a.nrojogo, a.rodada, t.nometime as mandante, t.escudotime, a.golsmandante, " +
                    "   a.golsvisitante, t2.escudotime, t2.nometime as visitante, " +
                    "   (select count(*) from tabaposta p where pontos = 5 and p.nrojogo = a.nrojogo) as pto5, " +
                    "   (select count(*) from tabaposta p where pontos = 3 and p.nrojogo = a.nrojogo) as pto3, " +
                    "   (select count(*) from tabaposta p where pontos = 2 and p.nrojogo = a.nrojogo) as pto2, " +
                    "   (select count(*) from tabaposta p where pontos = 1 and p.nrojogo = a.nrojogo) as pto1, " +
                    "   (select count(*) from tabaposta p where pontos = 0 and p.nrojogo = a.nrojogo) as pto0 " +
            "from " +
                    "   tabtabela a " +
                    "left join tabtimes t on (a.codmandante = t.nrotime) " +
                    "left join tabtimes t2 on (a.codvisitante = t2.nrotime) " +
                    "where " +
                    " rodada = " + _rodada;
        } else {
            sql = "SELECT " +
                    "   a.nrojogo, a.rodada, t.nometime as mandante, t.escudotime, a.golsmandante, " +
                    "   a.golsvisitante, t2.escudotime, t2.nometime as visitante, " +
                    "   (select count(*) from tabaposta p where pontos = 5 and p.nrojogo = a.nrojogo) as pto5, " +
                    "   (select count(*) from tabaposta p where pontos = 3 and p.nrojogo = a.nrojogo) as pto3, " +
                    "   (select count(*) from tabaposta p where pontos = 2 and p.nrojogo = a.nrojogo) as pto2, " +
                    "   (select count(*) from tabaposta p where pontos = 1 and p.nrojogo = a.nrojogo) as pto1, " +
                    "   (select count(*) from tabaposta p where pontos = 0 and p.nrojogo = a.nrojogo) as pto0 " +
                    "from " +
                    "   tabtabela a " +
                    "left join tabtimes t on (a.codmandante = t.nrotime) " +
                    "left join tabtimes t2 on (a.codvisitante = t2.nrotime) ";
        }
        Cursor cursor = mDB.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                JogoTabela jogotabela = new JogoTabela();
                jogotabela.setNrojogo(Integer.parseInt(cursor.getString(0)));
                jogotabela.setRodada(Integer.parseInt(cursor.getString(1)));
                jogotabela.setMandante(cursor.getString(2));
                jogotabela.setEscudomandante(cursor.getBlob(3));
                jogotabela.setGolsmandante(Integer.parseInt(cursor.getString(4)));
                jogotabela.setGolsvisitante(Integer.parseInt(cursor.getString(5)));
                jogotabela.setEscudovisitante(cursor.getBlob(6));
                jogotabela.setVisitante(cursor.getString(7));
                jogotabela.setPto5(cursor.getInt(8));
                jogotabela.setPto3(cursor.getInt(9));
                jogotabela.setPto2(cursor.getInt(10));
                jogotabela.setPto1(cursor.getInt(11));
                jogotabela.setPto0(cursor.getInt(12));
                listJogoTabela.add(jogotabela);

            } while (cursor.moveToNext());
        }
        cursor.close();
        mDB.close();
        return listJogoTabela;
    }

    public List<JogoAposta> allJogoAposta(int _rodada, int _nrojogador) {
        List<JogoAposta> listJogoAposta = new ArrayList<>();
        mDB = this.getWritableDatabase();
        String sql = "";
        if (_rodada != 0 && _nrojogador != 0) {
            sql = "SELECT " +
                    "   a.nrojogo, a.rodada, t.nometime as mandante, t.escudotime, p.golsmandante," +
                    "   p.golsvisitante, t2.nometime as visitante, t2.escudotime, p.pontos, p.naveia, p.naveiavisitante, " +
                    "   a.golsmandante, a.golsvisitante " +
                    "from " +
                    "   tabtabela a " +
                    "left join tabtimes t on (a.codmandante = t.nrotime) " +
                    "left join tabtimes t2 on (a.codvisitante = t2.nrotime) " +
                    "left join tabaposta p on (a.nrojogo = p.nrojogo)  " +
                    "where " +
                    " nrojogador = " + _nrojogador + " and " +
                    " rodada = " + _rodada;
        }
        if (_rodada == 0 && _nrojogador == 0) {
            sql = "SELECT " +
                    "   a.nrojogo, a.rodada, t.nometime as mandante, t.escudotime, p.golsmandante," +
                    "   p.golsvisitante, t2.nometime as visitante, t2.escudotime, p.pontos, p.naveia, p.naveiavisitante, " +
                    "   a.golsmandante, a.golsvisitante " +
                    "from " +
                    "   tabtabela a " +
                    "left join tabtimes t on (a.codmandante = t.nrotime) " +
                    "left join tabtimes t2 on (a.codvisitante = t2.nrotime) " +
                    "left join tabaposta p on (a.nrojogo = p.nrojogo)  ";

        }
        if (_rodada != 0 && _nrojogador == 0) {
            sql = "SELECT " +
                    "   a.nrojogo, a.rodada, t.nometime as mandante, t.escudotime, p.golsmandante," +
                    "   p.golsvisitante, t2.nometime as visitante, t2.escudotime, p.pontos, p.naveia, p.naveiavisitante, " +
                    "   a.golsmandante, a.golsvisitante " +
                    "from " +
                    "   tabtabela a " +
                    "left join tabtimes t on (a.codmandante = t.nrotime) " +
                    "left join tabtimes t2 on (a.codvisitante = t2.nrotime) " +
                    "left join tabaposta p on (a.nrojogo = p.nrojogo)  " +
                    "where " +
                    " rodada = " + _rodada;
        }
        if (_rodada == 0 && _nrojogador != 0) {
            sql = "SELECT " +
                    "   a.nrojogo, a.rodada, t.nometime as mandante, t.escudotime, p.golsmandante," +
                    "   p.golsvisitante, t2.nometime as visitante, t2.escudotime, p.pontos, p.naveia, p.naveiavisitante, " +
                    "   a.golsmandante, a.golsvisitante " +
                    "from " +
                    "   tabtabela a " +
                    "left join tabtimes t on (a.codmandante = t.nrotime) " +
                    "left join tabtimes t2 on (a.codvisitante = t2.nrotime) " +
                    "left join tabaposta p on (a.nrojogo = p.nrojogo)  " +
                    "where " +
                    " nrojogador = " + _nrojogador;
        }
        Cursor cursor = mDB.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                JogoAposta jogoaposta = new JogoAposta();
                jogoaposta.setNrojogo(parseInt(cursor.getString(0)));
                jogoaposta.setRodada(parseInt(cursor.getString(1)));
                jogoaposta.setMandante(cursor.getString(2));
                jogoaposta.setEscudomandante(cursor.getBlob(3));
                jogoaposta.setGolsmandante(parseInt(cursor.getString(4)));
                jogoaposta.setGolsvisitante(parseInt(cursor.getString(5)));
                jogoaposta.setVisitante(cursor.getString(6));
                jogoaposta.setEscudovisitante(cursor.getBlob(7));
                jogoaposta.setPontos(parseInt(cursor.getString(8)));
                jogoaposta.setNaveia(parseInt(cursor.getString(9)));
                jogoaposta.setNaveiavisitante(parseInt(cursor.getString(10)));
                jogoaposta.setGolsmandantetabela(parseInt(cursor.getString(11)));
                jogoaposta.setGolsvisitantetabela(parseInt(cursor.getString(12)));

                listJogoAposta.add(jogoaposta);

            } while (cursor.moveToNext());
        }
        cursor.close();
        mDB.close();
        return listJogoAposta;
    }

    public List<Jogador> allJogadores(int _rodada, boolean _total, boolean _busca, String _strnome) {

        List<Jogador> listJogador = new ArrayList<>();
        mDB = this.getWritableDatabase();
        String sql;

        if (!_total) {
            sql = "select " +
                    "  tabjogador.nrojogador, tabjogador.nome, sum(pontos) as totalpontos, sum(naveia) as totalnaveia, sum(naveiavisitante) as totalnaveiavisitante, " +
                    "  (select posicao from tabrodadapos where nrojogador = tabjogador.nrojogador and rodada = " + (_rodada-1) +") as posant, " +
                    "  (select posicao from tabrodadapos where nrojogador = tabjogador.nrojogador and rodada = " + _rodada +") as posatual " +
                   "from " +
                    "  tabaposta " +
                    "left join tabjogador on (tabaposta.nrojogador = tabjogador.nrojogador) " +
                    "left join tabtabela  on (tabaposta.nrojogo = tabtabela.nrojogo) " +
                    "where" +
                    "  tabtabela.rodada = " + _rodada;
            if (_busca){
                sql += "  and tabjogador.nome like '%"+_strnome+"%' ";
            }
        } else {
            sql = "select " +
                  "  tabjogador.nrojogador, tabjogador.nome, sum(pontos) as totalpontos, sum(naveia) as totalnaveia, sum(naveiavisitante) as totalnaveiavisitante, " +
                  "  (select posicao from tabrodadapos where nrojogador = tabjogador.nrojogador and rodada = " + (_rodada-1) +") as posant, " +
                  "  (select posicao from tabrodadapos where nrojogador = tabjogador.nrojogador and rodada = " + _rodada +") as posatual " +
                  "from " +
                  "  tabaposta " +
                  "left join tabjogador on (tabaposta.nrojogador = tabjogador.nrojogador) " +
                  "left join tabtabela  on (tabaposta.nrojogo = tabtabela.nrojogo) " +
                  "where " +
                  "  tabtabela.rodada <= " + _rodada;
            if (_busca){
                sql += "  and tabjogador.nome like '%"+_strnome+"%' ";
            }
        }
        sql +=  " group by tabjogador.nrojogador " +
                " order by totalpontos desc, totalnaveia desc, totalnaveiavisitante desc ";

        Cursor cursor = mDB.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                Jogador jogador = new Jogador();
                jogador.setNroparticipante(parseInt(cursor.getString(0)));
                //jogador.setPosicao(cursor.getInt(0));
                jogador.setNome(cursor.getString(1));
                jogador.setPontos(parseInt(cursor.getString(2)));
                jogador.setNaveia(parseInt(cursor.getString(3)));
                jogador.setNaveiavisitante(parseInt(cursor.getString(4)));
                try {
                      jogador.setPosant(parseInt(cursor.getString(5)));
                    } catch (Exception ex) {
                        jogador.setPosant(0);
                    }
                try {
                    jogador.setPosatual(parseInt(cursor.getString(6)));
                } catch (Exception ex) {
                    jogador.setPosatual(0);
                }

                listJogador.add(jogador);

            } while (cursor.moveToNext());
        }
        cursor.close();
        mDB.close();

        return listJogador;
    }

    public List<Jogador> allParticipantes() {

        List<Jogador> listParticipante = new ArrayList<>();
        mDB = this.getWritableDatabase();
        String sql = "select " +
                     "  nrojogador, nome " +
                     "from " +
                     "  tabjogador " +
                     "  order by nome ";
        Cursor cursor = mDB.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                Jogador jogador = new Jogador();
                jogador.setNroparticipante(parseInt(cursor.getString(0)));
                jogador.setNome(cursor.getString(1));
                listParticipante.add(jogador);

            } while (cursor.moveToNext());
        }
        cursor.close();
        mDB.close();

        return listParticipante;
    }

    public List<Campeao> allCampeoes(int _nrojogador) {

        List<Campeao> listCampeao = new ArrayList<>();
        mDB = this.getWritableDatabase();
        String sql;
            sql = "select " +
                    "    tabtimes.nometime, tabtimes.escudotime, tabcampeao.pontos, tabcampeao.jogos, tabcampeao.vitorias, tabcampeao.empates, tabcampeao.derrotas, " +
                    "    tabcampeao.golspro, tabcampeao.golscontra, tabcampeao.saldo " +
                    "from " +
                    "    tabcampeao " +
                    "left join tabtimes on (tabcampeao.codtime = tabtimes.nrotime) " +
                    "where " +
                    "    tabcampeao.nrojogador = " + _nrojogador +
                    " order by " +
                    "    pontos desc, vitorias desc, saldo desc, golspro desc ";
        Cursor cursor = mDB.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                Campeao campeao = new Campeao();
                campeao.setNometime(cursor.getString(0));
                campeao.setEscudotime(cursor.getBlob(1));
                campeao.setPontos(Integer.parseInt(cursor.getString(2)));
                campeao.setJogos(Integer.parseInt(cursor.getString(3)));
                campeao.setVitorias(Integer.parseInt(cursor.getString(4)));
                campeao.setEmpates(Integer.parseInt(cursor.getString(5)));
                campeao.setDerrotas(Integer.parseInt(cursor.getString(6)));
                campeao.setGolspro(Integer.parseInt(cursor.getString(7)));
                campeao.setGolscontra(Integer.parseInt(cursor.getString(8)));


                listCampeao.add(campeao);

            } while (cursor.moveToNext());
        }
        cursor.close();
        mDB.close();

        return listCampeao;
    }

    void atualizaJogoTabela(JogoTabela jogotabela, Boolean updateAposta) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        int nrojogo = jogotabela.getNrojogo();
        values.put("golsmandante", jogotabela.getGolsmandante());
        values.put("golsvisitante", jogotabela.getGolsvisitante());
        values.put("vencedor", jogotabela.getVencedor());
        values.put("empate", jogotabela.getEmpate());
        db.update("tabtabela", values, "nrojogo = ?", new String[]{String.valueOf(jogotabela.getNrojogo())});
        if (updateAposta) {
            atualizaJogoAposta(nrojogo, 0);
            atualizaJogoAposta(nrojogo, 1);
            atualizaJogoAposta(nrojogo, 2);
            atualizaJogoAposta(nrojogo, 3);
            atualizaJogoAposta(nrojogo, 4);
            atualizaJogoAposta(nrojogo, 5);
        }
        db.close();
    }

    public void atualizaJogoAposta(int _nrojogo, int _pontos) {

        SQLiteDatabase db = this.getWritableDatabase();

        //ContentValues values = new ContentValues();
        switch (_pontos) {
            case 0://zera os pontos para caso o usuario colocar o placar pela segunda vez
                db.execSQL("update tabaposta set pontos = 0, naveia = 0, naveiavisitante = 0 where nrojogo = "+_nrojogo);
                break;
            case 1://se acertar um dos gols
                db.execSQL(" UPDATE tabaposta set pontos = 1 "+
                           " where nro in ( "+
                           "               select nro from tabaposta "+
                           "               left join tabtabela on (tabaposta.nrojogo = tabtabela.nrojogo) "+
                           "               where "+
                           "                 tabaposta.nrojogo = "+_nrojogo+
                           "                 and (tabtabela.golsmandante = tabaposta.golsmandante OR tabtabela.golsvisitante = tabaposta.golsvisitante) "+
                           "                 and (tabtabela.vencedor <> tabaposta.vencedor) "+
                           "                 and (tabaposta.calcula = 'S'))");
                break;
            case 2://se acertar a vitoria de um dos dois times
                db.execSQL(" UPDATE tabaposta set pontos = 2 "+
                                " where nro in ( "+
                                "               select nro from tabaposta "+
                                "               left join tabtabela on (tabaposta.nrojogo = tabtabela.nrojogo) "+
                                "               where "+
                                "                 tabaposta.nrojogo = "+_nrojogo+
                                "                 and (tabtabela.vencedor = tabaposta.vencedor) "+
                                "                 and (tabtabela.golsmandante <> tabaposta.golsmandante and tabtabela.golsvisitante <> tabaposta.golsvisitante) "+
                                "                 and (tabaposta.calcula = 'S'))");
                break;
            case 3://se acertar a vitoria de um dos dois times + um dos dois gols
                db.execSQL(" UPDATE tabaposta set pontos = 3 "+
                        " where nro in ( "+
                        "               select nro from tabaposta "+
                        "               left join tabtabela on (tabaposta.nrojogo = tabtabela.nrojogo) "+
                        "               where "+
                        "                 tabaposta.nrojogo = "+_nrojogo+
                        "                 and (tabtabela.vencedor = tabaposta.vencedor) "+
                        "                 and (tabtabela.golsmandante = tabaposta.golsmandante OR tabtabela.golsvisitante = tabaposta.golsvisitante) "+
                        "                 and not(tabtabela.golsmandante = tabaposta.golsmandante and tabtabela.golsvisitante = tabaposta.golsvisitante) "+
                        "                 and (tabaposta.calcula = 'S'))");
                break;
            case 4://se acertar na veia visitante
                db.execSQL(" UPDATE tabaposta set pontos = 5, naveia = 1, naveiavisitante = 1 "+
                        " where nro in ( "+
                        "               select nro from tabaposta "+
                        "               left join tabtabela on (tabaposta.nrojogo = tabtabela.nrojogo) "+
                        "               where "+
                        "                 tabaposta.nrojogo = "+_nrojogo+
                        "                 and (tabtabela.golsmandante = tabaposta.golsmandante and tabtabela.golsvisitante = tabaposta.golsvisitante) "+
                        "                 and (tabaposta.vencedor = 'V') "+
                        "                 and (tabaposta.calcula = 'S'))");
                break;
            case 5://se acertar na veia mandante
                db.execSQL(" UPDATE tabaposta set pontos = 5, naveia = 1 "+
                        " where nro in ( "+
                        "               select nro from tabaposta "+
                        "               left join tabtabela on (tabaposta.nrojogo = tabtabela.nrojogo) "+
                        "               where "+
                        "                 tabaposta.nrojogo = "+_nrojogo+
                        "                 and (tabtabela.golsmandante = tabaposta.golsmandante and tabtabela.golsvisitante = tabaposta.golsvisitante) "+
                        "                 and (tabaposta.vencedor = 'M' or tabaposta.empate = 'S') "+
                        "                 and (tabaposta.calcula = 'S'))");
                break;
        }

        db.close();
    }

    JogoTabela selecionarJogoTabela(int _nrojogo) {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT " +
                                         "    a.nrojogo, a.rodada, t.nometime as mandante, t.escudotime, a.golsmandante, a.golsvisitante, " +
                                         "    t2.nometime as visitante, t2.escudotime " +
                                         "from " +
                                         "tabtabela a " +
                                         "left join tabtimes t on (a.codmandante = t.nrotime) " +
                                         "left join tabtimes t2 on (a.codvisitante = t2.nrotime) " +
                                         "left join tabaposta p on (a.nrojogo = p.nrojogo) " +
                                         "where "+
                                         " a.nrojogo = ?",new String[]{String.valueOf(_nrojogo)});
        if (cursor != null) {
            cursor.moveToFirst();
        }
        assert cursor != null;
        JogoTabela jogotabela = new JogoTabela(parseInt(cursor.getString(0)),
                                               parseInt(cursor.getString(1)),
                                               cursor.getString(2),
                                               cursor.getBlob(3),
                                               cursor.getInt(4),
                                               cursor.getInt(5),
                                               cursor.getString(6),
                                               cursor.getBlob(7));

        cursor.close();
        return jogotabela;
    }

/*    public void limpaPontosJogo(int _nrojogo) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(" UPDATE tabaposta set " +
                   "    pontos = 0," +
                   "    naveia = 0," +
                   "    naveiavisitante = 0 "+
                   " where nrojogo = "+_nrojogo);
        db.close();
    }*/

    public void atualizaPosicaoPontuacao(int _rodada){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE from tabrodadapos " +
                   "where " +
                   "  rodada = "+ _rodada);

        db.execSQL("insert into tabrodadapos " +
                   "select " +_rodada +
                   ", nrojogador, ROW_NUMBER() OVER() AS posicao " +
                   "from " +
                   "(select " +
                   "   tabjogador.nrojogador, tabjogador.nome, sum(pontos) as totalpontos, sum(naveia) as totalnaveia, sum(naveiavisitante) as totalnaveiavisitante, " +
                   "   (select posicao from tabrodadapos where nrojogador = tabjogador.nrojogador and rodada = " + (_rodada-1) + ") as posant " +
                   "from " +
                   "   tabaposta " +
                   "left join tabjogador on (tabaposta.nrojogador = tabjogador.nrojogador) " +
                   "left join tabtabela  on (tabaposta.nrojogo = tabtabela.nrojogo) " +
                   "where " +
                   "   tabtabela.rodada <= "+_rodada +
                   " group by " +
                   "   tabjogador.nrojogador " +
                   "order by " +
                   "   totalpontos desc, totalnaveia desc, totalnaveiavisitante desc ) ");
        db.close();
    }

    public int retornaRodada(){

        mDB = this.getWritableDatabase();
        String sql;
        sql = "select max(rodada) as rodada from tabrodadapos ";
        Cursor cursor = mDB.rawQuery(sql, null);
        cursor.moveToFirst();
        int rodada;
        try{
           rodada = Integer.parseInt(cursor.getString(cursor.getColumnIndex("rodada")));
        } catch (Exception e) {
            rodada = 1;
        }

        cursor.close();
        mDB.close();

        return rodada;
    }
}