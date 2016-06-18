Story: The Berlin Clock construction story

Meta:
@scope interview

Narrative:
    As a clock maker
    I want to check if the Berlin Clock and all the lights are installed properly
    So that someone else can implements the engine to light them up without worries.

Scenario: All switched off
When the the lights are all OFF
Then the clock should look like
O
OOOO
OOOO
OOOOOOOOOOO
OOOO

Scenario: All switched on
When the the lights are all ON
Then the clock should look like
Y
RRRR
RRRR
YYRYYRYYRYY
YYYY



