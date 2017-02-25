package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBox {

	static boolean answer;
	
	public static boolean displayConfirmBox(String title, String Message){
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);
		Label label = new Label();
		label.setText(Message);
		
		//Creating two buttons, yes and no
		Button yesButton = new Button("Yes");
		Button noButton = new Button("No");
		
		yesButton.setOnAction(e -> {
			answer = true;
			window.close();
			
		});
		
		
		noButton.setOnAction(e -> {
			answer = false;
			window.close();
			
		});
		
		
		
		HBox hLayout = new HBox(4);
		VBox vLayout = new VBox(10);
		hLayout.getChildren().addAll(yesButton, noButton);
		vLayout.getChildren().addAll(label, hLayout);
		
		vLayout.setAlignment(Pos.CENTER);
		hLayout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(vLayout);
		window.setScene(scene);
		window.showAndWait();
		
		
		return answer;
		
	}
	
}
