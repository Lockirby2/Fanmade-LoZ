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
public class BoundCheck implements Runnable{

	public Link link;

	public BoundCheck(Link theLink) {

		this.link = theLink;
	}

	//@Override
	public void run (){

		while (1>0){
			if (!link.hurt){
				if (this.link.linkExit.equals ("n")){
					if (isWalkable()){
						this.link.linkWalk = true;
					}
					else {
						this.link.linkWalk = false;
					}
				}
				else {
					if (!this.link.window.theMap.loading){
						if (this.link.linkExit.equals ("r")){
							this.link.direction = "r";
						}
						if (this.link.linkExit.equals ("l")){
							this.link.direction = "l";
						}
						if (this.link.linkExit.equals ("d")){
							this.link.direction = "d";
						}
						if (this.link.linkExit.equals ("u")){
							this.link.direction = "u";
						}
						this.link.linkWalk = true;
					}
					else {
						this.link.linkWalk = false;
					}
				}
			}

			try{
				Thread.sleep(5);
			}
			catch (InterruptedException e){
			}
		}

	}

	public boolean isWalkable() {

		int x, y;
		double mapX, mapY;
		int tileNum;

        if (this.link.direction.equals("n")){
        	return false;
        }
        else if (this.link.direction.equals("u")){
        	x = this.link.linkCo[0] + 14;
        	y = this.link.linkCo[1] + 14;
        	if (y == 0){
        		return true;
        	}
        }
        else if (this.link.direction.equals("r")){
        	x = this.link.linkCo[0] + 32;
        	y = this.link.linkCo[1] + 18;
        	if (x >= 539){
        		return true;
        	}
        }
        else if (this.link.direction.equals("d")){
        	x = this.link.linkCo[0] + 14;
        	y = this.link.linkCo[1] + 30;
        	if (y >= 369){
        		return true;
        	}
        }
        else {
        	x = this.link.linkCo[0] + 2;
        	y = this.link.linkCo[1] + 18;
        	if (x == 0){
        		return true;
        	}
        }

        mapX = x/34;
        mapY = y/34;

        x = (int)Math.floor(mapX);
        y = (int)Math.floor(mapY);

        tileNum = (y*16) + x;

		if(!link.linkExit.equals("n")){
			return true;
		}
        if (this.link.window.theMap.walkable[tileNum] == 1){
        	return true;
        }
        else {
        	return false;
        }

   	}
}
