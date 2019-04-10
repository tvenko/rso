package si.fri.apartment.users.api.v1;

import com.kumuluz.ee.configuration.cdi.ConfigBundle;

import javax.enterprise.context.ApplicationScoped;

@ConfigBundle("rest-properties")
@ApplicationScoped
public class RestProperties {

    private boolean healthy;

    public boolean isHealthy() {
        return healthy;
    }

    public void setHealth(boolean healthy) {
        this.healthy = healthy;
    }
}