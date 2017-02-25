package application;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import java.util.*;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;



public class GetProjectStage {
	
	Stage stage;
	String keyString;
	String loadDoc="!&@";
	loadMap hopethisworks;
	ArrayList<ToggleButton> buttons;
	ScrollPane scroll;
	TilePane projects;
	
	public String ProjectStage(Object something){
		
		this.hopethisworks =(loadMap)something;
		
	 	stage = new Stage();
	 	stage.setTitle("Select a Project");
	 	stage.setMinWidth(500);
	 	stage.setMinHeight(350);
	 	
	 	projects = new TilePane();
	 	projects.setAlignment(Pos.BASELINE_CENTER);
	 	projects.setHgap(10);
	 	projects.setVgap(10);
	 	projects.setPadding(new Insets(10,10,10,10));
	 	projects.setMaxHeight(200);
	 	projects.setPrefRows(1);
	 	projects.setPrefColumns(10);
	 	
	 	

	 	
	 	Map<String,String> hashmap= hopethisworks.hashmap;
	 	buttons = new ArrayList<ToggleButton>();
	 	ToggleGroup group = new ToggleGroup();
	 	int i=0;
	 	for (String key : hashmap.keySet()) {
	 		int x = i;
	 		buttons.add( new ToggleButton("" + key ) );
	 		buttons.get(i).setToggleGroup(group);
	 		//ToggleButton tb1 = new ToggleButton(""+key);
	 		
	 		buttons.get(i).setUserData(key);
	 		buttons.get(i).setOnAction(e->setString(x));
	 		StackPane s = new StackPane();
	 		
	 		Random rand = new Random();
	 		int  n = rand.nextInt(9) + 1;
	 		Pane p = new Pane();
	 		p.setId(""+i);
	 		final ImageView imv = new ImageView();
	 	    final Image image2 = new Image(GetProjectStage.class.getResourceAsStream("" +n + ".jpg"));
	 	    imv.setImage(image2);
	 		//p.setStyle("-fx-background-image: url(""+
	 		/*Image image = new Image("" + n);
	 		ImagePattern imagePattern = new ImagePattern(image);
	 		Rectangle rect = new Rectangle(100,100);
	 		rect.setFill(imagePattern);*/
	 		//Rectangle rect = new Rectangle(100,100);
	 				
	 		
	 		
	 		//rect.setArcHeight(30);
	 	    //rect.setArcWidth(30);

	 		s.getChildren().addAll(imv, buttons.get(i));
	 		p.getChildren().add(s);//buttons.get(i)
	 		p.setId(buttons.get(i).getText());
	 		projects.getChildren().add(p);//s
	 		p.lookup("some");
	 	
	 		i++;
	 	}
	 	
	 	scroll = new ScrollPane(projects);
	 	scroll.setMaxHeight(220);
	 	scroll.setMinHeight(220);
	 	//scroll.setMinWidth(200);
	 	
	 	
	 	Button select = new Button("Load Project");
	 	select.setOnAction(e->confirm());
	 	Button cancel = new Button("Cancel");
	 	cancel.setOnAction(e->exitProj());
	 	
	 	Button delete = new Button("Delete project");
	 	delete.setOnAction(e->delete());
	 	
	 	select.setId("project_button");
	 	cancel.setId("project_button");
	 	HBox footer = new HBox(select,delete, cancel);
	 	footer.setStyle("-fx-background: red;");
	 	footer.setAlignment(Pos.CENTER);
	 	footer.setId("footer");
	 	Label headerLabel = new Label("Great Web Designs Require Great Frames\n\tSelect A project Template:");
	 	
	 	HBox Header = new HBox(headerLabel);
	 	Header.setAlignment(Pos.CENTER);
	 	//for updating label in css
	 	Header.setId("header");
	 	BorderPane main = new BorderPane();
	 	main.setCenter(scroll);
	 	main.setBottom(footer);
	 	main.setTop(Header);
	 	
	 	
	 	//String test =group.selectedToggleProperty().toString();
	 //	String test =group.getSelectedToggle().toString();
	// System.out.println(test);
	 	/*some = new TextField();
	 	VBox pane = new VBox(some);*/
	 	
	 	
	 	Scene scene = new Scene(main);
	 	scene.getStylesheets().add(getClass().getResource("project.css").toExternalForm());
	 	
	 	stage.setScene(scene);
	 	stage.showAndWait();
	 	
	 return  loadDoc;	
	}
	
	private void setString(int i){
		keyString = buttons.get(i).getText();
		System.out.print(keyString);
	}
	
	private void confirm(){
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText("Project Selection");
		alert.setContentText(keyString + " is a great looking project\ndont forget about saving your other project!\nPRESS OK TO IMPORT OR CANCEL TO ABORT");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			String hashProj = hopethisworks.getProj(keyString);
			loadDoc=hashProj;
			stage.close();
		} else {
		   System.out.println("sometimes else is not necessary");
		}
		
	}
	
	private void delete(){
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText("Project Selection");
		alert.setContentText("Are you sure you want to delete:\n " + keyString);
		
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){	
		hopethisworks.deleteProj(keyString);
		try {
       	 //projMap = new loadMap();
            FileOutputStream fileOut = new FileOutputStream("loadmap.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(hopethisworks);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in loadmap.ser");
         }catch(IOException i1) {
            i1.printStackTrace();
            System.out.print("any prob sir?");
         }
		
		loadDoc= "refresh!&@";
		stage.close();		
		}
	}
	
	private void exitProj(){
		stage.close();
	}
	

	
	

}
