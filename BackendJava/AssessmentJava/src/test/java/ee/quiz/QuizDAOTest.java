package ee.quiz;

import ee.quiz.jdbc.QuestionDAO;
import ee.quiz.jdbc.QuizDAO;
import ee.quiz.models.Question;
import ee.quiz.models.Quiz;
import ee.quiz.util.ConfigUtil;
import ee.quiz.util.ConnectionInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;

public class QuizDAOTest {

    ConnectionInfo connectionInfo = ConfigUtil.readConnectionInfo();
    QuizDAO quizDAO;

    @BeforeEach
    void setUp(){
        quizDAO = new QuizDAO(connectionInfo);
    }

    @Test
    void shouldCreateQuiz(){
        Quiz quiz = new Quiz(null, "Testing Quiz", null);

        Quiz expected = quizDAO.postQuiz(quiz);

        Quiz actual = quizDAO.getQuizById(expected.getId());

        Assertions.assertEquals(expected.getName(), actual.getName());
    }

    @Test
    void quizShouldHaveNQuestions(){
        Quiz quiz = new Quiz(null, "Testing Quiz with N questions", new ArrayList<>());
        Quiz postedQuiz = quizDAO.postQuiz(quiz);

        ArrayList<Question> questions = new ArrayList<>();
        questions.add(new Question(null, 5L, "CS", "What is cs?", true, postedQuiz.getId(), new ArrayList<>()));
        questions.add(new Question(null, 4L, "CS", "What is cs?", false, postedQuiz.getId(), new ArrayList<>()));
        questions.add(new Question(null, 3L, "CS", "What is cs?", true, postedQuiz.getId(), new ArrayList<>()));

        QuestionDAO questionDAO = new QuestionDAO(connectionInfo);

        for (Question question : questions) {
            questionDAO.postQuestion(question);
        }

        Assertions.assertEquals(3, quizDAO.getQuizById(postedQuiz.getId()).getQuestions().size());
    }
}
