package com.bledi.android.Utilities;


import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public final class TwitterUtil {

    static TwitterUtil instance = new TwitterUtil();
    private RequestToken requestToken = null;
    private TwitterFactory twitterFactory = null;
    private Twitter twitter;

    public TwitterUtil() {
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setOAuthConsumerKey(ConstantValues.TWITTER_CONSUMER_KEY);
        configurationBuilder.setOAuthConsumerSecret(ConstantValues.TWITTER_CONSUMER_SECRET);
        Configuration configuration = configurationBuilder.build();
        twitterFactory = new TwitterFactory(configuration);
        twitter = twitterFactory.getInstance();
    }

    public TwitterFactory getTwitterFactory()
    {
        return twitterFactory;
    }

    public void setTwitterFactory(AccessToken accessToken)
    {
        twitter = twitterFactory.getInstance(accessToken);
    }

    public Twitter getTwitter()
    {
        return twitter;
    }
    public RequestToken getRequestToken() {
        if (requestToken == null) {
            try {
                requestToken = twitterFactory.getInstance().getOAuthRequestToken(ConstantValues.TWITTER_CALLBACK_URL);
            } catch (TwitterException e) {
                e.printStackTrace();  
            }
        }
        return requestToken;
    }


    public static TwitterUtil getInstance() {
        return instance;
    }


    public void reset() {
        instance = new TwitterUtil();
    }
}
