package messagingsystem.structure.messagingprotocol;

import messagingsystem.structure.message.Message;

public interface MessagingProtocol {
	boolean open();
	boolean transfer(Message message);
	boolean close();
}
