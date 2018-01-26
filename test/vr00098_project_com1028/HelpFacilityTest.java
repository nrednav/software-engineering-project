package vr00098_project_com1028;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class HelpFacilityTest {

	private HelpFacility hf;
	
	@Test
	public void testCreation() {
		hf = new HelpFacility();
		assertNotNull(hf);
	}
}
