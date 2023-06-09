package de.iav;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public class Timer implements Subject {
    private List<TimerObserver> observerList;
    private int elapsedSeconds;
    private boolean timerIsRunning;

    public Timer() {
        observerList = new ArrayList<>();
        timerIsRunning = false;
        elapsedSeconds = 0;

    }

    public void start() {
        if (!timerIsRunning) {
            timerIsRunning = true;
            Thread timerThread = new Thread(() -> {
                while (timerIsRunning) {
                    try {
                        Thread.sleep(1000);
                        elapsedSeconds++;
                        notifyObserver();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

            });
            timerThread.start();
        }

    }

    public void stop() {
        timerIsRunning = false;

    }


    @Override
    public void attachObserver(TimerObserver observer) {
        observerList.add(observer);
    }

    @Override
    public void detachObserver(TimerObserver observer) {
        observerList.remove(observer);

    }

    @Override
    public void notifyObserver() {
        for (TimerObserver timerObserver : observerList) {
            timerObserver.handleUpdate(elapsedSeconds);
        }

    }
}
