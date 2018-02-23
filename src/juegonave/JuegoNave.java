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
      
    //variable angulo de la nave
    double naveAngulo;
    //variable direccion (aplicado de 0 a 359 grados)
    double direccion;
    //variable direccion convertida en radianes
    double direccionRAD;
    //variable direccion X aplicando seno del angulo
    double navDirX;
    //variable direccion Y aplicando coseno del angulo
    double navDirY;
    //variable de velocidad de la nave
    double navVelX;
    double navVelY;
   
    double velNave;
    //variable de velocidad de giro
    int navVelGiro;
    //Variables de la posicion nave
    double posX = 400;
    double posY = 200;
    //
    double AstAngulo = Math.random()*359;
    
          
    //Variables ventana
    final int ventanaX =1300;
    final int ventanaY =700;
    Scene ventana;
    
    Nave nave = new Nave();
    
    Asteroide asteroide = new Asteroide();
    
    Bala bala;
    
    
    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        ventana = new Scene(root, ventanaX, ventanaY, Color.WHITE);
        primaryStage.setScene(ventana);
        primaryStage.show();
              
        root.getChildren().add(nave.getNave());
        
        root.getChildren().add(asteroide.getAsteroide());
               
        ventana.setOnKeyPressed((KeyEvent event) -> {
            
            switch(event.getCode()){
                case RIGHT:
                    nave.giro(2);
                    break;
                case LEFT:
                    nave.giro(-2);
                    break;
                case UP:
                    nave.acelerar();
                    break;
                case SPACE:
                    bala = new Bala(nave.posX, nave.posY, nave.angulo);
                    root.getChildren().add(bala.getBala());
                    break;
            }
        });
        
        ventana.setOnKeyReleased((KeyEvent event) -> {
            nave.giro(0);
        });

        //Llamada a la animación
        animacionNave.start();
    }//Cierre Método Start
    
    AnimationTimer animacionNave = new AnimationTimer() {
            @Override
        public void handle(long now) {
            
            asteroide.movAsteroide();
            
            nave.moverNave();
            
            if (bala != null){
                bala.moverBala();
            }    
                
        }
    };
}
