import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.*;
import java.util.Timer;
import javax.swing.JFrame;
import javax.swing.JLabel;
//import com.mysql.jdbc.Connection;

public class Main extends JFrame{
	
	Toolkit tk=Toolkit.getDefaultToolkit();
	Dimension screen=tk.getScreenSize(); // ȭ���� ũ�⸦ ����
	
	//--Ŭ���� �����
	static Main mineSweeper;
	//--��� �г�.. �ð� Ÿ�̸�, ����� ��ư, ���� ���� ī���Ͱ� ����
	static Top topPanel = new Top();
	//--�߾Ӻ��г�..���ڹ�ġ �������� ��ư�� VERTICAL * HORIZENTAL��ŭ ����ش�.
	static Center centerPanel = new Center();
	//--�ϴܺ� �г�..���� ��ŷ�� �ְ� ������ ����ڿ��� �����ش�.
	static Bottom bottomPanel = new Bottom();
	//��ġ���� �޼ҵ� ���� Ŭ����.. ���� �˰����� �ٽɺκ��̴�
	static Arrange arrange = new Arrange();
	static Timer timer;
	static Connection con;
	static Statement stmt;
	static ResultSet rs;
	static String first_name;
	static int first_score;
	
	//�˾�â���� ȭ�� ũ�⸦ ���������� �����ϱ� ���� static ����
	static int WIDTH, HEIGHT;
	Main(int width, int height){
		this.WIDTH = width;
		this.HEIGHT = height;
		this.setSize(width, height);
		//---�׻� ȭ�� �߾ӿ� â�� popup�ǰ� ���ִ� �����̴�.
		setLocation((screen.width-width)/2, (screen.height-height)/2);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("MINE SWEEPER");
		this.setLayout(new BorderLayout());
		this.add(topPanel, BorderLayout.NORTH);
		this.add(centerPanel, BorderLayout.CENTER);
		this.add(bottomPanel, BorderLayout.SOUTH);
		setResizable(false); //â ���� ���� �Ұ�
		this.setVisible(true);
	}
	
	public static void main(String[] args) throws SQLException {
		mineSweeper = new Main(350, 400);
		con = makeConnection();
		stmt = con.createStatement();
		getTopPlayer();
		
		
	}
	








	static void getTopPlayer(){
		//�ְ� ������ �÷��̾ DB���� �о�� Bottom�г��� Label�� ǥ��
		String topPlayer = "High-score by ";
		
		try {
			rs = stmt.executeQuery("select * from rank order by score limit 1");
			while(rs.next()){
				first_name = rs.getString("name");
				first_score = rs.getInt("score");
				System.out.println("�̸� : "+ first_name +", ���� : " + first_score);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		topPlayer +=""+first_name+" : "+first_score;
		Bottom.result.setText(topPlayer);
		
	}
	
	
	static void insertPlayerData(String playerName, int playerScore){
		//DB�� ���� ������ �÷��̾��� ������ insert�ϴ� �޼ҵ�
		String insertQuerry = "insert into rank values";
		insertQuerry +="('"+playerName+"',"+playerScore+")";
		try {
			stmt.executeUpdate(insertQuerry);
			System.out.println("insert Querry ����");
			System.exit(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("DB ���� ����");
		}
		
	}
	

	private static Connection makeConnection() {
		// TODO Auto-generated method stub
		String url = "jdbc:mysql://localhost/mineSweeper_rank";
		String id = "root";
		String password = "onlyroot";
		Connection con = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("����̹� ���� ����");
			con = (Connection) DriverManager.getConnection(url, id, password);
			System.out.println("�����ͺ��̽� ���� ����");
		}
		catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("���ῡ �����Ͽ����ϴ�");
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("����̹��� ã�� �� �����ϴ�");
		}
		return con;
	}//makeConnection
}//Conncetion
