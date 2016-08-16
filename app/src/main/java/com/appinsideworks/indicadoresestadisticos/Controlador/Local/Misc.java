package com.appinsideworks.indicadoresestadisticos.Controlador.Local;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by ramon_a on 8/10/16.
 */

public class Misc {

    private Context context;


    public void setContext(Context context) {
        this.context = context;
    }

    public boolean comprobarConectividad() {
        // TODO Auto-generated method stub
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if ((networkInfo == null || !networkInfo.isConnected() || !networkInfo
                .isAvailable())) {
            return false;
        } else {
            return true;
        }
    }
}
