package juegonave;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;



public class Bala {
    
    double angulo;
    double resto;
    double anguloRAD;
    
    int velGiro;
    
    double dirX = Math.sin(anguloRAD);
    double dirY = Math.cos(anguloRAD);
    
    double velX = 5;
    double velY = 5;
    
    final int ventanaX =1300;
    final int ventanaY =720;
    
    double posX;
    double posY;
    
    Circle formaBala;
    
    public Bala(double x, double y, double angulo) {
        
        formaBala = new Circle(x, y, 3);
        formaBala.setFill(Color.RED);
        
       
    }
    
    public void moverBala() {
        
        resto = angulo % 360;
        anguloRAD = Math.toRadians(resto);
        
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
                
        formaBala.setLayoutX(posX);
        formaBala.setLayoutY(posY);
    }
    
    public Circle getBala() {  
        return formaBala;
    }
}
