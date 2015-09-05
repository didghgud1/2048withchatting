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
		resetGame();
	}
	
	public int getBtNum(int x, int y) {
		return gameDB.getBtNumByPosition(x, y);
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
	
	public int checkWhetherGameEnd() {
		buttonInfo[][] btInfo = gameDB.getAllDB();
		//Case 1, All buttons are fully added, but Cannot move any tiles.
		boolean tileFull = true;
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				if(btInfo[i][j].getNum() == 0) {
					tileFull = false;
					break;
				}
			}
		}
		
		if(tileFull) {
			boolean noTileCanMove = true;
			for(int i=0; i<4; i++) {
				for(int j=0; j<4; j++) {
					if(i<4 && j<4) {
						if(i>0) {
							if(btInfo[i][j].getNum() == btInfo[i-1][j].getNum()) {
								noTileCanMove = false;
								i=4;
								j=4;
								break;
							}
						}
						if (j>0) {
							if(btInfo[i][j].getNum() == btInfo[i][j-1].getNum()) {
								noTileCanMove = false;
								i=4;
								j=4;
								break;
							}
						}
						if (i<3) {
							if(btInfo[i][j].getNum() == btInfo[i+1][j].getNum()) {
								noTileCanMove = false;
								i=4;
								j=4;
								break;
							}
						}
						if(j<3) {
							if(btInfo[i][j].getNum() == btInfo[i][j+1].getNum()) {
								noTileCanMove = false;
								i=4;
								j=4;
								break;
							}
						}
					}
				}
			}
			if(noTileCanMove) {
				return 0;
			}
		}

		//Case 2, 2048 is created.
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				if(btInfo[i][j].getNum() == 2048) {
					return 1;
				}
			}
		}
		//else case, game should be continued.
		return -1;
	}
	
	public void resetGame() {
		gameDB.Initialize();
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
				for(int j=0; j<3; j++) {
					if(tBtInfo[i][j].getNum() != 0) {
						if(tBtInfo[i][j].getNum() == tBtInfo[i][j+1].getNum()) {
							tBtInfo[i][j].setNum(tBtInfo[i][j+1].getNum()*2);
							tBtInfo[i][j+1].setNum(0);
						}
					}
				}
			}
		}else if(keyCode == KEY_UP) {
			for(int i=3; i>=0; i--) {
				for(int j=0; j<3; j++) {
					if(tBtInfo[j][i].getNum() != 0) {
						if(tBtInfo[j][i].getNum() == tBtInfo[j+1][i].getNum()) {
							tBtInfo[j][i].setNum(tBtInfo[j+1][i].getNum()*2);
							tBtInfo[j+1][i].setNum(0);
						}
					}
				}
			}
		}else if(keyCode == KEY_RIGHT) {
			for(int i=0; i<4; i++) {
				for(int j=3; j>0; j--) {
					if(tBtInfo[i][j].getNum() != 0) {
						if(tBtInfo[i][j].getNum() != 0) {
							if(tBtInfo[i][j].getNum() == tBtInfo[i][j-1].getNum()) {
								tBtInfo[i][j].setNum(tBtInfo[i][j-1].getNum()*2);
								tBtInfo[i][j-1].setNum(0);
							}
						}
					}
				}
			}
		}else if(keyCode == KEY_DOWN) {
			for(int i=3; i>0; i--) {
				for(int j=3; j>=0; j--) {
					if(tBtInfo[i][j].getNum() != 0) {
						if(tBtInfo[i][j].getNum() == tBtInfo[i-1][j].getNum()) {
							tBtInfo[i][j].setNum(tBtInfo[i-1][j].getNum()*2);
							tBtInfo[i-1][j].setNum(0);
						}
					}
				}
			}
		}
	}
}
