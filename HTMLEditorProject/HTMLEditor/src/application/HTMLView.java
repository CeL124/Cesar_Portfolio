/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 *
 * @author stevendickinson
 */
public class HTMLView {

        public void renderHTML(String htmlText) {
         String toWebView = htmlText;
//        toWebView = Editor.getText();
//        SaveFile(content, file);

        Stage stage = new Stage();
        stage.setTitle("HTML Preview");
        stage.setWidth(600);
        stage.setHeight(600);
        Scene scene = new Scene(new Group());


        HBox root = new HBox();

        WebView browser = new WebView();
        WebEngine webEngine = browser.getEngine();

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(browser);
        webEngine.loadContent(toWebView);

        root.getChildren().addAll(scrollPane);
        scene.setRoot(root);

        stage.setScene(scene);
        stage.show();
    }
}
