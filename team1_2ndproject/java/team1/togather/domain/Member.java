package team1.togather.domain;

public class Member {
	private int mnum;
	private String  maddr;
	private String pfrloc;
	private String mname;
	private String gender;
	private String birth;
	private String pwd;
	private String phone;
	private int athur;
	public Member() {}
	public Member(int mnum, String maddr, String pfrloc, String mname, String gender, String birth, String pwd,
			String phone, int athur) {
		super();
		this.mnum = mnum;
		this.maddr = maddr;
		this.pfrloc = pfrloc;
		this.mname = mname;
		this.gender = gender;
		this.birth = birth;
		this.pwd = pwd;
		this.phone = phone;
		this.athur = athur;
	}
	public int getMnum() {
		return mnum;
	}
	public void setMnum(int mnum) {
		this.mnum = mnum;
	}
	public String getMaddr() {
		return maddr;
	}
	public void setMaddr(String maddr) {
		this.maddr = maddr;
	}
	public String getPfrloc() {
		return pfrloc;
	}
	public void setPfrloc(String pfrloc) {
		this.pfrloc = pfrloc;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getAthur() {
		return athur;
	}
	public void setAthur(int athur) {
		this.athur = athur;
	}
	
	
}
