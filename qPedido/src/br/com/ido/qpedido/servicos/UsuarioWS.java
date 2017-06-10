package br.com.ido.qpedido.servicos;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.ido.qpedido.bo.UsuarioBO;
import br.com.ido.qpedido.entity.qpedido.Usuario;




@Path("/usuario")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioWS {
	

    @GET
  	@Path("/{codUsuario}")
  	public Response obterPorCodigo(@PathParam("codUsuario") Integer codigo) {
  		try {
  			Usuario usuario = UsuarioBO.getInstance().obterPorCodigo(codigo);
  			return Response.status(Status.OK).entity(usuario).build();

  		} catch (Exception e) {
  			e.printStackTrace();
  		}
  		return Response.status(Status.INTERNAL_SERVER_ERROR).build();

  	}


}
