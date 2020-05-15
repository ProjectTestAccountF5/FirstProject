package Login.Service;

import java.net.URL;
import java.util.ResourceBundle;

import CommonService.CommonServiceImpl;
import CommonService.ICommonService;
import MemberShip.DB.IMemberShipDBManage;
import MemberShip.DB.MemberShipDBManageImpl;
import Webpage.WebController;
import application.HomeController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Popup;

public class LoginController implements Initializable{
	ICommonService comServ = new CommonServiceImpl();
	IMemberShipDBManage memberManage = new MemberShipDBManageImpl();
	static public String recentUser;
	@FXML
	TextField loginIdTxt;
	@FXML
	PasswordField loginPwTxt;
	@FXML
	Button LoginBtn;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		EnterKeyPressed();
		
	}

	public void MembershipProc(ActionEvent e) {
		System.out.println("회원가입");
		Parent root = comServ.getScene(e);
		BorderPane bp = (BorderPane)root;
		bp.setCenter(comServ.Load("../MembershipFxml/membershipForm.fxml"));
		bp.getScene().getWindow().sizeToScene();
	}
	public void FavertalkProc(ActionEvent e) {
		System.out.println("페이버톡");
		BorderPane borderPane = (BorderPane)comServ.getScene(e);
		Parent centerScene = comServ.Load("../MembershipFxml/favertalklogin.fxml");
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
	}
	public void ExitProc(ActionEvent e) {
		HomeController ctrler = new HomeController();
		ctrler.HomeView();
		System.out.println("홈으로");

	}
	public void LoginProc(ActionEvent e) {
		System.out.println("로그인");
		Parent root = comServ.getScene(e);


		TextField idTxt = (TextField)root.lookup("#loginIdTxt");
		PasswordField pwTxt = (PasswordField)root.lookup("#loginPwTxt");
		System.out.println(idTxt.getText());
		System.out.println(pwTxt.getText());

		String matchedPw = memberManage.LoginProc(idTxt.getText());
		Popup popup = new Popup();
		try {
			if(matchedPw.contentEquals(pwTxt.getText())) {

				WebController wbctrler = new WebController();
				StackPane stackpane = new StackPane();
				VBox vbox = new VBox();
				vbox.setPadding(new Insets(10));
				vbox.setAlignment(Pos.CENTER);
				Label txtlbl = new Label("로그인 성공");
				txtlbl.setPrefSize(200, 100);
				txtlbl.setFont(new Font(36));
				Button successBtn = new Button("확인");
				successBtn.setFont(new Font(24));
				successBtn.setOnAction(event->{
					Parent poproot = comServ.getScene(event);
					Popup popscene = (Popup)poproot.getScene().getWindow();
					popscene.hide();
				});
				vbox.getChildren().addAll(txtlbl,successBtn);
				stackpane.getChildren().add(vbox);
				popup = comServ.showAlertPopUp(root.getScene(),"알림", stackpane);

				recentUser = idTxt.getText();
				wbctrler.setUser(recentUser);
				TextField userTextField = (TextField)root.lookup("#userStateTxt");
				userTextField.setText(recentUser);
				userTextField.setStyle("-fx-background-color: null;-fx-text-fill: white;");
				Button lgbtn = (Button)root.lookup("#loginBtn");
				lgbtn.setText("LOGOUT");
				BorderPane bp = (BorderPane)root;
				bp.setCenter(null);
				bp.getScene().getWindow().sizeToScene();
			}
			else {
				StackPane stackpane = new StackPane();
				VBox vbox = new VBox();
				vbox.setAlignment(Pos.CENTER);
				Label txtlbl = new Label("로그인 실패");
				txtlbl.setFont(new Font(24));
				Button failBtn = new Button("확인");
				failBtn.setFont(new Font(24));
				failBtn.setOnAction(event->{
					Parent poproot = comServ.getScene(event);
					Popup popscene = (Popup)poproot.getScene().getWindow();
					popscene.hide();
				});
				vbox.getChildren().addAll(txtlbl,failBtn);
				stackpane.getChildren().add(vbox);
				popup = comServ.showAlertPopUp(root.getScene(),"알림", stackpane);
				idTxt.clear();
				idTxt.requestFocus();
				pwTxt.clear();

				recentUser = "GUEST";
			}
		}catch (NullPointerException er) {
			StackPane stackpane = new StackPane();
			VBox vbox = new VBox();
			vbox.setPadding(new Insets(10));
			vbox.setAlignment(Pos.CENTER);
			Label txtlbl = new Label("로그인 실패");
			txtlbl.setPrefSize(200, 100);
			txtlbl.setFont(new Font(36));
			Button failBtn = new Button("확인");
			failBtn.setFont(new Font(24));
			failBtn.setOnAction(event->{
				Parent poproot = comServ.getScene(event);
				Popup popscene = (Popup)poproot.getScene().getWindow();
				popscene.hide();
			});
			vbox.getChildren().addAll(txtlbl,failBtn);
			stackpane.getChildren().add(vbox);
			popup = comServ.showAlertPopUp(root.getScene(),"알림", stackpane);
			idTxt.clear();
			idTxt.requestFocus();
			pwTxt.clear();

			recentUser = "GUEST";
		}
	}
	public void CancelProc(ActionEvent e) {
		HomeController ctrler = new HomeController();
		ctrler.LoginView(e);

	}
	public void EnterKeyPressed() {
		loginIdTxt.setOnKeyPressed(event ->{
			if(event.getCode().equals(KeyCode.ENTER)) {
				if(loginIdTxt.getText().isEmpty()) {
					loginIdTxt.requestFocus();
				}
				else {
					if(loginPwTxt.getText().isEmpty()) {
						loginPwTxt.requestFocus();
					}
					else {
						LoginBtn.requestFocus();
					}
				}
			}
		});

		
	}

}
