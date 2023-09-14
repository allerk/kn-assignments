package ee.quiz;

import ee.quiz.jdbc.QuestionDAO;
import ee.quiz.jdbc.QuizDAO;
import ee.quiz.jdbc.ResponseDAO;
import ee.quiz.models.Question;
import ee.quiz.models.Quiz;
import ee.quiz.models.Response;
import ee.quiz.util.ConfigUtil;
import ee.quiz.util.ConnectionInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class QuestionDAOTest {

    ConnectionInfo connectionInfo = ConfigUtil.readConnectionInfo();
    QuestionDAO questionDAO;

    QuizDAO quizDAO;

    Quiz targetQuiz;

    @BeforeEach
    void setUp(){
        questionDAO = new QuestionDAO(connectionInfo);
        quizDAO = new QuizDAO(connectionInfo);
        targetQuiz = quizDAO.postQuiz(
                new Quiz(
                        null, "Testing Quiz", null
                )
        );
    }


    @Test
    void shouldSaveQuestionToDb(){
        Question question = new Question
                (
                        null,
                        5L,
                        "CS",
                        "What is testing?",
                        false,
                        targetQuiz.getId(),
                        new ArrayList<>()
                );
        Question posted = questionDAO.postQuestion(question);

        Assertions.assertEquals(posted.getId(), questionDAO.getQuestionById(posted.getId()).getId());
    }

    @Test
    void shouldGetQuestionWithResponses(){
        Question question = new Question
                (
                        null,
                        4L,
                        "With responses",
                        "What is testing?",
                        false,
                        targetQuiz.getId(),
                        new ArrayList<>()
                );
        Question postedQuestion = questionDAO.postQuestion(question);

        ArrayList<Response> responses = new ArrayList<>();
        responses.add(new Response(null, "response 1", true, postedQuestion.getId()));
        responses.add(new Response(null, "response 2", false, postedQuestion.getId()));
        responses.add(new Response(null, "response 3", false, postedQuestion.getId()));

        ResponseDAO responseDAO = new ResponseDAO(connectionInfo);

        for (Response response : responses) {
            responseDAO.postResponse(response);
        }

        Assertions.assertEquals(3, questionDAO.getQuestionById(postedQuestion.getId()).getResponses().size());
    }

    @Test
    void shouldUpdateQuestion(){

        Question question = new Question
                (
                        null,
                        4L,
                        "CS",
                        "What is testing?",
                        false,
                        targetQuiz.getId(),
                        new ArrayList<>()
                );
        Question postedQuestion = questionDAO.postQuestion(question);

        Question questionToUpdate = new Question(
                null,
                4L,
                "CS",
                "I know what is testing!",
                true,
                targetQuiz.getId(),
                new ArrayList<>()
        );
        questionDAO.updateQuestion(questionToUpdate, postedQuestion.getId());

        Assertions.assertEquals(
                "I know what is testing!",
                questionDAO.getQuestionById(postedQuestion.getId()).getContent()
        );
    }

    @Test
    void deleteQuestion(){
        Question question = new Question
                (
                        null,
                        4L,
                        "CS",
                        "To delete?",
                        false,
                        targetQuiz.getId(),
                        new ArrayList<>()
                );
        Question postedQuestion = questionDAO.postQuestion(question);

//        questionDAO.deleteQuestion(targetQuestion.getId());
        Assertions.assertDoesNotThrow(() -> questionDAO.deleteQuestion(postedQuestion.getId()));
    }

    @Test
    void shouldSearchQuestionByTopic(){
        Question question = new Question
                (
                        null,
                        4L,
                        "FIND ME",
                        "To delete? - Nah!",
                        false,
                        targetQuiz.getId(),
                        new ArrayList<>()
                );
        Question postedQuestion = questionDAO.postQuestion(question);

        Assertions.assertEquals(postedQuestion.getTopic(), questionDAO.getQuestionByTopic(postedQuestion.getTopic()).getTopic());
    }

    @Test
    void questionCanHaveMoreThanOneCorrectResponse(){
        Question question = new Question
                (
                        null,
                        4L,
                        "With responses",
                        "What is testing?",
                        false,
                        targetQuiz.getId(),
                        new ArrayList<>()
                );
        Question postedQuestion = questionDAO.postQuestion(question);

        ArrayList<Response> responses = new ArrayList<>();
        responses.add(new Response(null, "questionCanHaveMoreThanOneCorrectResponse 1", true, postedQuestion.getId()));
        responses.add(new Response(null, "questionCanHaveMoreThanOneCorrectResponse 2", true, postedQuestion.getId()));
        responses.add(new Response(null, "questionCanHaveMoreThanOneCorrectResponse 3", false, postedQuestion.getId()));

        ResponseDAO responseDAO = new ResponseDAO(connectionInfo);

        int correctCounter = 0;
        for (Response response : responses) {
            Response data = responseDAO.postResponse(response);
            if (responseDAO.getResponseById(data.getId()).getIsCorrect()){
                correctCounter++;
            }
        }

        Assertions.assertTrue(correctCounter > 1);
    }

    @Test
    void questionCantHaveMoreThanOneCorrectResponse(){
        Question question = new Question
                (
                        null,
                        4L,
                        "With responses",
                        "What is testing?",
                        false,
                        targetQuiz.getId(),
                        new ArrayList<>()
                );
        Question postedQuestion = questionDAO.postQuestion(question);

        ArrayList<Response> responses = new ArrayList<>();
        responses.add(new Response(null, "questionCanHaveMoreThanOneCorrectResponse 1", true, postedQuestion.getId()));
        responses.add(new Response(null, "questionCanHaveMoreThanOneCorrectResponse 2", false, postedQuestion.getId()));
        responses.add(new Response(null, "questionCanHaveMoreThanOneCorrectResponse 3", false, postedQuestion.getId()));

        ResponseDAO responseDAO = new ResponseDAO(connectionInfo);

        int correctCounter = 0;
        for (Response response : responses) {
            Response data = responseDAO.postResponse(response);
            if (responseDAO.getResponseById(data.getId()).getIsCorrect()){
                correctCounter++;
            }
        }

        Assertions.assertEquals(1, correctCounter);
    }
}
