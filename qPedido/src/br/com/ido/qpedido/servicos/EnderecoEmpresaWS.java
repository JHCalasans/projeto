package br.com.ido.qpedido.servicos;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.ido.qpedido.bo.EmpresaBO;
import br.com.ido.qpedido.entity.qpedido.EnderecoEmpresa;

@Path("/enderecoEmpresa")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EnderecoEmpresaWS {
	

    @GET
  	@Path("/{sigla}")
  	public Response obterPorEstado(@PathParam("sigla") String sigla) {
  		try {
  			List<EnderecoEmpresa> resultado = EmpresaBO.getInstance().obterEnderecoEmpresaPorSigla(sigla);
  			
  			GenericEntity<List<EnderecoEmpresa>> entidade = new GenericEntity<List<EnderecoEmpresa>>(resultado) {};
  			return Response.status(Status.OK).entity(entidade).build();

  		} catch (Exception e) {
  			e.printStackTrace();
  			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Falha ao buscar usuário").build();
  		}
  		

  	}

}
