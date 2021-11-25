package aplicativo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class materiaisDAO {
	public bdgetset materiais;
	public BD bd;
	private PreparedStatement statement;
	private ResultSet resultSet;
	private String men, sql;
	public static final byte INCLUSAO = 1;
	public static final byte ALTERACAO = 2;
	public static final byte EXCLUSAO = 3;
	
	public materiaisDAO() {
		bd = new BD();
		materiais = new bdgetset();
	}
	
	public boolean localizar() {
		sql = "select * from login where email =?";
		try {
			statement = bd.c.prepareStatement(sql);
			statement.setString(1, materiais.getemail());
			resultSet = statement.executeQuery();
			resultSet.next();
			materiais.setemail(resultSet.getString(1));
			materiais.setsenha(resultSet.getString(2));
			System.out.println("Selecionado com sucesso!");
			return true;
		}catch (SQLException erro) {
			return false;
		}
	}
	
	public String atualizar(int operacao) {
		men = "Operação realizada com sucesso ";
		try {
			if(operacao == INCLUSAO) {
				sql = "insert into materias values (?,?)";
				statement = bd.c.prepareStatement(sql);
				statement.setString(1, materiais.getemail());
				statement.setString(2, materiais.getsenha());
				
			} else if(operacao == ALTERACAO) {
				sql = "update materais set  senha = ? where email =?";
				statement = bd.c.prepareStatement(sql);
				statement.setString(2, materiais.getemail());
				statement.setString(1, materiais.getsenha());
				
			} 
			if (statement.executeUpdate() == 0) {
				men = "Falha na operação";
			}
			
		} catch(SQLException erro) {
			men = "Falha na operação " + erro.toString();
		}
		return men;
	}
}

