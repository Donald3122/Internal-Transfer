package task.internaltransfers.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto {

    private Response response;

    @Getter
    @Setter
    @ToString
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Response {

        private int status;
        private String comment;
        private String description;
        private List<AccountDto> accounts;

        public Response(int status, String comment) {
            this.status = status;
            this.comment = comment;
        }

        public Response(int status, String comment, String description, List<AccountDto> accounts) {
            this.status = status;
            this.comment = comment;
            this.description = description;
            this.accounts = accounts;
        }
    }
}
