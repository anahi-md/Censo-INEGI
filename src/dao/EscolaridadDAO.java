package dao;

import modelo.Escolaridad;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EscolaridadDAO {

    public boolean insertar(Escolaridad e) {
        String sql = "INSERT INTO escolaridades (nombre) VALUES (?)";

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, e.getNombre());
            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.out.println("Error insertar escolaridad: " + ex.getMessage());
            return false;
        }
    }
    
    public List<Escolaridad> listar() {
        List<Escolaridad> lista = new ArrayList<>();
        String sql = "SELECT id_escolaridad, nombre FROM escolaridades ORDER BY nombre";

        try (Connection conn = ConexionBD.getConexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Escolaridad e = new Escolaridad();
                e.setIdEscolaridad(rs.getInt("id_escolaridad"));
                e.setNombre(rs.getString("nombre"));
                lista.add(e);
            }

        } catch (SQLException ex) {
            System.out.println("Error listar escolaridades: " + ex.getMessage());
        }

        return lista;
    }
    
    public boolean actualizar(Escolaridad e) {
        String sql = "UPDATE escolaridades SET nombre = ? WHERE id_escolaridad = ?";

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, e.getNombre());
            ps.setInt(2, e.getIdEscolaridad());
            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.out.println("Error actualizar escolaridad: " + ex.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM escolaridades WHERE id_escolaridad = ?";

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.out.println("Error eliminar escolaridad: " + ex.getMessage());
            return false;
        }
    }
}