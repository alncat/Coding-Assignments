package balls;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class EclipseBall extends ABall {

  public EclipseBall(Point loc, int radius, Point velocity, Color color,
      Component canvas) {
    // TODO Auto-generated constructor stub
    super(radius, color, velocity, loc, canvas);
    //double Bradius = 0.0;
  }
@Override
  public void paint(Graphics g) {    
  Shape ellipse = new Ellipse2D.Double(20.0, 10.0, 20.0, 10.0);
    g.setColor(Color.BLUE);
    ((Graphics2D) g).fill(ellipse);
  }
  
  @Override
  public void updateState() {
    // TODO Auto-generated method stub
    
  }

}
