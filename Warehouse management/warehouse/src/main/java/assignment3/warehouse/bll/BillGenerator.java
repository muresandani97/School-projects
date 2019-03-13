package assignment3.warehouse.bll;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;

import javax.swing.JOptionPane;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * This class is used to generate bills whenever a new order is placed and
 * inserted in the database
 */
public class BillGenerator {
	private static String FILE = "D:\\Scoala\\PT2018\\pt2018_30422_muresan_daniel_assignment_3\\warehouse\\bill.pdf";

	/**
	 * This method generates a pdf containing a title an a table with the data about
	 * the customer , product , quantity and the total price
	 * 
	 * @param orderID
	 *            an integer which uniquely identifies the order
	 * @param firstName
	 *            a String which represents the firstname of the customer
	 * @param lastName
	 *            a String which represents the lastname of the customer
	 * @param address
	 *            a String which represents the address of the customer
	 * @param email
	 *            a String which represents the email of the customer
	 * @param product
	 *            a String which represents the name of the product
	 * @param quantity
	 *            an integer which represents the quantity bought
	 * @param total
	 *            an integer which represents the total amount paid for the products
	 */
	public static void generateBill(int orderID, String firstName, String lastName, String address, String email,
			String product, int quantity, int total) {
		try {
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(FILE));
			document.open();
			addMetaData(document);
			addTitle(document, orderID);
			addTable(document, firstName, lastName, address, email, product, quantity, total);
			document.close();
		} catch (DocumentException e1) {
			e1.printStackTrace();
		} catch (FileNotFoundException e2) {
			JOptionPane.showMessageDialog(null, "Bill was not generated because the pdf file was open");
		}

	}

	private static void addMetaData(Document document) {
		document.addTitle("Bill");
		document.addKeywords("Bill, order , warehouse");
		document.addAuthor("Daniel Muresan");
	}

	private static void addTitle(Document document, int orderID) throws DocumentException {
		Paragraph title = new Paragraph("Bill for order " + orderID);
		title.setAlignment(Element.ALIGN_CENTER);
		addEmptyLine(title, 2);
		Paragraph date = new Paragraph("Bill generated at " + new Date());
		date.setAlignment(Element.ALIGN_CENTER);
		addEmptyLine(date, 3);
		document.add(title);
		document.add(date);
	}

	private static void addTable(Document document, String firstName, String lastName, String address, String email,
			String product, int quantity, int total) throws BadElementException {
		try {
			PdfPTable table = new PdfPTable(7);

			PdfPCell c1 = new PdfPCell(new Phrase("Firstname"));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);

			c1 = new PdfPCell(new Phrase("Lastname"));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);

			c1 = new PdfPCell(new Phrase("Address"));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);

			c1 = new PdfPCell(new Phrase("Email"));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);

			c1 = new PdfPCell(new Phrase("Product"));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);

			c1 = new PdfPCell(new Phrase("Quantity"));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);

			c1 = new PdfPCell(new Phrase("Total"));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);

			table.setHeaderRows(1);

			table.addCell(firstName);
			table.addCell(lastName);
			table.addCell(address);
			table.addCell(email);
			table.addCell(product);
			table.addCell(Integer.toString(quantity));
			table.addCell(Integer.toString(total));

			document.add(table);
		} catch (DocumentException e) {
			e.printStackTrace();
		}

	}

	private static void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}
}
