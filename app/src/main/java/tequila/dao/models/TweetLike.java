package tequila.dao.models;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit.
/**
 * Entity mapped to table "TWEET_LIKE".
 */
public class TweetLike {

    private Long id;
    private String type;
    private Boolean cancelled;
    private java.util.Date createdAt;
    private long tweetId;

    public TweetLike() {
    }

    public TweetLike(Long id) {
        this.id = id;
    }

    public TweetLike(Long id, String type, Boolean cancelled, java.util.Date createdAt, long tweetId) {
        this.id = id;
        this.type = type;
        this.cancelled = cancelled;
        this.createdAt = createdAt;
        this.tweetId = tweetId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getCancelled() {
        return cancelled;
    }

    public void setCancelled(Boolean cancelled) {
        this.cancelled = cancelled;
    }

    public java.util.Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(java.util.Date createdAt) {
        this.createdAt = createdAt;
    }

    public long getTweetId() {
        return tweetId;
    }

    public void setTweetId(long tweetId) {
        this.tweetId = tweetId;
    }

}
