package CommonService;

import java.util.List;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Popup;
import javafx.stage.Stage;

public interface ICommonService {
	public Parent Load(String formPath);
	public Popup showPopUp(Scene scene, String title, Node node);
	public Parent getScene(ActionEvent e);
	public Boolean CheckBox(ActionEvent e);
	public boolean isEmpty(Map<String, TextField> txtFldMap, String[] txtFldIdArr);
	public Map<String, TextField> getTextFieldInfo(Parent root, String[] txtFldIdArr);
	public Popup showAlertPopUp(Scene scene, String title, Node node);
	public Parent showWindow(Stage s, String formPath);
	public List<String> DivideCom(String str);
}
