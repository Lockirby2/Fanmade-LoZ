import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.awt.event.*;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.imageio.ImageIO;
//import json.org.*;

public class Link extends JPanel {

//	Global Variables
	public BufferedImage image; //The image that will be drawn of Link
	public BufferedImage[] linkPic = new BufferedImage[12]; //Our saved images of Link
	public int[] linkCo = new int[2]; //0 is link's horizontal coordinates, 1 is his veritcal coordinates
	public int HP = 10;
	public boolean linkWalk = true;
	public boolean linkSlash = false;
	public boolean canSlash = true;
	public boolean hurt = false;
	public boolean invincible = true;
	public boolean visible = true;
	public boolean flash = false;
	public boolean win = false;
	public boolean lose  = false;
	public boolean[] triforceCollected = new boolean[3];
	//public BufferedImage screenShot;
	public ZeldaPlayWindow window;
	public String direction = "n"; //The direction link is WALKING (not facing)
	public String sDirection = "u"; //The direction link is facing (but not necessarily walking)
	public String linkExit = "n";
	public LinkActionListenerUp theLinkActionListenerUp = new LinkActionListenerUp(this);
	public LinkActionListenerRight theLinkActionListenerRight = new LinkActionListenerRight(this);
	public LinkActionListenerLeft theLinkActionListenerLeft = new LinkActionListenerLeft(this);
	public LinkActionListenerDown theLinkActionListenerDown = new LinkActionListenerDown(this);
	public LinkActionListenerUpR theLinkActionListenerUpR = new LinkActionListenerUpR(this);
	public LinkActionListenerRightR theLinkActionListenerRightR = new LinkActionListenerRightR(this);
	public LinkActionListenerLeftR theLinkActionListenerLeftR = new LinkActionListenerLeftR(this);
	public LinkActionListenerDownR theLinkActionListenerDownR = new LinkActionListenerDownR(this);
	public LinkActionListenerX theLinkActionListenerX = new LinkActionListenerX(this);
	public LinkActionListenerZ theLinkActionListenerZ = new LinkActionListenerZ(this);
	public LinkActionListenerZR theLinkActionListenerZR = new LinkActionListenerZR(this);
	public InvincibleActionListener theInvincibleActionListener = new InvincibleActionListener(this);
	public Timer timer;


	//public JLabel theLabel = new JLabel("SAGFAD");

    public Link(int theX, int theY, /*BufferedImage theScreenShot,*/ ZeldaPlayWindow theWindow) {
    	super();
    	this.window = theWindow;
    	this.setPreferredSize(new Dimension(400, 400));
    	this.setBackground(Color.WHITE); //Initializes some stuff
    	linkCo[0] = theX;
    	linkCo[1] = theY;
    	triforceCollected[0] = false;
    	triforceCollected[1] = false;
    	triforceCollected[2] = false;
    	//screenShot = theScreenShot;

    	for (int i = 0; i < 12; i++){
	    	try{
	    		this.linkPic[i] = ImageIO.read(new File("Link sprites/" + i + ".gif")); //Saves each of Link's pictures for easy access
	    		//this.linkPic[i].setOpaque(false);
	    	}
	    	catch (IOException e){
	    	}
    	}

		//this.addActionListener((ActionListener)theLinkActionListener);
		/*Action doSomething = new AbstractAction(){
			public void actionPerformed(ActionEvent e) {
        		System.out.println("works");
    		}
		};*/
		//this.addActionListener(this.theLinkActionListener);
    	this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "up");                  //-|
    	this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "right");             //|
    	this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "down");               //|
    	this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "left");               //|
    	this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released UP"), "ReleasedU");   //|
    	this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released RIGHT"), "ReleasedR");//|
    	this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released DOWN"), "ReleasedD"); //|
    	this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released LEFT"), "ReleasedL"); //|Binds the keys to the actions Link can take
    	this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("x"), "X");
    	this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, 0), "Z");
    	this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, 0, true), "ZR");
																										    //|
    	this.getActionMap().put("up", theLinkActionListenerUp);											    //|
    	this.getActionMap().put("right", theLinkActionListenerRight);                                       //|
    	this.getActionMap().put("down", theLinkActionListenerDown);                                         //|
    	this.getActionMap().put("left", theLinkActionListenerLeft);                                         //|
    	this.getActionMap().put("ReleasedU", theLinkActionListenerUpR);  								    //|
    	this.getActionMap().put("ReleasedR", theLinkActionListenerRightR);  							    //|
    	this.getActionMap().put("ReleasedL", theLinkActionListenerLeftR);  								    //|
    	this.getActionMap().put("ReleasedD", theLinkActionListenerDownR);  							 	   //-|
    	this.getActionMap().put("Z", theLinkActionListenerZ);
    	this.getActionMap().put("ZR", theLinkActionListenerZR);
    	this.getActionMap().put("X", theLinkActionListenerX);

    	this.image = this.linkPic[0]; //Starts Link's image out at standing upwards

		timer = new Timer(500, theInvincibleActionListener);
		timer.setRepeats(false);
		timer.start();

    	(new Thread(new LinkChange(this))).start(); //Starts the thread which allows Link to change icons while walking
    	(new Thread(new BoundCheck(this))).start();
    	(new Thread(new LinkSlice(this))).start();
    	(new Thread(new LinknockBack(this))).start();
    	(new Thread(new BoundCheckHit(this))).start();
    	(new Thread(new InvincibleFlash(this))).start();
    	(new Thread(new LinkExiter(this))).start();
    	(new Thread(new LinkEnterer(this))).start();
    	(new Thread(new LinkDie(this))).start();
    	(new Thread(new getTri(this))).start();
    	(new Thread(new checkWin(this))).start();

    	//theMover = new LinkMove(this);

    	//this.registerKeyboardAction(theLinkActionListener, KeyStroke.getKeyStroke("SPACE"), WHEN_IN_FOCUSED_WINDOW);

    	/*@Override
    		public void actionPerformed(ActionEvent e) {
    			System.out.println("Works");
    		}
    	});*/

    	//this.add(theLabel);
    	/*this.setFocusable(true);
    	this.requestFocusInWindow();
    	this.addKeyListener(this.theLinkListener);*/

    }

}