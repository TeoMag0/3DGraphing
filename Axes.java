import java.awt.Graphics;

public class Axes extends Drawable{
    

    public Axes(){
        super(new Vector());
        float segmentSpacing = .5f;
        float axisLength = 10;

        for(int i=0;i<3;i++){
            for(float j=0;j<axisLength;j+=segmentSpacing){
                switch(i){
                    case 0:
                        new LineSegment(new Vector(j), new Vector(j+segmentSpacing));
                        new LineSegment(new Vector(-j), new Vector(-j-segmentSpacing));
                        break;
                    case 1:
                        new LineSegment(new Vector(0,j), new Vector(0,j+segmentSpacing));
                        new LineSegment(new Vector(0,-j), new Vector(0,-j-segmentSpacing));
                        break;
                    case 2:
                        new LineSegment(new Vector(0,0,j), new Vector(0,0,j+segmentSpacing));
                        new LineSegment(new Vector(0,0,-j), new Vector(0,0,-j-segmentSpacing));
                        break;
                }
            }
        }
    }

    public void drawMe(Graphics g){
        
    }
}
