package Exceptions;

/**
 * Excepción personalizada para manejar la plantilla no aprobada
 */

public class TemplateNotApprovedException extends PersonalizedException {

    /**
     * Constructor vacío.
     */

    public TemplateNotApprovedException() {
        ERROR_CODE = 563;
        ERROR_MSG = "Ha ocurrido un errror "+ERROR_CODE+" la plantilla no está aprobada";
    }
}
