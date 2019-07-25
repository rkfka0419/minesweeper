import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.StringTokenizer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class ClickHandler implements MouseListener {
	//���콺 �������� �̺�Ʈ�� ��� ó���ϴ� Ŭ����
	@Override
	public void mouseClicked(MouseEvent e) {
		JButton Clicked = (JButton)e.getSource();
        System.out.println(Clicked.getActionCommand()); //getText : ��ư�� �󺧰�
     	String imgstr = null;
     	int x, y=0;
     	StringTokenizer token = new StringTokenizer(Clicked.getActionCommand());
     	x = Integer.parseInt(token.nextToken("_"));		
     	y = Integer.parseInt(token.nextToken("_"));		
        if(e.isMetaDown()){ //������ ��ư�̸� ����� �Ŵ´�
        	if(e.getSource() == Center.bloc[x][y] && Center.flag[x][y] == false){
				Center.bloc[x][y].setIcon(new ImageIcon("img/marked.png"));
	        	System.out.println("��� ����");
				Center.flag[x][y] = true;
				Center.LEFT_MINE_NUM--;
				Top.updateLeftMineNum(Center.LEFT_MINE_NUM);
				if(Center.mine[x][y] == true)
					Center.GOAL++;
        	}
        	else{
				Center.bloc[x][y].setIcon(new ImageIcon("img/default.png"));
	        	System.out.println("��� ����");
				Center.flag[x][y] = false;   
				Center.LEFT_MINE_NUM++;    		
				Top.updateLeftMineNum(Center.LEFT_MINE_NUM);
 				// ����� ���� ������ ���������� ��¥ ī���͸� �÷��ش�
				if(Center.mine[x][y] == true)
					Center.GOAL++;
        	}
        	System.out.println("������ ����");
        		
        }
        else if(!e.isMetaDown()){ //���� Ŭ���̸� ���� ������
        	System.out.println("���ʴ���");
        	if(e.getSource() == Center.bloc[x][y]){
 				if(Center.mine[x][y] == true){ 										//���ڿ����̸�
 					Center.bloc[x][y].setIcon(new ImageIcon("img/mine.png"));					//���ڷ� �̹��� ��ü
 					Arrange.openAllBloc();										//��� ����� ����
 					Bottom.result.setText("FAIL....");
 				}
 				else{
 					if(Count.mine(x, y)==0)
 						Arrange.openEmptyBloc(x, y);
 					else{
 						imgstr = "img/open"+Count.mine(x, y)+".png";				//���ڰ��ƴϸ� �ֺ����ڰ����� ī��Ʈ����
 	 					Center.bloc[x][y].setIcon(new ImageIcon(imgstr));			//�����ش��ϴ� �̹����� ��ü
 	 				}
 					
 				}
 			}//��ưif������	
        }
     				
        System.out.println(x+"_"+y);
        //����� ���ڰ�����ŭ ��������
        if(Center.GOAL == Center.MINE_NUM ) {
        	Center.score = Top.count;
        	Bottom.result.setText(Center.score+"��..SUCCESS!!!!");
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

