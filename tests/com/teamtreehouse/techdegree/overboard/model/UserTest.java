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

    @Test (expected = VotingException.class)
    public void votingUpNotAllowedOnQuestionsByAuthor() throws Exception {
        user.upVote(question);
    }

    @Test (expected = VotingException.class)
    public void votingDownNotAllowedOnQuestionsByAuthor() throws Exception {
        user.downVote(question);
    }

    @Test (expected = VotingException.class)
    public void votingUpNotAllowedOnAnswersByAuthor() throws Exception {
        otherUser.upVote(answer);
    }

    @Test (expected = VotingException.class)
    public void votingDownNotAllowedOnAnswersByAuthor() throws Exception {
        otherUser.downVote(answer);
    }

    /* The four tests above are very specific and explicitly state what is being tested. I find this to be preferable to
       the consolidated code below, as the four separate tests explain what is not allowed more comprehensively.

    @Test (expected = VotingException.class)
    public void votingUpNotAllowedOnPostByAuthor() throws Exception {
        user.upVote(question);
    }

    @Test (expected = VotingException.class)
    public void votingDownNotAllowedOnPostByAuthor() throws Exception {
        user.downVote(question);
    }

     */

    @Test
    public void onlyOriginalQuestionerCanAcceptAnAnswer() throws Exception {
        //only the original questioner can accept an answer; ensure the intended messaging is being sent to back to the caller.
        thrown.expect(AnswerAcceptanceException.class);
        thrown.expectMessage("Only Jeremiah can accept this answer as it is their question");

        otherUser.acceptAnswer(answer);
    }

    @Test
    public void havingAnswersDownVotedDecreasesVoterReputation() throws Exception {
        user.acceptAnswer(answer); //increases rep for the answerer
        user.downVote(answer); //should decrease answerer rep

        assertEquals(14, otherUser.getReputation());
    }
}