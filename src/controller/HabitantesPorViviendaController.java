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
import dao.ViviendaDAO;
import modelo.Vivienda;
import view.HabitantesPorViviendaFrame;

public class HabitantesPorViviendaController {

    private final HabitantesPorViviendaFrame view;
    private final ViviendaDAO viviendaDAO;
    private final ReporteDAO reporteDAO;

    public HabitantesPorViviendaController(HabitantesPorViviendaFrame view) {
        this.view = view;
        this.viviendaDAO = new ViviendaDAO();
        this.reporteDAO = new ReporteDAO();
    }

    public void inicializar() {
        view.cargarViviendas(viviendaDAO.listar());
    }

    public void consultarHabitantes() {
        Vivienda vivienda = view.getViviendaSeleccionada();

        if (vivienda == null) {
            view.mostrarError("Selecciona una vivienda.");
            return;
        }

        view.mostrarHabitantes(
                reporteDAO.habitantesPorVivienda(vivienda.getIdVivienda())
        );
    }
}