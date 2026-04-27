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
import dao.HabitanteDAO;
import dao.OcupacionDAO;
import dao.SexoDAO;
import dao.ViviendaDAO;
import java.text.SimpleDateFormat;
import java.util.List;
import modelo.Escolaridad;
import modelo.Habitante;
import modelo.Ocupacion;
import modelo.Sexo;
import modelo.Vivienda;
import view.HabitanteFrame;

public class HabitanteController {

    private final HabitanteFrame view;
    private final HabitanteDAO habitanteDAO;
    private final SexoDAO sexoDAO;
    private final ViviendaDAO viviendaDAO;
    private final EscolaridadDAO escolaridadDAO;
    private final OcupacionDAO ocupacionDAO;

    public HabitanteController(HabitanteFrame view) {
        this.view = view;
        this.habitanteDAO = new HabitanteDAO();
        this.sexoDAO = new SexoDAO();
        this.viviendaDAO = new ViviendaDAO();
        this.escolaridadDAO = new EscolaridadDAO();
        this.ocupacionDAO = new OcupacionDAO();
    }

    public void inicializar() {
        view.cargarSexos(sexoDAO.listar());
        view.cargarViviendas(viviendaDAO.listar());
        view.cargarEscolaridades(escolaridadDAO.listar());
        view.cargarOcupaciones(ocupacionDAO.listar());
        view.cargarEstatus();
        consultarHabitantes();
    }

    public void guardarHabitante() {
        try {
            String error = validar();
            if (error != null) {
                view.mostrarError(error);
                return;
            }

            Habitante h = construirHabitante();
            boolean guardado = habitanteDAO.insertar(h);

            if (guardado) {
                view.mostrarMensaje("Habitante guardado correctamente.");
                view.limpiarFormulario();
                consultarHabitantes();
            } else {
                view.mostrarError("No se pudo guardar el habitante.");
            }

        } catch (Exception e) {
            view.mostrarError("Error al guardar habitante: " + e.getMessage());
        }
    }

    public void consultarHabitantes() {
        List<Habitante> habitantes = habitanteDAO.listar();
        view.mostrarHabitantes(habitantes);
    }

    public void eliminarHabitante() {
        int id = view.getIdHabitanteSeleccionado();

        if (id <= 0) {
            view.mostrarError("Selecciona un habitante de la tabla.");
            return;
        }

        if (habitanteDAO.eliminar(id)) {
            view.mostrarMensaje("Habitante eliminado correctamente.");
            view.limpiarFormulario();
            consultarHabitantes();
        } else {
            view.mostrarError("No se pudo eliminar el habitante.");
        }
    }

    public void actualizarHabitante() {
        try {
            int id = view.getIdHabitanteSeleccionado();

            if (id <= 0) {
                view.mostrarError("Selecciona un habitante de la tabla.");
                return;
            }

            String error = validar();
            if (error != null) {
                view.mostrarError(error);
                return;
            }

            Habitante h = construirHabitante();
            h.setIdHabitante(id);

            if (habitanteDAO.actualizar(h)) {
                view.mostrarMensaje("Habitante actualizado correctamente.");
                view.limpiarFormulario();
                consultarHabitantes();
            } else {
                view.mostrarError("No se pudo actualizar el habitante.");
            }

        } catch (Exception e) {
            view.mostrarError("Error al actualizar habitante: " + e.getMessage());
        }
    }

    private Habitante construirHabitante() throws Exception {
        Sexo sexo = view.getSexoSeleccionado();
        Vivienda vivienda = view.getViviendaSeleccionada();
        Escolaridad escolaridad = view.getEscolaridadSeleccionada();
        Ocupacion ocupacion = view.getOcupacionSeleccionada();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Habitante h = new Habitante();
        h.setNombre(view.getNombre());
        h.setPaterno(view.getPaterno());
        h.setMaterno(view.getMaterno());
        h.setEdad(view.getEdad());
        h.setCurp(view.getCurp());
        h.setFechaNac(sdf.parse(view.getFechaNac()));
        h.setIdSexo(sexo.getIdSexo());
        h.setIdVivienda(vivienda.getIdVivienda());
        h.setIdEscolaridad(escolaridad.getIdEscolaridad());
        h.setIdOcupacion(ocupacion.getIdOcupacion());
        h.setEstatus(view.getEstatusSeleccionado());

        return h;
    }

    private String validar() {
        if (view.getNombre().isEmpty()) return "El nombre es obligatorio.";
        if (view.getPaterno().isEmpty()) return "El apellido paterno es obligatorio.";
        if (view.getEdad() <= 0) return "La edad debe ser mayor a cero.";
        if (view.getCurp().isEmpty()) return "La CURP es obligatoria.";
        if (view.getFechaNac().isEmpty()) return "La fecha es obligatoria. Formato: yyyy-MM-dd";
        if (view.getSexoSeleccionado() == null) return "Selecciona sexo.";
        if (view.getViviendaSeleccionada() == null) return "Selecciona vivienda.";
        if (view.getEscolaridadSeleccionada() == null) return "Selecciona escolaridad.";
        if (view.getOcupacionSeleccionada() == null) return "Selecciona ocupación.";
        return null;
    }
}