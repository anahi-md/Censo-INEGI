/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author anahi
 */

import dao.LocalidadDAO;
import dao.MunicipioDAO;
import java.util.List;
import modelo.Localidad;
import modelo.Municipio;
import view.LocalidadFrame;

public class LocalidadController {

    private final LocalidadFrame view;
    private final LocalidadDAO localidadDAO;
    private final MunicipioDAO municipioDAO;

    public LocalidadController(LocalidadFrame view) {
        this.view = view;
        this.localidadDAO = new LocalidadDAO();
        this.municipioDAO = new MunicipioDAO();
    }

    public void inicializar() {
        view.cargarMunicipios(municipioDAO.listar());
        consultarLocalidades();
    }

    public void guardarLocalidad() {
        String nombre = view.getNombreLocalidad();
        Municipio municipio = view.getMunicipioSeleccionado();

        if (nombre.isEmpty()) {
            view.mostrarError("El nombre de la localidad es obligatorio.");
            return;
        }

        if (municipio == null) {
            view.mostrarError("Selecciona un municipio.");
            return;
        }

        Localidad l = new Localidad();
        l.setNombre(nombre);
        l.setIdMunicipio(municipio.getIdMunicipio());

        if (localidadDAO.insertar(l)) {
            view.mostrarMensaje("Localidad guardada correctamente.");
            view.limpiarFormulario();
            consultarLocalidades();
        } else {
            view.mostrarError("No se pudo guardar la localidad.");
        }
    }

    public void actualizarLocalidad() {
        int id = view.getIdLocalidad();
        String nombre = view.getNombreLocalidad();
        Municipio municipio = view.getMunicipioSeleccionado();

        if (id <= 0) {
            view.mostrarError("Selecciona una localidad válida.");
            return;
        }

        if (nombre.isEmpty()) {
            view.mostrarError("El nombre de la localidad es obligatorio.");
            return;
        }

        if (municipio == null) {
            view.mostrarError("Selecciona un municipio.");
            return;
        }

        Localidad l = new Localidad();
        l.setIdLocalidad(id);
        l.setNombre(nombre);
        l.setIdMunicipio(municipio.getIdMunicipio());

        if (localidadDAO.actualizar(l)) {
            view.mostrarMensaje("Localidad actualizada correctamente.");
            view.limpiarFormulario();
            consultarLocalidades();
        } else {
            view.mostrarError("No se pudo actualizar la localidad.");
        }
    }

    public void eliminarLocalidad() {
        int id = view.getIdLocalidad();

        if (id <= 0) {
            view.mostrarError("Selecciona una localidad válida.");
            return;
        }

        if (localidadDAO.eliminar(id)) {
            view.mostrarMensaje("Localidad eliminada correctamente.");
            view.limpiarFormulario();
            consultarLocalidades();
        } else {
            view.mostrarError("No se pudo eliminar la localidad.");
        }
    }

    public void consultarLocalidades() {
        List<Localidad> localidades = localidadDAO.listar();
        view.mostrarLocalidades(localidades);
    }
}