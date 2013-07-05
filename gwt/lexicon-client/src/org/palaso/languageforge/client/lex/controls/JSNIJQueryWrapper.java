package org.palaso.languageforge.client.lex.controls;

import com.google.gwt.user.client.Element;

/**
 * this class is s wrapper of js script (jquery.main.js) because GWT creates
 * HTML code on fly, so we need to initialize those jquery functions later.
 * 
 * @author xin
 * 
 */
public class JSNIJQueryWrapper {
	public static native void initializeJQueryOpenClose() /*-{
		$wnd.jQuery(function() {
			$wnd.initOpenClose();
		});
	}-*/;

	public static native void addJQueryScrollbars(String gwtBasePath) /*-{
		$wnd.jQuery(function() {
			$wnd.VSA_initScrollbars(gwtBasePath);
		});
	}-*/;

	public static native void removeJQueryScrollbars(Element parentdiv) /*-{
		for ( var i = 0; i < parentdiv.childNodes.length; i++) {
			if (parentdiv.childNodes[i].className == "scroll-content") {
				scrollcontent = parentdiv.childNodes[i];
				parentdiv.removeChild(scrollcontent);
				for ( var j = 0; j < scrollcontent.childNodes.length; j++) {
					scrollcontentinner = scrollcontent.childNodes[j];
					scrollcontent.removeChild(scrollcontentinner);
					parentdiv.appendChild(scrollcontentinner);
				}
			}
			if (parentdiv.childNodes[i].className == "vscroll-bar") {
				parentdiv.removeChild(parentdiv.childNodes[i]);
			}
		}
	}-*/;

	public static native boolean isJQueryAvailable() /*-{
		if (!$wnd.jQuery) {
			return false;
		} else {
			return true;
		}
	}-*/;
}
