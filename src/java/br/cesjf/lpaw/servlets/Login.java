/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cesjf.lpaw.servlets;

import br.cesjf.lpaw.db.ConexaoJavaDB;
import br.cesjf.lpaw.db.UsuarioDAO;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
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
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    //Conexão com o banco
    private static Connection conexao = null;
    
    public Login() throws ServletException 
    {
            try 
            {
                conexao = ConexaoJavaDB.getConnection();
            } catch (ClassNotFoundException ex) 
            {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                throw new ServletException(ex);   //mensagem de erro problema no drive         

            } catch (SQLException ex) 
            {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                throw new ServletException(ex);//msg erro problema conexao
            }
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        UsuarioDAO dao = null;
        
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        
        try {
            dao = new UsuarioDAO();
            if(dao.buscaLogin(email, senha)){
                HttpSession session = request.getSession(true);
                
                session.setAttribute("usuario", dao.retornaUsuario(email, senha));
                session.setAttribute("email_funcionario", email);
                session.setAttribute("nome_funcionario", dao.retornaUsuario(email, senha).getNome());
                response.sendRedirect("patio");
                
            } else {
                throw new ServletException("Usuário não existe");
            }
        } catch (Exception ex) {
            throw new ServletException("Erro ao criar usuario DAO "+ex);
            
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
