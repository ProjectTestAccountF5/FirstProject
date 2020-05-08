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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class SelectDB {
	final static String DRIVER = "org.sqlite.JDBC";
	final static String DB = "jdbc:sqlite:C:/강민성 JAVA 취업반/JAVA강의/팀프로젝트/작업폴더/project.db";
	
	public void KeywordSearch(Parent root, String keyword, String orderStr, String searchCat, String priceOpt, String sizeOpt) {
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(DB);
			System.out.println("keyword :"+keyword);
			System.out.println("orderStr :"+orderStr);
			System.out.println("searchCat :"+searchCat);
			System.out.println("priceOpt :"+priceOpt);
			String sql =
					"SELECT * "+
			"FROM productInfo "+
			"WHERE prdName like ? "+
			searchCat+priceOpt+sizeOpt+orderStr;
			System.out.println(sql);
			GridPane resultGrid = (GridPane)root.lookup("#resultGrid");
			resultGrid.getChildren().clear();
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setString(1, "%"+keyword+"%");
			
			ResultSet rs = pStmt.executeQuery();
			
			List<String> prdInfo = new ArrayList<String>();
			List<String> prdImageSrc = new ArrayList<String>();
			
			while(rs.next()) {
				String prdStr;
				if(rs.getString("dcprice").isEmpty()==false) {
					prdStr = "상품명 : "+rs.getString("prdName")+"\n상품가격 : "+
							rs.getString("price")+"\n할인가격 : "+rs.getString("dcprice")+"\n색상 : "+
							rs.getString("color")+"\n평점 : "+rs.getString("score");
					}else {
					prdStr = "상품명 : "+rs.getString("prdName")+"\n상품가격 : "+
							rs.getString("price")+"\n색상 : "+
							rs.getString("color")+"\n평점 : "+rs.getString("score");
				}
				prdInfo.add(prdStr);
				prdImageSrc.add(rs.getString("imgsrc"));
			}
			
			List<ImageView> prdImage = new ArrayList<ImageView>();
			List<Label> lblLst = new ArrayList<Label>();
			List<VBox> vboxLst = new ArrayList<VBox>();
			
			for(int i=0;i<prdInfo.size();i++) {
				prdImage.add(new ImageView(new Image(getClass().getResourceAsStream("/SearchItems/ImageSource/"+prdImageSrc.get(i)))));
				lblLst.add(new Label(prdInfo.get(i)));
				vboxLst.add(new VBox(prdImage.get(i), lblLst.get(i)));
				resultGrid.add(vboxLst.get(i), i%3, i/3);
			}
			
			Label totalNum = (Label)root.lookup("#totalNum");
			totalNum.setText("Total : "+prdInfo.size()+" items");
			
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
