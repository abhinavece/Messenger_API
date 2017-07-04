package org.api.heapdev.messenger.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/injectdemo")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class InjectDemoResources {
	
	@GET
	@Path("annotations")
	public String getParamsUsingAnnotations(@MatrixParam("params") String matrixParam,
											@HeaderParam("authSessionId") String header,
											@CookieParam("name") String cookie){
		return "matrix param: "+matrixParam + " Header: "+ header+ " cookie: "+cookie;
	}
	
	@GET
	@Path("context")
	public String getParamsUsingContext(@Context UriInfo uriInfo){
		String path = uriInfo.getAbsolutePath().toString();
		return path;
	}
}