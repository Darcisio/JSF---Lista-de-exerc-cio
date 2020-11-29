package br.edu.faculdadedelta.projetovendajsf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.projetovendajsf.modelo.Venda;
import br.edu.faculdadedelta.projetovendajsf.util.Conexao;

public class VendaDAO {

	// C R U D
	
	public void incluir(Venda venda) throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBanco();
		String sql = "INSERT INTO vendas (nome_livro, editora, valor, data_cadastro) "
				+ " VALUES (?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, venda.getNome().trim());
		ps.setString(1, venda.getEditora().trim());
		ps.setDouble(3, venda.getValor());
		ps.setDate(4, new java.sql.Date(venda.getDataCadastro().getTime()));
		
		ps.executeUpdate();
		
		ps.close();
		conn.close();
	}
	
	public void alterar(Venda venda) throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBanco();
		String sql = "UPDATE vendas SET produto_venda = ?, "
				+ " nome_livro = ?, "
				+ " editora = ?, "
				+ " valor  = ? "
				+ " data_cadastro  = ? "
				+ " WHERE id_venda = ?";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, venda.getNome().trim());
		ps.setString(1, venda.getEditora().trim());
		ps.setDouble(3, venda.getValor());
		ps.setDate(4, new java.sql.Date(venda.getDataCadastro().getTime()));
		ps.setLong(5, venda.getId());
		
		ps.executeUpdate();
		
		ps.close();
		conn.close();
	}
	
	public void excluir(Venda venda) throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBanco();
		String sql = "DELETE FROM vendas WHERE id_venda = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, venda.getId());
		
		ps.executeUpdate();
		
		ps.close();
		conn.close();
	}
	
	public List<Venda> listar() throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBanco();
		String sql = "SELECT id_venda, nome_livro, editora, "
				+ " valor, data_cadastro FROM vendas";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Venda> listaRetorno = new ArrayList<Venda>();
 		
		while (rs.next()) {
			Venda venda = new Venda();
			venda.setId(rs.getLong("id_venda"));
			venda.setNome(rs.getString("nome").trim());
			venda.setEditora(rs.getString("editora"));
			venda.setValor(rs.getDouble("valor"));
			venda.setDataCadastro(rs.getDate("data_cadastro"));
			listaRetorno.add(venda);
		}
		rs.close();
		ps.close();
		conn.close();
		
		return listaRetorno;
	}
}
