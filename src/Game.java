import java.awt.Color;
import java.util.ArrayList;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;

public class Game {

private ArrayList<Card> cardDeck;
private CanvasWindow canvas;
private int score;

    public static void main(String[] args){
        Game g = new Game();
        g.run();
    }

    public Game(){
        cardDeck = new ArrayList(9);
        canvas = new CanvasWindow("Memory Match", 500, 500);
    }

    public void run(){
        generateDeck();
        for (Card card: cardDeck){
            card.setGraphics();
            canvas.add(card);
        }
        canvas.onMouseDown(event -> event.getPosition());
        addUI();
        canvas.draw();
    }
    
    public void addUI(){
        GraphicsText ui = new GraphicsText();
        ui.setText("Points: " + score);
        ui.setFilled(true);
        ui.setFillColor(Color.BLACK);
        ui.setFontSize(20);
        ui.setPosition(50, 50);
        canvas.add(ui);
    }

    /**
     * creates 9 cards
     */
    public void generateDeck(){
        int y = -5;
        for (int i = 0; i < 9; i++){
            String name = "Card" + i;
            Card card = new Card(name, i);
            int x = 50 + (55 * (i % 3));
            if (i % 3 == 0){
                y += 55;
            }
            card.setPosition(x, y);
            cardDeck.add(card);
        }
    }
}
