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
public class LinkActionListenerZR extends AbstractAction{

	public Link link;

	public LinkActionListenerZR(Link theLink) {
		super();
		this.link = theLink; //Attaches this listener to link
	}

	//@Override
	public void actionPerformed (ActionEvent e){

		this.link.canSlash = true;

	}
}
