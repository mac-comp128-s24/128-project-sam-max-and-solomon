
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.events.MouseButtonEvent;

import java.awt.Color;
import java.awt.Toolkit;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Game {

private ArrayList<Card> cardDeck;
private CanvasWindow canvas;
private int score;
private GraphicsText ui;
private ArrayList<String> names = new ArrayList<String>(Arrays.asList("A", "A", "B", "B",
"C", "C", "D", "D", "E", "E", "F", "F", "G", "G", "H", "H", "I", "I", "J", "J", "K", "K", "L", "L",
"M", "M", "N", "N", "O", "O", "P", "P", "Q", "Q", "R", "R")); //Placeholder for names
private ArrayList<Card> flippedCards;
private final int WINDOW_WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
private final int WINDOW_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    public static void main(String[] args){
        Game g = new Game();
        g.run();
    }

    public Game(){
        cardDeck = new ArrayList(36);
        flippedCards = new ArrayList(2);
        canvas = new CanvasWindow("Memory Match", WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    /**
     * generates a deck of 36 cards, adds them to the canvas, handles click, adds UI
     */
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
        if (flippedCards.size() == 2){   
            checkMatch(flippedCards.get(0), flippedCards.get(1));
        }
        else{
            for (Card card : cardDeck){
                if (checkBounds(event.getPosition(), card)){
                    card.flip();
                    if (!flippedCards.contains(card)){
                        flippedCards.add(card);
                    }
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
        ui.setFontSize(50);
        ui.setPosition(WINDOW_WIDTH * 0.75, WINDOW_HEIGHT * 0.3);
        canvas.add(ui);
    }

    /**
     * creates 36 cards
     */
    private void generateDeck(){
        int y = 10;
        for (int i = 0; i < 36; i++){
            Card card = new Card(generateNames(names), i);
            int x = 85 + (95 * (i % 6));
            if (i % 6 == 0){
                y += 95;
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
            score += card1.getValue();
            score += card2.getValue();
            canvas.remove(card1);
            canvas.remove(card2);
            cardDeck.remove(card1);
            cardDeck.remove(card2);
            
        }
        else{
            card1.resetGraphics(); 
            card2.resetGraphics();
            score -= 5;
        }
        flippedCards.remove(card1);
        flippedCards.remove(card2);
        updateUI();
        checkWin();  
        
    }

    /**
     * checks if the click position is on a card
     * @param eventPosition
     * @param card
     * @return
     */
    private boolean checkBounds(Point eventPosition, Card card){
        if(eventPosition.getX() <= card.getMaxX() && eventPosition.getX() >= card.getX()){
            return (eventPosition.getY() <= card.getMaxY() && eventPosition.getY() >= card.getY());
        }
        else{
            return false;
        }
    }

    //generates a name at random
    private String generateNames(ArrayList<String> nameList){
        Random random = new Random();
        int choice = random.nextInt(0, names.size());
        String name = names.get(choice);
        names.remove(choice);
        return name;
    }

    /**
     * checks if the game is over by cardDeck size, then add congratulatory text to the canvas
     */
    private void checkWin(){
        if (cardDeck.size() == 0){
            GraphicsText text = new GraphicsText("You win!");
            text.setFontSize(100);
            text.setPosition(WINDOW_WIDTH/2, WINDOW_HEIGHT/2);
            canvas.add(text);
        }
    }

    /**
     * reshuffles the remaining deck
     */
    private void reshuffle(){
        HashMap<String, Integer> remainingCards = new HashMap<String, Integer>();
        int y = 10;
        for (Card card : cardDeck){
            canvas.remove(card);
            remainingCards.put(card.getName(), card.getValue());
        }
        ArrayList<String> newNames = new ArrayList(Arrays.asList(remainingCards.keySet()));
        for (int i = 0; i < cardDeck.size(); i++){
            String name = generateNames(newNames);
            Card card = new Card(name, remainingCards.get(name));
            int x = 85 + (95 * (i % 6));
            if (i % 6 == 0){
                y += 95;
            }
            card.setPosition(x, y);
        }
    }
}
