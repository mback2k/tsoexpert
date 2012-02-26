package de.uxnr.tsoexpert.ui.zone;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.IOException;

import javax.swing.JPanel;

import de.uxnr.tsoexpert.game.IDataHandler;
import de.uxnr.tsoexpert.game.communication.vo.ZoneVO;
import de.uxnr.tsoexpert.map.ZoneMap;

public class ZoneMapFrame extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener, IDataHandler<ZoneVO> {
	private static final long serialVersionUID = 7088418888582520017L;

	private ZoneMap m_zonemap;
	private boolean t_drag;
	private int t_lastX;
	private int t_lastY;
	
	public ZoneMapFrame() {
		super();
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.addMouseWheelListener(this);
	}

	protected void paintComponent(Graphics g) {
		if (this.m_zonemap != null) {
			this.m_zonemap.draw(this.getSize(), (Graphics2D) g);
		}
	}

	public void renderZoneVO(ZoneVO zoneVO) {
		this.m_zonemap = new ZoneMap(zoneVO);
		this.repaint();
	}
	
	public ZoneMap getZoneMap() {
		return this.m_zonemap;
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent event) {
		this.t_drag = true;
		this.t_lastX = event.getX();
		this.t_lastY = event.getY();
	}

	@Override
	public void mouseReleased(MouseEvent event) {
		this.t_drag = false;
	}

	@Override
	public void mouseDragged(MouseEvent event) {
		if (this.m_zonemap != null && this.t_drag) {
			this.m_zonemap.updateOffsetX(this.t_lastX - event.getX());
			this.m_zonemap.updateOffsetY(this.t_lastY - event.getY());
			this.t_lastX = event.getX();
			this.t_lastY = event.getY();
			this.repaint();
		}
	}

	@Override
	public void mouseMoved(MouseEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent event) {
		if (this.m_zonemap != null) {
			this.m_zonemap.updateZoomFactor(event.getWheelRotation()*-1);
			this.repaint();
		}
	}

	@Override
	public void handleData(final ZoneVO zoneVO) throws IOException {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				renderZoneVO(zoneVO);
			}
		});
	}
}
