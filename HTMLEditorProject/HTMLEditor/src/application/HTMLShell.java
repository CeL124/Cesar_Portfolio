package application;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.FileInputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.DOMReader;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
//import org.xml.sax.InputSource;
//import org.xml.sax.SAXException;
import org.cyberneko.html.parsers.DOMParser;//for html conversion
//import org.w3c.dom.Document;

import javafx.application.*;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.*;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TabPane.TabClosingPolicy;
import java.awt.Desktop;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.dom4j.Element;

//import javafx.event.*;
//import javafx.geometry.*;
//audio:
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class HTMLShell extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private Desktop desktop = Desktop.getDesktop();
    TreeItem<String> root, first;
    TreeView<String> tree;
    TextArea Editor;
    BorderPane leftBorderPane;
    Stage primaryStage;
    BorderPane MainBorderPane;
    GlobalAttrib globA;
    ArrayList<CheckBox> boxList = new ArrayList<>();

    //items for webview button
    BorderPane rightBorderPane;
    String toWebView;

    // serialize features
    String name;
    Map<String, String> projectMap = new HashMap<String, String>();
    loadMap projMap;
    String editText;

    @Override
    public void start(Stage primaryStage) {

        // make the main border pane
        MainBorderPane = new BorderPane();

        // create the top border pane
        BorderPane TopBorderPane = new BorderPane();
        // TopBorderPane.setPrefSize(100, 600);

        // Create and add Menu Bar features
        MenuBar MainMenu = new MenuBar();

        // add file menu features
        Menu File = new Menu("_File");
        Menu New = new Menu("_New Project");
        // add new project features

        MenuItem addproject = new MenuItem("Add a project");
        addproject.setOnAction(e -> loadproject());
        MenuItem getproj = new MenuItem("Get a project");
        getproj.setOnAction(e -> showproject());
        New.getItems().addAll(addproject, getproj);
        // open and open features

        Menu Open = new Menu("_Open");
        MenuItem Save = new MenuItem("_Save");
        Save.setOnAction(e -> saveDoc(Editor.getText()));
        // add and process for .html files

        MenuItem menuHTML = new MenuItem("Open_HTML(.html) file");
        menuHTML.setOnAction(e -> toHTMLTree());
        // Open.getItems().add(menuHTML);

        // add and process for .txt files
        MenuItem menuTxt = new MenuItem("Open _Text(.txt) file");
        menuTxt.setOnAction(e -> fileChooser());

        Open.getItems().addAll(menuHTML, menuTxt);
        File.getItems().addAll(New, Open, Save);

        // help menu, items, and events
        Menu Help = new Menu("_Help");
        MenuItem userG = new MenuItem("_User Guide");
        userG.setOnAction(e -> userGuide());
        MenuItem design = new MenuItem("_Design");
        design.setOnAction(e -> design());

        Help.getItems().addAll(userG, design);

        // about Menu
        Menu about = new Menu("About");
        MenuItem aboutH = new MenuItem("_About HTMLWorkbench");
        about.setOnAction(e -> some());
        aboutH.setOnAction(e -> new AboutWorkbook()); // AboutWorkbook.show());

        about.getItems().add(aboutH);

        MainMenu.getMenus().addAll(File, Help, about);

        TopBorderPane.setTop(MainMenu);

        /*
		 * //get file File inputFile = new
		 * File(getClass().getResource("input.txt").getFile()); SAXReader reader
		 * = new SAXReader(); Document document; try { document = reader.read(
		 * inputFile );
		 * 
		 * TreeWalk treewalk = new TreeWalk(document); TreeItem<String> whole
		 * =treewalk.getTree();
		 * 
		 * 
		 * 
		 * //Create tree root = new TreeItem<String>("<!DOCTYPE>");
		 * 
		 * 
		 * root.getChildren().add(whole);
		 * 
		 * tree = new TreeView<String>(whole); tree.setShowRoot(true); } catch
		 * (DocumentException e1) { // TODO Auto-generated catch block
		 * e1.printStackTrace(); }
         */
        // for selecting model can add delete and insert functions
        /*
		 * tree.getSelectionModel().selectedItemProperty() .addListener( (v,
		 * oldValue, newValue) -> tree_SelectionChange(newValue) );
         */
        root = new TreeItem<String>("<!DOCTYPE>");
        tree = new TreeView<String>(root);
        tree.setId("tree");

        // start new project feature serialize a file if not already done or
        // load .ser file
        projMap = null;
        try {
            FileInputStream fileIn = new FileInputStream("loadMap.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            projMap = (loadMap) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            System.out.println(
                    "Hello Welcome to HTML HACKER this must be your first time let us serialize some projects for you");
            // set up two projects and and create serfile
            try {
                projMap = new loadMap();
                FileOutputStream fileOut = new FileOutputStream("loadmap.ser");
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(projMap);
                out.close();
                fileOut.close();
                System.out.printf("Serialized data is saved in loadmap.ser");
            } catch (IOException i1) {
                i1.printStackTrace();
                System.out.println("some io issue");
            }

            i.printStackTrace();

            return;
        } catch (ClassNotFoundException c) {

            return;
        }

        // start tabs and buttons
        TabPane MainTab = new TabPane();
        
        // make the tabs not close on demand
        MainTab.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

        // start of basic tab
        Tab basic = new Tab("Basic");

        basic.setId("tab");

        ButtonFactory htmlFac = new ButtonFactory("<html>", "Basic");
        Button htmlTag = htmlFac.getPlain();
        htmlTag.setOnAction(e -> btnAdd1_Clicked(htmlTag));

        ButtonFactory titleF = new ButtonFactory("<title>", "Basic");
        Button titleTag = titleF.getPlain();
        titleTag.setOnAction(e -> btnAdd1_Clicked(titleTag));

        ButtonFactory bodyFac = new ButtonFactory("<body>", "Basic");
        Button bodyTag = bodyFac.getPlain();
        bodyTag.setOnAction(e -> btnAdd1_Clicked(bodyTag));

        ButtonFactory pFac = new ButtonFactory("<p>", "Basic");
        Button pTag = pFac.getPlain();
        pTag.setOnAction(e -> btnAdd1_Clicked(pTag));

        ButtonFactory brFac = new ButtonFactory("<br>", "Basic");
        Button brButton = brFac.getPlain();
        brButton.setOnAction(e -> btnAdd1_Clicked(brButton));

        ButtonFactory hrFac = new ButtonFactory("<hr>", "Basic");
        Button hrButton = hrFac.getPlain();
        hrButton.setOnAction(e -> btnAdd1_Clicked(hrButton));

        ButtonFactory h1Fac = new ButtonFactory("<h1>", "Basic");
        Button h1Button = h1Fac.getPlain();
        h1Button.setOnAction(e -> btnAdd1_Clicked(h1Button));

        ButtonFactory h2Fac = new ButtonFactory("<h2>", "Basic");
        Button h2Button = h2Fac.getPlain();
        h2Button.setOnAction(e -> btnAdd1_Clicked(h2Button));

        ButtonFactory h3Fac = new ButtonFactory("<h3>", "Basic");
        Button h3Button = h3Fac.getPlain();
        h3Button.setOnAction(e -> btnAdd1_Clicked(h3Button));

        ButtonFactory h4Fac = new ButtonFactory("<h4>", "Basic");
        Button h4Button = h4Fac.getPlain();
        h4Button.setOnAction(e -> btnAdd1_Clicked(h4Button));

        ButtonFactory h5Fac = new ButtonFactory("<h5>", "Basic");
        Button h5Button = h5Fac.getPlain();
        h5Button.setOnAction(e -> btnAdd1_Clicked(h5Button));

        ButtonFactory h6Fac = new ButtonFactory("<h6>", "Basic");
        Button h6Button = h6Fac.getPlain();
        h6Button.setOnAction(e -> btnAdd1_Clicked(h6Button));

        HBox basicBox = new HBox(htmlTag, titleTag, bodyTag, pTag, brButton, hrButton, h1Button, h2Button, h3Button,
                h4Button, h5Button, h6Button);

        basicBox.setMinHeight(50);
        basicBox.setMaxHeight(50);
        basicBox.setId("HBoxTab");
        basicBox.setAlignment(Pos.CENTER_LEFT);
        basic.setContent(basicBox);

        // end of basic tab
        // start formatting tab
        Tab formatting = new Tab("Formatting");
        formatting.setId("tab");

        ButtonFactory bFac = new ButtonFactory("<b>", "Formatting");
        Button bBtn = bFac.getPlain();
        bBtn.setOnAction(e -> btnAdd1_Clicked(bBtn));

        ButtonFactory abbrFac = new ButtonFactory("<abbr>", "Formatting");
        Button abbrBtn = abbrFac.getPlain();
        abbrBtn.setOnAction(e -> btnAdd1_Clicked(abbrBtn));

        ButtonFactory addressFac = new ButtonFactory("<address>", "Formatting");
        Button addressBtn = addressFac.getPlain();
        addressBtn.setOnAction(e -> btnAdd1_Clicked(addressBtn));

        ButtonFactory bdiFac = new ButtonFactory("<bdi>", "Formatting");
        Button bdiBtn = bdiFac.getPlain();
        bdiBtn.setOnAction(e -> btnAdd1_Clicked(bdiBtn));

        ButtonFactory bdoFac = new ButtonFactory("<bdo>", "Formatting");
        Button bdoBtn = bdoFac.getPlain();
        bdoBtn.setOnAction(e -> btnAdd1_Clicked(bdoBtn));

        ButtonFactory citeFac = new ButtonFactory("<cite>", "Formatting");
        Button citeBtn = citeFac.getPlain();
        citeBtn.setOnAction(e -> btnAdd1_Clicked(citeBtn));

        ButtonFactory delFac = new ButtonFactory("<del>", "Formatting", "cite datetime");
        SplitMenuButton delBtn = delFac.getButton();
        delBtn.setOnAction(e -> btnAdd_Clicked(delBtn));

        ButtonFactory codeFac = new ButtonFactory("<code>", "Formatting");
        Button codeBtn = codeFac.getPlain();
        codeBtn.setOnAction(e -> btnAdd1_Clicked(codeBtn));

        ButtonFactory prgssFac = new ButtonFactory("<progress>", "Formatting", "form max value");
        SplitMenuButton prgssBtn = prgssFac.getButton();
        prgssBtn.setOnAction(e -> btnAdd_Clicked(prgssBtn));

        ButtonFactory rpFac = new ButtonFactory("<rp>", "Formatting");
        Button rpBtn = rpFac.getPlain();
        rpBtn.setOnAction(e -> btnAdd1_Clicked(rpBtn));

        ButtonFactory sFac = new ButtonFactory("<s>", "Formatting");
        Button sBtn = sFac.getPlain();
        sBtn.setOnAction(e -> btnAdd1_Clicked(sBtn));

        ButtonFactory sampFac = new ButtonFactory("<samp>", "Formatting");
        Button sampBtn = sampFac.getPlain();
        sampBtn.setOnAction(e -> btnAdd1_Clicked(sampBtn));

        HBox formattingBox = new HBox(bBtn, abbrBtn, addressBtn, bdiBtn, bdoBtn, citeBtn, delBtn, codeBtn, prgssBtn,
                rpBtn, sBtn, sampBtn);
        formattingBox.setMinHeight(50);
        formattingBox.setMaxHeight(50);
        formattingBox.setId("HBoxTab");
        formattingBox.setAlignment(Pos.CENTER_LEFT);
        formatting.setContent(formattingBox);

        // end of formatting tab
        // did this to make attribute code for the split buttons look better
        String formTagAttrib = "accept accept_charset action autocomplete enctype method name novalidate target";

        String textareaTagAttrib = "autofocus cols dirname disabled form maxlength name wrap placeholder";

        String buttonTagAttrib = "autofocus disabled form formaction name type value";

        String selectTagAttrib = "autofocus disabled form multiple name";

        String keygenTagAttrib = "select challenge disabled form keytype name";

        String inputTagAttrib = "accept alt autocomplete autofocus autosave checked dirname disabled form"
                + " formaction height list max maxlength min multiple name pattern "
                + "placeholder src step type usemap value width";

        String iFrameTagAttrib, audioTagAttrib, videoTagAttrib, aTagAttrib, imgTagAttrib, mapTagAttrib;

        iFrameTagAttrib = "align height name src srcdoc width";
        audioTagAttrib = "autoplay buffered controls loop preload src";
        videoTagAttrib = "autoplay buffered controls height loop muted poster preload src width";
        aTagAttrib = "Download href hreflang media ping target";
        imgTagAttrib = "align alt height ismap src srcset usemap width";
        mapTagAttrib = "alt coords download media ping target";

        // starting forms and input tab
        Tab formsInput = new Tab("Forms/Input");
        formsInput.setId("tab");

        ButtonFactory formFac = new ButtonFactory("<form>", "Forms/Input", formTagAttrib);
        SplitMenuButton formBut = formFac.getButton();
        formBut.setOnAction(e -> btnAdd_Clicked(formBut));

        ButtonFactory inputFac = new ButtonFactory("<input>", "Forms/Input", inputTagAttrib);
        SplitMenuButton inputBut = inputFac.getButton();
        inputBut.setOnAction(e -> btnAdd_Clicked(inputBut));

        ButtonFactory txtFac = new ButtonFactory("<textarea>", "Forms/Input", textareaTagAttrib);
        SplitMenuButton txtBut = txtFac.getButton();
        txtBut.setOnAction(e -> btnAdd_Clicked(txtBut));

        ButtonFactory btnFac = new ButtonFactory("<button>", "Forms/Input", buttonTagAttrib);
        SplitMenuButton btnBut = btnFac.getButton();
        btnBut.setOnAction(e -> btnAdd_Clicked(btnBut));

        ButtonFactory selFac = new ButtonFactory("<select>", "Forms/Input", selectTagAttrib);
        SplitMenuButton selBut = selFac.getButton();
        selBut.setOnAction(e -> btnAdd_Clicked(selBut));

        ButtonFactory optGrpFac = new ButtonFactory("<optgroup>", "Forms/Input", "disabled");
        SplitMenuButton optGrpBut = optGrpFac.getButton();
        optGrpBut.setOnAction(e -> btnAdd_Clicked(optGrpBut));

        ButtonFactory optFac = new ButtonFactory("<option>", "Forms/Input", "disabled form name value");
        SplitMenuButton optBut = optFac.getButton();
        optBut.setOnAction(e -> btnAdd_Clicked(optBut));

        ButtonFactory lblFac = new ButtonFactory("<label>", "Forms/Input", "for formlabel");
        SplitMenuButton lblBut = lblFac.getButton();
        lblBut.setOnAction(e -> btnAdd_Clicked(lblBut));

        ButtonFactory fldSetFac = new ButtonFactory("<fieldset>", "Forms/Input", "disabled form name");
        SplitMenuButton fldSetBut = fldSetFac.getButton();
        fldSetBut.setOnAction(e -> btnAdd_Clicked(fldSetBut));

        ButtonFactory legendFac = new ButtonFactory("<legend>", "Forms/Input");
        Button legendBut = legendFac.getPlain();
        legendBut.setOnAction(e -> btnAdd1_Clicked(legendBut));

        ButtonFactory dListFac = new ButtonFactory("<datalist>", "Forms/Input");
        Button dListBut = dListFac.getPlain();
        dListBut.setOnAction(e -> btnAdd1_Clicked(dListBut));

        ButtonFactory keyGenFac = new ButtonFactory("<keygen>", "Forms/Input", keygenTagAttrib);
        SplitMenuButton keyGenBut = keyGenFac.getButton();
        keyGenBut.setOnAction(e -> btnAdd_Clicked(keyGenBut));

        HBox formInBox = new HBox(formBut, inputBut, txtBut, btnBut, selBut, optGrpBut, optBut, lblBut, fldSetBut,
                legendBut, dListBut, keyGenBut);
        formInBox.setMinHeight(50);
        formInBox.setMinHeight(50);
        formInBox.setId("HBoxTab");
        formInBox.setAlignment(Pos.CENTER_LEFT);
        formsInput.setContent(formInBox);

        // end of forms/input tab
        // start audio video tabs
        Tab audioVideo = new Tab("Audio/Video");
        audioVideo.setId("tab");

        ButtonFactory audioFac = new ButtonFactory("<audio>", "Audio/Video", audioTagAttrib);
        SplitMenuButton audBtn = audioFac.getButton();
        audBtn.setOnAction(e -> btnAdd_Clicked(audBtn));

        ButtonFactory srcFac = new ButtonFactory("<source>", "Audio/Video", "media src type");
        SplitMenuButton srcBtn = srcFac.getButton();
        srcBtn.setOnAction(e -> btnAdd_Clicked(srcBtn));

        ButtonFactory trkFac = new ButtonFactory("<track>", "Audio/Video", "default label src srclang");
        SplitMenuButton trkBtn = trkFac.getButton();
        trkBtn.setOnAction(e -> btnAdd_Clicked(trkBtn));

        ButtonFactory vidFac = new ButtonFactory("<video>", "Audio/Video", videoTagAttrib);
        SplitMenuButton vidBtn = vidFac.getButton();
        vidBtn.setOnAction(e -> btnAdd_Clicked(vidBtn));

        ButtonFactory imgFac = new ButtonFactory("<img>", "Audio/Video", imgTagAttrib);
        SplitMenuButton imgBtn = imgFac.getButton();
        imgBtn.setOnAction(e -> btnAdd_Clicked(imgBtn));

        ButtonFactory mapFac = new ButtonFactory("<map>", "Audio/Video", "name");
        SplitMenuButton mapBtn = mapFac.getButton();
        mapBtn.setOnAction(e -> btnAdd_Clicked(mapBtn));

        ButtonFactory areaFac = new ButtonFactory("<area>", "Audio/Video", mapTagAttrib);
        SplitMenuButton areaBtn = areaFac.getButton();
        areaBtn.setOnAction(e -> btnAdd_Clicked(areaBtn));

        ButtonFactory canvFac = new ButtonFactory("<canvas>", "Audio/Video", "height width");
        SplitMenuButton canvBtn = canvFac.getButton();
        canvBtn.setOnAction(e -> btnAdd_Clicked(canvBtn));

        ButtonFactory mtrFac = new ButtonFactory("<meter>", "Audio/Video", "high low max min optimum value form");
        SplitMenuButton mtrBtn = mtrFac.getButton();
        mtrBtn.setOnAction(e -> btnAdd_Clicked(mtrBtn));

        ButtonFactory preFac = new ButtonFactory("<pre>", "Audio/Video");
        Button preBtn = preFac.getPlain();
        preBtn.setOnAction(e -> btnAdd1_Clicked(preBtn));

        ButtonFactory iFac = new ButtonFactory("<i>", "Audio/Video");
        Button iBtn = iFac.getPlain();
        iBtn.setOnAction(e -> btnAdd1_Clicked(iBtn));

        ButtonFactory insFac = new ButtonFactory("<ins>", "Audio/Video", "cite datetime");
        SplitMenuButton insBtn = insFac.getButton();
        insBtn.setOnAction(e -> btnAdd_Clicked(insBtn));

        HBox audVidBox = new HBox(audBtn, srcBtn, trkBtn, vidBtn, imgBtn, mapBtn, areaBtn, canvBtn, mtrBtn, preBtn,
                iBtn, insBtn);
        audVidBox.setMinHeight(50);
        audVidBox.setMinHeight(50);
        audVidBox.setId("HBoxTab");
        audVidBox.setAlignment(Pos.CENTER_LEFT);
        audioVideo.setContent(audVidBox);
        // end of Audio-Video tab

        // start lists tab
        Tab lists = new Tab("Lists");
        lists.setId("tab");

        ButtonFactory ulFac = new ButtonFactory("<ul>", "Lists", "type");
        SplitMenuButton ulBtn = ulFac.getButton();
        ulBtn.setOnAction(e -> btnAdd_Clicked(ulBtn));

        ButtonFactory olFac = new ButtonFactory("<ol>", "Lists", "start");
        SplitMenuButton olBtn = olFac.getButton();
        olBtn.setOnAction(e -> btnAdd_Clicked(olBtn));

        ButtonFactory liFac = new ButtonFactory("<li>", "Lists", "value");
        SplitMenuButton liBtn = liFac.getButton();
        liBtn.setOnAction(e -> btnAdd_Clicked(liBtn));

        ButtonFactory dlFac = new ButtonFactory("<dl>", "Lists");
        Button dlBtn = dlFac.getPlain();
        dlBtn.setOnAction(e -> btnAdd1_Clicked(dlBtn));

        ButtonFactory dtFac = new ButtonFactory("<dt>", "Lists");
        Button dtBtn = dtFac.getPlain();
        dtBtn.setOnAction(e -> btnAdd1_Clicked(dtBtn));

        ButtonFactory ddFac = new ButtonFactory("<dd>", "Lists");
        Button ddBtn = ddFac.getPlain();
        ddBtn.setOnAction(e -> btnAdd1_Clicked(ddBtn));

        ButtonFactory tblFac = new ButtonFactory("<table>", "Lists", "align summary");
        SplitMenuButton tblBtn = tblFac.getButton();
        tblBtn.setOnAction(e -> btnAdd_Clicked(tblBtn));

        ButtonFactory cptnFac = new ButtonFactory("<caption>", "Lists", "align");
        SplitMenuButton cptnBtn = cptnFac.getButton();
        cptnBtn.setOnAction(e -> btnAdd_Clicked(cptnBtn));

        ButtonFactory clGrpFac = new ButtonFactory("<colgroup>", "Lists", "align");
        SplitMenuButton clGrpBtn = clGrpFac.getButton();
        clGrpBtn.setOnAction(e -> btnAdd_Clicked(clGrpBtn));

        ButtonFactory colFac = new ButtonFactory("<col>", "Lists", "align");
        SplitMenuButton colBtn = colFac.getButton();
        colBtn.setOnAction(e -> btnAdd_Clicked(colBtn));

        ButtonFactory aFac = new ButtonFactory("<a>", "Lists", aTagAttrib);
        SplitMenuButton aBtn = aFac.getButton();
        aBtn.setOnAction(e -> btnAdd_Clicked(aBtn));

        ButtonFactory linkFac = new ButtonFactory("<link>", "Lists", "href hreflang media");
        SplitMenuButton linkBtn = linkFac.getButton();
        linkBtn.setOnAction(e -> btnAdd_Clicked(linkBtn));

        HBox listsBox = new HBox(ulBtn, olBtn, liBtn, dlBtn, dtBtn, ddBtn, tblBtn, cptnBtn, clGrpBtn, colBtn, aBtn,
                linkBtn);
        listsBox.setMinHeight(50);
        listsBox.setMinHeight(50);
        listsBox.setAlignment(Pos.CENTER_LEFT);
        listsBox.setId("HBoxTab");
        lists.setContent(listsBox);

        // end of lists tab.
        // start styles tab.
        Tab styleTab = new Tab("Styles");
        styleTab.setId("tab");

        ButtonFactory styleF = new ButtonFactory("<style>", "Styles", "media scoped type");
        SplitMenuButton styleBtn = styleF.getButton();
        styleBtn.setOnAction(e -> btnAdd_Clicked(styleBtn));

        ButtonFactory divFac = new ButtonFactory("<div>", "Styles");
        Button divBtn = divFac.getPlain();
        divBtn.setOnAction(e -> btnAdd1_Clicked(divBtn));

        ButtonFactory spanFac = new ButtonFactory("<span>", "Styles");
        Button spanBtn = spanFac.getPlain();
        spanBtn.setOnAction(e -> btnAdd1_Clicked(spanBtn));

        ButtonFactory headerFac = new ButtonFactory("<header>", "Styles");
        Button headerBtn = headerFac.getPlain();
        headerBtn.setOnAction(e -> btnAdd1_Clicked(headerBtn));

        ButtonFactory footerFac = new ButtonFactory("<footer>", "Styles");
        Button footerBtn = footerFac.getPlain();
        footerBtn.setOnAction(e -> btnAdd1_Clicked(footerBtn));

        ButtonFactory mainFac = new ButtonFactory("<main>", "Styles");
        Button mainBtn = mainFac.getPlain();
        mainBtn.setOnAction(e -> btnAdd1_Clicked(mainBtn));

        ButtonFactory sectFac = new ButtonFactory("<section>", "Styles");
        Button sectBtn = sectFac.getPlain();
        sectBtn.setOnAction(e -> btnAdd1_Clicked(sectBtn));

        ButtonFactory artFac = new ButtonFactory("<article>", "Styles");
        Button artBtn = artFac.getPlain();
        artBtn.setOnAction(e -> btnAdd1_Clicked(artBtn));

        ButtonFactory asideFac = new ButtonFactory("<aside>", "Styles");
        Button asideBtn = asideFac.getPlain();
        asideBtn.setOnAction(e -> btnAdd1_Clicked(asideBtn));

        ButtonFactory detFac = new ButtonFactory("<details>", "Styles", "open");
        SplitMenuButton detBtn = detFac.getButton();
        detBtn.setOnAction(e -> btnAdd_Clicked(detBtn));

        ButtonFactory diaFac = new ButtonFactory("<dialog>", "Styles");
        Button diaBtn = diaFac.getPlain();
        diaBtn.setOnAction(e -> btnAdd1_Clicked(diaBtn));

        ButtonFactory summFac = new ButtonFactory("<summary>", "Styles");
        Button summBtn = summFac.getPlain();
        summBtn.setOnAction(e -> btnAdd1_Clicked(summBtn));

        HBox stylesBox = new HBox(styleBtn, divBtn, spanBtn, headerBtn, footerBtn, mainBtn, sectBtn, artBtn, asideBtn,
                detBtn, diaBtn, summBtn);
        stylesBox.setMinHeight(50);
        stylesBox.setMinHeight(50);
        stylesBox.setAlignment(Pos.CENTER_LEFT);
        stylesBox.setId("HBoxTab");
        styleTab.setContent(stylesBox);

        // end styles Tab
        // Start Meta/Programming Tag
        Tab metPrgmTab = new Tab("Meta/Programming");
        styleTab.setId("tab");

        ButtonFactory headFac = new ButtonFactory("<head>", "Meta/Programming");
        Button headBtn = headFac.getPlain();
        headBtn.setOnAction(e -> btnAdd1_Clicked(headBtn));

        ButtonFactory metaFac = new ButtonFactory("<meta>", "Meta/Programming", "charset content http name");
        SplitMenuButton metaBtn = metaFac.getButton();
        metaBtn.setOnAction(e -> btnAdd_Clicked(metaBtn));

        ButtonFactory baseFac = new ButtonFactory("<base>", "Meta/Programming", "Href target");
        SplitMenuButton baseBtn = baseFac.getButton();
        baseBtn.setOnAction(e -> btnAdd_Clicked(baseBtn));

        ButtonFactory scriptFac = new ButtonFactory("<script>", "Meta/Programming",
                "async charset defer language src type");
        SplitMenuButton scriptBtn = scriptFac.getButton();
        scriptBtn.setOnAction(e -> btnAdd_Clicked(scriptBtn));

        ButtonFactory noScriptFac = new ButtonFactory("<noscript>", "Meta/Programming");
        Button noScriptBtn = noScriptFac.getPlain();
        noScriptBtn.setOnAction(e -> btnAdd1_Clicked(noScriptBtn));

        ButtonFactory embedFac = new ButtonFactory("<embed>", "Meta/Programming", "height src type width");
        SplitMenuButton embedBtn = embedFac.getButton();
        embedBtn.setOnAction(e -> btnAdd_Clicked(embedBtn));

        ButtonFactory objFac = new ButtonFactory("<object>", "Meta/Programming",
                "data form height name type usemap width");
        SplitMenuButton objBtn = objFac.getButton();
        objBtn.setOnAction(e -> btnAdd_Clicked(objBtn));

        ButtonFactory paramFac = new ButtonFactory("<param>", "Meta/Programming", "name value");
        SplitMenuButton paramBtn = paramFac.getButton();
        paramBtn.setOnAction(e -> btnAdd_Clicked(paramBtn));

        ButtonFactory rubFac = new ButtonFactory("<ruby>", "Meta/Programming");
        Button rubBtn = rubFac.getPlain();
        rubBtn.setOnAction(e -> btnAdd1_Clicked(rubBtn));

        ButtonFactory wbrFac = new ButtonFactory("<wbr>", "Meta/Programming");
        Button wbrBtn = wbrFac.getPlain();
        wbrBtn.setOnAction(e -> btnAdd1_Clicked(wbrBtn));

        ButtonFactory subFac = new ButtonFactory("<sub>", "Meta/Programming");
        Button subBtn = subFac.getPlain();
        subBtn.setOnAction(e -> btnAdd1_Clicked(subBtn));

        ButtonFactory supFac = new ButtonFactory("<sup>", "Meta/Programming");
        Button supBtn = supFac.getPlain();
        supBtn.setOnAction(e -> btnAdd1_Clicked(supBtn));

        HBox metProgBox = new HBox(headBtn, metaBtn, baseBtn, scriptBtn, noScriptBtn, embedBtn, objBtn, paramBtn,
                wbrBtn, subBtn, supBtn);
        metProgBox.setMinHeight(50);
        metProgBox.setMinHeight(50);
        metProgBox.setId("HBoxTab");
        metProgBox.setAlignment(Pos.CENTER_LEFT);
        metPrgmTab.setContent(metProgBox);
        // end Meta Programming box

        // start Miscellaneous tab
        Tab miscTab = new Tab("Misc Tabs");
        miscTab.setId("tab");

        ButtonFactory dfnFac = new ButtonFactory("<dfn>", "Misc Tabs");
        Button dfnButton = dfnFac.getPlain();
        dfnButton.setOnAction(e -> btnAdd1_Clicked(dfnButton));

        ButtonFactory markFac = new ButtonFactory("<mark>", "Misc Tabs");
        Button markButton = markFac.getPlain();
        markButton.setOnAction(e -> btnAdd1_Clicked(markButton));

        ButtonFactory smallFac = new ButtonFactory("<small>", "Misc Tabs");
        Button smallBtn = smallFac.getPlain();
        smallBtn.setOnAction(e -> btnAdd1_Clicked(smallBtn));

        ButtonFactory timeFac = new ButtonFactory("<time>", "Misc Tabs", "datetime");
        SplitMenuButton timeBtn = timeFac.getButton();
        timeBtn.setOnAction(e -> btnAdd_Clicked(timeBtn));

        ButtonFactory outPutFac = new ButtonFactory("<output>", "Misc Tabs", "for form name");
        SplitMenuButton outPutBut = outPutFac.getButton();
        outPutBut.setOnAction(e -> btnAdd_Clicked(outPutBut));

        ButtonFactory iFrameFac = new ButtonFactory("<iframe>", "Misc Tabs", iFrameTagAttrib);
        SplitMenuButton iframeBut = iFrameFac.getButton();
        iframeBut.setOnAction(e -> btnAdd_Clicked(iframeBut));

        ButtonFactory uFac = new ButtonFactory("<u>", "Misc Tabs");
        Button uBtn = uFac.getPlain();
        uBtn.setOnAction(e -> btnAdd1_Clicked(uBtn));

        ButtonFactory navFac = new ButtonFactory("<nav>", "Misc Tabs");
        Button navBtn = navFac.getPlain();
        navBtn.setOnAction(e -> btnAdd1_Clicked(navBtn));

        ButtonFactory bQuoteFac = new ButtonFactory("<blockquote>", "Misc Tabs", "cite");
        SplitMenuButton bQuoteBtn = bQuoteFac.getButton();
        bQuoteBtn.setOnAction(e -> btnAdd_Clicked(bQuoteBtn));

        ButtonFactory qFac = new ButtonFactory("<q>", "Misc Tabs", "cite");
        SplitMenuButton qBtn = qFac.getButton();
        qBtn.setOnAction(e -> btnAdd_Clicked(qBtn));

        ButtonFactory kbdFac = new ButtonFactory("<kbd>", "Misc Tabs");
        Button kbdBtn = kbdFac.getPlain();
        kbdBtn.setOnAction(e -> btnAdd1_Clicked(kbdBtn));

        HBox miscBox = new HBox(dfnButton, markButton, smallBtn, timeBtn, outPutBut, iframeBut, uBtn, navBtn, bQuoteBtn,
                qBtn, kbdBtn);

        miscBox.setMinHeight(50);
        miscBox.setMinHeight(50);
        miscBox.setAlignment(Pos.CENTER_LEFT);
        miscBox.setId("HBoxTab");
        miscTab.setContent(miscBox);

        // END table tab
        
        Label labelSpace = new Label();
        Label labelSpace2 = new Label();
        Label labelSpace3 = new Label();
        
        MainTab.getTabs().addAll(basic, formatting, formsInput, audioVideo, lists, styleTab, metPrgmTab,
                miscTab/*
						 * HTMLStructureTab, mainList, hBtnTab, common, uncommon,
						 * forms, cool, tables
         */);
    
        HBox tabBox = new HBox();
        tabBox.getChildren().addAll(labelSpace, labelSpace2, labelSpace3, MainTab);
        tabBox.setMaxWidth(3600);
        TopBorderPane.setCenter(tabBox);
        
 

        HBox footerBox = new HBox();
        footerBox.setId("footer");
        //preview button to show web output
        Button htmlView = new Button("WebView");
        HTMLView render = new HTMLView();
        htmlView.setOnAction(e -> render.renderHTML(Editor.getText()));
        footerBox.getChildren().add(htmlView);
        footerBox.setAlignment(Pos.CENTER_RIGHT);
        ToggleButton globalButton = new ToggleButton("Show Globals");
        footerBox.getChildren().add(globalButton);
        footerBox.setAlignment(Pos.CENTER_RIGHT);

        // Toggle Button Stuff
        // Image image = new
        // Image(getClass().getResourceAsStream("onToggle.png"));
        VBox toggleBox = new VBox(4);
        // ToggleButton onToggle = new ToggleButton("ON"/*, new
        // ImageView(image)*/);
        BorderSlideBar rightFlapBar = new BorderSlideBar(200, globalButton, Pos.BASELINE_RIGHT, toggleBox);

        Label globLabel = new Label("GLOBAL ATTRIBUTES");

        ButtonFactory accesskeyFac = new ButtonFactory("accesskey");
        CheckBox accesskeyAt = accesskeyFac.getGlobals();
        boxList.add(accesskeyAt);

        ButtonFactory contenteditableFac = new ButtonFactory("contenteditable");
        CheckBox contenteditableAt = contenteditableFac.getGlobals();
        boxList.add(contenteditableAt);

        ButtonFactory classFac = new ButtonFactory("class");
        CheckBox classAt = classFac.getGlobals();
        boxList.add(classAt);

        ButtonFactory colspanfac = new ButtonFactory("colspan");
        CheckBox colspanAt = colspanfac.getGlobals();
        boxList.add(colspanAt);

        ButtonFactory dirFac = new ButtonFactory("dir");
        CheckBox dirAt = dirFac.getGlobals();
        boxList.add(dirAt);

        ButtonFactory draggablefac = new ButtonFactory("draggable");
        CheckBox draggableAt = draggablefac.getGlobals();
        boxList.add(draggableAt);

        ButtonFactory dropzoneFac = new ButtonFactory("dropzone");
        CheckBox dropzoneAt = dropzoneFac.getGlobals();
        boxList.add(dropzoneAt);

        ButtonFactory hiddenFac = new ButtonFactory("hidden");
        CheckBox hiddenAt = hiddenFac.getGlobals();
        boxList.add(hiddenAt);

        ButtonFactory idFac = new ButtonFactory("id");
        CheckBox idAt = idFac.getGlobals();
        boxList.add(idAt);

        ButtonFactory langFac = new ButtonFactory("lang");
        CheckBox langAt = langFac.getGlobals();
        boxList.add(langAt);

        ButtonFactory spellcheckFac = new ButtonFactory("spellcheck");
        CheckBox spellcheckAt = spellcheckFac.getGlobals();
        boxList.add(spellcheckAt);

        ButtonFactory styleFac = new ButtonFactory("style");
        CheckBox styleAt = styleFac.getGlobals();
        boxList.add(styleAt);

        ButtonFactory tabIndexFac = new ButtonFactory("tabindex");
        CheckBox tabIndexAt = tabIndexFac.getGlobals();
        boxList.add(tabIndexAt);

        ButtonFactory titleFac = new ButtonFactory("title");
        CheckBox titleAt = titleFac.getGlobals();
        boxList.add(titleAt);

        ButtonFactory translateFac = new ButtonFactory("translate");
        CheckBox translateAt = translateFac.getGlobals();
        boxList.add(translateAt);

        toggleBox.getChildren().addAll(globLabel, accesskeyAt, contenteditableAt, classAt, colspanAt, dirAt,
                draggableAt, dropzoneAt, hiddenAt, idAt, langAt, spellcheckAt, styleAt, tabIndexAt, titleAt,
                translateAt);

        // textArea
        // Editor = new TextArea();
        Editor = new TextArea();
        Editor.setWrapText(true);
        Editor.setPrefWidth(100);
        Editor.setPrefHeight(100);

        SplitPane splitPane = new SplitPane();
        // splitPane.getItems().addAll(Editor, globPane);
        splitPane.setDividerPositions(0.6f, 0.0f);
        // splitPane.setOn
        // Footer
        //Label Footer = new Label();

        // left textbox, label, and button for web search
        leftBorderPane = new BorderPane();

        TextField hey = new TextField();
        hey.setText("http://www.");
        Button search = new Button("m");
        search.setOnAction(e -> webSearch(hey.getText()));

        Label web = new Label("Enter URL:");
        HBox searchpanel = new HBox(web, hey, search);

        leftBorderPane.setBottom(searchpanel);
        leftBorderPane.setCenter(tree);




        // put the top pane into the main border pane
        MainBorderPane.setTop(TopBorderPane);
        MainBorderPane.setLeft(leftBorderPane);

        MainBorderPane.setBottom(footerBox);
        MainBorderPane.setCenter(Editor);
        MainBorderPane.setMinHeight(500);
        MainBorderPane.setMaxHeight(500);
        MainBorderPane.setRight(rightFlapBar);

        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        primaryStage.setX(primaryScreenBounds.getMinX());
        primaryStage.setY(primaryScreenBounds.getMinY());
        primaryStage.setWidth(primaryScreenBounds.getWidth());
        primaryStage.setHeight(primaryScreenBounds.getHeight());

        primaryStage.setOnCloseRequest(e -> {
            e.consume();
            Boolean answer = ConfirmBox.displayConfirmBox("Window closing alert", "All unsaved work will be lost. Are you sure you want to close?");
            if (answer) {
                primaryStage.close();
            }
        });

        Scene scene = new Scene(MainBorderPane, 1100, 600);
        scene.getStylesheets().add(getClass().getResource("HTMLdesign.css").toExternalForm());

        primaryStage.setScene(scene);

        primaryStage.setTitle("HTML EDITOR");
        primaryStage.show();

    }

    /*
	 * public void tree_SelectionChange(TreeItem<String> item){ if(item !=
	 * null){ root.setText(item.getValue()); } }
     */
    // need an implements class.
    public void btnAdd_Clicked(SplitMenuButton button) {

        String buttonFirst = button.getText();
        String attributes = "";
        // send text to tree here

        ObservableList<MenuItem> item = button.getItems();
        for (MenuItem checkMenuItem : item) {
            if (((CheckMenuItem) checkMenuItem).isSelected()) // System.out.println(checkMenuItem.getText());
            {
                attributes += " " + checkMenuItem.getText() + "=\" \"";
            }
        }

        for (int i = 0; i < boxList.size(); i++) {
            if (boxList.get(i).isSelected()) {
                attributes += " " + boxList.get(i).getText() + "=\" \"";

            }
        }

        // or here if you want attributes added to tree
        String buttonLast = new String(buttonFirst);
        buttonFirst = new StringBuffer(buttonFirst).insert(buttonFirst.length() - 1, attributes).toString();

        buttonLast = new StringBuffer(buttonLast).insert(1, "/").toString();

        // int x =Editor.getCaretPosition();
        Editor.insertText(Editor.getCaretPosition(), "\n" + buttonFirst + " \n" + buttonLast /* + getglobals */);
        // send to parser to check for last > then read to < make tree

    }

    public void btnAdd1_Clicked(Button button) {
        String buttonFirst = button.getText();
        String buttonLast = new String(buttonFirst);

        String globals = "";

        for (int i = 0; i < boxList.size(); i++) {
            if (boxList.get(i).isSelected()) {
                globals += " " + boxList.get(i).getText() + "=\" \"";

            }
        }

        buttonFirst = new StringBuffer(buttonFirst).insert(buttonFirst.length() - 1, globals).toString();
        buttonLast = new StringBuffer(buttonLast).insert(1, "/").toString();

        Editor.insertText(Editor.getCaretPosition(), "\n" + buttonFirst + " \n\n" + buttonLast);
        // send to parser to check for last > then read to < make tree

    }

    public void fileChooser() {
        // private Desktop desktop = Desktop.getDesktop();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open HTML or File");
        File file = fileChooser.showOpenDialog(primaryStage);
        // fileChooser.showOpenDialog(primaryStage);
        if (file != null) {

            // send file to editor here
            toEditor(file);
            toTree(file);

            // check what the file extension is
            // String ext1 =
            // FilenameUtils.getExtension("/path/to/file/foo.txt");
            // send document to XPATHparser method
            /*
			 * SAXReader reader = new SAXReader(); Document document; try {
			 * document = reader.read( file );
			 * 
			 * 
			 * 
			 * TreeWalk treewalk = new TreeWalk(document); TreeItem<String>
			 * whole =treewalk.getTree();
			 * 
			 * tree = new TreeView<String>(whole); tree.setShowRoot(true);
			 * leftBorderPane.setCenter(tree); } catch (DocumentException e1) {
			 * // TODO Auto-generated catch block e1.printStackTrace();
             */
        }
        // for selecting model can add delete and insert functions
        /*
		 * tree.getSelectionModel().selectedItemProperty() .addListener( (v,
		 * oldValue, newValue) -> tree_SelectionChange(newValue) );
         */
    }// end if

    public void toTree(File file) {
        SAXReader reader = new SAXReader();
        Document document;
        try {
            document = reader.read(file);

            TreeWalk treewalk = new TreeWalk(document);
            TreeItem<String> whole = treewalk.getTree();

            tree = new TreeView<String>(whole);
            tree.setShowRoot(true);
            leftBorderPane.setCenter(tree);
        } catch (DocumentException e1) {
            System.out.println("text doc exception");
            e1.printStackTrace();
        }
        // for selecting model can add delete and insert functions
        /*
		 * tree.getSelectionModel().selectedItemProperty() .addListener( (v,
		 * oldValue, newValue) -> tree_SelectionChange(newValue) );
         */
    }// end file chooser

    public void toHTMLTree() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open HTML");
        File file = fileChooser.showOpenDialog(primaryStage);
        // fileChooser.showOpenDialog(primaryStage);
        if (file != null) {

            DOMParser parser = new DOMParser();
            try {
                parser.parse(new InputSource("file:///" + file.toString()));
                org.w3c.dom.Document doc = parser.getDocument();
                DOMReader reader = new DOMReader();
                Document document = reader.read(doc);
                toTreeURL(document);
                documentToEditor(document);
            } catch (SAXException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }

    public void toTreeURL(Document doc) {

        System.out.println("did it get here");
        TreeWalk treewalk = new TreeWalk(doc);
        TreeItem<String> whole = treewalk.getTree();
        tree = new TreeView<String>(whole);
        tree.setShowRoot(true);
        leftBorderPane.setCenter(tree);

    }// end treewalk

    public void toEditor(File file) {
        // cleareditor here---
        Editor.setText("");
        // BufferedReader br=null;
        Scanner s;

        try {
            s = new Scanner(file).useDelimiter(">  ");
            while (s.hasNext()) {
                if (s.hasNextInt()) { // check if next token is an int
                    Editor.appendText(s.nextInt() + " "); // display the found
                    // integer
                } else {
                    Editor.appendText(s.next() + " "); // else read the next
                    // token
                }
            }
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        }
    }// end toEditor

    /*
	 * try {
	 * 
	 * String sCurrentLine; //br = new BufferedReader(new FileReader(file)); br=
	 * new Scanner(new FileReader(file)).useDelimiter("\\s+"); while
	 * ((sCurrentLine = br.readLine()) != null) {
	 * Editor.appendText(sCurrentLine); System.out.println(sCurrentLine); }
	 * 
	 * } catch (IOException e) { e.printStackTrace(); } finally { try { if (br
	 * != null)br.close(); } catch (IOException ex) { ex.printStackTrace(); } }
     */

    public void userGuide() {
        if (Desktop.isDesktopSupported()) {
            try {
                ClassLoader classLoader = getClass().getClassLoader();
                File myFile = new File(classLoader.getResource("application/userGuide.pdf").getFile());
                Desktop.getDesktop().open(myFile);
            } catch (IOException ex) {
                System.out.println("cannot open pdf");
            }
        }
    }

    public void design() {
        if (Desktop.isDesktopSupported()) {
            try {
                ClassLoader classLoader = getClass().getClassLoader();
                File myFile = new File(classLoader.getResource("application/design.pdf").getFile());
                Desktop.getDesktop().open(myFile);
            } catch (IOException ex) {
                System.out.println("cannot open pdf");
            }
        }
    }

    public void webSearch(String web) {
        /*
		 * URL domain = null;
		 * 
		 * try { domain = new URL(web); } catch (MalformedURLException e) {
		 * System.out.println("not a url make a popup lazy!");
		 * e.printStackTrace();
         */

        DOMParser parser = new DOMParser();
        try {
            parser.parse(web);

            org.w3c.dom.Document doc = parser.getDocument();

            DOMReader reader = new DOMReader();
            Document document = reader.read(doc);

            toTreeURL(document);
            documentToEditor(document);

        } catch (SAXException e1) {
            System.out.println("fail at URL document SAX parser");
            e1.printStackTrace();
        } catch (IOException e1) {
            System.out.println("fail at URL document IO");
            e1.printStackTrace();
        }
    }

    /*
	 * straight java BufferedReader in; try { in = new BufferedReader(new
	 * InputStreamReader(domain.openStream()));
	 * 
	 * String inputLine; while ((inputLine = in.readLine()) != null)
	 * System.out.println(inputLine); in.close(); } catch (IOException e) { //
	 * TODO Auto-generated catch block e.printStackTrace(); }
     */
    // System.out.println(domain);
    // element = document.getRootElement();
    // toTreeURL(document);
    public void documentToEditor(Document document) {
        Editor.setText("");
        String text = document.asXML();
        Editor.setText(text);
    }

    public void saveDoc(String editorText) {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter txtFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        FileChooser.ExtensionFilter htmlFilter = new FileChooser.ExtensionFilter("HTML files (*.html)", ".html");

        fileChooser.getExtensionFilters().addAll(txtFilter, htmlFilter);
        File file = fileChooser.showSaveDialog(primaryStage);
        SaveFile(editorText, file);
    }

    private void SaveFile(String content, File file) {
        try {
            FileWriter fileWriter = null;

            fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException ex) {
            // put this error on all exceptions.
            Logger.getLogger(HTMLShell.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void some() {
        AboutWorkbook hey = new AboutWorkbook();
        hey.getSome();
    }

    // load project methods
    private void loadproject() {
        TextInputDialog dialog = new TextInputDialog("Enter Project Name");
        dialog.setTitle("Text Input Dialog");
        dialog.setHeaderText("Enter a name for project");
        dialog.setContentText("Please enter project name:");

        Optional<String> result = dialog.showAndWait();

        name = result.get();
        // check if name exist in hashmap if it does exit and error.

        editText = Editor.getText();
        projMap.addProj(name, editText);
        try {
            // projMap = new loadMap();
            FileOutputStream fileOut = new FileOutputStream("loadmap.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(projMap);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in loadmap.ser");
        } catch (IOException i1) {
            i1.printStackTrace();
            System.out.print("any prob sir?");
        }

        /*
		 * try { writeObjectsToFile(filename, newproject); } catch (IOException
		 * e) { // TODO Auto-generated catch block e.printStackTrace(); } //List
		 * objects = readObjectsFromFile(filename);
         */
    }

    private void showproject() {
        // GetProjectStage.ProjectStage(projMap);
        GetProjectStage newproj = new GetProjectStage();// load in projects
        String Proj = newproj.ProjectStage(projMap);
        // String selection = newproj.getString();
        // Optional<String> result = newproj.getString();
        if (Proj == "refresh!&@") {
            showproject();
        }

        if (Proj != "!&@" && Proj != "refresh!&@") {
            Editor.setText(Proj);
        }

        // Editor.setText(""+ projMap.getProj(selection));
        // String proj = newproj.getinfo();
        // System.out.println(proj);
    }

}
