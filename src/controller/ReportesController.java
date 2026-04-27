/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author anahi
 */

import dao.ReporteDAO;
import view.ReportesFrame;

public class ReportesController {

    private final ReportesFrame view;
    private final ReporteDAO dao;

    public ReportesController(ReportesFrame view) {
        this.view = view;
        this.dao = new ReporteDAO();
    }

    public void reporteHabitantesPorMunicipio() {
        view.mostrarReporte(
                "Habitantes por municipio",
                new String[]{"Municipio", "Total habitantes"},
                dao.habitantesPorMunicipio()
        );
    }

    public void reporteHabitantesPorLocalidad() {
        view.mostrarReporte(
                "Habitantes por localidad",
                new String[]{"Localidad", "Total habitantes"},
                dao.habitantesPorLocalidad()
        );
    }

    public void reporteViviendasPorMunicipio() {
        view.mostrarReporte(
                "Viviendas por municipio",
                new String[]{"Municipio", "Total viviendas"},
                dao.viviendasPorMunicipio()
        );
    }

    public void reporteHabitantesPorTipoVivienda() {
        view.mostrarReporte(
                "Habitantes por tipo de vivienda",
                new String[]{"Tipo de vivienda", "Total habitantes"},
                dao.habitantesPorTipoVivienda()
        );
    }
}