package com.appinsideworks.indicadoresestadisticos.Vista;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.appinsideworks.indicadoresestadisticos.Controlador.Local.Misc;
import com.appinsideworks.indicadoresestadisticos.R;

public class ConexionFail extends Fragment {

    Button retry;
    Misc misc;

    public ConexionFail() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_conexion_fail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final FragmentManager FM = getActivity().getFragmentManager();
        final FragmentTransaction FT = FM.beginTransaction();

        retry = (Button) getActivity().findViewById(R.id.btnRetryConnection);
        misc = new Misc();
        misc.setContext(getActivity());

        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!misc.comprobarConectividad()) {
                    Snackbar.make(getActivity().findViewById(R.id.mainView), "Verifica tu conexión a internet", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {
                    MainFragment fragment = new MainFragment();

                    FT.remove(FM.findFragmentByTag("Failed"));
                    FT.add(R.id.mainView, fragment, "Main");
                    FT.commit();
                }
            }
        });
    }
}
