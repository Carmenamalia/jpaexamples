package com.springapps.jpaexamples.twitterapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService {
    UserRepository userRepository;
    TweetRepository tweetRepository;
    CommentRepository commentRepository;

    @Autowired
    public UserService(UserRepository userRepository, TweetRepository tweetRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.tweetRepository = tweetRepository;
        this.commentRepository = commentRepository;
    }

    //Adaug un user:
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    //Updatez un user:
    @Transactional
    public Tweet addTweetToUser2(Long userId, Tweet tweet) throws Exception {
        //caut userul in baza de date:
        User user = userRepository.findById(userId).orElseThrow(() -> new Exception("user not found"));
        tweet.setUser(user);//seteaza cheia straina(user_id) din tabelul tweet
//        user.getTweets().add(tweet);
//        Tweet dbTweet =  tweetRepository.save(tweet);
//        List<Tweet> tweets = user.getTweets();
//        return dbTweet;
        return tweetRepository.save(tweet);//salvez tweet in tabelul tweet
    }

    @Transactional
    public User addTweetToUser1(Long userId, Tweet tweet) throws Exception {
        //caut userul in baza de date:
        User user = userRepository.findById(userId).orElseThrow(() -> new Exception("user not found"));
        tweet.setUser(user);//seteaza cheia straina(user_id) din tabelul tweet
        user.getTweets().add(tweet);//adaug tweet
        return userRepository.save(user);//operatia e de update a userului si cascadarea face ca sa salveze si tweet-ul
    }
    //sterg toate tweet-urile de la un user:
    @Transactional
    public void deleteAllTweetsFromUser(Long userId) throws Exception {
        //metoda asta e mai eficienta pt ca nu am query cu db
        tweetRepository.deleteAllByUser_Id(userId);
    }

    //sterg toate tweet-urile de la un user:
    @Transactional
    public void deleteAllTweetsFromUser1(Long userId) throws Exception {
        User user = userRepository.findById(userId).orElseThrow(() -> new Exception("user not found"));
        tweetRepository.deleteAllInBatch(user.getTweets());
    }

    @Transactional
    public User deleteAllTweetsFromUser2(Long userId) throws Exception {
        //caut userul in baza de date:
        User user = userRepository.findById(userId).orElseThrow(() -> new Exception("user not found"));
        user.getTweets().clear();
        return userRepository.save(user);
    }

    //sterg un user (impreuna cu tweet-urile lui):
    public void deleteUser(Long userId) throws Exception {
        //caut userul in baza de date:
        User user = userRepository.findById(userId).orElseThrow(() -> new Exception("user not found"));
        userRepository.delete(user);
    }

    public List<Tweet> findAllTweetByUser(Long userId) {
        return tweetRepository.findAllByUser_Id(userId);
    }


    public List<Comment> findAllCommentsByUser(Long userId) {
        return commentRepository.findAllByTweet_User_Id(userId);

    }

    public List<Comment> findAllCommentsByUser2(Long userId) {
        //pasul 1 --obtinem lista de tweeturi a userului cu user id
        //var 1 - apelam metoda care foloseste un derived query declarat in repository
        List<Tweet> tweets = findAllTweetByUser(userId);
        //var2 - cautam userul si prin lazy loading ii accesam tweeturile
//        User user = userRepository.findById(userId).orElseThrow(() -> new Exception("user not found"));
//        user.getTweets();
        // pasul 2 - procesam tweeturile gasite astfel ca obtinem o lista de comm adunata de la fiecare tweet
        //var 1 - java 8 - stream pe tweets si flatMAp
        return tweets.stream().flatMap(t -> t.getComments().stream()).collect(Collectors.toList());
        //var 2 - parcurgere clasica si adaugarea elementelor intr-un array result
//        List<Comment> result = new ArrayList<>();
//        for (Tweet tweet:tweets){
//            result.addAll(tweet.getComments());
//        }
//        return result;
    }

    @Transactional
    public List<Comment> findAllCommentsByUser3(Long userId) {
        List<Comment> comments = commentRepository.findAll();
//        List<Comment> result = new ArrayList<>();
//        for (Comment comment : comments) {
//            if (comment.getTweet().getUser().getMovieId().equals(userId)){
//                result.add(comment);
//            }
//        }
//        return result;
        return comments.stream().filter(comment -> comment.getTweet().getUser().getId().equals(userId)).collect(Collectors.toList());
    }
}
