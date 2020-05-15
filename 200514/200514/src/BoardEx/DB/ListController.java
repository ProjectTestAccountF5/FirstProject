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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Window;

public class ListController implements Initializable{
	public static int BoardState = 0;
	public static int prdnum = 0;
	public static String num = null;
	static private Parent root;
	static private Parent root1;
	static private Parent root2;
	final int NUM = 0;
	final int TAG = 1; 
	final int TITLE = 2;
	final int WRITER = 3;
	final int DATE = 4;
	final int VIEW = 5;
	final int LIKE = 6;
	final int CONTENT = 7;
	final int BOARDSTATE = 8;
	final int PRDNUM = 9;
	final int BOARDTYPE = 10;

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
	public void setRoot(Parent root1,Parent root2) {
		this.root1 = root1;
		this.root2 = root2;

	}
	public void setPrdNum(int prdnum) {
		this.prdnum = prdnum;
	}
	public void setBoardState(int state) {
		System.out.println("셋보드스테이트 진행");
		BoardState = state;

		IBoardDBManage boardDB = new BoardDBManageImpl();
		List<String> DB = boardDB.ListProc();
		List<String> numlst = new ArrayList<String>();
		//BorderPane bp = (BorderPane)root;
		switch (BoardState) {


		case 1:{
			System.out.println("보드리스트를 호출하기위한 루트 " + root);
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

			System.out.println("리스트보드스테이트 : " + BoardState);




			for(int i = 0 ; i<DB.size();i++) {

				numlst.add(DB.get(i));
				if(numlst.size()==11) {
					System.out.println(numlst.get(BOARDSTATE));
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
		}break;
		case 2:{
			System.out.println("보드리스트를 호출하기위한 루트 " + (TableView)((ScrollPane)((BorderPane)root1).getCenter()).getContent());
			System.out.println("보드리스트를 호출하기위한 루트 " + ((BorderPane)root2).getCenter());
			System.out.println(((TableView)((ScrollPane)((BorderPane)root1).getCenter()).getContent()).getId().contentEquals("aTableView"));
			if(root1.getId().contentEquals("reviewPane")) {
				boardList =(TableView)((ScrollPane)((BorderPane)root1).getCenter()).getContent();
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

				System.out.println("리스트보드스테이트 : " + BoardState);




				for(int i = 0 ; i<DB.size();i++) {

					numlst.add(DB.get(i));
					if(numlst.size()==11) {
						if(Integer.parseInt(numlst.get(BOARDSTATE))==2 && Integer.parseInt(numlst.get(BOARDTYPE))==1) {
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
			if(((TableView)((ScrollPane)((BorderPane)root2).getCenter()).getContent()).getId().contentEquals("bTableView")) {
				boardList =(TableView)((ScrollPane)((BorderPane)root2).getCenter()).getContent();
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

				System.out.println("리스트보드스테이트 : " + BoardState);




				for(int i = 0 ; i<DB.size();i++) {

					numlst.add(DB.get(i));
					if(numlst.size()==11) {
						if(Integer.parseInt(numlst.get(BOARDSTATE))==2 && Integer.parseInt(numlst.get(BOARDTYPE))==2) {
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
		}break;
		default:
			break;
		}
		try {
			GetTitleProc();

		} catch (NullPointerException e) {
			System.out.println(BoardState);
			System.out.println("빈공간");
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
		if(BoardState == 1){
			boardList.setRowFactory(tv -> {

				// Define our new TableRow
				TableRow<TableRowDataModel> row = new TableRow<>();
				row.setOnMouseClicked(event -> {
					Parent rootv = (Parent)tv.getScene().getRoot();
					try {
						System.out.println(row.getItem().numListProperty());
					} catch (NullPointerException e) {
						System.out.println("빈공간입니다.");
						return;
					}
					num= String.valueOf(row.getItem().numListProperty().getValue());
					ReadController readctrler = new ReadController();
					readctrler.setBoardstate(BoardState);
					BorderPane borderPane = (BorderPane)rootv;
					Parent root = comserv.Load("../BoardEx/BoardReadEx.fxml");
					borderPane.setBottom(null);
					borderPane.setCenter(root);
				});
				return row;
			});
		}

		if(BoardState==2) {
			boardList.setRowFactory(tv -> {
				// Define our new TableRow
				TableRow<TableRowDataModel> row = new TableRow<>();
				row.setOnMouseClicked(event -> {
					System.out.println(event.getSource());
					//System.out.println(sptv);
					try {
						System.out.println(row.getItem().numListProperty());
					} catch (NullPointerException e) {
						System.out.println("빈공간입니다.");
						return;
					}
					num= String.valueOf(row.getItem().numListProperty().getValue());
					ReadController readctrler = new ReadController();
					readctrler.setBoardstate(BoardState);
					//BorderPane borderPane = (BorderPane)sptv.getContent();
					Parent root = comserv.Load("../BoardEx/BoardReadEx.fxml");
					//borderPane.setBottom(null);
					//borderPane.setCenter(root);
				});
				return row;
			});
		}
		return num;
	}
	public String setNum() {
		String number = this.num;
		return number;
	}

}
