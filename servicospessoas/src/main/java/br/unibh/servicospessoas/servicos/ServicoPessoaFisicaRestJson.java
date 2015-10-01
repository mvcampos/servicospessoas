
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

import br.unibh.servicospessoas.entidades.PessoaFisica;
import br.unibh.servicospessoas.persistencia.PessoaFisicaDAO;

// The Java class will be hosted at the URI path "/myresource"
@Path("/pessoafisica")
public class ServicoPessoaFisicaRestJson {

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/listar")
	public List<PessoaFisica> listar() {
		PessoaFisicaDAO dao = new PessoaFisicaDAO();
		return dao.findAll();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public PessoaFisica find(@PathParam("id") int id) {
		PessoaFisicaDAO dao = new PessoaFisicaDAO();
		return dao.find(new Long(id));
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/salvar")
	public PessoaFisica salvar(PessoaFisica p) {
		PessoaFisicaDAO dao = new PessoaFisicaDAO();
		dao.insert(p);
		return dao.findCpf(p.getCpf());
	}

	@PUT
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("{id}")
	public PessoaFisica updatePessoaJSONP(@PathParam("id") int id, PessoaFisica p) {

		PessoaFisicaDAO dao = new PessoaFisicaDAO();

		PessoaFisica pessoa = dao.find(new Long(id));

		pessoa.setNome(p.getNome());
		pessoa.setEndereco(p.getEndereco());
		pessoa.setEmail(p.getEmail());
		pessoa.setTelefone(p.getTelefone());
		pessoa.setCpf(p.getCpf());
		pessoa.setDataNascimento(p.getDataNascimento());
		pessoa.setEmail(p.getEmail());
		pessoa.setSexo(p.getSexo());

		dao.update(pessoa);

		return dao.find(pessoa.getId());

	}

	@DELETE
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/{id}")
	public String deleteJSONP(@PathParam("id") int id) {

		PessoaFisicaDAO dao = new PessoaFisicaDAO();
		PessoaFisica pessoa = dao.find(new Long(id));
		dao.delete(pessoa);
		
		return "Removido com sucesso";
	}

}
