/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DAO;
import dao.DAOFactory;
import dao.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
@WebServlet(name = "LoginController", urlPatterns = {
    "/login",
    "/logout",
    ""})
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        switch (request.getServletPath()) {
            case "":
                verificaLogin(request, response);
                break;

            case "/logout":
                doLogout(request, response);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        switch (request.getServletPath()) {
            case "/login":
                doLogin(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void verificaLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);//obtém a sessão sem forçar criá-la (false)
        RequestDispatcher dispatcher;

        if (session != null && session.getAttribute("usuarioLogado") != null) {
            //Logado, redireciona para página inicial
            dispatcher = request.getRequestDispatcher("/welcome.jsp");
        } else {
            //Não logado, redireciona para página de login
            dispatcher = request.getRequestDispatcher("/index.jsp");
        }

        dispatcher.forward(request, response);
    }


    private void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        switch (request.getServletPath()) {
            case "/login":
                Usuario usuario = new Usuario();
                usuario.setLogin(request.getParameter("login"));
                usuario.setPwd(request.getParameter("pwd"));

                RequestDispatcher dispatcher;

                HttpSession session;
                session = request.getSession();

                try (DAOFactory daoFactory = new DAOFactory();) {
                    UsuarioDAO dao = daoFactory.getUsuarioDAO();//observe que o tipo é da subclasse (UsuarioDAO) e não da interface/classe abstrata (DAO), pois o método authenticate só é definido na subclasse

                    dao.authenticate(usuario);

                    session.setAttribute("usuarioLogado", usuario);
                } catch (ClassNotFoundException | IOException | SQLException | SecurityException ex) {
                    session.setAttribute("error", ex.getMessage());
                }

                if (usuario.getUserType() == 1 && session.getAttribute("usuarioLogado") != null) {
                    dispatcher = request.getRequestDispatcher("/welcomeParticipante.jsp");
                    //response.sendRedirect(request.getContextPath() + "/part");
                }
                else if (usuario.getUserType() == 2  && session.getAttribute("usuarioLogado") != null) {
                    dispatcher = request.getRequestDispatcher("/welcomeMembro.jsp");
                    //response.sendRedirect(request.getContextPath() + "/membro");
                }
                else if (usuario.getUserType() == 3  && session.getAttribute("usuarioLogado") != null){
                    dispatcher = request.getRequestDispatcher("/welcomeAdm.jsp");
                    //response.sendRedirect(request.getContextPath() + "/adm");
                }
                else{
                    dispatcher = request.getRequestDispatcher("/index.jsp");
                }
                
                dispatcher.forward(request, response);
        }
    }

    private void doLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();//Invalida a sessão; será retornada uma sessão nova no próximo getSession
        }

        response.sendRedirect(request.getContextPath() + "/");
    }

}
