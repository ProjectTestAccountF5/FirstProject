package ShopView.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ShopView.Controller;
import ShopView.ProductInfo;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CommonServiceImpl implements CommonService{
	
	@Override
	public Parent Load(String formPath) {
		Parent root = null;
		FXMLLoader loader = new  FXMLLoader(getClass().getResource(formPath));
		try {
			root = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return root;
	}
	
	@Override
	public Parent showWindow(Stage s, String formPath) {
//		ScrollPane sp =new ScrollPane();
		FXMLLoader loader =new FXMLLoader(getClass().getResource(formPath));
		Parent root =null;
		try {
			root=loader.load();
//			sp.setContent(root);
			s.setScene(new Scene(root));
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		Controller ctrler =loader.getController();
		ctrler.setRoot(root);
		
		s.show();
		
		return root;
	}

	@Override
	public void MsgBox(String title, String headerStr, String ContentTxt) {
		Alert alert =new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(headerStr);
		alert.setContentText(ContentTxt);
		alert.show();
	}

	@Override
	public List<String> DivideCom(String str) {
		List<String> com = new ArrayList<String>();
		String [] result = str.split(",");
		
//		System.out.println(result);
		for(int i=0;i<result.length;i++)
			com.add(result[i]);
		return com;
	}

}
