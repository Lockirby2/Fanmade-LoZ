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
public class mapRefresher implements Runnable{

	public map theMap;

	public mapRefresher(map myMap) {

		this.theMap = myMap;
	}

	//@Override
	public void run (){
		while (1>0){
			if (!this.theMap.window.thePlayer.linkSlash && !this.theMap.window.thePlayer.hurt){
				if (this.theMap.window.thePlayer.linkWalk){
					//if (!this.theMap.window.thePlayer.linkExit.equals("l") && !this.theMap.window.thePlayer.linkExit.equals("r")){
						if (this.theMap.window.thePlayer.direction.equals("u")){ //If Link is moving, move him a pixel in the right direction
							this.theMap.window.thePlayer.linkCo[1] = this.theMap.window.thePlayer.linkCo[1] - 1;

							/*try{
								Thread.sleep(5); //Places a delay
							}
							catch (InterruptedException e){
							}*/
						}
						else if (this.theMap.window.thePlayer.direction.equals("d")){ //Repeat the process for the other directions
							this.theMap.window.thePlayer.linkCo[1] = this.theMap.window.thePlayer.linkCo[1] + 1;

							/*try{
								Thread.sleep(5);
							}
							catch (InterruptedException e){
							}*/
						}
					//}
					//else if(!this.theMap.window.thePlayer.linkExit.equals("u") && !this.theMap.window.thePlayer.linkExit.equals("d")){
						if (this.theMap.window.thePlayer.direction.equals("r")){
							this.theMap.window.thePlayer.linkCo[0] = this.theMap.window.thePlayer.linkCo[0] + 1;

							/*try{
								Thread.sleep(5);
							}
							catch (InterruptedException e){
							}*/
						}
						else if (this.theMap.window.thePlayer.direction.equals("l")){
							this.theMap.window.thePlayer.linkCo[0] = this.theMap.window.thePlayer.linkCo[0] - 1;

							/*try{
								Thread.sleep(5);
							}
							catch (InterruptedException e){
							}*/
						}
					//}
				}
				if (this.theMap.window.thePlayer.direction.equals("u")){
					if (this.theMap.window.thePlayer.image != this.theMap.window.thePlayer.linkPic[0] && this.theMap.window.thePlayer.image != this.theMap.window.thePlayer.linkPic[1]){
						this.theMap.window.thePlayer.image = this.theMap.window.thePlayer.linkPic[0]; //If Link's costume isn't the correct direction, change it so it is
					}
				}
				else if (this.theMap.window.thePlayer.direction.equals("d")){
					if (this.theMap.window.thePlayer.image != this.theMap.window.thePlayer.linkPic[4] && this.theMap.window.thePlayer.image != this.theMap.window.thePlayer.linkPic[5]){
						this.theMap.window.thePlayer.image = this.theMap.window.thePlayer.linkPic[4];
					}
				}
				else if (this.theMap.window.thePlayer.direction.equals("r")){
					if (this.theMap.window.thePlayer.image != this.theMap.window.thePlayer.linkPic[2] && this.theMap.window.thePlayer.image != this.theMap.window.thePlayer.linkPic[3]){
						this.theMap.window.thePlayer.image = this.theMap.window.thePlayer.linkPic[2];
					}
				}
				else if (this.theMap.window.thePlayer.direction.equals("l")){
					if (this.theMap.window.thePlayer.image != this.theMap.window.thePlayer.linkPic[6] && this.theMap.window.thePlayer.image != this.theMap.window.thePlayer.linkPic[7]){
						this.theMap.window.thePlayer.image = this.theMap.window.thePlayer.linkPic[6];
					}
				}
			}
			try{
				Thread.sleep(5);
			}
			catch (InterruptedException e){
			}
			this.theMap.repaint(); //Refreshes the map
		}

	}

	public static void main(String args[]) {
        //(new Thread(new mapRefresher())).start();
   	}
}
