/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

/**
 *
 * @author anahi
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReporteDAO {

    public int totalHabitantes() {
        String sql = "SELECT COUNT(*) FROM habitantes WHERE estatus = 1";

        try (Connection conn = ConexionBD.getConexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            if (rs.next()) return rs.getInt(1);

        } catch (SQLException e) {
            System.out.println("Error total habitantes: " + e.getMessage());
        }

        return 0;
    }

    public int totalViviendas() {
        String sql = "SELECT COUNT(*) FROM viviendas WHERE estatus = 1";

        try (Connection conn = ConexionBD.getConexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            if (rs.next()) return rs.getInt(1);

        } catch (SQLException e) {
            System.out.println("Error total viviendas: " + e.getMessage());
        }

        return 0;
    }

    public List<Object[]> habitantesPorMunicipio() {
        List<Object[]> lista = new ArrayList<>();

        String sql = """
            SELECT m.nombre AS municipio, COUNT(h.id_habitante) AS total
            FROM habitantes h
            INNER JOIN viviendas v ON h.id_vivienda = v.id_vivienda
            INNER JOIN municipios m ON v.id_municipio = m.id_municipio
            WHERE h.estatus = 1 AND v.estatus = 1
            GROUP BY m.nombre
            ORDER BY total DESC
        """;

        try (Connection conn = ConexionBD.getConexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Object[]{
                    rs.getString("municipio"),
                    rs.getInt("total")
                });
            }

        } catch (SQLException e) {
            System.out.println("Error habitantes por municipio: " + e.getMessage());
        }

        return lista;
    }

    public List<Object[]> habitantesPorLocalidad() {
        List<Object[]> lista = new ArrayList<>();

        String sql = """
            SELECT l.nombre AS localidad, COUNT(h.id_habitante) AS total
            FROM habitantes h
            INNER JOIN viviendas v ON h.id_vivienda = v.id_vivienda
            INNER JOIN localidades l ON v.id_localidad = l.id_localidad
            WHERE h.estatus = 1 AND v.estatus = 1
            GROUP BY l.nombre
            ORDER BY total DESC
        """;

        try (Connection conn = ConexionBD.getConexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Object[]{
                    rs.getString("localidad"),
                    rs.getInt("total")
                });
            }

        } catch (SQLException e) {
            System.out.println("Error habitantes por localidad: " + e.getMessage());
        }

        return lista;
    }

    public List<Object[]> viviendasPorMunicipio() {
        List<Object[]> lista = new ArrayList<>();

        String sql = """
            SELECT m.nombre AS municipio, COUNT(v.id_vivienda) AS total
            FROM viviendas v
            INNER JOIN municipios m ON v.id_municipio = m.id_municipio
            WHERE v.estatus = 1
            GROUP BY m.nombre
            ORDER BY total DESC
        """;

        try (Connection conn = ConexionBD.getConexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Object[]{
                    rs.getString("municipio"),
                    rs.getInt("total")
                });
            }

        } catch (SQLException e) {
            System.out.println("Error viviendas por municipio: " + e.getMessage());
        }

        return lista;
    }

    public List<Object[]> habitantesPorTipoVivienda() {
        List<Object[]> lista = new ArrayList<>();

        String sql = """
            SELECT tv.nombre AS tipo_vivienda, COUNT(h.id_habitante) AS total
            FROM habitantes h
            INNER JOIN viviendas v ON h.id_vivienda = v.id_vivienda
            INNER JOIN tipoviviendas tv ON v.id_tipo_vivienda = tv.id_tipovivienda
            WHERE h.estatus = 1 AND v.estatus = 1
            GROUP BY tv.nombre
            ORDER BY total DESC
        """;

        try (Connection conn = ConexionBD.getConexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Object[]{
                    rs.getString("tipo_vivienda"),
                    rs.getInt("total")
                });
            }

        } catch (SQLException e) {
            System.out.println("Error habitantes por tipo vivienda: " + e.getMessage());
        }

        return lista;
    }

    public List<Object[]> habitantesPorVivienda(int idVivienda) {
        List<Object[]> lista = new ArrayList<>();

        String sql = """
            SELECT id_habitante, nombre, paterno, materno, edad, curp
            FROM habitantes
            WHERE id_vivienda = ? AND estatus = 1
            ORDER BY paterno, materno, nombre
        """;

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idVivienda);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new Object[]{
                        rs.getInt("id_habitante"),
                        rs.getString("nombre"),
                        rs.getString("paterno"),
                        rs.getString("materno"),
                        rs.getInt("edad"),
                        rs.getString("curp")
                    });
                }
            }

        } catch (SQLException e) {
            System.out.println("Error habitantes por vivienda: " + e.getMessage());
        }

        return lista;
    }
}