package vr00098_project_com1028;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.RowFilter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * This class represents the View Collection frame that contains a table showing all the songs in the music collection
 * as well as ways to search through the collection, edit information about the songs and remove them
 * @author Vandern Rodrigues
 *
 */
public class ViewCollectionScreen extends JFrame {

	private static final long serialVersionUID = -1339675616550420323L;
	
	private JPanel contentPane; // Reference to main panel
	
	private JTable musicTable; // Reference to the table in the main panel
	private DefaultTableModel dm; // Reference to a default table model to be used by the music table
	
	private JToolBar toolBar; // Reference to the toolBar
	private JButton btnEdit, btnRemove, btnHome; // Reference to the buttons on the toolBar
	private JTextField searchField; // Reference to the search field on the toolBar
	
	private MusicCollection mc = new MusicCollection(); // Creating an instance of the MusicCollection

	private int hours = 0, minutes = 0, seconds = 0; // Song duration fields to be used when getting the songs duration from the database,
													 // reverting the song duration and populating the table with the reversion
	
	private String[] columnNames = {"SongID", "Song Name", "Artist Name", // Array of column names for the table
									"Album Name", "Genre",
									"Storage Media", "Song Remarks",
									"Song Duration", "Date Added"};

	/**
	 * Create the frame and populate it with a table and toolBar
	 */
	public ViewCollectionScreen() {
		setTitle("View Collection");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 768);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		populateTable(); // Initialize and populate the table
		initializeToolbar(); // Initialize the toolBar into the panel
		initializeButtons(); // Initialize the buttons and place them in the toolBar
	}
	
	/**
	 * This method gets the songs from the database and stores them in the musicCollection ArrayList
	 */
	public void getAllSongs() {
		
		MusicDBHandler handler = new MusicDBHandler(); // Instantiate a handler to obtain a connection to the database
		String sql_getSongs = "select * from music_collection"; // Create the query to get all the data from the table
		
		// Try-with-resources automatically closes the connection, statement and resultSet
		try(
			Connection connection = handler.connectToDB();
			Statement statement = connection.createStatement();	
			ResultSet resultSet = statement.executeQuery(sql_getSongs);
		) {
			
			// While the resultSet cursor points to a new row in its data table
			while(resultSet.next()) {
				
				// Get the Integer song duration and revert it back to hours, minutes and seconds
				revertSongDuration(resultSet.getInt(8));
				
				// Instantiate a song object to store the rest of the information retrieved
				Song song = new Song.SongBuilder(resultSet.getInt(1), 
												 resultSet.getString(2), 
												 resultSet.getString(3), 
												 resultSet.getString(5), 
												 resultSet.getDate(9))
						.albumName(resultSet.getString(4))
						.storageMedia(resultSet.getString(6))
						.songRemarks(resultSet.getString(7))
						.songDuration(hours, minutes, seconds).build();
				
				// Add the song to the ArrayList, and repeat the above for every row found in the resultSet table
				mc.addSong(song);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	/**
	 * This method handles initializing the table and populating it with the data retrieved from the database
	 */
	public void populateTable() {
		
		// Create a default table model and assign it to the music table
		dm = new DefaultTableModel(); 
		musicTable = new JTable(dm);
		musicTable.setFillsViewportHeight(true); // Make the table fill its parent container
		musicTable.setDefaultEditor(Object.class, null); // Set the default editor as null, thereby disabling the ability to edit cells
		musicTable.getTableHeader().setReorderingAllowed(false); // Disable re-ordering the columns
		
		// Add the table to a scroll pane for support when the data in the table exceeds the parent containers height
		JScrollPane scrollPane = new JScrollPane(musicTable);
		contentPane.add(scrollPane);
		
		// Get the column names for the table
		for(int i = 0; i < columnNames.length; i++) {
			dm.addColumn(columnNames[i]);
		}
		
		getAllSongs(); // Get the information from the database
		
		ArrayList<Song> musicCollection = mc.getMusicCollection(); // Get the ArrayList from the music collection object
		
		Object[] rowData  = new Object[9]; // Create an object array to store the data to be passed into a row in the music table
									// Using an object array handles various data types retrieved from the table for example:- Date Added
		
		// For each song in the ArrayList, get its data and store it in the object array
		for(int i = 0; i < musicCollection.size(); i++) {
			
			int tempSongDuration = musicCollection.get(i).getSongDuration();
			revertSongDuration(tempSongDuration);
			
			rowData[0] = musicCollection.get(i).getSongID();
			rowData[1] = musicCollection.get(i).getSongName();
			rowData[2] = musicCollection.get(i).getArtistName();
			rowData[3] = musicCollection.get(i).getAlbumName();
			rowData[4] = musicCollection.get(i).getGenre();
			rowData[5] = musicCollection.get(i).getStorageMedia();
			rowData[6] = musicCollection.get(i).getSongRemarks();
			
			// Pass the song duration in as a formatted string
			rowData[7] = hours + "h" + minutes + "m" + seconds + "s";
			rowData[8] = musicCollection.get(i).getDateAdded();
			
			// Add the row to the default model of the table
			dm.addRow(rowData);
		}
		
		// Remove the songID column from the table view
		TableColumnModel tableColumnModel = musicTable.getColumnModel();
		tableColumnModel.removeColumn(tableColumnModel.getColumn(0));
	}
	
	/**
	 * This method handles reverting the songs duration back from a total number of seconds to hours, minutes seconds
	 * @param songDuration
	 */
	public void revertSongDuration(int songDuration) {
			
		// If the song duration is less than 3600 seconds, the hours is automatically 0
		if(songDuration < 3600) {
			hours = 0;
			minutes = songDuration/60; // The minutes are determined through dividing the song duration by 60
			
			// If there is a remainder after dividing to find the minutes, then subtract the hours and minutes from the total duration
			if((songDuration%60) != 0) { 
				seconds = songDuration - hours*3600 - minutes*60; // The remainder from subtracting is the number of seconds
			} else {
				seconds = 0; // If there is no remainder, then there are no additional seconds
			}
			
			// If the song duration is equal to 3600 seconds, the number of hours is automatically 1
		} else if (songDuration == 3600) {
			hours = 1;
			minutes = 0;
			seconds = 0;
			
			// If the song duration is greater than 3600 seconds, the number of hours is found after dividing by 3600
		} else if (songDuration > 3600) {
			
			hours = songDuration/3600;
			double hoursPrecise = (songDuration/3600.00);
			
			// Subtract the hours in seconds from the original song duration, then divide by 60 to get the number of minutes
			double minutesPrecise = (60*(hoursPrecise - hours));
			minutes = (songDuration - hours*3600)/60;
			
			// Use the precise values of the hours and minutes to determine the leftover number of seconds
			seconds = (int) (60*(minutesPrecise - minutes));
		} 
	}
	
	/**
	 * This method initializes the toolBar with a search field, edit button, remove button and home button
	 */
	public void initializeToolbar() {
		
		toolBar = new JToolBar();
		toolBar.setFloatable(false); // Disable the toolBar from being movable
		contentPane.add(toolBar, BorderLayout.NORTH); // Add the toolBar to the top of the layout
		
		searchField = new JTextField(40); // Initialize the search field to a 40 character limit
		searchField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String itemToSearch = searchField.getText(); // Get any text typed in the field
				filterMusicTable(itemToSearch);	// Pass the text into the table sorter method
			}
		});
		
		// Initialize the toolBar buttons
		btnEdit = new JButton("Edit");
		btnRemove = new JButton("Remove");
		btnHome = new JButton("Home");
		
		toolBar.add(searchField); // Add the components one-by-one with a separator in between them
		toolBar.addSeparator();
		toolBar.add(btnEdit);
		toolBar.addSeparator();
		toolBar.add(btnRemove);
		toolBar.addSeparator();
		toolBar.add(btnHome);
	}
	
	/**
	 * This method handles the tables filtering and sorting capabilities
	 * @param item
	 */
	private void filterMusicTable(String item) {
		
		// Instantiate a row sorter for the table model
		TableRowSorter<DefaultTableModel> tableRowSorter = new TableRowSorter<DefaultTableModel>(dm);
		
		// Add the sorter to the table
		musicTable.setRowSorter(tableRowSorter);
		
		// Add a Regex filter to help find a match between the item searched and the table contents
		tableRowSorter.setRowFilter(RowFilter.regexFilter(item));
	}

	/**
	 * This method simply combines the calls to initialize the three toolbar buttons
	 */
	public void initializeButtons() {
		
		// Initializing the home button to re-direct to the main menu
		btnHome.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				MainMenu menu = new MainMenu();
				menu.setLocationRelativeTo(null);
				menu.setVisible(true);
			}
		});
		
		initializeRemoveButton(); 

		initializeEditButton();
	}
	
	/**
	 * This method initializes the remove button
	 */
	private void initializeRemoveButton() {
		
		// Initializing the remove button to remove the selected rows(s) in the table
		btnRemove.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				// First perform a check to make sure the table is not empty
				if(musicTable.getModel().getRowCount() != 0) {
					
					// If it's not empty, perform a check to see if any row(s) are selected
					if(musicTable.getSelectedRows().length > 0) {
						
						// If any row(s) are selected, remove them
						removeSelectedRows(musicTable.getSelectedRows());
					} else {
						
						// Otherwise tell the user that there are no rows currently selected to delete
						JOptionPane.showMessageDialog(contentPane, "There are no items currently selected to remove.");
					}
					
					// If the table is empty, tell the user there are no items available to remove
				} else {
					JOptionPane.showMessageDialog(contentPane, "There are no items available to remove.");
				}
			}
		});
		
	}

	/**
	 * This method handles the underlying process involved in removing a row from the table and database.
	 * @param selectedRows
	 */
	private void removeSelectedRows(int[] selectedRows) {
		
		MusicDBHandler handler = new MusicDBHandler(); // Create a handler to obtain a connection to the database
		
		int removalConfirmationResult; // Create an Integer flag to store the result of the input dialog
		
		// If the user selected single or multiple rows to delete, ask them to confirm their decision
		if(selectedRows.length == 1) {
			removalConfirmationResult = JOptionPane.showConfirmDialog(contentPane, "Do you wish to delete this item?",
					 "Confirmation dialog", JOptionPane.YES_NO_OPTION);
		} else {
			removalConfirmationResult = JOptionPane.showConfirmDialog(contentPane, "Do you wish to delete these items?",
					 "Confirmation dialog", JOptionPane.YES_NO_OPTION);
		}

		// If they clicked Yes to confirm their decision
		if(removalConfirmationResult == JOptionPane.YES_OPTION) {
			
			// Sort the selected rows into ascending order
			Arrays.sort(selectedRows);
			
			// Then for each row in the sorted array starting from the last row
			for(int i = selectedRows.length - 1; i >= 0; i--) {
				
				// Get the row's song ID
				String selectedSongID = musicTable.getModel().getValueAt(selectedRows[i], 0).toString();
				
				// Create the delete query with the song ID
				String sql_removeSelectedRows = "delete from music_collection where songID = " + selectedSongID;
				
				try (
					Connection removalConnection = handler.connectToDB();
					Statement removalStatement = removalConnection.createStatement();
				) {
					
					// Execute the update into the database
					if(removalStatement.executeUpdate(sql_removeSelectedRows) > 0) {
						
						// If the search field was empty, remove the row from the model
						if(searchField.getText().isEmpty()) {
						dm.removeRow(selectedRows[i]);
						
						/* If the search field was not empty, then a conversion on the row's index would first need to be made
						 * As the tables row methods and selection model refer to the view and not the model
						 */
						} else {
							dm.removeRow(musicTable.convertRowIndexToModel(selectedRows[i]));
						}
						
						// After all is said and done, notify all listeners that the data table data was changed
						dm.fireTableDataChanged();
					}
						
				} catch (SQLException e1) {
					e1.printStackTrace();
				} 
			}
		}
		
	}
	
	/**
	 * This method initializes the edit button in the toolBar
	 */
	private void initializeEditButton() {
		
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// Get the currently selected row from the table
				int selectedRow = musicTable.getSelectedRow();
				
				// If no row is selected, tell the user to select one
				if(selectedRow == -1) {
					JOptionPane.showMessageDialog(contentPane, "Please select an item to edit.");
					
				// When a row is selected
				} else {
					
					// Obtain the songID 
					int selectedRowSongID = (int) dm.getValueAt(selectedRow, 0);
					
					// Dispose the view collection frame and open the window with the form to edit the song information
					dispose();
					EditSongInfoFrame editFrame = new EditSongInfoFrame();
					
					// Pass the column data for the selected row to the next window
					editFrame.setFormFields(selectedRowSongID,
											(String) dm.getValueAt(selectedRow, 1),
											(String) dm.getValueAt(selectedRow, 2),
											(String) dm.getValueAt(selectedRow, 3),
											(String) dm.getValueAt(selectedRow, 4),
											(String) dm.getValueAt(selectedRow, 5),
											(String) dm.getValueAt(selectedRow, 6),
											(String) dm.getValueAt(selectedRow, 7));
					editFrame.setLocationRelativeTo(null);
					editFrame.setVisible(true);
				}
			}
		});
		
	}

	/**
	 * This method handles connecting to the database and executing an Update query
	 * @param command, query
	 * @return result, success or failure of executing query
	 */
	public static int updateDB(String command) {
		
		MusicDBHandler handler = new MusicDBHandler(); // Create the handler to obtain a connection
		int result = 0; // Create a variable to store the result of executing the update query
		
		try(
			Connection connection = handler.connectToDB();
				Statement statement = connection.createStatement();
		) {
			
			result = statement.executeUpdate(command);
			
		} catch (SQLException se) {
			se.printStackTrace();
		}
		
		return result; // return the result of executing the query
	}
}
