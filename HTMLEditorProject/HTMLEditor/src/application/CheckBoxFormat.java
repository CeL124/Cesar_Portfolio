package application;

import javafx.scene.control.*;
//import javafx.scene.shape.Rectangle;

public class CheckBoxFormat {
	
	private CheckBox boxItem;
	private String text;
	
	public CheckBoxFormat(String menuText){
		this.text = menuText;
		System.out.println("menuText: " + menuText + "\n");
		setBoxItem();
					
	}
	
	//set or change attribute tag or tool tip picture(from css)
			public void setBoxItem(){
				
			
				attribData attribCheck = new attribData();
				String attribText = attribCheck.getData(text);
				System.out.print(attribText);
				//Tooltip t = new Tooltip(attribText +"\n");
				//Rectangle rect = new Rectangle(15, 15);
				//Tooltip.install(text, t);
				this.boxItem = new CheckBox(text);
				
				
			}
			
		
			public CheckBox getBoxItem(){
				return this.boxItem;
			}
	
}
