package documentsystem.behaviour;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import component.Component;
import documentsystem.behaviour.service.DocumentCommandService;
import documentsystem.behaviour.service.Manageable;
import documentsystem.behaviour.service.event.ManageableEvent;
import documentsystem.behaviour.service.event.ManageableEventListener;
import documentsystem.behaviour.service.event.ManageableEventService;
import documentsystem.structure.Document;

public class DocumentSystemImpl implements DocumentCommandService, ManageableEventService, Component {

	private Map<Integer, Document> documents;
	private Set<ManageableEventListener> manageableEventListeners;

	private Logger logger;

	public DocumentSystemImpl() {
		manageableEventListeners = new HashSet<ManageableEventListener>();

		documents = new HashMap<Integer, Document>();
		logger = LogManager.getLogger(this.getClass());
	}
	
	public void Test() {
		
	}

	@Override
	public Boolean addDocument(Document document) {
		documents.put(document.getId(), document);

		logger.info("Document with title " + document.getTitel() + " added!");

		return documents.containsKey(document.getId());
	}

	@Override
	public Document createDocument(String title) {
		Manageable manageable = new Manageable() {

			@Override
			public String getTitle() {
				return title;
			}

			@Override
			public String getContent() {
				return "";
			}
			
		};
		
		Document document = new Document(title);
		
		logger.info("Document : " + title + " created!");
		
		// TODO Refactor into seperate method
		ManageableEvent manageableEvent = new ManageableEvent(manageable);
		
		for (ManageableEventListener manageableEventListener : manageableEventListeners) {
			manageableEventListener.onManageableCreated(manageableEvent);
		}
		
		return document;
	}

	@Override
	public void addManageableEventListener(ManageableEventListener manageableEventListener) {
		manageableEventListeners.add(manageableEventListener);
	}

	@Override
	public void removeManageableEventListener(ManageableEventListener manageableEventListener) {
		manageableEventListeners.remove(manageableEventListener);
	}

	@Override
	public String getCommandServiceName() {
		return DocumentCommandService.class.getName();
	}

	@Override
	public String getEventServiceName() {
		return ManageableEventService.class.getName();
	}

	@Override
	public String getCommandServicePath() {
		return DocumentCommandService.class.getPackageName();
	}

	@Override
	public String getEventServicePath() {
		return ManageableEventService.class.getPackageName();
	}

	@Override
	public String getComponentName() {
		return "DocumentSystem";
	}
	
}
