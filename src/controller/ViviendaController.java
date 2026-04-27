package controller;

import dao.LocalidadDAO;
import dao.MunicipioDAO;
import dao.TipoViviendaDAO;
import dao.ViviendaDAO;
import java.util.ArrayList;
import modelo.Localidad;
import modelo.Municipio;
import modelo.TipoVivienda;
import modelo.Vivienda;
import view.ViviendaFrame;

public class ViviendaController {

    private final ViviendaFrame view;
    private final ViviendaDAO viviendaDAO;
    private final MunicipioDAO municipioDAO;
    private final LocalidadDAO localidadDAO;
    private final TipoViviendaDAO tipoViviendaDAO;

    public ViviendaController(ViviendaFrame view) {
        this.view = view;
        this.viviendaDAO = new ViviendaDAO();
        this.municipioDAO = new MunicipioDAO();
        this.localidadDAO = new LocalidadDAO();
        this.tipoViviendaDAO = new TipoViviendaDAO();
    }

    public void inicializarFormulario() {
        view.cargarMunicipios(municipioDAO.listar());
        view.cargarTiposVivienda(tipoViviendaDAO.listar());
        view.cargarEstatus();
        view.cargarLocalidades(new ArrayList<>());
    }

    public void guardarVivienda() {
        String error = validar();
        if (error != null) {
            view.mostrarError(error);
            return;
        }

        Vivienda v = construirVivienda();
        v.setEstatus(view.getEstatusSeleccionado());

        if (viviendaDAO.insertar(v)) {
            view.mostrarMensaje("Vivienda guardada correctamente.");
            view.limpiarFormulario();
            consultarViviendas();
        } else {
            view.mostrarError("No se pudo guardar la vivienda.");
        }
    }

    public void actualizarVivienda() {
        int id = view.getIdViviendaSeleccionada();

        if (id <= 0) {
            view.mostrarError("Selecciona una vivienda de la tabla.");
            return;
        }

        String error = validar();
        if (error != null) {
            view.mostrarError(error);
            return;
        }

        Vivienda v = construirVivienda();
        v.setIdVivienda(id);
        v.setEstatus(view.getEstatusSeleccionado());

        if (viviendaDAO.actualizar(v)) {
            view.mostrarMensaje("Vivienda actualizada correctamente.");
            view.limpiarFormulario();
            consultarViviendas();
        } else {
            view.mostrarError("No se pudo actualizar la vivienda.");
        }
    }

    public void eliminarVivienda() {
        int id = view.getIdViviendaSeleccionada();

        if (id <= 0) {
            view.mostrarError("Selecciona una vivienda de la tabla.");
            return;
        }

        if (viviendaDAO.eliminar(id)) {
            view.mostrarMensaje("Vivienda eliminada correctamente.");
            view.limpiarFormulario();
            consultarViviendas();
        } else {
            view.mostrarError("No se pudo eliminar la vivienda.");
        }
    }

    public void consultarViviendas() {
        view.mostrarViviendas(viviendaDAO.listar());
    }

    private Vivienda construirVivienda() {
        Municipio municipio = view.getMunicipioSeleccionado();
        Localidad localidad = view.getLocalidadSeleccionada();
        TipoVivienda tipo = view.getTipoViviendaSeleccionado();

        Vivienda v = new Vivienda();
        v.setCalle(view.getCalle());
        v.setNumExterior(view.getNumExterior());
        v.setNumInterior(view.getNumInterior());
        v.setColonia(view.getColonia());
        v.setCodigoPostal(view.getCodigoPostal());
        v.setIdMunicipio(municipio.getIdMunicipio());
        v.setIdLocalidad(localidad.getIdLocalidad());
        v.setIdTipoVivienda(tipo.getIdTipoVivienda());
        v.setNumCuartos(view.getNumCuartos());
        v.setNumBanios(view.getNumBanios());
        v.setMaterialPredominante(view.getMaterial());
        v.setServiciosBasicos(view.getServicios());
        v.setObservaciones(view.getObservaciones());
        return v;
    }

    private String validar() {
        if (view.getCalle().isEmpty()) return "La calle es obligatoria.";
        if (view.getNumExterior().isEmpty()) return "El número exterior es obligatorio.";
        if (view.getColonia().isEmpty()) return "La colonia es obligatoria.";
        if (view.getCodigoPostal().isEmpty()) return "El código postal es obligatorio.";
        if (view.getMunicipioSeleccionado() == null) return "Selecciona municipio.";
        if (view.getLocalidadSeleccionada() == null) return "Selecciona localidad.";
        if (view.getTipoViviendaSeleccionado() == null) return "Selecciona tipo de vivienda.";
        if (view.getNumCuartos() < 0) return "El número de cuartos no puede ser negativo.";
        if (view.getNumBanios() < 0) return "El número de baños no puede ser negativo.";
        return null;
    }
    
    public void cargarLocalidadesPorMunicipio() {
        Municipio municipio = view.getMunicipioSeleccionado();

        if (municipio == null) {
            view.cargarLocalidades(new ArrayList<>());
            return;
        }

        view.cargarLocalidades(
            localidadDAO.listarPorMunicipio(municipio.getIdMunicipio())
        );
    }
}