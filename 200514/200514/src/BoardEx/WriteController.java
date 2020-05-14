package BoardEx;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.ResourceBundle;

import BoardEx.DB.Board;
import BoardEx.DB.BoardDBManageImpl;
import BoardEx.DB.IBoardDBManage;
import BoardEx.DB.ListController;
import BoardWrite.Service.BoardWriteServiceImpl;
import BoardWrite.Service.IBoardWriteService;
import CommonService.CommonServiceImpl;
import CommonService.ICommonService;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.HTMLEditor;

public class WriteController implements Initializable{
	IBoardWriteService writeserv = new BoardWriteServiceImpl();
	ICommonService comserv = new CommonServiceImpl();
	ListController lstCtrler = new ListController();
	private Parent root;
	final int TAG = 0;
	final int TITLE = 1;
	final int WRITER = 2;
	final int CONTENT = 3;
	
	public void setRoot(Parent root) {
		this.root = root;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
	private boolean isCheck(Map<String, TextField> txtFldMap, String []txtFldIdArr) {
		if(comserv.isEmpty(txtFldMap, txtFldIdArr)) {
			System.out.println("��� �־��");
			return false;
		}
		return true;
	}
	public void WriteProc(ActionEvent e) {
		Parent root = (Parent)e.getSource();
		TextField Title = (TextField)root.getScene().getRoot().lookup("#titleTxt");
		System.out.println("���� : " + Title.getText());
		HTMLEditor html = (HTMLEditor)root.getScene().getRoot().lookup("#contentHtml");
		System.out.println("HTML : " + html.getHtmlText());
		BoardProc(e);
	}
	public void CancelProc(ActionEvent e) {
		BorderPane borderPane = (BorderPane)comserv.getScene(e);
		Parent root = comserv.Load("../BoardEx/DB/BoardListEx.fxml");
		borderPane.setCenter(root);
		IBoardDBManage borman = new BoardDBManageImpl();
		borman.ListProc();
	}
	public void BoardProc(ActionEvent e) { // �ۼ����� �����͸� BoardDB�� �Է�
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		root = comserv.getScene(e);
		String []txtFldIdArr = {"#tagTxt", "#titleTxt", "#writerTxt"};
		Map<String, TextField> txtFldMap = 
				comserv.getTextFieldInfo(root, txtFldIdArr);
		HTMLEditor content = (HTMLEditor)comserv.getScene(e).lookup("#contentHtml");
		
		if(!isCheck(txtFldMap, txtFldIdArr))
			return;
		
		Board board = new Board();
		board.setTag(txtFldMap.get(txtFldIdArr[TAG]).getText());
		board.setTitle((txtFldMap.get(txtFldIdArr[TITLE]).getText()));
		board.setWriter((txtFldMap.get(txtFldIdArr[WRITER]).getText()));
		board.setDate(format1.format(time).substring(0,10));
		board.setView(0);
		board.setLike(0);
		board.setContent(content.getHtmlText());
		
		if(writeserv.BoardProc(board))
		{	comserv.ErrorMsg("�Խñ�", "����", "�Խñ� �ۼ� ����");
			CancelProc(e);
		}
		else
		{	comserv.ErrorMsg("�Խñ�","����","�����ڿ��� �����ϼ���.");
		CancelProc(e);
		}
	}
}
