package org.api.heapdev.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.api.heapdev.messenger.exception.DataNotFoundException;
import org.api.heapdev.messenger.model.Message;

public class MessageService {

	private static Map<Long, Message> messages = new HashMap<>();

	public MessageService() {
		messages.put(1L, new Message(1L, "First Message", "abhinavece"));
		messages.put(2L, new Message(2L, "Second Message", "abhinavece"));
		messages.put(3L, new Message(3L, "Third Message", "sanchayspidy"));
		messages.put(4L, new Message(4L, "Fourth Message", "abhinavece"));
		messages.put(5L, new Message(5L, "Fifth Message", "sanchayspidy"));
		messages.put(6L, new Message(6L, "Sixth Message", "vivek720"));
		messages.put(7L, new Message(7L, "Seventh Message", "vivek720"));
	}

	public List<Message> getAllMesssages() {
		return new ArrayList<Message>(messages.values());
	}

	public Message getMessage(Long id) {
		Message message = messages.get(id);
		if (message == null) {
			throw new DataNotFoundException("Message is not found with this id: " + id);
		}
		return message;
	}

	public List<Message> getAllMessagesOfTheYear(int year) {
		List<Message> messageForYear = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		for (Message message : messages.values()) {
			cal.setTime(message.getCreated());
			if (cal.get(Calendar.YEAR) == year) {
				messageForYear.add(message);
			}
		}
		return messageForYear;
	}

	public List<Message> getAllMessagesPaginated(int start, int size) {
		ArrayList<Message> list = new ArrayList<>(messages.values());
		if (start + size > list.size())
			return new ArrayList<>();
		return list.subList(start, start + size);
	}

	public Message addMessage(Message message) {
		message.setId(messages.size() + 1);
		messages.put(message.getId(), message);
		return message;
	}

	public Message updateMessage(Message message) {
		if (message.getId() <= 0) {
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}

	public Message deleteMessage(long id) {
		return messages.remove(id);
	}

}