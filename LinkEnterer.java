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
	import java.io.IOException;
public class LinkEnterer implements Runnable{

	public Link link;

	public LinkEnterer(Link theLink) {

		this.link = theLink;
	}

	//@Override
	public void run(){

		while (1>0){

			if (this.link.direction.equals("u")){
				if (this.link.linkCo[1] == 340){
					try{
						this.link.window.monLoad();
					}
					catch (IOException e){
					}
					this.link.linkCo[1] = this.link.linkCo[1] - 2;
					this.link.linkExit = "n";
					this.link.direction = "n";
				}
			}
			else if (this.link.direction.equals("d")){
				if (this.link.linkCo[1] == 0){
					try{
						this.link.window.monLoad();
					}
					catch (IOException e){
					}
					this.link.linkCo[1] = this.link.linkCo[1] + 2;
					this.link.linkExit = "n";
					this.link.direction = "n";
				}
			}
			else if (this.link.direction.equals("l")){
				if (this.link.linkCo[0] == 510){
					try{
						this.link.window.monLoad();
					}
					catch (IOException e){
					}
					this.link.linkCo[0] = this.link.linkCo[0] - 2;
					this.link.linkExit = "n";
					this.link.direction = "n";
				}
			}
			else if (this.link.direction.equals("r")){
				if (this.link.linkCo[0] == 0){
					try{
						this.link.window.monLoad();
					}
					catch (IOException e){
					}
					this.link.linkCo[0] = this.link.linkCo[0] + 2;
					this.link.linkExit = "n";
					this.link.direction = "n";
				}
			}

			try{
				Thread.sleep(2);
			}
			catch (InterruptedException e){
			}
		}

	}
}
