package vr00098_project_com1028;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class MusicDBHandlerTest {

	private MusicDBHandler handler;
	private Connection connection;
	
	@Test
	public void testCreation() {
		handler = new MusicDBHandler();
		assertNotNull(handler);
	}
	
	@Test
	public void testConnection() {
		testCreation();
		connection = handler.connectToDB();
		assertNotNull(connection);
	}
	
	
}
