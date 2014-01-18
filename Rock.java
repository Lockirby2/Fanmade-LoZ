import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.awt.event.*;
import java.util.*;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.imageio.ImageIO;
//import json.org.*;

public class Rock extends Projectile{

//	Global Variables

	//public JLabel theLabel = new JLabel("SAGFAD");"mis sprites/rock.gif"

    public Rock(Monster theMonster) {

    	super(theMonster);

	    for (int i = 0; i < 4; i++){
	    	try{
	    		this.cache[i] = ImageIO.read(new File("mis sprites/rock.gif")); //Saves each of Link's pictures for easy access
	    		//this.linkPic[i].setOpaque(false);
	    	}
	    	catch (IOException e){
	    	}
    	}
    	this.speed = 4;
    	this.strength = 1;
    	blockable = true;

    }

    public void run(){

    	while (1>0){
    		if (this.visible){
	    		this.move();
	    		this.linkHit();
    		}
    		if (checkBounds()){
    			this.visible = false;
    		}
    		try{
				Thread.sleep(this.speed); //Delay his next image change
			}
			catch (InterruptedException e){
			}
    	}

    }

}