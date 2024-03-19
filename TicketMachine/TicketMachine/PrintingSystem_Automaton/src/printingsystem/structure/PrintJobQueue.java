package printingsystem.structure;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class PrintJobQueue {
	private static PrintJobQueue printJobQueue;
	
	private Queue<PrintJob> queue;
	private Map<PrintFormat, Printer> printersMap;

	private PrintJobQueue() {
		queue = new LinkedList<PrintJob>();
		printersMap = new HashMap<PrintFormat, Printer>();
	}

	public static PrintJobQueue getInstance() {
		// konkrete Klasse / Was?
		if (printJobQueue == null) {
			printJobQueue = new PrintJobQueue();
		}

		return printJobQueue;
	}

	public void addPrinter(Printer printer) {
		printersMap.put(printer.getPrintFormat(), printer);
	}
	
	public void addPrintJob(PrintJob printJob) {
		queue.add(printJob);
		printJob.changePrintJobState(PrintJobAction.QUEUE);
		
		PrintJob lastPrintJob = queue.poll();
		if(lastPrintJob != null) {
			

			PrintFormat printFormat = PrintFormat.valueOf(lastPrintJob.getConfiguration().getPrintFormat());

			Printer selectedPrinter = printersMap.get(printFormat);
			selectedPrinter.executePrintJob(lastPrintJob);
		}
	}

}
