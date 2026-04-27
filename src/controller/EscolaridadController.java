/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author anahi
 */

import dao.EscolaridadDAO;
import java.util.List;
import modelo.Escolaridad;
import view.EscolaridadFrame;

public class EscolaridadController {

    private final EscolaridadFrame view;
    private final EscolaridadDAO dao;

    public EscolaridadController(EscolaridadFrame view) {
        this.view = view;
        this.dao = new EscolaridadDAO();
    }

    public void guardarEscolaridad() {
        String nombre = view.getNombreEscolaridad();

        if (nombre.isEmpty()) {
            view.mostrarError("El nombre de escolaridad es obligatorio.");
            return;
        }

        Escolaridad e = new Escolaridad();
        e.setNombre(nombre);

        if (dao.insertar(e)) {
            view.mostrarMensaje("Escolaridad guardada correctamente.");
            view.limpiarFormulario();
            consultarEscolaridades();
        } else {
            view.mostrarError("No se pudo guardar la escolaridad.");
        }
    }

    public void actualizarEscolaridad() {
        int id = view.getIdEscolaridad();
        String nombre = view.getNombreEscolaridad();

        if (id <= 0) {
            view.mostrarError("Selecciona una escolaridad válida.");
            return;
        }

        if (nombre.isEmpty()) {
            view.mostrarError("El nombre de escolaridad es obligatorio.");
            return;
        }

        Escolaridad e = new Escolaridad();
        e.setIdEscolaridad(id);
        e.setNombre(nombre);

        if (dao.actualizar(e)) {
            view.mostrarMensaje("Escolaridad actualizada correctamente.");
            view.limpiarFormulario();
            consultarEscolaridades();
        } else {
            view.mostrarError("No se pudo actualizar la escolaridad.");
        }
    }

    public void eliminarEscolaridad() {
        int id = view.getIdEscolaridad();

        if (id <= 0) {
            view.mostrarError("Selecciona una escolaridad válida.");
            return;
        }

        if (dao.eliminar(id)) {
            view.mostrarMensaje("Escolaridad eliminada correctamente.");
            view.limpiarFormulario();
            consultarEscolaridades();
        } else {
            view.mostrarError("No se pudo eliminar la escolaridad.");
        }
    }

    public void consultarEscolaridades() {
        List<Escolaridad> lista = dao.listar();
        view.mostrarEscolaridades(lista);
    }
}