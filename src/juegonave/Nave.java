package juegonave;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Nave {
    
    Polygon poligonoNave = new Polygon ();
      
    double angulo;
    double resto;
    double anguloRAD;
    
    int velGiro;
    
    double dirX;
    double dirY;
    
    double velX;
    double velY;
    
    final int ventanaX =1300;
    final int ventanaY =720;
    
    double posX = ventanaX/2;
    double posY = ventanaY/2;
    
    public Nave() {
        poligonoNave.setFill(Color.RED);
        poligonoNave.getPoints().addAll(new Double[]{
            0.0, -30.0,
            10.0, 0.0,
            0.0, -5.0,
            -10.0, 0.0});
    };
    
    public Polygon getNave(){
        return poligonoNave;
    }
    
    public void giro(int valorVelocidad){
        velGiro = valorVelocidad;   
    }
    
    public void moverNave(){
        resto = angulo % 360;
        anguloRAD = Math.toRadians(resto);
        
        posX += velX;
        posY += velY;
        
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
                
        poligonoNave.setLayoutX(posX);
        poligonoNave.setLayoutY(posY);
        
        angulo += velGiro;
        
        poligonoNave.setRotate(resto);
       
    }
    
    public void acelerar(){
        dirX = Math.sin(anguloRAD);
        dirY = Math.cos(anguloRAD);
        
        velX += (dirX * 0.2);
        velY += (-dirY * 0.2);
    }
    
}
