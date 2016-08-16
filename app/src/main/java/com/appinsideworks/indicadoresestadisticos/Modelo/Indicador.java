package com.appinsideworks.indicadoresestadisticos.Modelo;

/**
 * Created by ramon_a on 8/8/16.
 */
public class Indicador {

    private int desglose_geografico;
    private int indicador;
    private String nombre;
    private Boolean ver_areas;

    public Indicador(int desglose_geografico, int indicador, String nombre, Boolean ver_areas) {
        this.desglose_geografico = desglose_geografico;
        this.indicador = indicador;
        this.nombre = nombre;
        this.ver_areas = ver_areas;
    }

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

    @Override
    public String toString() {
        return "Indicador: " + indicador + "\nNombre: " + nombre + "\nDesglose geográfico: " + desglose_geografico + "\nVer áreas: " + ver_areas;
    }
}
