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
}
