package Entities.M06_DataOrigin;

import Entities.M02_Company.Company;

public class PathHandler {
    /**
     *
     * @param company objeto compania
     * @return direccion de path generada para la compania senalada
     */
    public String generatePath(Company company){
        return company.get_name().replaceAll("\\s","") + "_" + Encrypter.getCurrentDatePathFormat();
    }
}
