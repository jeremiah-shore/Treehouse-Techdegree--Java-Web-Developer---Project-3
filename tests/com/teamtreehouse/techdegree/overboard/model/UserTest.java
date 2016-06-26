package com.teamtreehouse.techdegree.overboard.model;

import com.teamtreehouse.techdegree.overboard.exc.AnswerAcceptanceException;
import com.teamtreehouse.techdegree.overboard.exc.VotingException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * Created by Jeremiah on 6/25/2016.
 */
public class UserTest {
    private Board board;
    private User user;
    private Question question; //extends post, which is abstract
    private User otherUser;
    private Answer answer;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        //extra credit: Create additional objects which can be shared across tests.
        board = new Board("Java");
        user = board.createUser("Jeremiah");
        question = user.askQuestion("Why is unit testing so important to software development?");
        otherUser = board.createUser("Craig");
        answer = otherUser.answerQuestion(question, "There's plenty of reasons. Pick one bro!");
    }

    @Test
    public void questionerReputationIncreasesIfQuestionIsUpvoted() {
        //the questioner’s reputation goes up by 5 points if their question is upvoted
        otherUser.upVote(question);

        assertEquals(5, user.getReputation());
    }

    @Test
    public void answererReputationIncreasesIfAnswerIsUpvoted() {
        //the answerer’s reputation goes up by 10 points if their answer is upvoted
        user.upVote(answer);

        assertEquals(10, otherUser.getReputation());
    }

    @Test
    public void answerAcceptedIncreasesAnswererReputation() {
        //having an answer accepted gives the answerer a 15 point reputation boost
        user.acceptAnswer(answer);

        assertEquals(15, otherUser.getReputation());
    }

    @Test (expected = VotingException.class) //TODO: refactor to test the error message
    public void votingNotAllowedOnQuestionsByAuthor() throws Exception {
        //voting either up or down is not allowed on questions by the original author
        user.upVote(question);
    }

    @Test (expected = VotingException.class) //TODO: refactor to test the error message
    public void votingNotAllowedOnQAnswersByAuthor() throws Exception {
        //voting either up or down is not allowed on answers by the original author
        otherUser.upVote(answer);
    }

    @Test
    public void onlyOriginalQuestionerCanAcceptAnAnswer() throws Exception {
        //only the original questioner can accept an answer; ensure the intended messaging is being sent to back to the caller.
        thrown.expect(AnswerAcceptanceException.class);
        thrown.expectMessage("Only Jeremiah can accept this answer as it is their question");

        otherUser.acceptAnswer(answer);
    }

    //todo: extra credit: Reviewing the User.getReputation method may expose some code that is not requested to be tested in the Meets Project instructions. Write the missing test.
}