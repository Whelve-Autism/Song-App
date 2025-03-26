package models;

import models.Artist;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArtistTest {
    private Artist validArtist1, invalidArtist1;

    @BeforeEach
    public void setup() {
        validArtist1 = new Artist("123456789012345", true);
        invalidArtist1 = new Artist("1234567890123456", false);
    }

    @AfterEach
    void tearDown() {
        validArtist1 = invalidArtist1 = null;

    }

    @Nested
    class Constructors {

        @Test
        void constructorTestingTheSettingOfDefaultValues() {
            Artist nameTooLong1 = new Artist("12345678901234567", true);
            assertEquals("123456789012345", nameTooLong1.getArtistName());
            Artist nameTooLong2 = new Artist("1234567890123456", true);
            assertEquals("123456789012345", nameTooLong2.getArtistName());
        }

        @Test
        void constructorTestingValidValues() {
            assertEquals("123456789012345", validArtist1.getArtistName());
            Artist justBelow = new Artist("12345678901234", true);
            assertEquals("12345678901234", justBelow.getArtistName());
            Artist emptyString = new Artist("", true);
            assertEquals("", emptyString.getArtistName());
            assertTrue(validArtist1.isVerified());
            Artist notVerified = new Artist("does not matter", false);
            assertFalse(notVerified.isVerified());

        }
    }

    @Nested
    class GettersAndSetters {

        @Test
        void artistNameGetAndSetWorkingCorrectly() {
            assertEquals("123456789012345", validArtist1.getArtistName());
            validArtist1.setArtistName("12345678901234X");
            assertEquals("12345678901234X", validArtist1.getArtistName()); //change should be made
            validArtist1.setArtistName("1234567890123456"); // too long should not be changed
            assertEquals("12345678901234X", validArtist1.getArtistName());
            validArtist1.setArtistName(""); // should  be changed
            assertEquals("", validArtist1.getArtistName());
        }
        @Test
        void artistVerifiedGetAndSetWorkingCorrectly() {
            assertTrue(validArtist1.isVerified());
            validArtist1.setVerified(false);
            assertFalse(validArtist1.isVerified());
            validArtist1.setVerified(true);
            assertTrue(validArtist1.isVerified());

        }
    }
    @Nested
    class ToString {

        @Test
        void toStringContainsAllFieldsInObject() {
            assertTrue(validArtist1.toString().contains("123456789012345"));

            assertTrue(validArtist1.toString().contains(" is a verified artist"));
            validArtist1.setVerified(false);
            assertTrue(validArtist1.toString().contains(" is not a verified artist"));

        }

        }
}
