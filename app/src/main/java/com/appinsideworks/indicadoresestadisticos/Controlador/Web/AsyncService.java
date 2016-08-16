package com.appinsideworks.indicadoresestadisticos.Controlador.Web;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;


/**
 * Created by ramon_a on 8/8/16.
 */
public class AsyncService extends AsyncTask<String, Void, String> {

    private String result;
    private HttpClient httpClient;
    private HttpGet httpGet;
    private HttpResponse httpResponse;
    private HttpEntity httpEntity;

    public String status;

    DataDownloadListener dataDownloadListener;


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
        String URL = strings[0];
        status = "running";

        httpClient = new DefaultHttpClient();
        httpGet = new HttpGet(URL);
        try {
            httpResponse = httpClient.execute(httpGet);
            httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity);
            return result;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (result != null) {
            dataDownloadListener.dataDownloadedSuccessfully(s);

        } else {
            dataDownloadListener.dataDownloadedFailed();
        }
    }

    public void setDataDownloadListener(DataDownloadListener dataDownloadListener) {
        this.dataDownloadListener = dataDownloadListener;
    }
}
