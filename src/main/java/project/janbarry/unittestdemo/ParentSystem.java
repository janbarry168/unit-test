package project.janbarry.unittestdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParentSystem {

    @Autowired
    ParentSystemDependency parentSystemDependency;

    public void printNow() {
        parentSystemDependency.printNow();
    }

}
