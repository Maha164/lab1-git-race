package es.unizar.webeng.hello.service

import java.time.Clock
import java.time.LocalTime
import org.springframework.stereotype.Service

/**
 * Represent the times of day with their greetings.
 *
 * @property salutation Greeting appropriate for the time of day.
 */
enum class TimeOfDay(val salutation: String) {
    MORNING("Good morning"),
    AFTERNOON("Good afternoon"),
    EVENING("Good evening"),
    NIGHT("Good night");

    companion object {
        /**
         * Function that returns the time of day based on the hour.
         *
         * @param hour Current hour from 0 to 23.
         * @return The time of the day, depending on the hour.
         */
        fun time(hour: Int): TimeOfDay = when (hour) {
            in 6..11 -> MORNING
            in 12..17 -> AFTERNOON
            in 18..21 -> EVENING
            else -> NIGHT
        }
    }
}

/**
 * Service that retrieves personalized greetings based on the time of day.
 *
 * @param clock Clock for obtaining the current instant.
 */
@Service
class GreetingService(
    private val clock: Clock = Clock.systemDefaultZone()
) {

    /**
     * Function that returns the time of day based on the hour given by 'clock'.
     *
     * @return The time of the day.
     */
    fun getTimeOfDay(): TimeOfDay {
        val currentHour = LocalTime.now(clock).hour
        return TimeOfDay.time(currentHour)
    }

    /**
     * Function that returns a greeting according to the name and hour given.
     *
     * @param name Name of the person to greet.
     * @return The greeting message.
     */
    fun getGreeting(name: String = ""): String {
        val salutation = getTimeOfDay().salutation
        return if (name.isNotBlank()) {
            "$salutation, $name!"
        } else {
            "$salutation!"
        }
    }
}