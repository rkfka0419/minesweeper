public class Count {
	int x,y=0;
	Count(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	static int mine(int x, int y){
		int count=0;
		
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if(Center.mine[x-1+i][y-1+j] == true)
					count++;
			}
		}
		return count;
	}
}