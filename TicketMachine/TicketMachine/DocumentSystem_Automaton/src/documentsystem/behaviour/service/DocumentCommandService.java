package documentsystem.behaviour.service;

import documentsystem.structure.Document;

public interface DocumentCommandService  {

	Document createDocument(String title);
	Boolean addDocument(Document document);

}