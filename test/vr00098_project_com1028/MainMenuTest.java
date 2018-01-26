package vr00098_project_com1028;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class MainMenuTest {

	private MainMenu menu;
	
	@Test
	public void testCreation() {
		menu = new MainMenu();
		assertNotNull(menu);
	}
}
