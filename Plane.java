import java.awt.Graphics;
import java.awt.Color;

public class Plane{
    
    private Vector equation;
    public Plane(Vector standardEq, Color color){
        equation = standardEq.clone();

        float pieceSideLength = .5f;
        for(float x=-10;x<10;x+=pieceSideLength){
            for(float y=-10;y<10;y+=pieceSideLength){
                new PlanePiece(equation, new Vector(x,0,y), pieceSideLength, color);
            }
        }
    }

}
