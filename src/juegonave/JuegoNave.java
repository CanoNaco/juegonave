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
    
    ArrayList <Bala> listaBala = new ArrayList();
    
    Pane root = new Pane();
    
    //Text gameOver = new Text ("Game Over");
    
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
        });
        
        root.getStylesheets().add("estilo/estilo.css");
        
        ventana.setOnKeyReleased((KeyEvent event) -> {
            nave.giro(0);
        });
        //Llamada a la animación
        animacionNave.start();
        //mostrar mensaje de fin de partida
        
        HBox gameOver = new HBox();
        gameOver.setTranslateX(100);
        gameOver.setTranslateY(100);
        gameOver.setMinWidth(ventanaX);
        gameOver.setAlignment(Pos.CENTER);
        gameOver.setSpacing(100);
        
        root.getChildren().add(gameOver);
        
        Text textoGameOver = new Text("Game Over");
        textoGameOver.setFont(Font.font(50));
        textoGameOver.setFill(Color.WHITE);
        
        gameOver.getChildren().add(textoGameOver);
    }//Cierre Método Start
    
    AnimationTimer animacionNave = new AnimationTimer() {
            @Override
        public void handle(long now) {
                       
            asteroide.movAsteroide();
            
            nave.moverNave();
               
            for(int i=0; i<listaBala.size(); i++){
                Bala bala = listaBala.get(i);
                bala.moverBala();
            }

            Shape perder = Shape.intersect(nave.poliNave,asteroide.poliAsteroide);
            boolean perderVacio = perder.getBoundsInLocal().isEmpty();
            
            if (perderVacio == false){
                System.out.println("Game Over");
            }
            
//            Shape punto = Shape.intersect(bala.formaBala,asteroide.poliAsteroide);
//            boolean puntoVacio = perder.getBoundsInLocal().isEmpty();
//            
//            if (puntoVacio == false){
//                System.out.println("punto");
//            }
        }
    };
}
