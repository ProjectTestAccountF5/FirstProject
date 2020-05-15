package SearchItems;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import CommonService.CommonServiceImpl;
import CommonService.ICommonService;
import SearchItems.DB.SelectDB;
import SearchItems.Service.DetailSearchImpl;
import SearchItems.Service.DisplayOrderImpl;
import SearchItems.Service.KeywordSearchImpl;
import SearchItems.Service.iDetailSearch;
import SearchItems.Service.iDisplayOrder;
import SearchItems.Service.iKeywordSearch;
import ShopView.ProductInfo;
import ShopView.ShopDetailsController;
import ShopView.ShopMainController;
import ShopView.Data.IProductManage;
import ShopView.Data.ProductManageImpl;
import ShopView.Service.ShopDetailsService;
import ShopView.Service.ShopDetailsServiceImpl;
import ShopView.Service.ShopMainService;
import ShopView.Service.ShopMainServiceImpl;
import application.HomeController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Controller implements Initializable {
	@FXML
	GridPane resultGrid;
	SelectDB selDB;
	iDisplayOrder displayOrder;
	iKeywordSearch keywordSearch;
	iDetailSearch detailSearch;
	IProductManage prodManage;
	ShopMainController shopmain;
	ShopMainService shopMainServ;
	ShopDetailsController sdctrler;
	HomeController homectrler;
	static private Parent root;
	private ICommonService comServ;
	private ShopDetailsService shopDetailServ;
	private String keyword;
	private String searchCat = "";
	private String orderStr = "";
	private String priceOpt = "";
	private String sizeOpt = "";
	private static List<Integer> prdNumLst;

	public void setRoot(Parent root) {
		this.root = root;
	}
	
	public void setPrdNumLst(List<Integer> prdNumLst) {
		this.prdNumLst = prdNumLst;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		selDB = new SelectDB();
		displayOrder = new DisplayOrderImpl();
		keywordSearch = new KeywordSearchImpl();
		detailSearch = new DetailSearchImpl();
		prodManage = new ProductManageImpl();
		comServ = new CommonServiceImpl();
		shopmain = new ShopMainController();
		shopMainServ = new ShopMainServiceImpl();
		shopDetailServ = new ShopDetailsServiceImpl();
		homectrler = new HomeController();
		getNode();
	}

	public void KeywordSearch() {
		keyword = keywordSearch.KeywordSearch(root);
		selDB.KeywordSearch(root, keyword, orderStr, searchCat, priceOpt, sizeOpt);

		MinPriceSet();
		MaxPriceSet();
	}

	public void SelectMenuCat() {
		searchCat = keywordSearch.SelectMenuCat(root);
	}

	public void PriceLowBtn() {
		orderStr = displayOrder.DisplayOrder(root);
		selDB.KeywordSearch(root, keyword, orderStr, searchCat, priceOpt, sizeOpt);
	}

	public void PriceHighBtn() {
		orderStr = displayOrder.DisplayOrder(root);
		selDB.KeywordSearch(root, keyword, orderStr, searchCat, priceOpt, sizeOpt);
	}

	public void NameOrdBtn() {
		orderStr = displayOrder.DisplayOrder(root);
		selDB.KeywordSearch(root, keyword, orderStr, searchCat, priceOpt, sizeOpt);
	}

	public void DetailSearch() {
		priceOpt = detailSearch.PriceSearch(root);
		sizeOpt = detailSearch.SizeSearch(root);

		selDB.KeywordSearch(root, keyword, orderStr, searchCat, priceOpt, sizeOpt);
	}
	
	public void getNode() {
		resultGrid.setOnMouseClicked(e -> {
			resultGrid = (GridPane) e.getSource();
			Node clickedChild = e.getPickResult().getIntersectedNode();
			
			int index = resultGrid.getChildren().indexOf(clickedChild);
			if(index==-1) {
	            clickedChild=clickedChild.getParent();
	            index =resultGrid.getChildren().indexOf(clickedChild);
	         }
			System.out.println("index : " + index);
			OpenPrdDetails(index);
		});
	}
	
	public void OpenPrdDetails(int prodNum) {
		sdctrler = new ShopDetailsController();
		sdctrler.setRoot(root);
		List<ProductInfo> lstProdInfo = new ArrayList<ProductInfo>();
		lstProdInfo.addAll(prodManage.getProductInfo(prdNumLst.get(prodNum)));
		Parent form = shopMainServ.OpenPrdDetails();
		System.out.println("openprd디테일까지 옴");
		shopDetailServ.AddComboBox(form, comServ.DivideCom(lstProdInfo.get(0).getColor()), "#colorCmb"); // 색상
		shopDetailServ.AddComboBox(form, comServ.DivideCom(lstProdInfo.get(0).getPrdsize()), "#sizeCmb"); // 사이즈
		shopDetailServ.AddComboBox(form, Arrays.asList("1"), "#qtyCmb"); // 수량
		shopDetailServ.AddTitleLbl(form, lstProdInfo.get(0).getPrdName(), "#prdName"); // 제품명
		shopDetailServ.AddScoreLbl(form, lstProdInfo.get(0).getScore(), "#scoreLbl");
		shopDetailServ.AlmoSoldOutLbl(form, lstProdInfo.get(0).getStock(), "#almoSoldOutLbl"); // 재고파악 후 품절임박 표시
		shopDetailServ.AddPriceLbl(form, lstProdInfo.get(0).getPrice(), lstProdInfo.get(0).getDcprice(), "#priceLbl",
				"#dcpriceLbl");
		shopDetailServ.AddImgView(form, "DetailImg/" + lstProdInfo.get(0).getImgdetail(), "#detailImgView");// 상세사진
		shopDetailServ.AddImgView(form, "ImgSource/" + lstProdInfo.get(0).getImgsrc(), "#prdImgView");// 대표사진
		BorderPane mainBorderPane = (BorderPane)root;
		ScrollPane sp = new ScrollPane();
		sp.setContent(form);
		mainBorderPane.setCenter(sp);
		homectrler.setScrPane(sp);
		sdctrler.setRoot(root);
		
		BorderPane detailBorderPane = (BorderPane)sp.getContent();

		BorderPane boardRootA = (BorderPane)comServ.Load("../BoardEx/DB/BoardListEx.fxml");
		BorderPane boardRootB = (BorderPane)comServ.Load("../BoardEx/DB/BoardListEx.fxml");
		System.out.println("바텀 bp"+detailBorderPane.getBottom());
		HBox bottomHBox = (HBox)detailBorderPane.getBottom(); 
		System.out.println(bottomHBox);
		VBox leftVbox = (VBox)bottomHBox.getChildren().get(0);
		 VBox rightVbox = (VBox)bottomHBox.getChildren().get(1); 
		 BorderPane reviewPane = (BorderPane)leftVbox.lookup("#reviewPane"); 
		 BorderPane qnaPane =(BorderPane)rightVbox.lookup("#qnaPane"); 
		 System.out.println("리뷰페인: "+ reviewPane + "qna페인 : "+qnaPane);
		 reviewPane.getChildren().clear();
		 qnaPane.getChildren().clear();
		 reviewPane.setCenter(boardRootA.getCenter());
		 qnaPane.setCenter(boardRootB.getCenter());
		
		

	}

	public void MinPriceSet() {
		TextField minPriceTxt = (TextField) root.lookup("#minPrice");
		minPriceTxt.textProperty().addListener((obs, oldTxt, newTxt) -> {
			minPriceTxt.setText(newTxt.replaceAll("[^0-9_]", ""));
		});
	}

	public void MaxPriceSet() {
		TextField maxPriceTxt = (TextField) root.lookup("#maxPrice");
		maxPriceTxt.textProperty().addListener((obs, oldTxt, newTxt) -> {
			maxPriceTxt.setText(newTxt.replaceAll("[^0-9_]", ""));
		});
	}
}
