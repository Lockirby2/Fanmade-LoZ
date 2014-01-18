import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.awt.event.*;
import java.util.Random;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.imageio.ImageIO;
//import json.org.*;

public class Monster implements Runnable{

//	Global Variables

	public int straightWalking; // Higher number means they change directions more often
	public int speed; // Higher speed means they walk slower, not faster.
	public int HP;
	public int strength;
	public int x, y;
	public int hurtCount;
	public int shootRate; // Higher number means they shoot more often
	public int num;
	public boolean diagonal;
	public boolean invincible;
	public boolean damaging;
	public boolean makeClang;
	public boolean knockBack;
	public boolean switchImage;
	public boolean visible;
	public boolean hurt = false;
	public boolean dead = false;
	public boolean flash = false;
	public String direction;
	public ZeldaPlayWindow window;
	public BufferedImage image;
	public BufferedImage[] diePic = new BufferedImage[2];
	public BufferedImage[] monPic = new BufferedImage[8];
	public Random r = new Random();
	public InvincibleActionListener theInvincibleActionListener = new InvincibleActionListener(this);
	public Projectile projectile;
	public Timer timer;

	//public JLabel theLabel = new JLabel("SAGFAD");

    public Monster(int theX, int theY, /*BufferedImage theScreenShot,*/ ZeldaPlayWindow theWindow, int theNum) {

    	x = theX * 34;
    	y = theY * 34;
    	num = theNum;
    	this.window = theWindow;

    	try {
	    	this.diePic[0] = ImageIO.read(new File("Monster Sprites/dieSprite1.gif"));
	    	this.diePic[1] = ImageIO.read(new File("Monster Sprites/dieSprite2.gif"));
    	}
	    catch (IOException e){
	    }

	    timer = new Timer(500, theInvincibleActionListener);
		timer.setRepeats(false);
		timer.start();

	    (new Thread(new InvincibleFlash(this))).start();

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

    public void appear(){

		this.direction = "u";

    }

    public void die(){

		this.image = diePic[0];
		this.knockBack = false;
		this.makeClang = false;
		this.invincible = true;
		this.damaging = false;
		dead = true;
		try{
			Thread.sleep(100);
		}
		catch (InterruptedException e){
		}
		this.image = diePic[1];
		try{
			Thread.sleep(100);
		}
		catch (InterruptedException e){
		}
		this.image = null;
		this.window.theMonster[num] = null;
		while (1>0){
			try{
				Thread.sleep(1000000);
			}
			catch (InterruptedException e){
			}
		}

    }

    public void checkChange(){

		if (x%34 == 0 && y%34 == 0){
			if (r.nextInt(10) < straightWalking){
				changeDirection();
			}
			while (checkBlock()){
				changeDirection();
			}
			//System.out.println(this.direction);
		}

    }

    public boolean checkBlock(){

		if (this.direction == "u"){
			if (y/34 > 0){
				if (this.window.theMap.walkable[((y/34) - 1) * 16 + (x/34)] != 1){
					return true;
				}
			}
			else {
				return true;
			}
		}
		else if (this.direction == "r"){
			if (x/34 < 15){
				if (this.window.theMap.walkable[(y/34) * 16 + ((x/34)+1)] != 1){
					return true;
				}
			}
			else {
				return true;
			}
		}
		else if (this.direction == "d"){
			if (y/34 < 10){
				if (this.window.theMap.walkable[((y/34) + 1) * 16 + (x/34)] != 1){
					return true;
				}
			}
			else {
				return true;
			}
		}
		else if (this.direction == "l"){
			if (x/34 > 0){
				if (this.window.theMap.walkable[(y/34) * 16 + ((x/34)-1)] != 1){
					return true;
				}
			}
			else {
				return true;
			}
		}
		return false;

    }

    public void changeDirection(){

    	int temp = r.nextInt(4);

    	if (temp == 0){
    		this.direction = "u";
    		if (switchImage){
    			this.image = this.monPic[0];
    		}
    	}
		else if (temp == 1){
    		this.direction = "r";
    		if (switchImage){
    			this.image = this.monPic[2];
    		}
    	}
		else if (temp == 2){
    		this.direction = "d";
    		if (switchImage){
    			this.image = this.monPic[4];
    		}
    	}
    	else if (temp == 3){
    		this.direction = "l";
    		if (switchImage){
    			this.image = this.monPic[6];
    		}
    	}
    	checkChange();

    }

    public void linkHit(){
    	if (damaging && !this.window.thePlayer.invincible && !this.window.thePlayer.hurt){
			if ((this.window.thePlayer.linkCo[0]+17) >= x && (this.window.thePlayer.linkCo[0]+17) <= (x + 34) && (this.window.thePlayer.linkCo[1]+17) >= y && (this.window.thePlayer.linkCo[1]+17) <= (y + 34)){
				this.window.thePlayer.hurt = true;
				this.window.thePlayer.invincible = true;
				this.window.thePlayer.timer.restart();
				this.window.thePlayer.HP = this.window.thePlayer.HP - strength;
    		}
    	}
    }

    public void monHit(){
    	if (this.window.thePlayer.linkSlash && !this.invincible && !this.hurt){
			if (this.window.thePlayer.sDirection == "u"){
				if (x+17 >= this.window.thePlayer.linkCo[0] && x+17 <= this.window.thePlayer.linkCo[0] + 34 && y+34 >= this.window.thePlayer.linkCo[1] - 22 && y+34 <= this.window.thePlayer.linkCo[1] + 14){
					this.hurt = true;
					this.invincible = true;
					timer.restart();
					this.flash = true;
					this.HP = this.HP - 1;
					if (this.direction == "u" || this.direction == "d"){
						this.direction = "d";
						this.knockBack = true;
					}
					else {
						this.knockBack = false;
					}
					//System.out.println("HitUp");
				}
    		}
    		if (this.window.thePlayer.sDirection == "r"){
				if (x+17 >= this.window.thePlayer.linkCo[0] + 34 && x+17 <= this.window.thePlayer.linkCo[0] + 68 && y+17 >= this.window.thePlayer.linkCo[1] && y+17 <= this.window.thePlayer.linkCo[1] + 34){
					this.hurt = true;
					this.invincible = true;
					timer.restart();
					this.flash = true;
					this.HP = this.HP - 1;
					if (this.direction == "r" || this.direction == "l"){
						this.direction = "l";
						this.knockBack = true;
					}
					else {
						this.knockBack = false;
					}
					//System.out.println("HitRight");
				}
    		}
    		if (this.window.thePlayer.sDirection == "d"){
				if (x+17 >= this.window.thePlayer.linkCo[0] && x+17 <= this.window.thePlayer.linkCo[0] + 34 && y+17 >= this.window.thePlayer.linkCo[1] + 34 && y+17 <= this.window.thePlayer.linkCo[1] + 68){
					this.hurt = true;
					this.invincible = true;
					timer.restart();
					this.flash = true;
					this.HP = this.HP - 1;
					if (this.direction == "u" || this.direction == "d"){
						this.direction = "u";
						this.knockBack = true;
					}
					else {
						this.knockBack = false;
					}
					//System.out.println("HitDown");
				}
    		}
    		if (this.window.thePlayer.sDirection == "l"){
				if (x+17 >= this.window.thePlayer.linkCo[0] - 34 && x+17 <= this.window.thePlayer.linkCo[0] && y+17 >= this.window.thePlayer.linkCo[1] && y+17 <= this.window.thePlayer.linkCo[1] + 34){
					this.hurt = true;
					this.invincible = true;
					timer.restart();
					this.flash = true;
					this.HP = this.HP - 1;
					if (this.direction == "l" || this.direction == "r"){
						this.direction = "r";
						this.knockBack = true;
					}
					else {
						this.knockBack = false;
					}
					//System.out.println("HitLeft");
				}
    		}
    		hurtCount = 0;
    	}
    }

    public void monockback(){
		if (this.knockBack){
			if (hurtCount <= 68 && !checkBlockHit()){
				if (this.direction.equals("u")){ //If Link is walking upwards...

					y = y + 1;


				}
				else if (this.direction.equals("d")){

					y = y - 1;


				}
				else if (this.direction.equals("r")){

					x = x - 1;


				}
				else if (this.direction.equals("l")){
					x = x + 1;

				}
			}
		}
    }

    public boolean checkBlockHit(){

		if (this.direction == "u"){
			if (y/34 < 9){
				if (this.window.theMap.walkable[((y/34) + 1) * 16 + (x/34)] != 1){
					return true;
				}
			}
			else {
				return true;
			}
		}
		else if (this.direction == "r"){
			if (x/34 > 0){
				if (this.window.theMap.walkable[(y/34) * 16 + ((x/34))] != 1){
					return true;
				}
			}
			else {
				return true;
			}
		}
		else if (this.direction == "d"){
			if (y/34 > 0){
				if (this.window.theMap.walkable[((y/34)) * 16 + (x/34)] != 1){
					return true;
				}
			}
			else {
				return true;
			}
		}
		else if (this.direction == "l"){
			if (x/34 < 14){
				if (this.window.theMap.walkable[(y/34) * 16 + ((x/34) + 1)] != 1){
					return true;
				}
			}
			else {
				return true;
			}
		}
		return false;

    }

    public void shoot(){

		if (projectile != null){
			//System.out.println("shoot");
			this.projectile.x = x;
			this.projectile.y = y;
			this.projectile.direction = direction;
			if (this.projectile.direction == "u"){
				this.projectile.image = this.projectile.cache[0];
			}
			else if (this.projectile.direction == "r"){
				this.projectile.image = this.projectile.cache[1];
			}
			else if (this.projectile.direction == "d"){
				this.projectile.image = this.projectile.cache[2];
			}
			else if (this.projectile.direction == "l"){
				this.projectile.image = this.projectile.cache[3];
			}
			this.projectile.visible = true;
		}

    }

    public boolean checkShoot(){

		if (x%34 == 0 && y%34 == 0){
			if (this.projectile != null && this.projectile.visible == true){
				return false;
			}
			if (r.nextInt(10) < shootRate){
				return true;
			}
		}
		return false;

    }

}