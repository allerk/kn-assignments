package ee.ordermanagement.AssessementSpring.controllers.dto.post;

import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderDtoPost {

    private LocalDate dateOfSubmission;

    private Long customer_id;
}
