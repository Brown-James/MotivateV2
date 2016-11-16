package com.brownjames.motivatev2;

import com.brownjames.motivatev2.data.Task;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by james on 23/09/16.
 */

public class TaskTests {
    @Before
    public void setup() {

    }

    @Test
    public void equals() {
        Task t1 = new Task(1, "task1", 1000, 10, "GBP");
        Task t2 = new Task(2, "task1", 1000, 10, "GBP");
        Task t3 = new Task(1, "task3", 1000, 10, "GBP");

        assertTrue(t1.equals(t2));
        assertFalse(t1.equals(t3));
    }
}
