package juegonave;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;



public class Bala {
    
    double angulo;
    double resto;
    double anguloRad;
    
    int velGiro;
    
    double dirX;
    double dirY;
    
    double vel = 3;
    
    final int ventanaX =1300;
    final int ventanaY =720;
    
    double posX;
    double posY;
    
    Circle formaBala;
    
    public Bala(double x, double y, double a) {
        
        formaBala = new Circle(x, y, 3);

        formaBala.setFill(Color.RED);
        
        posX = x;
        posY = y;
        angulo = a;        
    }
    
    public void moverBala() {
        resto = angulo % 360;
        anguloRad = Math.toRadians(resto);
        
        dirX = Math.sin(anguloRad);
        dirY = Math.cos(anguloRad);
               
        posX += dirX * vel;
        posY += -(dirY * vel);
       
        formaBala.setCenterX(posX);
        formaBala.setCenterY(posY);
    }
    
    public Circle getBala() {  
        return formaBala;
    }   
    
}
