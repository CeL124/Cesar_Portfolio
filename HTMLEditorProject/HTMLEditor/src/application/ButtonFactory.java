package application;

import javafx.scene.input.*;
import javafx.event.EventHandler;
import javafx.scene.control.*;



public class ButtonFactory{

	
	private SplitMenuButton splitButton = new SplitMenuButton();
	private Button plainButton = new Button();
	private CheckBox checkButton = new CheckBox();
	//private String[] attributeForCheckbox;
	//private String buttonString;		
	
		/**
		 * initializes the objects. Constructor with no argument.
		 */
		public ButtonFactory(){
			this.splitButton.setText("null button");
			this.splitButton.setId("null Tab");
			this.plainButton.setText("null button");
			this.plainButton.setId("null Tab");
			//this.buttonString =null;
		}
		
		/**
		 * Deals with plain buttons and with tool tip for plain button
		 * @param id
		 * @param text
		 */
		public ButtonFactory(String id, String text){
			this.plainButton.setText(id);
			this.plainButton.setId(text);
			plainButton.setOnMouseEntered(new EventHandler<MouseEvent>(){
				@Override
				public void handle(MouseEvent event){
					Tooltip t = new Tooltip("get the data here +\n");
					System.out.println("what");
					plainButton.setTooltip(t);
				}
			});
		}
		
		/**
		 * Deals with split menu button
		 * @param id
		 * @param text
		 * @param attribute
		 */
		public ButtonFactory(String id, String text, String attribute){			
			this.splitButton.setText(id);
			this.splitButton.setId(text);	
			attribData buttonData = new attribData();
			//(buttondata.getData("Accept"));                              //(style.getText().toLowerCase())));
			 attributeParse(attribute);
			//this.buttonString=makeString();
			 
			
		}
		
		/**
		 * Gets the parsed global attributes.
		 * @param attribute
		 */
		public ButtonFactory(String attribute){
			
			globParser(attribute);
		}
		
		
		/**
		 * parses input and adds checkMenuItemsToButtonList
		 * @param attribute
		 * @return
		 */
		private String[] attributeParse(String attribute) {
			//split the string by spaces
			
			String[]y=attribute.split(" ");
			System.out.println(y.length);
			
			//make the string capitalized and add to split button
			//for (String s: y){
				for(int i = 0; i < y.length; i++){
				y[i]=y[i].toUpperCase();
				
				String x =""+ Atrib.valueOf(y[i]);
				System.out.println("\n" + x);
				//format the checkMenus
				MenuFormat menuFormatObj = new MenuFormat(x);
				CheckMenuItem item = menuFormatObj.getMenuItem();
				//add items to split menu
				splitButton.getItems().add(item);
			}
			
			
			return y;
		}
		
		/**
		 * Parses the global Attributes
		 * @param attribute
		 * @return
		 */
		private String[] globParser(String attribute) {
			// split the string by spaces

			String[] y = attribute.split(" ");
			System.out.println(y.length);

			// make the string capitalized and add to split button
			// for (String s: y){
			for (int i = 0; i < y.length; i++) {
				y[i] = y[i].toUpperCase();

				String x = "" + GlobalAttrib.valueOf(y[i]);
				System.out.println("\n" + x);
				// format the checkMenus
				CheckBoxFormat checkFormatObj = new CheckBoxFormat(x);
				CheckBox boxItem = checkFormatObj.getBoxItem();
				String boxText = boxItem.getText();
				//checkButton  = new CheckBox(boxText);
				checkButton.setText(boxText);
			}

			return y;
		}
		
		/**
		 * getter gets split button
		 * @return
		 */
		public SplitMenuButton getButton(){
			return this.splitButton;
		}
		
		/**
		 * getter. Gets normal button
		 * @return
		 */
		public Button getPlain(){
			return this.plainButton;
		}

		/**
		 * getter. Gets the check Box
		 * @return
		 */
		public CheckBox getGlobals(){
			return this.checkButton;
		}

		
		

}
