package MemberShip.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberShipDBManageImpl implements IMemberShipDBManage{
	Member member;
	final static String DRIVER = "org.sqlite.JDBC";
	final static String DB = "jdbc:sqlite:src/DataBase/membershipDB.db";
	Connection conn;
	
	public MemberShipDBManageImpl() {
		member = new Member();
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(DB);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean MemberProc(Member member) {
		String sql = "INSERT INTO member " + "(id, name, pw, phone, email) " + "VALUES (?,?,?,?,?)";
		try {
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setString(1, member.getId());
			pStmt.setString(2, member.getName());
			pStmt.setString(3, member.getPw());
			pStmt.setString(4, member.getPhone());
			pStmt.setString(5, member.getEmail());

			pStmt.executeUpdate();

			pStmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}	
	@Override
	public void UpdateMember(String id, String name, String phone, String pw, String email) {
		String sql = "UPDATE member SET name = ? , "
				+ "phone = ? "
				+ ",pw = ? "
				+ ",email = ? "
				+ "WHERE id = ?";
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(DB);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

			// set the corresponding param
			pstmt.setString(1, name);
			pstmt.setString(2, phone);
			pstmt.setString(3, pw);
			pstmt.setString(4, email);
			pstmt.setString(5, id);
			// update 
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public String LoginProc(String id) { // 아이디를 읽어와서 DB에있는 PW와 매칭
		String matchedPw = null;
		String sql = "SELECT pw " +
				"FROM member " + "WHERE id = ?";
		try {	Class.forName(DRIVER);
		conn = DriverManager.getConnection(DB);
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setString(1, id);
			pStmt.execute();


			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				matchedPw = rs.getString("pw");
			}
			rs.close();
			pStmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return matchedPw;
		

	}
	@Override
	public List<String> ReadProc(String id) { // 아이디를 읽어와서 DB에있는 PW와 매칭
		List<String> lstMember = new ArrayList<String>();
		String sql = "SELECT * " +
				"FROM member " + " WHERE id = ? ";
		try {
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setString(1, id);
			pStmt.execute();


			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				lstMember.add(rs.getString("name"));
				lstMember.add(rs.getString("phone"));
				lstMember.add(rs.getString("email"));
				System.out.println(lstMember);
			}
			pStmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lstMember;

	}

}
