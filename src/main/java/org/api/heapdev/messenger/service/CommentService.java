package org.api.heapdev.messenger.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.api.heapdev.messenger.database.DatabaseClass;
import org.api.heapdev.messenger.model.Comment;
import org.api.heapdev.messenger.model.ErrorMessage;
import org.api.heapdev.messenger.model.Message;

public class CommentService {

	private static Map<Long, Message> messages = DatabaseClass.getMessages();

	public List<Comment> getAllComments(long messageId) {
		Map<Long, Comment> commentsMap = messages.get(messageId).getComments();
		return new ArrayList<Comment>(commentsMap.values());
	}

	public Comment getComment(long messageId, long commentId) {
		ErrorMessage errorMessage = new ErrorMessage("Data Not Found", 404, "http://www.heapdev.com/error/404/description");
		Response response = Response.status(Status.NOT_FOUND).entity(errorMessage).type(MediaType.APPLICATION_JSON).build();

		Message message = messages.get(messageId);
		if (message == null) {
			throw new WebApplicationException(response);
		}

		Map<Long, Comment> comments = messages.get(messageId).getComments();
		Comment comment = comments.get(commentId);
		if (comment == null) {
			throw new WebApplicationException(response);
		}
		
		return comment;
	}

	public Comment addComment(long messageId, Comment comment) {
		comment.setId(messages.get(messageId).getComments().size() + 1);
		messages.get(messageId).getComments().put(comment.getId(), comment);
		return comment;
	}

	public Comment updateComment(long messageId, Comment comment) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		if (comment.getId() <= 0) {
			return null;
		}
		comments.put(comment.getId(), comment);
		return comment;
	}

	public Comment deleteComment(long messageId, long commentId) {
		return messages.get(messageId).getComments().remove(commentId);
	}

}
