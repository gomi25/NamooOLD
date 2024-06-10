package dto;

import java.util.ArrayList;

public class ChatroomDto {
	private boolean bookmarkYn;
	private ArrayList<ProfileUrlColorDto> listProfileUrlColor;
	private int chatroomIdx;
	private String chatroomName;
	private String chatRecentDateTime;
	
	public ChatroomDto(boolean bookmarkYn, ArrayList<ProfileUrlColorDto> listProfileUrlColor, int chatroomIdx,
			String chatroomName, String chatRecentDateTime) {
		this.bookmarkYn = bookmarkYn;
		this.listProfileUrlColor = listProfileUrlColor;
		this.chatroomIdx = chatroomIdx;
		this.chatroomName = chatroomName;
		this.chatRecentDateTime = chatRecentDateTime;
	}

	public boolean isBookmarkYn() {
		return bookmarkYn;
	}

	public void setBookmarkYn(boolean bookmarkYn) {
		this.bookmarkYn = bookmarkYn;
	}

	public ArrayList<ProfileUrlColorDto> getListProfileUrlColor() {
		return listProfileUrlColor;
	}

	public void setListProfileUrlColor(ArrayList<ProfileUrlColorDto> listProfileUrlColor) {
		this.listProfileUrlColor = listProfileUrlColor;
	}

	public int getChatroomIdx() {
		return chatroomIdx;
	}

	public void setChatroomIdx(int chatroomIdx) {
		this.chatroomIdx = chatroomIdx;
	}

	public String getChatroomName() {
		return chatroomName;
	}

	public void setChatroomName(String chatroomName) {
		this.chatroomName = chatroomName;
	}

	public String getChatRecentDateTime() {
		return chatRecentDateTime;
	}

	public void setChatRecentDateTime(String chatRecentDateTime) {
		this.chatRecentDateTime = chatRecentDateTime;
	}
	
	
}
