/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author anahi
 */

import dao.SexoDAO;
import modelo.Sexo;
import view.SexoFrame;

public class SexoController {
    private final SexoFrame view;
    private final SexoDAO dao;

    public SexoController(SexoFrame view) {
        this.view = view;
        this.dao = new SexoDAO();
    }

    public void consultarSexos() {
        view.mostrarSexos(dao.listar());
    }

    public void guardarSexo() {
        String nombre = view.getNombreSexo();

        if (nombre.isEmpty()) {
            view.mostrarError("El sexo es obligatorio.");
            return;
        }

        Sexo s = new Sexo();
        s.setNombre(nombre);

        if (dao.insertar(s)) {
            view.mostrarMensaje("Sexo guardado.");
            view.limpiarFormulario();
            consultarSexos();
        } else {
            view.mostrarError("No se pudo guardar.");
        }
    }

    public void actualizarSexo() {
        int id = view.getIdSexo();
        String nombre = view.getNombreSexo();

        if (id <= 0) {
            view.mostrarError("Selecciona un sexo válido.");
            return;
        }

        Sexo s = new Sexo();
        s.setIdSexo(id);
        s.setNombre(nombre);

        if (dao.actualizar(s)) {
            view.mostrarMensaje("Sexo actualizado.");
            view.limpiarFormulario();
            consultarSexos();
        } else {
            view.mostrarError("No se pudo actualizar.");
        }
    }

    public void eliminarSexo() {
        int id = view.getIdSexo();

        if (id <= 0) {
            view.mostrarError("Selecciona un sexo válido.");
            return;
        }

        if (dao.eliminar(id)) {
            view.mostrarMensaje("Sexo eliminado.");
            view.limpiarFormulario();
            consultarSexos();
        } else {
            view.mostrarError("No se pudo eliminar.");
        }
    }
}