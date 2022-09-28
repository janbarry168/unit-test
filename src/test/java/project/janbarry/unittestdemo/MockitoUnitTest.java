package project.janbarry.unittestdemo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MockitoUnitTest {

    @InjectMocks
    SystemUnderTest sut;

    @Mock
    DependencyOfComponent mock;

    @Spy
    DependencyOfComponent spy;

    @Spy
    ParentSystemDependency parentSystemDependency;

    @Test
    public void mockTestDouble() {
        SystemUnderTest sut = new SystemUnderTest(mock);
        sut.doLogic();
    }

    @Test
    public void spyTestDouble() {
        SystemUnderTest sut = new SystemUnderTest(spy);
        sut.doLogic();
    }

    @Test
    public void manualMockTestDouble() {
        DependencyOfComponent doc = mock(DependencyOfComponent.class);
        when(doc.getToday(ZoneId.of("Asia/Taipei")))
                .thenReturn(ZonedDateTime.now(ZoneId.of("Europe/Paris")).format(DateTimeFormatter.ISO_ZONED_DATE_TIME));

        SystemUnderTest sut = new SystemUnderTest(doc);
        sut.doLogic();

        verify(doc, times(1)).getToday(ZoneId.of("Asia/Taipei"));
    }

    @Test
    public void manualSpyTestDouble() {
        DependencyOfComponent doc = spy(DependencyOfComponent.class);
        SystemUnderTest sut = new SystemUnderTest(doc);
        sut.doLogic();
    }

    @Test
    public void evilMockTestDouble() {
        mock.getToday(ZoneId.of("Asia/Taipei"));
        when("some string no meaning").thenReturn("stupid mock");

        SystemUnderTest sut = new SystemUnderTest(mock);
        sut.doLogic();
    }

    @Test
    public void spySut() {
        SystemUnderTest spy = spy(sut);
        doAnswer(invocation -> {
            ZoneId zoneId = ZoneId.of("Europe/Paris");
            System.out.println(zoneId);
            System.out.println(ZonedDateTime.now(zoneId).format(DateTimeFormatter.ISO_ZONED_DATE_TIME));
            return null;
        }).when(spy).doLogic();
        spy.doLogic();
    }

    @Test
    public void parentInjectProblem() {
        Exception exception = assertThrows(Exception.class, () -> sut.printNow());
        System.out.println(exception.getMessage());
    }

    @Test
    public void innerClassTestDouble() {
        DependencyOfComponent doc = new DependencyOfComponent() {
            @Override
            public String getToday(ZoneId zoneId) {
                return ZonedDateTime.now(ZoneId.of("Europe/Paris")).format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
            }
        };
        SystemUnderTest sut = new SystemUnderTest(doc);
        sut.doLogic();
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
