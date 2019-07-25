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
	Dimension screen=tk.getScreenSize(); // 화면의 크기를 구함
	
	//--클래스 선언부
	static Main mineSweeper;
	//--상단 패널.. 시간 타이머, 재시작 버튼, 지뢰 갯수 카운터가 들어간다
	static Top topPanel = new Top();
	//--중앙부패널..지뢰배치 구역으로 버튼을 VERTICAL * HORIZENTAL만큼 깔아준다.
	static Center centerPanel = new Center();
	//--하단부 패널..현재 랭킹의 최고 점수를 사용자에게 보여준다.
	static Bottom bottomPanel = new Bottom();
	//배치관리 메소드 집합 클래스.. 게임 알고리즘의 핵심부분이다
	static Arrange arrange = new Arrange();
	static Timer timer;
	static Connection con;
	static Statement stmt;
	static ResultSet rs;
	static String first_name;
	static int first_score;
	
	//팝업창으로 화면 크기를 연쇄적으로 전달하기 위해 static 선언
	static int WIDTH, HEIGHT;
	Main(int width, int height){
		this.WIDTH = width;
		this.HEIGHT = height;
		this.setSize(width, height);
		//---항상 화면 중앙에 창이 popup되게 해주는 문장이다.
		setLocation((screen.width-width)/2, (screen.height-height)/2);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("MINE SWEEPER");
		this.setLayout(new BorderLayout());
		this.add(topPanel, BorderLayout.NORTH);
		this.add(centerPanel, BorderLayout.CENTER);
		this.add(bottomPanel, BorderLayout.SOUTH);
		setResizable(false); //창 넓이 조절 불가
		this.setVisible(true);
	}
	
	public static void main(String[] args) throws SQLException {
		mineSweeper = new Main(350, 400);
		con = makeConnection();
		stmt = con.createStatement();
		getTopPlayer();
		
		
	}
	








	static void getTopPlayer(){
		//최고 점수의 플레이어를 DB에서 읽어와 Bottom패널의 Label에 표시
		String topPlayer = "High-score by ";
		
		try {
			rs = stmt.executeQuery("select * from rank order by score limit 1");
			while(rs.next()){
				first_name = rs.getString("name");
				first_score = rs.getInt("score");
				System.out.println("이름 : "+ first_name +", 점수 : " + first_score);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		topPlayer +=""+first_name+" : "+first_score;
		Bottom.result.setText(topPlayer);
		
	}
	
	
	static void insertPlayerData(String playerName, int playerScore){
		//DB에 현재 성공한 플레이어의 점수를 insert하는 메소드
		String insertQuerry = "insert into rank values";
		insertQuerry +="('"+playerName+"',"+playerScore+")";
		try {
			stmt.executeUpdate(insertQuerry);
			System.out.println("insert Querry 성공");
			System.exit(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("DB 삽입 실패");
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
			System.out.println("드라이버 적재 성공");
			con = (Connection) DriverManager.getConnection(url, id, password);
			System.out.println("데이터베이스 연결 성공");
		}
		catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("연결에 실패하였습니다");
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("드라이버를 찾을 수 없습니다");
		}
		return con;
	}//makeConnection
}//Conncetion
