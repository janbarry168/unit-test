package project.janbarry.unittestdemo;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class DependencyOfComponent {

    public void printDateTime(ZoneId zoneId) {
        System.out.println(ZonedDateTime.now(zoneId));
    }

}
