package ee.quiz.jdbc;

import ee.quiz.models.Question;
import ee.quiz.models.Response;
import ee.quiz.util.ConnectionInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class QuestionDAO {
    private final ConnectionInfo connectionInfo;

    public QuestionDAO(ConnectionInfo connectionInfo){
        this.connectionInfo = connectionInfo;
    }

    public Question postQuestion(Question question){

        String sql = "insert into questions (difficultyRank, topic, content, isManyCorrectResponsesAllowed, quiz_id) values (?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, new String[] {"id"})) {

            ps.setLong(1, question.getDifficultyRank());
            ps.setString(2, question.getTopic());
            ps.setString(3, question.getContent());
            ps.setBoolean(4, question.getIsManyCorrectResponsesAllowed());
            ps.setLong(5, question.getQuizId());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (!rs.next()){
                throw new RuntimeException("unexpected");
            }

            return new Question(
                    rs.getLong("id"),
                    question.getDifficultyRank(),
                    question.getTopic(),
                    question.getContent(),
                    question.getIsManyCorrectResponsesAllowed(),
                    question.getQuizId(),
                    question.getResponses());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Question updateQuestion(Question question, Long id){
        String sql = "update questions set difficultyRank = ?, topic = ?, content = ?, isManyCorrectResponsesAllowed = ?, quiz_id = ? where id = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, new String[] {"id"})) {

            ps.setLong(1, question.getDifficultyRank());
            ps.setString(2, question.getTopic());
            ps.setString(3, question.getContent());
            ps.setBoolean(4, question.getIsManyCorrectResponsesAllowed());
            ps.setLong(5, question.getQuizId());
            ps.setLong(6, id);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (!rs.next()){
                throw new RuntimeException("unexpected");
            }

            return new Question(
                    rs.getLong("id"),
                    question.getDifficultyRank(),
                    question.getTopic(),
                    question.getContent(),
                    question.getIsManyCorrectResponsesAllowed(),
                    question.getQuizId(),
                    question.getResponses());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Question> getAllQuestions(){

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {

            ResultSet resultSet = stmt.executeQuery("select questions.id as q_id, difficultyRank, topic, content, isManyCorrectResponsesAllowed, quiz_id as qu_id, responses.id as r_id, text, isCorrect from questions left join responses on questions.id = responses.question_id");

            Map<Long, Question> questionMap = new HashMap<>();

            while (resultSet.next()){
                Long questionId = resultSet.getLong("q_id");

                if (!questionMap.containsKey(questionId)) {
                    Question question = new Question(
                            questionId,
                            resultSet.getLong("difficultyRank"),
                            resultSet.getString("topic"),
                            resultSet.getString("content"),
                            resultSet.getBoolean("isManyCorrectResponsesAllowed"),
                            resultSet.getLong("qu_id"),
                            new ArrayList<>()
                    );
                    questionMap.put(questionId, question);
                }

                if (resultSet.getLong("r_id") != 0){
                    Response response = new Response(
                            resultSet.getLong("r_id"),
                            resultSet.getString("text"),
                            resultSet.getBoolean("isCorrect"),
                            questionId
                    );

                    questionMap.get(questionId).getResponses().add(response);
                }
            }

            return new ArrayList<>(questionMap.values());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Question getQuestionByTopic(String topicName){

        String sql = "select questions.id as q_id, difficultyRank, topic, content, isManyCorrectResponsesAllowed, quiz_id as qu_id, responses.id as r_id, text, isCorrect from questions left join responses on questions.id = responses.question_id where topic = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, topicName);
            ResultSet resultSet = pstmt.executeQuery();

            Question question = null;

            while (resultSet.next()){
                if (question == null){
                    question = new Question(
                            resultSet.getLong("q_id"),
                            resultSet.getLong("difficultyRank"),
                            resultSet.getString("topic"),
                            resultSet.getString("content"),
                            resultSet.getBoolean("isManyCorrectResponsesAllowed"),
                            resultSet.getLong("qu_id"),
                            new ArrayList<>()
                    );
                }
                Long questionId = resultSet.getLong("q_id");

                if (resultSet.getLong("r_id") != 0){
                    Response response = new Response(
                            resultSet.getLong("r_id"),
                            resultSet.getString("text"),
                            resultSet.getBoolean("isCorrect"),
                            questionId
                    );

                    question.getResponses().add(response);
                }
            }

            return question;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Question getQuestionById(Long id){

        String sql = "select questions.id as q_id, difficultyRank, topic, content, isManyCorrectResponsesAllowed, quiz_id as qu_id, responses.id as r_id, text, isCorrect from questions left join responses on questions.id = responses.question_id where questions.id = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            ResultSet resultSet = pstmt.executeQuery();

            Question question = null;

            while (resultSet.next()){
                if (question == null){
                    question = new Question(
                            resultSet.getLong("q_id"),
                            resultSet.getLong("difficultyRank"),
                            resultSet.getString("topic"),
                            resultSet.getString("content"),
                            resultSet.getBoolean("isManyCorrectResponsesAllowed"),
                            resultSet.getLong("qu_id"),
                            new ArrayList<>()
                    );
                }
                Long questionId = resultSet.getLong("q_id");

                if (resultSet.getLong("r_id") != 0){
                    Response response = new Response(
                            resultSet.getLong("r_id"),
                            resultSet.getString("text"),
                            resultSet.getBoolean("isCorrect"),
                            questionId
                    );

                    question.getResponses().add(response);
                }
            }

            return question;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteQuestion(long deleteId){
        String sql = "DELETE FROM questions WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setLong(1, deleteId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting question with ID " + deleteId, e);
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                connectionInfo.getUrl(),
                connectionInfo.getUser(),
                connectionInfo.getPass());
    }
}
