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

public class InvincibleFlash implements Runnable{

//	Global Variables

	public Monster monster;
	public Link link;
	public boolean forLink;
//	public BufferedImage

	//public JLabel theLabel = new JLabel("SAGFAD");

    public InvincibleFlash(Link thePlayer) {

    	link = thePlayer;
    	forLink = true;

    }

    public InvincibleFlash(Monster theMonster) {

    	monster = theMonster;
    	forLink = false;

    }

    public void run(){

		while (1>0){
	    	if (this.forLink){
		    	if (this.link.invincible && this.link.visible){
		    		this.link.visible = false;
		    	}
		    	else {
		    		this.link.visible = true;
		    	}
		    	//System.out.println(this.link.visible);
	    	}
	    	else {
		    	if (this.monster.flash && this.monster.visible){
		    		this.monster.visible = false;
		    	}
		    	else {
		    		this.monster.visible = true;
		    	}
	    	}
	    	try{
				Thread.sleep(1); //Delay his next image change
			}
			catch (InterruptedException e){
			}
		}

    }
}