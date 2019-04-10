package si.fri.apartment.apartmentsanalysis.api.v1;

import com.kumuluz.ee.discovery.annotations.RegisterService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/v1")
@RegisterService
public class ApartmentsAnalysisApplication extends Application{

}
