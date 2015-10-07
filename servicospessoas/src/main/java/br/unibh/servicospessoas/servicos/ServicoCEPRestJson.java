
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

import br.unibh.servicospessoas.entidades.CEP;
import br.unibh.servicospessoas.persistencia.CEPDAO;


// The Java class will be hosted at the URI path "/myresource"
@Path("/cep")
public class ServicoCEPRestJson {

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/listar")
	public List<CEP> listar() {
		CEPDAO dao = new CEPDAO();
		return dao.findAll();
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("{endereco}")
	public List<CEP> listar(@PathParam("endereco") String endereco) {
		CEPDAO dao = new CEPDAO();
		return dao.findCep(endereco);
	}

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/buscar/{cep}")
	public CEP find(@PathParam("cep") int cep) {
		CEPDAO dao = new CEPDAO();
		return dao.find(new Long(cep));
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/salvar")
	public CEP salvar(CEP p) {
		CEPDAO dao = new CEPDAO();
		dao.insert(p);
		return dao.find(p.getCep());
	}
	
	@PUT
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("{id}")
	public CEP updateJSONP(@PathParam("id") int id, CEP p) {

		CEPDAO dao = new CEPDAO();

		CEP cep = dao.find(new Long(id));

		cep.setCidade(p.getCidade());
		cep.setEndereco(p.getEndereco());
		

		dao.update(cep);

		return dao.find(cep.getCep());

	}

	@DELETE
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/{id}")
	public String deleteJSONP(@PathParam("id") int id) {

		CEPDAO dao = new CEPDAO();
		CEP cep = dao.find(new Long(id));
		dao.delete(cep);
		
		return "Removido com sucesso";
	}
	

}