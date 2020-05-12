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
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class LoginController implements Initializable{
	ICommonService comServ = new CommonServiceImpl();
	IMemberShipDBManage memberManage = new MemberShipDBManageImpl();
	static public String recentUser;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}
	public void MembershipProc(ActionEvent e) {
		System.out.println("회원가입");
		Parent root = comServ.getScene(e);
		BorderPane bp = (BorderPane)root;
		bp.getChildren().clear();
		bp.setCenter(comServ.Load("../MembershipFxml/membershipForm.fxml"));
		bp.getScene().getWindow().sizeToScene();
	}
	public void FavertalkProc(ActionEvent e) {
		System.out.println("페이버톡");
		Parent root = comServ.getScene(e);
		BorderPane bp = (BorderPane)root;
		bp.getChildren().clear();
		bp.setCenter(comServ.Load("../MembershipFxml/favertalklogin.fxml"));

	}
	public void ExitProc(ActionEvent e) {
		System.out.println("닫기");
		Parent root = comServ.getScene(e);
		Stage stage = (Stage)root.getScene().getWindow();
		stage.close();

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
		if(matchedPw.contentEquals(pwTxt.getText())) {
			WebController wbctrler = new WebController();
			popup = comServ.showPopUp(popup, root.getScene(),"알림");

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
			popup = comServ.showPopUp(popup, root.getScene(),"알림");
			idTxt.clear();
			idTxt.requestFocus();
			pwTxt.clear();

			recentUser = "GUEST";
		}
	}
	public void CancelProc(ActionEvent e) {
		Parent root = comServ.getScene(e);
		BorderPane bp = (BorderPane)root;
		bp.getChildren().clear();
		bp.setCenter(comServ.Load("../MembershipFxml/loginform.fxml"));
		bp.getScene().getWindow().sizeToScene();

	}
}
