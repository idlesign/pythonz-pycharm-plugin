import com.intellij.openapi.application.ApplicationInfo;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.ui.popup.JBPopupFactory;
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

        ApplicationInfo appInfo = ApplicationInfo.getInstance();
        header.put("ide-name", appInfo.getVersionName());
        header.put("ide-version", appInfo.getFullVersion());
    }

    public JComponent getComponent(){
        return browser.getComponent();
    }

    public Boolean loadUrl(String url) {
        if (browser != null) {

            CefRequest request = CefRequest.create();
            request.setHeaderMap(header);
            request.setURL(url);

            browser.getCefBrowser().loadRequest(request);

            return true;
        }
        return false;
    }

}
