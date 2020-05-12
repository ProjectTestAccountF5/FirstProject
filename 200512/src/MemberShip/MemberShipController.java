package MemberShip;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import CommonService.CommonServiceImpl;
import CommonService.ICommonService;
import MemberShip.DB.IMemberShipDBManage;
import MemberShip.DB.Member;
import MemberShip.DB.MemberShipDBManageImpl;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class MemberShipController implements Initializable{
	ICommonService comServ = new CommonServiceImpl();
	IMemberShipDBManage memberManage = new MemberShipDBManageImpl();
	final int ID = 0;
	final int NAME = 1;
	final int PW = 2;
	final int PWRE = 3;
	final int PHONE = 4;
	final int EMAIL = 5;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
	}
	private boolean isCheck(Map<String, TextField> txtFldMap, String []txtFldIdArr) {
		if(comServ.isEmpty(txtFldMap, txtFldIdArr)) {
			System.out.println("��� �־��");
			return false;
		}
		return true;
	}
	public void CancelProc(ActionEvent e) {
		Parent root = comServ.getScene(e);
		BorderPane bp = (BorderPane)root;
		bp.getChildren().clear();
		bp.setCenter(comServ.Load("../MembershipFxml/loginform.fxml"));
		bp.getScene().getWindow().sizeToScene();
	}
	public void MemberShipProc(ActionEvent e) {// ȸ������ �����͸� membershipDB�� �Է�
		Parent root = comServ.getScene(e);
		String []txtFldIdArr = {"#IdTxt", "#NameTxt", "#PwTxt","#PwTxtre", "#PhoneNumberTxt","#UserEmailTxt"};
		Map<String, TextField> txtFldMap = 
				comServ.getTextFieldInfo(root, txtFldIdArr);
		
		if(!isCheck(txtFldMap, txtFldIdArr))
			return;
		
		Member member = new Member();
		member.setId(txtFldMap.get(txtFldIdArr[ID]).getText());
		member.setName((txtFldMap.get(txtFldIdArr[NAME]).getText()));
		member.setPw((txtFldMap.get(txtFldIdArr[PW]).getText()));
		member.setPhone((txtFldMap.get(txtFldIdArr[PHONE]).getText()));
		member.setEmail((txtFldMap.get(txtFldIdArr[EMAIL]).getText()));
		
		System.out.println(txtFldMap.get(txtFldIdArr[ID]).getText());
		System.out.println((txtFldMap.get(txtFldIdArr[NAME]).getText()));
		System.out.println((txtFldMap.get(txtFldIdArr[PW]).getText()));
		System.out.println((txtFldMap.get(txtFldIdArr[PWRE]).getText()));
		System.out.println((txtFldMap.get(txtFldIdArr[PHONE]).getText()));
		System.out.println((txtFldMap.get(txtFldIdArr[EMAIL]).getText()));
		
		if(memberManage.MemberProc(member)) {
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("ȸ������");
		alert.setContentText("ȸ�������� �����ϼ̽��ϴ�.");
		alert.show();
		
		BorderPane bp = (BorderPane)root;
		bp.getChildren().clear();
		bp.setCenter(comServ.Load("../MembershipFxml/loginform.fxml"));
		bp.getScene().getWindow().sizeToScene();
		}
		else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("ȸ������");
			alert.setContentText("ȸ�����Կ� �����ϼ̽��ϴ�.");
			alert.show();
		}
	}
	
		
		
}
