package project.janbarry.unittestdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;

@Service
public class SystemUnderTest {

    public DependencyOfComponent doc;

    private static ZoneId ZONE_ID = ZoneId.systemDefault();

    @Autowired
    public SystemUnderTest(DependencyOfComponent doc) {
        this.doc = doc;
    }

    public void doLogic() {
        doc.printDateTime(getZoneId());
        System.out.println(getZoneId());
    }

    private ZoneId getZoneId() {
        return ZONE_ID;
    }

}
