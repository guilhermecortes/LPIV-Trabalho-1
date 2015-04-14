
package br.cesjf.lpaw.db;

import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;

public class ConexaoJavaDB 
{
    private static Connection conexao = null; //retorna SOMENTE UMA conexão
    //Classe de SOMENTE de retorno, não resolve nenhum problema, por isso usa o throws para disparar excessões
    public static Connection getConnection() throws ClassNotFoundException, SQLException
    {
        if(conexao == null)
        {
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        System.out.println("Driver carregado com sucesso!"); 
  
        conexao = java.sql.DriverManager.getConnection("jdbc:derby://localhost:1527/db_estacionamento", "fulano", "senha123");
        System.out.println("Conexão bem sucedida com o SGBD!");    
        }    
        return conexao;
        
    }
}
