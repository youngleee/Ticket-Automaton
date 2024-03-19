package messagingsystem.structure.message;

import messagingsystem.structure.MessageType;
import messagingsystem.structure.communicationpartner.EmailReceiver;
import messagingsystem.structure.communicationpartner.EmailSender;
import messagingsystem.structure.communicationpartner.Receiver;
import messagingsystem.structure.communicationpartner.Sender;
import messagingsystem.structure.content.Content;
import messagingsystem.structure.content.EmailContent;

public class MessageBuilder {

	private MessageBuilder() {
	}
	
	public static Message createMessage(String receiver, String sender, String contentAsString, MessageType messageType) {
		Message message;
		
		MessageHeader messageHeader = createMessageHeader(sender, receiver, messageType);
		MessageBody messageBody = createMessageBody(contentAsString, messageType);
		
		
		switch (messageType) {
		case EMAIL: {
			message = new EmailMessage(messageHeader, messageBody);
			break;
		}
		case SMS: {
			message = new SMSMessage(messageHeader, messageBody);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + messageType);
		}
		
		return message;
	}
	
	private static MessageHeader createMessageHeader(String senderAddress, String receiverAddress, MessageType messageType) {
		Receiver receiver = null;
		Sender sender = null;
		
		// TODO Factory Method Pattern !!!
		switch (messageType) {
		case EMAIL: {
			receiver = new EmailReceiver(receiverAddress);
			sender = new EmailSender(senderAddress);
			break;
		}
		case SMS: {
			// TODO
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + messageType);
		}
		
		return new MessageHeader(sender, receiver);
	}
	
	private static MessageBody createMessageBody(String contentAsString, MessageType messageType) {
		Content content = null;
		
		// TODO Factory Method Pattern !!!
		switch (messageType) {
		case EMAIL: {
			content = new EmailContent(contentAsString);
			break;
		}
		case SMS: {
			// TODO
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + messageType);
		}
		return new MessageBody(content);
	}
}