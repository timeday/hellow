package xmlJava;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.Text;
import org.dom4j.io.SAXReader;

public class XmlS {
	
	public static void main(String[] args) throws Exception {
		/**
		 * <contactList>
	<contact id="001">
		<name>张三</name>
		<age>20</age>
		<phone>134222223333</phone>
		<email>zhangsan@qq.com</email>
		<qq>432221111</qq>
	</contact>
	<contact id="002">
		<name>李四</name>
		<age>20</age>
		<phone>134222225555</phone>
		<email>lisi@qq.com</email>
		<qq>432222222</qq>
	</contact>
</contactList>
		 */
		
		SAXReader reader=new SAXReader();
		
		Document doc = reader.read(new File("./src/contact.xml"));
		
		StringBuffer stringBuffer=new StringBuffer();
		
		
		Element rootElement = doc.getRootElement();
		System.out.println(rootElement.getName());
	
		senl(stringBuffer, rootElement);
		
		System.out.println(stringBuffer.toString());
		
	}

	private static void senl(StringBuffer stringBuffer, Element rootElement) {
		stringBuffer.append("<"+rootElement.getName()+">");
		
		    List<Attribute> attributes = rootElement.attributes();
		    
		    if(attributes!=null){
				for (Attribute attr : attributes) {
					System.out.println(attr.getName()+"="+attr.getValue());
					stringBuffer.append(" "+attr.getName()+"=\""+attr.getValue()+"\"");
				}
			}
		
		    stringBuffer.append(">");
		    Iterator<Node> nodeIterator = rootElement.nodeIterator();
		    
		    while (nodeIterator.hasNext()) {
				Node node = (Node) nodeIterator.next();
				
				if (node instanceof Element) {
					
					Element element=(Element) node;
					
					senl(stringBuffer, element);
				}
				
				if(node instanceof Text){
					Text text = (Text)node;
					stringBuffer.append(text.getText());
				}
			}
		stringBuffer.append("</"+rootElement.getName()+">");
	}

}
