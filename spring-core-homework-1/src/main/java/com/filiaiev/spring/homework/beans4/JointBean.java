package com.filiaiev.spring.homework.beans4;

import com.filiaiev.spring.homework.interfaces.Beanterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class JointBean {
    private Beanterface beanG;
    private Beanterface beanH;
    private Beanterface beanI;

    @Autowired
    public JointBean(Beanterface beanG,
                     @Qualifier("beanH") Beanterface beanH,
                     @Qualifier("beanI") Beanterface beanI){
        this.beanG = beanG;
        this.beanH = beanH;
        this.beanI = beanI;
    }

    @Override
    public String toString(){
        return "JointBean: beanG(primary) = " + beanG + ", beanH(q) = " + beanH +
                ", beanI(q) = " + beanI;
    }
}
