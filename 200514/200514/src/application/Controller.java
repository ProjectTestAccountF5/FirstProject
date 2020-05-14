package application;

import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class Controller implements Initializable {
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
		Parent leftScene = comserv.Load("../ShopView/shopView.fxml");	
		scrPane = new ScrollPane();
		scrPane.setContent(leftScene);
		//borderPane.setLeft(leftScene);

		//BorderPane contentPane = new BorderPane();
		//scr.setOrientation(Orientation.VERTICAL);
		scrPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		scrPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		scrPane.setContent(leftScene);
		borderPane.setCenter(scrPane);
		ShopMainController smctrler = new ShopMainController();
		smctrler.setRoot(leftScene);
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
		AnchorPane anchorpane = new AnchorPane();
		Pane pane = new Pane();
		pane.getChildren().add(centerScene);
		pane.setPrefSize(450, 300);
		anchorpane.getChildren().add(pane);
		anchorpane.getChildren().get(0).setLayoutX(620);
		anchorpane.getChildren().get(0).setLayoutY(300);
		//StackPane sp = new StackPane();
		//sp.getChildren().add(centerScene);
		borderPane.setLeft(null);
		borderPane.setCenter(anchorpane);
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
		borderPane.setCenter(null);
		borderPane.setBottom(null);
		Parent centerScene = comserv.Load("../SearchItems/searchwindow.fxml");
		ScrollPane scrollPane = new ScrollPane();

		//scrollPane.setPrefSize(comserv.getScene(e).getScene().getWidth(), comserv.getScene(e).getScene().getHeight());
		scrollPane.setContent(centerScene);
		borderPane.setCenter(scrollPane);
		ctrler.setRoot(root);
	}
	public void CartView(ActionEvent e) {
		MainPopupShowInit();
		
		Button btn = (Button)e.getSource();
		System.out.println(btn.getId());
		wbctrler.basketView(e);

	}
	public void TopView(ActionEvent e) {
		scrPane.setVvalue(0);
	}
	public void BottomView(ActionEvent e) {
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

}
