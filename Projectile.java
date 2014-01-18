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

public class Projectile implements Runnable{

//	Global Variables

	public int speed; // Higher speed means they walk slower, not faster.
	public int strength;
	public int x, y;
	public boolean damaging = false;
	public boolean blockable;
	public boolean visible = false;
	public String direction;
	public Monster monster;
	public BufferedImage image;
	public BufferedImage[] cache = new BufferedImage[4];

	//public JLabel theLabel = new JLabel("SAGFAD");

    public Projectile(Monster theMonster) {

    	x = 0;
    	y = 0;
    	this.monster = theMonster;

    }

    public void run(){

    }

    public void move(){

		if (direction == "u"){
			y = y - 1;
		}
		else if (direction == "r"){
			x = x + 1;
		}
		else if (direction == "d"){
			y = y + 1;
		}
		else if (direction == "l"){
			x = x - 1;
		}

    }

    public void linkHit(){
    	if (!this.monster.window.thePlayer.invincible && !this.monster.window.thePlayer.hurt){
			if ((this.monster.window.thePlayer.linkCo[0]+17) >= x && (this.monster.window.thePlayer.linkCo[0]+17) <= (x + 34) && (this.monster.window.thePlayer.linkCo[1]+17) >= y && (this.monster.window.thePlayer.linkCo[1]+17) <= (y + 34)){
				if (checkDamaging()){
					this.monster.window.thePlayer.hurt = true;
					this.monster.window.thePlayer.invincible = true;
					this.monster.window.thePlayer.HP = this.monster.window.thePlayer.HP - strength;
					this.monster.window.thePlayer.timer.restart();
				}
				this.visible = false;
    		}
    	}
    }

    public boolean checkDamaging(){
		if (blockable && !this.monster.window.thePlayer.linkSlash){
	    	if (this.monster.window.thePlayer.sDirection == "u" && this.direction == "d"){
				return false;
	    	}
	    	else if (this.monster.window.thePlayer.sDirection == "d" && this.direction == "u"){
				return false;
	    	}
	    	else if (this.monster.window.thePlayer.sDirection == "r" && this.direction == "l"){
				return false;
	    	}
	    	else if (this.monster.window.thePlayer.sDirection == "l" && this.direction == "r"){
				return false;
	    	}
		}
    	else if (!visible){
    		return false;
    	}
    	return true;

    }

    public boolean checkBounds(){

    	if (x < -34 || x > 544 || y < -34 || y > 374){
			return true;
    	}

    	return false;

    }

}