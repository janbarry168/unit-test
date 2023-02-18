package project.janbarry.unittest;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ParentSystemDependency {

    public void printNow() {
        System.out.println(LocalDateTime.now());
    }

}
