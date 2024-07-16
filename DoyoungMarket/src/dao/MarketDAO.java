package dao;

//관리자 상품 관련 오라클 연동 클레스 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import dto.MarketDTO;

public class MarketDAO {
	private Connection conn = null;
	private String url = "jdbc:oracle:thin:@localhost:1521:orcl";

	public MarketDAO() {
		// 드라이버 로드
		String driver = "oracle.jdbc.driver.OracleDriver";
		try {
			Class.forName(driver);

//				getConnection();//테스트 용도
		} catch (Exception e) {
			System.out.println("로드 실패");
		}
	}

	// 3번 Connection 자원 획득하는 메서드
	public Connection getConnection() {
		try {
			conn = DriverManager.getConnection(url, "system", "11111111");

			return conn;
		} catch (Exception e) {
			System.out.println("연결 실패");
		}
		return null;
	}

	public void insert(MarketDTO mt) {
		PreparedStatement ps = null;
		if (getConnection() != null) {
			try {
				String sql = "insert into market values (?,?,?,?)";
				ps = conn.prepareStatement(sql);
				ps.setString(1, mt.getName());
				ps.setString(2, mt.getDetail());
				ps.setInt(3, mt.getEa());
				ps.setInt(4, mt.getPrice());
				ps.executeUpdate();
				System.out.println("등록완료");
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	public void update(MarketDTO mt) {
		PreparedStatement ps = null;
		if (getConnection() != null) {
			try {
				String sql = "update market set ea = ea+(?) where name =(?)";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, mt.getEa());
				ps.setString(2, mt.getName());
				ps.executeUpdate();
				System.out.println("입고완료");
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	public void delete(MarketDTO mt) {
		PreparedStatement ps = null;
		if (getConnection() != null) {
			try {
				String sql = "delete from market where name =(?)";
				ps = conn.prepareStatement(sql);
				ps.setString(1, mt.getName());
				ps.executeUpdate();
				System.out.println("삭제완료");
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	public ArrayList<MarketDTO> selectAll() {
		ArrayList<MarketDTO> g = new ArrayList<>();
		if (getConnection() != null) {
			try {
				Statement st = null;
				ResultSet rs = null;
				String sql = "select * from market";
				st = conn.createStatement();
				rs = st.executeQuery(sql);
				while (rs.next()) {
					String name = rs.getString("name");
					String detail = rs.getString("detail");
					int ea = rs.getInt("ea");
					int price = rs.getInt("price");
					MarketDTO newg = new MarketDTO();
					newg.setName(name);
					newg.setDetail(detail);
					newg.setEa(ea);
					newg.setPrice(price);
					g.add(newg);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return g;
	}

}
