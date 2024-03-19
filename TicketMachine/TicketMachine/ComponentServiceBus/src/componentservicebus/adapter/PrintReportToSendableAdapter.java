package componentservicebus.adapter;

import messagingsystem.behaviour.service.MessagingCommandService;
import messagingsystem.behaviour.service.Sendable;
import messagingsystem.structure.MessageType;
import printingsystem.behaviour.service.PrintReport;
import printingsystem.behaviour.service.event.PrintableEvent;
import printingsystem.behaviour.service.event.PrintableEventListener;
import printingsystem.behaviour.service.event.PrintableEventService;

public class PrintReportToSendableAdapter implements PrintableEventListener {

	private MessagingCommandService messagingCommandService;

	public PrintReportToSendableAdapter(PrintableEventService printableEventService, MessagingCommandService messagingCommandService) {
		printableEventService.addPrintableEventListener(this);
		this.messagingCommandService = messagingCommandService;
	}

	@Override
	public void onPrintableExcuted(PrintableEvent printableEvent) {
		PrintReport printReport = printableEvent.getPrintReport();
		
		Sendable sendable = new Sendable() {
			
			@Override
			public String getSender() {
				return "TicketAutomaton1@train.com";
			}
			
			@Override
			public String getReceiver() {
				return "TrainCentral@train.com";
			}
			
			@Override
			public MessageType getMessageType() {
				return MessageType.EMAIL;
			}
			
			@Override
			public String getContent() {
				return "Print date: " + printReport.getPrintDate() + " Print price: " + printReport.getPrice();
			}
		};
		
		messagingCommandService.sendMessage(sendable);
	}

}
