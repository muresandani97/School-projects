package assignment4.bank.models;

@SuppressWarnings("serial")
public class SavingAccount extends Account implements java.io.Serializable{
	private float interest;

	public SavingAccount(int id, float interest) {
		super(id, 0);
		this.interest = interest;
	}

	public float getInterest() {
		return this.interest;
	}

	public void setInterest(float interest) {
		this.interest = interest;
	}

	public float computeWinnings() {
		return (interest / 100) * getBalance();
	}
}
