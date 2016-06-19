package com.ubs.opsit.interviews;

import com.ubs.opsit.interviews.model.BerlinClock;
import com.ubs.opsit.interviews.rendering.BerlinClockRenderer;
import com.ubs.opsit.interviews.rendering.InterviewStoryRenderer;
import static com.ubs.opsit.interviews.support.BehaviouralTestEmbedder.aBehaviouralTestRunner;
import static org.assertj.core.api.Assertions.assertThat;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Test;

/**
 * Test class to check if the BerlinClock model and renderer are ok. As you can
 * see the clock has no engine, this means it is not capable of initialize
 * itself on a given time, in fact, in this test, we are only going to check the
 * clock status when the lamps are all off or all on
 */
public class BerlinClockConstruction {

    private BerlinClock berlinClock = new BerlinClock(null);
    private BerlinClockRenderer berlinClockRenderer = new InterviewStoryRenderer();

    @Test
    public void berlinClockConstructionTests() throws Exception {
        aBehaviouralTestRunner()
                .usingStepsFrom(this)
                .withStory("berlin-clock-construction.story")
                .run();
    }

    @When("the lights are all $")
    public void whenTheLampsAreAll(String lightsStatus) throws Exception {
        switch (lightsStatus) {
            case "OFF":
                berlinClock.turnAllTheLampsOff();
                break;
            case "ON":
                berlinClock.turnAllTheLampsOn();
                break;
            default:
                throw new Exception("Unknown lights status");
        }
    }

    @Then("the clock should look like $")
    public void thenTheClockShouldLookLike(String theExpectedBerlinClockOutput) throws Exception {
        assertThat(berlinClockRenderer.render(berlinClock)).isEqualTo(theExpectedBerlinClockOutput);
    }

}
