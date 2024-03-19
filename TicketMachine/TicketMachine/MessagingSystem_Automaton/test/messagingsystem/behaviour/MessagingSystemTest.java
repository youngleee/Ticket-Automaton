package messagingsystem.behaviour;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import messagingsystem.behaviour.service.MessagingCommandService;
import messagingsystem.behaviour.service.Sendable;
import messagingsystem.structure.MessageType;

class MessagingSystemTest {

	private static MessagingCommandService messagingSystem;
	private static Sendable sendable;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		messagingSystem = new MessagingSystemImpl();
		
		sendable = new Sendable() {
			
			@Override
			public String getSender() {
				return "slotos@leuphana.de";
			}
			
			@Override
			public String getReceiver() {
				return "rainer.zufall@web.de";
			}
			
			@Override
			public MessageType getMessageType() {
				return MessageType.EMAIL;
			}
			
			@Override
			public String getContent() {
				return "This is a content!";
			}
		};
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		messagingSystem = null;
	}

	@Test
	void canMessageBeSentViaEmail() {
		Assertions.assertTrue(messagingSystem.sendMessage(sendable).isDeliverySuccessful());
	}

}
