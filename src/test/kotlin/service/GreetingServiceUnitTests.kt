package es.unizar.webeng.hello.service

import java.time.Clock
import java.time.Instant
import java.time.ZoneOffset
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GreetingServiceUnitTests {
    @Test
    fun `should return the correct greeting for each time of day`() {
        assertThat(greetingAt("2026-09-25T06:00:00Z").getGreeting("Ada"))
            .isEqualTo("Good morning, Ada!")
        assertThat(greetingAt("2026-09-25T08:30:00Z").getGreeting("Ada"))
            .isEqualTo("Good morning, Ada!")
        assertThat(greetingAt("2026-09-25T11:59:00Z").getGreeting("Ada"))
            .isEqualTo("Good morning, Ada!")
        assertThat(greetingAt("2026-09-25T12:00:00Z").getGreeting("Ada"))
            .isEqualTo("Good afternoon, Ada!")
        assertThat(greetingAt("2026-09-25T14:20:00Z").getGreeting("Ada"))
            .isEqualTo("Good afternoon, Ada!")
        assertThat(greetingAt("2026-09-25T17:59:00Z").getGreeting("Ada"))
            .isEqualTo("Good afternoon, Ada!")
        assertThat(greetingAt("2026-09-25T18:00:00Z").getGreeting("Ada"))
            .isEqualTo("Good evening, Ada!")
        assertThat(greetingAt("2026-09-25T20:30:00Z").getGreeting("Ada"))
            .isEqualTo("Good evening, Ada!")
        assertThat(greetingAt("2026-09-25T21:59:00Z").getGreeting("Ada"))
            .isEqualTo("Good evening, Ada!")
        assertThat(greetingAt("2026-09-25T22:00:00Z").getGreeting("Ada"))
            .isEqualTo("Good night, Ada!")
        assertThat(greetingAt("2026-09-25T00:30:00Z").getGreeting("Ada"))
            .isEqualTo("Good night, Ada!")
        assertThat(greetingAt("2026-09-25T05:59:00Z").getGreeting("Ada"))
            .isEqualTo("Good night, Ada!")
    }

    @Test
    fun `should omit the name when it is blank`() {
        assertThat(greetingAt("2026-09-25T08:00:00Z").getGreeting("   "))
            .isEqualTo("Good morning!")
    }

    private fun greetingAt(instant: String): GreetingService = GreetingService(
        Clock.fixed(Instant.parse(instant), ZoneOffset.UTC)
    )
}