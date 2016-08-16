package com.appinsideworks.indicadoresestadisticos.Controlador.Web;

import java.util.Objects;

/**
 * Created by ramon_a on 8/8/16.
 */
public interface DataDownloadListener {

    void dataDownloadedSuccessfully(Object data);

    void dataDownloadedFailed();
}
