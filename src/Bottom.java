import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Bottom extends JPanel{
	static JLabel result;
	Bottom(){
		result = new JLabel("");
		this.add(result);
		//DB커넥션을 이쪽해다 해도 좋을 것 같다.
	}

}