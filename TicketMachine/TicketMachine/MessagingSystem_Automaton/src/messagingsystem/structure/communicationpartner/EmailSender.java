package de.leuphana.swa.messagingsystem.structure.communicationpartner;

public class EmailSender implements Sender {
	// sp�ter Role-Object-Pattern
	private String name;
	// TODO statt String sp�ter event. Address
	private String address;
	
	public EmailSender(String senderAddress) {
		this.address = senderAddress;
	}

}
