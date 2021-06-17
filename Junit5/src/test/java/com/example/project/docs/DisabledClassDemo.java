package com.example.project.docs;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled("Disabled until bug #99 has been fixed")
class DisabledClassDemo {

	@Disabled("Disabled until bug #42 has been resolved")
    @Test
    void testWillBeSkipped() {
    }

}