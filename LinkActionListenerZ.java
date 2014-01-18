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
public class LinkActionListenerZ extends AbstractAction{

	public Link link;

	public LinkActionListenerZ(Link theLink) {
		super();
		this.link = theLink; //Attaches this listener to link
	}

	//@Override
	public void actionPerformed (ActionEvent e){

		if (!this.link.hurt){
			if (this.link.canSlash){
				this.link.linkSlash = true;
			}
			this.link.canSlash = false;
		}

	}
}
