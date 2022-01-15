package com.game;

import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public final class Renderer extends JPanel {
	
	private final Game game;
	private Image icon;
	
	public Renderer(Game game) {
		this.game = game;
		setMinimumSize(Commons.RESOLUTION);
		setPreferredSize(Commons.RESOLUTION);
		setMaximumSize(Commons.RESOLUTION);
		setSize(Commons.RESOLUTION);
		icon = Game.readImage("slime");
	}
	
	public void start() {
		
		addMouseListener(game.getGameMenu());
		addMouseMotionListener(game.getGameMenu());
		
		JFrame frame = new JFrame("Towers vs. Slimes");
		frame.setLayout(new BorderLayout());
		frame.add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIconImage(icon);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		
		final Renderer r = this;
		
		new Thread(() -> {
			while(true) {
				long start = System.currentTimeMillis();
				int time = (1000 / 60) - (int) (System.currentTimeMillis() - start);
				game.tick();
				SwingUtilities.invokeLater(() -> {
					r.repaint();
				});
				
				try {
	                Thread.sleep(time < 0 ? 0 : time);
	            } catch (InterruptedException e) {
	                throw new RuntimeException(e);
	            }
			}
		}).start();	
		
	}
	
	public void paintComponent(Graphics g) {
		game.render(g);
	}
}
