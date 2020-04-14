import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.popup.ComponentPopupBuilder;
import com.intellij.openapi.ui.popup.JBPopup;
import com.intellij.openapi.ui.popup.JBPopupFactory;

import javax.swing.*;
import java.awt.*;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Objects;


public class ShowPythonzAction extends AnAction {

    public void actionPerformed(AnActionEvent event) {

        Editor editor = event.getData(PlatformDataKeys.EDITOR);
        String term = getTerm(editor, true);

        if (term.isEmpty() || term.length() < 3) {
            return;
        }

        String termEncoded;
//        String urlBase = "http://localhost:8000";
        String urlBase = "https://pythonz.net";
        urlBase += "/references/ide/?term={term}";

        try {

            termEncoded = urlBase.replace("{term}", URLEncoder.encode(term, "UTF-8"));

        } catch (UnsupportedEncodingException e) {
            throw new AssertionError("UTF-8 not supported for URL encoding");
        }

        BrowserService browser = ServiceManager.getService(BrowserService.class);

        if (browser.loadUrl(termEncoded)) {
            showPopup(browser.getComponent(), editor);
        }

    }

    private String getTerm(Editor editor, Boolean autoselect) {
        String term;

        try {
            term = Objects.requireNonNull(editor.getSelectionModel().getSelectedText()).trim();

        } catch (NullPointerException e) {
            term = "";
        }

        if (term.isEmpty() && autoselect) {
            editor.getSelectionModel().selectWordAtCaret(true);
            term = getTerm(editor, false);
        }

        return term;
    }

    private void showPopup(JComponent panel, Editor editor) {

        ComponentPopupBuilder builder = JBPopupFactory.getInstance().
                createComponentPopupBuilder(panel, null).
                setTitle("pythonz.net").
                setCancelOnWindowDeactivation(true).
                setRequestFocus(true).
                setResizable(true).
                setShowBorder(true).
                setMovable(true);

        final JBPopup popup = builder.createPopup();
        popup.setMinimumSize(new Dimension(600, 450));
        popup.showInBestPositionFor(editor);

    }
}