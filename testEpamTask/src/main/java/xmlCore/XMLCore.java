package xmlCore;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.io.*;
import java.util.*;

// DefaultHandler -- �����, ��������������� �����������
// ����������� �� SAX-������� (������ ��������, ����� �������� � �.�.)
// SAX = Simple API to XML
public class XMLCore extends DefaultHandler {
	// �������������� �������
	private Account currentAccount;

	// ������ ���� �����
	private ArrayList<Account> accounts = new ArrayList<Account>();
	// ����� ��� ����� ��������� ������ ��� ������� XML
	private CharArrayWriter contents = new CharArrayWriter();

	// ������� �� ����������� ������ ��������
	public void startElement(String namespaceURI, String localName, String qName, Attributes attr) throws SAXException {
		// ������� ������ ��������� ������
		contents.reset();
		// ���� ��������� ����� �������, ������ ������
		if (localName.equals("account")) {
			currentAccount = new Account();
		}
		
		// ���� ���������� ���, ���������� ������� "���"
		/* if (localName.equals("fio")) {
			if (attr.getValue("sex") != null) {
				currentPerson.sex = attr.getValue("sex");
			}
		} */
	}

	// ������� �� ����������� ��� ��������
	public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
		// ���� ����������� ���, ��������� ��� ��������
		if (localName.equals("email")) {
			currentAccount.email = contents.toString();
		}
		if (localName.equals("password")) {
			currentAccount.password = contents.toString();
		}
	
		// ���� ����������� ����� ������ � ��������, ��������� �������� � ������
		if (localName.equals("account")) {
			accounts.add(currentAccount);
			currentAccount = null;
		}
	}

	// ������� �� ����������� ���������� ������
	public void characters(char[] ch, int start, int length) throws SAXException {
		contents.write(ch, start, length);
	}

	// ����� ���������� ��� ���� �����
	public void print_all() {
		for (int i = 0; i < accounts.size(); i++) {
			accounts.get(i).print();
		}
	}

	public ArrayList<Account> get_all() {
		return accounts;
	}
}
