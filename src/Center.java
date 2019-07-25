import java.awt.GridLayout;
import java.util.Random;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Center extends JPanel{
	static final int VERTICAL = 15;
	static final int HORIZENTAL = 15;
	static final int MINE_NUM = 1;
	static int LEFT_MINE_NUM=0;
	static int score=0;
	static boolean timerStop = false;
	static  int GOAL =0;
	static JButton[][] bloc = new JButton[VERTICAL+4][HORIZENTAL+4];
	static boolean[][] mine = new boolean[VERTICAL+4][HORIZENTAL+4];
	static boolean[][] flag = new boolean[VERTICAL+4][HORIZENTAL+4];
	Random rand = new Random();
	Center(){
		this.setLayout(new GridLayout(VERTICAL, HORIZENTAL));
		//버튼 생성 시퀀스, default값으로 이미지 입힘
		for(int y =0 ; y<VERTICAL+3;y++){
			for(int x=0;x<HORIZENTAL+3;x++){
				bloc[x][y] = new JButton();
				mine[x][y] = false;
				flag[x][y] = false;
				bloc[x][y].setActionCommand(""+(x)+"_"+(y));
			}
		}//2중for문종료
		for(int y =0 ; y<VERTICAL+3;y++){
			for(int x=0;x<HORIZENTAL+3;x++){
				bloc[x][y].setIcon(new ImageIcon("img/default.png"));
				bloc[x][y].setSize(20,20);
				//이미지 기본값을 Default로 설정
				bloc[x][y].addMouseListener(new ClickHandler());
			}
		}//2중for문종료
		for(int y =2 ; y<VERTICAL+2;y++){
			for(int x=2;x<HORIZENTAL+2;x++){
				this.add(Center.bloc[x][y]);
			}
		}//2중for문종료
		//지뢰 배치 메소드
		Arrange.arrange_Mine();
		
	}
}//클래스

