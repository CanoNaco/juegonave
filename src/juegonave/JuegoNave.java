package juegonave;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class JuegoNave extends Application {
    //Nave
    Polygon nave = new Polygon ();
    
    //variable angulo de la nave
    double NavAngulo;
    //variable direccion (aplicado de 0 a 359 grados)
    double direccion;
    //variable direccion convertida en radianes
    double direccionRAD;
    //variable direccion X aplicando seno del angulo
    double NavDirX;
    //variable direccion Y aplicando coseno del angulo
    double NavDirY;
    //variable de velocidad de la nave
    double NavVelX;
    double NavVelY;
   
    double velNave;
    //variable de velocidad de giro
    int NavVelGiro;
    //Variables de la posicion nave
    double posX = 400;
    double posY = 200;
    //
    double AstAngulo = Math.random()*359;
    
          
    //Variables ventana
    final int ventanaX =1300;
    final int ventanaY =720;
    Scene ventana;
    
    
    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        ventana = new Scene(root, ventanaX, ventanaY, Color.WHITE);
        primaryStage.setScene(ventana);
        primaryStage.show();
              
        nave.setFill(Color.RED);
        nave.getPoints().addAll(new Double[]{
            0.0, -30.0,
            10.0, 0.0,
            0.0, -5.0,
            -10.0, 0.0});
        root.getChildren().add(nave);
        
        Asteroide asteroide = new Asteroide();
        root.getChildren().add(asteroide.getPoligono());
        
        ventana.setOnKeyPressed((KeyEvent event) -> {
            
            switch(event.getCode()){
                case RIGHT:
                    NavVelGiro = 2;
                    break;
                case LEFT:
                    NavVelGiro = -2;
                    break;
                case UP:
                    //calcular direccion de la nave
                    NavDirX = Math.sin(direccionRAD);
                    NavDirY = Math.cos(direccionRAD);
                    //calcular la velocidad
                    NavVelX += (NavDirX * 0.2);
                    NavVelY += (-NavDirY * 0.2);
                    break;
            }
        });
        
        ventana.setOnKeyReleased((KeyEvent event) -> {
            NavVelGiro = 0;
        });

        //Llamada a la animación
        animationNave.start();
    }//Cierre Método Start
    
    AnimationTimer animationNave = new AnimationTimer() {
            @Override
        public void handle(long now) {
            //la direccion es el resto del angulo entre 360    
            direccion = NavAngulo % 360;
            direccionRAD = Math.toRadians(direccion);               
            //giro de la nave
            NavAngulo += NavVelGiro;
            nave.setRotate(direccion);
            //modificar la posicion en relacion a la velocidad   
            posX += NavVelX;
            posY += NavVelY;
            //para que la nave vuelva a aparecer por los bordes
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
            //cambio de direccion de la nave / movimiento
            nave.setLayoutX(posX);
            nave.setLayoutY(posY);
                
        }
    };
}
