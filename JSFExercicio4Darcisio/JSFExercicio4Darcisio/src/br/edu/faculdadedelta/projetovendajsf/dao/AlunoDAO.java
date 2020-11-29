package br.edu.faculdadedelta.projetovendajsf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.projetovendajsf.modelo.Aluno;
import br.edu.faculdadedelta.projetovendajsf.util.Conexao;

public class AlunoDAO {

	// C R U D
	
	public void incluir(Aluno venda) throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBanco();
		String sql = "INSERT INTO vendas (nome, grau, idade, data_cadastro) "
				+ " VALUES (?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, venda.getNome().trim());
		ps.setString(1, venda.getGrau().trim());
		ps.setDouble(3, venda.getIdade());
		ps.setDate(4, new java.sql.Date(venda.getDataCadastro().getTime()));
		
		ps.executeUpdate();
		
		ps.close();
		conn.close();
	}
	
	public void alterar(Aluno venda) throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBanco();
		String sql = "UPDATE vendas SET produto_venda = ?, "
				+ " nome = ?, "
				+ " grau = ?, "
				+ " idade  = ? "
				+ " data_cadastro  = ? "
				+ " WHERE id_venda = ?";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, venda.getNome().trim());
		ps.setString(1, venda.getGrau().trim());
		ps.setDouble(3, venda.getIdade());
		ps.setDate(4, new java.sql.Date(venda.getDataCadastro().getTime()));
		ps.setLong(5, venda.getId());
		
		ps.executeUpdate();
		
		ps.close();
		conn.close();
	}
	
	public void excluir(Aluno venda) throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBanco();
		String sql = "DELETE FROM vendas WHERE id_venda = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, venda.getId());
		
		ps.executeUpdate();
		
		ps.close();
		conn.close();
	}
	
	public List<Aluno> listar() throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBanco();
		String sql = "SELECT id_venda, nome, grau, "
				+ " idade, data_cadastro FROM vendas";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Aluno> listaRetorno = new ArrayList<Aluno>();
 		
		while (rs.next()) {
			Aluno aluno = new Aluno();
			aluno.setId(rs.getLong("id_venda"));
			aluno.setNome(rs.getString("nome").trim());
			aluno.setGrau(rs.getString("grau"));
			aluno.setIdade(rs.getDouble("idade"));
			aluno.setDataCadastro(rs.getDate("data_cadastro"));
			listaRetorno.add(aluno);
		}
		rs.close();
		ps.close();
		conn.close();
		
		return listaRetorno;
	}
}
