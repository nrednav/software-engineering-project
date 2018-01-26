package vr00098_project_com1028;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ViewCollectionScreenTest {

	private ViewCollectionScreen vcs;
	
	@Test
	public void testCreation() {
	
		vcs = new ViewCollectionScreen();
		assertNotNull(vcs);

	}
}
