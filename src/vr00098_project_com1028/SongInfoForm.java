package vr00098_project_com1028;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import java.sql.*;

/**
 * This class represents the frame containing the form to enter information about a single song
 * @author Vandern Rodrigues
 *
 */
public class SongInfoForm extends JFrame {

	private static final long serialVersionUID = 1156665350779291076L;
	
	// References to the main content panel and the panel containing the form
	private JPanel contentPane, formPane; 
	
	// References to the form labels
	private JLabel songNameLabel, artistNameLabel, albumNameLabel, genreLabel; 
	private JLabel storageMediaLabel, songRemarksLabel;
	private JLabel songDurationLabel;
	
	// References to the form fields
	private JTextField songNameField, artistNameField, albumNameField, genreField;
	private JTextField storageMediaField, songRemarksField;
	private JTextField secondsField, minutesField, hoursField;
	
	// References to the form buttons
	private JButton btnSubmit, btnCancel;
	
	// Initialization of flag variable that tracks if data was inserted into the database
	private boolean isInserted = false;
	
	// Reference to the SQL command that handles insertion in to the database
	String sql_Insert;

	/**
	 * Create the main content pane and places the form inside it.
	 */
	public SongInfoForm() {
		setTitle("Song Information Form");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 389);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		// Create the form panel, set its border and then add it to the main panel
		formPane = new JPanel();
		Border innerBorder = BorderFactory.createTitledBorder("Add Song Information");
		Border outerBorder = BorderFactory.createEmptyBorder(10,10,10,10);
		formPane.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		contentPane.add(formPane);
		
		initializeLabelsAndFields(); // Initialize form labels and fields
		createForm(); // Create the form with the initialized form labels and fields
		initializeFormButtons(); // Initialize the form buttons
	}
	
	/**
	 * This method initializes the form labels and fields
	 */
	private void initializeLabelsAndFields() {
		
		//// Song Name ////
		songNameLabel = new JLabel("Song Name: ");
		songNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		songNameField  = new JTextField(40);
		songNameField.addKeyListener(new KeyAdapter() { 
			public void keyTyped(KeyEvent e) {
				if(songNameField.getText().length() >= 40) { // Consume the characters typed if they exceed the limit defined
					e.consume();
				}
			}
		});
		
		//// Artist Name ////
		artistNameLabel = new JLabel("Artist Name: ");
		artistNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		artistNameField = new JTextField(40);
		artistNameField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (artistNameField.getText().length() >= 40) {
					e.consume();
				}
			}
		});
		
		//// Genre ////
		genreLabel = new JLabel("Genre: ");
		genreLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		genreField = new JTextField(10);
		genreField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (genreField.getText().length() >= 10) {
					e.consume();
				}
			}
		});
		
		//// Album Name ////
		albumNameLabel = new JLabel("Album Name: ");
		albumNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		albumNameField = new JTextField(40);
		albumNameField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (albumNameField.getText().length() >= 40) {
					e.consume();
				}
			}
		});
		
		//// Storage Media ////
		storageMediaLabel = new JLabel("Storage Media: ");
		storageMediaLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		storageMediaField = new JTextField(10);
		storageMediaField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (storageMediaField.getText().length() >= 10) {
					e.consume();
				}
			}
		});
		
		//// Song Remarks ////
		songRemarksLabel = new JLabel("Song Remarks: ");
		songRemarksLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		songRemarksField = new JTextField(30);
		songRemarksField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (songRemarksField.getText().length() >= 30) {
					e.consume();
				}
			}
		});
		
		//// Song Duration ////
		songDurationLabel = new JLabel("Song Duration: ");
		songDurationLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		secondsField = new JTextField(2);
		secondsField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (secondsField.getText().length() >= 2) {
					e.consume();
				}
			}
			
		});
		
		minutesField = new JTextField(2);
		minutesField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (minutesField.getText().length() >= 2) {
					e.consume();
				}
			}
		});
		hoursField = new JTextField(2);
		hoursField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (hoursField.getText().length() >= 2 ) {
					e.consume();
				}
			}
		});
	}

	
	/**
	 * This method specifies the form layout and creates the form
	 */
	private void createForm() {
		
		// Set GridbayLayout as the form panels main layout manager
		GridBagLayout gbl_contentPane = new GridBagLayout();
		formPane.setLayout(gbl_contentPane);
		
		//// Song Name row ////
		GridBagConstraints gc1 = new GridBagConstraints(); // The GridBagConstrains reference the cell positions on the layout
		gc1.gridx = 1; // Add the label to Column 1
		gc1.gridy = 1; // and Row 1
		gc1.anchor = GridBagConstraints.LINE_END; // Align the label to end of the cell
		formPane.add(songNameLabel,gc1);
		GridBagConstraints gc2 = new GridBagConstraints();
		gc2.gridx = 2; // Add the field to Column to the right of the label
		gc2.gridy = 1;
		formPane.add(songNameField, gc2);
		
		//// Artist Name row ////
		GridBagConstraints gc3 = new GridBagConstraints();
		gc3.gridx = 1;
		gc3.gridy = 2;
		gc3.anchor = GridBagConstraints.LINE_END;
		formPane.add(artistNameLabel, gc3);
		GridBagConstraints gc4 = new GridBagConstraints();
		gc4.gridx = 2;
		gc4.gridy = 2;
		formPane.add(artistNameField, gc4);
		
		//// Genre row ////
		GridBagConstraints gc5 = new GridBagConstraints();
		gc5.gridx = 1;
		gc5.gridy = 3;
		gc5.anchor = GridBagConstraints.LINE_END;
		formPane.add(genreLabel, gc5);
		GridBagConstraints gc6 = new GridBagConstraints();
		gc6.gridx = 2;
		gc6.gridy = 3;
		gc6.anchor = GridBagConstraints.LINE_START;
		formPane.add(genreField, gc6);
		
		//// Album Name row ////
		GridBagConstraints gc7 = new GridBagConstraints();
		gc7.gridx = 1;
		gc7.gridy = 4;
		gc7.anchor = GridBagConstraints.LINE_END;
		formPane.add(albumNameLabel, gc7);
		GridBagConstraints gc8 = new GridBagConstraints();
		gc8.gridx = 2;
		gc8.gridy = 4;
		formPane.add(albumNameField, gc8);
		
		//// Storage Media row ////
		GridBagConstraints gc9 = new GridBagConstraints();
		gc9.gridx = 1;
		gc9.gridy = 5;
		gc9.anchor = GridBagConstraints.LINE_END;
		formPane.add(storageMediaLabel, gc9);
		GridBagConstraints gc10 = new GridBagConstraints();
		gc10.gridx = 2;
		gc10.gridy = 5;
		gc10.anchor = GridBagConstraints.LINE_START;
		formPane.add(storageMediaField, gc10);
		
		//// Song Remarks row ////
		GridBagConstraints gc11 = new GridBagConstraints();
		gc11.gridx = 1;
		gc11.gridy = 6;
		gc11.anchor = GridBagConstraints.LINE_END;
		formPane.add(songRemarksLabel, gc11);
		GridBagConstraints gc12 = new GridBagConstraints();
		gc12.gridx = 2;
		gc12.gridy = 6;
		gc12.anchor = GridBagConstraints.LINE_START;
		formPane.add(songRemarksField, gc12);
		
		//// Song Duration row ////
		GridBagConstraints gc13 = new GridBagConstraints();
		gc13.gridx = 1;
		gc13.gridy = 7;
		gc13.anchor = GridBagConstraints.LINE_END;
		formPane.add(songDurationLabel, gc13);
		GridBagConstraints gc14 = new GridBagConstraints();
		gc14.anchor = GridBagConstraints.WEST;
		gc14.gridx = 2;
		gc14.gridy = 7;
		formPane.add(hoursField, gc14);
		GridBagConstraints gc15 = new GridBagConstraints();
		gc15.anchor = GridBagConstraints.WEST;
		gc15.insets = new Insets(0, 40, 0, 0);
		gc15.gridx = 2;
		gc15.gridy = 7;
		formPane.add(minutesField, gc15);
		GridBagConstraints gc16 = new GridBagConstraints();
		gc16.anchor = GridBagConstraints.WEST;
		gc16.insets = new Insets(0, 80, 0, 0);
		gc16.gridx = 2;
		gc16.gridy = 7;
		formPane.add(secondsField, gc16);
		GridBagConstraints gc17 = new GridBagConstraints();
		gc17.gridx = 2;
		gc17.gridy = 7;
		gc17.insets = new Insets(0, 120, 0, 0);
		gc17.anchor = GridBagConstraints.LINE_START;
		formPane.add(new JLabel("(hours, minutes, seconds)"), gc17);
	}

	/**
	 * This method initializes the submit and cancel buttons with action listeners
	 */
	private void initializeFormButtons() {
		
		// Initialize the buttons
		btnSubmit = new JButton("Submit");
		btnCancel = new JButton("Cancel");
		
		// Add an action listener that re-directs to the main menu if clicked
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				MainMenu menu = new MainMenu();
				menu.setLocationRelativeTo(null);
				menu.setVisible(true);
			}
		});
		
		// Add an action listener that call the connectToMusicDB method and saves the form data after validation checks
		btnSubmit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// The following are validation checks performed on fields in the form to prevent errors when inserting into the database
				if(!songNameField.getText().matches("[a-zA-Z0-9 ]+") || songNameField.getText().startsWith(" ")) {
					JOptionPane.showMessageDialog(contentPane, "You did not enter a song name.");
				} else if (!artistNameField.getText().matches("[a-zA-Z0-9 ]+") || artistNameField.getText().startsWith(" ")) {
					JOptionPane.showMessageDialog(contentPane, "You did not enter an artist name.");
				} else if (!genreField.getText().matches("[a-zA-Z0-9 ]+") || genreField.getText().startsWith(" ")) {
					JOptionPane.showMessageDialog(contentPane, "You did not enter a genre.");
				} else if (!hoursField.getText().isEmpty() && !hoursField.getText().matches("[0-9]+")) {
					JOptionPane.showMessageDialog(contentPane, "You did not enter a valid number of hours.");
				} else if (!minutesField.getText().isEmpty() && !minutesField.getText().matches("[0-9]+")) {
					JOptionPane.showMessageDialog(contentPane, "You did not enter a valid number of minutes.");
				} else if (!secondsField.getText().isEmpty() && !secondsField.getText().matches("[0-9]+")) {
					JOptionPane.showMessageDialog(contentPane, "You did not enter a valid number of seconds.");
				} else {
					
					// If all checks pass, connect to the database and save the data
					connectToMusicDB(); 
					
					// Alert the user if their data was saved successfully
					if(isInserted) {
						JOptionPane.showMessageDialog(contentPane, "The information you entered was saved successfully.");
					}

					// After all the above is done, close the window and re-direct to the main menu
					dispose();
					MainMenu menu = new MainMenu();
					menu.setLocationRelativeTo(null);
					menu.setVisible(true);
				}
			}
		});
		
		// Add both buttons to the main panel
		contentPane.add(btnSubmit);
		contentPane.add(btnCancel);
	}
	
	/**
	 * This method connects to the music database and stores the users input from the form
	 */
	public void connectToMusicDB() {
		
		MusicDBHandler handler = new MusicDBHandler(); // Instantiate a database handler to obtain a connection to the database
		
		getFormData(); // Get the data from the form
		
		try (
			Connection connection = handler.connectToDB(); // Obtain connection to db via handler
			Statement statement = connection.createStatement(); // Create statement object to hold SQL commands
		) {
			
			// Create the SQL command to create the table in the database if it does not already exist
			String sql_CreateTable = "create table if not exists music_collection(" +
									 "songID INT AUTO_INCREMENT, " +
									 "songName VARCHAR(40), " +
									 "artistName VARCHAR(40), " +
									 "albumName VARCHAR(40)," +
									 "genre VARCHAR(10), " +
									 "storageMedia VARCHAR(10), " +
									 "songRemarks VARCHAR(30), " +
									 "songDuration INT, " +
									 "dateAdded DATE, " +
									 "PRIMARY KEY (songID))";
			
			// Execute both SQL commands
			statement.executeUpdate(sql_CreateTable);
			
			// Execute the Insertion command and store its result to indicate if data was inserted successfully
			if(statement.executeUpdate(sql_Insert) > 0) {
				isInserted = true;
			}
			
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * This method gets the data from the form and initializes the SQL query with the data obtained
	 */
	private void getFormData() {
		
		// Get today's date and store it an SQL compatible formats
		Calendar calendar = new GregorianCalendar();
		java.sql.Date dateAdded = new java.sql.Date(calendar.getTimeInMillis());
		
		// These variables will store the parsed data from the song duration fields
		int hours = 0, minutes = 0, seconds = 0;	
		
		// If the song duration fields are empty, set their default values to 0, otherwise parse the string values to integers
		try {
			if(hoursField.getText().isEmpty()) {
				hours = 0;												
			} else if (!hoursField.getText().isEmpty()) {
				hours = Integer.parseInt(hoursField.getText());
			}
			
			if(minutesField.getText().isEmpty()) {
				minutes = 0;
			} else if (!minutesField.getText().isEmpty()) {
				minutes = Integer.parseInt(minutesField.getText());
			}
			
			if(secondsField.getText().isEmpty()) {
				seconds = 0;
			} else if (!secondsField.getText().isEmpty()) {
				seconds = Integer.parseInt(secondsField.getText());
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		// Create a song object via the builder pattern to store the data from the text fields
		Song song = new Song.SongBuilder(1, songNameField.getText(),
											artistNameField.getText(),
											genreField.getText(),
											dateAdded)
						.songDuration(hours, minutes, seconds)
						.albumName(albumNameField.getText())
						.songRemarks(songRemarksField.getText())
						.storageMedia(storageMediaField.getText()).build();
		
		// Pull the data from the song object and initialize the Insert query
		sql_Insert = "insert into music_collection" +
		" (songName, artistName, albumName, genre, storageMedia, songRemarks, songDuration, dateAdded)" +
		" values ('" + song.getSongName() +
		"', '" + song.getArtistName() +
		"', '" + song.getAlbumName() + 
		"', '" + song.getGenre() +
		"', '" + song.getStorageMedia() +
		"', '" + song.getSongRemarks() + 
		"', " + song.getSongDuration() +
		", '" + song.getDateAdded() + "')";
	}
}
