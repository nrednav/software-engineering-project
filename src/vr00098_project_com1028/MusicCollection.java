package vr00098_project_com1028;

import java.util.ArrayList;

/**
 * This class models a temporary music collection that stores the song objects in an array list in a non-persisted state
 * @author Vandern Rodrigues
 *
 */
public class MusicCollection {

	// Creating the music collection to store the songs 
	private ArrayList<Song> musicCollection = new ArrayList<Song>();
	
	/**
	 * This method will add a song to the array list
	 * @param song
	 */
	public void addSong(Song song) {
		musicCollection.add(song);
	}
	
	/**
	 * This method returns the music collection ArrayList
	 * @return musicCollection
	 */
	public ArrayList<Song> getMusicCollection() {
		return musicCollection;
	}
	
	
}
