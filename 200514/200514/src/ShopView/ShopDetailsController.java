package ShopView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import BoardEx.DB.ListController;
import CommonService.CommonServiceImpl;
import CommonService.ICommonService;
import ShopView.Data.IProductManage;
import ShopView.Data.ProductManageImpl;
import ShopView.Service.ShopDetailsService;
import ShopView.Service.ShopDetailsServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Popup;

public class ShopDetailsController extends Controller implements Initializable{
	static private Parent root;
	private ICommonService comServ;
	private ShopDetailsService shopDetailServ;
	private IProductManage productManage;
	static private 	StackPane sp;
	ICommonService comserv;
	private Popup popup;
	@Override
	public void setRoot(Parent root) {
		this.root =root;
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		comServ =new CommonServiceImpl();
		shopDetailServ = new ShopDetailsServiceImpl();
		productManage =new ProductManageImpl();


		ListController lstCtrler = new ListController();
		sp = new StackPane(); // 후기 게시판 호출용
		BorderPane mainbp = (BorderPane)root;


	}

	public int isCheck() {
		System.out.println("=========isCheck=======");
		int returnNum=0;
		ArrayList<String> cmb = new ArrayList<String>();
		cmb.add(0, "#colorCmb");
		cmb.add(1, "#sizeCmb");
		cmb.add(2, "#qtyCmb");
		System.out.println(cmb);
		for(int i=0;i<cmb.size();i++) {
			if(shopDetailServ.isComboBox(root, cmb.get(i))==0)	//false면
				returnNum+= 0;
			else
				returnNum+= 1;
		}
		if(returnNum<3) {
			System.out.println(returnNum);
			return 0;
		}
		return 1;
	}

	public void reviewProc(ActionEvent e) {
		BorderPane boardRoot = (BorderPane)comServ.Load("../../BoardEx/DB/BoardListEx.fxml");

		ListController lstCtrler = new ListController();

		Parent s1 = (Parent)e.getSource();
		HBox hbox = (HBox)s1.getParent();
		VBox vbox = (VBox)hbox.getParent();
		BorderPane reviewPane = (BorderPane)vbox.lookup("#reviewPane");
		reviewPane.setCenter(boardRoot.getCenter());
		lstCtrler.setRoot((Parent)boardRoot.getCenter());
		ScrollPane sp = (ScrollPane)(boardRoot.getCenter());
		sp.setPrefSize(890, 455);
		vbox.getChildren().add(sp);
		/*
		sp.getChildren().add(boardRoot);
		VBox vbox =(VBox)((Parent)e.getSource()).getParent().getParent();
		sp.setId("StackPaneListView");
		if(vbox.getChildren().contains(sp)) {
			vbox.getChildren().remove(sp);
			}
		lstCtrler.setRoot((Parent)sp.getChildren().get(0));
		vbox.getChildren().add(sp); 
		 */
		lstCtrler.setBoardState(2);

		/*BorderPane bp = (BorderPane)root;
		bp.setLeft(null);
		bp.setCenter(comServ.Load("../../BoardEx/DB/BoardListEx.fxml"));
		bp.getScene().getWindow().sizeToScene();
		 */

	}
	public void qnaProc(ActionEvent e) {

		//	Parent s1 = (Parent)e.getSource();
		//	HBox hbox = (HBox)s1.getParent();
		//	VBox vbox = (VBox)hbox.getParent();

	}
	public void buyProc() {
		if(isCheck()==0) {
			//comServ.MsgBox("메시지", "다시 시도", "상품 옵션을 선택하세요");

			StackPane stackpane = new StackPane();
			VBox vbox = new VBox();
			vbox.setAlignment(Pos.CENTER);
			Label txtlbl1 = new Label("다시 시도");
			txtlbl1.setFont(new Font(24));
			Label txtlbl2 = new Label("상품 옵션을 선택하세요");
			txtlbl2.setFont(new Font(24));
			Button failBtn = new Button("확인");
			failBtn.setFont(new Font(24));
			failBtn.setOnAction(event->{
				Parent poproot = comServ.getScene(event);
				Popup popscene = (Popup)poproot.getScene().getWindow();
				popscene.hide();
			});
			vbox.getChildren().addAll(txtlbl1,txtlbl2,failBtn);
			stackpane.getChildren().add(vbox);
			popup = comServ.showAlertPopUp(root.getScene(), "메시지", stackpane);
			return;
		}
		else {
			//	comServ.MsgBox("메시지", "성공", "결제요청되었습니다.");

			StackPane stackpane = new StackPane();
			VBox vbox = new VBox();
			vbox.setAlignment(Pos.CENTER);
			Label txtlbl1 = new Label("성공");
			txtlbl1.setFont(new Font(24));
			Label txtlbl2 = new Label("결제요청되었습니다.");
			txtlbl2.setFont(new Font(24));
			Button failBtn = new Button("확인");
			failBtn.setFont(new Font(24));
			failBtn.setOnAction(event->{
				Parent poproot = comServ.getScene(event);
				Popup popscene = (Popup)poproot.getScene().getWindow();
				popscene.hide();
			});
			vbox.getChildren().addAll(txtlbl1,txtlbl2,failBtn);
			stackpane.getChildren().add(vbox);
			popup = comServ.showAlertPopUp(root.getScene(), "메시지", stackpane);
			return;
		}
	}
	public void cartProc() {
		if(isCheck()==0) {
			//comServ.MsgBox("메시지", "다시 시도", "상품 옵션을 선택하세요");

			StackPane stackpane = new StackPane();
			VBox vbox = new VBox();
			vbox.setAlignment(Pos.CENTER);
			Label txtlbl1 = new Label("다시 시도");
			txtlbl1.setFont(new Font(24));
			Label txtlbl2 = new Label("상품 옵션을 선택하세요");
			txtlbl2.setFont(new Font(24));
			Button failBtn = new Button("확인");
			failBtn.setFont(new Font(24));
			failBtn.setOnAction(event->{
				Parent poproot = comServ.getScene(event);
				Popup popscene = (Popup)poproot.getScene().getWindow();
				popscene.hide();
			});
			vbox.getChildren().addAll(txtlbl1,txtlbl2,failBtn);
			stackpane.getChildren().add(vbox);
			popup = comServ.showAlertPopUp(root.getScene(), "메시지", stackpane);
			return;
		}
		else {
			//comServ.MsgBox("메시지", "성공", "장바구니에 추가되었습니다.");

			StackPane stackpane = new StackPane();
			VBox vbox = new VBox();
			vbox.setAlignment(Pos.CENTER);
			Label txtlbl1 = new Label("성공");
			txtlbl1.setFont(new Font(24));
			Label txtlbl2 = new Label("장바구니에 추가되었습니다.");
			txtlbl2.setFont(new Font(24));
			Button failBtn = new Button("확인");
			failBtn.setFont(new Font(24));
			failBtn.setOnAction(event->{
				Parent poproot = comServ.getScene(event);
				Popup popscene = (Popup)poproot.getScene().getWindow();
				popscene.hide();
			});
			vbox.getChildren().addAll(txtlbl1,txtlbl2,failBtn);
			stackpane.getChildren().add(vbox);
			popup = comServ.showAlertPopUp(root.getScene(), "메시지", stackpane);
		}
	}

}
