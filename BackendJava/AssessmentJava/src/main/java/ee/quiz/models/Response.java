package ee.quiz.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Response {
    private Long id;
    private String text;
    private boolean isCorrect;
    private Long questionId;

    public boolean getIsCorrect() {
        return isCorrect;
    }
}
