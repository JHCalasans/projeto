package br.com.ido.qpedido.servicos;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.ido.qpedido.bo.MesaEnderecoEmpresaBO;
import br.com.ido.qpedido.entity.qpedido.MesaEnderecoEmpresa;
import br.com.minhaLib.excecao.excecaonegocio.ExcecaoNegocio;

@Path("/mesaEnderecoEmpresa")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MesaEndderecoEmpresaWS {
	
	@GET
	@Path("/lerQRCode")
	public Response lerQRCode(@QueryParam("codMesa") Long codMesa, @QueryParam("codUsuario") Integer codUsuario) {
		try {
			MesaEnderecoEmpresa resultado = MesaEnderecoEmpresaBO.getInstance().leituraQRCode(codMesa, codUsuario);
			return Response.status(Status.OK).entity(resultado).build();
		} catch (ExcecaoNegocio e){
			e.printStackTrace();
			return Response.status(Status.FORBIDDEN).type(MediaType.TEXT_PLAIN).entity(e.getMessage()).build();
		}catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).type(MediaType.TEXT_PLAIN).entity("Falha ao ler QRCode").build();
		}
	}

}
