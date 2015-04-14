/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cesjf.lpaw.db;

import br.cesjf.lpaw.classes.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author guilhermecortes
 */
public class UsuarioDAO 
{
    private static Connection conexao = null;
    private static PreparedStatement comandoListar;
    private static PreparedStatement comandoCriar;
    private static PreparedStatement comandoExcluir;
    private static PreparedStatement comandoSalvar;
    private static PreparedStatement comandoBuscaPorId;
    private static PreparedStatement comandoBuscaLogin;
    
    public UsuarioDAO() throws Exception 
    {
        conexao = ConexaoJavaDB.getConnection();
        
        comandoListar = conexao.prepareStatement("SELECT * FROM usuario"); 
        comandoCriar = conexao.prepareStatement("INSERT INTO usuario (nome, email, senha) VALUES(?,?,?)");
        comandoExcluir = conexao.prepareStatement("DELETE FROM usuario WHERE id = ?");
        comandoSalvar = conexao.prepareStatement("UPDATE usuario SET nome=?, email=?, senha=? WHERE id=?");
        comandoBuscaPorId = conexao.prepareStatement("SELECT * FROM usuario WHERE id = ?");
        comandoBuscaLogin = conexao.prepareStatement("SELECT * FROM usuario WHERE email = ? AND senha = ?");
        
    }
    
    //Listar 
    public List<Usuario> listAll() throws Exception
    {
        List<Usuario> usuarios = new ArrayList<>();
        ResultSet resultados = comandoListar.executeQuery();
        System.out.println("Comando Executado!");
        while (resultados.next())
        {
            usuarios.add(new Usuario(resultados.getInt("id"), resultados.getString("nome"), 
                    resultados.getString("email"), resultados.getString("senha")));
        }
        return usuarios;
    }
    
    //Criar 
    public void criar(Usuario usuario) throws Exception
    {
        comandoCriar.setString(1, usuario.getNome());
        comandoCriar.setString(2, usuario.getEmail());
        comandoCriar.setString(3, usuario.getSenha());
        comandoCriar.executeUpdate();
    }
 
    //Excluir 
    public void excluir(Usuario usuario) throws Exception
    {
        comandoExcluir.setInt(1, usuario.getId());
        comandoExcluir.executeUpdate();
    }
    
    //Editar 
    public void salvar(Usuario usuario) throws Exception
    {
        comandoSalvar.setString(1, usuario.getNome());
        comandoSalvar.setString(2, usuario.getEmail());
        comandoSalvar.setString(3, usuario.getSenha());
        comandoSalvar.setInt(4, usuario.getId());
        comandoSalvar.executeUpdate();
    }
    
    
    //Busca pelo id
    public Usuario buscaPorId(Integer id) throws Exception
    {
        Usuario usuario = null;
        comandoBuscaPorId.setInt(1, id);
        ResultSet resultado = comandoBuscaPorId.executeQuery();
        if(resultado.next())
        {
            usuario = new Usuario();
            usuario.setNome(resultado.getString("nome"));
            usuario.setEmail(resultado.getString("email"));
            usuario.setSenha(resultado.getString("senha"));
        } else
        {
            throw new Exception("Não existe usuario com id: " + id);
        }
        return usuario;
    }    
    
    //Retorna true ou false se o usuário existir
    public boolean buscaLogin(String email, String senha) throws Exception
    {
        Usuario usuario = null;
        comandoBuscaLogin.setString(1, email);
        comandoBuscaLogin.setString(2, senha);
        ResultSet resultado = comandoBuscaLogin.executeQuery();
        if(resultado.next())
        {
            usuario = new Usuario();
            usuario.setNome(resultado.getString("nome"));
            usuario.setEmail(resultado.getString("email"));
            usuario.setSenha(resultado.getString("senha"));
            return true;
        } else
        {
            return false;
        }
    }    
        
    
    //Retorna true ou false se o usuário existir
    public Usuario retornaUsuario(String email, String senha)
    {
        Usuario usuario = null;
        try {
            comandoBuscaLogin.setString(1, email);
            comandoBuscaLogin.setString(2, senha);
            ResultSet resultado = comandoBuscaLogin.executeQuery();
            if(resultado.next())
            {
                usuario = new Usuario();
                usuario.setNome(resultado.getString("nome"));
                usuario.setEmail(resultado.getString("email"));
                usuario.setSenha(resultado.getString("senha"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;
    }    
    
    
}
