package vr00098_project_com1028;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;

/**
 * This class represents the Frame containing the album information form,
 * @author Vandern Rodrigues
 *
 */
public class AlbumInfoForm extends JFrame {
	
	private static final long serialVersionUID = -7310529828106230910L; // Unique serial ID used to identify class for serialization
	private JPanel contentPane; // References frames main layout
	private JPanel albumInfoPane; // References pane containing album information and buttons
	
	// References to form fields
	private JTextField hoursField, minutesField, secondsField; 
	private JTextField songNameField, songRemarksField; 
	private JTextField artistNameField, albumNameField, genreField, storageMediaField;
	
	// ArrayLists to store references to dynamically created song name, remarks and duration fields
	private ArrayList<JTextField> songNames = new ArrayList<JTextField>(SongOrAlbumPicker.getAlbumSize());
	private ArrayList<JTextField> songRemarks = new ArrayList<JTextField>(SongOrAlbumPicker.getAlbumSize());
	private ArrayList<JTextField> songHours = new ArrayList<JTextField>(SongOrAlbumPicker.getAlbumSize());
	private ArrayList<JTextField> songMinutes = new ArrayList<JTextField>(SongOrAlbumPicker.getAlbumSize());
	private ArrayList<JTextField> songSeconds = new ArrayList<JTextField>(SongOrAlbumPicker.getAlbumSize());
	
	private MusicCollection mCollection; // Object referencing MusicCollection class to store songs
	
	private JButton btnSubmit, btnCancel; // References to the Submit and Cancel buttons on the form
	
	private boolean isInserted = false; // Flag variable to indicate if data was inserted into database successfully

	/**
	 * Create the frame and define its layout and contents
	 */
	public AlbumInfoForm() {
		setTitle("Album Form");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0,1));
		
		createAlbumPane(); // Call to initialize the album panel into the frame
	}
	
	/**
	 * This method handles creating and initializing the album panel
	 */
	private void createAlbumPane() {
		
		albumInfoPane = new JPanel();	// Initialize album panel
		albumInfoPane.setBorder(BorderFactory.createTitledBorder("Add Album Information")); // Create titled border for panel
		albumInfoPane.setLayout(new MigLayout("", "[][][][][][][][][][][][][]", "[][][][][]")); // Set MiG as layout manager of panel
		JScrollPane scrollPane = new JScrollPane(albumInfoPane); // To support the dynamic fields placement exceeding the panels height,
		contentPane.add(scrollPane);							 // Add the panel to a scroll pane
		
		//// Artist Name Row ////
		JLabel artistNameLabel = new JLabel("Artist Name: ");  // Create label for field
		artistNameField = new JTextField(40); // Initialize field reference	
		artistNameField.addKeyListener(new KeyAdapter() {      // To prevent errors from occurring during insertion into the database,
			public void keyTyped(KeyEvent e) {			   	   // Add a key listener that performs a check on the length of text entered,
				if(artistNameField.getText().length() >= 40) { // If limit defined is exceeded, consume every character entered thereafter
					e.consume();
				}
			}
		});
		albumInfoPane.add(artistNameLabel, "cell 0 0"); // Add the label to the cell position 0, 0
		albumInfoPane.add(artistNameField, "cell 1 0"); // Add the field to the cell right next to it
		
		initializeFormButtons(); // Call to add buttons to the form panel
		
		//// Album Name Row ////
		JLabel albumNameLabel = new JLabel("Album Name: ");
		albumNameField = new JTextField(40);
		albumNameField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if(albumNameField.getText().length() >= 40) {
					e.consume();
				}
			}
		});
		albumInfoPane.add(albumNameLabel, "cell 0 1");
		albumInfoPane.add(albumNameField, "cell 1 1");
		
		//// Genre Row ////
		JLabel genreLabel = new JLabel("Genre: ");
		genreField = new JTextField(10);
		genreField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if(genreField.getText().length() >= 10) {
					e.consume();
				}
			}
		});
		albumInfoPane.add(genreLabel, "cell 0 2");
		albumInfoPane.add(genreField, "cell 1 2");
		
		//// Storage Media Row ////
		JLabel storageMediaLabel = new JLabel("Storage Media: ");
		storageMediaField = new JTextField(10);
		storageMediaField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if(storageMediaField.getText().length() >= 10) {
					e.consume();
				}
			}
		});
		albumInfoPane.add(storageMediaLabel, "cell 0 3");
		albumInfoPane.add(storageMediaField, "cell 1 3");		
		
		int layoutRow = 3; // This variable references the last cell's row number
		
		// This for-loop handles the dynamic addition of song information fields based on the users input to the input dialog
		for(int i = 0; i < SongOrAlbumPicker.getAlbumSize(); i++) {
			
			layoutRow++; // Add a new row to the panel
			
			//// Song Name Row ////
			JLabel songNameLabel = new JLabel("Song " + (i+1) + " name: ");
			songNameField = new JTextField(40);
			songNameField.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) {
					if(songNameField.getText().length() >= 40) {
						e.consume();
					}
				}
			});
			songNames.add(songNameField);
			albumInfoPane.add(songNameLabel, "cell 0 " + layoutRow); // Add the labels and fields to the new row
			albumInfoPane.add(songNameField, "cell 1 " + layoutRow);
			
			layoutRow++;
			
			//// Song Remarks Row ////
			JLabel songRemarksLabel = new JLabel("Song " + (i+1) + " remarks:");
			songRemarksField = new JTextField(30);
			songRemarksField.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) {
					if(songRemarksField.getText().length() >= 30) {
						e.consume();
					}
				}
			});
			songRemarks.add(songRemarksField);
			albumInfoPane.add(songRemarksLabel, "cell 0 " + layoutRow);
			albumInfoPane.add(songRemarksField, "cell 1 " + layoutRow);
			layoutRow++;
			
			//// Song Duration Row ////
			JLabel songDurationLabel = new JLabel("Song " + (i+1) + " duration: ");
			
			hoursField = new JTextField(2);
			hoursField.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) {
					if(hoursField.getText().length() >= 2) {
						e.consume();
					}
				}
			});
			songHours.add(hoursField);
			
			minutesField = new JTextField(2);
			minutesField.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) {
					if(minutesField.getText().length() >= 2) {
						e.consume();
					}
				}
			});
			songMinutes.add(minutesField);
			
			secondsField = new JTextField(2);
			secondsField.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent e) {
					if(secondsField.getText().length() >= 2) {
						e.consume();
					}
				}
			});
			songSeconds.add(secondsField);
			
			albumInfoPane.add(songDurationLabel, "cell 0 " + layoutRow);
			albumInfoPane.add(hoursField, "flowx, cell 1 " + layoutRow); // The 'flow x' attribute means any cell added to the same
			albumInfoPane.add(minutesField, "cell 1 " + layoutRow);      // x-position after it, will flow into place right next to it
			albumInfoPane.add(secondsField, "cell 1 " + layoutRow);
		}
		
	}

	/**
	 * This method handles initializing and adding the buttons to the form
	 */
	private void initializeFormButtons() {
		
		//// Submit Button ////
		btnSubmit = new JButton("Submit");
		albumInfoPane.add(btnSubmit, "cell 12 0");
		btnSubmit.addActionListener(new ActionListener() { // Add an action listener that listens for a button click
			
			@Override
			public void actionPerformed(ActionEvent e) { // Carry out the responses to the click in this method
				
				saveAlbumForm(); // Save contents of the form
				
				// If data is entered successfully display a dialog saying so, close the window and open the main menu 
				if(isInserted) {
					JOptionPane.showMessageDialog(albumInfoPane, "The information you entered was saved successfully.");
					dispose();
					MainMenu menu = new MainMenu();
					menu.setLocationRelativeTo(null);
					menu.setVisible(true);
				}
				

			}
		});
		
		//// Cancel Button ////
		btnCancel = new JButton("Cancel");
		albumInfoPane.add(btnCancel, "cell 12 1");
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				MainMenu menu = new MainMenu();
				menu.setLocationRelativeTo(null);
				menu.setVisible(true);
			}
		});
	}

	/**
	 * This method handles saving the contents of the form to the database
	 */
	private void saveAlbumForm() {
		
		mCollection = new MusicCollection(); // Initialize the music collection object created
		
		Calendar calendar = new GregorianCalendar(); // Obtain a reference to todays date from Gregorian calendar
		java.sql.Date dateAdded = new java.sql.Date(calendar.getTimeInMillis()); // Use the reference to get the Date in a SQL format
		
		int hours = 0, minutes = 0, seconds = 0; // Variables to store data parsed from song duration fields
		
		boolean continueFlag = false; // Flag variable to indicate if its okay to continue saving the data
		
		// The following are validation checks performed on the form to prevent errors when inserting into the database
		for(int v = 0; v < songNames.size(); v++) {
			
			// Display dialogs if the required fields are left empty
			if(!songNames.get(v).getText().matches("[a-zA-Z0-9 ]+") || songNames.get(v).getText().startsWith(" ")) {
				JOptionPane.showMessageDialog(contentPane, "You did not enter a song name.");
			} else if (!artistNameField.getText().matches("[a-zA-Z0-9 ]+") || artistNameField.getText().startsWith(" ")) {
				JOptionPane.showMessageDialog(contentPane, "You did not enter an artist name.");
			} else if (!genreField.getText().matches("[a-zA-Z0-9 ]+") || genreField.getText().startsWith(" ")) {
				JOptionPane.showMessageDialog(contentPane, "You did not enter a genre.");
				
			// Display dialogs if the data entered into the song duration fields is not numeric
			} else if (!songHours.get(v).getText().isEmpty() && !songHours.get(v).getText().matches("[0-9]+")) {
				JOptionPane.showMessageDialog(contentPane, "You did not enter a valid number of hours.");
			} else if (!songMinutes.get(v).getText().isEmpty() && !songMinutes.get(v).getText().matches("[0-9]+")) {
				JOptionPane.showMessageDialog(contentPane, "You did not enter a valid number of minutes.");
			} else if (!songSeconds.get(v).getText().isEmpty() && !songSeconds.get(v).getText().matches("[0-9]+")) {
				JOptionPane.showMessageDialog(contentPane, "You did not enter a valid number of seconds.");
			} else {
				continueFlag = true; // If all checks passed, set the continue flag variable to true
			}
		}
		
		// Check if its okay to continue
		if(continueFlag) {
			
			// If it is, iterate through all elements in the arrayLists containing references to the jTextFields
			for(int i=0; i < SongOrAlbumPicker.getAlbumSize(); i++) {
				
				// If the song duration fields are left empty set their default values to 0, 
				// Otherwise parse the data entered into Integer values and store them in the appropriate variable
				try {
					if(songHours.get(i).getText().isEmpty()) {
						hours = 0;												
					} else if (!hoursField.getText().isEmpty()) {
						hours = Integer.parseInt(songHours.get(i).getText());
					}
					
					if(songMinutes.get(i).getText().isEmpty()) {
						minutes = 0;
					} else if (!minutesField.getText().isEmpty()) {
						minutes = Integer.parseInt(songMinutes.get(i).getText());
					}
					
					if(songSeconds.get(i).getText().isEmpty()) {
						seconds = 0;
					} else if (!secondsField.getText().isEmpty()) {
						seconds = Integer.parseInt(songSeconds.get(i).getText());
					} 
					
				} catch (NumberFormatException nfe) { // Catch the number format exception raised when parsing the data
					nfe.printStackTrace();
				}
				
				// Create a song object and initialize its member variables via the builder pattern
				Song song = new Song.SongBuilder(i, songNames.get(i).getText(),
													artistNameField.getText(), 
													genreField.getText(), dateAdded)
									.albumName(albumNameField.getText())
									.storageMedia(storageMediaField.getText())
									.songRemarks(songRemarks.get(i).getText())
									.songDuration(hours, minutes, seconds).build();
				
				mCollection.addSong(song); // For each song generated, it will be added to the ArrayList in the music collection object
			}
			
			connectToMusicDB(); // Connect to the database and save the information extracted
		}
	}
	
	/**
	 * This method handles connecting to the database and inserting data into the music collection table
	 */
	private void connectToMusicDB() {
		
		MusicDBHandler handler = new MusicDBHandler(); // Create a database handler to obtain a connection to the database
		
		try (
			Connection connection = handler.connectToDB(); // Obtain the connection
			Statement statement = connection.createStatement(); // Create a statement to hold the insertion query
		) {
			
			ArrayList<Song> songs = mCollection.getMusicCollection(); // Get the arrayList of songs from the music collection
			
			String sql_saveAlbum = null; // Create the string that will contain the query
			
			// For every element in the arrayList retrieved
			for(int i = 0; i < songs.size(); i++) {
				
				// Alter the string query to get data from the arrayList index that matches the iteration number
				sql_saveAlbum = "insert into music_collection" +
								" (songName, artistName, albumName, genre, storageMedia, songRemarks, songDuration, dateAdded)" +
								" values ('" + songs.get(i).getSongName() +
								"', '" + songs.get(i).getArtistName() +
								"', '" + songs.get(i).getAlbumName() + 
								"', '" + songs.get(i).getGenre() +
								"', '" + songs.get(i).getStorageMedia() +
								"', '" + songs.get(i).getSongRemarks() + 
								"', " + songs.get(i).getSongDuration() +
								", '" + songs.get(i).getDateAdded() + "')";
				
				// Execute the query and set the flag variable to indicate a successful insertion
				if(statement.executeUpdate(sql_saveAlbum) > 0) {
					isInserted = true;
				}
			}
			
		} catch (SQLException e) { // Catch any SQL exceptions raised
			e.printStackTrace();
		}
	}
			
}
