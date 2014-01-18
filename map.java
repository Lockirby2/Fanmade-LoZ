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
	import java.awt.Graphics2D;
	import java.awt.RenderingHints;
	import javax.imageio.*;
	import java.awt.image.*;
	import java.lang.Process;
public class map extends JPanel {

	public BufferedImage[] theTileI = new BufferedImage[176];
	public BufferedImage[] heartI = new BufferedImage[3];
	public BufferedImage[] specialScreen = new BufferedImage[3];
	public BufferedImage image;
	public BufferedImage triforce;
	public BufferedImage black = ImageIO.read(new File("Zelda tiles/5.gif"));
	public int[] walkable = new int[176];
	public BufferedReader r = null;
	public ZeldaPlayWindow window;
	public int x, y;
	public String[] heart = new String[5];
	public boolean loading;

	public map(ZeldaPlayWindow theWindow) throws IOException {
		super(new GridLayout(11, 16));
		this.window = theWindow;

		x = 0;
		y = 0;
		mapLoad(x, y);
		for (int i = 0; i < 3; i++){ //Sets each tile individually to its image and whether it is walkable
			try {
				heartI[i] = ImageIO.read(new File("Heart Sprites/" + (i) + ".gif"));
				specialScreen[i] = ImageIO.read(new File("screen/" + (i) + ".bmp"));
			}
			catch (IOException e){
			}
		}
		try {
			triforce = ImageIO.read(new File("mis sprites/triforce.gif"));
		}
		catch (IOException e){
		}

		(new Thread(new mapRefresher(this))).start();

	}

	public void mapLoad(int mapX, int mapY) {
		loading = true;
		int temp = 0;
		try {
			r = new BufferedReader(new FileReader(mapX + " " + mapY + ".txt")); //Reads from the file specified for the map
		}
		catch (IOException e){
		}

		for (int i = 0; i < 176; i++){ //Sets each tile individually to its image and whether it is walkable
			try {
				temp = Integer.parseInt(r.readLine());
				//System.out.println(temp);
				theTileI[i] = ImageIO.read(new File("Zelda tiles/" + temp + ".gif"));
				temp = Integer.parseInt(r.readLine());
				walkable[i] = temp;
			}
			catch (IOException e){
			}
		}

		loading = false;
		 //Starts the thread which refreshes the map and moves Link's position
    }

    public void mapErase(){

		for (int i = 0; i < 176; i++){ //Sets each tile individually to its image and whether it is walkable
			theTileI[i] = null;
		}
    }

    public void paintComponent (Graphics g) {
    	super.paintComponent(g);

    	Graphics2D g2 = (Graphics2D)g;
//    	True if nothing has been drawn yet.
    	if (image == null) {
    		this.image = (BufferedImage)this.createImage(544, 176); //Creates the image of the game map
    		Graphics2D gc = this.image.createGraphics();
    		g2.drawImage(this.image, null, 0, 0);

    		//This is a new line of code to load our image


    	}
    	else {
    		g2.drawImage(this.image, null, 0, 0); //Draws the image in the correct position
    		this.mapRefresh(g2);
    	}
    }

    public void mapRefresh (Graphics2D g2) {
    	if (!this.window.thePlayer.win && !this.window.thePlayer.lose && !this.window.intro){
	    	if (this.window.thePlayer.linkExit.equals("n")){
		    	for (int i = 0; i < 16; i++){ //column
		    		for (int j = 0; j < 11; j++){ //row
		    			g2.drawImage(theTileI[i+(j*16)], i*34, j*34, null); //Draws each tile in turn
		    		}
		    	}
	    	}
	    	else {
	    		for (int i = 0; i < 16; i++){ //column
		    		for (int j = 0; j < 11; j++){ //row
		    			g2.drawImage(black, i*34, j*34, null); //Draws each tile in turn
		    		}
		    	}
	    	}
	    	if (this.window.thePlayer.visible){
		    	if (!this.window.thePlayer.linkSlash){
		    		g2.drawImage(this.window.thePlayer.image, this.window.thePlayer.linkCo[0], this.window.thePlayer.linkCo[1], null); //Draws Link
		    	}
		    	else {
		    		if (this.window.thePlayer.image == this.window.thePlayer.linkPic[8]){
		    			g2.drawImage(this.window.thePlayer.image, this.window.thePlayer.linkCo[0], this.window.thePlayer.linkCo[1] - 34, null); //Draws Link
		    		}
		    		else if (this.window.thePlayer.image == this.window.thePlayer.linkPic[9]){
		    			g2.drawImage(this.window.thePlayer.image, this.window.thePlayer.linkCo[0], this.window.thePlayer.linkCo[1], null); //Draws Link
		    		}
		    		else if (this.window.thePlayer.image == this.window.thePlayer.linkPic[10]){
		    			g2.drawImage(this.window.thePlayer.image, this.window.thePlayer.linkCo[0], this.window.thePlayer.linkCo[1], null); //Draws Link
		    		}
		    		else if (this.window.thePlayer.image == this.window.thePlayer.linkPic[11]){
		    			g2.drawImage(this.window.thePlayer.image, this.window.thePlayer.linkCo[0] - 35, this.window.thePlayer.linkCo[1], null); //Draws Link
		    		}
		    	}
	    	}
	    	for (int i = 0; i < 10; i++){
	    		if (this.window.theMonster[i] != null && this.window.theMonster[i].visible){
	    			g2.drawImage(this.window.theMonster[i].image, this.window.theMonster[i].x, this.window.theMonster[i].y, null);
	    		}
	    	}
	    	for (int i = 0; i < 10; i++){
	    		if (this.window.theMonster[i] != null && this.window.theMonster[i].projectile != null && this.window.theMonster[i].projectile.visible){
	    			g2.drawImage(this.window.theMonster[i].projectile.image, this.window.theMonster[i].projectile.x, this.window.theMonster[i].projectile.y, null);
	    		}
	    	}
	    	for (int i = 0; i < 10; i++){
	    		if (i < this.window.thePlayer.HP){
	    			if (i % 2 == 0){
	    				heart[i/2] = "half";
	    			}
	    			else {
	    				heart[(i-1)/2] = "full";
	    			}
	    		}
	    		else if (i % 2 == 0){
	    			heart[i/2] = "empty";
	    		}
	    	}

	    	for (int i = 0; i < 5; i++){
	    		if (heart[i].equals("full")){
					g2.drawImage(heartI[2], (i*34) + 374, 34, null);
	    		}
	    		else if (heart[i].equals("half")){
					g2.drawImage(heartI[1], (i*34) + 374, 34, null);
	    		}
	    		else {
					g2.drawImage(heartI[0], (i*34) + 374, 34, null);
	    		}
	    	}

	    	if (x == 3 && y == 0 && !this.window.thePlayer.triforceCollected[0]){
	    		g2.drawImage(triforce, 170, 238, null);
	    	}
	    	else if (x == 3 && y == 3 && !this.window.thePlayer.triforceCollected[1]){
	    		g2.drawImage(triforce, 374, 102, null);
	    	}
	    	else if (x == 0 && y == 3 && !this.window.thePlayer.triforceCollected[2]){
	    		g2.drawImage(triforce, 34, 204, null);
	    	}
    	}
    	else if (this.window.intro){
    		g2.drawImage(specialScreen[0], 0, 0, null);
    	}
    	else if (this.window.thePlayer.lose){
    		g2.drawImage(specialScreen[1], 0, 0, null);
    	}
    	else if (this.window.thePlayer.win){
    		g2.drawImage(specialScreen[2], 0, 0, null);
    	}
    	g2.dispose();
    }

    public static void main(String[] args) {

    }
}
