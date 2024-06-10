package dto;

public class ProfileUrlColorDto {
	private int memberIdx;
	private String profilePicUrl1;
	private String profilePicUrl2;
	private String profileColor;

	public ProfileUrlColorDto(int memberIdx, String profilePicUrl1, String profilePicUrl2, String profileColor) {
		this.memberIdx = memberIdx;
		this.profilePicUrl1 = profilePicUrl1;
		this.profilePicUrl2 = profilePicUrl2;
		this.profileColor = profileColor;
	}

	public int getMemberIdx() {
		return memberIdx;
	}

	public void setMemberIdx(int memberIdx) {
		this.memberIdx = memberIdx;
	}

	public String getProfilePicUrl1() {
		return profilePicUrl1;
	}

	public void setProfilePicUrl1(String profilePicUrl1) {
		this.profilePicUrl1 = profilePicUrl1;
	}

	public String getProfilePicUrl2() {
		return profilePicUrl2;
	}

	public void setProfilePicUrl2(String profilePicUrl2) {
		this.profilePicUrl2 = profilePicUrl2;
	}

	public String getProfileColor() {
		return profileColor;
	}

	public void setProfileColor(String profileColor) {
		this.profileColor = profileColor;
	}
	
	
}
