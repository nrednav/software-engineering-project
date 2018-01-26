package vr00098_project_com1028;

import java.util.Date;

/**
 * This class models a Song object that holds various song information
 * 
 * It utilizes the builder pattern to solve the problem of there being too many optional parameters of information for a given song
 * 
 * @author Vandern Rodrigues
 *
 */
public class Song {
	
	// Create and initialize the song information variables
	private int songID = 0; 
	private int songDuration; 
	private Date dateAdded;
	private String songName = "", artistName = "", albumName = "", genre = "", storageMedia = "", songRemarks = "";

	/*
	 *  The constructor is made private to prevent instantiation without using the Builder
	 *  
	 *  A SongBuilder object is passed in to utilize its constructor and setter methods to create the song object
	 *  
	 */
	private Song(SongBuilder builder) {
		
		// Update the initialized variables using the builder
		songID = builder.songID;
		songName = builder.songName;
		artistName = builder.artistName;
		albumName = builder.albumName;
		genre = builder.genre;
		storageMedia = builder.storageMedia;
		songRemarks = builder.songRemarks;
		songDuration = builder.songDuration;
		dateAdded = builder.dateAdded;
	}
	
	/**
	 * 
	 * @author Vandern Rodrigues
	 *
	 */
	public static class SongBuilder {
		
		// Required parameters
		private int songID;
		private String songName, 
					   		 artistName, 
					   		 genre;
		private Date dateAdded;
		
		// Optional parameters
		private String albumName = "",
					   storageMedia = "",
					   songRemarks = "";
		
		private int songDuration;
		
		// Create the default constructor with the required parameters
		public SongBuilder(int songID, String songName, String artistName, String genre, Date dateAdded) {
			this.songID = songID;
			this.songName = songName;
			this.artistName = artistName;
			this.genre = genre;
			this.dateAdded = dateAdded;
		}
		
		// Create methods to set the optional parameters of the SongBuilder
		public SongBuilder albumName(String str)
		{ albumName = str; return this; }
		
		public SongBuilder storageMedia(String str)
		{ storageMedia = str; return this; }
		
		public SongBuilder songRemarks(String str) 
		{ songRemarks = str; return this; }
		
		public SongBuilder songDuration(int hours, int minutes, int seconds) 
		{ songDuration = seconds + (minutes*60) + (hours*3600); return this; }
		
		// build() uses the Song classes private constructor that in turn uses the SongBuilder to return a Song object
		public Song build() {
			return new Song(this);
		}
	}
	
	/**
	 * This method returns the song ID
	 * @return songID
	 */
	public int getSongID() {
		return songID;
	}
	
	/**
	 * This method returns the song name
	 * @return song name
	 */
	public String getSongName() {
		return songName;
	}
	
	/**
	 * This method returns the songs artist name
	 * @return artist name
	 */
	public String getArtistName() {
		return artistName;
	}
	
	/**
	 * This method returns the songs album name
	 * @return album name
	 */
	public String getAlbumName() {
		return albumName;
	}
	
	/**
	 * This method returns the songs genre
	 * @return genre
	 */
	public String getGenre() {
		return genre;
	}
	
	/**
	 * This method returns today's date
	 * @return date added
	 */
	public Date getDateAdded() {
		return dateAdded;
	}
	
	/**
	 * This method returns the song remarks
	 * @return song remarks
	 */
	public String getSongRemarks() {
		return songRemarks;
	}
	
	/**
	 * This method returns the songs storage media
	 * @return storage media
	 */
	public String getStorageMedia() {
		return storageMedia;
	}
	
	/**
	 * This method returns the songs duration
	 * @return
	 */
	public int getSongDuration() {
		return songDuration;
	}
}
