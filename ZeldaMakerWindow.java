/**
 * @(#)GraphicalStuff.java
 *
 *
 * @author
 * @version 1.00 2012/3/19
 */
	import java.awt.*;
	import javax.swing.*;
	import java.awt.event.*;
	import java.io.*;
	import javax.swing.border.Border;
	import javax.swing.border.LineBorder;
	import javax.swing.plaf.metal.MetalIconFactory;
public class ZeldaMakerWindow extends JFrame {

	public ImageIcon[] tilePic = new ImageIcon[44]; //The pictures of the tiles in turn
	public JLabel[] mapTile = new JLabel[44]; //The labels for the selecting buttons
	public ImageIcon[] placedTImage = new ImageIcon[176]; //The images of the tiles that are placed
	public JLabel[] placedTile = new JLabel[176]; //The labels for the placed tiles
	public int[] walkable = new int[176]; //Whether the tile is one that you can step on, enter, etc.
	public int[] tileNum = new int[176]; //Which image the tile has
	public int selected = 3; //Which tile is selected to place
	public ZeldaMakerListener selector = new ZeldaMakerListener(this); //The listener which selects the tiles
	public ZeldaPlacementListener placer = new ZeldaPlacementListener(this); //The listener which places the tiles

	public ZeldaMakerWindow(String title) {
		super(title);
		this.setSize(300,800);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); //Basics

		BorderLayout border = new BorderLayout();
		JPanel tilePanel = new JPanel(new GridLayout(11, 4));
		JPanel placePanel = new JPanel(new GridLayout(11, 16)); //The grid for playing is 16x11.
		this.setLayout (border);
		this.add(tilePanel, BorderLayout.WEST);
		this.add(placePanel, BorderLayout.CENTER);

		for (int i = 0; i < 44; i++){ //Initializes all of the tile selecting buttons to their respective numbers
			tilePic[i] = new ImageIcon("Zelda tiles/" + i + ".gif");
			mapTile[i] = new JLabel(tilePic[i]);
			mapTile[i].addMouseListener(selector);
			tilePanel.add(mapTile[i]);
			mapTile[i].setBorder(LineBorder.createGrayLineBorder());
		}

		for (int i = 0; i < 176; i++){ //Initializes all of the placed tiles to their default values, to be edited
			placedTImage[i] = new ImageIcon("Zelda tiles/" + 3 + ".gif");
			placedTile[i] = new JLabel(placedTImage[i]);
			placedTile[i].addMouseListener(placer);
			placePanel.add(placedTile[i]);
			walkable[i] = 1;
			tileNum[i] = 3;
			placedTile[i].setBorder(BorderFactory.createLineBorder(Color.green));
		}

		this.pack();
		this.setVisible(true); //Final adjustments

	}

    public static void main(String[] args) {
		ZeldaMakerWindow theZeldaMakerWindow = new ZeldaMakerWindow("Zelda Tile Editor");
    }
}
