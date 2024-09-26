import javax.swing.*;
import java.awt.*;

public class Screen extends JPanel{

    public static final Vector screenPixelDimensions = new Vector(1280, 720);
    private static float pixelsPerUnit = 50;
    public static Screen Singleton;

    public static Camera cam = new Camera(new Vector(0,0,0), new Vector[]{new Vector(1, 0, 0), new Vector(0,1,0), new Vector(0, 0, 1)}, 70, screenPixelDimensions.y()/pixelsPerUnit);
    private CamControls camControls;


    public Screen(){
        setLayout(null);
        setFocusable(true);
        Singleton = this;
        
        camControls = new CamControls();
        new Thread(camControls).start();

        //new RectangularPrism(new Vector(), new Vector(1, 1, 1));

        new Axes();
        new Plane(new Vector(2, 1, 0, 0), Color.BLUE);
        new Plane(new Vector(0,1,0,3), Color.RED);
        new Plane(new Vector(0,1,1,0), Color.GRAY);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Drawable.draw(g);
    }

    public void animate(){
        while(true){
            float deltaTime = .01f;

            
            repaint();
            try {
                Thread.sleep((int) (deltaTime * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static Vector getWorldCoords(Vector coords) {
        float newX = (coords.x() - screenPixelDimensions.x() / 2) / pixelsPerUnit;
        float newY = (screenPixelDimensions.y() / 2 - coords.y()) / pixelsPerUnit;
        return new Vector(newX, newY, 0);
    }

    public static Vector getScreenCoords(Vector coords) {
        int newX = (int) ((coords.x()) * pixelsPerUnit + screenPixelDimensions.x() / 2);
        int newY = (int) (screenPixelDimensions.y() / 2 - (coords.y()) * pixelsPerUnit);
        return new Vector(newX, newY, 0);
    }

    public static int toPixels(float num) {
        return (int) (num * pixelsPerUnit);
    }

    public static float toCoords(int pixels) {
        return (float) pixels / pixelsPerUnit;
    }

    @Override
    public Dimension getPreferredSize(){
        return new Dimension(1280, 720);
    }
}