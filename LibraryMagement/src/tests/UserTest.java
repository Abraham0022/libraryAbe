package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.InvalidUserException;
import libraryManagement.User;

class UserTest {

    private User user;
    private LocalDate today;

    @BeforeEach
    void setUp() throws InvalidUserException {
        today = LocalDate.now();
        user = new User("Anacleto", "anacleto@abraham.com", "SOC12345", today);
    }

    // ✅ Constructor correcto
    @Test
    void testUserCreation() {
        assertEquals("Anacleto", user.getName());
        assertEquals("anacleto@abraham.com", user.getEmail());
        assertEquals("SOC12345", user.getMemberNumber());
        assertEquals(today, user.getRegistrationDate());
        assertFalse(user.isSantioned());
        assertNull(user.getSanctionEndDate());
    }

    // ❌ Email incorrecto
    @Test
    void testInvalidEmailMissAt() {

        assertThrows(InvalidUserException.class, () -> {
            new User("Anacleto", "badAtEmail.com", "SOC12345", today);
        });
    }
    @Test
    void testInvalidEmailMissPoint() {

        assertThrows(InvalidUserException.class, () -> {
            new User("Anacleto", "bad@Emailcom", "SOC12345", today);
        });
    }
    @Test
    void testInvalidEmailBadDomain() {
        assertThrows(InvalidUserException.class, () -> {
            new User("Anacleto", "bad@Email.como", "SOC12345", today);
        });
    }
    // ❌ MemberNumber 
    @Test
    void testInvalidMemberNumberLeters() {
        assertThrows(InvalidUserException.class, () -> {
            new User("Anacleto", "good@email.com", "12345", today);
        });
    }
    @Test
    void testInvalidMemberNumberNumbers() {
        assertThrows(InvalidUserException.class, () -> {
            new User("Anacleto", "good@email.com", "SOC1234", today);
        });
    }
    // ✅ Cambiar email correcto
    @Test
    void testSetEmailCorrect() throws InvalidUserException {

        user.setEmail("new@email.com");

        assertEquals("new@email.com", user.getEmail());
    }

    // ❌ Cambiar email incorrecto
    @Test
    void testSetEmailInvalid() {

        assertThrows(InvalidUserException.class, () -> {
            user.setEmail("badEmail");
        });
    }

    // ✅ Cambiar memberNumber correcto
    @Test
    void testSetMemberNumberCorrect() throws InvalidUserException {

        user.setMemberNumber("SOC54321");

        assertEquals("SOC54321", user.getMemberNumber());
    }

    // ❌ Cambiar memberNumber incorrecto
    @Test
    void testSetMemberNumberInvalid() {

        assertThrows(InvalidUserException.class, () -> {
            user.setMemberNumber("ABC123");
        });
    }

    // ✅ Test sanction
    @Test
    void testSanctionUser() {

        user.sanction(5);

        assertTrue(user.isSantioned());
        assertEquals(today.plusDays(5), user.getSanctionEndDate());
    }

    // ✅ Test liftSanction
    @Test
    void testLiftSanction() {

        user.sanction(5);
        user.liftSanction();

        assertFalse(user.isSantioned());
        assertNull(user.getSanctionEndDate());
    }

    // ✅ Cambiar nombre
    @Test
    void testSetName() {

        user.setName("Peter");

        assertEquals("Peter", user.getName());
    }

    // ✅ Cambiar registrationDate
    @Test
    void testSetRegistrationDate() {

        LocalDate newDate = LocalDate.of(2024, 1, 1);

        user.setRegistrationDate(newDate);

        assertEquals(newDate, user.getRegistrationDate());
    }
}

