package MemberShip.DB;

import java.util.List;

public interface IMemberShipDBManage {
	
	public boolean MemberProc(Member member);
	public void UpdateMember(String id, String name, String phone, String pw, String email);
	String LoginProc(String id);
	List<String> ReadProc(String id);
}
