package ShopView.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ShopView.ProductInfo;
import ShopView.ShopMainController;
import ShopView.Service.DisplayOrderImpl;
import ShopView.Service.iDisplayOrder;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ProductManageImpl implements IProductManage{
	final static String DRIVER="org.sqlite.JDBC";
	final static String DB ="jdbc:sqlite:src/ShopView/Data/project.db";
	Connection conn;
	private String orderStr = "";
	iDisplayOrder displayOrd = new DisplayOrderImpl();
	
	public ProductManageImpl() {
		try {
			Class.forName(DRIVER);
			conn=DriverManager.getConnection(DB);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public List<VBox> InitialDisplay(String orderStr) {
		try {
			String sql ="SELECT * "+
					"FROM productInfo "+
					orderStr
					;
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			List<String> prdInfo = new ArrayList<String>();
			List<String> prdColor = new ArrayList<String>();
			List<String> prdScore = new ArrayList<String>();
			List<String> prdImageSrc = new ArrayList<String>();
			List<Integer> prdOrderNum = new ArrayList<Integer>();

			while (rs.next()) {
				String prdStr;
				String colorStr;
				String scoreStr;
				int prodNum;
				if(rs.getString("dcprice").isEmpty()==false) {
					prdStr = rs.getString("prdName")+"\nKRW "+
							rs.getString("price")+" → "+rs.getString("dcprice");
					}else {
					prdStr = rs.getString("prdName")+"\nKRW "+
							rs.getString("price");
				}
				colorStr = rs.getString("color");
				scoreStr = RatingStar(rs.getString("score"));
				prodNum = rs.getInt("prodNum");
				prdInfo.add(prdStr);
				prdColor.add(colorStr);
				prdScore.add(scoreStr);
				prdOrderNum.add(prodNum);
				prdImageSrc.add(rs.getString("imgsrc"));
			}
			
			ShopMainController mainController = new ShopMainController();
			mainController.setPrdNumLst(prdOrderNum);
			List<ImageView> prdImage = new ArrayList<ImageView>();
			List<Label> lblLst = new ArrayList<Label>();
			List<HBox> hboxLst = new ArrayList<HBox>();
			List<Label> scoreLbl = new ArrayList<Label>();
			List<VBox> vboxLst = new ArrayList<VBox>();
			
			for (int i = 0; i < prdInfo.size(); i++) {
				prdImage.add(new ImageView(
						new Image(getClass().getResourceAsStream("/ImgSource/" + prdImageSrc.get(i)))));
				lblLst.add(new Label(prdInfo.get(i)));
				HBox hbox = new HBox();
				hbox.setSpacing(5);
				hbox.setAlignment(Pos.CENTER);
				if(prdColor.get(i).contains("black")) {
					Label lbl = new Label();
					lbl.setPrefSize(30, 8);
					lbl.setStyle("-fx-border-color:white; -fx-background-color: black;");
					hbox.getChildren().add(lbl);
				}
				if(prdColor.get(i).contains("beige")) {
					Label lbl = new Label();
					lbl.setPrefSize(30, 8);
					lbl.setStyle("-fx-border-color:lightgray; -fx-background-color: beige;");
					hbox.getChildren().add(lbl);
				}
				if(prdColor.get(i).contains("gray")) {
					Label lbl = new Label();
					lbl.setPrefSize(30, 8);
					lbl.setStyle("-fx-border-color:white; -fx-background-color: darkgray;");
					hbox.getChildren().add(lbl);
				}
				if(prdColor.get(i).contains("skyblue")) {
					Label lbl = new Label();
					lbl.setPrefSize(30, 8);
					lbl.setStyle("-fx-border-color:white; -fx-background-color: skyblue;");
					hbox.getChildren().add(lbl);
				}
				if(prdColor.get(i).contains("white")) {
					Label lbl = new Label();
					lbl.setPrefSize(30, 8);
					lbl.setStyle("-fx-border-color:lightgray; -fx-background-color: white;");
					hbox.getChildren().add(lbl);
				}
				if(prdColor.get(i).contains("navy")) {
					Label lbl = new Label();
					lbl.setPrefSize(30, 8);
					lbl.setStyle("-fx-border-color:white; -fx-background-color: darkblue;");
					hbox.getChildren().add(lbl);
				}
				if(prdColor.get(i).contains("ivory")) {
					Label lbl = new Label();
					lbl.setPrefSize(30, 8);
					lbl.setStyle("-fx-border-color:lightgray; -fx-background-color: ivory;");
					hbox.getChildren().add(lbl);
				}
				if(prdColor.get(i).contains("olive")) {
					Label lbl = new Label();
					lbl.setPrefSize(30, 8);
					lbl.setStyle("-fx-border-color:white; -fx-background-color: olive;");
					hbox.getChildren().add(lbl);
				}
				hboxLst.add(hbox);
				scoreLbl.add(new Label(prdScore.get(i)));
				scoreLbl.get(i).setTextFill(Color.ORANGE);
				vboxLst.add(new VBox(prdImage.get(i), lblLst.get(i), hboxLst.get(i), scoreLbl.get(i)));
			}
			
			rs.close();
			stmt.close();
			return vboxLst;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String RatingStar(String score) {
		double star = Double.parseDouble(score);
		String starscore = "";
		for(int i=0; i<5; i++) {
			if (i < Math.round(star)) {
				starscore = starscore + "★";
			}else {
				starscore = starscore + "☆";
			}
		}		
		return starscore;
	}

	@Override
	public void DisplayOrder(String orderStr) {
		this.orderStr = orderStr;
	}

	@Override
	public List<ProductInfo> getProductInfo(int prodNum) {
		String sql ="SELECT * "+
				"FROM productInfo "+
				"WHERE prodNum like ? "+
				orderStr
				;
		List<ProductInfo> lstProductInfo =new ArrayList<ProductInfo>();
		
		try {
			PreparedStatement pStmt =conn.prepareStatement(sql);
			
			pStmt.setInt(1, prodNum);
			ResultSet rs =pStmt.executeQuery();
			
			while(rs.next()) {
				ProductInfo productInfo =new ProductInfo();
				
				productInfo.setPrdName(rs.getString("prdName"));
				productInfo.setPrice(rs.getString("price"));
				productInfo.setDcprice(rs.getString("dcprice"));
				productInfo.setColor(rs.getString("color"));
				productInfo.setPrdsize(rs.getString("prdsize"));
				productInfo.setQty(rs.getInt("qty"));
				productInfo.setStock(rs.getInt("stock"));
				productInfo.setTag(rs.getString("tag"));
				productInfo.setScore(rs.getDouble("score"));
				productInfo.setImgsrc(rs.getString("imgsrc"));
				productInfo.setImgdetail(rs.getString("imgdetail"));
				productInfo.setProdNum(rs.getInt("prodNum"));
				
				lstProductInfo.add(productInfo);
				
				pStmt.close();
//				System.out.println(productInfo.getColor()+" productManage db연결");
//				System.out.println(lstProductInfo.get(0).getColor()+"productManage lst추가");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return lstProductInfo;
		
	}

}
