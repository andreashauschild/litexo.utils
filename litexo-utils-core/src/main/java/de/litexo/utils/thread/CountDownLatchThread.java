package de.litexo.utils.thread;

import java.util.concurrent.CountDownLatch;

/**
 * www.litexo.de
 *
 * @author Andreas Hauschild
 */
public abstract class CountDownLatchThread implements Runnable {

    private CountDownLatch latch;

    public abstract void execute();

    @Override
    public void run() {

        try {
            execute();
        } finally {
            this.latch.countDown();
        }
    }

    public void setLatch(final CountDownLatch latch) {
        this.latch = latch;
    }
}
