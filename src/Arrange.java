import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Arrange  {
//������ ��ġ, ���� ������ ����ϴ� �޼ҵ��� ��Ƴ��� Ŭ����
	
	//����۹�ư�� ��ϵ��� �ٽ� ����Ʈ��, ���ڸ� �������ִ� �޼ҵ�
	static void ReArrange_bloc(){
		for(int y =2 ; y<Center.VERTICAL+2;y++){
			for(int x=2;x<Center.HORIZENTAL+2;x++){
				Center.bloc[x][y].setSize(20,20);
				//���� �� �⺻���� Default�� ����
				Center.bloc[x][y].setIcon(new ImageIcon("img/default.png"));
				Center.mine[x][y] = false;
				Center.flag[x][y] = false;
			}
		}//2��for������
	}
	//���ڸ� ��ġ���ִ� �޼ҵ�
	static void arrange_Mine(){
		int x,y;
		for(int i=0;i<Center.MINE_NUM;){
			x = (int) (Math.random()*15) +2;
			y = (int) (Math.random()*15) +2;
			if(Center.mine[x][y] == false){//������ �ߺ��� �������ش�
				Center.mine[x][y] = true;
				i++;
			}
		}//���� ��ġ for��
		Center.LEFT_MINE_NUM = Center.MINE_NUM;
	}
	//��� ���� ���̰� ���ִ� �޼ҵ�, ���ڸ� �Ͷ߷ȴٴ� ���̹Ƿ� ī���� �����带 ������Ų��.
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
		}//for��
	}
	//������ �� ��ϱ��� �����ִ� �޼ҵ�
	static void openEmptyBloc(int x, int y){
		String imgstr=null;
		//���� ���� ��ư ī��Ʈ�� 0�̸�
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				imgstr = "img/open"+Count.mine(x-1+i, y-1+j)+".png";	
				Center.bloc[x-1+i][y-1+j].setIcon(new ImageIcon(imgstr));	
				
			}
		}
		//�ֺ� 8���� �� �����Ѵ�
	}
	

}