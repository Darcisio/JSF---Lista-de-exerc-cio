package br.edu.faculdadedelta.projetovendajsf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.projetovendajsf.modelo.Empresa;
import br.edu.faculdadedelta.projetovendajsf.util.Conexao;

public class EmpresaDAO {

	// C R U D
	
	public void incluir(Empresa venda) throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBanco();
		String sql = "INSERT INTO vendas (nome, cnpj, endereco, data_cadastro) "
				+ " VALUES (?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, venda.getNome().trim());
		ps.setString(1, venda.getCnpj().trim());
		ps.setString(3, venda.getEndereco());
		ps.setDate(4, new java.sql.Date(venda.getDataCadastro().getTime()));
		
		ps.executeUpdate();
		
		ps.close();
		conn.close();
	}
	
	public void alterar(Empresa venda) throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBanco();
		String sql = "UPDATE vendas SET produto_venda = ?, "
				+ " nome = ?, "
				+ " cnpj = ?, "
				+ " endereco = ? "
				+ " data_cadastro  = ? "
				+ " WHERE id_venda = ?";
		
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, venda.getNome().trim());
		ps.setString(1, venda.getCnpj().trim());
		ps.setString(3, venda.getEndereco());
		ps.setDate(4, new java.sql.Date(venda.getDataCadastro().getTime()));
		ps.setLong(5, venda.getId());
		
		ps.executeUpdate();
		
		ps.close();
		conn.close();
	}
	
	public void excluir(Empresa venda) throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBanco();
		String sql = "DELETE FROM vendas WHERE id_venda = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, venda.getId());
		
		ps.executeUpdate();
		
		ps.close();
		conn.close();
	}
	
	public List<Empresa> listar() throws ClassNotFoundException, SQLException {
		Conexao conexao = new Conexao();
		Connection conn = conexao.conectarNoBanco();
		String sql = "SELECT id_venda, nome, cnpj, "
				+ " endereco, data_cadastro FROM vendas";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Empresa> listaRetorno = new ArrayList<Empresa>();
 		
		while (rs.next()) {
			Empresa empresa = new Empresa();
			empresa.setId(rs.getLong("id_venda"));
			empresa.setNome(rs.getString("nome").trim());
			empresa.setCnpj(rs.getString("cnpj"));
			empresa.setEndereco(rs.getString("endereco"));
			empresa.setDataCadastro(rs.getDate("data_cadastro"));
			listaRetorno.add(empresa);
		}
		rs.close();
		ps.close();
		conn.close();
		
		return listaRetorno;
	}
}
