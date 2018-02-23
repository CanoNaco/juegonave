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
    int velGiro = 1;
        
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
        
        if (posX >= ventanaX){
                posX = 0;
        }
        if (posY >= ventanaY){
                posY = 0;
        }
        if (posX < 0){
                posX = ventanaX;
        }
        if (posY < 0){
                posY = ventanaY;
        }
        
        asteroide.setLayoutX(posX);
        asteroide.setLayoutY(posY);
        
        angulo += velGiro;
        
        asteroide.setRotate(angulo);
    }
    
    public Polygon getAsteroide(){
        return asteroide;
    }   
}
