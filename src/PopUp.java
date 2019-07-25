import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PopUp extends JFrame{
	//������ ������ ��ȭ ���ڸ� ���� ��Ϲ�ư���� DB�� input�Ѵ�
	

	Toolkit tk=Toolkit.getDefaultToolkit();
	Dimension screen=tk.getScreenSize(); // ȭ���� ũ�⸦ ����
	JPanel popUpPanel = new JPanel();
	JLabel scoreSegment, scoreLabel, userLabel;
	JTextField user = new JTextField(10);
	JButton input = new JButton("���");
	int playerScore=0;
	String playerName=null;
	
	
	PopUp(int width, int height, int score){
		this.playerScore = score;
		this.setSize(width/2, height/3);
	setLocation((screen.width-width)/2 + width/4, (screen.height-height)/2 + height/4);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Result");
		this.setVisible(true);
		
		scoreLabel = new JLabel("�ð�    :        ");
		scoreSegment = new JLabel(score+"��              ");
		userLabel = new JLabel("  �̸�:");
		input.addActionListener(e-> {
			//���ٽ� �̺�Ʈ ó��
			System.out.println("����");
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