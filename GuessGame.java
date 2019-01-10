
package guessgame;

import java.awt.FlowLayout;
import java.awt.Container;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionListener;   // import libraries
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;

class GuessGameNumber extends JFrame
{
    private int number, guessCount, lastDistance;
    private JTextField guessInput;
    private JLabel prompt1, prompt2, message;
    private JButton newGame;
    private Color background;
    Container container;
    
    public GuessGameNumber()     // constructor
    {
        super ("Guessing Game");
        guessCount = 0;
        background = Color.white;                                                                // initialize the frame
        prompt1 = new JLabel ( "I have a number between 1 and 1000. Can you guess what it is?");
        prompt2 = new JLabel ("Enter your first guess: ");
        
        guessInput = new JTextField(5);
        guessInput.addActionListener(new GuessHandler());     // add ActionListener to text field
        message = new JLabel ("Guess result appears here.");
        
        newGame = new JButton("New Game");
        newGame.addActionListener(new ActionListener ()
        {
            public void actionPerformed ( ActionEvent e)     // reset if user presses new game
            {
                message.setText("Guess Result");
                guessInput.setText("");
                guessInput.setEditable(true);
                background = Color.white;
                theGame();
                repaint();
            }
        }
        );
        
        container = getContentPane();
        container.setLayout(new FlowLayout()); 
        container.add(prompt1);
        container.add(prompt2);
        container.add(guessInput);                 // add objects to container
        container.add(message);
        container.add(newGame);
        setSize(300, 150);
        setVisible(true);
        theGame();
    }
    
    public void theGame()
    {
        number = (int)(Math.random()* 1000 + 1);  // randomly generate number between 1 and 1000
    }
    
    public void paint (Graphics g)            
    {
        super.paint(g);
        container.setBackground(background);    // set background color
    }
    
    public void react (int guess)               // react to the user's guess
    {
        guessCount++;
        int currentDistance = 1000;
        if (guessCount == 1)                   // if this is the very first guess
        {
            lastDistance = Math.abs(guess - number);  // compute distance between guess and number
            if (guess > number)
                message.setText("Too High, guess a lower number.");
            else                                                       // tell user too high or too low
                message.setText("Too Low, guess a higher number.");
            
        }
        else                                                          // not the first guess
        {
            currentDistance = Math.abs(guess - number);
            if (guess > number)
            {
                message.setText("Too High, guess a lower number.");
                background = (currentDistance <= lastDistance) ? Color.red : Color.blue;  // change red if new guess is closer, blue if not
                lastDistance = currentDistance;
            }
            else if (guess < number)
            {
                message.setText("Too Low, guess a higher number.");
                background = (currentDistance <= lastDistance) ? Color.red : Color.blue;   // change red if new guess is closer, blue if not
                lastDistance = currentDistance;
            }
            else
            {
                message.setText("Correct!");         // user guessed the number
                background = Color.white;
                guessInput.setEditable(false);
                guessCount = 0;
            }
            repaint();
        }
    }
    
    class GuessHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            int guess = Integer.parseInt(guessInput.getText());  // get the guess input by the user
            react (guess);
        }
    }
}
       

public class GuessGame {

   
    public static void main(String[] args) {
       
        GuessGameNumber app = new GuessGameNumber();        // instantiate GuessGameNumber
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // exit program when user closes window
        
        app.setSize(500,200);                      // set size of window
        app.setVisible(true);                      // make visible 
    }
    
}
