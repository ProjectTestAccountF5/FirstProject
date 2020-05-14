package BoardWrite.Service;

import BoardEx.DB.Board;
import BoardEx.DB.BoardDBManageImpl;
import BoardEx.DB.IBoardDBManage;

public class BoardWriteServiceImpl implements IBoardWriteService{
	@Override
	public boolean BoardProc(Board board) {
		IBoardDBManage boardManage= new BoardDBManageImpl();
		return boardManage.BoardProc(board);
	}
}
