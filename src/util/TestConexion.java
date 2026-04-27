/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import dao.ConexionBD;
import java.sql.Connection;

public class TestConexion {
    public static void main(String[] args) {
        try (Connection conn = ConexionBD.getConexion()) {
            if (conn != null) {
                System.out.println("Conexión exitosa a MySQL");
            }
        } catch (Exception e) {
            System.out.println("Error de conexión:");
            e.printStackTrace();
        }
    }
}
