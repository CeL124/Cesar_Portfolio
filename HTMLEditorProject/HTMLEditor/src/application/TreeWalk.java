package application;

import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
//import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
//import org.dom4j.io.SAXReader;
import javafx.scene.control.*;

public class TreeWalk {
	
	private Document document;
	private Element element;
	//private TreeView<String> tree;
	private TreeItem<String> root = new TreeItem<String>("PRESS TO NAVIGATE YOUR TREE");
	TreeItem<String> parent;
	TreeItem<String> sibling;
	private Map<String, TreeItem<String>> Data = new HashMap<String, TreeItem<String>>();
	String key;
	int counter;
	
TreeWalk(Document document) {
		 this.document = document;
		 this.element = document.getRootElement();
		 this.parent = new TreeItem<String>("<" + element.getName().toUpperCase() +">"+" " + element.getUniquePath());
		 this.key=("<" + element.getName().toUpperCase() +">"+" " + element.getUniquePath());
		 Data.put(key, parent);
		 
		 //set a base so when there is no parent element the tree will not exclude nonbase items
		 root.getChildren().add(parent);
		 
		 //test to make sure tree is uploading to hashmap:
		 TreeItem<String> test = Data.get("<" + element.getName().toUpperCase() +">"+" " + element.getUniquePath());
		 System.out.println(test.getValue());
		 
	
	    }

public TreeItem<String> getTree(){
	treeWalk2(element);
	return root;//tree1;
}

public void treeWalk2(Element element){
	
	
	
	for ( int i = 0, size = element.nodeCount(); i < size; i++ ) {
		
        Node node = element.node(i);
        
        
        	
        if ( node instanceof Element ) {
        	//add tree item to parent
        	//its adding the child element first and then hitting the else it needs to add the child element first
        	//before it adds to the hash table
        	
        	parent=Data.get(key);
        	System.out.print(key+("\n"));
        	System.out.println(""+ parent.getValue());
        	
        	String siblingNode = "" + "<" + node.getName().toUpperCase() +">" + " " + node.getUniquePath();
        	TreeItem<String> sibling = new TreeItem<String>(siblingNode);
        	Object value =Data.get(siblingNode);
        	
        	if (value == null) {	
        		//if(!(Data.containsKey(key))){
        			Data.put(siblingNode, sibling);
        			System.out.print("coming here?");
        			
        			
        		}
        	
        	
        	parent.getChildren().add(sibling);
        	Data.replace(key, parent);
        	
        	
        	
        	 treeWalk2( (Element) node );
        }
        else {
        	key ="<" + element.getName().toUpperCase() +">"+" " + element.getUniquePath();
        	
        	
        	
        } 
      
	}
	
//return parent= Data.get(key);	
}
}