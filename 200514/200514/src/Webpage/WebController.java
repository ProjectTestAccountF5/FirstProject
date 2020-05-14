package Webpage;

import java.net.URL;
import java.util.ResourceBundle;

import CommonService.CommonServiceImpl;
import CommonService.ICommonService;
import Login.Service.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class WebController implements Initializable{
	static public String recentUser;
	ICommonService comServ = new CommonServiceImpl();
	LoginController logctrler = new LoginController();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	System.out.println(recentUser);
	}
	public void setUser(String user) {
		recentUser = user;
	}
	public void ProfileView(ActionEvent e) {
		MembershipController memberctrler = new MembershipController();
		memberctrler.setRecentUser(recentUser);
		BorderPane bp = (BorderPane)comServ.getScene(e);
		BorderPane bpm = (BorderPane)bp.lookup("#webMain");
		bpm.setCenter(comServ.Load("../MembershipFxml/mypage(회원정보).fxml"));
		bp.getScene().getWindow().sizeToScene();
	}
	public void webpage(ActionEvent e) {
		BorderPane bp = (BorderPane)comServ.getScene(e);
		BorderPane bpm = (BorderPane)bp.lookup("#webMain");
		bpm.setCenter(comServ.Load("../MembershipFxml/orderlist.fxml"));
		bp.getScene().getWindow().sizeToScene();
	}
	public void basketView(ActionEvent e) {
		BorderPane bp = (BorderPane)comServ.getScene(e);
		BorderPane bpm = (BorderPane)bp.lookup("#webMain");
		bpm.setCenter(comServ.Load("../MembershipFxml/shoppingbasket.fxml"));
		bp.getScene().getWindow().sizeToScene();
	}
	public void CancelProc(ActionEvent e) {
		Parent root = comServ.getScene(e);
		BorderPane bp = (BorderPane)root;
		bp.setCenter(comServ.Load("../MembershipFxml/loginform.fxml"));
		bp.getScene().getWindow().sizeToScene();
		recentUser = "GUEST";
		TextField userTextField = (TextField)root.lookup("#userStateTxt");
		userTextField.setText(recentUser);
		userTextField.setStyle("-fx-background-color: null;-fx-text-fill: white;");
		Button loginBtn = (Button)root.lookup("#loginBtn");
		loginBtn.setText("LOGIN");
	}
	public void BasketBuyProc(ActionEvent e) {
		System.out.println("장바구니 구매하기");
	}
	public void BasketCancelProc(ActionEvent e) {
		BorderPane bp = (BorderPane)comServ.getScene(e);
		BorderPane bpm = (BorderPane)bp.lookup("#webMain");
		bpm.setCenter(null);
		bp.getScene().getWindow().sizeToScene();
	}
}
