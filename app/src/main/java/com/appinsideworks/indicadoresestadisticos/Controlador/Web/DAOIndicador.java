package com.appinsideworks.indicadoresestadisticos.Controlador.Web;


import android.app.ProgressDialog;
import android.content.Context;

import com.appinsideworks.indicadoresestadisticos.Controlador.Local.ParserIndicadores;
import com.appinsideworks.indicadoresestadisticos.Modelo.Indicador;

import java.util.List;

/**
 * Created by ramon_a on 8/8/16.
 */
public class DAOIndicador implements DataDownloadListener {


    private AsyncService asyncService;
    private static String token = "e08947ce-3fed-701c-a71a-c4fcb98c60d1";
    private static String URL = "http://www3.inegi.org.mx/sistemas/api/indicadores/interna_v1_0/API.svc/CatalogoIndicadoresMovil/es/null/null/null/null/null/null/1/null/json/" + token;

    private DataDownloadListener dataDownloadListener;
    private Context context;
    List<Indicador> list;

    public DAOIndicador(Context context) {
        this.context = context;
    }

    public void obtenerIndicadores() {
        asyncService = new AsyncService();
        asyncService.setDataDownloadListener(this);
        asyncService.execute(URL);
    }

    public void setDataDownloadListener(DataDownloadListener dataDownloadListener) {
        this.dataDownloadListener = dataDownloadListener;
    }


    @Override
    public void dataDownloadedSuccessfully(Object data) {
        ParserIndicadores parserIndicadores = new ParserIndicadores();
        list = parserIndicadores.JsonToList(data, context);
        dataDownloadListener.dataDownloadedSuccessfully(list);

    }

    @Override
    public void dataDownloadedFailed() {
        dataDownloadListener.dataDownloadedFailed();
    }
}
