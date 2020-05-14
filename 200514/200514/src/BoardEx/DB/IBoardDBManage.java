package BoardEx.DB;

import java.util.List;

public interface IBoardDBManage {
	//게시판 정보를 DB에 저장
		public boolean BoardProc(Board board);

		public List<String> ListProc();
		public List<String> ReadProc();
		public void UpdateBoard(String number, String title, String content);
		public void DeleteBoard(String number);
}
