package se.faridmobin.adventuregame.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import se.faridmobin.adventuregame.models.Resident;
import se.faridmobin.adventuregame.models.Burglar;

public class GameTest {

    @Test
    public void testPunch() {
        Resident resident = new Resident();
        Burglar burglar = new Burglar();
        resident.punch(burglar);
        assertEquals(9, burglar.getHealth(), "Tjuven ska ha 9 hälsa efter ett slag.");
    }

    @Test
    public void testTakeHit() {
        Resident resident = new Resident();
        resident.takeHit(4);
        assertEquals(8, resident.getHealth(), "Boendet ska ha 8 hälsa efter att ha tagit 4 skador.");
    }

    @Test
    public void testIsConscious() {
        Resident resident = new Resident();
        assertTrue(resident.isConscious(), "Boendet måste vara medveten med 12 hälsa.");
        resident.takeHit(12);
        assertFalse(resident.isConscious(), "Boendet ska vara knockad efter att få 12 skador.");
    }
}
