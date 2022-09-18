package project.janbarry.unittestdemo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.Instant;
import java.time.ZoneId;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class Mockito5UnitTest {

    @InjectMocks
    SystemUnderTest sut;

    @Mock
    DependencyOfComponent mock;

    @Spy
    DependencyOfComponent spy;


    @Test
    public void mockTestDouble() {
        sut.doLogic();
    }

    @Test
    public void spyTestDouble() {
        SystemUnderTest sut = new SystemUnderTest(spy);
        sut.doLogic();
    }

    @Test
    public void spySut() {
        SystemUnderTest spy = spy(sut);
        doAnswer(invocation -> {
            System.out.println(Instant.now());
            System.out.println(ZoneId.systemDefault());
            return null;
        }).when(spy).doLogic();
        sut.doLogic();
        spy.doLogic();
    }

    @Test
    public void reflectionPrivateStaticField() {
        Field field = ReflectionUtils.findField(SystemUnderTest.class, "ZONE_ID");
        ReflectionUtils.makeAccessible(field);
        // private static final -> fail
        ReflectionUtils.setField(field, sut, ZoneId.of("Europe/Paris"));
        sut.doLogic();
    }

}
