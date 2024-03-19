package ticketautomaton.behaviour;

import component.Component;
import componentservicebus.ComponentServiceBus;
import documentsystem.behaviour.DocumentSystemImpl;
import documentsystem.behaviour.service.DocumentCommandService;
import messagingsystem.behaviour.MessagingSystemImpl;
import messagingsystem.behaviour.service.MessagingCommandService;
import printingsystem.behaviour.PrintingSystemImpl;
import printingsystem.behaviour.service.PrintingCommandService;

public class TicketAutomaton {
	// Components
	private PrintingCommandService printingSystem;
	private DocumentCommandService documentSystem;
	private MessagingCommandService messagingSystem;
	
	// Connector 
	private ComponentServiceBus componentServiceBus;
	
	public TicketAutomaton() {
		// Create topology (star)
		documentSystem = new DocumentSystemImpl();
		printingSystem = new PrintingSystemImpl();
		messagingSystem = new MessagingSystemImpl();
		
		componentServiceBus = new ComponentServiceBus();
		
		componentServiceBus.registerComponent((Component) documentSystem);
		componentServiceBus.registerComponent((Component) printingSystem);
		componentServiceBus.registerComponent((Component) messagingSystem);
		
		componentServiceBus.configureComponentConnections();
	}
	
	public boolean sellTicket(String start, String end) {
		return componentServiceBus.sellTicket(start, end);
	}

}