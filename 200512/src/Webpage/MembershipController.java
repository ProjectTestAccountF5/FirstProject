package Webpage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import CommonService.CommonServiceImpl;
import CommonService.ICommonService;
import Login.Service.LoginController;
import MemberShip.DB.IMemberShipDBManage;
import MemberShip.DB.MemberShipDBManageImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Popup;

public class MembershipController implements Initializable{
	ICommonService comServ = new CommonServiceImpl();
	LoginController logCtrler = new LoginController();
	IMemberShipDBManage memberDBManage = new MemberShipDBManageImpl();
	List<String> lstMember;
	static String recentUser;
	@FXML TextField IdTxt;
	@FXML TextField NameTxt;
	@FXML TextField PhoneNumTxt;
	@FXML TextField EmailTxt;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		System.out.println(recentUser);
		lstMember = memberDBManage.ReadProc(recentUser);
		
		IdTxt.setText(recentUser);
		IdTxt.setDisable(true);
		NameTxt.setText(lstMember.get(0));
		PhoneNumTxt.setText(lstMember.get(1));
		EmailTxt.setText(lstMember.get(2));

	}
		public void setRecentUser(String user) {
			recentUser = user;
		}
	public void Updatemember(ActionEvent e) {
		TextField idTxt = (TextField)comServ.getScene(e).lookup("#IdTxt");
		TextField nameTxt = (TextField)comServ.getScene(e).lookup("#NameTxt");
		TextField pwTxt = (TextField)comServ.getScene(e).lookup("#PwTxt");
		TextField cpwTxt = (TextField)comServ.getScene(e).lookup("#ChangePwTxt");
		TextField pnTxt = (TextField)comServ.getScene(e).lookup("#PhoneNumTxt");
		TextField emailTxt = (TextField)comServ.getScene(e).lookup("#EmailTxt");

		String id = idTxt.getText();
		String name = nameTxt.getText();
		String pw = pwTxt.getText();
		String cpw = cpwTxt.getText();
		String phone = pnTxt.getText();
		String email = emailTxt.getText();
		
		System.out.println("회원정보 수정");
		if(memberDBManage.LoginProc(id).contentEquals(pw)) {
		memberDBManage.UpdateMember(id, name, phone, cpw, email);
		Popup popup = new Popup();

		Parent root = comServ.getScene(e);
		popup = comServ.showPopUp(popup,root.getScene(), "회원정보 수정");
		BorderPane bp = (BorderPane)root;
		bp.setCenter(comServ.Load("../MembershipFxml/mypage(main).fxml"));
		bp.getScene().getWindow().sizeToScene();
		}
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("회원정보 수정");
			alert.setContentText("현재 비밀번호가 다릅니다");
			alert.show();
			pwTxt.clear();
			pwTxt.requestFocus();
		}
	}
	public void CancelProc(ActionEvent e) {
		Parent root = comServ.getScene(e);
		BorderPane bp = (BorderPane)root;
		bp.setCenter(comServ.Load("../MembershipFxml/mypage(main).fxml"));
		bp.getScene().getWindow().sizeToScene();
	}
}
