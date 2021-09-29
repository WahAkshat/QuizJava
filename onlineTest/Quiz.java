import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Quiz extends JFrame implements ActionListener
{
	JLabel l;
	JButton b1,b2;
	ButtonGroup bg;
	JRadioButton jb[]=new JRadioButton[5];

	int count=0,current=0,x=1,y=1,now=0;
	int m[]=new int[4];

	public static void main(String s[])
	{
		new Quiz("Online Test Of Indian Prime Ministers");
	}
	
	Quiz(String s) //Parameterized Constructor
	{
		super(s);
		l=new JLabel();
		add(l);
		bg=new ButtonGroup();
		for(int i=0;i<5;i++)
		{
			jb[i]=new JRadioButton();	
			add(jb[i]);
			bg.add(jb[i]);
		}
		b2=new JButton("Bookmark"); //bookmarked questions cannot be marked for score
		b1=new JButton("Next Question");

		b1.addActionListener(this);
		b2.addActionListener(this);
		add(b1);add(b2);

		question_set();
		l.setBounds(30,40,550,20);
		jb[0].setBounds(50,80,180,20);
		jb[1].setBounds(50,110,180,20);
		jb[2].setBounds(50,140,180,20);
		jb[3].setBounds(50,170,180,20);
		b1.setBounds(100,240,180,30);
		b2.setBounds(300,240,180,30);
		b1.setBackground(Color.ORANGE);
		b2.setBackground(Color.PINK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setLocation(250,100);
		setVisible(true);
		setSize(600,350);
	}

	void question_set() //QUESTIONS
	{
		jb[4].setSelected(true);
		if(current==0)
		{
			l.setText("Que1: Who was the Prime Minister of India, when the 1991 reforms in India was implemented?");
			jb[0].setText("Manmohan Singh");jb[1].setText("PV Narasimha Rao");jb[2].setText("Indira Gandhi");
			jb[3].setText("Rajiv Gandhi");
		}
		if(current==1)
		{
			l.setText("Que2: The Indian Prime Minister launched the ‘Swarna Jayanti’ scholarships for the youth of which country?");
			jb[0].setText("Sri Lanka");jb[1].setText("Nepal");jb[2].setText("Bangladesh");jb[3].setText("Myanmar");
		}
		if(current==2)
		{
			l.setText("Que3: In February 2019, Prime Minister Narendra Modi laid the foundation stone for the construction " +
					"Indian Institutes of Technology (IIT) and Indian Institutes of " +
					"Information Technology (IIIT) in one of the cities of Karnataka. What is the name of that city?");
			jb[0].setText("Tumakuru");jb[1].setText("Raichur");jb[2].setText("Chikmagalur");jb[3].setText("Dharwad");
		}
		if(current==3)
		{
			l.setText("Que4: The Prime Minister of India recently laid foundation stone of Metro Service and " +
					"flagged off eight trains in which State?");
			jb[0].setText("Gujarat");jb[1].setText("Bihar");jb[2].setText("Uttar Pradesh");jb[3].setText("Madhya Pradesh");
		}

		l.setBounds(30,40,450,20);
		for(int i=0,j=0;i<=90;i+=30,j++)
			jb[j].setBounds(50,80+i,200,20);
	}

	boolean ans_check() //answer key
	{
		if(current==0)
			return(jb[1].isSelected());
		if(current==1)
			return(jb[2].isSelected());
		if(current==2)
			return(jb[3].isSelected());
		if(current==3)
			return(jb[0].isSelected());
		return false;
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==b1)
		{
			if(ans_check())
				count=count+1;
			current++;
			question_set();	// calling the question set
			if(current==3)
			{
				b1.setEnabled(false);
				b2.setText("Result");
			}
		}
		if(e.getActionCommand().equals("Bookmark"))
		{
			JButton bk=new JButton("Bookmark"+x);
			bk.setBounds(480,50+30*x,100,30);
			add(bk);
			bk.addActionListener(this);
			m[x]=current;
			x++;
			current++;
			question_set();
			if(current==3)
				b2.setText("Result");
			setVisible(false);
			setVisible(true);
		}
		for(int i=0,y=1;i<x;i++,y++)
		{
		if(e.getActionCommand().equals("Bookmark"+y))
		{
			if(ans_check())
				count=count+1;
			now=current;
			current=m[y];
			question_set();
			((JButton)e.getSource()).setEnabled(false);
			current=now;
		}
			if(e.getActionCommand().equals("Result"))
			{
				if(ans_check())
					count=count+1;
				current++;
				String mess = (count<2)?"\nYou scored less than 50 percent and should work hard":"\nYou have scored " +
						">= 50 percent and it is decent score!";
				JOptionPane.showMessageDialog(this,"Marks scored is "+count+"/4"+mess);
				System.exit(0);
			}}}
}
