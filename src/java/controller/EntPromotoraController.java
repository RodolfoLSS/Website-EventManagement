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
import model.EntPromotora;

/**
 *
 * @author RodolfoSaldanha
 */

@WebServlet(
        name = "EntPromotoraController",
        urlPatterns = {
            "/entpromotora",
            "/entpromotora/create",
            "/entpromotora/update"})
public class EntPromotoraController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher;

        switch (request.getServletPath()) {
            case "/entpromotora":
                listEntPromotora(request, response);
                break;
            case "/entpromotora/create":
                dispatcher = request.getRequestDispatcher("/view/entPromotora/create.jsp?acao=c");
                dispatcher.forward(request, response);
                break;

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        switch (request.getServletPath()) {
            case "/entpromotora/create":
                createEntPromotora(request, response);
                break;
        case "/entpromotora":
                listEntPromotora(request, response);
                break;
  
        }

    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void listEntPromotora(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (DAOFactory daoFactory = new DAOFactory();) {
            DAO dao = daoFactory.getEntPromotoraDAO();

            List<EntPromotora> entPromotoraList = dao.all();
            request.setAttribute("entPromotoraList", entPromotoraList);
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            request.getSession().setAttribute("error", ex.getMessage());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/entPromotora/index.jsp");
        dispatcher.forward(request, response);

    }

    private void createEntPromotora(HttpServletRequest request, HttpServletResponse response) throws IOException {
        EntPromotora entPromotora = new EntPromotora();
        entPromotora.setEntName(request.getParameter("entName"));
        entPromotora.setDescription(request.getParameter("description"));

        try (DAOFactory daoFactory = new DAOFactory();) {
            DAO dao = daoFactory.getEntPromotoraDAO();

            dao.create(entPromotora);

            response.sendRedirect(request.getContextPath() + "/entpromotora");
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            HttpSession session = request.getSession();
            session.setAttribute("error", ex.getMessage());
            response.sendRedirect(request.getContextPath() + "/entpromotora/create");
        }
    }
}
