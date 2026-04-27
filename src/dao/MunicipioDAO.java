package dao;

import modelo.Municipio;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MunicipioDAO {

    public List<Municipio> listar() {
        List<Municipio> lista = new ArrayList<>();
        String sql = "SELECT id_municipio, nombre FROM municipios ORDER BY nombre";

        try (Connection conn = ConexionBD.getConexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Municipio m = new Municipio();
                m.setIdMunicipio(rs.getInt("id_municipio"));
                m.setNombre(rs.getString("nombre"));
                lista.add(m);
            }

        } catch (SQLException e) {
            System.out.println("Error listar municipios: " + e.getMessage());
        }

        return lista;
    }

    public Municipio obtenerPorId(int id) {
        String sql = "SELECT id_municipio, nombre FROM municipios WHERE id_municipio = ?";
        Municipio m = null;

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                m = new Municipio();
                m.setIdMunicipio(rs.getInt("id_municipio"));
                m.setNombre(rs.getString("nombre"));
            }

        } catch (SQLException e) {
            System.out.println("Error obtener municipio: " + e.getMessage());
        }

        return m;
    }

    public boolean insertar(Municipio m) {
        String sql = "INSERT INTO municipios (nombre) VALUES (?)";

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, m.getNombre());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error insertar municipio: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizar(Municipio m) {
        String sql = "UPDATE municipios SET nombre = ? WHERE id_municipio = ?";

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, m.getNombre());
            ps.setInt(2, m.getIdMunicipio());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error actualizar municipio: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM municipios WHERE id_municipio = ?";

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error eliminar municipio: " + e.getMessage());
            return false;
        }
    }
}