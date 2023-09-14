package ee.quiz.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Question {
    private Long id;
    private Long difficultyRank;
    private String topic;
    private String content;
    private boolean isManyCorrectResponsesAllowed;
    private Long quizId;
    private List<Response> responses;

    public boolean getIsManyCorrectResponsesAllowed() {
        return isManyCorrectResponsesAllowed;
    }
}
