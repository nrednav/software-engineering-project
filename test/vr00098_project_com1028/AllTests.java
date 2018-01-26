package vr00098_project_com1028;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)

@SuiteClasses({
	AlbumInfoFormTest.class,
	EditSongInfoFrameTest.class,
	HelpFacilityTest.class,
	MainMenuTest.class,
	MusicCollectionTest.class,
	MusicDBHandlerTest.class,
	SongInfoFormTest.class,
	SongOrAlbumPickerTest.class,
	SongTest.class, 
	ViewCollectionScreenTest.class,
})

/**
 * This class runs all the tests
 * @author Vandern Rodrigues
 *
 */
public class AllTests {
	
	public static void main(String[] args) {
		
		Result result = JUnitCore.runClasses(AllTests.class);
		
	      for (Failure failure : result.getFailures()) {
	          System.out.println(failure.toString());
	       }
	 		
	       System.out.println(result.wasSuccessful());
	}
}
