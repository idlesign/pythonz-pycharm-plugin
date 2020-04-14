import com.intellij.openapi.application.ApplicationInfo;
import com.intellij.openapi.components.Service;
import com.intellij.ui.jcef.JBCefBrowser;
import org.cef.network.CefRequest;

import javax.swing.*;
import java.util.Hashtable;
import java.util.Map;


@Service
final public class BrowserService {

    private JBCefBrowser browser;
    Map<String, String> header = new Hashtable<>();

    public BrowserService() {
        browser = new JBCefBrowser();

        browser.loadHTML(
                "<div style=\"font-family:monospace\">" +
                "Now when the browser is initialized you may call code hint again.</div>");

        ApplicationInfo appInfo = ApplicationInfo.getInstance();
        header.put("ide-name", appInfo.getVersionName());
        header.put("ide-version", appInfo.getFullVersion());
    }

    public JComponent getComponent(){
        return browser.getComponent();
    }

    public void loadUrl(String url) {
        CefRequest request = CefRequest.create();
        request.setHeaderMap(header);
        request.setURL(url);

        browser.getCefBrowser().loadRequest(request);
    }

}
