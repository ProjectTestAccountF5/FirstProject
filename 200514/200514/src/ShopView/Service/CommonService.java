package ShopView.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ShopView.ProductInfo;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public interface CommonService {
	public Parent showWindow(Stage s, String formPath);
	public void MsgBox(String title,String headerStr, String ContentTxt);
	public List<String> DivideCom(String str);
//	public Map<String, ComboBox<String>> getComboBoxInfo(Parent cmbInfo, String[] cmbArr);
//	public boolean isEmpty(Map<String, ProductInfo> prodInfo, String[] prodInfoArr);
	public Parent Load(String formPath);
}
