package dao;

import modelo.ActividadEconomica;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActividadEconomicaDAO {

    public boolean insertar(ActividadEconomica a) {
        String sql = "INSERT INTO actividadeseconomicas (nombre, descripcion) VALUES (?, ?)";

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, a.getNombre());
            ps.setString(2, a.getDescripcion());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error insertar actividad económica: " + e.getMessage());
            return false;
        }
    }
    
    public ActividadEconomica obtenerPorId(int id) {
        String sql = "SELECT id_actividad, nombre, descripcion FROM actividadeseconomicas WHERE id_actividad = ?";
        ActividadEconomica a = null;

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    a = new ActividadEconomica();
                    a.setIdActividad(rs.getInt("id_actividad"));
                    a.setNombre(rs.getString("nombre"));
                    a.setDescripcion(rs.getString("descripcion"));
                }
            }

        } catch (SQLException e) {
            System.out.println("Error obtener actividad económica: " + e.getMessage());
        }

        return a;
    }

    public boolean actualizar(ActividadEconomica a) {
        String sql = "UPDATE actividadeseconomicas SET nombre = ?, descripcion = ? WHERE id_actividad = ?";

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, a.getNombre());
            ps.setString(2, a.getDescripcion());
            ps.setInt(3, a.getIdActividad());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error actualizar actividad económica: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "UPDATE actividadeseconomicas SET estatus = 0 WHERE id_actividad = ?";

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error eliminar actividad económica: " + e.getMessage());
            return false;
        }
    }
    
    public List<ActividadEconomica> listar() {
        List<ActividadEconomica> lista = new ArrayList<>();
        String sql = "SELECT id_actividad, nombre, descripcion FROM actividadeseconomicas WHERE estatus = 1 ORDER BY nombre";

        try (Connection conn = ConexionBD.getConexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                ActividadEconomica a = new ActividadEconomica();
                a.setIdActividad(rs.getInt("id_actividad"));
                a.setNombre(rs.getString("nombre"));
                a.setDescripcion(rs.getString("descripcion"));
                lista.add(a);
            }

        } catch (SQLException e) {
            System.out.println("Error listar actividades económicas: " + e.getMessage());
        }

        return lista;
    }
}