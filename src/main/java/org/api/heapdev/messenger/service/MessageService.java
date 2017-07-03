package org.api.heapdev.messenger.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.api.heapdev.messenger.model.Message;

public class MessageService {	
	
	private static Map<Long, Message> messages = new HashMap<>();
	
	public List<Message> getAllMesssages(){
		return new ArrayList<Message>(messages.values());
	}
	
	public Message getMessage(Long id){
		return messages.get(id);
	}
	
	public void addMessage(Message message){
		
	}

}