package application;

import java.net.URL;
import java.util.ResourceBundle;

import BoardEx.DB.ListController;
import CommonService.CommonServiceImpl;
import CommonService.ICommonService;
import ShopView.ShopMainController;
import Webpage.WebController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class HomeController implements Initializable {
	ICommonService comserv;
	WebController wbctrler;
	public static String userState = null;
	@FXML Button fullscrBtn;
	@FXML TextField userStateTxt;
	@FXML Button loginBtn;
	@FXML BorderPane mainPane;
	static private ScrollPane scrPane;
	static private Scene scene;
	static Popup mainPopup = new Popup();
	static private Parent root;
	static Node mainPopupNode;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		comserv = new CommonServiceImpl();
		wbctrler = new WebController();
		UserTextFieldControl(userState); 
		if(!userStateTxt.getText().contentEquals("GUEST")) {
			loginBtn.setText("LOGOUT");
		}
		mainPopupNode = comserv.Load("../application/PopUp.fxml");
		System.out.println("init완료");
	}
	
	public void setRoot(Parent root) {

		this.root = root;
		System.out.println("this.root : "+ this.root);
	}
	/*public void CheckBox(ActionEvent e) {
		//Parent root = (Parent)e.getSource();
		//CheckBox notChk = (CheckBox)root.lookup("#notShowCheckBox");
		CheckBox notChk = (CheckBox)comserv.getScene(e).lookup("#notShowCheckBox");
		checked = notChk.isSelected();
		Popup notPopup = (Popup)comserv.getScene(e).getScene().getWindow();
		if(checked)
			notPopup.hide();
	}*/
	
	public void setPopup(Popup popup) {
		this.mainPopup = popup;
	}
	public void MainPopupShowInit() {
		scene = root.getScene();
		if(mainPopup.isShowing()) {
			mainPopup.hide();
		}
		mainPopup = comserv.showPopUp(scene,"팝업창1",mainPopupNode);
	}
	public void HomeView() {
		MainPopupShowInit();
		
		//BorderPane borderPane = (BorderPane)comserv.getScene(e);
		BorderPane borderPane = (BorderPane)this.root;
		borderPane.setLeft(null);
		borderPane.setCenter(null);

		//Parent root = (Parent)e.getSource();
		//scene = root.getScene();
		
		
	}
	public void UserTextFieldControl(String user) {
		if(userState==null){
			userState="GUEST";
		}
		else {
		userState = user;
		}
		System.out.println(userState);

		userStateTxt.setText(userState);
		userStateTxt.setStyle("-fx-background-color: null;-fx-text-fill: white;");
		//usrTxt.setText(user);
		//usrTxt.setStyle("-fx-background-color: null;-fx-text-fill: white;");
	}
	public void ShopView(ActionEvent e) {
		MainPopupShowInit();
		
		BorderPane borderPane = (BorderPane)comserv.getScene(e);
		Parent shopView = comserv.Load("../ShopView/shopView.fxml");
		borderPane.setCenter(shopView);
		ShopMainController smctrler = new ShopMainController();
		this.scrPane = (ScrollPane)borderPane.getCenter();
		setScrPane((ScrollPane)scrPane.getContent().lookup("#shopScrPane"));
		smctrler.setRoot(shopView);
		System.out.println(scrPane.getId());
	}
	public void BoardView(ActionEvent e) {
		MainPopupShowInit();
		
		Button btn = (Button)e.getSource();
		System.out.println(btn.getId());
		Parent root = comserv.getScene(e);
		BorderPane bp = (BorderPane)root;
		bp.setLeft(null);
		bp.setCenter(comserv.Load("../BoardEx/DB/BoardListEx.fxml"));
		bp.getScene().getWindow().sizeToScene();
		ListController lstctrler =new ListController();
		lstctrler.setRoot(root);
		lstctrler.setBoardState(1);
	}
	public void MembershipView(ActionEvent e) {
		MainPopupShowInit();
		
		Parent root = comserv.getScene(e);
		System.out.println("사용자 : " + userStateTxt.getText());
		System.out.println(userStateTxt.getText().contentEquals("GUEST"));
		if(userStateTxt.getText().contentEquals("GUEST")) {	
			LoginView(e);
		}
		else{
			Button btn = (Button)e.getSource();
			System.out.println(btn.getId());
			BorderPane bp = (BorderPane)root;
			bp.setLeft(null);
			bp.setCenter(comserv.Load("../MembershipFxml/mypage(main).fxml"));
			return;
		}

	}
	public void LoginView(ActionEvent e) {
		MainPopupShowInit();
		
		Button btn = (Button)e.getSource();
		System.out.println(btn.getId());
		/*
		 * Stage stage = new Stage(); Parent root =
		 * comserv.Load("../MembershipFxml/loginform.fxml"); stage.setScene(new
		 * Scene(root)); stage.show();
		 */
		BorderPane borderPane = (BorderPane)comserv.getScene(e);
		Parent centerScene = comserv.Load("../MembershipFxml/loginform.fxml");
		
		StackPane pane = new StackPane();
		pane.getChildren().add(centerScene);
		pane.setPrefSize(1800, 932);
		scrPane.setContent(pane);
		scrPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		scrPane.setVbarPolicy(ScrollBarPolicy.NEVER);
		//StackPane sp = new StackPane();
		//sp.getChildren().add(centerScene);
		borderPane.setLeft(null);
		borderPane.setCenter(scrPane);
		borderPane.setBottom(null);
		if(userStateTxt.getText()!="GUEST") {
			userState = "GUEST";
			userStateTxt.setText(userState);
			loginBtn.setText("LOGIN");
		}
	}

	public void SearchView(ActionEvent e) {
		SearchItems.Controller ctrler = new SearchItems.Controller();
		MainPopupShowInit();
		
		BorderPane borderPane = (BorderPane)comserv.getScene(e);
		borderPane.setLeft(null);
		borderPane.setBottom(null);
		Parent centerScene = comserv.Load("../SearchItems/searchwindow.fxml");
		
		//scrollPane.setPrefSize(comserv.getScene(e).getScene().getWidth(), comserv.getScene(e).getScene().getHeight());
		borderPane.setCenter(centerScene);
		ctrler.setRoot(root);
		BorderPane searchPane = (BorderPane)borderPane.getCenter();
		setScrPane((ScrollPane)searchPane.getCenter());
		System.out.println("너비" + scrPane.getPrefWidth()+"높이"+scrPane.getHeight());
	}
	public void CartView(ActionEvent e) {
		MainPopupShowInit();
		
		Button btn = (Button)e.getSource();
		System.out.println(btn.getId());
		wbctrler.basketView(e);

	}
	public void TopView() {
		scrPane.setVvalue(0);
	}
	public void BottomView() {
		scrPane.setVvalue(100);
	}

	public void FullScreen(ActionEvent e) {
		Stage stage = (Stage) fullscrBtn.getScene().getWindow();
		stage.setFullScreen(true);
	}
	public void setUser(String user) {
		System.out.println(user);
		String state = user;
		UserTextFieldControl(state);
	}
	public void setScrPane(ScrollPane scrpane) {
		this.scrPane = scrpane;
	}

}
