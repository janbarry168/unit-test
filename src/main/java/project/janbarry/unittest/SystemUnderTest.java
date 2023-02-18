package project.janbarry.unittest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;

@Service
public class SystemUnderTest extends ParentSystem {

    public DependencyOfComponent doc;

    private static ZoneId ZONE_ID = ZoneId.of("Asia/Taipei");

    @Autowired
    public SystemUnderTest(DependencyOfComponent doc) {
        this.doc = doc;
    }

    public void doLogic() {
        System.out.println(ZONE_ID);
        String today = doc.getToday(ZONE_ID);
        System.out.println(today);
    }

}
