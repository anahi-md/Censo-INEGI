/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author anahi
 */

import dao.TipoViviendaDAO;
import modelo.TipoVivienda;
import view.TipoViviendaFrame;

public class TipoViviendaController {
    private final TipoViviendaFrame view;
    private final TipoViviendaDAO dao;

    public TipoViviendaController(TipoViviendaFrame view) {
        this.view = view;
        this.dao = new TipoViviendaDAO();
    }

    public void consultarTipos() {
        view.mostrarTiposVivienda(dao.listar());
    }

    public void guardarTipo() {
        String nombre = view.getNombreTipoVivienda();

        if (nombre.isEmpty()) {
            view.mostrarError("El tipo de vivienda es obligatorio.");
            return;
        }

        TipoVivienda t = new TipoVivienda();
        t.setNombre(nombre);

        if (dao.insertar(t)) {
            view.mostrarMensaje("Tipo de vivienda guardado.");
            view.limpiarFormulario();
            consultarTipos();
        } else {
            view.mostrarError("No se pudo guardar.");
        }
    }

    public void actualizarTipo() {
        int id = view.getIdTipoVivienda();
        String nombre = view.getNombreTipoVivienda();

        if (id <= 0) {
            view.mostrarError("Selecciona un tipo válido.");
            return;
        }

        TipoVivienda t = new TipoVivienda();
        t.setIdTipoVivienda(id);
        t.setNombre(nombre);

        if (dao.actualizar(t)) {
            view.mostrarMensaje("Tipo de vivienda actualizado.");
            view.limpiarFormulario();
            consultarTipos();
        } else {
            view.mostrarError("No se pudo actualizar.");
        }
    }

    public void eliminarTipo() {
        int id = view.getIdTipoVivienda();

        if (id <= 0) {
            view.mostrarError("Selecciona un tipo válido.");
            return;
        }

        if (dao.eliminar(id)) {
            view.mostrarMensaje("Tipo de vivienda eliminado.");
            view.limpiarFormulario();
            consultarTipos();
        } else {
            view.mostrarError("No se pudo eliminar.");
        }
    }
}