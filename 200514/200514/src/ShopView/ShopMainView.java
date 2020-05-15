package ShopView;

import java.io.IOException;

import CommonService.CommonServiceImpl;
import CommonService.ICommonService;
import javafx.application.Application;

import javafx.stage.Stage;

public class ShopMainView extends Application{
	@Override
	public void start(Stage primaryStage) throws IOException {
		ICommonService comServ =new CommonServiceImpl();
		comServ.showWindow(primaryStage, "../shopView.fxml");
	}
	public static void main(String[] args) {
		launch(args);
	}
	

}
