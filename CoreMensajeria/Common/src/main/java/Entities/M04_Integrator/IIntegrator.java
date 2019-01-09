package Entities.M04_Integrator;

/**
* Interfaz que contiene los métodos de utilizarán
* todos los integradores en común.
*
* @author Kevin Martinez
* @author Braulio Picon
* @author Alexander Fernandez
* @see IIntegrator
*/
public interface IIntegrator {
    MessageIntegrator sendMessage(String msg, String address, String idMsg);
}
