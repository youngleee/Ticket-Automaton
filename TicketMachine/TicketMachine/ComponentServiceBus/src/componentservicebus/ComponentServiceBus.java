package componentservicebus;

import java.util.HashMap;
import java.util.Map;

import component.Component;
import componentservicebus.adapter.ManageableToPrintableAdapter;
import componentservicebus.adapter.PrintReportToSendableAdapter;
import documentsystem.behaviour.service.DocumentCommandService;
import documentsystem.behaviour.service.event.ManageableEventService;
import messagingsystem.behaviour.service.MessagingCommandService;
import printingsystem.behaviour.service.PrintingCommandService;
import printingsystem.behaviour.service.event.PrintableEventService;

public class ComponentServiceBus {

	private Map<String, Component> componentMap;
	private Map<String, String> commandServices;
	private Map<String, String> eventServices;

	public ComponentServiceBus() {
		componentMap = new HashMap<String, Component>();
		commandServices = new HashMap<String, String>();
		eventServices = new HashMap<String, String>();
	}

	public void registerComponent(Component component) {
		componentMap.put(component.getComponentName(), component);
		commandServices.put(component.getComponentName(),
				component.getCommandServicePath() + "." + component.getCommandServiceName());
		eventServices.put(component.getComponentName(),
				component.getEventServicePath() + "." + component.getEventServiceName());
	}

	public void configureComponentConnections() {

		Component sourceComponent = componentMap.get("DocumentSystem");
		Component targetComponent = componentMap.get("PrintingSystem");

		new ManageableToPrintableAdapter((ManageableEventService) sourceComponent,
				(PrintingCommandService) targetComponent);

		sourceComponent = componentMap.get("PrintingSystem");
		targetComponent = componentMap.get("MessagingSystem");

		new PrintReportToSendableAdapter((PrintableEventService) sourceComponent,
				(MessagingCommandService) targetComponent);
	}

	public boolean sellTicket(String start, String end) {
		Component startComponent = componentMap.get("DocumentSystem");
		((DocumentCommandService) startComponent).createDocument(start + " " + end);

		return true;
	}
}
