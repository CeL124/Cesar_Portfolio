package application;



public enum GlobalAttrib {
	// Set<Atrib> globalSet = EnumSet.of(Atrib.ACCESSKEY, Atrib.CONTENTEDITABLE,
	// Atrib.COLSPAN, Atrib.CLASS,
	// Atrib.CONTEXTMENU, Atrib.DIR, Atrib.DRAGGABLE, Atrib.DROPZONE,
	// Atrib.HIDDEN, Atrib.ID, Atrib.LANG,
	// Atrib.SPELLCHECK, Atrib.STYLE, Atrib.TABINDEX, Atrib.TITLE,
	// Atrib.TRANSLATE);

	ACCESSKEY, CONTENTEDITABLE, COLSPAN, CLASS, CONTEXTMENU, DIR, DRAGGABLE, DROPZONE, HIDDEN, ID, LANG, SPELLCHECK, STYLE, TABINDEX, TITLE, TRANSLATE;

	public String toString() {
		switch (this) {
		case ACCESSKEY:
			return "accessKey";
		case CONTENTEDITABLE:
			return "contenteditable";
		case COLSPAN:
			return "colspan";
		case CLASS:
			return "class";
		case CONTEXTMENU:
			return "contextmenu";
		case DIR:
			return "dir";
		case DRAGGABLE:
			return "draggable";
		case DROPZONE:
			return "dronezone";
		case HIDDEN:
			return "hidden";
		case ID:
			return "id";
		case LANG:
			return "lang";
		case SPELLCHECK:
			return "spellcheck";
		case STYLE:
			return "style";
		case TABINDEX:
			return "tabindex";
		case TITLE:
			return "title";
		case TRANSLATE:
			return "translate";
		default:
			return "Error";

		}
	}

}