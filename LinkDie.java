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
public class LinkDie implements Runnable{

	public Link link;

	public LinkDie(Link theLink) {

		this.link = theLink;
	}

	//@Override
	public void run (){
		while (1>0){
			if (!this.link.linkSlash){

				if (link.HP < 1){
					this.link.lose = true;
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
