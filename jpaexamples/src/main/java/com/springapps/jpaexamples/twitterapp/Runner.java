package com.springapps.jpaexamples.twitterapp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class Runner implements CommandLineRunner {

    UserService userService;
    TweetService tweetService;

    @Autowired
    public Runner(UserService userService, TweetService tweetService) {
        this.userService = userService;
        this.tweetService = tweetService;
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User("Costel");
        userService.saveUser(user);

        Tweet tweet = new Tweet("bitcoin is up");
        userService.addTweetToUser1(1L, tweet);

        Tweet foundTweet = tweetService.findByText("bitcoin is up");
        Comment comment = new Comment("ce fain");
        Comment comment1 = new Comment("dislike");
        tweetService.addCommentToTweet(foundTweet.getId(),comment);
        tweetService.addCommentToTweet(foundTweet.getId(),comment1);
        System.out.println(userService.findAllCommentsByUser(1L));

        // userService.deleteAllTweetsFromUser(1L);
        //userService.deleteAllTweetsFromUser1(1L);
        //userService.deleteUser(1L);
        //userService.deleteAllTweetsFromUser1(1L);
    }

}
