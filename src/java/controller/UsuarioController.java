/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DAO;
import dao.DAOFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Usuario;

/**
 *
 * @author RodolfoSaldanha
 */
@WebServlet(
        name = "UsuarioController",
        urlPatterns = {
            "/usuario",
            "/usuario/create",
            "/usuario/info",
            "/usuario/delete",
            "/usuario/update"})
public class UsuarioController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher;

        switch (request.getServletPath()) {
            case "/usuario":
                listUsuarios(request, response);
                break;
            case "/usuario/info":
                listInfo(request, response);
                break;
            case "/usuario/create":
                dispatcher = request.getRequestDispatcher("/view/usuario/create.jsp?acao=c");
                dispatcher.forward(request, response);
                break;

            case "/usuario/delete":
                deleteUsuario(request, response);
                break;

            case "/usuario/update":
                updateUsuario(request, response);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        switch (request.getServletPath()) {
            case "/usuario/create":
                createUsuario(request, response);
                break;

            case "/usuario/update":
                updateUsuarioPost(request, response);
                break;
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void listUsuarios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (DAOFactory daoFactory = new DAOFactory();) {
            DAO dao = daoFactory.getUsuarioDAO();

            List<Usuario> usuarioList = dao.all();
            request.setAttribute("usuarioList", usuarioList);
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            request.getSession().setAttribute("error", ex.getMessage());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/usuario/index.jsp");
        dispatcher.forward(request, response);

    }
    
    private void listInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (DAOFactory daoFactory = new DAOFactory();) {
            DAO dao = daoFactory.getUsuarioDAO();

            List<Usuario> usuarioList = dao.all();
            request.setAttribute("usuarioList", usuarioList);
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            request.getSession().setAttribute("error", ex.getMessage());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/usuario/index.jsp");
        dispatcher.forward(request, response);

    }

    private void createUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Usuario usuario = new Usuario();

        usuario.setFullName(request.getParameter("fullName"));
        usuario.setPhotoPath("teste"); //att
        usuario.setCpf(request.getParameter("cpf"));
        usuario.setRg(request.getParameter("rg"));
        usuario.setNameTag(request.getParameter("nameTag"));
        usuario.setEmail(request.getParameter("email"));
        usuario.setCep(request.getParameter("cep"));
        usuario.setLogradouro(request.getParameter("logradouro"));
        usuario.setComplemento(request.getParameter("complemento"));
        usuario.setBairro(request.getParameter("bairro"));
        usuario.setAddress(request.getParameter("logradouro") + request.getParameter("complemento")); //att
        usuario.setCity(request.getParameter("city"));
        usuario.setState(request.getParameter("state"));
        usuario.setTelResidencial(request.getParameter("tel"));
        usuario.setDBirth(request.getParameter("dBirth"));
        usuario.setEstadoCivil(request.getParameter("estadoCivil"));
        usuario.setEscolaridade(request.getParameter("escolaridade")); 
        usuario.setProfession(request.getParameter("profession"));
        usuario.setInstOrigem(request.getParameter("instOrigem")); 
        usuario.setLogin(request.getParameter("login"));
        usuario.setPwd(request.getParameter("pwd"));
        usuario.setUserType(Integer.parseInt(request.getParameter("userType")));

        try (DAOFactory daoFactory = new DAOFactory();) {
            DAO dao = daoFactory.getUsuarioDAO();

            dao.create(usuario);

            response.sendRedirect(request.getContextPath() + "/usuario");
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            HttpSession session = request.getSession();
            session.setAttribute("error", ex.getMessage());
            response.sendRedirect(request.getContextPath() + "/usuario/create");
        }
    }

    private void deleteUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (DAOFactory daoFactory = new DAOFactory();) {
            DAO dao = daoFactory.getUsuarioDAO();

            dao.delete(Long.parseLong(request.getParameter("id")));
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            request.getSession().setAttribute("error", ex.getMessage());
        }

        response.sendRedirect(request.getContextPath() + "/usuario");

    }

    private void updateUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (DAOFactory daoFactory = new DAOFactory();) {
            DAO dao = daoFactory.getUsuarioDAO();

            Usuario usuario = (Usuario) dao.read(Long.parseLong(request.getParameter("id")));
            request.setAttribute("u", usuario);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/usuario/update.jsp?acao=u");
            dispatcher.forward(request, response);
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            request.getSession().setAttribute("error", ex.getMessage());
            response.sendRedirect(request.getContextPath() + "/usuario");
        }
    }

    private void updateUsuarioPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Usuario usuario = new Usuario();
        usuario.setFullName(request.getParameter("fullName"));
        usuario.setPhotoPath("teste"); //att
        usuario.setCpf(request.getParameter("cpf"));
        usuario.setRg(request.getParameter("rg"));
        usuario.setNameTag(request.getParameter("nameTag"));
        usuario.setEmail(request.getParameter("email"));
        usuario.setCep(request.getParameter("cep"));
        usuario.setLogradouro(request.getParameter("logradouro"));
        usuario.setComplemento(request.getParameter("complemento"));
        usuario.setBairro(request.getParameter("bairro"));
        usuario.setAddress(request.getParameter("logradouro") + request.getParameter("complemento")); //att
        usuario.setCity(request.getParameter("city"));
        usuario.setState(request.getParameter("state"));
        usuario.setTelResidencial(request.getParameter("tel"));
        //usuario.setDBirth(request.getParameter("dBirth"));
        usuario.setEstadoCivil(request.getParameter("estadoCivil"));
        usuario.setEscolaridade(request.getParameter("escolaridade")); 
        usuario.setProfession(request.getParameter("profession"));
        usuario.setInstOrigem(request.getParameter("instOrigem")); 
        usuario.setLogin(request.getParameter("login"));
        usuario.setPwd(request.getParameter("pwd"));
        usuario.setUserType(Integer.parseInt(request.getParameter("userType")));

        if (!request.getParameter("pwd").isEmpty()) {
            usuario.setPwd(request.getParameter("pwd"));
        }

        try (DAOFactory daoFactory = new DAOFactory();) {
            DAO dao = daoFactory.getUsuarioDAO();

            dao.update(usuario);

            response.sendRedirect(request.getContextPath() + "/usuario");
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            HttpSession session = request.getSession();
            session.setAttribute("error", ex.getMessage());
            response.sendRedirect(request.getContextPath() + "/usuario/update");
        }
    }

}