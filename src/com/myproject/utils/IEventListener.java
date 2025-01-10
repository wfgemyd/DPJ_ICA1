package com.myproject.utils;

import com.myproject.events.Event;


public interface IEventListener {
    void onEvent(Event e);
}