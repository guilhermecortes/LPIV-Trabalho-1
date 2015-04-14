/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cesjf.lpaw.servlets;

import br.cesjf.lpaw.classes.Estadia;
import br.cesjf.lpaw.classes.Evento;
import br.cesjf.lpaw.db.EstadiaDAO;
import br.cesjf.lpaw.db.EventoDAO;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author guilhermecortes
 */
@WebServlet(name = "CriaEntrada", urlPatterns = {"/CriaEntrada"})
public class CriaEntrada extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String placa = request.getParameter("placa");
        HttpSession session = request.getSession(true);
        String nome_usuario = (String) session.getAttribute("nome_funcionario");

        EstadiaDAO dao;
        EventoDAO eventoDAO = null;
        try {
            dao = new EstadiaDAO();
            eventoDAO = new EventoDAO();
            
            Estadia estadia = new Estadia();
            
            estadia.setPlaca(placa);
            estadia.setEntrada(new Date());

            dao.criarEstadia(estadia);
            
            //Cria evento
            Evento evento = new Evento();
            evento.setAcao("Entrada");
            evento.setUsuario(nome_usuario);
            evento.setEstadia(estadia.getId().toString());
            eventoDAO.criar(evento);
            
            response.sendRedirect("patio");
        } 
        catch (Exception ex) {
            throw new ServletException(ex);
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
