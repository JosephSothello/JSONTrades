package objects;

import java.util.Date;

public class Trade {
	
	private String cusip;
	private int secSID;
	private Date maturityDate;
	
	public Trade() {
	}

	public Trade(String cusip, int secSID, Date maturityDate) {
		this.cusip = cusip;
		this.secSID = secSID;
		this.maturityDate = maturityDate;
	}

	public String getCusip() {
		return cusip;
	}

	public void setCusip(String cusip) {
		this.cusip = cusip;
	}

	public int getSecSID() {
		return secSID;
	}

	public void setSecSID(int secSID) {
		this.secSID = secSID;
	}

	public Date getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(Date maturityDate) {
		this.maturityDate = maturityDate;
	}

	
}
