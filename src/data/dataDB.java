package data;

class buttonInfo {
	private int num;//Current number
	
	public buttonInfo(int _num) {
		this.num = _num;
	}
	
	public void setNum(int numValue) {
		num = numValue;
	}
	
	public int getNum() {
		return num;
	}
}

class dataDB {
	private buttonInfo[][] btInfo;
	
	public dataDB() {
		btInfo = new buttonInfo[4][4];
	}
	
	public void Initialize() {
		//initial info
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				btInfo[i][j] = new buttonInfo(0);
			}
		}
		
		int rand_x_1, rand_y_1, rand_x_2, rand_y_2;//rand position of button
		int randNum1, randNum2;//rand number
		
		randNum1 = (int)(Math.random()*2+1)*2;
		randNum2 = (int)(Math.random()*2+1)*2;
		//random location find
		while(true) {
			rand_x_1 = (int)(Math.random()*4);
			rand_y_1 = (int)(Math.random()*4);
			rand_x_2 = (int)(Math.random()*4);
			rand_y_2 = (int)(Math.random()*4);
			
			if((rand_x_1 != rand_x_2) && (rand_y_1 != rand_y_2)) {
				break;
			}
		}
		btInfo[rand_x_1][rand_y_1].setNum(randNum1);
		btInfo[rand_y_1][rand_y_2].setNum(randNum2);
	}
	
	public buttonInfo[][] getAllDB() {
		return btInfo;
	}
	
	public void setUpdatedDB(buttonInfo[][] newbtInfo) {
		btInfo = newbtInfo;
	}
	
	public int getBtNumByPosition(int x, int y) {
		return btInfo[x][y].getNum();
	}
}
