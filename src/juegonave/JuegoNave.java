package juegonave;

import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
    
    double AstAngulo = Math.random()*359;
              
    //Variables ventana
    final int ventanaX =1300;
    final int ventanaY =700;
    Scene ventana;
    
    Nave nave = new Nave();
    
    Asteroide asteroide = new Asteroide();
    
    Bala bala;
    
    Juego juego = new Juego();
    
    //listas
    ArrayList <Bala> listaBala = new ArrayList();
    ArrayList <Asteroide> listaAster = new ArrayList();
    
    Pane root = new Pane();
        
    @Override
    public void start(Stage primaryStage) {
        
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
                    listaBala.add(bala);
                    root.getChildren().add(bala.getBala());
                    break;
            }
            //crear los asteroides
            for(int i=0; i<=3; i++){
                asteroide = new Asteroide();
                listaAster.add(asteroide);
                //System.out.println(listaAster.get(i));
            }
        });
        
        root.getStylesheets().add("estilo/estilo.css");
        
        ventana.setOnKeyReleased((KeyEvent event) -> {
            nave.giro(0);
        });
        //Llamada a la animación
        animacionNave.start();
        //mostrar mensaje de fin de partida

    }//Cierre Método Start
    
    AnimationTimer animacionNave = new AnimationTimer() {
            @Override
        public void handle(long now) {
                       
            nave.moverNave();
               
            for(int i=0; i<listaBala.size(); i++){
                Bala bala = listaBala.get(i);
                bala.moverBala();
            }

            for(int i=0; i<listaAster.size(); i++){
                Asteroide asteroide = listaAster.get(i); 
                asteroide.movAsteroide();
            }
            
            Shape perder = Shape.intersect(nave.poliNave,asteroide.poliAsteroide);
            boolean perderVacio = perder.getBoundsInLocal().isEmpty();
            
            if (perderVacio == false){
                juego.gameOver(root);
                animacionNave.stop();   
            }
            
//            Shape punto = Shape.intersect(bala.formaBala,asteroide.poliAsteroide);
//            boolean puntoVacio = punto.getBoundsInLocal().isEmpty();
//            
//            if (puntoVacio == false){
//                System.out.println("punto");
//            }
        }
    };
}
