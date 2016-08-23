package com.appinsideworks.indicadoresestadisticos.Controlador.Web;


import android.content.Context;

import com.appinsideworks.indicadoresestadisticos.Controlador.Local.ParserIndicadores;
import com.appinsideworks.indicadoresestadisticos.Modelo.Indicador;

import java.util.List;

/**
 * Created by ramon_a on 8/8/16.
 */
public class DAOIndicador implements DataDownloadListener {

    private AsyncService asyncService;

    private String TOKEN = "e08947ce-3fed-701c-a71a-c4fcb98c60d1";
    private String DATATYPE;

    private DataDownloadListener dataDownloadListener;
    private Context context;
    List<Indicador> list;

    public DAOIndicador(Context context) {
        this.context = context;
    }

    public void obtenerIndicadores() {
        DATATYPE = "LISTADO";
        String URL = "http://www3.inegi.org.mx/sistemas/api/indicadores/interna_v1_0/API.svc/CatalogoIndicadoresMovil/es/null/null/null/null/null/null/1/null/json/" + TOKEN;
        System.out.println(URL);

        asyncService = new AsyncService();
        asyncService.setDataDownloadListener(this);
        asyncService.execute(URL);
    }

    public void obtenerIndicador(int indicador) {
        DATATYPE = "INDICADOR";
        String URL = "http://www3.inegi.org.mx/sistemas/api/indicadores/v1/Indicador/" + indicador + "/00/es/false/json/" + TOKEN;
        System.out.println(URL);
        asyncService = new AsyncService();
        asyncService.setDataDownloadListener(this);
        asyncService.execute(URL);
    }

    public void setDataDownloadListener(DataDownloadListener dataDownloadListener) {
        this.dataDownloadListener = dataDownloadListener;
    }


    @Override
    public void dataDownloadedSuccessfully(Object data) {

        switch (DATATYPE) {
            case "LISTADO":
                ParserIndicadores parserIndicadores = new ParserIndicadores();
                list = parserIndicadores.JsonToList(data, context);
                dataDownloadListener.dataDownloadedSuccessfully(list);
                break;
            case "INDICADOR":
                dataDownloadListener.dataDownloadedSuccessfully(data);
                break;
            case "METADATO":
                break;
            case "COMPARATIVO":
                break;
        }

    }

    @Override
    public void dataDownloadedFailed() {
        dataDownloadListener.dataDownloadedFailed();
    }
}
