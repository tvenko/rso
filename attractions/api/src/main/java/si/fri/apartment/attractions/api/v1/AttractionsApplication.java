package si.fri.apartment.attractions.api.v1;



import com.kumuluz.ee.discovery.annotations.RegisterService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/v1")
@RegisterService
public class AttractionsApplication extends Application {
}
