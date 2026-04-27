package dao;

import modelo.ViviendaActividad;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ViviendaActividadDAO {

    public boolean insertar(ViviendaActividad va) {
        String sql = "INSERT INTO vivienda_actividad (id_vivienda, id_actividad) VALUES (?, ?)";

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, va.getIdVivienda());
            ps.setInt(2, va.getIdActividad());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error insertar vivienda_actividad: " + e.getMessage());
            return false;
        }
    }

    public List<ViviendaActividad> listarPorVivienda(int idVivienda) {
        List<ViviendaActividad> lista = new ArrayList<>();
        String sql = "SELECT * FROM vivienda_actividad WHERE id_vivienda = ?";

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idVivienda);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ViviendaActividad va = new ViviendaActividad();
                va.setId(rs.getInt("id"));
                va.setIdVivienda(rs.getInt("id_vivienda"));
                va.setIdActividad(rs.getInt("id_actividad"));
                lista.add(va);
            }

        } catch (SQLException e) {
            System.out.println("Error listar vivienda_actividad: " + e.getMessage());
        }

        return lista;
    }
    
    public List<ViviendaActividad> listar() {
        List<ViviendaActividad> lista = new ArrayList<>();
        String sql = "SELECT id, id_vivienda, id_actividad FROM vivienda_actividad";

        try (Connection conn = ConexionBD.getConexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                ViviendaActividad va = new ViviendaActividad();
                va.setId(rs.getInt("id"));
                va.setIdVivienda(rs.getInt("id_vivienda"));
                va.setIdActividad(rs.getInt("id_actividad"));
                lista.add(va);
            }

        } catch (SQLException e) {
            System.out.println("Error listar vivienda_actividad: " + e.getMessage());
        }

        return lista;
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM vivienda_actividad WHERE id = ?";

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error eliminar vivienda_actividad: " + e.getMessage());
            return false;
        }
    }
}