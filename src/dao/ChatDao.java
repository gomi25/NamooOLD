package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dto.ChatroomDto;
import dto.ProfileUrlColorDto;

public class ChatDao {
	private Connection getConnection() throws Exception {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "namoo";	
		String pw = "7777";	
		
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, id, pw);
		
		return conn;
	}

	// (1) 채팅방 목록 구현#1 -- 2024-06-07
	// getBookmarkChatroomYn(int,int) : 해당 memberIdx가 해당 chatroomIdx를 bookmark 즐겨찾기 했으면 true, 아니면 false 를 리턴.
	public boolean getBookmarkChatroomYn(int memberIdx, int chatroomIdx) throws Exception {
		String sql = "SELECT COUNT(*) FROM bookmark" + 
				" WHERE member_idx_from=?" + 
				" AND chatroom_idx=?"; 
		
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, memberIdx);
		pstmt.setInt(2, chatroomIdx);
		
		ResultSet rs = pstmt.executeQuery();
		boolean result = false;
		// 여러번 수행하지 않아도 될 경우 while 대신 if문 사용해도 됨
		if(rs.next()) {
			result = (rs.getInt(1)==1);	// 첫 번째 컬럼 값이 1이면 true 아니면 false
		}
		rs.close();
		pstmt.close();
		conn.close();
		
		return result;
	}

	// (2) 채팅방 목록 구현#2 -- 2024-06-07
	// ArrayList<ProfileUrlColorDto> getListProfileUrlColorMax4(int chatroomIdx) : 프로필이미지(색깔)의 목록을 리턴.
	public ArrayList<ProfileUrlColorDto> getListProfileUrlColorMax4(int chatroomIdx) throws Exception {
		ArrayList<ProfileUrlColorDto> listRet = new ArrayList<ProfileUrlColorDto>();
		String sql = "SELECT cm.member_idx,  " + 
				"        m.profile_pic_url profile_pic_url1,  " + 
				"        (SELECT img_url FROM profile_img WHERE profile_img_idx = m.profile_img_idx) profile_pic_url2, " + 
				"        (SELECT color FROM color WHERE color_idx = m.color_idx) profile_color " + 
				" FROM chat_member cm INNER JOIN member m " + 
				" ON cm.member_idx = m.member_idx " + 
				" WHERE chatroom_idx = ?";
		
		Connection conn = getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, chatroomIdx);
		
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			int memberIdx = rs.getInt("member_idx");
			String profilePicUrl1 = rs.getString("profile_pic_url1");
			String profilePicUrl2 = rs.getString("profile_pic_url2");
			String profileColor = rs.getString("profile_color");
			ProfileUrlColorDto dto = new ProfileUrlColorDto(memberIdx, profilePicUrl1, profilePicUrl2, profileColor);
			listRet.add(dto);
		}
		rs.close();
		pstmt.close();
		conn.close();
		
		return listRet;
	}
	
	//	System.out.println("<< 채팅방 목록 출력 >>");
	
	// 3) 채팅방idx / 채팅방이름
	public ArrayList<ChatroomDto> getChatroomList(int memberIdx, int teamIdx) throws Exception {
		ArrayList<ChatroomDto> listRet = new ArrayList<ChatroomDto>();
		String sql = "SELECT t1.chatroom_idx," + 
					"	t1.채팅방이름, (CASE t1.최근챗일시 WHEN TO_DATE('2001/01/01','yyyy/mm/dd') THEN null ELSE t1.최근챗일시 END) \"최근챗일시\"" + 
					" FROM (SELECT cr.chatroom_idx, cr.name \"채팅방이름\", NVL((SELECT MAX(c.chat_date) FROM chat c WHERE c.chatroom_idx=cr.chatroom_idx),'2001/01/01') \"최근챗일시\"" + 
					" FROM chatroom cr INNER JOIN chat_member cm " + 
					" ON cr.chatroom_idx = cm.chatroom_idx" + 
					" WHERE cr.team_idx = ?" + 
					" AND cm.member_idx = ?" + 
					" ORDER BY 최근챗일시 DESC) t1";
		
		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, teamIdx);
		pstmt.setInt(2, memberIdx);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			int chatroomIdx = rs.getInt("chatroom_idx");
			String chatroomName = rs.getString("채팅방이름");
			String chatRecentDateTime = rs.getString("최근챗일시");
			boolean bookmarkYn = getBookmarkChatroomYn(memberIdx, chatroomIdx);	
			ArrayList<ProfileUrlColorDto> listProfileUrlColor = getListProfileUrlColorMax4(chatroomIdx);
			ChatroomDto dto = new ChatroomDto(bookmarkYn, listProfileUrlColor, chatroomIdx, chatroomName, chatRecentDateTime);
			listRet.add(dto);		
		}
		rs.close();
		pstmt.close();
		conn.close();
		
		return listRet;
	}
}
