package cl.fuentes.batch.tool;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;


@SuppressWarnings("serial")
public class RotationExample extends Canvas implements KeyListener {
	public final static int SIZEX = 160; //This will be our Image Width
	public final static int SIZEY = 120; //This will be our Image Height
	private int x; //This will be used to move the image horizontally
	private int y; //This will be used to move the image vertically
	private int degrees; //This will be used to set rotation degrees

	public static void main(String[] args) {

		JFrame fr = new JFrame("Rotation Example");
		RotationExample re = new RotationExample();
		fr.getContentPane().add(re);
		fr.setSize(800, 500);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fr.setVisible(true);
        }

	public RotationExample() { //Key Listener setup
		this.addKeyListener(this);
        }

	public static BufferedImage makeImageDebug(int type) {
		BufferedImage img = new BufferedImage(SIZEX, SIZEY, type);
		Graphics2D g = img.createGraphics();
		g.fillRect(0, 0, 4, 4);
		g.setColor(new Color(0xff0000));
		g.fillRect(0, SIZEY-4, 4, 4);
		g.setColor(new Color(0x00ff00));
		g.fillRect(SIZEX-4, 0, 4, 4);
		g.setColor(new Color(0x0000ff));
		g.fillRect(SIZEX-4, SIZEY-4, 4, 4);
		g.dispose();
		return img; 
        }
	
	public static BufferedImage makeImage(int type) { //A simple draw
		BufferedImage img = new BufferedImage(SIZEX, SIZEY, type);
		Graphics2D g = img.createGraphics();
		g.fillRect(0, 0, SIZEX, SIZEY);
		g.dispose();
		return img; 
        }
	
	public static DoublePoint rotatePoint(double x, double y, double cx, double cy, double a) {
		return new DoublePoint(
		cx+((x-cx) * Math.cos(a) - (y-cy) * Math.sin(a)),
		cy+((x-cx) * Math.sin(a) + (y-cy) * Math.cos(a))); 
        }

	public static Point[] getMinMaxPointRotation(int width, int height, double radians) {
            DoublePoint cr = new DoublePoint(width/2, height/2); //Gets the center point
            DoublePoint[] p = { //Rotate the four points
            rotatePoint(0, 0, cr.x, cr.y, radians),
            rotatePoint(0, height, cr.x, cr.y, radians),
            rotatePoint(width, 0, cr.x, cr.y, radians),
            rotatePoint(width, height, cr.x, cr.y, radians) };
            //Correct point initialization in order to avoid problems with values
            //Initialization with 0 is not a great idea...
            int majorsize = width > height ? width : height;
            Point min = new Point(majorsize, majorsize);
            Point max = new Point(-majorsize, -majorsize);
            for(int i = 0; i < p.length; i++) { //check for values...
		Point po = p[i].toPoint();
		if (po.x < min.x) { min.x = po.x; }
			if (po.x > max.x) { max.x = po.x; }
			if (po.y < min.y) { min.y = po.y; }
			if (po.y > max.y) { max.y = po.y; }
            }
            return new Point[] { min, max };
        } //return them as array
	
	public static BufferedImage rotateImage(BufferedImage img, double degrees) {
		//Let's get the minimum and maximum point that our rotation will get
		Point[] xd = getMinMaxPointRotation(img.getWidth(), img.getHeight(), Math.toRadians(degrees));
		//As a consequence make the image that big
		BufferedImage rotated = new BufferedImage(xd[1].x-xd[0].x, xd[1].y-xd[0].y, img.getType());
		//We create a Graphics2D object...
		Graphics2D f = rotated.createGraphics();
		f.translate(rotated.getWidth()/2, rotated.getHeight()/2); //translate half the width and height
		f.rotate(Math.toRadians(degrees)); //rotate the image...
		f.translate(-img.getWidth()/2, -img.getHeight()/2); //translate-back to half the original width and height
		f.drawImage(img, 0, 0, null); //draw image
		f.dispose();
		return rotated;
        }



	public void paint(Graphics g) { //paint method for Canvas

		BufferedImage RGBimg = makeImage(BufferedImage.TYPE_INT_RGB);
		BufferedImage ARGBimg = makeImage(BufferedImage.TYPE_INT_ARGB);
		//get the two rotated images
		BufferedImage img1 = rotateImage(RGBimg, degrees);
		BufferedImage img2 = rotateImage(ARGBimg, degrees);
		//Get here, again, the minimum and the maximum point, in order to use the minimum point to let the image be centered
		Point[] xd = getMinMaxPointRotation(RGBimg.getWidth(), RGBimg.getHeight(), Math.toRadians(degrees));
		//and then draw them according to the minimum point... simple, isn't it?
		g.drawImage(img1, x+xd[0].x, y+xd[0].y, null);
		g.drawImage(img2, x+300+xd[0].x, y+xd[0].y, null);
        	//this is used just as indication
		g.drawString("USE ARROWS TO MOVE, \"O\" to rotate CLOCKWISE and \"A\" to rotate COUNTER-CLOCKWISE", 0, 400);
		g.drawString(String.format("X: %d, Y: %d, DEGREES: %d", x, y, degrees), 0, 415);
        }

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_A) { degrees--; }
		if (e.getKeyCode() == KeyEvent.VK_O) { degrees++; }
		
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) { x++; }
		if (e.getKeyCode() == KeyEvent.VK_LEFT) { x--; }
		if (e.getKeyCode() == KeyEvent.VK_DOWN) { y++; }
		if (e.getKeyCode() == KeyEvent.VK_UP) { y--; }

		//Some println stuff
		System.out.println(String.format("x: %d, y: %d, degrees: %d", x, y, degrees));
		repaint();
        }

	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}




}
