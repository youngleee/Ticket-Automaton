package messagingsystem.structure.messagingfactory;

import messagingsystem.structure.MessageType;
import messagingsystem.structure.message.Message;
import messagingsystem.structure.message.MessageBuilder;
import messagingsystem.structure.messagingprotocol.MessagingProtocol;
import messagingsystem.structure.messagingprotocol.MessagingProtocolFactory;

public class EmailMessagingFactory extends AbstractMessagingFactory {

	@Override
	public MessagingProtocol createMessagingProtocol() {
		return MessagingProtocolFactory.createMessagingProtocol(MessageType.EMAIL);
	}

	@Override
	public Message createMessage(String sender, String receiver, String contentAsString) {
		return MessageBuilder.createMessage(receiver, sender, contentAsString, MessageType.EMAIL);
	}

}
