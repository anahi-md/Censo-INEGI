package dao;

import modelo.Ocupacion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OcupacionDAO {

    public boolean insertar(Ocupacion o) {
        String sql = "INSERT INTO ocupaciones (nombre) VALUES (?)";

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, o.getNombre());
            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.out.println("Error insertar ocupación: " + ex.getMessage());
            return false;
        }
    }

    public List<Ocupacion> listar() {
        List<Ocupacion> lista = new ArrayList<>();
        String sql = "SELECT id_ocupacion, nombre FROM ocupaciones ORDER BY nombre";

        try (Connection conn = ConexionBD.getConexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Ocupacion o = new Ocupacion();
                o.setIdOcupacion(rs.getInt("id_ocupacion"));
                o.setNombre(rs.getString("nombre"));
                lista.add(o);
            }

        } catch (SQLException ex) {
            System.out.println("Error listar ocupaciones: " + ex.getMessage());
        }

        return lista;
    }

    public boolean actualizar(Ocupacion o) {
        String sql = "UPDATE ocupaciones SET nombre = ? WHERE id_ocupacion = ?";

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, o.getNombre());
            ps.setInt(2, o.getIdOcupacion());
            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.out.println("Error actualizar ocupación: " + ex.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM ocupaciones WHERE id_ocupacion = ?";

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.out.println("Error eliminar ocupación: " + ex.getMessage());
            return false;
        }
    }
}