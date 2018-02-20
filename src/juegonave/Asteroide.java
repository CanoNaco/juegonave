package juegonave;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Asteroide {
    Polygon asteroide = new Polygon ();
    
    double angulo = Math.random()*359;
    double direccion = angulo % 360;
    double anguloRAD = Math.toRadians(direccion);
    double dirX = Math.sin(anguloRAD);
    double dirY = Math.cos(anguloRAD);
    double velX;
    double velY;
    double posX = 20;
    double posY = 20;
    final int ventanaX =1300;
    final int ventanaY =720;
    
        
    public Asteroide(){
        asteroide.setFill(Color.BLUE);
        asteroide.getPoints().addAll(new Double[]{
            0.0, 10.0,
            40.0, -10.0,
            100.0, 10.0,
            110.0, 60.0,
            50.0, 80.0,
            -20.0, 50.0
        });    
    }   
    
    public void movAsteroide(){
        posX += dirX;
        posY += dirY;
        asteroide.setLayoutX(posX);
        asteroide.setLayoutY(posY);
    }
    
    public Polygon getAsteroide(){
        return asteroide;
    }   
}
