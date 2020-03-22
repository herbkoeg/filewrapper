package de.hk;

import org.junit.jupiter.api.Test;

public class MyUtilTest {

    @Test
    public void testReadAndExtendProgramId() {

        MyUtil.readAndExtendProgramId();

    }

    @Test
    public void testExtendProgramId() {
        System.out.println(MyUtil.extendProgramId("PROGRAM-ID jamei"));
    }
}
