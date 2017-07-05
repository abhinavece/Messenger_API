package org.api.heapdev.messenger.resources;

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

import org.api.heapdev.messenger.model.Comment;
import org.api.heapdev.messenger.service.CommentService;

@Path("/")
public class CommentResource {

//	@GET
//	@Path("/{commentsId}")
//	public String getComment(@PathParam("messageId") long mid, @PathParam("commentsId") long id) {
//		return "This is comment with ID::: " + id + " and messageID: " + mid;
//	}

	static CommentService commentService = new CommentService();

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Comment> getAllComments(@PathParam("messageId") long messageId) {
		return commentService.getAllComments(messageId);
	}
	
	@GET
	@PathParam("/{commentId}")
	public Comment getComment(@PathParam("messageId") long messageId, 
								@PathParam("commentId") long commentId){
		return commentService.getComment(messageId, commentId);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Comment addComment(@PathParam("messageId") long messageId, Comment comment){
		return commentService.addComment(messageId, comment);
	}
	
	@PUT
	@PathParam("/{commentId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Comment updateComment(@PathParam("messageId") long messageId, 
									@PathParam("commentId") long commentId,
									Comment comment){
		comment.setId(commentId);
		return commentService.updateComment(messageId, comment);
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Comment deleteComment(@PathParam("messageId") long messageId, 
									@PathParam("commentId") long commentId){
		return commentService.deleteComment(messageId, commentId);
	}

}
