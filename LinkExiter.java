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
	import java.lang.Math;
public class LinkExiter implements Runnable{

	public Link link;

	public LinkExiter(Link theLink) {

		this.link = theLink;
	}

	//@Override
	public void run(){

		while (1>0){

			if (this.link.direction.equals("u")){
				if (this.link.linkCo[1] < 5){
					this.link.linkExit = "u";
					//this.link.window.theMap.mapErase();
					for (int i = 0; i < 10; i++){
						if (this.link.window.theMonster[i] != null){
							this.link.window.theMonster[i].HP = 0;
						}
					}
				}
			}
			else if (this.link.direction.equals("d")){
				if (this.link.linkCo[1] > 335){
					this.link.linkExit = "d";
					//this.link.window.theMap.mapErase();
					for (int i = 0; i < 10; i++){
						if (this.link.window.theMonster[i] != null){
							this.link.window.theMonster[i].HP = 0;
						}
					}
				}
			}
			else if (this.link.direction.equals("l")){
				if (this.link.linkCo[0] < 5){
					this.link.linkExit = "l";
					//this.link.window.theMap.mapErase();
					for (int i = 0; i < 10; i++){
						if (this.link.window.theMonster[i] != null){
							this.link.window.theMonster[i].HP = 0;
						}
					}
				}
			}
			else if (this.link.direction.equals("r")){
				if (this.link.linkCo[0] > 505){
					this.link.linkExit = "r";
					//this.link.window.theMap.mapErase();
					for (int i = 0; i < 10; i++){
						if (this.link.window.theMonster[i] != null){
							this.link.window.theMonster[i].HP = 0;
						}
					}
				}
			}

			if (this.link.direction.equals("u")){
				if (this.link.linkCo[1] < - 44){
					this.link.linkCo[1] = 384;
					this.link.window.theMap.y = this.link.window.theMap.y - 1;
					this.link.window.theMap.mapLoad(this.link.window.theMap.x, this.link.window.theMap.y);
				}
			}
			else if (this.link.direction.equals("d")){
				if (this.link.linkCo[1] > 384){
					this.link.linkCo[1] = -44;
					this.link.window.theMap.y = this.link.window.theMap.y + 1;
					this.link.window.theMap.mapLoad(this.link.window.theMap.x, this.link.window.theMap.y);
				}
			}
			else if (this.link.direction.equals("l")){
				if (this.link.linkCo[0] < -44){
					this.link.linkCo[0] = 554;
					this.link.window.theMap.x = this.link.window.theMap.x - 1;
					this.link.window.theMap.mapLoad(this.link.window.theMap.x, this.link.window.theMap.y);
				}
			}
			else if (this.link.direction.equals("r")){
				if (this.link.linkCo[0] > 554){
					this.link.linkCo[0] = -44;
					this.link.window.theMap.x = this.link.window.theMap.x + 1;
					this.link.window.theMap.mapLoad(this.link.window.theMap.x, this.link.window.theMap.y);
				}
			}

			try{
				Thread.sleep(5);
			}
			catch (InterruptedException e){
			}
		}

	}
}
