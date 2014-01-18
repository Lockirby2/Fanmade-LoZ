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
	import java.awt.AlphaComposite;
	import java.awt.Graphics2D;
	import java.awt.RenderingHints;
	import java.awt.image.BufferedImage;
	import java.io.File;
	import java.io.IOException;
	import javax.imageio.ImageIO;
	import javax.swing.*;
	import java.util.*;
	import java.io.File;
	import java.io.IOException;
	import javax.sound.sampled.AudioFormat;
	import javax.sound.sampled.AudioInputStream;
	import javax.sound.sampled.AudioSystem;
	import javax.sound.sampled.DataLine;
	import javax.sound.sampled.FloatControl;
	import javax.sound.sampled.LineUnavailableException;
	import javax.sound.sampled.SourceDataLine;
	import javax.sound.sampled.UnsupportedAudioFileException;
	import java.applet.*;
	import java.net.*;

public class ZeldaPlayWindow extends JFrame {

	public final int NORTH = 0;
	public final int EAST = 1;
	public final int SOUTH = 2;
	public final int WEST = 3; //Numbers defining the compass directions
	public int x, y;
	public BufferedImage screenShot; //Nothing important
	public Link thePlayer; //The instance of Link
	public Monster[] theMonster = new Monster[10];
	public String[] monString = new String[10];
	public map theMap; //The instance of the map in the background (not a minimap)
	public Random r = new Random();
	public BufferedReader br;
	public boolean intro = true;

	public ZeldaPlayWindow(String title) throws Exception{
		super(title);
		this.setSize(544,400);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); //Sets window commands

		BorderLayout border = new BorderLayout();
		thePlayer = new Link (300, 200, /*screenShot,*/ this);
		this.add(thePlayer);
		theMap = new map (this);
		FlowLayout ItemPanel = new FlowLayout();  //Organizes the layout
		this.setLayout (border);
		this.add(theMap, BorderLayout.CENTER);

		monLoad();

		//Sound theSound = new Sound("something");
		//theSound.playSound();

		/*Robot robot = new Robot();
		this.setVisible(true);
		screenShot = robot.createScreenCapture(new Rectangle(390, 350));
		this.setVisible(false);
		JLabel theLabel = new JLabel(new ImageIcon(screenShot));
		this.add(theLabel, BorderLayout.CENTER);*/


		//this.add(thePlayer);

		//this.pack();
		this.setVisible(true); //Makes final adjustments

		try{
			Thread.sleep(3000); //Delay his next image change
		}
		catch (InterruptedException e){
		}

		this.intro = false;

	}

	public void monLoad() throws IOException{
		this.theMap.loading = true;
		br = new BufferedReader(new FileReader(this.theMap.x + " " + this.theMap.y + ".txt")); //Reads from the file specified for the map

		for (int i = 0; i < 362; i++){
			if (i > 351){
				monString[i-352] = br.readLine();
			}
			else {
				br.readLine();
			}
		}

		for (int i = 0; i < 10; i++){
			if (theMonster[i] == null){
				do {
					x = r.nextInt(14) + 1;
					y = r.nextInt(9) + 1;
				} while (this.theMap.walkable[(y*16) + x] != 1);

				theMonster[i] = chooseMon(x, y, monString[i], i);
				(new Thread(theMonster[i])).start();
			}
		}
		this.theMap.loading = false;
    }

    public Monster chooseMon(int monX, int monY, String theMonString, int theNum) throws IOException{

		if (theMonString.equals("redoctorok")){
			return new RedOctorok(monX, monY, this, theNum);
		}
		else if (theMonString.equals("blueoctorok")){
			return new BlueOctorok(monX, monY, this, theNum);
		}
		else if (theMonString.equals("reddarknut")){
			return new RedDarknut(monX, monY, this, theNum);
		}
		else if (theMonString.equals("bluedarknut")){
			return new BlueDarknut(monX, monY, this, theNum);
		}
		else if (theMonString.equals("bluegoriya")){
			return new BlueGoriya(monX, monY, this, theNum);
		}
		else if (theMonString.equals("redgoriya")){
			return new RedGoriya(monX, monY, this, theNum);
		}
		else if (theMonString.equals("redmoblin")){
			return new RedMoblin(monX, monY, this, theNum);
		}
		else if (theMonString.equals("bluemoblin")){
			return new BlueMoblin(monX, monY, this, theNum);
		}
		else if (theMonString.equals("bluelynel")){
			return new BlueLynel(monX, monY, this, theNum);
		}
		else if (theMonString.equals("redlynel")){
			return new RedLynel(monX, monY, this, theNum);
		}
		else {
			return null;
		}

    }

    public static void main(String[] args) throws Exception{
		ZeldaPlayWindow theZeldaPlayWindow = new ZeldaPlayWindow("The Legend of Zelda");
    }

}
