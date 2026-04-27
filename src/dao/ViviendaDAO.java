package dao;

import modelo.Vivienda;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ViviendaDAO {

    // INSERT
    public boolean insertar(Vivienda v) {
        String sql = "INSERT INTO viviendas (calle, num_exterior, num_interior, colonia, codigo_postal, id_localidad, id_municipio, id_tipo_vivienda, num_cuartos, num_banios, material_predominante, servicios_basicos, observaciones, estatus) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, v.getCalle());
            ps.setString(2, v.getNumExterior());
            ps.setString(3, v.getNumInterior());
            ps.setString(4, v.getColonia());
            ps.setString(5, v.getCodigoPostal());
            ps.setInt(6, v.getIdLocalidad());
            ps.setInt(7, v.getIdMunicipio());
            ps.setInt(8, v.getIdTipoVivienda());
            ps.setInt(9, v.getNumCuartos());
            ps.setInt(10, v.getNumBanios());
            ps.setString(11, v.getMaterialPredominante());
            ps.setString(12, v.getServiciosBasicos());
            ps.setString(13, v.getObservaciones());
            ps.setBoolean(14, v.isEstatus());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error insertar vivienda: " + e.getMessage());
            return false;
        }
    }

    // SELECT
    public List<Vivienda> listar() {
        List<Vivienda> lista = new ArrayList<>();
        String sql = """
                        SELECT 
                            v.id_vivienda,
                            v.calle,
                            v.num_exterior,
                            v.num_interior,
                            v.colonia,
                            v.codigo_postal,
                            v.id_municipio,
                            v.id_localidad,
                            v.id_tipo_vivienda,
                            v.num_cuartos,
                            v.num_banios,
                            v.material_predominante,
                            v.servicios_basicos,
                            v.observaciones,
                            v.estatus,
                            COALESCE(m.nombre, 'Sin municipio') AS municipio,
                            COALESCE(l.nombre, 'Sin localidad') AS localidad,
                            COALESCE(tv.nombre, 'Sin tipo') AS tipo_vivienda
                        FROM viviendas v
                        LEFT JOIN municipios m ON v.id_municipio = m.id_municipio
                        LEFT JOIN localidades l ON v.id_localidad = l.id_localidad
                        LEFT JOIN tipoviviendas tv ON v.id_tipo_vivienda = tv.id_tipovivienda
                        WHERE v.estatus = 1 OR v.estatus IS NULL
                        ORDER BY v.id_vivienda DESC
                    """;

        try (Connection conn = ConexionBD.getConexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Vivienda v = new Vivienda();

                v.setIdVivienda(rs.getInt("id_vivienda"));
                v.setCalle(rs.getString("calle"));
                v.setNumExterior(rs.getString("num_exterior"));
                v.setNumInterior(rs.getString("num_interior"));
                v.setColonia(rs.getString("colonia"));
                v.setCodigoPostal(rs.getString("codigo_postal"));
                v.setNumCuartos(rs.getInt("num_cuartos"));
                v.setNumBanios(rs.getInt("num_banios"));
                v.setMaterialPredominante(rs.getString("material_predominante"));
                v.setServiciosBasicos(rs.getString("servicios_basicos"));
                v.setObservaciones(rs.getString("observaciones"));
                v.setEstatus(rs.getBoolean("estatus"));
                v.setNombreMunicipio(rs.getString("municipio"));
                v.setNombreLocalidad(rs.getString("localidad"));
                v.setNombreTipoVivienda(rs.getString("tipo_vivienda"));
                
                lista.add(v);
            }

        } catch (SQLException e) {
            System.out.println("Error listar viviendas: " + e.getMessage());
        }

        return lista;
    }

    // UPDATE
    public boolean actualizar(Vivienda v) {
        String sql = "UPDATE viviendas SET calle=?, num_exterior=?, num_interior=?, colonia=?, codigo_postal=?, id_localidad=?, id_municipio=?, id_tipo_vivienda=?, num_cuartos=?, num_banios=?, material_predominante=?, servicios_basicos=?, observaciones=? WHERE id_vivienda=?";

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, v.getCalle());
            ps.setString(2, v.getNumExterior());
            ps.setString(3, v.getNumInterior());
            ps.setString(4, v.getColonia());
            ps.setString(5, v.getCodigoPostal());
            ps.setInt(6, v.getIdLocalidad());
            ps.setInt(7, v.getIdMunicipio());
            ps.setInt(8, v.getIdTipoVivienda());
            ps.setInt(9, v.getNumCuartos());
            ps.setInt(10, v.getNumBanios());
            ps.setString(11, v.getMaterialPredominante());
            ps.setString(12, v.getServiciosBasicos());
            ps.setString(13, v.getObservaciones());
            ps.setInt(14, v.getIdVivienda());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error actualizar vivienda: " + e.getMessage());
            return false;
        }
    }

    // DELETE ( solo elimina de manera logica)
    public boolean eliminar(int id) {
        String sql = "UPDATE viviendas SET estatus = 0 WHERE id_vivienda = ?";

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error eliminar vivienda: " + e.getMessage());
            return false;
        }
    }
}