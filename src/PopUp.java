import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PopUp extends JFrame{
	//성공시 새로이 대화 상자를 띄우고 등록버튼으로 DB에 input한다
	

	Toolkit tk=Toolkit.getDefaultToolkit();
	Dimension screen=tk.getScreenSize(); // 화면의 크기를 구함
	JPanel popUpPanel = new JPanel();
	JLabel scoreSegment, scoreLabel, userLabel;
	JTextField user = new JTextField(10);
	JButton input = new JButton("등록");
	int playerScore=0;
	String playerName=null;
	
	
	PopUp(int width, int height, int score){
		this.playerScore = score;
		this.setSize(width/2, height/3);
	setLocation((screen.width-width)/2 + width/4, (screen.height-height)/2 + height/4);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Result");
		this.setVisible(true);
		
		scoreLabel = new JLabel("시간    :        ");
		scoreSegment = new JLabel(score+"초              ");
		userLabel = new JLabel("  이름:");
		input.addActionListener(e-> {
			//람다식 이벤트 처리
			System.out.println("종료");
			playerName = user.getText();
			System.out.println(playerName);
			Main.insertPlayerData(playerName, playerScore);
		});
		
		this.add(popUpPanel);
		popUpPanel.add(scoreLabel);
		popUpPanel.add(scoreSegment);
		popUpPanel.add(userLabel);
		popUpPanel.add(user);
		popUpPanel.add(input);
		
	}

}