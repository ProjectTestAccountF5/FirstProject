package CommonService;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Window;

public class CommonServiceImpl implements ICommonService{
	static private Boolean checked = false;
	@FXML CheckBox notShowCheckBox;

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
	public Popup showPopUp(Popup popup, Scene scene, String title) {
		ScrollPane sp = new ScrollPane();
		sp.setContent(Load("../application/PopUp.fxml"));
		BorderPane container = new BorderPane();
        container.setStyle("-fx-background-color : white;");
        StackPane titleStackPane = new StackPane();
        titleStackPane.setStyle("-fx-background-color: steelblue;");
        Text titlePopUp = new Text(title);
        titlePopUp.setFont(new Font(40));
        titlePopUp.setStyle("-fx-fill : white");
        titleStackPane.getChildren().add(titlePopUp);
        titleStackPane.setPrefHeight(40);
        container.setTop(titleStackPane);
        container.setCenter(sp);

        // Dragging implementation:

        ObjectProperty<Point2D> mouseLocation = new SimpleObjectProperty<>();

        container.setOnMousePressed(event -> 
            mouseLocation.set(new Point2D(event.getScreenX(), event.getScreenY())));

        container.setOnMouseDragged(event -> {
            if (mouseLocation.get() != null) {
                double x = event.getScreenX();
                double deltaX = x - mouseLocation.get().getX() ;//x좌표의 변화량
                double y = event.getScreenY();
                double deltaY = y - mouseLocation.get().getY() ;//y좌표의 변화량
                //in case of 2 or more computer screens this help me to avoid get stuck on 1 screen
                if(Math.abs(popup.getX()-x)>popup.getWidth()){
                   popup.setX(x);
                   popup.setY(y);
                }else {
                popup.setX(popup.getX() + deltaX);//기존x좌표 + 마우스 드래그한 위치의 x좌표의 변화량
                popup.setY(popup.getY() + deltaY);//기존Y좌표 + 마우스 드래그한 위치의 Y좌표의 변화량
                }
                mouseLocation.set(new Point2D(x, y));
            }
        });
        container.setOnMouseReleased(event -> mouseLocation.set(null));
        Window window = scene.getWindow();
        popup.getScene().setRoot(container);
        popup.show(window);
		if(checked)
			popup.hide();
		return popup;
	}

	@Override
	public Parent getScene(ActionEvent e) {
		Parent btnObj = (Parent)e.getSource();
		return btnObj.getScene().getRoot();
	}

	@Override
	public Boolean CheckBox(ActionEvent e) {
		//Parent root = (Parent)e.getSource();
		//CheckBox notChk = (CheckBox)root.lookup("#notShowCheckBox");
		CheckBox notChk = (CheckBox)getScene(e).lookup("#notShowCheckBox");
		checked = notChk.isSelected();
		Popup notPopup = (Popup)getScene(e).getScene().getWindow();
		if(checked)
			notPopup.hide();
		return checked;
	}

	@Override
	public boolean isEmpty(Map<String, TextField> txtFldMap, String[] txtFldIdArr) {
		for(String txtFldId : txtFldIdArr) {
			TextField txtFld = txtFldMap.get(txtFldId);
			
			if(txtFld.getText().isEmpty()) {
				txtFld.requestFocus();
				return true;
			}
		}
		return false;
	}
	@Override
	public Map<String, TextField> getTextFieldInfo(Parent root, String[] txtFldIdArr) {
		Map<String, TextField> txtFldMap = new HashMap<String, TextField>();
		
		for(String txtFldId : txtFldIdArr) {
			TextField txtFld = (TextField)root.lookup(txtFldId);
			txtFldMap.put(txtFldId, txtFld);
		}
		return txtFldMap;
	}
}
