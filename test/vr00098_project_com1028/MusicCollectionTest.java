package vr00098_project_com1028;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class MusicCollectionTest {
	
	private MusicCollection mc;
	
	@Test
	public void testCreation() {
		mc = new MusicCollection();
		assertNotNull(mc);
	}
}
