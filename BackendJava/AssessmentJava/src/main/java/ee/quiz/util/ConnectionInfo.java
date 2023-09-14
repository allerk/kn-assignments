package ee.quiz.util;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class ConnectionInfo {
    String url;
    String user;
    String pass;
}
