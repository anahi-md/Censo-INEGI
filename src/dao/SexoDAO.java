package dao;

import modelo.Sexo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SexoDAO {

    public boolean insertar(Sexo s) {
        String sql = "INSERT INTO sexos (nombre) VALUES (?)";

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, s.getNombre());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error insertar sexo: " + e.getMessage());
            return false;
        }
    }

    public List<Sexo> listar() {
        List<Sexo> lista = new ArrayList<>();
        String sql = "SELECT id_sexo, nombre FROM sexos ORDER BY nombre";

        try (Connection conn = ConexionBD.getConexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Sexo s = new Sexo();
                s.setIdSexo(rs.getInt("id_sexo"));
                s.setNombre(rs.getString("nombre"));
                lista.add(s);
            }

        } catch (SQLException e) {
            System.out.println("Error listar sexos: " + e.getMessage());
        }

        return lista;
    }

    public boolean actualizar(Sexo s) {
        String sql = "UPDATE sexos SET nombre = ? WHERE id_sexo = ?";

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, s.getNombre());
            ps.setInt(2, s.getIdSexo());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error actualizar sexo: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM sexos WHERE id_sexo = ?";

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error eliminar sexo: " + e.getMessage());
            return false;
        }
    }
}