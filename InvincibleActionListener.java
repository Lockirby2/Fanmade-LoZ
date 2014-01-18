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
public class InvincibleActionListener implements ActionListener{

	public Link link;
	public Monster monster;
	public boolean forLink;

	public InvincibleActionListener(Link theLink) {
		this.link = theLink; //Attaches this listener to link
		forLink = true;
	}

	public InvincibleActionListener(Monster theMonster) {
		this.monster = theMonster; //Attaches this listener to link
		forLink = false;
	}

	//@Override
	public void actionPerformed (ActionEvent e){

		if (this.forLink){//If Link is still moving in this direction, he will stop moving
			this.link.invincible = false;
			//System.out.println(this.link.invincible);
		}
		else {
			this.monster.invincible = false;
			//System.out.println(this.monster.invincible);
		}

	}
}
