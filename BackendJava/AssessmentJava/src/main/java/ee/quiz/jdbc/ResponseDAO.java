package ee.quiz.jdbc;

import ee.quiz.models.Response;
import ee.quiz.util.ConnectionInfo;

import java.sql.*;

public class ResponseDAO {

    private final ConnectionInfo connectionInfo;

    public ResponseDAO(ConnectionInfo connectionInfo){
        this.connectionInfo = connectionInfo;
    }

    public Response postResponse(Response response){

        String sql = "insert into responses (text, isCorrect, question_id) values (?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, new String[] {"id"})) {

            ps.setString(1, response.getText());
            ps.setBoolean(2, response.getIsCorrect());
            ps.setLong(3, response.getQuestionId());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (!rs.next()){
                throw new RuntimeException("unexpected");
            }

            return new Response(
                    rs.getLong("id"),
                    response.getText(),
                    response.getIsCorrect(),
                    response.getQuestionId());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Response getResponseById(Long id){

        String sql = "select id, text, isCorrect, question_id from responses where id = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            ResultSet resultSet = pstmt.executeQuery();

            Response response = null;

            while (resultSet.next()){
                if (response == null){
                    response = new Response(
                            resultSet.getLong("id"),
                            resultSet.getString("text"),
                            resultSet.getBoolean("isCorrect"),
                            resultSet.getLong("question_id")
                    );
                }
//                Long questionId = resultSet.getLong("q_id");
//
//                if (resultSet.getLong("r_id") != 0){
//                    Response response = new Response(
//                            resultSet.getLong("r_id"),
//                            resultSet.getString("text"),
//                            resultSet.getBoolean("isCorrect"),
//                            questionId
//                    );
//
//                    question.getResponses().add(response);
//                }
            }

            return response;

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
