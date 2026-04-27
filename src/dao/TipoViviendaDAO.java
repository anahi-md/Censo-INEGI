package dao;

import modelo.TipoVivienda;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TipoViviendaDAO {

    public List<TipoVivienda> listar() {
        List<TipoVivienda> lista = new ArrayList<>();
        String sql = "SELECT id_tipovivienda, nombre FROM tipoviviendas ORDER BY nombre";

        try (Connection conn = ConexionBD.getConexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                TipoVivienda t = new TipoVivienda();
                t.setIdTipoVivienda(rs.getInt("id_tipovivienda"));
                t.setNombre(rs.getString("nombre"));
                lista.add(t);
            }

        } catch (SQLException e) {
            System.out.println("Error listar tipos de vivienda: " + e.getMessage());
        }

        return lista;
    }

    public TipoVivienda obtenerPorId(int id) {
        String sql = "SELECT id_tipovivienda, nombre FROM tipoviviendas WHERE id_tipovivienda = ?";
        TipoVivienda t = null;

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                t = new TipoVivienda();
                t.setIdTipoVivienda(rs.getInt("id_tipovivienda"));
                t.setNombre(rs.getString("nombre"));
            }

        } catch (SQLException e) {
            System.out.println("Error obtener tipo vivienda: " + e.getMessage());
        }

        return t;
    }

    public boolean insertar(TipoVivienda t) {
        String sql = "INSERT INTO tipoviviendas (nombre) VALUES (?)";

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, t.getNombre());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error insertar tipo vivienda: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizar(TipoVivienda t) {
        String sql = "UPDATE tipoviviendas SET nombre = ? WHERE id_tipovivienda = ?";

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, t.getNombre());
            ps.setInt(2, t.getIdTipoVivienda());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error actualizar tipo vivienda: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM tipoviviendas WHERE id_tipovivienda = ?";

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error eliminar tipo vivienda: " + e.getMessage());
            return false;
        }
    }
}