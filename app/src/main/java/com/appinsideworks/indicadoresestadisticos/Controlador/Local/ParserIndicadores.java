package com.appinsideworks.indicadoresestadisticos.Controlador.Local;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.appinsideworks.indicadoresestadisticos.Modelo.Indicador;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TransferQueue;

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

    public String JsonToSeries(Object object) {
        JSONArray result = new JSONArray();

        try {
            JSONObject jsonObject = new JSONObject((String) object);
            JSONObject data = jsonObject.getJSONObject("Data");
            JSONArray series = data.getJSONArray("Serie");

            for (int i = series.length() - 18; i < series.length(); i++) {
                JSONArray temp = new JSONArray();
                temp.put(series.getJSONObject(i).getString("TimePeriod").replace("/","-"));
                temp.put(series.getJSONObject(i).getDouble("CurrentValue"));
                result.put(temp);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result.toString();
    }
}
