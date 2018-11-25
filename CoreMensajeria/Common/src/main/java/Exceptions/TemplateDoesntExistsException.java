package Exceptions;

public class TemplateDoesntExistsException extends Exception{
    private int idTemplate;

    public TemplateDoesntExistsException() {
        super();
    }

    public TemplateDoesntExistsException(Exception e) {
        super(e);
    }

    public TemplateDoesntExistsException(String message, Exception e) {
        super(message, e);
    }

    public TemplateDoesntExistsException(String message, Exception e, int idTemplate) {
        super(message, e);
        this.idTemplate = idTemplate;
    }

    public int getIdTemplate() {
        return idTemplate;
    }
}
