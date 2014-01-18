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

public class BlueDarknut extends Monster{

//	Global Variables

	//public JLabel theLabel = new JLabel("SAGFAD");

    public BlueDarknut(int theX, int theY, /*BufferedImage theScreenShot,*/ ZeldaPlayWindow theWindow, int theNum) {

    	super(theX, theY, theWindow, theNum);

    	for (int i = 0; i < 8; i++){
	    	try{
	    		this.monPic[i] = ImageIO.read(new File("Monster Sprites/Darknut/B" + i + ".gif")); //Saves each of Link's pictures for easy access
	    		//this.linkPic[i].setOpaque(false);
	    	}
	    	catch (IOException e){
	    	}
    	}
    	this.switchImage = true;
    	this.image = this.monPic[0];
    	this.straightWalking = 3;
    	this.speed = 9;
    	this.HP = 8;
    	this.strength = 4;
    	this.damaging = true;

    }

    public void run(){

    	this.appear();
    	(new Thread(new MonsterChange(this))).start();

    	while (1>0){
    		if (!this.hurt || !this.knockBack){
	    		this.move();
	    		this.checkChange();
	    		if (this.defend()){
	    			this.invincible = true;
	    		}
	    		else {
	    			this.invincible = false;
	    		}
	    		this.linkHit();
	    		this.monHit();
	    		try{
					Thread.sleep(this.speed); //Delay his next image change
				}
				catch (InterruptedException e){
				}
    		}
    		else {
    			this.monockback();
    		}
    		hurtCount++;
    		if (hurtCount == 69){
    			if (!this.knockBack){
					this.hurt = false;
					this.invincible = false;
					this.flash = false;
				}
				else {
					hurtCount = 0;
					this.knockBack = false;
				}
			}
			if (this.HP == 0){
				this.die(); //Homestuck reference FTW!
				break;
			}
    		try{
				Thread.sleep(1); //Delay his next image change
			}
			catch (InterruptedException e){
			}
    	}

    }

    public boolean defend(){
    	if (this.window.thePlayer.sDirection == "u" && this.direction == "d"){
			return true;
    	}
    	else if (this.window.thePlayer.sDirection == "d" && this.direction == "u"){
			return true;
    	}
    	else if (this.window.thePlayer.sDirection == "r" && this.direction == "l"){
			return true;
    	}
    	else if (this.window.thePlayer.sDirection == "l" && this.direction == "r"){
			return true;
    	}
    	return false;
    }

}