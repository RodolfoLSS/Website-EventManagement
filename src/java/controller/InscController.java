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
import model.Inscricao;

/**
 *
 * @author RodolfoSaldanha
 */

@WebServlet(
        name = "InscController",
        urlPatterns = {
            "/inscricao/add",
            "/inscricao"})
public class InscController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        switch (request.getServletPath()) {
            case "/inscricao":
                fazerInscricao(request, response);
                break;
            case "/inscricao/add":
                fazerInscricao(request, response);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        switch (request.getServletPath()) {
            case "/inscricao":
                RequestDispatcher dispatcher = request.getRequestDispatcher("/view/inscricao/index.jsp");
                dispatcher.forward(request, response);
                break;
            case "/inscricao/add":
                fazerInscricao(request, response);
                break;
        }

    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void fazerInscricao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Inscricao inscricao = new Inscricao();
        inscricao.setPayment(request.getParameter("payment"));
        inscricao.setGetToKnowEvent(request.getParameter("getToKnowEvent"));
        inscricao.setDescriptiveField(request.getParameter("descriptive"));
        inscricao.setFk_eventid((long)6);
        inscricao.setFk_uid((long)2);
        
        try (DAOFactory daoFactory = new DAOFactory();) {
            DAO dao = daoFactory.getInscricaoDAO();

            dao.create(inscricao);

            response.sendRedirect(request.getContextPath() + "/evento/partc");
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            HttpSession session = request.getSession();
            session.setAttribute("error", ex.getMessage());
            response.sendRedirect(request.getContextPath() + "/evento/create");
        }
    }
    
}