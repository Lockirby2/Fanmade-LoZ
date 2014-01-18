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
public class LinkSlice implements Runnable{

	public Link link;

	public LinkSlice(Link theLink) {

		this.link = theLink;
	}

	//@Override
	public void run (){
		while (1>0){
			if (this.link.linkSlash){
				if (this.link.sDirection.equals("u")){ //If Link is walking upwards...

					this.link.image = this.link.linkPic[8]; //Switch to the other

				}
				else if (this.link.sDirection.equals("d")){

					this.link.image = this.link.linkPic[10];

				}
				else if (this.link.sDirection.equals("r")){

					this.link.image = this.link.linkPic[9];

				}
				else if (this.link.sDirection.equals("l")){

					this.link.image = this.link.linkPic[11];

				}

				try{
					Thread.sleep(150); //Delay his next image change
				}
				catch (InterruptedException e){
				}

				this.link.linkSlash = false;

				if (this.link.sDirection.equals("u")){ //If Link is walking upwards...

					this.link.image = this.link.linkPic[0]; //Switch to the other

				}
				else if (this.link.sDirection.equals("d")){

					this.link.image = this.link.linkPic[4];

				}
				else if (this.link.sDirection.equals("r")){

					this.link.image = this.link.linkPic[2];

				}
				else if (this.link.sDirection.equals("l")){

					this.link.image = this.link.linkPic[6];

				}

				/*try{
					Thread.sleep(800); //Delay his next image change
				}
				catch (InterruptedException e){
				}*/

			}
			try{
				Thread.sleep(5); //Delay his next image change
			}
			catch (InterruptedException e){
			}
		}

	}

	public static void main(String args[]) {
        //(new Thread(new mapRefresher())).start();
   	}
}
