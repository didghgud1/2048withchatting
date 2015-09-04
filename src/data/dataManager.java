package data;

import javax.swing.plaf.SliderUI;

public class dataManager {
	//DB
	dataDB gameDB;
	//Key data
	final static int KEY_LEFT = 37;
	final static int KEY_UP = 38;
	final static int KEY_RIGHT = 39;
	final static int KEY_DOWN = 40;
	
	public dataManager() {
		gameDB = new dataDB();
		if(gameDB == null) {
			System.out.println("Fail to create DB");
		}
		gameDB.Initialize();
	}
	
	public void processKeyData(int keyCode) {
		//37 left, 38 up, 39 right, 40 down
		buttonInfo[][] btInfo = gameDB.getAllDB();
		
		//Algorithm
		//1. Move the all buttons to each side
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				if(keyCode == KEY_LEFT) {
					if(btInfo[i][j].getNum() != 0) {
						for(int k=0; k<j; k++) {
							if(btInfo[i][k].getNum() == 0) {
								btInfo[i][k].setNum(btInfo[i][j].getNum());
								btInfo[i][j].setNum(0);
							}
						}
					}
				}else if(keyCode == KEY_RIGHT) {
					if(btInfo[i][j].getNum() != 0) {
						for(int k=3; k>=j; k--) {
							if(btInfo[i][k].getNum() == 0) {
								btInfo[i][k].setNum(btInfo[i][j].getNum());
								btInfo[i][j].setNum(0);
							}
						}
					}
				}else if(keyCode == KEY_UP) {
					if(btInfo[i][j].getNum() != 0) {
						for(int k=0; k<i; k++) {
							if(btInfo[k][j].getNum() == 0) {
								btInfo[k][j].setNum(btInfo[i][j].getNum());
								btInfo[i][j].setNum(0);
							}
						}
					}
				}else if(keyCode == KEY_DOWN) {
					if(btInfo[i][j].getNum() != 0) {
						for(int k=3; k>=i; k--) {
							if(btInfo[k][j].getNum() == 0) {
								btInfo[k][j].setNum(btInfo[i][j].getNum());
								btInfo[i][j].setNum(0);
							}
						}
					}
				}
			}
		}
		
		//2. Merge the buttons.
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				if(keyCode == KEY_LEFT) {
					//Important logic
					if(btInfo[i][j].getNum() != 0) {
						for(int k=0; k<3; k++) {
							if(btInfo[i][k].getNum() != 0 
								&& (btInfo[i][k].getNum() == btInfo[i][k+1].getNum())) {
								btInfo[i][k].setNum(btInfo[i][k+1].getNum()*2);
								btInfo[i][k+1].setNum(0);
								i++;
								j=0;
								break;
							}
						}
					}
				}else if(keyCode == KEY_RIGHT) {
				//Important logic
					if(btInfo[i][j].getNum() != 0) {
						for(int k=3; k>=1; k--) {
							if(btInfo[i][k].getNum() != 0 
								&& (btInfo[i][k].getNum() == btInfo[i][k-1].getNum())) {
								btInfo[i][k].setNum(btInfo[i][k-1].getNum()*2);
								btInfo[i][k-1].setNum(0);
								i++;
								j=0;
								break;
							}
						}
					}
				}else if(keyCode == KEY_UP) {
					if(btInfo[i][j].getNum() != 0) {
						for(int k=0; k<3; k++) {
							if(btInfo[k][j].getNum() != 0 
								&& (btInfo[k][j].getNum() == btInfo[k+1][j].getNum())) {
								btInfo[k][j].setNum(btInfo[k+1][j].getNum()*2);
								btInfo[k+1][j].setNum(0);
								i++;
								j=0;
								break;
							}
						}
					}
				}else if(keyCode == KEY_DOWN) {
					if(btInfo[i][j].getNum() != 0) {
						for(int k=3; k>=1; k--) {
							if(btInfo[k][j].getNum() != 0 
								&& (btInfo[k][j].getNum() == btInfo[k-1][j].getNum())) {
								btInfo[k][j].setNum(btInfo[k-1][j].getNum()*2);
								btInfo[k-1][j].setNum(0);
								i++;
								j=0;
								break;
							}
						}
					}
				}
			}
		}
		
		//3. then move the button
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				if(keyCode == KEY_LEFT) {
					//Important logic
					if(btInfo[i][j].getNum() != 0) {
						for(int k=0; k<j; k++) {
							if(btInfo[i][k].getNum() == 0) {
								btInfo[i][k].setNum(btInfo[i][j].getNum());
								btInfo[i][j].setNum(0);
							}
						}
					}
				}else if(keyCode == KEY_RIGHT) {
				//Important logic
					if(btInfo[i][j].getNum() != 0) {
						for(int k=3; k>=j; k--) {
							if(btInfo[i][k].getNum() == 0) {
								btInfo[i][k].setNum(btInfo[i][j].getNum());
								btInfo[i][j].setNum(0);
							}
						}
					}
				}else if(keyCode == KEY_UP) {
					if(btInfo[i][j].getNum() != 0) {
						for(int k=0; k<i; k++) {
							if(btInfo[k][j].getNum() == 0) {
								btInfo[k][j].setNum(btInfo[i][j].getNum());
								btInfo[i][j].setNum(0);
							}
						}
					}
				}else if(keyCode == KEY_DOWN) {
					if(btInfo[i][j].getNum() != 0) {
						for(int k=3; k>=i; k--) {
							if(btInfo[k][j].getNum() == 0) {
								btInfo[k][j].setNum(btInfo[i][j].getNum());
								btInfo[i][j].setNum(0);
							}
						}
					}
				}
			}
		}
		
		//4. Create new value
		int rand_x, rand_y;
		int randNum = (int)(Math.random()*2+1)*2;
		//random location find
		while(true) {
			rand_x = (int)(Math.random()*4);
			rand_y = (int)(Math.random()*4);
			
			if(btInfo[rand_x][rand_y].getNum() == 0) {
				break;
			}
		}
		btInfo[rand_x][rand_y].setNum(randNum);
		//Algorithm
		gameDB.setUpdatedDB(btInfo);
	}
	
	public int getBtNum(int x, int y) {
		return gameDB.getBtNumByPosition(x, y);
	}
}
