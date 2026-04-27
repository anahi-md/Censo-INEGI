/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import modelo.Habitante;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HabitanteDAO {

    // INSERT
    public boolean insertar(Habitante h) {
        String sql = "INSERT INTO habitantes (nombre, paterno, materno, edad, fecha_nac, curp, id_sexo, id_vivienda, id_escolaridad, id_ocupacion, estatus) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, h.getNombre());
            ps.setString(2, h.getPaterno());
            ps.setString(3, h.getMaterno());
            ps.setInt(4, h.getEdad());
            if (h.getFechaNac() != null) {
                ps.setDate(5, new java.sql.Date(h.getFechaNac().getTime()));
            } else {
                ps.setNull(5, java.sql.Types.DATE);
            }
            ps.setString(6, h.getCurp());
            ps.setInt(7, h.getIdSexo());
            ps.setInt(8, h.getIdVivienda());
            ps.setInt(9, h.getIdEscolaridad());
            ps.setInt(10, h.getIdOcupacion());
            ps.setBoolean(11, h.isEstatus());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error insertar habitante: " + e.getMessage());
            return false;
        }
    }

    // SELECT (listar todos)
    public List<Habitante> listar() {
        List<Habitante> lista = new ArrayList<>();
        String sql = "SELECT\n" +
                        "    h.id_habitante,\n" +
                        "    h.nombre,\n" +
                        "    h.paterno,\n" +
                        "    h.materno,\n" +
                        "    h.edad,\n" +
                        "    h.curp,\n" +
                        "    h.fecha_nac,\n" +
                        "    s.nombre AS sexo,\n" +
                        "    v.calle AS vivienda,\n" +
                        "    e.nombre AS escolaridad,\n" +
                        "    o.nombre AS ocupacion,\n" +
                        "    h.estatus\n" +
                        "FROM habitantes h\n" +
                        "INNER JOIN sexos s ON h.id_sexo = s.id_sexo\n" +
                        "INNER JOIN viviendas v ON h.id_vivienda = v.id_vivienda\n" +
                        "INNER JOIN escolaridades e ON h.id_escolaridad = e.id_escolaridad\n" +
                        "INNER JOIN ocupaciones o ON h.id_ocupacion = o.id_ocupacion\n" +
                        "WHERE h.estatus = 1\n" +
                        "ORDER BY h.id_habitante DESC;";

        try (Connection conn = ConexionBD.getConexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Habitante h = new Habitante();

                h.setIdHabitante(rs.getInt("id_habitante"));
                h.setNombre(rs.getString("nombre"));
                h.setPaterno(rs.getString("paterno"));
                h.setMaterno(rs.getString("materno"));
                h.setEdad(rs.getInt("edad"));
                h.setFechaNac(rs.getDate("fecha_nac"));
                h.setCurp(rs.getString("curp"));
                h.setEstatus(rs.getBoolean("estatus"));
                h.setNombreSexo(rs.getString("sexo"));
                h.setNombreVivienda(rs.getString("vivienda"));
                h.setNombreEscolaridad(rs.getString("escolaridad"));
                h.setNombreOcupacion(rs.getString("ocupacion"));
                
                lista.add(h);
            }

        } catch (SQLException e) {
            System.out.println("Error listar habitantes: " + e.getMessage());
        }

        return lista;
    }

    // SELECT por ID
    public Habitante obtenerPorId(int id) {
        String sql = "SELECT * FROM habitantes WHERE id_habitante = ?";
        Habitante h = null;

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                h = new Habitante();

                h.setIdHabitante(rs.getInt("id_habitante"));
                h.setNombre(rs.getString("nombre"));
                h.setPaterno(rs.getString("paterno"));
                h.setMaterno(rs.getString("materno"));
                h.setEdad(rs.getInt("edad"));
                h.setFechaNac(rs.getDate("fecha_nac"));
                h.setCurp(rs.getString("curp"));
                h.setIdSexo(rs.getInt("id_sexo"));
                h.setIdVivienda(rs.getInt("id_vivienda"));
                h.setIdEscolaridad(rs.getInt("id_escolaridad"));
                h.setIdOcupacion(rs.getInt("id_ocupacion"));
                h.setEstatus(rs.getBoolean("estatus"));
            }

        } catch (SQLException e) {
            System.out.println("Error obtener habitante: " + e.getMessage());
        }

        return h;
    }

    // UPDATE
    public boolean actualizar(Habitante h) {
        String sql = "UPDATE habitantes SET nombre=?, paterno=?, materno=?, edad=?, fecha_nac=?, curp=?, id_sexo=?, id_vivienda=?, id_escolaridad=?, id_ocupacion=? WHERE id_habitante=?";

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, h.getNombre());
            ps.setString(2, h.getPaterno());
            ps.setString(3, h.getMaterno());
            ps.setInt(4, h.getEdad());
            ps.setDate(5, new java.sql.Date(h.getFechaNac().getTime()));
            ps.setString(6, h.getCurp());
            ps.setInt(7, h.getIdSexo());
            ps.setInt(8, h.getIdVivienda());
            ps.setInt(9, h.getIdEscolaridad());
            ps.setInt(10, h.getIdOcupacion());
            ps.setInt(11, h.getIdHabitante());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error actualizar habitante: " + e.getMessage());
            return false;
        }
    }

    // DELETE lógico
    public boolean eliminar(int id) {
        String sql = "UPDATE habitantes SET estatus = 0 WHERE id_habitante = ?";

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error eliminar habitante: " + e.getMessage());
            return false;
        }
    }

    // listar por vivienda
    public List<Habitante> listarPorVivienda(int idVivienda) {
        List<Habitante> lista = new ArrayList<>();
        String sql = "SELECT * FROM habitantes WHERE id_vivienda = ? AND estatus = 1";

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idVivienda);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Habitante h = new Habitante();

                h.setIdHabitante(rs.getInt("id_habitante"));
                h.setNombre(rs.getString("nombre"));
                h.setPaterno(rs.getString("paterno"));
                h.setMaterno(rs.getString("materno"));
                h.setEdad(rs.getInt("edad"));
                h.setFechaNac(rs.getDate("fecha_nac"));
                h.setCurp(rs.getString("curp"));
                h.setIdSexo(rs.getInt("id_sexo"));
                h.setIdVivienda(rs.getInt("id_vivienda"));
                h.setIdEscolaridad(rs.getInt("id_escolaridad"));
                h.setIdOcupacion(rs.getInt("id_ocupacion"));
                h.setEstatus(rs.getBoolean("estatus"));

                lista.add(h);
            }

        } catch (SQLException e) {
            System.out.println("Error listar por vivienda: " + e.getMessage());
        }

        return lista;
    }
}