import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Arrange  {
//지뢰의 배치, 블럭의 리셋을 담당하는 메소들을 모아놓은 클래스
	
	//재시작버튼시 블록들을 다시 디폴트로, 지뢰를 해제해주는 메소드
	static void ReArrange_bloc(){
		for(int y =2 ; y<Center.VERTICAL+2;y++){
			for(int x=2;x<Center.HORIZENTAL+2;x++){
				Center.bloc[x][y].setSize(20,20);
				//리셋 시 기본값을 Default로 설정
				Center.bloc[x][y].setIcon(new ImageIcon("img/default.png"));
				Center.mine[x][y] = false;
				Center.flag[x][y] = false;
			}
		}//2중for문종료
	}
	//지뢰를 배치해주는 메소드
	static void arrange_Mine(){
		int x,y;
		for(int i=0;i<Center.MINE_NUM;){
			x = (int) (Math.random()*15) +2;
			y = (int) (Math.random()*15) +2;
			if(Center.mine[x][y] == false){//지뢰의 중복을 방지해준다
				Center.mine[x][y] = true;
				i++;
			}
		}//지뢰 배치 for문
		Center.LEFT_MINE_NUM = Center.MINE_NUM;
	}
	//모든 블럭을 보이게 해주는 메소드, 지뢰를 터뜨렸다는 뜻이므로 카운터 스레드를 중지시킨다.
	static void openAllBloc(){
		Center.timerStop = true;
		//Top.mineCounter.stop();
		String imgstr=null;
		for(int y=1;y<Center.HORIZENTAL+2;y++){
			for(int x=1;x<Center.VERTICAL+2;x++){
				if(Center.mine[x][y] == true){
					Center.bloc[x][y].setIcon(new ImageIcon("img/mine.png"));					
				}
				else{
					imgstr = "img/open"+Count.mine(x, y)+".png";
					Center.bloc[x][y].setIcon(new ImageIcon(imgstr));
				}
			}
		}//for문
	}
	//인접한 빈 블록까지 열어주는 메소드
	static void openEmptyBloc(int x, int y){
		String imgstr=null;
		//만약 누른 버튼 카운트가 0이면
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				imgstr = "img/open"+Count.mine(x-1+i, y-1+j)+".png";	
				Center.bloc[x-1+i][y-1+j].setIcon(new ImageIcon(imgstr));	
				
			}
		}
		//주변 8개를 다 오픈한다
	}
	

}