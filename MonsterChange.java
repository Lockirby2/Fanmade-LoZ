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
public class MonsterChange implements Runnable{

	public Monster monster;

	public MonsterChange(Monster theMonster) {

		this.monster = theMonster;
	}

	//@Override
	public void run (){
		while (1>0){
			if (this.monster.dead){
				break;
			}
			if (this.monster.direction.equals("u")){ //If Link is walking upwards...
				if (this.monster.image == this.monster.monPic[0]){ //If he has one icon for walking upwards...
					this.monster.image = this.monster.monPic[1]; //Switch to the other
				}
				else { //Repeat for all other directions and images
					this.monster.image = this.monster.monPic[0];
				}
			}
			else if (this.monster.direction.equals("d")){
				if (this.monster.image == this.monster.monPic[4]){
					this.monster.image = this.monster.monPic[5];
				}
				else {
					this.monster.image = this.monster.monPic[4];
				}
			}
			else if (this.monster.direction.equals("r")){
				if (this.monster.image == this.monster.monPic[2]){
					this.monster.image = this.monster.monPic[3];
				}
				else {
					this.monster.image = this.monster.monPic[2];
				}
			}
			else if (this.monster.direction.equals("l")){
				if (this.monster.image == this.monster.monPic[6]){
					this.monster.image = this.monster.monPic[7];
				}
				else {
					this.monster.image = this.monster.monPic[6];
			}

			}
			try{
				Thread.sleep(100); //Delay his next image change
			}
			catch (InterruptedException e){
			}
		}

	}

	public static void main(String args[]) {
        //(new Thread(new mapRefresher())).start();
   	}
}
