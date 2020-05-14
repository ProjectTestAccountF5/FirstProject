package Login.Service;

import java.net.URL;
import java.util.ResourceBundle;

import CommonService.CommonServiceImpl;
import CommonService.ICommonService;
import MemberShip.DB.IMemberShipDBManage;
import MemberShip.DB.MemberShipDBManageImpl;
import Webpage.WebController;
import application.Controller;
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
		System.out.println("ȸ������");
		Parent root = comServ.getScene(e);
		BorderPane bp = (BorderPane)root;
		bp.setCenter(comServ.Load("../MembershipFxml/membershipForm.fxml"));
		bp.getScene().getWindow().sizeToScene();
	}
	public void FavertalkProc(ActionEvent e) {
		System.out.println("���̹���");
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
		Controller ctrler = new Controller();
		ctrler.HomeView();
		System.out.println("Ȩ����");

	}
	public void LoginProc(ActionEvent e) {
		System.out.println("�α���");
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
				Label txtlbl = new Label("�α��� ����");
				txtlbl.setPrefSize(200, 100);
				txtlbl.setFont(new Font(36));
				Button successBtn = new Button("Ȯ��");
				successBtn.setFont(new Font(24));
				successBtn.setOnAction(event->{
					Parent poproot = comServ.getScene(event);
					Popup popscene = (Popup)poproot.getScene().getWindow();
					popscene.hide();
				});
				vbox.getChildren().addAll(txtlbl,successBtn);
				stackpane.getChildren().add(vbox);
				popup = comServ.showPopUp(root.getScene(),"�˸�", stackpane);

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
				Label txtlbl = new Label("�α��� ����");
				txtlbl.setFont(new Font(24));
				Button failBtn = new Button("Ȯ��");
				failBtn.setFont(new Font(24));
				failBtn.setOnAction(event->{
					Parent poproot = comServ.getScene(event);
					Popup popscene = (Popup)poproot.getScene().getWindow();
					popscene.hide();
				});
				vbox.getChildren().addAll(txtlbl,failBtn);
				stackpane.getChildren().add(vbox);
				popup = comServ.showPopUp(root.getScene(),"�˸�", stackpane);
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
			Label txtlbl = new Label("�α��� ����");
			txtlbl.setPrefSize(200, 100);
			txtlbl.setFont(new Font(36));
			Button failBtn = new Button("Ȯ��");
			failBtn.setFont(new Font(24));
			failBtn.setOnAction(event->{
				Parent poproot = comServ.getScene(event);
				Popup popscene = (Popup)poproot.getScene().getWindow();
				popscene.hide();
			});
			vbox.getChildren().addAll(txtlbl,failBtn);
			stackpane.getChildren().add(vbox);
			popup = comServ.showPopUp(root.getScene(),"�˸�", stackpane);
			idTxt.clear();
			idTxt.requestFocus();
			pwTxt.clear();

			recentUser = "GUEST";
		}
	}
	public void CancelProc(ActionEvent e) {
		Controller ctrler = new Controller();
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
