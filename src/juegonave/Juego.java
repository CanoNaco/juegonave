package juegonave;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Juego {
    
    final int ventanaX =1300;
    final int ventanaY =700;
    
    Text textScore;
    Text textHighScore;
    
    int score;
    int highScore;
    
    int tamText = 40;
    
    public void gameOver(Pane p){
        //mostrar mensaje de fin de partida
        HBox gameOver = new HBox();
        gameOver.setTranslateY(ventanaY/2);
        gameOver.setMinWidth(ventanaX);
        gameOver.setAlignment(Pos.CENTER);
        gameOver.setSpacing(100);
        
        p.getChildren().add(gameOver);
        
        Text textoGameOver = new Text("Game Over");
        textoGameOver.setFont(Font.font(50));
        textoGameOver.setFill(Color.WHITE);
        
        gameOver.getChildren().add(textoGameOver);
    }
    public void marcador (Pane root){
        //Layout Principal
        HBox paneScores = new HBox();
        paneScores.setTranslateY(20);
        paneScores.setMinWidth(ventanaX);
        paneScores.setAlignment(Pos.CENTER);
        paneScores.setSpacing(100);
        root.getChildren().add(paneScores);
        
        //Layout para puntuacion actual
        HBox paneCurrentScore = new HBox();
        paneCurrentScore.setSpacing(20);
        paneScores.getChildren().add(paneCurrentScore);
        
        //Layout para la puntuación máxima
        HBox paneHighScore = new HBox();
        paneCurrentScore.setSpacing(20);
        paneScores.getChildren().add(paneHighScore);
        
        //Texto de la etiqueta para la puntuación
        Text textTitleScore = new Text("Score:");
        textTitleScore.setFont(Font.font(tamText));
        textTitleScore.setFill(Color.WHITE);

        //texto para la puntuacion
        textScore = new Text("0");
        textScore.setFont(Font.font(tamText));
        textScore.setFill(Color.WHITE);
        
        //Texto de etiqueta ara la puntiacion maxima
        Text textTitleHighScore = new Text("Max Score: ");
        textTitleHighScore.setFont(Font.font(tamText));
        textTitleHighScore.setFill(Color.WHITE);
        
        //Texto puntuacion maxima
        textHighScore = new Text("0");
        textHighScore.setFont(Font.font(tamText));
        textHighScore.setFill(Color.WHITE);
        
        //Añadir texto a los los layout reservados para ellos
        paneCurrentScore.getChildren().add(textTitleScore);
        paneCurrentScore.getChildren().add(textScore);
        paneHighScore.getChildren().add(textTitleHighScore);
        paneHighScore.getChildren().add(textHighScore);
    }
    
    public void scoreAum(){
        score++;
        textScore.setText(String.valueOf(score));
    }
    public void scoreMax(){
        if (score > highScore) {
            highScore = score;
            textHighScore.setText(String.valueOf(highScore));
        }    
    }
    public void reset(){
        score = 0;
        textScore.setText(String.valueOf(score));
    }
}
