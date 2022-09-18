package project.janbarry.unittestdemo;

import org.junit.jupiter.api.Test;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.time.Instant;
import java.time.ZoneId;

import static org.mockito.Mockito.*;

public class DemoUnitTest {

    @Test
    public void innerClassTestDouble() {
        DependencyOfComponent doc = new DependencyOfComponent() {
            @Override
            public void printDateTime(ZoneId zoneId) {
                System.out.println(Instant.now());
            }
        };
        SystemUnderTest sut = new SystemUnderTest(doc);
        sut.doLogic();
    }

    @Test
    public void mockTestDouble() {
        DependencyOfComponent doc = mock(DependencyOfComponent.class);
        SystemUnderTest sut = new SystemUnderTest(doc);
        sut.doLogic();
    }

    @Test
    public void spyTestDouble() {
        DependencyOfComponent doc = spy(DependencyOfComponent.class);
        SystemUnderTest sut = new SystemUnderTest(doc);
        sut.doLogic();
    }

}
