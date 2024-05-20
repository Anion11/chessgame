package Move;

public class MoveError {
    private Boolean notError;
    private String errorText = "Некорректный ход. Пожалуйста, попробуйте снова.";
    public Boolean getNotError() {
        return notError;
    }
    public void setNotError(Boolean notError) {
        this.notError = notError;
    }
    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }
    public String getErrorText() {
        return errorText;
    }
}
