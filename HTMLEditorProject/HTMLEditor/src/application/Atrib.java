package application;

public enum Atrib {

    ACCEPT, ALTACCEPT, ACCEPT_CHARSET, CLASS,COLSPAN, ACCESSKEY, ACTION, ALIGN, ALT, ASYNC, AUTOCOMPLETE, AUTOFOCUS, AUTOPLAY, AUTOSAVE, MEDIA, SCOPED, TYPE, OPEN
    ,CHARSET, CITE, CONTENT,DATETIME, HTTP, NAME, TARGET, HREF,DEFER, LANGUAGE, SRC, HEIGHT, WIDTH, DATA, FORM, USEMAP, VALUE, HIGH, LOW,
    MAX, MIN, OPTIMUM, ENCTYPE, METHOD, NOVALIDATE, CHECKED, DIRNAME, DISABLED, FORMACTION, LIST, MAXLENGTH, MULTIPLE, PATTERN, PLACEHOLDER, STEP
    ,COLS, WRAP, FOR, FORMLABEL, SELECT, CHALLENGE, KEYTYPE, SRCDOC, BUFFERED, CONTROLS, LOOP, PRELOAD, DEFAULT, LABEL, SRCLANG
    ,MUTED, POSTER, DOWNLOAD, HREFLANG, PING, ISMAP,SRCSET, COORDS, START, SUMMARY, CONTENTEDITABLE, CONTEXTMENU, DATA_, DIR, DRAGGABLE, DROPZONE, HEADERS, HIDDEN, ICON, ID, ITEMPROP, KIND, LANG, MANIFEST, NONVALIDATE, READONLY, REL, REQUIRED, REVERSED, ROWS, ROWSPAN, SANDBOX, SCOPE, SEAMLESS, SELECTED, SHAPE, SIZE, SIZES, SPAN, SPELLCHECK, STYLE, TABINDEX, TITLE;
	
	
    public String toString() {
        switch (this) {

            case ACCEPT:
                return "Accept";
            case ACCEPT_CHARSET:
                return "Accept-Charset";
            case ACCESSKEY:
                return "Access Key";
            case ACTION:
                return "Action";
            case ALIGN:
                return "Align";
            case ALT:
                return "Alt";
            case ASYNC:
                return "Async";
            case AUTOCOMPLETE:
                return "Auto Complete";
            case AUTOFOCUS:
                return "Auto Focus";
            case AUTOPLAY:
                return "Auto Play";
            case AUTOSAVE:
                return "Auto Save";
            case MEDIA:
                return "Media";
            case OPEN:
                return "Open";
            case SCOPED:
                return "Scoped";
            case TYPE:
                return "Type";
            case COLSPAN: //global
                return "Colspan";     
            case CLASS: //global
                return "Class";
            case CHARSET:
                return "Charset";   
            case CONTENT:
                return "Content";
            case NAME:
                return "Name";
            case HTTP:
                return "Http-equiv";
            case TARGET:
                return "Target";
            case HREF:
                return "Href";
            case DEFER:
                return "Defer";
            case LANGUAGE:
                return "Language";
            case SRC:
                return "Src";
            case HEIGHT:
                return "Height";
            case WIDTH:
                return "Width";
            case DATA:
                return "Data";
            case FORM:
                return "Form";
            case USEMAP:
                return "Usemap";
            case VALUE:
                return "Value";
            case CITE:
                return "Cite";
            case DATETIME:
                return "Datetime";
            case HIGH:
                return "High";
            case LOW:
                return "Low";
            case MAX:
                return "Max";
            case MIN:
                return "Min";
            case OPTIMUM:
                return "Optimum";
            case ENCTYPE:
                return "Enctype";
            case METHOD:
                return "Method";
            case NOVALIDATE:
                return "Novalidate";
            case CHECKED:
                return "Checked";
            case DIRNAME:
                return "Dirname";
            case DISABLED:
                return "Disabled";
            case FORMACTION:
                return "Formaction";
            case MAXLENGTH:
                return "Maxlength";
            case MULTIPLE:
                return "Multiple";
            case PATTERN:
                return "Pattern";
            case PLACEHOLDER:
                return "Placeholder";
            case STEP:
                return "Step";
            case LIST:
                return "List";
            case COLS:
                return "Cols";
            case WRAP:
                return "Wrap";
            case SELECT:
                return "Select";
            case CHALLENGE:
                return "Challenge";
            case KEYTYPE:
                return "Keytype";
            case SRCDOC:
                return "SRCdoc";
            case BUFFERED:
                return "Buffered";
            case CONTROLS:
                return "Controls";
            case LOOP:
                return "Loop";
            case PRELOAD:
                return "Preload";
            case DEFAULT:
                return "Default";
            case LABEL:
                return "Label";
            case SRCLANG:
                return "SRClang";
            case MUTED:
                return "Muted";
            case POSTER:
                return "Poster";
            case DOWNLOAD:
                return "Download";
            case HREFLANG:
                return "Hreflang";
            case PING:
                return "Ping";
            case ISMAP:
                return "Ismap";
            case SRCSET:
                return "SRCset";
            case COORDS:
                return "Coords";
            case START:
                return "Start"; 
            case SUMMARY:
                return "Summary";
            default:
                return "Error";
        }
        //put in all attributes
    }

}
