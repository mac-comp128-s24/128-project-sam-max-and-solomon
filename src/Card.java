import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Rectangle;
import java.awt.Color;


public class Card extends GraphicsGroup{
    private String name;
    private int value;
    private int WIDTH = 85;
    private int HEIGHT = 85;
    private GraphicsGroup flipGroup;
    
    /**
     * creates a card object with a name and a value of points
     * @param name 
     * @param value
     */
    public Card(String name, int value){
        this.name = name;
        this.value = value;
        flipGroup = new GraphicsGroup(0,0);
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
     *
     * @return name of the card
     */
    public String getName(){
        return name;
    }

    /**
     * 
     * @return score value of the card
     */
    public int getValue(){
        return value;
    }

    /**
     * creates a plain rectangle representing the card
     */
    public void setGraphics(){
        Rectangle base = new Rectangle(0, 0, WIDTH, HEIGHT);
        base.setFilled(true);
        base.setFillColor(Color.BLACK);
        this.add(base);
        
    }

    /**
     * removes the text and red from the group
     */
    public void resetGraphics(){
        this.remove(flipGroup);
        setGraphics();
    }

    /**
     * changes the card to red
     */
    public void flip(){
        Rectangle faceCard = new Rectangle(0, 0, WIDTH, HEIGHT);
        faceCard.setFillColor(Color.red);
        flipGroup.add(faceCard);

        GraphicsText text = new GraphicsText(name);
        text.setPosition(WIDTH/2, HEIGHT / 2);
        text.setFillColor(Color.BLACK);
        flipGroup.add(text);

        this.add(flipGroup);
    }

    /**
     * 
     * @return the maximum x value of the card
     */
    public double getMaxX(){
        return (this.getX() + WIDTH);
    }

    /**
     * 
     * @return the maximum y value of the card
     */
    public double getMaxY(){
        return (this.getY() + HEIGHT);
    }    
}
