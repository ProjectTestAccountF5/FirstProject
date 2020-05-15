package SearchItems.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import SearchItems.Controller;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class SelectDB {
	final static String DRIVER = "org.sqlite.JDBC";
	final static String DB = "jdbc:sqlite:src/ShopView/Data/project.db";

	public void KeywordSearch(Parent root, String keyword, String orderStr, String searchCat, String priceOpt,
			String sizeOpt) {
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(DB);
			System.out.println("keyword :" + keyword);
			System.out.println("orderStr :" + orderStr);
			System.out.println("searchCat :" + searchCat);
			System.out.println("priceOpt :" + priceOpt);
			String sql = "SELECT * " + "FROM productInfo " + "WHERE prdName like ? " + searchCat + priceOpt + sizeOpt
					+ orderStr;
			System.out.println(sql);
			GridPane resultGrid = (GridPane) root.lookup("#resultGrid");
			resultGrid.getChildren().clear();
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setString(1, "%" + keyword + "%");

			ResultSet rs = pStmt.executeQuery();

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
							rs.getString("price")+" ¡æ "+rs.getString("dcprice");
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
			
			Controller ctrler = new Controller();
			ctrler.setPrdNumLst(prdOrderNum);
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
				hbox.setAlignment(Pos.CENTER);
				hboxLst.add(hbox);
				scoreLbl.add(new Label(prdScore.get(i)));
				scoreLbl.get(i).setTextFill(Color.ORANGE);
				VBox resultVbox = new VBox(prdImage.get(i), lblLst.get(i), hboxLst.get(i), scoreLbl.get(i));
				resultVbox.setAlignment(Pos.CENTER);
				vboxLst.add(resultVbox);
				resultGrid.add(vboxLst.get(i), i % 3, i / 3);
			}

			Label totalNum = (Label) root.lookup("#totalNum");
			totalNum.setText("Total : " + prdInfo.size() + " items");

			rs.close();
			pStmt.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String RatingStar(String score) {
		double star = Double.parseDouble(score);
		String starscore = "";
		for(int i=0; i<5; i++) {
			if (i < Math.round(star)) {
				starscore = starscore + "¡Ú";
			}else {
				starscore = starscore + "¡Ù";
			}
		}		
		return starscore;
	}
}
