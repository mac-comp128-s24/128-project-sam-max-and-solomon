import java.awt.Color;
import java.util.ArrayList;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.events.MouseButtonEvent;

public class Game {

private ArrayList<Card> cardDeck;
private CanvasWindow canvas;
private int score;
private GraphicsText ui;
private ArrayList<Card> flippedCards;

    public static void main(String[] args){
        Game g = new Game();
        g.run();
    }

    public Game(){
        cardDeck = new ArrayList(36);
        flippedCards = new ArrayList(2);
        canvas = new CanvasWindow("Memory Match", 500, 500);
    }

    private void run(){
        generateDeck();
        for (Card card: cardDeck){
            card.setGraphics();
            canvas.add(card);
        }
        canvas.onMouseDown(event -> handleClick(event));
        
        addUI();
        canvas.draw();
    }

    /**
     * Flips the card when clicked, then prompts match checking as appropriate
     * @param event
     */
    private void handleClick(MouseButtonEvent event){
        System.out.println("Click detected");
        if (flippedCards.size() == 2){  
            System.out.println(flippedCards.get(0).getName() + flippedCards.get(1).getName()); 
            checkMatch(flippedCards.get(0), flippedCards.get(1));
        }
        else{
            for (Card card : cardDeck){
                if (checkBounds(event.getPosition(), card)){
                    card.flip();
                    flippedCards.add(card);
            }
        }
    }
        
    }
    
    /**
     * adds UI to the canvas
     */
    private void addUI(){
        ui = new GraphicsText();
        ui.setText("Points: " + score);
        ui.setFilled(true);
        ui.setFillColor(Color.BLACK);
        ui.setFontSize(20);
        ui.setPosition(50, 50);
        canvas.add(ui);
    }

    /**
     * creates 36 cards
     */
    private void generateDeck(){
        int y = 0;
        for (int i = 0; i < 36; i++){
            String name = "Card" + (i % 18);
            Card card = new Card(name, i);
            int x = 50 + (55 * (i % 6));
            if (i % 6 == 0){
                y += 55;
            }
            card.setPosition(x, y);
            cardDeck.add(card);
        }
    }

    /** 
     * adds the UI
     */
    private void updateUI(){
        if (ui != null){
            ui.setText("Points: " + score);
        }    
    }

    /**
     * checks if the cards match
     * @param card1
     * @param card2
     */
    private void checkMatch(Card card1, Card card2){
        if (card1.getName().equals(card2.getName())){
            System.out.println("Match detected");
            score += card1.getValue();
            score += card2.getValue();
            canvas.remove(card1);
            canvas.remove(card2);
            updateUI();
        }
        else{
            card1.resetGraphics(); 
            card2.resetGraphics();
        }
        
        flippedCards.remove(card1);
        flippedCards.remove(card2);
        
    }

    private boolean checkBounds(Point eventPosition, Card card){
        if(eventPosition.getX() <= card.getMaxX() && eventPosition.getX() >= card.getX()){
            return (eventPosition.getY() <= card.getMaxY() && eventPosition.getY() >= card.getY());
        }
        else{
            return false;
        }
    }

    
}
