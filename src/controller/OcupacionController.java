/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author anahi
 */

import dao.OcupacionDAO;
import java.util.List;
import modelo.Ocupacion;
import view.OcupacionFrame;

public class OcupacionController {
    private final OcupacionFrame view;
    private final OcupacionDAO dao;

    public OcupacionController(OcupacionFrame view) {
        this.view = view;
        this.dao = new OcupacionDAO();
    }

    public void consultarOcupaciones() {
        view.mostrarOcupaciones(dao.listar());
    }

    public void guardarOcupacion() {
        String nombre = view.getNombreOcupacion();

        if (nombre.isEmpty()) {
            view.mostrarError("La ocupación es obligatoria.");
            return;
        }

        Ocupacion o = new Ocupacion();
        o.setNombre(nombre);

        if (dao.insertar(o)) {
            view.mostrarMensaje("Ocupación guardada.");
            view.limpiarFormulario();
            consultarOcupaciones();
        } else {
            view.mostrarError("No se pudo guardar.");
        }
    }

    public void actualizarOcupacion() {
        int id = view.getIdOcupacion();
        String nombre = view.getNombreOcupacion();

        if (id <= 0) {
            view.mostrarError("Selecciona una ocupación válida.");
            return;
        }

        Ocupacion o = new Ocupacion();
        o.setIdOcupacion(id);
        o.setNombre(nombre);

        if (dao.actualizar(o)) {
            view.mostrarMensaje("Ocupación actualizada.");
            view.limpiarFormulario();
            consultarOcupaciones();
        } else {
            view.mostrarError("No se pudo actualizar.");
        }
    }

    public void eliminarOcupacion() {
        int id = view.getIdOcupacion();

        if (id <= 0) {
            view.mostrarError("Selecciona una ocupación válida.");
            return;
        }

        if (dao.eliminar(id)) {
            view.mostrarMensaje("Ocupación eliminada.");
            view.limpiarFormulario();
            consultarOcupaciones();
        } else {
            view.mostrarError("No se pudo eliminar.");
        }
    }
}