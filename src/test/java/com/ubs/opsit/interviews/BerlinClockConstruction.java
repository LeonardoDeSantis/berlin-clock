package com.ubs.opsit.interviews;

import com.ubs.opsit.interviews.model.BerlinClock;
import com.ubs.opsit.interviews.rendering.BerlinClockRenderer;
import com.ubs.opsit.interviews.rendering.InterviewStoryRenderer;
import static com.ubs.opsit.interviews.support.BehaviouralTestEmbedder.aBehaviouralTestRunner;
import static org.assertj.core.api.Assertions.assertThat;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Test;

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
    public void whenTheLightsAreAll(String lightsStatus) throws Exception {
        switch (lightsStatus) {
            case "OFF": berlinClock.turnAllTheLightsOff();
            break;
            case "ON": berlinClock.turnAllTheLightsOn();
            break;
            default: throw new Exception("Unknown lights status");
        }        
    }
    
    @Then("the clock should look like $")
    public void thenTheClockShouldLookLike(String theExpectedBerlinClockOutput) throws Exception {
        assertThat(berlinClockRenderer.render(berlinClock)).isEqualTo(theExpectedBerlinClockOutput);
    }
    
}
