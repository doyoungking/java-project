package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import dto.UpurchaseDTO;

public class UpurchaseDAO {
	private Connection conn = null;
	private String url = "jdbc:oracle:thin:@localhost:1521:orcl";

	public UpurchaseDAO() {
		String driver = "oracle.jdbc.driver.OracleDriver";
		try {
			Class.forName(driver);

//				getConnection();//테스트 용도
		} catch (Exception e) {
			System.out.println("로드 실패");
		}
	}

	public Connection getConnection() {
		try {
			conn = DriverManager.getConnection(url, "system", "11111111");

			return conn;
		} catch (Exception e) {
			System.out.println("연결 실패");
		}
		return null;
	}

	public void insert(UpurchaseDTO p) {// 장바구니 담기
		ResultSet rs = null;
		PreparedStatement ps = null;
		if (getConnection() != null) {
			try {
				String sql = "select price from market where name =(?)";// 해당 상품 단가 가져오기
				ps = conn.prepareStatement(sql);
				ps.setString(1, p.getName());
				ps.executeUpdate();
				rs = ps.executeQuery(sql);
				int pr = 0;
				while (rs.next()) {
					pr = rs.getInt("price");
				}
				int pp = pr * p.getEa();// 단가 * 입력한 수량 =결제 금액

				String sql2 = "insert into purchase values(?,?,?,?)";// 장바구니 테이블 insert
				ps = conn.prepareStatement(sql2);
				ps.setString(1, p.getId());
				ps.setString(2, p.getName());
				ps.setInt(3, p.getEa());
				ps.setInt(4, pp);
				int a = ps.executeUpdate();

			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	public void eaupdate(UpurchaseDTO p) {// 장바구니 담기
		ResultSet rs = null;
		PreparedStatement ps = null;
		if (getConnection() != null) {
			try {
				String sql3 = "update market set ea = ea-(?) where name =(?)";// 상품 테이블 장바구니 담은 수량만큼 재고차감
				ps = conn.prepareStatement(sql3);
				ps.setInt(1, p.getEa());
				ps.setString(2, p.getName());
				ps.executeUpdate();

			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	public void purchase(UpurchaseDTO p, int totalpay) {// 상품구매시 보유금액-총결제금액
		ResultSet rs = null;
		PreparedStatement ps = null;
		if (getConnection() != null) {
			try {
				String sql = "update player set cash = cash-(?) where id =(?)";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, totalpay);
				ps.setString(2, p.getId());
				int an = ps.executeUpdate();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	public void del(UpurchaseDTO p) {// 상품구매시 장바구니목록 삭제
		PreparedStatement ps = null;
		if (getConnection() != null) {
			try {
				String sql = "delete from purchase where id =(?)";
				ps = conn.prepareStatement(sql);
				ps.setString(1, p.getId());
				int an = ps.executeUpdate();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	public int showprice(String id) {// 총결제금액 계산하는 메서드
		ResultSet rs = null;
		PreparedStatement ps = null;
		int shprice = 0;
		if (getConnection() != null) {
			try {
				String sql = "select p_price from purchase where id =(?)";
				ps = conn.prepareStatement(sql);
				ps.setString(1, id);
				ps.executeUpdate();
				rs = ps.executeQuery(sql);
				while (rs.next()) {
					shprice = shprice + rs.getInt("p_price");// 총결제금액으로 계산
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return shprice;
	}

	public ArrayList<UpurchaseDTO> select() {
		ArrayList<UpurchaseDTO> g = new ArrayList<>();
		if (getConnection() != null) {
			try {
				Statement st = null;
				ResultSet rs = null;
				String sql = "select * from purchase";
				st = conn.createStatement();
				rs = st.executeQuery(sql);
				while (rs.next()) {
					String id = rs.getString("id");
					String name = rs.getString("name");
					int ea = rs.getInt("ea");
					int price = rs.getInt("p_price");
					UpurchaseDTO newg = new UpurchaseDTO();
					newg.setId(id);
					newg.setName(name);
					newg.setEa(ea);
					newg.setP_price(price);
					g.add(newg);

				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return g;
	}

	public void delete(UpurchaseDTO p) {// 장바구니 취소 기능
		if (getConnection() != null) {
			try {
				PreparedStatement ps = null;
				String sql = "update market set ea = ea +(?) where name =(?)";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, p.getEa());
				ps.setString(2, p.getName());
				ps.executeUpdate();
				String sql2 = "delete from purchase where name =(?) and ea =(?)";
				ps = conn.prepareStatement(sql2);
				ps.setString(1, p.getName());
				ps.setInt(2, p.getEa());
				ps.executeUpdate();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

}
