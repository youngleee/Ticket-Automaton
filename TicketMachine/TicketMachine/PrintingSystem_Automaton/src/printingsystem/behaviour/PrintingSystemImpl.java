package printingsystem.behaviour;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import component.Component;
import printingsystem.behaviour.service.PrintConfiguration;
import printingsystem.behaviour.service.PrintReport;
import printingsystem.behaviour.service.Printable;
import printingsystem.behaviour.service.PrintingCommandService;
import printingsystem.behaviour.service.event.PrintableEvent;
import printingsystem.behaviour.service.event.PrintableEventListener;
import printingsystem.behaviour.service.event.PrintableEventService;
import printingsystem.structure.PrintFormat;
import printingsystem.structure.PrintJob;
import printingsystem.structure.PrintJobQueue;
import printingsystem.structure.Printer;

public class PrintingSystemImpl implements PrintingCommandService, PrintableEventService, Component {

	private PrintJobQueue printJobQueue;
	private Set<PrintableEventListener> printableEventListeners;

	public PrintingSystemImpl() {
		printableEventListeners = new HashSet<PrintableEventListener>();


		printJobQueue = PrintJobQueue.getInstance();
		printJobQueue.addPrinter(new Printer(PrintFormat.A4));
		printJobQueue.addPrinter(new Printer(PrintFormat.A3));
	}

	@Override
	public PrintReport printDocument(Printable printable, PrintConfiguration printConfiguration) {

		PrintJob printJob = new PrintJob(printable, printConfiguration);
		printJobQueue.addPrintJob(printJob);

		PrintReport printReport = new PrintReport();
		printReport.setConfirmationText(printable.getContent());
		printReport.setPrintDate(LocalDate.now());
		printReport.setPrintSuccessful(true);
		
		PrintableEvent printableEvent = new PrintableEvent(printReport);

		for (PrintableEventListener printableEventListener : printableEventListeners) {
			printableEventListener.onPrintableExcuted(printableEvent);
		}

		return printReport;
	}

	@Override
	public Set<String> getSupportedPrintFormats() {

		return Arrays.stream(PrintFormat.values()).map(Enum::name).collect(Collectors.toSet());
	}

	@Override
	public String getCommandServiceName() {
		return PrintingCommandService.class.getName();
	}

	@Override
	public String getEventServiceName() {
		return PrintableEventService.class.getName();
	}

	@Override
	public String getCommandServicePath() {
		return PrintingCommandService.class.getPackageName();
	}

	@Override
	public String getEventServicePath() {
		return PrintableEventService.class.getPackageName();
	}

	@Override
	public String getComponentName() {
		return "PrintingSystem";
	}

	@Override
	public void addPrintableEventListener(PrintableEventListener printableEventListener) {
		printableEventListeners.add(printableEventListener);
	}

	@Override
	public void removePrintableEventListener(PrintableEventListener printableEventListener) {
		printableEventListeners.remove(printableEventListener);
	}

}
