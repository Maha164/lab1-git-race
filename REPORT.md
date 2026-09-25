# Lab 1 Git Race -- Project Report

This note uses the same disclosure fields as the group-project **AI use (10%)** slice. Lab 1 is still **limited**: assistive GenAI only — not a full or substantial generated solution. The project will later expect agents plus `AGENTS.md` and one skill; you do **not** need those here.

Do not invent a percentage of “AI vs original” lines. Empty or fake disclosure fails this lab.

## What I specified

[The increment you decided to add *before* generating or pasting code. How you would know it works.]

I decided to work in a GitHub Codespace, as the teacher explained in class, using the IA agent provided in VS Code. Before generating code, I asked the agent to explain the provided code, and once I understood it, I asked how the new functionality could be implemented to test it without being limited by the current time. These technologies are new to me, that is why using AI really helped me understand it.

I have decided to implement one of the options proposed by the teacher. The codebase has been modified so that the website offers a greeting based on the time of day: good morning, good afternoon, good evening and good night.

To do this, the time is obtained, and a different greeting is displayed depending on the hour. The website displays "Good morning" from 6:00 to 11:59, “Good afternoon” from 12:00 to 17:59, “Good evening” from 18:00 to 21:59 and “Good night” from 22:00 to 5:59.

After researching and consulting the official documentation, I have decided to implement this as a service to enable code reuse while adhering to the MVC (Model-View-Controller) architecture; this way, the controller handles the HTTP protocol, while the service determines the time of day and generates the appropriate greeting. Implementing it this way also facilitates testing to verify the application's correct operation.

Once implemented, AI was used to correct it, and it was launched using the following command: `./gradlew bootRun`. This command compiles and runs the Spring Boot application. Once this is done, you can open the application in a browser and perform some manual tests; however, it is not possible to verify that the appropriate greeting is displayed based on the time of day, that is why other tests have been designed.

## What I changed

[Files and behaviour. Not a restatement of the starter README.]

The files I modified are: HelloControler.kt, HelloWorld.kt, HelloControllerMVCTests.kt, HelloControllerUnitTests.kt and IntegrationTest.kt. The files I added are: GreetingService.kt and GreetingServiceUnitTests.kt.

The main change has been the implementation of the service; the other changes were made to ensure that the controller uses it.

## Technical decisions

[Choices you own: API shape, tests, data, what you rejected.]

It has been implemented so that each component has a specific responsibility: the service handles retrieving the greeting based on the time of day, while the controllers manage HTTP requests and responses.

By injecting `java.time.Clock` as a Spring bean, we achieve independence from real time, enabling us to perform tests without being constrained by the current time.

Finally, tests have been implemented and executed at various levels to ensure the correct running of the application.

## How I verified

[Commands (`./gradlew check`), what failed first, what you fixed. You remain accountable for correctness.]

The codebase provided by the teacher includes tests. When implementing the code change, these tests were modified to verify the changes that had been made.

### Unit tests

To perform unit tests and avoid non-deterministic behavior, `LocalTime.now(clock)` was used; this allows `Clock.systemDefaultZone(...)` to be called in production, while `Clock.fixed(...)` is used during testing. `Clock.systemDefaultZone(...)` shows the actual time, while `Clock.fixed(...)` is told what time to simulate. With the fixed clock, it is possible to simulate any time of day and verify the application's correct operation using deterministic tests capable of evaluating limit cases.

### MVC tests

These tests are used to test the controllers. The `@MockitoBean` annotation is used to create a mock, ensuring the controllers do not depend on the service or the physical system clock. The objective is to verify whether the controller correctly handles the HTTP request and returns the correct response.

### Integration test

Unlike unit tests or web-layer tests, this test fully starts up the Spring Boot application, launches a real web server on a network port, and makes real HTTP requests over the local network. When running this test, the application will operate under the assumption that it is 10:00 AM; this ensures that the "Good morning..." checks always work.

To run the tests, we execute the following command: `./gradlew test`.

At first, some of the MVC tests and the integration test failed because I haven't used the clock correctly in these tests. With the help of AI, I have added the `testClock()` function so that these tests can be run simulating a specific time.


## AI disclosure

Fill **either** the list **or** the no-AI line.

- **Tools / skills:** AI Agent of the codespace and Gemini with the university account.
- **Purpose:**  I used Gemini to understand what is the best option for getting the time and running tests without relying on the current time, and the AI ​​agent offered in the Codespace helped me write the tests.
- **Representative prompts:** ¿Qué es lo que hace este código? - ¿Por qué cuándo se ha lanzado hay dos puertos? - Se desea implementar una mejora en la que cambia el saludo dependiendo de la hora del día. ¿Cómo se podrían implementar las pruebas para esto? - ¿Cuál es la mejor forma de implementar los MVCTest y los tests de integración? - ¿En MVC no se podría poner el reloj como una variable y no como un Bean?
- **Affected files/sections:** The affected files are those containing tests.
- **Validation steps:** I have carefully reviewed the generated code and, in some cases, modified it after accepting it. Then I run it to see if it behaves as expected. 
- **Citations:** … (external snippets you adapted)
- **Human-reviewed:** The unit tests did not cover all scenarios, so I added them.
