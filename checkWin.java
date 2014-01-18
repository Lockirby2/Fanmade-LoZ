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
public class checkWin implements Runnable{

	public Link link;

	public checkWin(Link theLink) {

		this.link = theLink;
	}

	//@Override
	public void run (){
		while (1>0){
			if (this.link.triforceCollected[0] && this.link.triforceCollected[1] && this.link.triforceCollected[2]){

				this.link.win = true;

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
