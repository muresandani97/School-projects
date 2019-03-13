package assignment4.bank.serialize;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import assignment4.bank.models.Bank;

public class Serializer {

	public static Bank deserialize() {
		Bank theBank = new Bank();
		try {
			FileInputStream fIn = new FileInputStream("bank.ser");
			ObjectInputStream oIn = new ObjectInputStream(fIn);
			theBank = (Bank) oIn.readObject();
			oIn.close();
			fIn.close();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		return theBank;
	}

	public static void serialize(Bank theBank) {
		try {
			FileOutputStream fOut = new FileOutputStream("bank.ser");
			ObjectOutputStream oOut = new ObjectOutputStream(fOut);
			oOut.writeObject(theBank);
			oOut.close();
			fOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
