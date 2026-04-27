/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.ActividadEconomicaDAO;
import java.util.List;
import modelo.ActividadEconomica;
import view.ActividadEconomicaFrame;

public class ActividadEconomicaController {

    private final ActividadEconomicaFrame view;
    private final ActividadEconomicaDAO dao;

    public ActividadEconomicaController(ActividadEconomicaFrame view) {
        this.view = view;
        this.dao = new ActividadEconomicaDAO();
    }

    public void guardarActividad() {
        String nombre = view.getNombreActividad();
        String descripcion = view.getDescripcionActividad();

        if (nombre.isEmpty()) {
            view.mostrarError("El nombre es obligatorio.");
            return;
        }

        ActividadEconomica a = new ActividadEconomica();
        a.setNombre(nombre);
        a.setDescripcion(descripcion);

        if (dao.insertar(a)) {
            view.mostrarMensaje("Actividad guardada correctamente.");
            view.limpiarFormulario();
            consultarActividades();
        } else {
            view.mostrarError("No se pudo guardar la actividad.");
        }
    }

    public void actualizarActividad() {
        int id = view.getIdActividad();
        String nombre = view.getNombreActividad();
        String descripcion = view.getDescripcionActividad();

        if (id <= 0) {
            view.mostrarError("Selecciona una actividad válida.");
            return;
        }

        if (nombre.isEmpty()) {
            view.mostrarError("El nombre es obligatorio.");
            return;
        }

        ActividadEconomica a = new ActividadEconomica();
        a.setIdActividad(id);
        a.setNombre(nombre);
        a.setDescripcion(descripcion);

        if (dao.actualizar(a)) {
            view.mostrarMensaje("Actividad actualizada correctamente.");
            view.limpiarFormulario();
            consultarActividades();
        } else {
            view.mostrarError("No se pudo actualizar la actividad.");
        }
    }

    public void eliminarActividad() {
        int id = view.getIdActividad();

        if (id <= 0) {
            view.mostrarError("Selecciona una actividad válida.");
            return;
        }

        if (dao.eliminar(id)) {
            view.mostrarMensaje("Actividad eliminada correctamente.");
            view.limpiarFormulario();
            consultarActividades();
        } else {
            view.mostrarError("No se pudo eliminar la actividad.");
        }
    }

    public void consultarActividades() {
        List<ActividadEconomica> actividades = dao.listar();
        view.mostrarActividades(actividades);
    }
}