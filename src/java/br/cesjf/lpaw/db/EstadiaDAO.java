/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cesjf.lpaw.db;

import br.cesjf.lpaw.classes.Estadia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author guilhermecortes
 */
public class EstadiaDAO 
{
    private static Connection conexao = null;
    private static PreparedStatement comandoListarEstadia;
    private static PreparedStatement comandoCriarEstadia;
    private static PreparedStatement comandoExcluirEstadia;
    private static PreparedStatement comandoSalvar;
    private static PreparedStatement comandoFecharEstadia;
    private static PreparedStatement comandoBuscaPorId;
    
    public EstadiaDAO() throws Exception 
    {
        conexao = ConexaoJavaDB.getConnection();
        
        comandoListarEstadia = conexao.prepareStatement("SELECT * FROM estadia"); 
        comandoCriarEstadia = conexao.prepareStatement("INSERT INTO estadia (placa, entrada) VALUES(?,current_timestamp)",PreparedStatement.RETURN_GENERATED_KEYS);
        comandoExcluirEstadia = conexao.prepareStatement("DELETE FROM estadia WHERE id = ?");
        comandoSalvar = conexao.prepareStatement("UPDATE estadia SET placa=?, entrada=?, saida=?, valor_pago=? WHERE id=?");
        comandoFecharEstadia = conexao.prepareStatement("UPDATE estadia SET saida=current_timestamp, valor_pago=? WHERE id=?");
        comandoBuscaPorId = conexao.prepareStatement("SELECT * FROM estadia WHERE id = ?");
        
    }
    
    //Listar estadias
    public List<Estadia> listAll() throws Exception
    {
        List<Estadia> estadias = new ArrayList<>();
        ResultSet resultados = comandoListarEstadia.executeQuery();
        System.out.println("Comando Executado!");
        while (resultados.next())
        {
            estadias.add(new Estadia(resultados.getInt("id"), resultados.getString("placa"), 
                    resultados.getTimestamp("entrada"), resultados.getTimestamp("saida"), 
                    resultados.getDouble("valor_pago")));
        }
        return estadias;
    }
    
    //Criar estadia
    public void criarEstadia(Estadia novaEstadia) throws Exception
    {
        comandoCriarEstadia.setString(1, novaEstadia.getPlaca());
        comandoCriarEstadia.executeUpdate();
        
        ResultSet res = comandoCriarEstadia.getGeneratedKeys();
        if(res.next()){
            novaEstadia.setId(res.getInt(1));
        } else{
            throw new Exception("Erro ao retornar id da estadia");
        }
            
    }
 
    //Excluir estadia
    public void excluirEstadia(Estadia estadia) throws Exception
    {
        comandoExcluirEstadia.setInt(1, estadia.getId());
        comandoExcluirEstadia.executeUpdate();
    }
    
    //Editar estadia
    public void salvarEstadia(Estadia estadia) throws Exception
    {
        java.util.Date dt_entrada = estadia.getEntrada();  
        java.util.Date dt_saida = estadia.getSaida();  
        java.sql.Timestamp entrada = new java.sql.Timestamp (dt_entrada.getTime()); 
        java.sql.Timestamp saida = new java.sql.Timestamp (dt_saida.getTime()); 
        
        comandoSalvar.setString(1, estadia.getPlaca());
        comandoSalvar.setTimestamp(2, entrada);
        comandoSalvar.setTimestamp(3, saida);
        comandoSalvar.setDouble(4, estadia.getValor_pago());
        comandoSalvar.setInt(5, estadia.getId());
        comandoSalvar.executeUpdate();
    }
    
    //Fechar estadia
    public void fecharEstadia(Estadia estadia) throws Exception
    {
        comandoFecharEstadia.setDouble(1, estadia.getValor_pago());
        comandoFecharEstadia.setInt(2, estadia.getId());
        comandoFecharEstadia.executeUpdate();
    }
    
    
    //Busca estadia pelo id
    public Estadia buscaPorId(Integer id) throws Exception
    {
        Estadia estadia = null;
        comandoBuscaPorId.setInt(1, id);
        ResultSet resultado = comandoBuscaPorId.executeQuery();
        if(resultado.next())
        {
            estadia = new Estadia();
            estadia.setId(resultado.getInt("id"));
            estadia.setPlaca(resultado.getString("placa"));
            estadia.setEntrada(resultado.getTimestamp("entrada"));
            estadia.setSaida(resultado.getTimestamp("saida"));
            estadia.setValor_pago(resultado.getDouble("valor_pago"));
        } else
        {
            throw new Exception("Não existe estadia com id: " + id);
        }
        return estadia;
    }    
    
    
}
