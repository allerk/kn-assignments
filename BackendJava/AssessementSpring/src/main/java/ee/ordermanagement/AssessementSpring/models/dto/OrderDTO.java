package ee.ordermanagement.AssessementSpring.models.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class OrderDTO {

    private Long id;

    private LocalDate dateOfSubmission;

    private List<OrderLineDTO> orderLines;

    private Long customer_id;
}
