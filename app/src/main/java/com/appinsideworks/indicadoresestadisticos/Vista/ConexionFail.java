package com.appinsideworks.indicadoresestadisticos.Vista;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.appinsideworks.indicadoresestadisticos.Controlador.Local.Misc;
import com.appinsideworks.indicadoresestadisticos.MainActivity;
import com.appinsideworks.indicadoresestadisticos.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConexionFail extends Fragment {

    Button retry;

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
        retry = (Button) getActivity().findViewById(R.id.btnRetryConnection);
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager FM = getActivity().getFragmentManager();
                FragmentTransaction FT = FM.beginTransaction();
                FT.remove(FM.findFragmentByTag("Failed"));
                FT.commit();
                MainActivity mainActivity = new MainActivity();
            }
        });
    }
}
