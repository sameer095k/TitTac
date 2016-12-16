import javax.swing.*;   	// for swing components

// for ActionListener
import java.awt.*;      	// for Font
import java.awt.event.*;	// for ActionListener \

public class Tactic extends JFrame
	implements ActionListener 

{
	//Declare your instance objects here
	JPanel mainPanel = new JPanel();
	JPanel miniPanel[] = new JPanel[9]; 
	JLabel gameLabel = new JLabel("     Tic Tac Toe      ");
	JLabel label[] = new JLabel[9];
	JButton playButtons[] = new JButton[9];
	ImageIcon x = new ImageIcon(new ImageIcon("tictac0.jpg").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
	ImageIcon o = new ImageIcon(new ImageIcon("tictac1.jpg").getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT));
	char c[] = new char[9];
	int turn;
	
	
	JLabel programmerNameLabel = new JLabel ("Programmed by Mud and Sam"); 
	Font bigFont = new Font("Times New Roman", Font.BOLD, 28);
	Font programmerFont = new Font("Arial", Font.ITALIC, 9); 
	
	
	//Declare constants
	final int SIZE_WIDTH_INTEGER = 300;
	final int SIZE_HEIGHT_INTEGER = 300;
	final int SIZE_BUTTON = 300;
	

	public static void main(String[] args)
	{
		Tactic basicGUI = new Tactic();
		basicGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}//end of main

	//This is the constructor for this class. It will be called from main
	//It will set up our GUI panel with needed components
	//and set up appropriate event listening
	public Tactic()
	{
		
		//add GUI components to the appropriate container 
			
		instantiate();
		setSizes();
		setPanels();
		addListeners();

		repaint();
		revalidate();
		//add the JPanel to the JFrame
		add(mainPanel);
		//set the properties of the JFrame for display		
		this.setSize(SIZE_WIDTH_INTEGER, SIZE_HEIGHT_INTEGER);
		this.setVisible(true);
	}

	
	public void instantiate()
	{
		for(int count = 0; count < 9; count++)
		{
			miniPanel[count] = new JPanel();
			playButtons[count] = new JButton();
			label[count] = new JLabel();
			c[count] = 'a';
			//System.out.println(c[count]);
		}
	}
	
	public void setSizes()
	{
		mainPanel.setSize(SIZE_WIDTH_INTEGER,SIZE_HEIGHT_INTEGER);
		mainPanel.setLayout(new GridLayout(3,3));  
		mainPanel.setBackground(Color.BLUE);
		for(int count = 0; count < 9; count++)
		{
			playButtons[count].setSize(SIZE_BUTTON,SIZE_BUTTON); 
			miniPanel[count].setSize(SIZE_BUTTON,SIZE_BUTTON);
			miniPanel[count].setLayout(new GridLayout(1,1));
		}
	}
	
	public void setPanels()
	{
		for(int i = 0; i < 9; i++)
		{
			miniPanel[i].setBackground(Color.BLACK);
			miniPanel[i].add(playButtons[i]);
		}
		for(int i = 0; i <9; i++)
			mainPanel.add(miniPanel[i]);
	}
	
	public void addListeners()	
	{
		for(int i = 0; i < 9; i++)
			playButtons[i].addActionListener(this);
	}

	public void	actionPerformed(ActionEvent e)
	{
  	    Object sourceObject = e.getSource();
		ImageIcon p = new ImageIcon();
		char a;
		final char b = 'b';
  	    int t = -1;
  	    

		for(int i = 0; i < 9; i++)
		{
			if(sourceObject == playButtons[i])
				t = i;
		}
		if(turn%2==0)
		{
			p = x;
			a ='x';
		}
		else //if(turn%2==1)
		{
			p = o;
			a = 'o';
		}
		c[t] = a;

		miniPanel[t].remove(playButtons[t]);
		label[t].setIcon(p);
		miniPanel[t].add(label[t]);
		repaint();
		revalidate();
		if(turn >= 4)
		{
			if(checkwinner())
			{
				System.out.println("Winner: " + a);
				System.out.println("Sam and Mud Productions");
				for(int i = 0; i < 9; i++)
					playButtons[i].setEnabled(false);
			}
			else if(turn == 8)
			{
				System.out.println("TIE GAME");
				System.out.println("Sam and Mud Productions");
				for(int i = 0; i < 9; i++)
					playButtons[i].setEnabled(false);
			}
		}
		turn++;
	}
	
	public boolean checkwinner()
	{
		//check vertical
		for(int i = 0; i < 3; i++)
			if((c[i] != 'a' && c[i+3] != 'a' && c[i+6] != 'a') && 
					(c[i] == c[i+3] && c[i+3] == c[i+6]))
		    {
		        return true;
		    }		
		//check horizontal
	    for(int i = 0; i <= 6; i+=3)
	    {
	        if((c[i] != 'a' && c[i+1] != 'a' && c[i+2] != 'a') && 
	        (c[i] == c[i+1] && c[i] == c[i+2]))
		    {
		        return true;
		    }	    
	    }
	    //Check Diagonals
	    char topLeft = c[0], topRight = c[2], middle = c[4],
	            bottomLeft = c[6], bottomRight = c[8];
	    
	    //Check Left Diagonal
	    if((topLeft != 'a' && bottomRight != 'a' && middle!= 'a') &&
	    	(topLeft == middle && middle == bottomRight))
	    {
	        return true;
	    }
	    //Check Right Diagonal
	    if(topRight == 'a')
	    	return false;
	    if(middle == 'a')
	    	return false;
	    if(bottomLeft == 'a')
	    	return false;
	    if((topRight == middle && middle == bottomLeft))
	    {
	        return true;
	    }		 
		return false;
	}
}//end of class