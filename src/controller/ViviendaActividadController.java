/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author anahi
 */

import dao.ActividadEconomicaDAO;
import dao.ViviendaActividadDAO;
import dao.ViviendaDAO;
import modelo.ActividadEconomica;
import modelo.Vivienda;
import modelo.ViviendaActividad;
import view.ViviendaActividadFrame;

public class ViviendaActividadController {

    private final ViviendaActividadFrame view;
    private final ViviendaActividadDAO viviendaActividadDAO;
    private final ViviendaDAO viviendaDAO;
    private final ActividadEconomicaDAO actividadDAO;

    public ViviendaActividadController(ViviendaActividadFrame view) {
        this.view = view;
        this.viviendaActividadDAO = new ViviendaActividadDAO();
        this.viviendaDAO = new ViviendaDAO();
        this.actividadDAO = new ActividadEconomicaDAO();
    }

    public void inicializar() {
        view.cargarViviendas(viviendaDAO.listar());
        view.cargarActividades(actividadDAO.listar());
        consultarAsignaciones();
    }

    public void asignarActividad() {
        Vivienda vivienda = view.getViviendaSeleccionada();
        ActividadEconomica actividad = view.getActividadSeleccionada();

        if (vivienda == null) {
            view.mostrarError("Selecciona una vivienda.");
            return;
        }

        if (actividad == null) {
            view.mostrarError("Selecciona una actividad económica.");
            return;
        }

        ViviendaActividad va = new ViviendaActividad();
        va.setIdVivienda(vivienda.getIdVivienda());
        va.setIdActividad(actividad.getIdActividad());

        if (viviendaActividadDAO.insertar(va)) {
            view.mostrarMensaje("Actividad asignada correctamente.");
            view.limpiarFormulario();
            consultarAsignaciones();
        } else {
            view.mostrarError("No se pudo asignar la actividad.");
        }
    }

    public void consultarAsignaciones() {
        view.mostrarAsignaciones(viviendaActividadDAO.listar());
    }

    public void eliminarAsignacion() {
        int id = view.getIdAsignacionSeleccionada();

        if (id <= 0) {
            view.mostrarError("Selecciona una asignación de la tabla.");
            return;
        }

        if (viviendaActividadDAO.eliminar(id)) {
            view.mostrarMensaje("Asignación eliminada correctamente.");
            consultarAsignaciones();
        } else {
            view.mostrarError("No se pudo eliminar la asignación.");
        }
    }
}