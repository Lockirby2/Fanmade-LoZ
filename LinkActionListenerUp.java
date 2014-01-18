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
public class LinkActionListenerUp extends AbstractAction{

	public Link link;

	public LinkActionListenerUp(Link theLink) {
		super();
		this.link = theLink; //Attaches this listener to link
	}

	//@Override
	public void actionPerformed (ActionEvent e){

		if (!this.link.hurt && !this.link.window.intro && this.link.linkExit.equals("n")){
			this.link.direction = "u"; //Changes Link's direction when the key bound to it is pressed
			this.link.sDirection = "u";
			//System.out.println("T");
		}

	}
}
