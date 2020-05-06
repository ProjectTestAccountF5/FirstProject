package CommonService;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Popup;

public interface ICommonService {
	public Parent Load(String formPath);
	public Popup showPopUp(Popup popup, Scene scene);
	public Parent getScene(ActionEvent e);
	public Boolean CheckBox(ActionEvent e);
}
