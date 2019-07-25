import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.StringTokenizer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class ClickHandler implements MouseListener {
	//마우스 리스너의 이벤트를 모두 처리하는 클래스
	@Override
	public void mouseClicked(MouseEvent e) {
		JButton Clicked = (JButton)e.getSource();
        System.out.println(Clicked.getActionCommand()); //getText : 버튼의 라벨값
     	String imgstr = null;
     	int x, y=0;
     	StringTokenizer token = new StringTokenizer(Clicked.getActionCommand());
     	x = Integer.parseInt(token.nextToken("_"));		
     	y = Integer.parseInt(token.nextToken("_"));		
        if(e.isMetaDown()){ //오른쪽 버튼이면 깃발을 꼽는다
        	if(e.getSource() == Center.bloc[x][y] && Center.flag[x][y] == false){
				Center.bloc[x][y].setIcon(new ImageIcon("img/marked.png"));
	        	System.out.println("깃발 꼽음");
				Center.flag[x][y] = true;
				Center.LEFT_MINE_NUM--;
				Top.updateLeftMineNum(Center.LEFT_MINE_NUM);
				if(Center.mine[x][y] == true)
					Center.GOAL++;
        	}
        	else{
				Center.bloc[x][y].setIcon(new ImageIcon("img/default.png"));
	        	System.out.println("깃발 뽑음");
				Center.flag[x][y] = false;   
				Center.LEFT_MINE_NUM++;    		
				Top.updateLeftMineNum(Center.LEFT_MINE_NUM);
 				// 깃발이 지뢰 위에만 꼽혔을때에 진짜 카운터를 올려준다
				if(Center.mine[x][y] == true)
					Center.GOAL++;
        	}
        	System.out.println("오른쪽 눌림");
        		
        }
        else if(!e.isMetaDown()){ //왼쪽 클릭이면 지뢰 시퀀스
        	System.out.println("왼쪽눌림");
        	if(e.getSource() == Center.bloc[x][y]){
 				if(Center.mine[x][y] == true){ 										//지뢰영역이면
 					Center.bloc[x][y].setIcon(new ImageIcon("img/mine.png"));					//지뢰로 이미지 교체
 					Arrange.openAllBloc();										//모든 블록을 연다
 					Bottom.result.setText("FAIL....");
 				}
 				else{
 					if(Count.mine(x, y)==0)
 						Arrange.openEmptyBloc(x, y);
 					else{
 						imgstr = "img/open"+Count.mine(x, y)+".png";				//지뢰가아니면 주변지뢰갯수를 카운트한후
 	 					Center.bloc[x][y].setIcon(new ImageIcon(imgstr));			//값에해당하는 이미지로 교체
 	 				}
 					
 				}
 			}//버튼if문종료	
        }
     				
        System.out.println(x+"_"+y);
        //깃발이 지뢰갯수만큼 꼽혔으면
        if(Center.GOAL == Center.MINE_NUM ) {
        	Center.score = Top.count;
        	Bottom.result.setText(Center.score+"초..SUCCESS!!!!");
        	PopUp pop = new PopUp(Main.WIDTH, Main.HEIGHT, Center.score);
        }
        
        
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}

