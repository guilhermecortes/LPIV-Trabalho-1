/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cesjf.lpaw.db;

import br.cesjf.lpaw.classes.Evento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author guilhermecortes
 */
public class EventoDAO 
{
    private static Connection conexao = null;
    private static PreparedStatement comandoListar;
    private static PreparedStatement comandoCriar;
//    private static PreparedStatement comandoExcluirEstadia;
//    private static PreparedStatement comandoSalvar;
//    private static PreparedStatement comandoFecharEstadia;
//    private static PreparedStatement comandoBuscaPorId;
    
    public EventoDAO() throws Exception 
    {
        conexao = ConexaoJavaDB.getConnection();
        
        comandoListar = conexao.prepareStatement("SELECT * FROM evento"); 
        comandoCriar = conexao.prepareStatement("INSERT INTO evento (acao, hora, usuario, estadia) VALUES(?,current_timestamp, ?, ?)");
//        comandoExcluirEstadia = conexao.prepareStatement("DELETE FROM estadia WHERE id = ?");
//        comandoSalvar = conexao.prepareStatement("UPDATE estadia SET placa=?, entrada=?, saida=?, valor_pago=? WHERE id=?");
//        comandoFecharEstadia = conexao.prepareStatement("UPDATE estadia SET saida=current_timestamp, valor_pago=? WHERE id=?");
//        comandoBuscaPorId = conexao.prepareStatement("SELECT * FROM estadia WHERE id = ?");
        
    }
    
    //Listar estadias
    public List<Evento> listAll() throws Exception
    {
        List<Evento> eventos = new ArrayList<>();
        ResultSet resultados = comandoListar.executeQuery();
        System.out.println("Comando Executado!");
        while (resultados.next())
        {
            eventos.add(new Evento(resultados.getInt("id"), resultados.getString("acao"), 
                    resultados.getTimestamp("hora"), resultados.getString("usuario"), 
                    resultados.getString("estadia")));
        }
        return eventos;
    }
    
    //Criar evento
    public void criar(Evento evento) throws Exception
    {
        comandoCriar.setString(1, evento.getAcao());
        comandoCriar.setString(2, evento.getUsuario());
        comandoCriar.setString(3, evento.getEstadia());
        comandoCriar.executeUpdate();
    }
 
//    //Excluir estadia
//    public void excluirEstadia(Estadia estadia) throws Exception
//    {
//        comandoExcluirEstadia.setInt(1, estadia.getId());
//        comandoExcluirEstadia.executeUpdate();
//    }
    
//    //Editar estadia
//    public void salvarEstadia(Estadia estadia) throws Exception
//    {
//        java.util.Date dt_entrada = estadia.getEntrada();  
//        java.util.Date dt_saida = estadia.getSaida();  
//        java.sql.Date entrada = new java.sql.Date (dt_entrada.getTime()); 
//        java.sql.Date saida = new java.sql.Date (dt_saida.getTime()); 
//        
////        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
////        Date dt_entrada = sdf.parse(estadia.getEntrada());
//        
//        comandoSalvar.setString(1, estadia.getPlaca());
//        comandoSalvar.setDate(2, entrada);
//        comandoSalvar.setDate(3, saida);
//        comandoSalvar.setDouble(4, estadia.getValor_pago());
//        comandoSalvar.setInt(5, estadia.getId());
//        comandoSalvar.executeUpdate();
//    }
//    
//    //Fechar estadia
//    public void fecharEstadia(Estadia estadia) throws Exception
//    {
//        comandoFecharEstadia.setDouble(1, estadia.getValor_pago());
//        comandoFecharEstadia.setInt(2, estadia.getId());
//        comandoFecharEstadia.executeUpdate();
//    }
//    
//    
//    //Busca estadia pelo id
//    public Estadia buscaPorId(Integer id) throws Exception
//    {
//        Estadia estadia = null;
//        comandoBuscaPorId.setInt(1, id);
//        ResultSet resultado = comandoBuscaPorId.executeQuery();
//        if(resultado.next())
//        {
//            estadia = new Estadia();
//            estadia.setId(resultado.getInt("id"));
//            estadia.setPlaca(resultado.getString("placa"));
//            estadia.setEntrada(resultado.getTimestamp("entrada"));
//            estadia.setSaida(resultado.getTimestamp("saida"));
//            estadia.setValor_pago(resultado.getDouble("valor_pago"));
//        } else
//        {
//            throw new Exception("Não existe estadia com id: " + id);
//        }
//        return estadia;
//    }    
    
    
}
