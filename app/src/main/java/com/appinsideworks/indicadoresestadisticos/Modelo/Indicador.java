package com.appinsideworks.indicadoresestadisticos.Modelo;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * Created by ramon_a on 8/8/16.
 */
public class Indicador implements Parcelable {
    //Atributos
    private int desglose_geografico;
    private int indicador;
    private String nombre;
    private Boolean ver_areas;

    //Constructores
    public Indicador(int desglose_geografico, int indicador, String nombre, Boolean ver_areas) {
        this.desglose_geografico = desglose_geografico;
        this.indicador = indicador;
        this.nombre = nombre;
        this.ver_areas = ver_areas;
    }

    public Indicador(Parcel parcel) {
        readFromParcel(parcel);
    }

    //Fin

    //Getters y Setters
    public int getDesglose_geografico() {
        return desglose_geografico;
    }

    public void setDesglose_geografico(int desglose_geografico) {
        this.desglose_geografico = desglose_geografico;
    }

    public int getIndicador() {
        return indicador;
    }

    public void setIndicador(int indicador) {
        this.indicador = indicador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getVer_areas() {
        return ver_areas;
    }

    public void setVer_areas(Boolean ver_areas) {
        this.ver_areas = ver_areas;
    }

    //Fin


    //ToString
    @Override
    public String toString() {
        return "Indicador: " + indicador + "\nNombre: " + nombre + "\nDesglose geográfico: " + desglose_geografico + "\nVer áreas: " + ver_areas;
    }


    //Parcelable

    public static final Parcelable.Creator<Indicador> CREATOR = new Creator<Indicador>() {

        @Override
        public Indicador[] newArray(int i) {
            return new Indicador[i];
        }

        @Override
        public Indicador createFromParcel(Parcel parcel) {
            return new Indicador(parcel);
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(desglose_geografico);
        parcel.writeInt(indicador);
        parcel.writeString(nombre);
        parcel.writeBooleanArray(new boolean[]{ver_areas});

    }

    private void readFromParcel(Parcel in) {
        this.desglose_geografico = in.readInt();
        this.indicador = in.readInt();
        this.nombre = in.readString();

        boolean[] aux = new boolean[1];
        in.readBooleanArray(aux);
        this.ver_areas = aux[0];
    }
    //Fin
}
