package br.unibh.servicospessoas.servicos;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.unibh.servicospessoas.entidades.PessoaJuridica;
import br.unibh.servicospessoas.persistencia.PessoaJuridicaDAO;


@Path("/pessoajuridica")
public class ServicoPessoaJuridicaRestJson {

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/listar")
	public List<PessoaJuridica> listar() {
		PessoaJuridicaDAO dao = new PessoaJuridicaDAO();
		return dao.findAll();
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public PessoaJuridica find(@PathParam("id") int id) {
		PessoaJuridicaDAO dao = new PessoaJuridicaDAO();
		return dao.find(new Long(id));
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/salvar")
	public PessoaJuridica salvar(PessoaJuridica p) {
		PessoaJuridicaDAO dao = new PessoaJuridicaDAO();
		dao.insert(p);
		return dao.findCnpj(p.getCnpj());
	}

	@PUT
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("{id}")
	public PessoaJuridica updatePessoaJSONP(@PathParam("id") int id, PessoaJuridica p) {

		PessoaJuridicaDAO dao = new PessoaJuridicaDAO();

		PessoaJuridica pessoa = dao.find(new Long(id));

		pessoa.setNome(p.getNome());
		pessoa.setEndereco(p.getEndereco());
		pessoa.setSite(p.getSite());
		pessoa.setTelefone(p.getTelefone());
		pessoa.setCnpj(p.getCnpj());
		pessoa.setDataConstituicao(p.getDataConstituicao());
		dao.update(pessoa);

		return dao.find(pessoa.getId());

	}

	@DELETE
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/{id}")
	public String deleteJSONP(@PathParam("id") int id) {

		PessoaJuridicaDAO dao = new PessoaJuridicaDAO();
		PessoaJuridica pessoa = dao.find(new Long(id));
		dao.delete(pessoa);
		
		return "Removido com sucesso";
	}

}
