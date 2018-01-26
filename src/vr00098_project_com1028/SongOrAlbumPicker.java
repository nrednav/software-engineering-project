package vr00098_project_com1028;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

/**
 * This class represents the frame displaying three buttons associated with adding a song, album or return to the home screen
 * @author Vandern Rodrigues
 *
 */
public class SongOrAlbumPicker extends JFrame {

	private static final long serialVersionUID = -1157450803880884211L;
	
	private JPanel contentPane; // Reference to main panel
	private JPanel buttonPane; // Reference to button panel nested within main panel
	
	private JButton btnAddAlbum, btnAddSong, btnHome; // References to buttons to be placed in the button panel
	
	private static int albumSize; // Static reference to number input by the user, to be accessed by the album info form

	/**
	 * Create the frame.
	 */
	public SongOrAlbumPicker() {
		setTitle("Form picker");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 384, 384);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		createButtonPane(); // Create and initialize the button panel
		initializeButtons(); // Initialize the buttons and place them inside the button panel
	}
	
	/**
	 * This method creates the button panel
	 */
	private void createButtonPane() {
		buttonPane = new JPanel();
		buttonPane.setLayout(new BorderLayout());
		buttonPane.setPreferredSize(new Dimension(300, 300));
		buttonPane.setBorder(BorderFactory.createLoweredBevelBorder());
		contentPane.add(buttonPane);
	}
	
	/**
	 * This method initializes the buttons with action listeners to redirect to the relevant frames
	 */
	private void initializeButtons() {
		
		// Initialize the buttons and resize them
		btnAddAlbum = new JButton("Add an album");
		btnAddAlbum.setPreferredSize(new Dimension(111, 100));
		
		btnAddSong = new JButton("Add a song");
		btnAddSong.setPreferredSize(new Dimension(97, 100));
		
		btnHome = new JButton("Home");
		btnHome.setPreferredSize(new Dimension(97, 100));
		
		// Add the buttons to the button panel
		buttonPane.add(btnHome, BorderLayout.SOUTH);
		buttonPane.add(btnAddAlbum, BorderLayout.CENTER);
		buttonPane.add(btnAddSong, BorderLayout.NORTH);
		
		// Button linked to the Song form
		btnAddSong.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				SongInfoForm form = new SongInfoForm();
				form.setLocationRelativeTo(null);
				form.setVisible(true);
			}
		});
		
		// Button linked to the Album form
		btnAddAlbum.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String result = JOptionPane.showInputDialog("Please enter the number of songs to be added to the album.");
				
				// Validate that data is entered into the input dialog
				if((result != null) && (result.length() > 0)) {
					
					// Validate that the data entered is numeric
					if(result.matches("[0-9]+")) {
						
						// Store the result in a static variable to be accessed by Album info form
						albumSize = Integer.parseInt(result);
						
						// Restrict the number of songs that can be added to be between 1 and 16
						if((albumSize > 0) && (albumSize <= 16)) {
							dispose();
							AlbumInfoForm albumInfoForm = new AlbumInfoForm();
							albumInfoForm.setLocationRelativeTo(null);
							albumInfoForm.setVisible(true);
						} else {
							JOptionPane.showMessageDialog(contentPane, "Sorry the number you have entered is too large.");
						}

					} else {
						JOptionPane.showMessageDialog(contentPane, "Please enter a valid number.");
					}

				}
			}
		});
		
		// Button linked to main menu
		btnHome.addActionListener(new ActionListener() {
			
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
	 * This method provides static access to the album size entered by the user
	 * @return
	 */
	public static int getAlbumSize() {
		return albumSize;
	}

}
