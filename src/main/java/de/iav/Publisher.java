package de.iav;

import java.util.Observer;

public interface Subject {
    void attachObserver(TimerObserver observer);
    void detachObserver(TimerObserver observer);
    void notifyObserver();
}
