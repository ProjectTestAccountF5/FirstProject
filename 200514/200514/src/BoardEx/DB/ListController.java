package BoardEx.DB;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import CommonService.CommonServiceImpl;
import CommonService.ICommonService;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Window;

public class ListController implements Initializable{

	public static String num = null;
	@FXML
	private TableView<TableRowDataModel> boardList;
	@FXML
	private TableColumn<TableRowDataModel, Integer> numListColumn;
	@FXML
	private TableColumn<TableRowDataModel, String> tagListColumn;
	@FXML
	private TableColumn<TableRowDataModel, String> titleListColumn;
	@FXML
	private TableColumn<TableRowDataModel, String> writerListColumn;
	@FXML
	private TableColumn<TableRowDataModel, String> dateListColumn;
	@FXML
	private TableColumn<TableRowDataModel, Integer> viewListColumn;
	@FXML
	private TableColumn<TableRowDataModel, Integer> likeListColumn;
	@FXML
	ObservableList<TableRowDataModel> myList;
	Board board = new Board();

	ICommonService comserv = new CommonServiceImpl();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		IBoardDBManage boardDB = new BoardDBManageImpl();
		List<String> DB = boardDB.ListProc();
		List<String> numlst = new ArrayList<String>();


		numListColumn.setCellValueFactory(cellData -> cellData.getValue().numListProperty().asObject());
		tagListColumn.setCellValueFactory(cellData -> cellData.getValue().tagListProperty());
		titleListColumn.setCellValueFactory(cellData -> cellData.getValue().titleListProperty());
		writerListColumn.setCellValueFactory(cellData -> cellData.getValue().writerListProperty());
		dateListColumn.setCellValueFactory(cellData -> cellData.getValue().dateListProperty());
		viewListColumn.setCellValueFactory(cellData -> cellData.getValue().viewListProperty().asObject());
		likeListColumn.setCellValueFactory(cellData -> cellData.getValue().likeListProperty().asObject());
		for(int i = 0 ; i<DB.size();i++) {
			numlst.add(DB.get(i));
			if(numlst.size()==8) {
				boardList.getItems().add( new TableRowDataModel(
						new SimpleIntegerProperty(Integer.parseInt(numlst.get(0))),
						new SimpleStringProperty(numlst.get(1)),
						new SimpleStringProperty(numlst.get(2)),
						new SimpleStringProperty(numlst.get(3)),
						new SimpleStringProperty(numlst.get(4)), 
						new SimpleIntegerProperty(Integer.parseInt(numlst.get(5))),
						new SimpleIntegerProperty(Integer.parseInt(numlst.get(6)))
						)
						);
				numlst.clear();
				continue;

			}
			try {
				GetTitleProc();
			} catch (NullPointerException e) {
				System.out.println("ºó°ø°£");
			}
		}

		/*
		 * ObservableList<TableRowDataModel> myList = FXCollections.observableArrayList(
		 * myList =FXCollections.observableArrayList( for(int i=0;i<DB.size();i++) new
		 * TableRowDataModel( Integer.parseInt(DB.get(i%7)), DB.get(i%7), DB.get(i%7),
		 * DB.get(i%7), Integer.parseInt(DB.get(i%7)), DB.get(i%7),
		 * Integer.parseInt(DB.get(i%7)) //new TableRowDataModel(new
		 * SimpleIntegerProperty(Integer.parseInt(DB.get(0))), new
		 * SimpleStringProperty(DB.get(1)), new SimpleStringProperty(DB.get(2)), new
		 * SimpleStringProperty(DB.get(3)), new SimpleStringProperty(DB.get(4)), new
		 * SimpleIntegerProperty(Integer.parseInt(DB.get(5))), new
		 * SimpleIntegerProperty(Integer.parseInt(DB.get(6)))) );
		 */
	}
	public void WriteProc(ActionEvent e) {

		BorderPane borderPane = (BorderPane)comserv.getScene(e);
		Parent root = comserv.Load("../BoardEx/BoardWriteEx.fxml");
		borderPane.setTop(null);
		borderPane.setBottom(null);
		borderPane.setCenter(root);
		Window window = borderPane.getScene().getWindow();
		window.sizeToScene();
	}
	public String GetTitleProc() {

		boardList.setRowFactory(tv -> {

			// Define our new TableRow
			TableRow<TableRowDataModel> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				Parent rootv = (Parent)tv.getScene().getRoot();
				try {
					System.out.println(row.getItem().numListProperty());
				} catch (NullPointerException e) {
					System.out.println("ºó°ø°£ÀÔ´Ï´Ù.");
					return;
				}
				num= String.valueOf(row.getItem().numListProperty().getValue());
				BorderPane borderPane = (BorderPane)rootv;
				Parent root = comserv.Load("../BoardEx/BoardReadEx.fxml");
				borderPane.setBottom(null);
				borderPane.setCenter(root);
				Window window = borderPane.getScene().getWindow();
				window.sizeToScene();
			});
			return row;
		});
		return num;

	}
	public String setNum() {
		String number = this.num;
		return number;
	}
}
