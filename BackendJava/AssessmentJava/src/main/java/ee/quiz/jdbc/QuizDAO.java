package ee.quiz.jdbc;

import ee.quiz.models.Question;
import ee.quiz.models.Quiz;
import ee.quiz.util.ConnectionInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuizDAO {

    private final ConnectionInfo connectionInfo;

    public QuizDAO(ConnectionInfo connectionInfo){
        this.connectionInfo = connectionInfo;
    }

    public Quiz postQuiz(Quiz quiz){

        String sql = "insert into quiz (name) values (?)";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, new String[] {"id"})) {

            ps.setString(1, quiz.getName());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (!rs.next()){
                throw new RuntimeException("unexpected");
            }

            return new Quiz(
                    rs.getLong("id"),
                    quiz.getName(),
                    quiz.getQuestions());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Quiz> getAllQuizes(){

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {

            ResultSet resultSet = stmt.executeQuery("select id as q_id, name from quiz");

            Map<Long, Quiz> questionMap = new HashMap<>();

            while (resultSet.next()){
                Long quizId = resultSet.getLong("q_id");

                if (!questionMap.containsKey(quizId)) {
                    Quiz quiz = new Quiz(
                            quizId,
                            resultSet.getString("name"),
                            new ArrayList<>()
                    );
                    questionMap.put(quizId, quiz);
                }

//                if (resultSet.getLong("r_id") != 0){
//                    Response response = new Response(
//                            resultSet.getLong("r_id"),
//                            resultSet.getString("text"),
//                            resultSet.getBoolean("isCorrect"),
//                            questionId
//                    );
//
//                    questionMap.get(questionId).getResponses().add(response);
//                }
            }

            return new ArrayList<>(questionMap.values());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Quiz getQuizById(Long id){

        String sql = "select quiz.id as qui_id, name, questions.id as q_id, difficultyRank, topic, content, isManyCorrectResponsesAllowed, quiz_id from quiz left join questions on quiz.id = questions.quiz_id where quiz.id = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            ResultSet resultSet = pstmt.executeQuery();

            Quiz quiz = null;

            while (resultSet.next()){
                if (quiz == null){
                    quiz = new Quiz(
                            resultSet.getLong("qui_id"),
                            resultSet.getString("name"),
                            new ArrayList<>()
                    );
                }
                Long qui_id = resultSet.getLong("qui_id");

                if (resultSet.getLong("q_id") != 0){
                    Question question = new Question(
                            resultSet.getLong("q_id"),
                            resultSet.getLong("difficultyRank"),
                            resultSet.getString("topic"),
                            resultSet.getString("content"),
                            resultSet.getBoolean("isManyCorrectResponsesAllowed"),
                            qui_id,
                            new ArrayList<>()
                    );

                    quiz.getQuestions().add(question);
                }
            }

            return quiz;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                connectionInfo.getUrl(),
                connectionInfo.getUser(),
                connectionInfo.getPass());
    }
}
