package com.appinsideworks.indicadoresestadisticos;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.appinsideworks.indicadoresestadisticos.Controlador.Local.Misc;
import com.appinsideworks.indicadoresestadisticos.Vista.ConexionFail;
import com.appinsideworks.indicadoresestadisticos.Vista.MainFragment;


public class MainActivity extends AppCompatActivity {

    FragmentManager FM;
    FragmentTransaction FT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FM = getFragmentManager();
        FT = FM.beginTransaction();

        Misc msc = new Misc();
        msc.setContext(getApplicationContext());

        if (!msc.comprobarConectividad()) {
            FT.add(R.id.mainView, new ConexionFail(), "Failed");
        } else {
            FT.add(R.id.mainView, new MainFragment(), "Main");
        }

        FT.commit();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
