package big;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;

public class Xml {
	private String filename;
	private org.w3c.dom.Element rootElement;
	org.w3c.dom.Document doc;

	public Xml(String filename) {
		this.filename = filename;
		DocumentBuilderFactory docFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder docBuilder;
		try {
			docBuilder = docFactory.newDocumentBuilder();

			// root elements
			this.doc = docBuilder.newDocument();
			this.rootElement = this.doc.createElement("searchresult");
			doc.appendChild(rootElement);

			org.w3c.dom.Element query = doc.createElement("query");
			query.appendChild(doc.createTextNode(" "));
			this.rootElement.appendChild(query);

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		}
	}

	public Xml() {
		this("file.xml");
	}

	public void AddDocument(int id, String titleSource, String contentSource,
			String urlSource) {

		org.w3c.dom.Element document = doc.createElement("document");
		this.rootElement.appendChild(document);

		// set attribute to staff element
		Attr attr = doc.createAttribute("id");
		attr.setValue(Integer.toString(id));
		document.setAttributeNode(attr);

		// title elements
		org.w3c.dom.Element title = doc.createElement("title");
		title.appendChild(doc.createTextNode(titleSource));
		document.appendChild(title);

		// url elements
		org.w3c.dom.Element url = doc.createElement("url");
		url.appendChild(doc.createTextNode(urlSource));
		document.appendChild(url);
		// snippet elements
		org.w3c.dom.Element snippet = doc.createElement("snippet");
		snippet.appendChild(doc.createTextNode(contentSource));
		document.appendChild(snippet);

	}

	public void Save(String filename) {
		TransformerFactory transformerFactory = TransformerFactory
				.newInstance();
		Transformer transformer = null;
		try {
			transformer = transformerFactory.newTransformer();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(filename));

		try {
			transformer.transform(source, result);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("File saved!");

	}

}
