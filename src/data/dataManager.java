package data;

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
	
	public int getBtNum(int x, int y) {
		return gameDB.getBtNumByPosition(x, y);
	}
	
	private void moveButtonToEdge(buttonInfo[][] tBtInfo, int keyCode) {
		if(keyCode == KEY_LEFT) {
			for(int i=0; i<4; i++) {
				for(int j=0; j<4; j++) {
					if(tBtInfo[i][j].getNum() != 0) {
						for(int k=0; k<j; k++) {
							if(tBtInfo[i][k].getNum() == 0) {
								tBtInfo[i][k].setNum(tBtInfo[i][j].getNum());
								tBtInfo[i][j].setNum(0);
							}
						}
					}
				}
			}
		}else if(keyCode == KEY_UP) {
			for(int i=0; i<4; i++) {
				for(int j=0; j<4; j++) {
					if(tBtInfo[i][j].getNum() != 0) {
						for(int k=0; k<i; k++) {
							if(tBtInfo[k][j].getNum() == 0) {
								tBtInfo[k][j].setNum(tBtInfo[i][j].getNum());
								tBtInfo[i][j].setNum(0);
							}
						}
					}
				}
			}
		}else if(keyCode == KEY_RIGHT) {
			for(int i=0; i<4; i++) {
				for(int j=3; j>=0; j--) {
					if(tBtInfo[i][j].getNum() != 0) {
						for(int k=3; k>j; k--) {
							if(tBtInfo[i][k].getNum() == 0) {
								tBtInfo[i][k].setNum(tBtInfo[i][j].getNum());
								tBtInfo[i][j].setNum(0);
							}
						}
					}
				}
			}
		}else if(keyCode == KEY_DOWN) {
			for(int i=3; i>=0; i--) {
				for(int j=3; j>=0; j--) {
					if(tBtInfo[i][j].getNum() != 0) {
						for(int k=3; k>i; k--) {
							if(tBtInfo[k][j].getNum() == 0) {
								tBtInfo[k][j].setNum(tBtInfo[i][j].getNum());
								tBtInfo[i][j].setNum(0);
							}
						}
					}
				}
			}
		}
	}
	
	private void mergeButton(buttonInfo[][] tBtInfo, int keyCode) {
		if(keyCode == KEY_LEFT) {
			for(int i=0; i<4; i++) {
				for(int j=0; j<4; j++) {
					if(i < 4 && tBtInfo[i][j].getNum() != 0) {
						for(int k=0; k<3; k++) {
							if(tBtInfo[i][k].getNum() != 0 
								&& (tBtInfo[i][k].getNum() == tBtInfo[i][k+1].getNum())) {
								tBtInfo[i][k].setNum(tBtInfo[i][k+1].getNum()*2);
								tBtInfo[i][k+1].setNum(0);
								i++;
								j=0;
								break;
							}
						}
					}
				}
			}
		}else if(keyCode == KEY_UP) {
			for(int i=3; i>=0; i--) {
				for(int j=0; j<4; j++) {
					if(i >= 0 && tBtInfo[j][i].getNum() != 0) {
						for(int k=0; k<3; k++) {
							if(tBtInfo[j][i].getNum() != 0 
								&& (tBtInfo[k][i].getNum() == tBtInfo[k+1][i].getNum())) {
								tBtInfo[k][i].setNum(tBtInfo[k+1][i].getNum()*2);
								tBtInfo[k+1][i].setNum(0);
								i--;
								j=0;
								break;
							}
						}
					}
				}
			}
		}else if(keyCode == KEY_RIGHT) {
			for(int i=0; i<4; i++) {
				for(int j=3; j>=0; j--) {
					if(i < 4 && tBtInfo[i][j].getNum() != 0) {
						for(int k=3; k>=1; k--) {
							if(tBtInfo[i][k].getNum() != 0 
								&& (tBtInfo[i][k].getNum() == tBtInfo[i][k-1].getNum())) {
								tBtInfo[i][k].setNum(tBtInfo[i][k-1].getNum()*2);
								tBtInfo[i][k-1].setNum(0);
								i++;
								j=3;
								break;
							}
						}
					}
				}
			}
		}else if(keyCode == KEY_DOWN) {
			for(int i=3; i>=0; i--) {
				for(int j=3; j>=0; j--) {
					if(i >= 0 && tBtInfo[i][j].getNum() != 0) {
						for(int k=3; k>=1; k--) {
							if(tBtInfo[k][j].getNum() != 0 
								&& (tBtInfo[k][j].getNum() == tBtInfo[k-1][j].getNum())) {
								tBtInfo[k][j].setNum(tBtInfo[k-1][j].getNum()*2);
								tBtInfo[k-1][j].setNum(0);
								i--;
								j=3;
								break;
							}
						}
					}
				}
			}
		}
	}
	
	public void processKeyData(int keyCode) {
		//37 left, 38 up, 39 right, 40 down
		buttonInfo[][] btInfo = gameDB.getAllDB();
		
		//Algorithm
		//1. Move the all buttons to each side
		moveButtonToEdge(btInfo, keyCode);
		//2. Merge the buttons.
		mergeButton(btInfo, keyCode);
		//3. then move the button
		moveButtonToEdge(btInfo, keyCode);
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
		//end Algorithm
		//update db to db storage
		gameDB.setUpdatedDB(btInfo);
	}
}
