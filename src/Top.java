import java.awt.Color;
import java.awt.GridLayout;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Top extends JPanel{
	static JButton btn = new JButton("재시작");
	static JLabel clock0, clock1, clock2;
	static JLabel counter0, counter1, counter2;
	static Timer timer = new Timer();
	//static Thread mineCounter;
	static int count = 0 ;
	JPanel clockPanel = new JPanel();
	JPanel leftedMinePanel = new JPanel();

	Top(){
		this.setLayout(new GridLayout(1,3));
		clock0 = new JLabel();		clock1 = new JLabel();		clock2 = new JLabel();
		counter0 = new JLabel(); counter1 = new JLabel(); counter2 = new JLabel();
		clockPanel.setLayout(null);
		clock0.setBounds(35, 5, 20, 20);
		clock1.setBounds(50, 5, 20, 20);
		clock2.setBounds(65, 5, 20, 20);
		
		TimerTask task = new TimerTask(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if(Center.timerStop == false){
			clock2.setIcon(new ImageIcon("img/count"+((count++)%10)+".png"));
			clock1.setIcon(new ImageIcon("img/count"+((count-1)%100)/10+".png"));
			clock0.setIcon(new ImageIcon("img/count"+((count-1)%1000)/100+".png"));
				}
			}
		};
		timer.schedule(task, 0, 1000);
		
		leftedMinePanel.setLayout(null);
		counter0.setBounds(35, 5, 20, 20);
		counter1.setBounds(50, 5, 20, 20);
		counter2.setBounds(65, 5, 20, 20);
		updateLeftMineNum(Center.MINE_NUM);
		
		btn.addActionListener(e-> {
			//람다식 이벤트 처리
			Arrange.ReArrange_bloc();
			Arrange.arrange_Mine();
			count =0;
			Center.timerStop = false;
			updateLeftMineNum(Center.MINE_NUM);
			Center.LEFT_MINE_NUM = Center.MINE_NUM;
			Main.getTopPlayer();
		});
		clockPanel.add(clock0);	clockPanel.add(clock1);	clockPanel.add(clock2);
		leftedMinePanel.add(counter0);	leftedMinePanel.add(counter1);					leftedMinePanel.add(counter2);
		this.add(clockPanel);
		this.add(btn);
		this.add(leftedMinePanel);
	}
	static int updateLeftMineNum(int MINE_NUM){
		counter2.setIcon( new ImageIcon("img/count"+((MINE_NUM)%10)+".png"));
		counter1.setIcon( new ImageIcon("img/count"+((MINE_NUM)%100)/10+".png"));
		counter0.setIcon( new ImageIcon("img/count"+((MINE_NUM)%1000)/100+".png"));
		return 0;
	}
}