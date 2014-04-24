package big;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileNotFoundException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;

//import org.w3c.dom.Document ;
//import org.w3c.dom.Element ;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Main {
	private static final String ROOT_FILE_PATH = "crawl5\\";
	private static final String FILE = "file6.xml";
	static ArrayList<String> urlList;
	static Iterator<String> iterator;
	private static final String TITLE_TAG = "h1.heading";
	private static final String CONTENT_TAG = "div.text-block";
	public Main() {
		iterator = urlList.iterator();
	}

	public static void main(String[] args) throws IOException {

		int index = 0;
		Document doc;
		Xml test = new Xml();
		Element content;
		Element title;
		int j = 0;
		Scanner s = null;
		urlList = new ArrayList<String>();
		try {
			s = new Scanner(new File("test.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (s.hasNext()) {
			urlList.add(s.next());
		}
		s.close();
		File f = new File(ROOT_FILE_PATH);
		File[] allSubFiles = f.listFiles();

		for (int i = 0; i < allSubFiles.length - 1; i++) {
			File file = new File(ROOT_FILE_PATH + i + ".html");

			if (file.isDirectory()) {
				System.out.println(file.getAbsolutePath() + " is directory");
				// Steps for directory
			} else {
				System.out.println(file.getAbsolutePath() + " is file " + i);
				// steps for files
				doc = Jsoup.parse(file, null, urlList.get(i));

				content = doc.select(CONTENT_TAG).first();
				title = doc.select(TITLE_TAG).first();
				if (title != null && content != null) {

					test.AddDocument(index, title.text(), content.text(),
							urlList.get(i));
					System.out.println(urlList.get(i));
					index++;
					System.out.println(title.text());
				}
			}
		}
		test.Save(FILE);
	}

}
