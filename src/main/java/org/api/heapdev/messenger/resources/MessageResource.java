package org.api.heapdev.messenger.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.api.heapdev.messenger.model.Message;
import org.api.heapdev.messenger.service.MessageService;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(value={MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
public class MessageResource {

	MessageService messageService = new MessageService();

	@GET
	public List<Message> getMessages(@BeanParam MessageFilterBean filterBean) {
		if (filterBean.getYear() > 0) {
			return messageService.getAllMessagesOfTheYear(filterBean.getYear());
		}
		if (filterBean.getStart() > 0 && filterBean.getSize() > 0) {
			return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
		}
		
		return messageService.getAllMesssages();
	}

	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") Long id, @Context UriInfo uriInfo) {
		Message message = messageService.getMessage(id);
		
//		String uri = uriInfo.getAbsolutePathBuilder().build().toString();		
		String uri = getUriForSelf(uriInfo, message);
		String profileuri = getUriForAuthor(uriInfo, message);
		String commentUri = getUriForComments(uriInfo, message);
		
		message.addLink(uri, "self");
		message.addLink(profileuri, "author");
		message.addLink(commentUri, "comments");
		return message;
	}

	private String getUriForComments(UriInfo uriInfo, Message message) {
		String commentUri = uriInfo
									.getBaseUriBuilder()
									.path(MessageResource.class)
									.path(MessageResource.class, "getCommentResource")
									.path(CommentResource.class)
									.resolveTemplate("messageId", message.getId())
									.build()
									.toString();
		return commentUri;	
	}

	private String getUriForAuthor(UriInfo uriInfo, Message message) {
		String profileuri = uriInfo
									.getBaseUriBuilder()
									.path(ProfileResource.class)
									.path(message.getAuthor())
									.build()
									.toString();
		return profileuri;
	}


	private String getUriForSelf(UriInfo uriInfo, Message message) {
		String uri = uriInfo
							.getBaseUriBuilder()
							.path(MessageResource.class)
							.path(Long.toString(message.getId()))
							.build()
							.toString();
		return uri;
	}

	/**
	 * We can even use uri as: something/{id1}/name/{id2}
	 * 
	 * @PathParam("id1") int id1, @PathParam("id2") int id2
	 * 
	 */

	@POST
	public Response addMessage(Message message, @Context UriInfo uriInfo) {
		Message newMessage = messageService.addMessage(message);
		String newId = String.valueOf(newMessage.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
		return Response.created(uri).entity(newMessage).build();
	}

	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long id, Message message) {
		message.setId(id);
		return messageService.updateMessage(message);
	}

	@DELETE
	@Path("/{messageId}")
	public void deleteMessage(@PathParam("messageId") long id) {
		messageService.deleteMessage(id);
	}

	@Path("/{messageId}/comments")
	public CommentResource getCommentResource() {
		return new CommentResource();
	}
}
