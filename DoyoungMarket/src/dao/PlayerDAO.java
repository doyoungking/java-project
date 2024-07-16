package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import dto.PlayerDTO;

public class PlayerDAO {
	private Connection conn = null;
	private String url = "jdbc:oracle:thin:@localhost:1521:orcl";

	public PlayerDAO() {
		// 드라이버 로드
		String driver = "oracle.jdbc.driver.OracleDriver";
		try {
			Class.forName(driver);
			System.out.println("로드 성공");
//				getConnection();//테스트 용도
		} catch (Exception e) {
			System.out.println("로드 실패");
		}
	}

	// 3번 Connection 자원 획득하는 메서드
	public Connection getConnection() {
		try {
			conn = DriverManager.getConnection(url, "system", "11111111");
			System.out.println("연결 성공");
			return conn;
		} catch (Exception e) {
			System.out.println("연결 실패");
		}
		return null;
	}

	public boolean insert(PlayerDTO ut) {
		PreparedStatement psmt = null;
		if (getConnection() != null) {
			try {
				String sql = "insert into player values (?,?,?,?,?)";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, ut.getId());
				psmt.setString(2, ut.getPw());
				psmt.setString(3, ut.getUserName());
				psmt.setString(4, ut.getPhonenumber());
				psmt.setInt(5, 0);
				int a = psmt.executeUpdate();
				System.out.println(a + "건 입력 성공");
				return true;
			} catch (Exception e) {
				System.out.println("입력 오류");
			}
		}
		return false;
	}

	public void update(PlayerDTO pt) {
		ResultSet rs = null;
		PreparedStatement ps = null;
		if (getConnection() != null) {
			try {
				String sql2 = "update player set cash = cash+(?) where id =(?)";
				ps = conn.prepareStatement(sql2);
				ps.setInt(1, pt.getCash());
				ps.setString(2, pt.getId());
				ps.executeUpdate();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	public String showcash(String id) {
		ResultSet rs = null;
		PreparedStatement ps = null;
		String scash = null;
		if (getConnection() != null) {
			try {
				String sql = "select cash from player where id = (?)";
				ps = conn.prepareStatement(sql);
				ps.setString(1, id);
				ps.executeUpdate();
				rs = ps.executeQuery(sql);
				while (rs.next()) {
					scash = rs.getString("cash" + "");
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return scash;
	}

	public ArrayList<PlayerDTO> selectall() {
		ArrayList<PlayerDTO> g = new ArrayList<>();
		if (getConnection() != null) {
			try {
				Statement st = null;
				ResultSet rs = null;
				String sql = "select id,username,phonenumber,cash from player";
				st = conn.createStatement();
				rs = st.executeQuery(sql);
				while (rs.next()) {
					String id = rs.getString("id");
					String userName = rs.getString("userName");
					String phonenumber = rs.getString("phonenumber");
					int cash = rs.getInt("cash");
					PlayerDTO newg = new PlayerDTO();
					newg.setId(id);
					newg.setUserName(userName);
					newg.setPhonenumber(phonenumber);
					newg.setCash(cash);
					g.add(newg);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return g;

	}

	public String chkUser(String id, String pw) {
		if (getConnection() != null) {
			try {
				Statement st = null;
				ResultSet rs = null;
				String sql = "select * from player";
				st = conn.createStatement();
				rs = st.executeQuery(sql);
				while (rs.next()) {
					String cid = rs.getString("id");
					String cpw = rs.getString("pw");
					if (cid.equals(id) && cpw.equals(pw)) {
						System.out.println(id + "님 로그인 하셨습니다");
						return id;
					}
				}
			} catch (Exception e) {
				System.out.println("입력 오류");
			}
		}
		return null;
	}

}
