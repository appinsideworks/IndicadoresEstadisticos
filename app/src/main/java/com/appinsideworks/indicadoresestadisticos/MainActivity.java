package com.appinsideworks.indicadoresestadisticos;

import com.appinsideworks.indicadoresestadisticos.Controlador.Local.AdapterIndicador;
import com.appinsideworks.indicadoresestadisticos.Controlador.Local.Misc;
import com.appinsideworks.indicadoresestadisticos.Controlador.Web.DAOIndicador;
import com.appinsideworks.indicadoresestadisticos.Controlador.Web.DataDownloadListener;
import com.appinsideworks.indicadoresestadisticos.Modelo.Indicador;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;
import xyz.danoz.recyclerviewfastscroller.vertical.VerticalRecyclerViewFastScroller;


public class MainActivity extends AppCompatActivity implements DataDownloadListener {
    // Remove the below line after defining your own ad unit ID.

    private static final String TOAST_TEXT = "Test ads are being shown. "
            + "To show live ads, replace the ad unit ID in res/values/strings.xml with your own ad unit ID.";

    Misc misc;
    SwipeRefreshLayout swipeContainer;
    RecyclerView recyclerView;
    VerticalRecyclerViewFastScroller fastScroller;
    AdapterIndicador adapter;
    DAOIndicador daoIndicador;
    AdView adView;
    AdRequest adRequest;
    LinearLayoutManager manager;
    FloatingActionButton fab;

    FragmentManager FM;
    FragmentTransaction FT;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     *
     * Some Changes here!!! :D Hello GitHub Plataform! :D
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        misc = new Misc();
        misc.setContext(this);
        FM = getFragmentManager();

        if (!misc.comprobarConectividad()) {
            Snackbar.make(findViewById(R.id.mainView), "Verifica tu conexión a internet", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        } else {

            // Load an ad into the AdMob banner view.
            adView = (AdView) findViewById(R.id.adView);
            swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
            fastScroller = (VerticalRecyclerViewFastScroller) findViewById(R.id.fastScroller);

            adRequest = new AdRequest.Builder()
                    .setRequestAgent("android_studio:ad_template").build();
            daoIndicador = new DAOIndicador(MainActivity.this);
            daoIndicador.setDataDownloadListener(this);
            daoIndicador.obtenerIndicadores();

            manager = new LinearLayoutManager(this);

            recyclerView = (RecyclerView) findViewById(R.id.rc_view);
            recyclerView.setLayoutManager(manager);

            fastScroller.setRecyclerView(recyclerView);
            fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setBackgroundColor(Color.parseColor("#4499cc"));
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Developer: Ramón Álvarez Baeza", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
            swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    List<Indicador> lista_vacia = new ArrayList<Indicador>();
                    recyclerView.setAdapter(new AdapterIndicador(lista_vacia, MainActivity.this));
                    daoIndicador.obtenerIndicadores();
                }
            });
            swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light);

        }

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void dataDownloadedSuccessfully(Object data) {

        List<Indicador> list = (List<Indicador>) data;
        adapter = new AdapterIndicador(list, MainActivity.this);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new SlideInLeftAnimator());
        swipeContainer.setRefreshing(false);
        adView.loadAd(adRequest);

    }

    @Override
    public void dataDownloadedFailed() {

    }
}
