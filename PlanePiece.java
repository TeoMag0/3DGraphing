import java.awt.Graphics;
import java.awt.Color;

public class PlanePiece extends Drawable{
    
    private Vector equation;
    private float sideLength;
    private Color color;
    
    public PlanePiece(Vector equation, Vector pos, float sideLength, Color color){
        super(pos);
        this.equation = equation;
        this.sideLength = sideLength;
        this.color = color;
    }

    public void drawMe(Graphics g){
        Color c = g.getColor();
        g.setColor(color);

        Vector[] corners = new Vector[] {
            new Vector(pos().x(), yVal(pos().x(), pos().z()), pos().z()), 
            new Vector(pos().x()+sideLength, yVal(pos().x()+sideLength, pos().z()), pos().z()),
            new Vector(pos().x()+sideLength, yVal(pos().x()+sideLength, pos().z()+sideLength), pos().z()+sideLength),
            new Vector(pos().x(), yVal(pos().x(), pos().z()+sideLength), pos().z()+sideLength),
        };
        Vector[] screenCorners = new Vector[]{
            Point.getScreenCoords(corners[0]), 
            Point.getScreenCoords(corners[1]), 
            Point.getScreenCoords(corners[2]), 
            Point.getScreenCoords(corners[3]), 
        };
        for(Vector each : screenCorners){
            if(Float.isNaN(each.x())){
                return;
            }
        }
        int[] xPoints = new int[]{(int)screenCorners[0].x(), (int)screenCorners[1].x(), (int)screenCorners[2].x(), (int)screenCorners[3].x()};
        int[] yPoints = new int[]{(int)screenCorners[0].y(), (int)screenCorners[1].y(), (int)screenCorners[2].y(), (int)screenCorners[3].y()};
        
        Color c2 = g.getColor();
        g.setColor(c2.darker());
        g.fillPolygon(xPoints, yPoints, 4);
        g.setColor(c2);
        g.drawPolygon(xPoints, yPoints, 4);

        g.setColor(c);
    }
    private float yVal(float x, float z){
        return -(equation.get(3)+z*equation.z()+x*equation.x())/equation.y();
    }
}
