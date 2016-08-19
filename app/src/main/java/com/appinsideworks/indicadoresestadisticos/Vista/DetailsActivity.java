package com.appinsideworks.indicadoresestadisticos.Vista;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.appinsideworks.indicadoresestadisticos.R;

/**
 * Created by ramon_a on 8/17/16.
 */
public class DetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        TextView headTitle = (TextView) findViewById(R.id.headTitle);
        Toolbar toolbar;


        toolbar = (Toolbar) findViewById(R.id.cardToolbar);
        toolbar.inflateMenu(R.menu.menu_details);

        Intent intent = getIntent();
        String titulo = intent.getStringExtra("Titulo");
        headTitle.setText(titulo);


    }
}
