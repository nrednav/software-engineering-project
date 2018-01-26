package vr00098_project_com1028;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import java.awt.Color;
import java.awt.Cursor;

/**
 * This class represents the JFrame containing the help facility 
 * @author Vandern Rodrigues
 *
 */
public class HelpFacility extends JFrame {
	
	private static final long serialVersionUID = 1555592805376227527L;
	
	// References to panels and top hyperlink as well as the home button
	private JPanel contentPane, helpFacility;
	private JLabel returnToTop, returnHome;
	private JScrollPane scrollPane;

	/**
	 * Create the frame.
	 */
	public HelpFacility() {
		setTitle("Help Facility");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1072, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		initializeHelpFacility(); // Initialize the entire help facility into the frame
	}
	
	/**
	 * This method initializes the entire help facility with all the relevant sections
	 */
	private void initializeHelpFacility() {
		helpFacility = new JPanel(new MigLayout());
		helpFacility.setBackground(new Color(240, 248, 255));
		scrollPane = new JScrollPane(helpFacility);
		
		// Initialize the home button
		initializeHomeButton(); 
		
		// Initialize and place all the sections into the help facility
		initializeHeadingSection();
		initializeFirstSection();
		initializeSecondSection();
		initializeThirdSection();
		initializeFourthSection();
		initializeFifthSection();
		initializeSixthSection();
		initializeSeventhSection();
	
		contentPane.add(scrollPane);
		

	}
	
	/**
	 * This method initializes the 'Home' label to direct the user back to the main menu
	 */
	private void initializeHomeButton() {
		
		returnHome = new JLabel("<html><u>Home</u></html>");
		returnHome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		returnHome.setForeground(Color.BLUE);
		returnHome.setCursor(new Cursor(Cursor.HAND_CURSOR));
		returnHome.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				MainMenu menu = new MainMenu();
				menu.setLocationRelativeTo(null);
				menu.setVisible(true);
			}
		});
		
		helpFacility.add(returnHome, "cell 1 0");
	}
	
	/**
	 * This method initializes the header section of the page
	 */
	private void initializeHeadingSection() {
		
		// Heading paragraphs
		JLabel separator = new JLabel(" ");
		separator.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel welcomeLabel = new JLabel("Hi! Welcome to the help facility.");
		welcomeLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JLabel postWelcomeLabel = new JLabel("This was written to help you navigate around the software.");
		postWelcomeLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel preTOCLabel = new JLabel("Below you shall find a table of contents corresponding to each of the features provided by this software.");
		preTOCLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		// Table of contents
		JLabel tocLabel = new JLabel("Table of Contents:");
		tocLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		// Using HTML to set this label as a list item
		JLabel addToCollectionLabel = new JLabel("<html><ul><li><u>Adding to the music collection</u></li></ul></html>");
		addToCollectionLabel.setForeground(Color.BLUE); // Setting the default color of the list item
		addToCollectionLabel.setFont(new Font("Tahoma", Font.PLAIN, 14)); // Setting the font of list item
		addToCollectionLabel.setCursor(new Cursor(Cursor.HAND_CURSOR)); // When the list item is hovered, its cursor will be the hand cursor
		addToCollectionLabel.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				scrollPane.getVerticalScrollBar().setValue(400); // When clicked, it will scroll to the defined position
			}
		});
		
		JLabel addSongLabel = new JLabel("<html><ul><li><u>Adding a song to your collection</u></li></ul></html>");
		addSongLabel.setForeground(Color.BLUE);
		addSongLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addSongLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		addSongLabel.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				scrollPane.getVerticalScrollBar().setValue(900);
			}
		});
		
		JLabel addAlbumLabel = new JLabel("<html><ul><li><u>Adding an album to your collection</u></li></ul></html>");
		addAlbumLabel.setForeground(Color.BLUE);
		addAlbumLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addAlbumLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		addAlbumLabel.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				scrollPane.getVerticalScrollBar().setValue(1500);
			}
		});
		
		JLabel viewCollectionLabel = new JLabel("<html><ul><li><u>Viewing your collection</u></li></ul></html>");
		viewCollectionLabel.setForeground(Color.BLUE);
		viewCollectionLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		viewCollectionLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		viewCollectionLabel.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				scrollPane.getVerticalScrollBar().setValue(2200);
			}
		});
		
		JLabel searchCollectionLabel = new JLabel("<html><ul><li><u>Searching through your music collection</u></li></ul></html>");
		searchCollectionLabel.setForeground(Color.BLUE);
		searchCollectionLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		searchCollectionLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		searchCollectionLabel.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				scrollPane.getVerticalScrollBar().setValue(2575);
			}
		});
		
		JLabel editSongLabel = new JLabel("<html><ul><li><u>Editing information about your songs</u></li></ul></html>");
		editSongLabel.setForeground(Color.BLUE);
		editSongLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		editSongLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		editSongLabel.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				scrollPane.getVerticalScrollBar().setValue(3190);
			}
		});
		
		JLabel removeSongLabel = new JLabel("<html><ul><li><u>Removing songs from your collection</u></li></ul></html>");
		removeSongLabel.setForeground(Color.BLUE);
		removeSongLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		removeSongLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		removeSongLabel.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				scrollPane.getVerticalScrollBar().setValue(4185);
			}
		});
		
		// Add all the opening paragraphs and the table of contents to the main panel
		helpFacility.add(welcomeLabel, "cell 0 0");
		helpFacility.add(postWelcomeLabel, "cell 0 1");
		helpFacility.add(preTOCLabel, "cell 0 2");
		helpFacility.add(separator, "cell 0 3");
		helpFacility.add(tocLabel, "cell 0 4");
		helpFacility.add(addToCollectionLabel, "cell 0 5");
		helpFacility.add(addSongLabel, "cell 0 6");
		helpFacility.add(addAlbumLabel, "cell 0 7");
		helpFacility.add(viewCollectionLabel, "cell 0 8");
		helpFacility.add(searchCollectionLabel, "cell 0 9");
		helpFacility.add(editSongLabel, "cell 0 10");
		helpFacility.add(removeSongLabel, "cell 0 11");
	}

	/**
	 * This method initializes the first section detailing how to add to the music collection
	 */
	private void initializeFirstSection() {
		
		JLabel addToCollectionHeading = new JLabel("<html><br><u>1. Adding to your music collection</u></html>");
		addToCollectionHeading.setFont(new Font("Tahoma", Font.BOLD, 18));
		helpFacility.add(addToCollectionHeading, "cell 0 14");
		
		JLabel addToCollectionText = new JLabel("<html>To add to your music collection,"
				+ " simply click the 'Add to collection' button from the main menu."
				+ "<br> You will then be prompted with a new screen asking you whether you wish to add a single song or an album of songs.</html>");
		addToCollectionText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		helpFacility.add(addToCollectionText, "cell 0 15");
		
		JLabel mmImage = new JLabel("");
		
		/*
		 * The images in the help facility are accessed from within the project folder itself
		 * 
		 * An ImageIcon contains an image object which can be accessed via .getImage(),
		 * Using that allows for resizing the image after which it may be re-assigned back to an ImageIcon object
		 * 
		 * So the overall process follows as -> get Image -> resize it -> Assign image back to ImageIcon
		 * 
		 * This is repeated for every image imported to the help facility
		 */
		mmImage.setIcon(new ImageIcon(new ImageIcon("img/MainMenu.png")
				.getImage().getScaledInstance(400, 400, Image.SCALE_DEFAULT)));
		helpFacility.add(mmImage, "flowx, cell 0 16");	
		
		JLabel soapImage = new JLabel("");
		soapImage.setIcon(new ImageIcon(new ImageIcon("img/SongOrAlbumPicker.png")
				.getImage().getScaledInstance(400, 400, Image.SCALE_DEFAULT)));
		helpFacility.add(soapImage, "cell 0 16");
		
	}

	/**
	 * This method initializes the second section detailing how to add a song to the music collection
	 */
	private void initializeSecondSection() {
		
		JLabel addSongHeading = new JLabel("<html><br><u>2. Adding a song</u></html>");
		addSongHeading.setFont(new Font("Tahoma", Font.BOLD, 18));
		helpFacility.add(addSongHeading, "cell 0 18");
		
		setTopHyperlink(returnToTop); // This is the first usage of the method that sets the top hyperlink
		helpFacility.add(returnToTop, "cell 1 19");
		
		JLabel addSongText = new JLabel("<html> To add a single song to your collection,"
				+ " click the 'Add Song' button and you will be directed to a new window that contains a form." +
				"<br> Simply fill in the relevant details and click the 'Submit' button to save the information. <br>" +
				"If you change your mind, click the 'Cancel' button to return to the main menu.");
		addSongText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		helpFacility.add(addSongText, "cell 0 19");
		
		JLabel songFormImage = new JLabel("");
		songFormImage.setIcon(new ImageIcon(new ImageIcon("img/SongInformationForm.png")
				.getImage().getScaledInstance(700, 350, Image.SCALE_DEFAULT)));
		helpFacility.add(songFormImage, "cell 0 20");
		
	}
	
	/**
	 * This method initializes the third section detailing how to add an album of songs to the music collection
	 */
	private void initializeThirdSection() {
		
		JLabel addAlbumHeading = new JLabel("<html><br><u>3. Adding an album</u></html>");
		addAlbumHeading.setFont(new Font("Tahoma", Font.BOLD, 18));
		helpFacility.add(addAlbumHeading, "cell 0 22");
		
		setTopHyperlink(returnToTop);
		helpFacility.add(returnToTop, "cell 1 22");
		
		JLabel addAlbumText = new JLabel("<html>To add an album of songs to your collection all at once,"
			+ " click the 'Add an Album' button. <br> You will be prompted with a window that asks you to specify " 
			+ "how many songs will be present in the album. Enter the number of songs and click OK. <br> This will take you to" 
			+ " a new window that contains a form with fields to enter the common album information"  
			+ "as well as song-specific information. <br>" 
			+ "Enter the information and click 'Submit' to save the contents of the form.</html>");
		addAlbumText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		helpFacility.add(addAlbumText, "cell 0 23");
		
		JLabel albumSizeImage = new JLabel("");
		albumSizeImage.setIcon(new ImageIcon(new ImageIcon("img/AlbumSizeInputDialog.png")
				.getImage().getScaledInstance(420, 200, Image.SCALE_DEFAULT)));
		helpFacility.add(albumSizeImage, "cell 0 24");
	
		JLabel albumFormImage = new JLabel("");
		albumFormImage.setIcon(new ImageIcon(new ImageIcon("img/AlbumForm.png")
				.getImage().getScaledInstance(800, 400, Image.SCALE_DEFAULT)));
		helpFacility.add(albumFormImage, "cell 0 25");
		
	}
	
	/**
	 * This method initializes the fourth section detailing how to view your music collection
	 */
	private void initializeFourthSection() {
		
		JLabel viewCollectionHeading = new JLabel("<html><br><u>4. Viewing your music collection</u></html>");
		viewCollectionHeading.setFont(new Font("Tahoma", Font.BOLD, 18));
		helpFacility.add(viewCollectionHeading, "cell 0 26");
		
		setTopHyperlink(returnToTop);
		helpFacility.add(returnToTop, "cell 1 27");
		
		JLabel viewCollectionText = new JLabel("<html>To view your music collection,"
				+ " simply click on the 'View Collection' button from the main menu.<br>" 
				+ " You will then be greeted with a table displaying the song(s) you have added to your collection.</html>");
		viewCollectionText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		helpFacility.add(viewCollectionText, "cell 0 27");
		
		JLabel viewTableImage = new JLabel("");
		viewTableImage.setIcon(new ImageIcon(new ImageIcon("img/ViewCollectionTable.png")
				.getImage().getScaledInstance(800, 400, Image.SCALE_DEFAULT)));
		helpFacility.add(viewTableImage, "cell 0 28");
	}
	
	/**
	 * This method initializes the fifth section detailing how to search through your music collection
	 */
	private void initializeFifthSection() {
		
		JLabel searchCollectionHeading = new JLabel("<html><br><u>5. Searching through music collection</u></html>");
		searchCollectionHeading.setFont(new Font("Tahoma", Font.BOLD, 18));
		helpFacility.add(searchCollectionHeading, "cell 0 29");
		
		JLabel searchCollectionText = new JLabel("<html>You can search through your music collection via the search field at the top.<br>" 
				+ " Simply enter any piece of information about a song you've added and the table will filter through" 
				+ " the collection to find a match.<br> If there is no match found the table will display nothing.</html>");
		searchCollectionText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		helpFacility.add(searchCollectionText, "cell 0 30");
		
		JLabel preSearchImage = new JLabel("");
		preSearchImage.setIcon(new ImageIcon(new ImageIcon("img/TablePreSearch.png")
				.getImage().getScaledInstance(750, 250, Image.SCALE_DEFAULT)));
		helpFacility.add(preSearchImage, "cell 0 31");
		
		JLabel postSearchImage = new JLabel("");
		postSearchImage.setIcon(new ImageIcon(new ImageIcon("img/TablePostSearch.png")
				.getImage().getScaledInstance(750, 250, Image.SCALE_DEFAULT)));
		helpFacility.add(postSearchImage, "cell 0 32");
			
	}
	
	/**
	 * This method initializes the sixth section detailing how to edit a songs information
	 */
	private void initializeSixthSection() {
		
		JLabel editSongHeading = new JLabel("<html><br><u>6. Editing song information</u></html>");
		editSongHeading.setFont(new Font("Tahoma", Font.BOLD, 18));
		helpFacility.add(editSongHeading, "cell 0 33");
		
		setTopHyperlink(returnToTop);
		helpFacility.add(returnToTop, "cell 1 33");
		
		JLabel editSongText = new JLabel("<html>To edit a song's information, select a row in the table and click 'Edit'.<br>"
				+ " You will then be directed to a new window containing a form with some fields already filled in. <br>"
				+ " So make any changes or additions and then press Enter to save them.</html>");
		editSongText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		helpFacility.add(editSongText, "cell 0 34");
		
		JLabel preEditImage = new JLabel("");
		preEditImage.setIcon(new ImageIcon(new ImageIcon("img/TablePreEdit.png")
				.getImage().getScaledInstance(900, 300, Image.SCALE_DEFAULT)));
		helpFacility.add(preEditImage, "flowx, cell 0 35");
		
		JLabel editImage = new JLabel("");
		editImage.setIcon(new ImageIcon(new ImageIcon("img/TableDuringEdit.png")
				.getImage().getScaledInstance(700, 500, Image.SCALE_DEFAULT)));
		helpFacility.add(editImage, "cell 0 36");
		
		JLabel postEditImage = new JLabel("");
		postEditImage.setIcon(new ImageIcon(new ImageIcon("img/TablePostEdit.png")
				.getImage().getScaledInstance(900, 300, Image.SCALE_DEFAULT)));
		helpFacility.add(postEditImage, "cell 0 37");
	}
	
	/**
	 * This method initializes the seventh section detailing how to remove song(s) from the music collection
	 */
	private void initializeSeventhSection() {
		
		JLabel removeSongHeading = new JLabel("<html><br><u>6. Removing song(s) from your music collection</u></html>");
		removeSongHeading.setFont(new Font("Tahoma", Font.BOLD, 18));
		helpFacility.add(removeSongHeading, "cell 0 38");
		
		setTopHyperlink(returnToTop);
		helpFacility.add(returnToTop, "cell 1 38");
		
		JLabel removeSongText = new JLabel("<html>You have the option of removing a single song or multiple songs at once.<br>"
				+ " To remove a single song, simply select it and click 'Remove'. <br>"
				+ " To remove multiple songs, select them all and click 'Remove'. </html>");
		removeSongText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		helpFacility.add(removeSongText, "cell 0 39");
		
		JLabel preRemoveImage = new JLabel("");
		preRemoveImage.setIcon(new ImageIcon(new ImageIcon("img/TablePreRemove.png")
				.getImage().getScaledInstance(950, 400, Image.SCALE_SMOOTH)));
		helpFacility.add(preRemoveImage, "cell 0 40");
		
		JLabel postRemoveImage = new JLabel("");
		postRemoveImage.setIcon(new ImageIcon(new ImageIcon("img/TablePostRemove.png")
				.getImage().getScaledInstance(950, 400, Image.SCALE_SMOOTH)));
		helpFacility.add(postRemoveImage, "cell 0 41");
	}
	
	/**
	 * This method initializes the 'Top' label to act as a hyperlink to the top of the page 
	 */
	private void initializeTopHyperLink() {
		returnToTop = new JLabel("<html><u>Top</u></html>");
		returnToTop.setFont(new Font("Tahoma", Font.PLAIN, 14));
		returnToTop.setForeground(Color.BLUE);
		returnToTop.setCursor(new Cursor(Cursor.HAND_CURSOR));
		returnToTop.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				scrollPane.getVerticalScrollBar().setValue(0); // All this method does is reset the vertical scrollbars position back to 0
															   // and in doing so it serves as a bookmark to the top of the page
			}
		});
	}
	
	/**
	 * This method exists solely to re-initialize the 'Top' hyperlink
	 * @param label
	 */
	private void setTopHyperlink(JLabel label) {
		
		initializeTopHyperLink(); // Initialize the top hyperlink
		label = new JLabel("<html><u>Top</u></html>"); // Change the variable reference each time to re-use the features definition
		returnToTop.setFont(new Font("Tahoma", Font.PLAIN, 14));
		returnToTop.setForeground(Color.BLUE);
		returnToTop.setCursor(new Cursor(Cursor.HAND_CURSOR));
		returnToTop.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				scrollPane.getVerticalScrollBar().setValue(0);
			}
		});
	}
	
} 
