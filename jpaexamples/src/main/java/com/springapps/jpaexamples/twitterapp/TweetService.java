package com.springapps.jpaexamples.twitterapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TweetService {
    //dependinte:
    TweetRepository tweetRepository;
    CommentRepository commentRepository;

    @Autowired//injectez dependintele in bean
    public TweetService(TweetRepository tweetRepository, CommentRepository commentRepository) {
        this.tweetRepository = tweetRepository;
        this.commentRepository = commentRepository;
    }

    //adaug un comentariu la un tweet:
    @Transactional
    public Comment addCommentToTweet(Long tweetId, Comment comment) throws Exception {
        Tweet tweet = tweetRepository.findById(tweetId).orElseThrow(() -> new Exception("tweet not found"));
        comment.setTweet(tweet);//pun cheia straina tweet in tabelul comment(leg comment de tweet)
        return commentRepository.save(comment); //salvez comentul
    }

    @Transactional
    public Tweet addCommentToTweet2(Long tweetId, Comment comment) throws Exception {
        Tweet tweet = tweetRepository.findById(tweetId).orElseThrow(() -> new Exception("tweet not found"));
        comment.setTweet(tweet);//setez cheia straina
        tweet.getComments().add(comment);//updatez tweet-ul:adaug comment in lista de comment a tweet-ului
        return tweetRepository.save(tweet);//salvez tweet
    }

    public Tweet findByText(String text) {
        return tweetRepository.findByText(text);
    }

    //sterg toate comentariile de la un tweet:
    public void deleteAllCommentFromTweet(Long tweetId) {
        commentRepository.deleteAllByTweet_Id(tweetId);
    }

    //sterg un anumit comnetariu de la un tweet:
    public void deleteTweet(Long tweetId) {
        tweetRepository.deleteById(tweetId);
    }
}
