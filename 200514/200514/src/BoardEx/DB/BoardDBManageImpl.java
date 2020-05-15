package BoardEx.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardDBManageImpl implements IBoardDBManage {
	Board board = new Board();
	final int NUM = 0;
	final int TAG = 1;
	final int TITLE = 2;
	final int WRITER = 3;
	final int DATE = 4;
	final int VIEW = 5;
	final int LIKE = 6;
	final int CONTENT = 7;
	final static String DRIVER = "org.sqlite.JDBC";
	final static String DB = "jdbc:sqlite:src/DataBase/TableViewDB.db";
	Connection conn;

	public BoardDBManageImpl() {
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
	public boolean BoardProc(Board board) {
		String sql = "INSERT INTO Board " + "(tag, title, writer, date, view, like, content, boardstate) " + "VALUES (?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setString(1, board.getTag());
			pStmt.setString(2, board.getTitle());
			pStmt.setString(3, board.getWriter());
			pStmt.setString(4, board.getDate());
			pStmt.setInt(5, board.getView());
			pStmt.setInt(6, board.getLike());
			pStmt.setString(7, board.getContent());
			pStmt.setInt(8, board.getBoardstate());

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
	public List<String> ListProc() { // 게시판에 작성되있는 글 호출
		String sql = "SELECT * " +
				"FROM Board ";
		List<String> DBarray = new ArrayList<String>();
		try {
			PreparedStatement pStmt = conn.prepareStatement(sql);

			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				board.setNum(rs.getInt("Number")); 
				board.setTag(rs.getString("tag"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer")); 
				board.setDate(rs.getString("date"));
				board.setView(rs.getInt("view"));
				board.setLike(rs.getInt("like"));
				board.setContent(rs.getString("content"));
				board.setBoardstate(rs.getInt("boardstate"));
				board.setPrdnum(rs.getInt("prdnum"));
				board.setBoardtype(rs.getInt("boardtype"));
				DBarray.add(String.valueOf(board.getNum()));
				DBarray.add(board.getTag());
				DBarray.add(board.getTitle());
				DBarray.add(board.getWriter());
				DBarray.add(board.getDate());
				DBarray.add(String.valueOf(board.getView()));
				DBarray.add(String.valueOf(board.getLike()));
				DBarray.add(board.getContent());
				DBarray.add(String.valueOf(board.getBoardstate()));
				DBarray.add(String.valueOf(board.getPrdnum()));
				DBarray.add(String.valueOf(board.getBoardtype()));
				System.out.println(DBarray);
			}
			pStmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//return result;
		}
		return DBarray;

	}
	@Override
	public List<String> ReadProc() { // 게시판에 작성되있는 글 호출
		ListController lstctrler = new ListController();
		String sql = "SELECT * " +
				"FROM Board " + "WHERE Number = ?";
		List<String> DBRarray = new ArrayList<String>();
		try {
			PreparedStatement pStmt;

			pStmt = conn.prepareStatement(sql);

			pStmt.setString(1, lstctrler.setNum());
			pStmt.execute();


			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				board.setNum(rs.getInt("Number")); 
				board.setTag(rs.getString("tag"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer")); 
				board.setDate(rs.getString("date"));
				board.setView(rs.getInt("view"));
				board.setLike(rs.getInt("like"));
				board.setContent(rs.getString("content"));
				DBRarray.add(String.valueOf(board.getNum()));
				DBRarray.add(board.getTag());
				DBRarray.add(board.getTitle());
				DBRarray.add(board.getWriter());
				DBRarray.add(board.getDate());
				DBRarray.add(String.valueOf(board.getView()));
				DBRarray.add(String.valueOf(board.getLike()));
				DBRarray.add(board.getContent());
				System.out.println(DBRarray);
			}
			pStmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return DBRarray;

	}

	@Override
	public void UpdateBoard(String number, String title, String content) {
		String sql = "UPDATE Board SET title = ? , "
				+ "content = ? "
				+ "WHERE Number = ?";

		String url = "jdbc:sqlite:src/DataBase/TableViewDB.db";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

			// set the corresponding param
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, number);
			// update 
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void DeleteBoard(String number) {
		String sql = "DELETE FROM Board WHERE number = " + number;

		String url = "jdbc:sqlite:src/DataBase/TableViewDB.db";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
