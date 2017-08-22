package xmlCore;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.io.*;
import java.util.*;

// DefaultHandler -- класс, предоставляющий возможность
// реагировать на SAX-события (начало элемента, конец элемента и т.п.)
// SAX = Simple API to XML
public class XMLCore extends DefaultHandler {
	// обрабатываемый человек
	private Account currentAccount;

	// список всех людей
	private ArrayList<Account> accounts = new ArrayList<Account>();
	// Буфер для сбора текстовых данных при анализе XML
	private CharArrayWriter contents = new CharArrayWriter();

	// Реакция на обнаружение нового элемента
	public void startElement(String namespaceURI, String localName, String qName, Attributes attr) throws SAXException {
		// Очистка буфера текстовых данных
		contents.reset();
		// Если обнаружен новый человек, создаём объект
		if (localName.equals("account")) {
			currentAccount = new Account();
		}
		
		// Если обнаружено ФИО, определяем атрибут "пол"
		/* if (localName.equals("fio")) {
			if (attr.getValue("sex") != null) {
				currentPerson.sex = attr.getValue("sex");
			}
		} */
	}

	// Реакция на закрывающий тег элемента
	public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
		// Если закрывается ФИО, добавляем его человеку
		if (localName.equals("email")) {
			currentAccount.email = contents.toString();
		}
		if (localName.equals("password")) {
			currentAccount.password = contents.toString();
		}
	
		// Если закрывается набор данных о человеке, добавляем человека в список
		if (localName.equals("account")) {
			accounts.add(currentAccount);
			currentAccount = null;
		}
	}

	// Реакция на обнаружение символьных данных
	public void characters(char[] ch, int start, int length) throws SAXException {
		contents.write(ch, start, length);
	}

	// Вывод информации обо всех людях
	public void print_all() {
		for (int i = 0; i < accounts.size(); i++) {
			accounts.get(i).print();
		}
	}

	public ArrayList<Account> get_all() {
		return accounts;
	}
}
