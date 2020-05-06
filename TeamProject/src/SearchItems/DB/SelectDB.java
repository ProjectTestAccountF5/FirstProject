package SearchItems.DB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class SelectDB {
	final static String DRIVER = "org.sqlite.JDBC";
	final static String DB = "jdbc:sqlite:C:/Users/khlee/Documents/īī���� ���� ����/���/project.db";

	public void KeywordSearch(Parent root, String keyword, String orderStr, String searchCat, String priceOpt) {
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(DB);
			System.out.println("keyword :"+keyword);
			System.out.println("orderStr :"+orderStr);
			System.out.println("searchCat :"+searchCat);
			String sql =
					"SELECT * "+
			"FROM productInfo "+
			"WHERE prdName like ? "+
			searchCat+priceOpt;
			
			sql=sql+orderStr;
			System.out.println(sql);
			GridPane resultGrid = (GridPane)root.lookup("#resultGrid");
			resultGrid.getChildren().clear();
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setString(1, "%"+keyword+"%");
			
			ResultSet rs = pStmt.executeQuery();
			
			List<String> prdInfo = new ArrayList<String>();
			
			while(rs.next()) {
				String prdStr;
				if(rs.getString("dcprice").isEmpty()==false) {
					prdStr = "��ǰ�� : "+rs.getString("prdName")+"\n��ǰ���� : "+
							rs.getString("price")+"\n���ΰ��� : "+rs.getString("dcprice")+"\n���� : "+
							rs.getString("color")+"\n���� : "+rs.getString("score");
					}else {
					prdStr = "��ǰ�� : "+rs.getString("prdName")+"\n��ǰ���� : "+
							rs.getString("price")+"\n���� : "+
							rs.getString("color")+"\n���� : "+rs.getString("score");
				}
				prdInfo.add(prdStr);
			}
			
			List<Label> lblLst = new ArrayList<Label>();
			for(int i=0;i<prdInfo.size();i++) {
				lblLst.add(new Label(prdInfo.get(i)));
				resultGrid.add(lblLst.get(i), i%3, i/3);
			}
			
			Label totalNum = (Label)root.lookup("#totalNum");
			totalNum.setText("Total : "+prdInfo.size()+" items");
			/*
			 * ��ǰ��, ��ǰ����, ���ΰ���, ����, ����
			 */
			
			rs.close();
			pStmt.close();
			conn.close();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
