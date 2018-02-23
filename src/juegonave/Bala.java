package juegonave;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;



public class Bala {
    
    double angulo;
    double resto;
    double anguloRAD;
    
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
        resto = angulo % 360;
        anguloRAD = Math.toRadians(resto);
        
        dirX = Math.sin(anguloRAD);
        dirY = Math.cos(anguloRAD);
    }
    
    public void moverBala() {
        
        //resto = angulo % 360;
        //anguloRAD = Math.toRadians(resto);
        
        posX += dirX * vel;
        posY += -(dirY * vel);
        
//        if (posX >= ventanaX){
//                posX = 0;
//        }
//        if (posY >= ventanaY){
//                posY = 0;
//        }
//        if (posX < 0){
//                posX = ventanaX;
//        }
//        if (posY < 0){
//                posY = ventanaY;
//        }
                
        formaBala.setCenterX(posX);
        formaBala.setCenterY(posY);
    }
    
    public Circle getBala() {  
        return formaBala;
    }
}
