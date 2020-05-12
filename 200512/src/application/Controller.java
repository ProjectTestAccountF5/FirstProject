package application;

import java.net.URL;
import java.util.ResourceBundle;

import CommonService.CommonServiceImpl;
import CommonService.ICommonService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class Controller implements Initializable {
	ICommonService comserv = new CommonServiceImpl();
	public static String userState = null;
	@FXML Button fullscrBtn;
	@FXML TextField userStateTxt;
	@FXML Button loginBtn;
	private ScrollPane scrPane = new ScrollPane();
	private Scene scene;
	static Popup mainPopup = new Popup();
	Parent root;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		UserTextFieldControl(userState);     
		if(!userStateTxt.getText().contentEquals("GUEST")) {
			loginBtn.setText("LOGOUT");
		}
			
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
	public void setRoot(Parent root) {
		this.root = root;
	}
	public void HomeView(ActionEvent e) {

		BorderPane borderPane = (BorderPane)comserv.getScene(e);
		borderPane.setLeft(null);
		borderPane.setCenter(null);

		Parent root = (Parent)e.getSource();
		scene = root.getScene();
		mainPopup = comserv.showPopUp(mainPopup, scene,"팝업창1");
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
		BorderPane borderPane = (BorderPane)comserv.getScene(e);
		Parent leftScene = comserv.Load("../application/SubButton.fxml");
		borderPane.setLeft(leftScene);

		//BorderPane contentPane = new BorderPane();
		//scr.setOrientation(Orientation.VERTICAL);
		scrPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		scrPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);

		VBox vbox = new VBox();
		for(int i=0; i<100;i++) {
			Button btn = new Button(String.valueOf(i));
			btn.setOnAction(event->{
				System.out.println(btn.getText());
			});
			vbox.getChildren().add(btn);
		}
		scrPane.setContent(vbox);
		borderPane.setCenter(scrPane);
	}
	public void BoardView(ActionEvent e) {
		Button btn = (Button)e.getSource();
		System.out.println(btn.getId());
	}
	public void MembershipView(ActionEvent e) {
		Parent root = comserv.getScene(e);
		System.out.println("사용자 : " + userStateTxt.getText());
		System.out.println(userStateTxt.getText().contentEquals("GUEST"));
		if(userStateTxt.getText().contentEquals("GUEST")) {	
			BorderPane bp = (BorderPane)root;
			bp.setLeft(null);
			bp.setCenter(comserv.Load("../MembershipFxml/loginform.fxml"));
			bp.getScene().getWindow().sizeToScene();
			return;
		}
		else{
			Button btn = (Button)e.getSource();
			System.out.println(btn.getId());
			BorderPane bp = (BorderPane)root;
			bp.setLeft(null);
			bp.setCenter(comserv.Load("../MembershipFxml/mypage(main).fxml"));
			bp.getScene().getWindow().sizeToScene();
			return;
		}

	}
	public void LoginView(ActionEvent e) {
		Button btn = (Button)e.getSource();
		System.out.println(btn.getId());
		/*
		 * Stage stage = new Stage(); Parent root =
		 * comserv.Load("../MembershipFxml/loginform.fxml"); stage.setScene(new
		 * Scene(root)); stage.show();
		 */
		BorderPane borderPane = (BorderPane)comserv.getScene(e);
		borderPane.setLeft(null);
		borderPane.setCenter(null);
		borderPane.setBottom(null);
		Parent centerScene = comserv.Load("../MembershipFxml/loginform.fxml");
		borderPane.setCenter(centerScene);
		if(userStateTxt.getText()!="GUEST") {
			userState = "GUEST";
			userStateTxt.setText(userState);
			loginBtn.setText("LOGIN");
		}
	}

	public void SearchView(ActionEvent e) {
		BorderPane borderPane = (BorderPane)comserv.getScene(e);
		borderPane.setLeft(null);
		borderPane.setCenter(null);
		borderPane.setBottom(null);
		Parent centerScene = comserv.Load("../SearchItems/searchwindow.fxml");
		ScrollPane scrollPane = new ScrollPane();

		//scrollPane.setPrefSize(comserv.getScene(e).getScene().getWidth(), comserv.getScene(e).getScene().getHeight());
		scrollPane.setContent(centerScene);
		borderPane.setCenter(scrollPane);
	}
	public void CartView(ActionEvent e) {
		Button btn = (Button)e.getSource();
		System.out.println(btn.getId());

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
