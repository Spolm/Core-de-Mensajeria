package Factory;
import DTO.M02_DTO.DTOCompany;
import DTO.M03_DTO.DTOCampaign;

 /**
 * Fabrica que instancia todos los dto
 */
public class DTOFactory {

      /**
      * Crea objeto Company
      */
    public static DTOCompany CreateDtoCompany(){
        return new DTOCompany();
    }
      /**
      * Crea objeto Campaign
      */
    public static DTOCampaign CreateDtoCampaign() {
        return new DTOCampaign();
    }


}
