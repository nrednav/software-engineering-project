package vr00098_project_com1028;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.GridLayout;

/**
 * This class represents the main menu frame
 * @author Vandern Rodrigues
 *
 */
public class MainMenu extends JFrame {

	private static final long serialVersionUID = -36478935984385779L;
	
	private JPanel contentPane; // Reference to the frames main layout
	private JButton btnAddToCollection, btnHelpFacility, btnViewCollection; // References the buttons in the main panel
	
	private MusicDBHandler handler; // Reference to handler that will be used to obtain a connection to the database
	
	private boolean tableExists; // Flag variable to indicate if table exists in database or not

	/**
	 * Launch the frame into the application window
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the main menu frame containing 3 buttons with links to other frames
	 */
	public MainMenu() {
		setTitle("Main Menu");
		
		// Panel properties
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new GridLayout(0, 1, 0, 0)); 
		setContentPane(contentPane);

		
		//// Add To Collection Button ////
		btnAddToCollection = new JButton("Add to Collection");
		btnAddToCollection.setFont(new Font("Tahoma", Font.PLAIN, 46));
		btnAddToCollection.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				dispose();
				SongOrAlbumPicker picker = new SongOrAlbumPicker();
				picker.setLocationRelativeTo(null);
				picker.setVisible(true);

			}
		});
		
		//// Help Facility Button ////
		btnHelpFacility = new JButton("Help Facility");
		btnHelpFacility.setFont(new Font("Tahoma", Font.PLAIN, 46));
		btnHelpFacility.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				HelpFacility hf = new HelpFacility();
				hf.setLocationRelativeTo(null);
				hf.setVisible(true);
			}
		});
		
		//// View Collection Button ////
		btnViewCollection = new JButton("View collection");
		btnViewCollection.setFont(new Font("Tahoma", Font.PLAIN, 46));
		btnViewCollection.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// Before proceeding to the frame, check to see if the table is empty
				if(!doesTableExist()) {
					
					// If it is, display a message dialog to the user indicating so
					JOptionPane.showMessageDialog(contentPane, "There is no music collection available to view.");
				} else {
					
					// Otherwise, proceed to the frame
					dispose();
					ViewCollectionScreen vcs = new ViewCollectionScreen();
					vcs.setLocationRelativeTo(null);
					vcs.setVisible(true);
				}
			}
		});
		
		// Add the buttons to the panel
		contentPane.add(btnAddToCollection);
		contentPane.add(btnViewCollection);
		contentPane.add(btnHelpFacility);
	}
	
	/**
	 * This method checks the music_collection table in the database to see if it is empty and returns the result
	 * @return
	 */
	private boolean doesTableExist() {
		
		handler = new MusicDBHandler();
		
		tableExists = false; // This variable will indicate if the table exists the database or not
		
		try (
			Connection connection = handler.connectToDB();
			Statement statement = connection.createStatement();
			ResultSet rSet = connection.getMetaData().getTables(null, null, "MUSIC_COLLECTION", null);
		) {
			
			// For every table found, store its TABLE_NAME and check if it is equal to "MUSIC_COLLECTION"
			while(rSet.next()) {
				
				String tableName = rSet.getString("TABLE_NAME");
				
				// If a match is made, then set the flag variable to true
				if(tableName.equals("MUSIC_COLLECTION")) {
					tableExists = true;
				} 
			}
		
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return tableExists;
	}
}
