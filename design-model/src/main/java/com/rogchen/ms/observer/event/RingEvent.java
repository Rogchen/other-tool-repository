package com.rogchen.ms.observer.event;

import java.util.EventObject;

/**
 * @Description:
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2019/4/23 12:00
 **/
public class RingEvent extends EventObject {

    private static final long serialVersionUID=1L;
    private boolean sound;    //true表示上课铃声,false表示下课铃声

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public RingEvent(Object source,boolean sound) {
        super(source);
        this.sound = sound;
    }
}
