package vr00098_project_com1028;

import java.util.Date;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class SongTest {

	private Song song;
	private int songID = 0; 
	private Date dateAdded;
	private String songName = "Lion",
			artistName = "Toto",
			albumName = "Live in amsterdam",
			genre = "Rock",
			storageMedia = "CD",
			songRemarks = "None";
	
	@Test
	public void testCreation() {
		song = new Song.SongBuilder(songID, songName, artistName, genre, dateAdded)
				.albumName(albumName)
				.storageMedia(storageMedia)
				.songRemarks(songRemarks)
				.songDuration(0, 0, 0).build();
		
		assertNotNull(song);
	}
	
	@Test
	public void testSongName() {
		testCreation();
		assertEquals("Lion", song.getSongName());
	}
	
}
