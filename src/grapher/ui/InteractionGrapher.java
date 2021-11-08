package grapher.ui;

import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class InteractionGrapher implements MouseListener, MouseMotionListener, MouseWheelListener{

	private Grapher g;
	private STATE state;
	private Point prec;
	private MouseEvent last;

	public enum STATE {
		NOTHING, LEFTPRESSED, LEFTDRAGGED, RIGHTPRESSED, RIGHTDRAGGED
	}
	
	InteractionGrapher(Grapher g) {
		this.g = g;
		state = STATE.NOTHING;
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		switch (state) {
		case LEFTPRESSED:
		case LEFTDRAGGED:
			state = STATE.LEFTDRAGGED;
			System.out.println("LEFTDRAGGED");
			Point pt = arg0.getPoint();
			g.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			g.translate(pt.x - prec.x, pt.y - prec.y);
			prec = pt;
			break;
		case RIGHTDRAGGED:
		case RIGHTPRESSED:
			state = STATE.RIGHTDRAGGED;
			System.out.println("RIGHTDRAGGED");
			last = arg0;
			g.repaint();
			break;
		case NOTHING:
		default:
			break;
		}
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int stateint = e.getButton();
		switch (stateint) {
		case MouseEvent.BUTTON1:
			state = STATE.LEFTPRESSED;
			break;
		case MouseEvent.BUTTON3:
			state = STATE.RIGHTPRESSED;
			break;
		}
		prec = e.getPoint();
		last = e;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (state == STATE.RIGHTDRAGGED)
			g.zoom(prec, last.getPoint());
		else if (state == STATE.LEFTPRESSED)
			g.zoom(e.getPoint(), 5);
		else if (state == STATE.RIGHTPRESSED)
			g.zoom(e.getPoint(), -5);
		else if (state == STATE.LEFTDRAGGED) {
			g.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			g.repaint();
		}
		state = STATE.NOTHING;
	}
	
	public void drawRect(Graphics2D g) {
		if (state == STATE.RIGHTDRAGGED) {
			Point pt = last.getPoint();
			g.drawRect(prec.x, prec.y, pt.x - prec.x, pt.y - prec.y);
		}
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if (state == STATE.NOTHING) {
			if (e.getWheelRotation() > 0) {
				g.zoom(e.getPoint(), -5);
			} else {
				g.zoom(e.getPoint(), 5);
			}
		}
	}
}
