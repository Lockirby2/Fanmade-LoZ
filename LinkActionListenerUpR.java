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
public class LinkActionListenerUpR extends AbstractAction{

	public Link link;

	public LinkActionListenerUpR(Link theLink) {
		super();
		this.link = theLink; //Attaches this listener to link
	}

	//@Override
	public void actionPerformed (ActionEvent e){

		if (this.link.direction == "u"){//If Link is still moving in this direction, he will stop moving
			this.link.direction = "n";
		}

	}
}
