package myFrame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.util.List;

import Geom.Point3D;
import Pacman.Pacman;
import Path.Path;

public class PacmanThread extends Thread{
	DotsAndLines canvas;
	Pacman pacman;
	List<Path> lines;
	public PacmanThread(DotsAndLines canvas, Pacman pacman, List<Path> lines)
	{
		this.canvas = canvas;
		this.pacman = pacman;
		this.lines = lines;
	}
	@Override
	public void run() {	
		for(int i=0; i<lines.size(); i++)
		{
			Path path = lines.get(i);
			if(path.ID.equals(pacman.getInfo().getID()))
			{
				if(path.time >= 0)
				{
				try {
					Thread.sleep((long) (path.time * 5));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				MyFrame.UpdateTime(MyFrame.getTime() + path.time);
				canvas.AddSolution(path);
				pacman.translate(path.vec);
				canvas.repaint();
				}
			}
		}
		canvas.finished.add(true);
	}
}
