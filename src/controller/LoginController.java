/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author anahi
 */

import dao.UsuarioDAO;
import modelo.Usuario;
import view.LoginFrame;

public class LoginController {

    private final LoginFrame view;
    private final UsuarioDAO usuarioDAO;

    public LoginController(LoginFrame view) {
        this.view = view;
        this.usuarioDAO = new UsuarioDAO();
    }

    public void iniciarSesion() {
        String username = view.getUsuario();
        String password = view.getPassword();

        if (username.isEmpty()) {
            view.mostrarError("El usuario es obligatorio.");
            return;
        }

        if (password.isEmpty()) {
            view.mostrarError("La contraseña es obligatoria.");
            return;
        }

        Usuario usuario = usuarioDAO.autenticar(username, password);

        if (usuario == null) {
            view.mostrarError("Usuario o contraseña incorrectos.");
            return;
        }

        view.mostrarMensaje("Bienvenido " + usuario.getNombre());
        view.abrirMenuPrincipal();
    }
}