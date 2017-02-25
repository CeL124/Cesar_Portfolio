package application;
//import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.control.*;

import java.net.URL;

import com.sun.javafx.geom.Rectangle;

//import javafx.scene.image.ImageView;
import javafx.geometry.*;

public class AboutWorkbook {
	String some;
	Stage stage;
	MediaPlayer mediaPlayer;
	public AboutWorkbook(){
		 URL resource = getClass().getResource("ledZeppelin.mp3");
		 Media media = new Media(resource.toString());
	     mediaPlayer = new MediaPlayer(media);
	     mediaPlayer.play();
	    
	     
	     stage = new Stage();
	 	//stage.initModality(Modality.);
	 	stage.setTitle("About HTML_Workbench");
	 	stage.setMinWidth(500);
	 	stage.setMinHeight(500);
	 	
	 	
	 	
	 	//final ImageView imageView = new ImageView(new Image("application/2016-11-29.png"));
	 	Label lbl = new Label();
	 	String info =("Head honcho and people pleaser: \t\tCESAR \"purple belt\" LOPEZ\n\nUser guides and CSS raider: \t\t\tSTEVE\"dominator\" DICKENSON\n\nMercyless tester and diagram model: \tVIJAY \"catwalk\" PATEL\n\nLead docs: \t\t\t\t\t\tDAVID\"new shoes\" CHEW\n\nLeader of underdog rebel coders: \t\tCHRIS \"alamo\" VICTORES\n");
	 	lbl.setText(info);
	 	

	 	Button btnOK = new Button();
	 	btnOK.setText("CLOSE");
	 	btnOK.setOnAction(e -> aggregatorsOut());

	 	VBox pane = new VBox(20);
	 	
	 	pane.getChildren().addAll(lbl, btnOK);
	 	pane.setAlignment(Pos.BOTTOM_CENTER);



	 	pane.setStyle("-fx-background-image: url(\"/application/2016-11-29.png\");-fx-background-size: 500, 500;-fx-background-repeat: no-repeat;-fx-background-radius: 30;");
	 	
	 	
	 	Scene scene = new Scene(pane);
	 	
	 	stage.setScene(scene);
	 	stage.showAndWait();
	 	
	 
	}
	
	public void aggregatorsOut(){
		mediaPlayer.stop();
		stage.close();
	}

	public void setSome(String sa){
		this.some = sa;
		
	}
	public String getSome(){
		
		return some;
	}

}
