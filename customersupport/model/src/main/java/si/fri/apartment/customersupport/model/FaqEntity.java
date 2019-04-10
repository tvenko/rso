package si.fri.apartment.customersupport.model;

import javax.persistence.*;

@Entity(name = "faq")
@NamedQueries({
        @NamedQuery(name = "FAQ.getAll", query = "SELECT e FROM faq e"),
})
public class FaqEntity {

    private long id;
    private String question;
    private String answer;
    private String complain;

    public FaqEntity() {}

    public FaqEntity(String question, String answer, String complain) {
        this.question = question;
        this.answer = answer;
        this.complain = complain;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "question", nullable = false, length = 1000)
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Basic
    @Column(name = "answer", nullable = false, length = 10000)
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Basic
    @Column(name = "complain", nullable = false, length = 10000)
    public String getComplain() {
        return complain;
    }

    public void setComplain(String complain) {
        this.complain = complain;
    }

    public String toString() {
        return String.format("%d %s %s %s \n", id, question, answer, complain);
    }
}
