package application;


import javafx.scene.control.*;
import javafx.scene.shape.Rectangle;

public class MenuFormat {
	private CheckMenuItem menuItem;
	private String text;
	
		
		public MenuFormat(String menuText){
			this.text = menuText;
			System.out.println("menuText: " + menuText + "\n");
			setMenuItem();
						
		}
		
		//set or change attribute tag or tool tip picture(from css)
		public void setMenuItem(){
			
			//probably better to just make MenuFormat Static and initialize only once when the program loads this is going
			//to save like 200 hash tables instead of one...
			attribData attribCheck = new attribData();
			String attribText = attribCheck.getData(text);
			System.out.print(attribText);
			Tooltip t = new Tooltip(attribText +"\n");
			Rectangle rect = new Rectangle(15, 15);
			Tooltip.install(rect, t);
			this.menuItem = new CheckMenuItem(text,rect);
			
			
		}
		
	
		public CheckMenuItem getMenuItem(){
			return this.menuItem;
		}
	}
