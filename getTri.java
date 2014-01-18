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
public class getTri implements Runnable{

	public Link link;

	public getTri(Link theLink) {

		this.link = theLink;
	}

	//@Override
	public void run (){
		while (1>0){
			if (this.link.window.theMap != null){
				if (this.link.window.theMap.x == 3 && this.link.window.theMap.y == 0 && !this.link.triforceCollected[0]){
					if (this.link.linkCo[0] + 17 > 170 && this.link.linkCo[0] + 17 < 204 && this.link.linkCo[1] + 17 > 238 && this.link.linkCo[1] + 17 < 272){
						this.link.triforceCollected[0] = true;
					}
				}
				else if (this.link.window.theMap.x == 3 && this.link.window.theMap.y == 3 && !this.link.triforceCollected[1]){
					if (this.link.linkCo[0] + 17 > 374 && this.link.linkCo[0] + 17 < 408 && this.link.linkCo[1] + 17 > 102 && this.link.linkCo[1] + 17 < 136){
						this.link.triforceCollected[1] = true;
					}
				}
				else if (this.link.window.theMap.x == 0 && this.link.window.theMap.y == 3 && !this.link.triforceCollected[2]){
					if (this.link.linkCo[0] + 17 > 34 && this.link.linkCo[0] + 17 < 68 && this.link.linkCo[1] + 17 > 204 && this.link.linkCo[1] + 17 < 238){
						this.link.triforceCollected[2] = true;
					}
				}
			}

			try{
				Thread.sleep(10); //Delay his next image change
			}
			catch (InterruptedException e){
			}
			//
		}

	}

	public static void main(String args[]) {
        //(new Thread(new mapRefresher())).start();
   	}
}
