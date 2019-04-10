package si.fri.apartment.hosts.api.v1.health;

import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import si.fri.apartment.hosts.api.v1.configuration.RestProperties;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Health
@ApplicationScoped
public class HostsServiceHealthCheck implements HealthCheck {

    @Inject
    private RestProperties restProperties;

    @Override
    public HealthCheckResponse call() {

        if (restProperties.getHealthy()) {
            return HealthCheckResponse.named(HostsServiceHealthCheck.class.getSimpleName()).up().build();
        } else {
            return HealthCheckResponse.named(HostsServiceHealthCheck.class.getSimpleName()).down().build();
        }

    }

}