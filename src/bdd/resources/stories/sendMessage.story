Meta:

Narrative:
As a user
I want to perform an action
So that I can achieve a business goal

Scenario: the user u1 sends a message m1 for a group g1 and every member of g1 should be able to read the message
Given the new user(s) u1,u2,u3
And the new group g1 of 3 user(s) u2,u3 created by user u1
When the user u1 sends a message m1 for group g1
Then the users u1,u2,u3 of the group g1 should be able to read the message m1