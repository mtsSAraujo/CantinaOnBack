package gov.fatec.manumanager.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponseBody {

    private int status;

    private String path;

    private String timeStamp;

    private List<String> errors = new ArrayList<>();

    public void addError(String message){
        this.errors.add(message);
    }

}
