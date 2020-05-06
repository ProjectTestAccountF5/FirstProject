package application;

import java.net.URL;
import java.util.ResourceBundle;

import CommonService.CommonServiceImpl;
import CommonService.ICommonService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class Controller implements Initializable {
	ICommonService comserv = new CommonServiceImpl();
	@FXML Button fullscrBtn;

	private ScrollPane scrPane = new ScrollPane();
	private Scene scene;
	static Popup mainPopup = new Popup();


	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}


	/*public void CheckBox(ActionEvent e) {
		//Parent root = (Parent)e.getSource();
		//CheckBox notChk = (CheckBox)root.lookup("#notShowCheckBox");
		CheckBox notChk = (CheckBox)comserv.getScene(e).lookup("#notShowCheckBox");
		checked = notChk.isSelected();
		Popup notPopup = (Popup)comserv.getScene(e).getScene().getWindow();
		if(checked)
			notPopup.hide();
	}*/
	public void HomeView(ActionEvent e) {

		BorderPane borderPane = (BorderPane)comserv.getScene(e);
		borderPane.setLeft(null);
		borderPane.setCenter(null);

		Parent root = (Parent)e.getSource();
		scene = root.getScene();
		mainPopup = comserv.showPopUp(mainPopup, scene);
	}
	public void ShopView(ActionEvent e) {
		BorderPane borderPane = (BorderPane)comserv.getScene(e);
		Parent leftScene = comserv.Load("../application/SubButton.fxml");
		borderPane.setLeft(leftScene);

		//BorderPane contentPane = new BorderPane();
		//scr.setOrientation(Orientation.VERTICAL);
		scrPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		scrPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);

		VBox vbox = new VBox();
		for(int i=0; i<100;i++) {
			Button btn = new Button(String.valueOf(i));
			btn.setOnAction(event->{
				System.out.println(btn.getText());
			});
			vbox.getChildren().add(btn);
		}
		scrPane.setContent(vbox);
		borderPane.setCenter(scrPane);
	}
	public void BoardView(ActionEvent e) {
		Button btn = (Button)e.getSource();
		System.out.println(btn.getId());
	}
	public void MembershipView(ActionEvent e) {
		Button btn = (Button)e.getSource();
		System.out.println(btn.getId());
	}
	public void LoginView(ActionEvent e) {
		Button btn = (Button)e.getSource();
		System.out.println(btn.getId());

	}
	public void SearchView(ActionEvent e) {
		BorderPane borderPane = (BorderPane)comserv.getScene(e);
		borderPane.setLeft(null);
		borderPane.setCenter(null);
		borderPane.setBottom(null);
		Parent centerScene = comserv.Load("../SearchItems/searchwindow.fxml");
		ScrollPane scrollPane = new ScrollPane();

		//scrollPane.setPrefSize(comserv.getScene(e).getScene().getWidth(), comserv.getScene(e).getScene().getHeight());
		scrollPane.setContent(centerScene);
		borderPane.setCenter(scrollPane);
	}
	public void CartView(ActionEvent e) {
		Button btn = (Button)e.getSource();
		System.out.println(btn.getId());

	}
	public void TopView(ActionEvent e) {
		scrPane.setVvalue(0);
	}
	public void BottomView(ActionEvent e) {
		scrPane.setVvalue(100);
	}

	public void FullScreen(ActionEvent e) {
		Stage stage = (Stage) fullscrBtn.getScene().getWindow();
		stage.setFullScreen(true);
	}
	

}
