package messagingsystem.structure.messagingfactory;

import messagingsystem.structure.MessageType;
import messagingsystem.structure.message.Message;
import messagingsystem.structure.messagingprotocol.MessagingProtocol;

public abstract class AbstractMessagingFactory {
	public abstract MessagingProtocol createMessagingProtocol();
	public abstract Message createMessage(String sender, String receiver, String contentAsString);

	public static AbstractMessagingFactory getFactory(MessageType messageType) {
		AbstractMessagingFactory abstractMessagingFactory;
		
		switch (messageType) {
		case EMAIL: {
			abstractMessagingFactory = new EmailMessagingFactory();
			break;
		}
		case SMS: {
			abstractMessagingFactory = new SMSMessagingFactory();
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + messageType);
		}
		
		return abstractMessagingFactory;
	}
}
