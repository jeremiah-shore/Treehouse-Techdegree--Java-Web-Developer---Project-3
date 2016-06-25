package com.teamtreehouse.techdegree.overboard.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Jeremiah on 6/25/2016.
 */
public class UserTest {

    @Before
    public void setUp() throws Exception {
        //extra credit: Create additional objects which can be shared across tests.
    }

    @Test
    public void questionerReputationIncreasesIfQuestionIsUpvoted() {
        //the questioner’s reputation goes up by 5 points if their question is upvoted
    }

    @Test
    public void answererReputationIncreasesIfAnswerIsUpvoted() {
        //the answerer’s reputation goes up by 10 points if their answer is upvoted
    }

    @Test
    public void answerAcceptedIncreasesAnswererReputation() {
        //having an answer accepted gives the answerer a 15 point reputation boost
    }

    @Test
    public void votingNotAllowedOnQuestionsByAuthor() throws Exception {
        //voting either up or down is not allowed on questions by the original author
    }

    @Test
    public void votingNotAllowedOnQAnswersByAuthor() throws Exception {
        //voting either up or down is not allowed on answers by the original author
    }

    @Test
    public void onlyOriginalQuestionerCanAcceptAnAnswer() throws Exception {
        //only the original questioner can accept an answer; ensure the intended messaging is being sent to back to the caller.
    }

    //todo: extra credit: Reviewing the User.getReputation method may expose some code that is not requested to be tested in the Meets Project instructions. Write the missing test.
}