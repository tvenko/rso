package si.fri.apartment.users.api.v1;

import org.eclipse.microprofile.health.HealthCheckResponse;

import javax.inject.Inject;
import java.util.logging.Logger;

public class HealthCheck implements org.eclipse.microprofile.health.HealthCheck {

    @Inject
    private RestProperties restProperties;

    @Override
    public HealthCheckResponse call() {

        if (restProperties.isHealthy()) {
            return HealthCheckResponse.named(HealthCheck.class.getSimpleName()).up().build();
        } else {
            return HealthCheckResponse.named(HealthCheck.class.getSimpleName()).down().build();
        }

    }

}
