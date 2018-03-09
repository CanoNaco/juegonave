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
    
    //colisiones
    Shape perder;
    Shape punto;
    //ventana
    Scene ventana;
    //nave
    Nave nave = new Nave();
    //asteroide
    Asteroide asteroide = new Asteroide();
    //bala
    Bala bala;
    //juego
    Juego juego = new Juego();
    //listas
    ArrayList <Bala> listaBala = new ArrayList();
    ArrayList <Asteroide> listaAster = new ArrayList();
    
    Asteroide suprAster;
    Bala suprBala;
    
    Pane root = new Pane();
        
    @Override
    public void start(Stage primaryStage) {
        
        ventana = new Scene(root, ventanaX, ventanaY, Color.WHITE);
        primaryStage.setScene(ventana);
        primaryStage.show();
              
        root.getChildren().add(nave.getNave());
        
        //root.getChildren().add(asteroide.getAsteroide());
                              
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
        //crear los asteroides
        for(int i=0; i<3; i++){
            asteroide = new Asteroide();
            listaAster.add(asteroide);
            root.getChildren().add(asteroide.getAsteroide());
            
        juego.marcador(root);
        }
        
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

                for (int o=0; o<listaAster.size(); o++){
                    Asteroide asteroide = listaAster.get(o);
                    punto = Shape.intersect(bala.formaBala,asteroide.poliAsteroide);
                    boolean puntoVacio = punto.getBoundsInLocal().isEmpty();

                    if (puntoVacio == false){
                        suprAster = listaAster.get(o);
                        suprBala = listaBala.get(i);
                        listaBala.remove(suprBala);
                        listaAster.remove(suprAster);
                        root.getChildren().remove(asteroide.getAsteroide());
                        root.getChildren().remove(bala.getBala());
                    }
                }
            }
            root.getChildren().remove(suprAster);
            root.getChildren().remove(suprBala);

            for(int i=0; i<listaAster.size(); i++){
                Asteroide asteroide = listaAster.get(i); 
                asteroide.movAsteroide();
                perder = Shape.intersect(nave.poliNave,asteroide.poliAsteroide);
                boolean perderVacio = perder.getBoundsInLocal().isEmpty();
            
                if (perderVacio == false){
                    juego.gameOver(root);
                    animacionNave.stop();   
                }
            }
        }
    };
}
