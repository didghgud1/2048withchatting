package maingui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import data.*;

public class MainUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4475565040148873169L;
	//DB Manager
	dataManager dbController;
	//UI element
	//Game Area
	private static MainUI Instance;
	private JPanel jpGamePanel;
	private JButton[][] jbNumber;
	//chatting
	private JTextArea jtChattingTextArea;
	private JScrollPane jsChattngTextPane;
	private JTextArea jtInputTextArea;
	private JScrollPane jsInputTextPane;
	private JButton jbClickButton;
	
	public MainUI() {
		super("2048 Games with Chatting");
		
		this.setLayout(null);
		this.setSize(850, 450);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static MainUI getInstance() {
		if(Instance == null) {
			Instance = new MainUI();
		}
		return Instance;
	}
	
	public void initialize() {
		dbController = new dataManager();
		
		createResource();
		setResourceListener();
		setResourceOSDData();
	}
	
	public void showUI() {
		refreshUI();
		this.setVisible(true);
	}
	
	private void createResource() {
		//Game UI Initialize.
		jpGamePanel = new JPanel();
		jbNumber = new JButton[4][4];
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				jbNumber[i][j] = new JButton("no."+(i*4+j+1));
			}
		}
		//chatting UI Initialize
		jtChattingTextArea = new JTextArea("No Text", 400, 300);
		jtChattingTextArea.setEditable(false);
		jsChattngTextPane = new JScrollPane(jtChattingTextArea);
		jtInputTextArea = new JTextArea();
		jsInputTextPane = new JScrollPane(jtInputTextArea);
		jbClickButton = new JButton("Send");

	}

	private void setResourceListener() {
		this.setFocusable(true);
		this.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Key Pressed key code is" + e.getKeyCode());
				dbController.processKeyData(e.getKeyCode());
				refreshUI();
			}
		});
	}
	
	private void setResourceOSDData() {
		Container container = this.getContentPane();
		
		jpGamePanel.setBounds(0, 10, 400, 400);
		for(int i=1; i<=4; i++) {
			for(int j=1; j<=4; j++) {
				jbNumber[i-1][j-1].setBounds(100*(j-1), 100*(i-1), 100, 100);
				jbNumber[i-1][j-1].setPreferredSize(new Dimension(80, 80));
				jbNumber[i-1][j-1].setBorderPainted(false);
				jbNumber[i-1][j-1].setFont(new Font("Arial", Font.PLAIN, 20));
				jbNumber[i-1][j-1].setBackground(Color.WHITE);
				jbNumber[i-1][j-1].setEnabled(false);
				jpGamePanel.add(jbNumber[i-1][j-1]);
			}
		}
		
		jsChattngTextPane.setBounds(400, 10, 400, 300);
		jsInputTextPane.setBounds(400, 320, 320, 80);
		jbClickButton.setBounds(720, 320, 80, 80);
		container.add(jpGamePanel);
		container.add(jsChattngTextPane);
		container.add(jsInputTextPane);
		container.add(jbClickButton);
	}

	private void setButtonColor(JButton jbButton, int num) {
		switch(num) {
		case 2 :
			jbButton.setBackground(Color.RED);
			break;
		case 4 :
			jbButton.setBackground(Color.ORANGE);
			break;
		case 8 :
			jbButton.setBackground(Color.YELLOW);
			break;
		case 16 :
			jbButton.setBackground(Color.GREEN);
			break;
		case 32 :
			jbButton.setBackground(Color.BLUE);
			break;
		case 64 :
			jbButton.setBackground(Color.GRAY);
			break;
		case 128 :
			jbButton.setBackground(Color.PINK);
			break;
		case 256 :
			jbButton.setBackground(Color.LIGHT_GRAY);
			break;
		case 512 :
			jbButton.setBackground(Color.CYAN);
			break;
		case 1024 :
			jbButton.setBackground(Color.DARK_GRAY);
			break;
		case 2048 :
			jbButton.setBackground(Color.MAGENTA);
			break;
		case 0 :
		default :
			jbButton.setBackground(Color.WHITE);
			break;
		}
	}
	
	private void refreshUI() {
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				jbNumber[i][j].setText(dbController.getBtNum(i, j) + "");
				setButtonColor(jbNumber[i][j], dbController.getBtNum(i, j));
			}
		}
		try {
			Thread.currentThread().sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
