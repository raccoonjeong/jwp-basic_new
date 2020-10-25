package next.model;

import java.util.Date;
import java.util.Objects;

public class Answer {
    Long answerId;
    String writer;
    String contents;
    Date createdDate;
    Long questionId;

    public long getTimeFromCreateDate() {
        return this.createdDate.getTime();
    }

    public Answer(Long answerId, String writer, String contents,
                  Date createdDate, Long questionId) {
        this.answerId = answerId;
        this.writer = writer;
        this.contents = contents;
        this.createdDate = createdDate;
        this.questionId = questionId;
    }

    public Answer(String writer, String contents,
                  Long questionId) {
        this(0L, writer, contents, new Date(), questionId);
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return Objects.equals(answerId, answer.answerId) &&
                Objects.equals(writer, answer.writer) &&
                Objects.equals(contents, answer.contents) &&
                Objects.equals(createdDate, answer.createdDate) &&
                Objects.equals(questionId, answer.questionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answerId, writer, contents, createdDate, questionId);
    }

    @Override
    public String toString() {
        return "Answer{" +
                "answerId=" + answerId +
                ", writer='" + writer + '\'' +
                ", contents='" + contents + '\'' +
                ", createdDate=" + createdDate +
                ", questionId=" + questionId +
                '}';
    }
}
