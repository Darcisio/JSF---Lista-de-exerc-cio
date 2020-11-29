package br.edu.faculdadedelta.projetovendajsf.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.faculdadedelta.projetovendajsf.dao.AlunoDAO;
import br.edu.faculdadedelta.projetovendajsf.modelo.Aluno;

@ManagedBean
@SessionScoped
public class AlunoController {

	private Aluno aluno = new Aluno();
	private AlunoDAO dao = new AlunoDAO();
	
	public Aluno getVenda() {
		return aluno;
	}
	
	public void setVenda(Aluno aluno) {
		this.aluno = aluno;
	}
	
	
	public void limparCampos() {
		aluno = new Aluno();
	}
	
	public String salvar() {
		try {
			if (aluno.getId() == null) {
				// incluir
				dao.incluir(aluno);
				FacesMessage mensagem = new FacesMessage("Inclusão realizada com sucesso!");
				FacesContext.getCurrentInstance().addMessage(null, mensagem);
				limparCampos();
			} else {
				// alterar
				dao.alterar(aluno);
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
	
	public List<Aluno> getLista() {
		List<Aluno> listaRetorno = new ArrayList<Aluno>();
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
			dao.excluir(aluno);
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
