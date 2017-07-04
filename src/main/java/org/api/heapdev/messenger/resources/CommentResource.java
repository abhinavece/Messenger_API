package org.api.heapdev.messenger.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CommentResource {
	
	@GET
	public String getData(){
		return "test succeeded";
	}
	
	@GET
	@Path("/{commentsId}")
	public String getComment(@PathParam("messageId") long mid, @PathParam("commentsId") long id){
		return "This is comment with ID::: "+id+ " and messageID: "+mid;
	}
	
}
