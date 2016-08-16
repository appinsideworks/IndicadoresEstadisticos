package com.appinsideworks.indicadoresestadisticos.Controlador.Local;

import android.content.Context;
import android.widget.Toast;

import com.appinsideworks.indicadoresestadisticos.Modelo.Indicador;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ramon_a on 8/8/16.
 */
public class ParserIndicadores {


    public List<Indicador> JsonToList(Object object, Context context) {

        ArrayList<Indicador> list = new ArrayList<Indicador>();
        try {
            JSONArray jsonArray = new JSONArray((String) object);
            Indicador indicador;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                indicador = new Indicador(
                        jsonObject.getInt("Desglose_Geografico"),
                        jsonObject.getInt("Indicador"),
                        jsonObject.getString("Nombre"),
                        jsonObject.getBoolean("Ver_Areas"));

                list.add(indicador);
            }
            return list;

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Toast.makeText(context, "Error al cargar la información", Toast.LENGTH_SHORT).show();
            return new ArrayList<Indicador>();
        }
    }
}
