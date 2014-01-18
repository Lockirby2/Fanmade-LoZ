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
public class BoundCheckHit implements Runnable{

	public Link link;

	public BoundCheckHit(Link theLink) {

		this.link = theLink;
	}

	//@Override
	public void run (){

		while (1>0){
			if (link.hurt){

				if (isWalkable()){
					this.link.linkWalk = true;
				}
				else {
					this.link.linkWalk = false;
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

        if (this.link.sDirection.equals("u")){
        	x = this.link.linkCo[0] + 14;
        	y = this.link.linkCo[1] + 34;
        	if (y > 340){
        		return false;
        	}
        }
        else if (this.link.sDirection.equals("r")){
        	x = this.link.linkCo[0];
        	y = this.link.linkCo[1] + 18;
        	if (x < 34){
        		return false;
        	}
        }
        else if (this.link.sDirection.equals("d")){
        	x = this.link.linkCo[0] + 14;
        	y = this.link.linkCo[1];
        	if (y < 34){
        		return false;
        	}
        }
        else {
        	x = this.link.linkCo[0] + 34;
        	y = this.link.linkCo[1] + 18;
        	if (x > 510){
        		return false;
        	}
        }

        mapX = x/34;
        mapY = y/34;

        x = (int)Math.floor(mapX);
        y = (int)Math.floor(mapY);

        tileNum = (y*16) + x;

        if (this.link.window.theMap.walkable[tileNum] != 1){
        	return false;
        }
        /*else if (this.link.direction.equals("n")){
        	return true;
        }*/
        else {
        	return true;
        }

   	}
}
