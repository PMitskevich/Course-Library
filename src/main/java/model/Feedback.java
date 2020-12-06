package model;

public class Feedback {
    private long feedbackId;
    private long userId;
    private String feedback;

    public Feedback() {
    }

    public Feedback(long feedbackId, long userId, String feedback) {
        this.feedbackId = feedbackId;
        this.userId = userId;
        this.feedback = feedback;
    }

    public long getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(long feedbackId) {
        this.feedbackId = feedbackId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
