package com.appinsideworks.indicadoresestadisticos.Vista;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.appinsideworks.indicadoresestadisticos.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

/**
 * Created by ramon_a on 8/17/16.
 */
public class DetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView headTitle = (TextView) findViewById(R.id.headTitle);
        Toolbar toolbar;
        AdView adView;
        AdRequest adRequest;


        toolbar = (Toolbar) findViewById(R.id.cardToolbar);
        toolbar.inflateMenu(R.menu.menu_details);

        Intent intent = getIntent();
        String titulo = intent.getStringExtra("Titulo");
        headTitle.setText(titulo);

        adView = (AdView) findViewById(R.id.adViewDetails);
        adRequest = new AdRequest.Builder().addTestDevice("18F7A617925794A546F8AE71D6C1DF11").build();
        adView.loadAd(adRequest);

    }
}
