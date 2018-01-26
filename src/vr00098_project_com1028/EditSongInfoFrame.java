package vr00098_project_com1028;

import java.awt.BorderLayout;
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
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import java.awt.Dimension;

/**
 * This class represents the frame enclosing the form panel responsible for editing the song information
 * @author Vandern Rodrigues
 *
 */
public class EditSongInfoFrame extends JFrame {

	private static final long serialVersionUID = -6628207677500051239L;
	
	private JPanel contentPane, editForm; // References to the main panel and the edit form
	
	private JButton btnSave, btnCancel; // References to the Save and Cancel buttons
	
	// References to the form labels
	private JLabel songNameLabel, artistNameLabel, albumNameLabel,
				   genreLabel, storageMediaLabel, songRemarksLabel,
				   songDurationLabel;
	
	// References to the form fields
	private JTextField songNameField, artistNameField, albumNameField,
					   genreField, storageMediaField, songRemarksField,
					   songDurationField;
	
	private int songID; // Reference to the songID of the song to be edited

	/**
	 * Create the frame.
	 */
	public EditSongInfoFrame() {
		setTitle("Edit Song Information");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		initializeEditForm();
	}
	
	/**
	 * This method initializes the edit form panel and adds it to the content pane
	 */
	private void initializeEditForm() {
		editForm = new JPanel();
		editForm.setLayout(new MigLayout("", "[][][]", "[][][][][][][][][][]"));
		editForm.setBorder(BorderFactory.createTitledBorder("Edit song information"));
		
		//// Song Name Row ////
		songNameLabel = new JLabel("Song Name: ");
		songNameField = new JTextField(40);
		songNameField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if(songNameField.getText().length() >= 40) {
					e.consume();
				}
			}
		});
		editForm.add(songNameLabel, "cell 0 0"); // Add the labels and fields to the new row
		editForm.add(songNameField, "cell 1 0");
		
	
		//// Artist Name Row ////
		artistNameLabel = new JLabel("Artist Name: ");  // Create label for field
		artistNameField = new JTextField(40); // Initialize field reference	
		artistNameField.addKeyListener(new KeyAdapter() {      // To prevent errors from occurring during insertion into the database,
			public void keyTyped(KeyEvent e) {			   	   // Add a key listener that performs a check on the length of text entered,
				if(artistNameField.getText().length() >= 40) { // If limit defined is exceeded, consume every character entered thereafter
					e.consume();
				}
			}
		});
		editForm.add(artistNameLabel, "cell 0 1"); // Add the label to the cell position 0, 0
		editForm.add(artistNameField, "cell 1 1"); // Add the field to the cell right next to it
		
		//// Album Name Row ////
		albumNameLabel = new JLabel("Album Name: ");
		albumNameField = new JTextField(40);
		albumNameField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if(albumNameField.getText().length() >= 40) {
					e.consume();
				}
			}
		});
		editForm.add(albumNameLabel, "cell 0 2");
		editForm.add(albumNameField, "cell 1 2");
		
		//// Genre Row ////
		genreLabel = new JLabel("Genre: ");
		genreField = new JTextField(10);
		genreField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if(genreField.getText().length() >= 10) {
					e.consume();
				}
			}
		});
		editForm.add(genreLabel, "cell 0 3");
		editForm.add(genreField, "cell 1 3");
		
		//// Storage Media Row ////
		storageMediaLabel = new JLabel("Storage Media: ");
		storageMediaField = new JTextField(10);
		storageMediaField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if(storageMediaField.getText().length() >= 10) {
					e.consume();
				}
			}
		});
		editForm.add(storageMediaLabel, "cell 0 4");
		editForm.add(storageMediaField, "cell 1 4");	
		
		//// Song Remarks Row ////
		songRemarksLabel = new JLabel("Song Remarks:");
		songRemarksField = new JTextField(30);
		songRemarksField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if(songRemarksField.getText().length() >= 30) {
					e.consume();
				}
			}
		});
		editForm.add(songRemarksLabel, "cell 0 5");
		editForm.add(songRemarksField, "cell 1 5");
		
		//// Song Duration Row ////
		songDurationLabel = new JLabel("Song Duration: ");
		songDurationField = new JTextField(10);
		songDurationField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if(songDurationField.getText().length() >= 10) {
					e.consume();
				}
			}
		});
		editForm.add(songDurationLabel, "cell 0 6");
		editForm.add(songDurationField, "cell 1 6");
		
		initializeFormButtons();
		
		contentPane.add(editForm);	
		
	}
	
	/**
	 * This method initializes the buttons and places them on the form
	 */
	private void initializeFormButtons() {
		
		JLabel separator = new JLabel(" ");
		editForm.add(separator, "cell 0 7");
		
		//// Save Button ////
		btnSave = new JButton("Save");
		btnSave.setPreferredSize(new Dimension(71, 25));
		editForm.add(btnSave, "cell 2 8");
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// Save the contents of the edit form and then return to view collection frame
				saveFormData(); 
				dispose();
				ViewCollectionScreen vcs = new ViewCollectionScreen();
				vcs.setLocationRelativeTo(null);
				vcs.setVisible(true);
			}
		});
		
		//// Cancel Button ////
		btnCancel = new JButton("Cancel");
		btnCancel.setPreferredSize(new Dimension(72, 25));
		editForm.add(btnCancel, "cell 2 9");
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// Return to view collection frame
				dispose();
				ViewCollectionScreen vcs = new ViewCollectionScreen();
				vcs.setLocationRelativeTo(null);
				vcs.setVisible(true);
			}
		});
		
	}
	
	/**
	 * This method updates the database with the contents of the edit form
	 */
	private void saveFormData() {
		
		// Get today's date and store it an SQL compatible formats
		Calendar calendar = new GregorianCalendar();
		java.sql.Date dateAdded = new java.sql.Date(calendar.getTimeInMillis());
		
		int hours = 0, minutes = 0, seconds = 0;
		
		String songDuration = songDurationField.getText();
		
		// Validate the form fields
		if(songDuration.equals("")) {
			JOptionPane.showMessageDialog(contentPane, "If you wish to enter a song duration, please enter it in the format 00h00m00s");
			songDurationField.setText("0");
		} else {
			hours = Integer.parseInt(songDuration.toString()
					.substring(0, songDuration.toString().indexOf("h")));
			minutes = Integer.parseInt(songDuration.toString()
					.substring(songDuration.toString().indexOf("h")+1, songDuration.toString().lastIndexOf("m")));
			seconds = Integer.parseInt(songDuration.toString()
					.substring(songDuration.toString().indexOf("m")+1, songDuration.toString().lastIndexOf("s")));
		}
		
		// Create a song object to store data from text fields
		Song song = new Song.SongBuilder(0,
										 songNameField.getText(),
										 artistNameField.getText(),
										 genreField.getText(),
										 dateAdded)
							.albumName(albumNameField.getText())
							.storageMedia(storageMediaField.getText())
							.songRemarks(songRemarksField.getText())
							.songDuration(hours, minutes, seconds).build();
		
		// Create the query that will update the corresponding row in the database
		String updateQuery = "UPDATE music_collection SET " +
							"songName" + " = '" + song.getSongName() + "', "
							+ "artistName" + " = '" + song.getArtistName() + "', "
							+ "albumName" + " = '" + song.getAlbumName() + "', "
							+ "genre" + " = '" + song.getGenre() + "',"
							+ "storageMedia" + " = '" + song.getStorageMedia() + "',"
							+ "songRemarks" + " = '" + song.getSongRemarks() + "', "
							+ "songDuration" + " = " + song.getSongDuration() 
							+ " WHERE SONGID = " + this.songID ;
		
		// Connect to the database and execute the query
		ViewCollectionScreen.updateDB(updateQuery);
	}
	
	/**
	 * This method sets the default text of the text fields to be the values passed in from selected table row
	 * @param songName
	 * @param artistName
	 * @param albumName
	 * @param genre
	 * @param storageMedia
	 * @param songRemarks
	 * @param songDuration
	 */
	public void setFormFields(int songID,
							  String songName,
							  String artistName,
							  String albumName,
							  String genre,
							  String storageMedia,
							  String songRemarks,
							  String songDuration) 
	{
		this.songID = songID;
		this.songNameField.setText(songName);
		this.artistNameField.setText(artistName);
		this.albumNameField.setText(albumName);
		this.genreField.setText(genre);
		this.storageMediaField.setText(storageMedia);
		this.songRemarksField.setText(songRemarks);
		this.songDurationField.setText(songDuration);
	}

}
