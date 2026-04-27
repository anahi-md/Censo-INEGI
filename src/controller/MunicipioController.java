/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author anahi
 */

import dao.MunicipioDAO;
import java.util.List;
import modelo.Municipio;
import view.MunicipioFrame;

public class MunicipioController {

    private final MunicipioFrame view;
    private final MunicipioDAO dao;

    public MunicipioController(MunicipioFrame view) {
        this.view = view;
        this.dao = new MunicipioDAO();
    }

    public void guardarMunicipio() {
        String nombre = view.getNombreMunicipio();

        if (nombre.isEmpty()) {
            view.mostrarError("El nombre es obligatorio.");
            return;
        }

        Municipio m = new Municipio();
        m.setNombre(nombre);

        if (dao.insertar(m)) {
            view.mostrarMensaje("Municipio guardado.");
            view.limpiarFormulario();
            consultarMunicipios();
        } else {
            view.mostrarError("No se pudo guardar.");
        }
    }

    public void actualizarMunicipio() {
        int id = view.getIdSeleccionadoTabla();
        String nombre = view.getNombreMunicipio();

        if (id <= 0) {
            view.mostrarError("Selecciona un municipio.");
            return;
        }

        Municipio m = new Municipio();
        m.setIdMunicipio(id);
        m.setNombre(nombre);

        if (dao.actualizar(m)) {
            view.mostrarMensaje("Municipio actualizado.");
            consultarMunicipios();
        } else {
            view.mostrarError("No se pudo actualizar.");
        }
    }

    public void eliminarMunicipio() {
        int id = view.getIdSeleccionadoTabla();

        if (id <= 0) {
            view.mostrarError("Selecciona un municipio.");
            return;
        }

        if (dao.eliminar(id)) {
            view.mostrarMensaje("Municipio eliminado.");
            consultarMunicipios();
        } else {
            view.mostrarError("No se pudo eliminar.");
        }
    }

    public void consultarMunicipios() {
        List<Municipio> lista = dao.listar();
        view.mostrarMunicipios(lista);
    }
}