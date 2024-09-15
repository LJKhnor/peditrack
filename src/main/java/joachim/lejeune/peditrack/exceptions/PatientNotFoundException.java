package joachim.lejeune.peditrack.exceptions;

public class PatientNotFoundException extends Throwable{
    public PatientNotFoundException(String message) {
        super(message);
    }
}
