package project.janbarry.unittestdemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.Instant;
import java.time.ZoneId;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.spy;

@RunWith(MockitoJUnitRunner.class)
public class Mockito4UnitTest {

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
    
}
