/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DAO;
import dao.DAOFactory;
import dao.UsuarioDAO;
import dao.EntPromotoraDAO;
import dao.InscricaoDAO;
import dao.LocalDAO;
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
import model.Evento;
import model.Usuario;
import model.EntPromotora;
import model.Local;
import model.Inscricao;

/**
 *
 * @author RodolfoSaldanha
 */

@WebServlet(
        name = "EventoController",
        urlPatterns = {
            "/evento",
            "/evento/partc",
            "/evento/membro",
            "/evento/create",
            "/evento/listpart",
            "/evento/update"})
public class EventoController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher;

        switch (request.getServletPath()) {
            case "/evento":
                listEventos(request, response);
                break;
            case "/evento/membro":
                listEventosMembro(request, response);
                break;
            case "/evento/partc":
                eventosAbertos(request, response);
                break;
            case "/evento/create":
                dispatcher = request.getRequestDispatcher("/view/evento/create.jsp?acao=c");
                dispatcher.forward(request, response);
                break;
            case "/evento/listpart":
                listPart(request, response);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        switch (request.getServletPath()) {
            case "/evento/create":
                createEvento(request, response);
                break;
            case "/evento/update":
                updateEvento(request, response);
                break;
  
        }

    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void listEventos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (DAOFactory daoFactory = new DAOFactory();) {
            DAO dao = daoFactory.getEventoDAO();

            List<Evento> eventoList = dao.all();
            request.setAttribute("eventoList", eventoList);
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            request.getSession().setAttribute("error", ex.getMessage());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/evento/index.jsp");
        dispatcher.forward(request, response);

    }
    
    private void listPart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (DAOFactory daoFactory = new DAOFactory();) {
            InscricaoDAO daoI = daoFactory.getInscricaoDAO();
            
            List<Usuario> partList = daoI.inscritosList(Long.parseLong(request.getParameter("id")));
            request.setAttribute("partList", partList);
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            request.getSession().setAttribute("error", ex.getMessage());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/evento/listpart.jsp");
        dispatcher.forward(request, response);

    }
    
    private void listEventosMembro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (DAOFactory daoFactory = new DAOFactory();) {
            DAO dao = daoFactory.getEventoDAO();

            List<Evento> eventoList = dao.all();
            request.setAttribute("eventoList", eventoList);
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            request.getSession().setAttribute("error", ex.getMessage());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/evento/eventoMembro.jsp");
        dispatcher.forward(request, response);

    }
    
    private void eventosAbertos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (DAOFactory daoFactory = new DAOFactory();) {
            DAO dao = daoFactory.getEventoDAO();

            List<Evento> eventoList = dao.all();
            request.setAttribute("eventoList", eventoList);
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            request.getSession().setAttribute("error", ex.getMessage());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/evento/partc.jsp");
        dispatcher.forward(request, response);

    }

    private void createEvento(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Evento evento = new Evento();
        Usuario usuario = new Usuario();
        EntPromotora entPromotora = new EntPromotora();
        Local local = new Local();
        
        usuario.setPwd(request.getParameter("pwd"));
        local.setPlaceName(request.getParameter("place"));
        entPromotora.setEntName(request.getParameter("entPromotora"));
        
        try (DAOFactory daoFactory = new DAOFactory();) {
            UsuarioDAO daoU = daoFactory.getUsuarioDAO();
            daoU.setId(usuario);
            
            EntPromotoraDAO daoE = daoFactory.getEntPromotoraDAO();
            daoE.setId(entPromotora);
            
            LocalDAO daoL = daoFactory.getLocalDAO();
            daoL.setId(local);
            
            
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            HttpSession session = request.getSession();
            session.setAttribute("error", ex.getMessage());
        }
        
        evento.setEventName(request.getParameter("eventName"));
        evento.setDescription(request.getParameter("description"));
        evento.setImpInformation(request.getParameter("impInformation"));
        evento.setPrice(Integer.parseInt(request.getParameter("price")));
        evento.setBeginDate(request.getParameter("beginDate"));
        evento.setEndDate(request.getParameter("endDate"));
        evento.setBeginTime(request.getParameter("beginTime"));
        evento.setEndTime(request.getParameter("endTime"));
        evento.setWeekdays(request.getParameter("weekdays"));
        evento.setBeginDate(request.getParameter("beginDate"));
        evento.setEventType(Integer.parseInt(request.getParameter("eventType")));
        evento.setFk_usuario_evento(usuario.getUid());
        evento.setFk_local_evento(local.getLid());
        evento.setFk_entPromotora_evento(entPromotora.getEid());

        try (DAOFactory daoFactory = new DAOFactory();) {
            DAO dao = daoFactory.getEventoDAO();

            dao.create(evento);

            response.sendRedirect(request.getContextPath() + "/evento");
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            HttpSession session = request.getSession();
            session.setAttribute("error", ex.getMessage());
            response.sendRedirect(request.getContextPath() + "/evento/create");
        }
    }
    
    private void updateEvento(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Evento evento = new Evento();
        Usuario usuario = new Usuario();
        EntPromotora entPromotora = new EntPromotora();
        Local local = new Local();
        
        usuario.setPwd(request.getParameter("pwd"));
        local.setPlaceName(request.getParameter("place"));
        entPromotora.setEntName(request.getParameter("entPromotora"));
        
        try (DAOFactory daoFactory = new DAOFactory();) {
            UsuarioDAO daoU = daoFactory.getUsuarioDAO();
            daoU.setId(usuario);
            
            EntPromotoraDAO daoE = daoFactory.getEntPromotoraDAO();
            daoE.setId(entPromotora);
            
            LocalDAO daoL = daoFactory.getLocalDAO();
            daoL.setId(local);
            
            
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            HttpSession session = request.getSession();
            session.setAttribute("error", ex.getMessage());
        }
        
        evento.setEventName(request.getParameter("eventName"));
        evento.setDescription(request.getParameter("description"));
        evento.setImpInformation(request.getParameter("impInformation"));
        evento.setPrice(Integer.parseInt(request.getParameter("price")));
        evento.setBeginDate(request.getParameter("beginDate"));
        evento.setEndDate(request.getParameter("endDate"));
        evento.setBeginTime(request.getParameter("beginTime"));
        evento.setEndTime(request.getParameter("endTime"));
        evento.setWeekdays(request.getParameter("weekdays"));
        evento.setBeginDate(request.getParameter("beginDate"));
        evento.setEventType(Integer.parseInt(request.getParameter("eventType")));
        evento.setFk_usuario_evento(usuario.getUid());
        evento.setFk_local_evento(local.getLid());
        evento.setFk_entPromotora_evento(entPromotora.getEid());

        try (DAOFactory daoFactory = new DAOFactory();) {
            DAO dao = daoFactory.getEventoDAO();

            dao.update(evento);

            response.sendRedirect(request.getContextPath() + "/evento");
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            HttpSession session = request.getSession();
            session.setAttribute("error", ex.getMessage());
            response.sendRedirect(request.getContextPath() + "/evento/create");
        }
    }
}
