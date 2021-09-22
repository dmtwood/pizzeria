package com.dimidev.vdab.spring.pizzeria.sessions;

import com.dimidev.vdab.spring.pizzeria.domain.Pizza;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@SessionScope
public class Order implements Serializable {

// MEMBER VARS

    private static final long serialVersionUID = 1L;
    private List<Long> ids = new ArrayList<>();


// METHODS

    public boolean contains(long id){
        return ids.contains(id);
    }

    public boolean isFilled(){
        return ! ids.isEmpty();
    }

    public void add(long id){
        ids.add(id);
    }

}
