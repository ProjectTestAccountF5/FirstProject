package BoardEx.DB;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import BoardEx.ReadController;
import BoardEx.WriteController;
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
	public static int BoardState = 0;
	public static String num = null;
	static private Parent root;
	final int NUM = 0;
	final int TAG = 1; 
	final int TITLE = 2;
	final int WRITER = 3;
	final int DATE = 4;
	final int VIEW = 5;
	final int LIKE = 6;
	final int BOARDSTATE = 8;
	
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
	public void setRoot(Parent root) {
		this.root = root;
		
	}
public void setBoardState(int state) {
	System.out.println("�º��彺����Ʈ ����");
	BoardState = state;

	IBoardDBManage boardDB = new BoardDBManageImpl();
	List<String> DB = boardDB.ListProc();
	List<String> numlst = new ArrayList<String>();
	//BorderPane bp = (BorderPane)root;
	switch (BoardState) {
	
	
	case 1:{
		System.out.println("���帮��Ʈ�� ȣ���ϱ����� ��Ʈ " + root);
	boardList =(TableView)root.lookup("#boardList");
	System.out.println(boardList);
	numListColumn = (TableColumn)boardList.getVisibleLeafColumn(NUM);
	tagListColumn = (TableColumn)boardList.getVisibleLeafColumn(TAG);
	titleListColumn = (TableColumn)boardList.getVisibleLeafColumn(TITLE);
	writerListColumn = (TableColumn)boardList.getVisibleLeafColumn(WRITER);
	dateListColumn = (TableColumn)boardList.getVisibleLeafColumn(DATE);
	viewListColumn = (TableColumn)boardList.getVisibleLeafColumn(VIEW);
	likeListColumn = (TableColumn)boardList.getVisibleLeafColumn(LIKE);
	
	
	numListColumn.setCellValueFactory(cellData -> cellData.getValue().numListProperty().asObject());
	tagListColumn.setCellValueFactory(cellData -> cellData.getValue().tagListProperty());
	titleListColumn.setCellValueFactory(cellData -> cellData.getValue().titleListProperty());
	writerListColumn.setCellValueFactory(cellData -> cellData.getValue().writerListProperty());
	dateListColumn.setCellValueFactory(cellData -> cellData.getValue().dateListProperty());
	viewListColumn.setCellValueFactory(cellData -> cellData.getValue().viewListProperty().asObject());
	likeListColumn.setCellValueFactory(cellData -> cellData.getValue().likeListProperty().asObject());

System.out.println("����Ʈ���彺����Ʈ : " + BoardState);




		for(int i = 0 ; i<DB.size();i++) {

			numlst.add(DB.get(i));
			if(numlst.size()==9) {
				if(Integer.parseInt(numlst.get(BOARDSTATE))==1) {
				boardList.getItems().add( new TableRowDataModel(
						new SimpleIntegerProperty(Integer.parseInt(numlst.get(NUM))),
						new SimpleStringProperty(numlst.get(TAG)),
						new SimpleStringProperty(numlst.get(TITLE)),
						new SimpleStringProperty(numlst.get(WRITER)),
						new SimpleStringProperty(numlst.get(DATE)), 
						new SimpleIntegerProperty(Integer.parseInt(numlst.get(VIEW))),
						new SimpleIntegerProperty(Integer.parseInt(numlst.get(LIKE)))
						)
						);
			
				}
				numlst.clear();
				continue;
			}
	}
	}
	default:
		break;
	}
	try {
		System.out.println("����ȿ�����");
		GetTitleProc();
		
	} catch (NullPointerException e) {
		System.out.println(BoardState);
		System.out.println("�����");
	}
}
public void WriteProc(ActionEvent e) {

	BorderPane borderPane = (BorderPane)comserv.getScene(e);
	Parent root = comserv.Load("../BoardEx/BoardWriteEx.fxml");
	borderPane.setBottom(null);
	borderPane.setCenter(root);
	Window window = borderPane.getScene().getWindow();
	window.sizeToScene();
	WriteController writectrler = new WriteController();
	writectrler.setBoardState(BoardState);
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
				System.out.println("������Դϴ�.");
				return;
			}
			num= String.valueOf(row.getItem().numListProperty().getValue());
			ReadController readctrler = new ReadController();
			readctrler.setBoardstate(BoardState);
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
