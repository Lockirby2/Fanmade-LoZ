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
public class LinknockBack implements Runnable{

	public Link link;

	public LinknockBack(Link theLink) {

		this.link = theLink;
	}

	//@Override
	public void run (){
		int count = 0;
		while (1>0){
			if (this.link.hurt){
				if (this.link.linkWalk){
					if (this.link.sDirection.equals("u")){ //If Link is walking upwards...

						this.link.linkCo[1] = this.link.linkCo[1] + 1;

					}
					else if (this.link.sDirection.equals("d")){

						this.link.linkCo[1] = this.link.linkCo[1] - 1;

					}
					else if (this.link.sDirection.equals("r")){

						this.link.linkCo[0] = this.link.linkCo[0] - 1;

					}
					else if (this.link.sDirection.equals("l")){

						this.link.linkCo[0] = this.link.linkCo[0] + 1;

					}
				}
				count++;
				if (count > 68){
					count = 0;
					this.link.hurt = false;
				}
			}

			try{
				Thread.sleep(1); //Delay his next image change
			}
			catch (InterruptedException e){
			}

		}

	}

	public static void main(String args[]) {
        //(new Thread(new mapRefresher())).start();
   	}
}
