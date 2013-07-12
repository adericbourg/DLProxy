import java.util.Map;

import play.Application;
import play.GlobalSettings;
import play.Play;

import com.typesafe.config.ConfigValue;

/**
 * @author adericbourg
 */
public class Global extends GlobalSettings {

    private static final String PROXY_SETTING_PREFIX = "network.out.";

    @Override
    public void onStart(Application app) {
        super.onStart(app);
        registerProxySettings();
    }

    private void registerProxySettings() {
        for (Map.Entry<String, ConfigValue> configurationEntry : Play.application().configuration().entrySet()) {
            if (configurationEntry.getKey().startsWith(PROXY_SETTING_PREFIX)) {
                System.setProperty(configurationEntry.getKey().substring(PROXY_SETTING_PREFIX.length()), configurationEntry.getValue()
                        .render());
            }
        }
    }
}
