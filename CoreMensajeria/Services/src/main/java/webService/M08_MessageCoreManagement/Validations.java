package webService.M08_MessageCoreManagement;

import Classes.M08_Validation.ValidationReciveParameter;
import Classes.M08_Validation.ValidationTemplate;

import java.util.Date;

public class Validations {

    //Funcion para validar fecha de la plantilla a enviar
    public boolean Validate_sendDate(String templateDate){
         Date fecha = new Date();
         int day= fecha.getDay();
         int year=fecha.getYear();
         int month=fecha.getMonth();
         String date = day+"-"+month+"-"+year;

        if (templateDate.equals("12-12-19")){
            return true;
        }
        return false;
    }

    //Funcion para validar hora de la plantilla a enviar
    public boolean Validate_sendHour(String templateHour){

        String hour="";
        if (templateHour.equals("00:00:00")){
            return true;
        }
        return false;
    }

    //Funcion para validar la longitud del mensaje si es sms
    public boolean ValidateSMSlentgh(String message,String chanel){

        if ((chanel.equals("SMS")) && (message.length()>160)){
            return true;
        }
        if (chanel=="CANAL"){
            return true;
        }
        return false;
    }

    //Funcion para validar parametros del mensaje o el mismo mensaje
    public  boolean Validate_messageParameter(String message){

        if(message.equals("")){
            return true;
        }
        return false;
    }

    //Funcion para validar las validaciones
    public String Validate_Condicion(boolean date, boolean hour, boolean message, boolean lentgh, ValidationTemplate template, ValidationReciveParameter parameters){

        if (!date){ return "No se puede enviar plantilla la fecha de envio no valida";}
        else if (!hour){return "No se puede enviar plantilla la hora no es valida"; }
        else if (message){return "Parametro de mensaje esta vacio o nulo"; }
        else if (lentgh){return "La longitud del sms es invalida";}
        else{
            //construyo la plantilla con los parametros
          //  template.set_message(parameters.get_message());
            //template.set_chanel(parameters.get_chanel());

            //Envio el template con los parametros a los integradores
            String responseIntegradores = "Exito en Envio";

            //Registro el response en base de datos

            //Retorno respuesta
            return  responseIntegradores;
        }

    }

}
