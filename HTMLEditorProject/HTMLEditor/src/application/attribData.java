package application;

import java.util.HashMap;
import java.util.Map;

public class attribData {
	
	private Map<String, String> Data = new HashMap<String, String>();
	
	
	public attribData(){
		
		Data.put("" +Atrib.ACCEPT, "List of types the server accepts, typically a file type.");
		Data.put("" +Atrib.ACCEPT_CHARSET, "List of supported charsets.");
		Data.put("" +Atrib.ACCESSKEY, "	Defines a keyboard shortcut to activate or add focus to the element.");
		Data.put("" +Atrib.ACTION, "The URI of a program that processes the information submitted via the form.");
		Data.put("" +Atrib.ALIGN, "Specifies the horizontal alignment of the element.");
		Data.put("" +Atrib.ALT, "Alternative text in case an image can't be displayed.");
		Data.put("" +Atrib.ASYNC, "Indicates that the script should be executed asynchronously.");
		Data.put("" +Atrib.AUTOCOMPLETE, "Indicates whether controls in this form can by default have their values automatically completed by the browser.");
		Data.put("" +Atrib.AUTOFOCUS, "The element should be automatically focused after the page loaded.");
		Data.put("" +Atrib.AUTOPLAY, "The audio or video should play as soon as possible.");
		Data.put("" +Atrib.AUTOSAVE, "Previous values should persist dropdowns of selectable values across page loads.");
		Data.put("" +Atrib.BUFFERED, "	Contains the time range of already buffered media.");
		Data.put("" +Atrib.CHALLENGE,"A challenge string that is submitted along with the public key.");
		Data.put("" +Atrib.CHARSET,"Declares the character encoding of the page or script.");
		Data.put("" +Atrib.CHECKED,	"Indicates whether the element should be checked on page load.");	
		Data.put("" +Atrib.CITE, "	Contains a URI which points to the source of the quote or change.");
		
		Data.put("" +Atrib.CLASS, "Often used with CSS to style elements with common properties.");//GLOBAL
		Data.put("" +Atrib.COLS, "Defines the number of columns in a textarea.");
		Data.put("" +Atrib.COLSPAN, "The colspan attribute defines the number of columns a cell should span.");
		Data.put("" +Atrib.CONTENT, "A value associated with http-equiv or name depending on the context.");
		Data.put("" +Atrib.CONTENTEDITABLE,"Indicates whether the element's content is editable");//GLOBAL
		Data.put("" +Atrib.CONTEXTMENU,"Defines the ID of a <menu> element which will serve as the element's context menu.");//GLOBAL
		Data.put("" +Atrib.CONTROLS,"Indicates whether the browser should show playback controls to the user.");
		Data.put("" +Atrib.COORDS,"A set of values specifying the coordinates of the hot-spot region.");
		Data.put("" +Atrib.DATA,"Specifies the URL of the resource.");
		Data.put("" +Atrib.DATA_,"Lets you attach custom attributes to an HTML element.");//GLOBAL_NEED TO FIGURE OUT HOW TO IGNORE * FOR FULL TYPE DATA_*
		Data.put("" +Atrib.DATETIME,"Indicates the date and time associated with the element.");
		Data.put("" +Atrib.DEFAULT,"Indicates that the track should be enabled unless the user's preferences indicate something different.");
		Data.put("" +Atrib.DEFER,"Indicates that the script should be executed after the page has been parsed.");
		Data.put("" +Atrib.DIR,"Defines the text direction. Allowed values are ltr (Left-To-Right) or rtl (Right-To-Left)");//GLOBAL
		Data.put("" +Atrib.DIRNAME,"");
		Data.put("" +Atrib.DISABLED, "Indicates whether the user can interact with the element.");
		Data.put("" +Atrib.DOWNLOAD, "Indicates that the hyperlink is to be used for downloading a resou");
		Data.put("" +Atrib.DRAGGABLE, "Defines whether the element can be dragged.");//GLOBAL
		Data.put("" +Atrib.DROPZONE, "Indicates that the element accept the dropping of content on it.");//GLOBAL
		Data.put("" +Atrib.ENCTYPE, "Defines the content type of the form date when the method is POST.");
		Data.put("" +Atrib.FOR, "Describes elements which belongs to this one.");
		Data.put("" +Atrib.FORM, "Indicates the form that is the owner of the element.");
		Data.put("" +Atrib.FORMACTION,"Indicates the action of the element, overriding the action defined in the <form>.");
		Data.put("" +Atrib.HEADERS,"IDs of the <th> elements which applies to this element");
		Data.put("" +Atrib.HEIGHT,"	Prevents rendering of given element, while keeping child elements, e.g. script elements, active.");
		Data.put("" +Atrib.HIDDEN,"Indicates the lower bound of the upper range.");//GLOBAL
		Data.put("" +Atrib.HIGH, "Indicates the lower bound of the upper range.");
		Data.put("" +Atrib.HREF,"The URL of a linked resource.");
		Data.put("" +Atrib.HREFLANG, "Specifies the language of the linked resource.");
		Data.put(""+Atrib.ICON, "Specifies a picture which represents the command.");
		Data.put(""+Atrib.ID, "	Often used with CSS to style a specific element. The value of this attribute must be unique.");//GLOBAL
		Data.put(""+Atrib.ISMAP, "Indicates that the image is part of a server-side image map.");
		Data.put(""+Atrib.ITEMPROP, "");//GLOBAL
		Data.put(""+Atrib.KEYTYPE, "Specifies the type of key generated.");
		Data.put(""+Atrib.KIND, "Specifies the kind of text track.");
		Data.put(""+Atrib.LABEL, "Specifies a user-readable title of the text track.");
		Data.put(""+Atrib.LANG, "Defines the language used in the element.");//GLOBAL
		Data.put(""+Atrib.LANGUAGE, "Defines the script language used in the element.");
		Data.put(""+Atrib.LIST, "Identifies a list of pre-defined options to suggest to the user.");
		Data.put(""+Atrib.LOOP, "Indicates whether the media should start playing from the start when it's finished.");
		Data.put(""+Atrib.LOW, "Indicates the upper bound of the lower range.");
		Data.put(""+Atrib.MANIFEST, "Indicates the upper bound of the lower range.");
		Data.put(""+Atrib.MAX, "Indicates the maximum value allowed.");
		Data.put(""+Atrib.MAXLENGTH, "Defines the maximum number of characters allowed in the element.");
		Data.put(""+Atrib.MEDIA, "Specifies a hint of the media for which the linked resource was designed.");
		Data.put(""+Atrib.METHOD, "	Defines which HTTP method to use when submitting the form. Can be GET (default) or POST.");
		Data.put(""+Atrib.MIN, "Indicates the minimum value allowed.");
		Data.put(""+Atrib.MULTIPLE, "Indicates whether multiple values can be entered in an input of the type email or file.");
		Data.put(""+Atrib.MUTED, "Indicates whether the audio will be initially silenced on page load.");
		Data.put(""+Atrib.NAME, "Name of the element. For example used by the server to identify the fields in form submits.");
		Data.put(""+Atrib.NONVALIDATE, "This attribute indicates that the form shouldn't be validated when submitted.");
		Data.put(""+Atrib.OPEN, "Indicates whether the details will be shown on page load.");
		Data.put(""+Atrib.OPTIMUM, "Indicates the optimal numeric value.");
		Data.put(""+Atrib.PATTERN, "Defines a regular expression which the element's value will be validated against.");
		Data.put(""+Atrib.PING, "");
		Data.put(""+Atrib.PLACEHOLDER, "Provides a hint to the user of what can be entered in the field.");
		Data.put(""+Atrib.POSTER, "A URL indicating a poster frame to show until the user plays or seeks.");
		Data.put(""+Atrib.PRELOAD, "Indicates whether the whole resource, parts of it or nothing should be preloaded.");
		Data.put(""+Atrib.READONLY, "Indicates whether the element can be edited.");
		Data.put(""+Atrib.REL, "Specifies the relationship of the target object to the link object.");
		Data.put(""+Atrib.REQUIRED, "Indicates whether this element is required to fill out or not.");
		Data.put(""+Atrib.REVERSED, "	Indicates whether the list should be displayed in a descending order instead of a ascending.");
		Data.put(""+Atrib.ROWS, "Defines the number of rows in a text area.");
		Data.put(""+Atrib.ROWSPAN, "Defines the number of rows a table cell should span over.");
		Data.put(""+Atrib.SANDBOX, "");
		Data.put(""+Atrib.SCOPE, "");
		Data.put(""+Atrib.SCOPED, "");
		Data.put(""+Atrib.SEAMLESS, "");
		Data.put(""+Atrib.SELECTED, "Defines a value which will be selected on page load.");
		Data.put(""+Atrib.SHAPE, "");
		Data.put(""+Atrib.SIZE, "Defines the width of the element (in pixels). If the element's type attribute is text or password then it's the number of characters.");
		Data.put(""+Atrib.SIZES, "");
		Data.put(""+Atrib.SPAN, "");
		Data.put(""+Atrib.SPELLCHECK, "Indicates whether spell checking is allowed for the element.");//GLOBAL
		Data.put(""+Atrib.SRC, "The URL of the embeddable content.");
		Data.put(""+Atrib.SRCDOC, "");
		Data.put(""+Atrib.SRCLANG, "");
		Data.put(""+Atrib.SRCSET, "");
		Data.put(""+Atrib.START, "Defines the first number if other than 1.");
		Data.put(""+Atrib.STEP, "");
		Data.put(""+Atrib.STYLE, "Defines CSS styles which will override styles previously set.");//GLOBAL
		Data.put(""+Atrib.SUMMARY, "");
		Data.put(""+Atrib.TABINDEX, "Overrides the browser's default tab order and follows the one specified instead.");//GLOBAL
		Data.put(""+Atrib.TARGET, "");
		Data.put(""+Atrib.TITLE, "Text to be displayed in a tooltip when hovering over the element.");//GLOBAL
		Data.put(""+Atrib.TYPE, "Defines the type of the element.");
		Data.put(""+Atrib.USEMAP, "");
		Data.put(""+Atrib.VALUE, "Defines a default value which will be displayed in the element on page load.");
		Data.put(""+Atrib.WRAP, "Indicates whether the text should be wrapped.");
	}
	
	public String getData(String event){
		 if (!Data.containsKey(event)) {
	            return null;
	        }

	        return Data.get(event);
	    }
}
