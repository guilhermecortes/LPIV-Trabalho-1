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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "SalvaEdicao", urlPatterns = {"/SalvaEdicao"})
public class SalvaEdicao extends HttpServlet {

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
        Integer id = Integer.valueOf(request.getParameter("id"));
        String placa = request.getParameter("placa");
        String entrada = (request.getParameter("entrada"));
        String saida = request.getParameter("saida");
        Double valor = Double.parseDouble(request.getParameter("valor"));
        
        //Pega os dados da sessão
        HttpSession session = request.getSession(true);
        String nome_usuario = (String) session.getAttribute("nome_funcionario");
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        Date dt_entrada = null;
        Date dt_saida = null;
        try {
            dt_entrada = sdf.parse(entrada);
            dt_saida = sdf.parse(saida);
        } catch (ParseException ex) {
            Logger.getLogger(SalvaEdicao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        EstadiaDAO dao;
        Estadia estadia = new Estadia();
        EventoDAO eventoDAO = null;
        try {
            dao = new EstadiaDAO();
            estadia = dao.buscaPorId(id);
            estadia.setPlaca(placa);
            estadia.setEntrada(dt_entrada);
            estadia.setSaida(dt_saida);
            estadia.setValor_pago(valor);
            dao.salvarEstadia(estadia);
           
            //Cria evento
            Evento evento = new Evento();
            eventoDAO = new EventoDAO();
            evento.setAcao("Edição");
            evento.setUsuario(nome_usuario);
            evento.setEstadia(id.toString());
            eventoDAO.criar(evento);
            
            response.sendRedirect("patio");
        } 
        catch (Exception ex) {
            throw new ServletException("SalvaEdicao!!!!!!!" + ex);
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
