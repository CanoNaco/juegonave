package juegonave;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Asteroide {
    Polygon poligono = new Polygon ();
        
    public Asteroide(){
        poligono.setFill(Color.BLUE);
        poligono.getPoints().addAll(new Double[]{
            0.0, 10.0,
            40.0, -10.0,
            100.0, 10.0,
            110.0, 60.0,
            50.0, 80.0,
            -20.0, 50.0
        });    
    }
    public Polygon getPoligono(){
        return poligono;
    }
}
