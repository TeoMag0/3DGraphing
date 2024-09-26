import java.util.ArrayList;
import java.awt.Graphics;

public abstract class Drawable {
    private static final ArrayList<Drawable> objects = new ArrayList<>();
    private Vector pos;

    public Drawable(Vector pos){
        this.pos = pos.clone();
        objects.add(this);
    }

    public static void draw(Graphics g){
        sortObjects();
        for(Drawable each : objects){
            each.drawMe(g);
        }
    }
    public Vector pos(){
        return pos.clone();
    }
    public void move(Vector v){
        pos = Vector.sum(pos, v);
    }
    private static void sortObjects(){
        for(int i=0;i<objects.size()-1;i++){
            for(int j=0;j<objects.size()-i-1;j++){
                if(Vector.dist(Screen.cam.pos(), objects.get(j).pos()) < Vector.dist(Screen.cam.pos(), objects.get(j+1).pos())){
                    Drawable temp = objects.get(j);
                    objects.set(j, objects.get(j+1));
                    objects.set(j+1, temp);
                }
            }
        }
    }

    public abstract void drawMe(Graphics g);
}
