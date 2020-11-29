package br.edu.faculdadedelta.projetovendajsf.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.faculdadedelta.projetovendajsf.dao.EmpresaDAO;
import br.edu.faculdadedelta.projetovendajsf.modelo.Empresa;

@ManagedBean
@SessionScoped
public class EmpresaController {

	private Empresa empresa = new Empresa();
	private EmpresaDAO dao = new EmpresaDAO();
	
	public Empresa getVenda() {
		return empresa;
	}
	
	public void setVenda(Empresa empresa) {
		this.empresa = empresa;
	}
	
	
	public void limparCampos() {
		empresa = new Empresa();
	}
	
	public String salvar() {
		try {
			if (empresa.getId() == null) {
				// incluir
				dao.incluir(empresa);
				FacesMessage mensagem = new FacesMessage("Inclusão realizada com sucesso!");
				FacesContext.getCurrentInstance().addMessage(null, mensagem);
				limparCampos();
			} else {
				// alterar
				dao.alterar(empresa);
				FacesMessage mensagem = new FacesMessage("Alteração realizada com sucesso!");
				FacesContext.getCurrentInstance().addMessage(null, mensagem);
				limparCampos();
			}
		} catch (ClassNotFoundException | SQLException e) {
			FacesMessage mensagem = new FacesMessage("Erro ao realizar a operação. "
					+ "Tente novamente mais tarde. " + e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, mensagem);
			e.printStackTrace();
		}
		return "cadastroLivros.xhtml";
	}
	
	public List<Empresa> getLista() {
		List<Empresa> listaRetorno = new ArrayList<Empresa>();
		try {
			listaRetorno = dao.listar();
		} catch (ClassNotFoundException | SQLException e) {
			FacesMessage mensagem = new FacesMessage("Erro ao realizar a operação. "
					+ "Tente novamente mais tarde. " + e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, mensagem);
			e.printStackTrace();
		}
		return listaRetorno;
	}
	
	public String editar() {
		return "cadastroLivros.xhtml";
	}
	
	public String excluir() {
		try {
			dao.excluir(empresa);
			FacesMessage mensagem = new FacesMessage("Exclusão realizada com sucesso!");
			FacesContext.getCurrentInstance().addMessage(null, mensagem);
			limparCampos();
		} catch (ClassNotFoundException | SQLException e) {
			FacesMessage mensagem = new FacesMessage("Erro ao realizar a operação. "
					+ "Tente novamente mais tarde. " + e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, mensagem);
			e.printStackTrace();
		}
		return "listaLivros.xhtml";
	}
}
