package dao;

import modelo.Localidad;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocalidadDAO {

    public List<Localidad> listar() {
        List<Localidad> lista = new ArrayList<>();
        String sql = "SELECT id_localidad, nombre, id_municipio FROM localidades ORDER BY nombre";

        try (Connection conn = ConexionBD.getConexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Localidad l = new Localidad();
                l.setIdLocalidad(rs.getInt("id_localidad"));
                l.setNombre(rs.getString("nombre"));
                l.setIdMunicipio(rs.getInt("id_municipio"));
                lista.add(l);
            }

        } catch (SQLException e) {
            System.out.println("Error listar localidades: " + e.getMessage());
        }

        return lista;
    }

    public List<Localidad> listarPorMunicipio(int idMunicipio) {
        List<Localidad> lista = new ArrayList<>();
        String sql = "SELECT id_localidad, nombre, id_municipio FROM localidades WHERE id_municipio = ? ORDER BY nombre";

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idMunicipio);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Localidad l = new Localidad();
                l.setIdLocalidad(rs.getInt("id_localidad"));
                l.setNombre(rs.getString("nombre"));
                l.setIdMunicipio(rs.getInt("id_municipio"));
                lista.add(l);
            }

        } catch (SQLException e) {
            System.out.println("Error listar localidades por municipio: " + e.getMessage());
        }

        return lista;
    }

    public Localidad obtenerPorId(int id) {
        String sql = "SELECT id_localidad, nombre, id_municipio FROM localidades WHERE id_localidad = ?";
        Localidad l = null;

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                l = new Localidad();
                l.setIdLocalidad(rs.getInt("id_localidad"));
                l.setNombre(rs.getString("nombre"));
                l.setIdMunicipio(rs.getInt("id_municipio"));
            }

        } catch (SQLException e) {
            System.out.println("Error obtener localidad: " + e.getMessage());
        }

        return l;
    }

    public boolean insertar(Localidad l) {
        String sql = "INSERT INTO localidades (nombre, id_municipio) VALUES (?, ?)";

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, l.getNombre());
            ps.setInt(2, l.getIdMunicipio());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error insertar localidad: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizar(Localidad l) {
        String sql = "UPDATE localidades SET nombre = ?, id_municipio = ? WHERE id_localidad = ?";

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, l.getNombre());
            ps.setInt(2, l.getIdMunicipio());
            ps.setInt(3, l.getIdLocalidad());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error actualizar localidad: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM localidades WHERE id_localidad = ?";

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error eliminar localidad: " + e.getMessage());
            return false;
        }
    }
}