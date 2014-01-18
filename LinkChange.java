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
public class LinkChange implements Runnable{

	public Link link;

	public LinkChange(Link theLink) {

		this.link = theLink;
	}

	//@Override
	public void run (){
		while (1>0){
			if (!this.link.linkSlash){
				if (this.link.direction.equals("u")){ //If Link is walking upwards...
					if (this.link.image == this.link.linkPic[0]){ //If he has one icon for walking upwards...
						this.link.image = this.link.linkPic[1]; //Switch to the other
					}
					else { //Repeat for all other directions and images
						this.link.image = this.link.linkPic[0];
					}
				}
				else if (this.link.direction.equals("d")){
					if (this.link.image == this.link.linkPic[4]){
						this.link.image = this.link.linkPic[5];
					}
					else {
						this.link.image = this.link.linkPic[4];
					}
				}
				else if (this.link.direction.equals("r")){
					if (this.link.image == this.link.linkPic[2]){
						this.link.image = this.link.linkPic[3];
					}
					else {
						this.link.image = this.link.linkPic[2];
					}
				}
				else if (this.link.direction.equals("l")){
					if (this.link.image == this.link.linkPic[6]){
						this.link.image = this.link.linkPic[7];
					}
					else {
						this.link.image = this.link.linkPic[6];
					}

				}
				try{
					Thread.sleep(100); //Delay his next image change
				}
				catch (InterruptedException e){
				}
			}

			else {
				if (this.link.sDirection.equals("u")){ //If Link is walking upwards...
					this.link.image = this.link.linkPic[8];
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
					Thread.sleep(5); //Delay his next image change
				}
				catch (InterruptedException e){
				}
			}
			//
		}

	}

	public static void main(String args[]) {
        //(new Thread(new mapRefresher())).start();
   	}
}
