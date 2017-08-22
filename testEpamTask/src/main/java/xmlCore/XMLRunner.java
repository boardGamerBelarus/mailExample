package xmlCore;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class XMLRunner {

	public static ArrayList<Account> getDataAsList() {
		String path = XMLRunner.class.getProtectionDomain().getCodeSource().getLocation().getPath();

		try {
			// ������ SAX-������
			XMLReader xr = XMLReaderFactory.createXMLReader();
			// ������������� ���������� SAX-�������
			XMLCore xml = new XMLCore();
			xr.setContentHandler(xml);
			// ������ XML-����
			xr.parse(new InputSource(new FileReader(path + File.separator + "xmlCore" + File.separator + "data.xml")));
			// ������� ���������� ��� ���� �����
			return xml.get_all();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
