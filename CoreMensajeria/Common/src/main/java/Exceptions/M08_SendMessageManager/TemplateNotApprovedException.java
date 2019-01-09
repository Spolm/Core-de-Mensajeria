package Exceptions.M08_SendMessageManager;

import Exceptions.PersonalizedException;

/**
 * Excepción personalizada que se ejecuta cuando la plantilla
 * no esta en estado de aprobada
 *
 * @see PersonalizedException
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
