package project.janbarry.unittest;

import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class DependencyOfComponent {

    public String getToday(ZoneId zoneId) {
        return ZonedDateTime.now(zoneId).format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
    }

}
