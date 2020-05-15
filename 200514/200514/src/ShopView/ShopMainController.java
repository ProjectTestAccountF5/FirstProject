package ShopView;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import BoardEx.DB.ListController;
import CommonService.CommonServiceImpl;
import CommonService.ICommonService;
import ShopView.Data.IProductManage;
import ShopView.Data.ProductManageImpl;
import ShopView.Service.DisplayOrderImpl;
import ShopView.Service.ShopDetailsService;
import ShopView.Service.ShopDetailsServiceImpl;
import ShopView.Service.ShopMainService;
import ShopView.Service.ShopMainServiceImpl;
import ShopView.Service.iDisplayOrder;
import application.HomeController;
import javafx.beans.binding.ObjectBinding;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class ShopMainController extends Controller implements Initializable {
	@FXML
	ScrollPane scrollPane;
	@FXML
	GridPane gridPane;
	@FXML
	ImageView prdImg1;
	@FXML
	Button prdBtn2;
	static private Parent root;
	private ICommonService comServ;
	private ShopMainService shopMainServ;
	private ShopDetailsService shopDetailServ;
	private IProductManage prodManage;
	ListController lstctrler;
	private ObjectBinding<Bounds> bounds;
	private String orderStr = "";
	private static List<Integer> prdNumLst;
	private static int boardstate;
	iDisplayOrder displayOrder = new DisplayOrderImpl();
	HomeController mainctrler;

	@Override
	public void setRoot(Parent root) {
		this.root = root;
	}
	public void setBoardState(int boardstate) {
		this.boardstate = boardstate;
	}
	public void setPrdNumLst(List<Integer> prdNumLst) {
		this.prdNumLst = prdNumLst;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		comServ = new CommonServiceImpl();
		shopMainServ = new ShopMainServiceImpl();
		shopDetailServ = new ShopDetailsServiceImpl();
		prodManage = new ProductManageImpl();
		mainctrler = new HomeController();
		lstctrler = new ListController();
		InitialDisplay(orderStr);
		getNode();
	}

	public Parent OpenPrdDetails(ArrayList<Integer> pos) { // 클릭한 뒤
		System.out.println("======shopMainController======");

		ArrayList<Integer> location = new ArrayList<Integer>(); // gridpane의 position값 가져오기
		location.addAll(pos);
//		for (int i = 0; i < pos.size(); i++)
//			System.out.println("pos " + i + " : " + pos.get(i));

		List<ProductInfo> lstProdInfo = new ArrayList<ProductInfo>();
		lstProdInfo.addAll(prodManage.getProductInfo(prdNumLst.get(pos.get(0))));
		Parent form = shopMainServ.OpenPrdDetails(); // shopMainServiceImpl

//		String [] colorItems= {lstProdInfo.get(0).getColor()};
//		String [] sizeItems= {lstProdInfo.get(0).getPrdsize()};

		comServ.DivideCom(lstProdInfo.get(0).getColor());
		comServ.DivideCom(lstProdInfo.get(0).getPrdsize());

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
		/*
		 * ImageView prdiv = (ImageView)form.lookup("#prdImgView");
		 * prdiv.setFitWidth(840); prdiv.setFitHeight(600); ImageView detailiv =
		 * (ImageView)form.lookup("#detailImgView"); detailiv.setFitHeight(4000);
		 * detailiv.setFitWidth(1300);
		 */
		return form;

	}

	public void InitialDisplay(String orderStr) {
		gridPane.getChildren().clear();
		List<VBox> vbox = prodManage.InitialDisplay(orderStr);
		for (int i = 0; i < vbox.size(); i++) {
			vbox.get(i).setAlignment(Pos.TOP_CENTER);
			gridPane.add(vbox.get(i), i % 3, i / 3);
		}
	}

	public void PriceLowBtn() {
		orderStr = displayOrder.DisplayOrder(root);
		InitialDisplay(orderStr);
	}

	public void PriceHighBtn() {
		orderStr = displayOrder.DisplayOrder(root);
		InitialDisplay(orderStr);
	}

	public void NameOrdBtn() {
		orderStr = displayOrder.DisplayOrder(root);
		InitialDisplay(orderStr);
	}

	public void getNode() {

		gridPane.setOnMouseClicked(e -> {
			ArrayList<Integer> pos = new ArrayList<Integer>();
			Parent root = (Parent) e.getSource();
			gridPane = (GridPane) root;
			Node clickedChild = e.getPickResult().getIntersectedNode();
			
			
			int index = gridPane.getChildren().indexOf(clickedChild);
			if(index==-1) {
	            clickedChild=clickedChild.getParent();
	            index =gridPane.getChildren().indexOf(clickedChild);
	         }
			System.out.println("index : " + index);
			pos.add(index);
			try {
				int column = gridPane.getColumnIndex(gridPane.getChildren().get(index));
				System.out.println("column : " + column);
				pos.add(column);
			} catch (NullPointerException e2) {
				int column = 0;
				System.out.println("column : " + column);
			}
			try {
				int row = gridPane.getRowIndex(gridPane.getChildren().get(index));
				System.out.println("row : " + row);
				pos.add(row);
			} catch (NullPointerException e2) {
				int row = 0;
				System.out.println("row : " + row);
			}
			Parent form = OpenPrdDetails(pos);
			StackPane sp = new StackPane();
			sp.setPrefWidth(1800);
			sp.getChildren().add(form);
			GridPane bp = (GridPane)e.getSource();
			BorderPane borderpane = (BorderPane)bp.getScene().getRoot();
			scrollPane = (ScrollPane)borderpane.getCenter();
			scrollPane.setContent(sp);
			scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
			scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
			ShopDetailsController sdctrler = new ShopDetailsController();
			mainctrler.setScrPane(scrollPane);
			sdctrler.setRoot(form);
			sdctrler.setBoardState(boardstate);

			BorderPane detailBorderPane = (BorderPane)sp.getChildren().get(0);

			BorderPane boardRootA = (BorderPane)comServ.Load("../BoardEx/DB/BoardListEx.fxml");
			BorderPane boardRootB = (BorderPane)comServ.Load("../BoardEx/DB/BoardListEx.fxml");
			System.out.println("바텀 bp"+detailBorderPane.getBottom());
			HBox bottomHBox = (HBox)detailBorderPane.getBottom(); 
			VBox leftVbox = (VBox)bottomHBox.getChildren().get(0);
			 VBox rightVbox = (VBox)bottomHBox.getChildren().get(1); 
			 BorderPane reviewPane = (BorderPane)leftVbox.lookup("#reviewPane"); 
			 BorderPane qnaPane =(BorderPane)rightVbox.lookup("#qnaPane"); 
			 System.out.println("리뷰페인: "+ reviewPane + "qna페인 : "+qnaPane);
			 reviewPane.getChildren().clear();
			 qnaPane.getChildren().clear();
			 ScrollPane a = (ScrollPane)boardRootA.getCenter();
			 System.out.println(a.getContent());
			 a.getContent().setId("aTableView");
			 reviewPane.setCenter(a);
			 ScrollPane b = (ScrollPane)boardRootB.getCenter();
			 b.getContent().setId("bTableView");
			 qnaPane.setCenter(b);
			 
			 System.out.println("rp" + ((ScrollPane)reviewPane.getCenter()).getContent() + "qnap"   + ((ScrollPane)qnaPane.getCenter()).getContent());
			 System.out.println(boardstate);
			 
			// ScrollPane sp1 = (ScrollPane)reviewPane.getCenter();
			// ScrollPane sp2 = (ScrollPane)qnaPane.getCenter();
			 lstctrler.setRoot((Parent)reviewPane,(Parent)qnaPane);
			 lstctrler.setBoardState(boardstate);
			 
		});
	}

	/*
	 * private class MyEventHandler implements EventHandler<Event>{
	 * 
	 * @Override public void handle(Event evt) {
	 * System.out.println(((Control)evt.getSource()).getId()); } } public Node
	 * getGidPaneNode(int col, int row) { for(Node node : gridPane.getChildren()) {
	 * if(gridPane.getColumnIndex(node)==col && gridPane.getRowIndex(node)==row)
	 * return node; } return null; }
	 */

}
