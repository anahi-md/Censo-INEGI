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
import view.DashboardFrame;

public class DashboardController {

    private final DashboardFrame view;
    private final ReporteDAO dao;

    public DashboardController(DashboardFrame view) {
        this.view = view;
        this.dao = new ReporteDAO();
    }

    public void cargarDashboard() {
        view.mostrarTotales(
                dao.totalHabitantes(),
                dao.totalViviendas()
        );

        view.mostrarHabitantesPorMunicipio(dao.habitantesPorMunicipio());
        view.mostrarHabitantesPorLocalidad(dao.habitantesPorLocalidad());
        view.mostrarHabitantesPorTipoVivienda(dao.habitantesPorTipoVivienda());
    }
}