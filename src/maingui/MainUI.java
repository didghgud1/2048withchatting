package maingui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
	private JDialog jdEndPopup;
	private JButton jbResetButton;
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
		jdEndPopup = new JDialog();
		jdEndPopup.setResizable(false);
		jbResetButton = new JButton("Reset");
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
	
	private void setResourceOSDData() {
		Container container = this.getContentPane();
		
		jpGamePanel.setBounds(0, 10, 400, 400);
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				jbNumber[i][j].setBounds(100*j, 100*i, 100, 100);
				jbNumber[i][j].setPreferredSize(new Dimension(80, 80));
				jbNumber[i][j].setBorderPainted(false);
				jbNumber[i][j].setFont(new Font("Arial", Font.PLAIN, 20));
				jbNumber[i][j].setBackground(Color.WHITE);
				jbNumber[i][j].setEnabled(false);
				jpGamePanel.add(jbNumber[i][j]);
			}
		}
		jdEndPopup.setLayout(new FlowLayout());
		jdEndPopup.add(jbResetButton);
		
		jsChattngTextPane.setBounds(400, 10, 400, 300);
		jsInputTextPane.setBounds(400, 320, 320, 80);
		jbClickButton.setBounds(720, 320, 80, 80);
		container.add(jpGamePanel);
		container.add(jsChattngTextPane);
		container.add(jsInputTextPane);
		container.add(jbClickButton);
	}

	private void setResourceListener() {
		//get Game Key value
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
				//Process DB Data then change each button data
				dbController.processKeyData(e.getKeyCode());
				//redraw UI to correct information
				refreshUI();
				//Check the condition of end of this game
				int gameStatus = dbController.checkWhetherGameEnd();
				if(gameStatus == 0) {
					//end case, show game over popup. then reset game.
					//should make show popup func
					jdEndPopup.setTitle("Game Over !");
					jdEndPopup.setSize(300, 100);
					Point location = jpGamePanel.getLocationOnScreen();
					int x = location.x;
					int y = location.y;
					jdEndPopup.setLocation(x, y);
					jdEndPopup.setVisible(true);
				}else if(gameStatus == 1) {
					//end case, show game win popup. then reset game.
					//should make show popup func
					jdEndPopup.setTitle("You Win !");
					jdEndPopup.setSize(300, 100); 
					Point location = jpGamePanel.getLocationOnScreen();
					int x = location.x;
					int y = location.y;
					jdEndPopup.setLocation(x, y);
					jdEndPopup.setVisible(true);
				}
				//else case, Game is being continue
			}
		});
		
		//get dialog button, should reset the game.
		jbResetButton.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println("clicked");
				//reset game
				dbController.resetGame();
				jdEndPopup.dispose();
				refreshUI();
			}
		});
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
	}
}
