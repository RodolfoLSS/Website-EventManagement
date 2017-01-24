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
import model.Local;

/**
 *
 * @author RodolfoSaldanha
 */
@WebServlet(
        name = "PlaceController",
        urlPatterns = {
            "/place",
            "/place/create",
            "/place/update"})
public class PlaceController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher;

        switch (request.getServletPath()) {
            case "/place":
                listLocal(request, response);
                break;
            case "/place/create":
                dispatcher = request.getRequestDispatcher("/view/local/create.jsp?acao=c");
                dispatcher.forward(request, response);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        switch (request.getServletPath()) {
            case "/place/create":
                createLocal(request, response);
                break;
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void listLocal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (DAOFactory daoFactory = new DAOFactory();) {
            DAO dao = daoFactory.getLocalDAO();

            List<Local> localList = dao.all();
            request.setAttribute("localList", localList);
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            request.getSession().setAttribute("error", ex.getMessage());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/local/index.jsp");
        dispatcher.forward(request, response);

    }

    private void createLocal(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Local local = new Local();

        local.setPlaceName(request.getParameter("placeName"));
        local.setTelephone(request.getParameter("telephone")); 
        local.setCep(request.getParameter("cep"));
        local.setLogradouro(request.getParameter("logradouro"));
        local.setComplemento(request.getParameter("complemento"));
        local.setBairro(request.getParameter("bairro"));
        local.setAddress(request.getParameter("logradouro") + request.getParameter("complemento"));
        local.setCity(request.getParameter("city"));
        local.setState(request.getParameter("state"));
        local.setCoordenates(request.getParameter("coordenates"));

        try (DAOFactory daoFactory = new DAOFactory();) {
            DAO dao = daoFactory.getLocalDAO();

            dao.create(local);

            response.sendRedirect(request.getContextPath() + "/place");
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            HttpSession session = request.getSession();
            session.setAttribute("error", ex.getMessage());
            response.sendRedirect(request.getContextPath() + "/place/create");
        }
    }

}