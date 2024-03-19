package messagingsystem.structure.message;

import messagingsystem.structure.communicationpartner.Receiver;
import messagingsystem.structure.communicationpartner.Sender;

public class MessageHeader {
	private Sender sender;
	private Receiver receiver;
	
	public MessageHeader(Sender sender, Receiver receiver) {
		this.receiver = receiver;
		this.sender = sender;
	}
}
