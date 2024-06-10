package test;

import java.util.ArrayList;
import java.util.Scanner;

import dao.ChatDao;
import dto.ChatroomDto;
import dto.ProfileUrlColorDto;

public class ChatDaoTest {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("<< 채팅방 목록 출력 >>");
		System.out.print("member_idx(ex.2) : ");
		int memberIdx = sc.nextInt();
		System.out.print("team_idx(ex.2) : ");
		int teamIdx = sc.nextInt();
		
		ChatDao dao = new ChatDao();
		ArrayList<ChatroomDto> list1 = dao.getChatroomList(memberIdx, teamIdx);

		// 이하는 list1에 담긴 내용을 출력 확인.
		System.out.println("-------------------");
		for(int i=0; i<=list1.size()-1; i++) {
			ChatroomDto dto = list1.get(i);
			int chatroomIdx = dto.getChatroomIdx();
			boolean bookmarkYn = dto.isBookmarkYn();
			ArrayList<ProfileUrlColorDto> listProfileUrl = dao.getListProfileUrlColorMax4(chatroomIdx);
			String chatroomName = dto.getChatroomName();
			String recentDateTime = dto.getChatRecentDateTime();
			System.out.println((i+1) + "번.");
			System.out.println("채팅방 즐겨찾기 여부 : " + bookmarkYn);
			System.out.println("채팅방 프로필 이미지(색깔) : ");
			for(int j=0; j<=listProfileUrl.size()-1; j++) {
				ProfileUrlColorDto dto2 = listProfileUrl.get(j);
				System.out.println("\t memberIdx = " + dto2.getMemberIdx());
				System.out.println("\t profile_pic_url1 = " + dto2.getProfilePicUrl1());
				System.out.println("\t profile_pic_url2 = " + dto2.getProfilePicUrl2());
				System.out.println("\t profile_color = " + dto2.getProfileColor());
			}
			System.out.println("채팅방 이름 : " + chatroomName);
			System.out.println("최근 메시지 일시 : " + recentDateTime);
		}
	}
}













