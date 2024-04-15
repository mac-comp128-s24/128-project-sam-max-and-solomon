import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Rectangle;
import java.awt.Color;

public class Card extends GraphicsGroup{
    private String name;
    private int value;
    private int x;
    private int y;
    
    /**
     * creates a card object with a name and a value of points
     * @param name 
     * @param value
     */
    public Card(String name, int value){
        this.name = name;
        this.value = value;
    }

    /**
     * sets the name
     * @param name
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * sets point value
     * @param value
     */
    public void setValue(int value){
        this.value = value;
    }

    /**
     * gets name
     * @return
     */
    public String getName(){
        return name;
    }

    /**
     * gets value
     * @return
     */
    public int getValue(){
        return value;
    }

    /**
     * creates a plain rectangle representing the card
     */
    public void setGraphics(){
        Rectangle base = new Rectangle(0, 0, 50, 50);
        base.setFilled(true);
        base.setFillColor(Color.BLACK);
        this.add(base);
    }

    /**
     * changes the card to red
     */
    public void flip(){
        Rectangle faceCard = new Rectangle(0, 0, 50, 50);
        faceCard.setFillColor(Color.red);
        this.add(faceCard);
    }
}
