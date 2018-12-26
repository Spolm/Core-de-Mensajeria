package Factory;
import DTO.M02_DTO.DTOCompany;
public class DTOFactory {


    public static DTOCompany CreateDtoCompany(){
        return new DTOCompany();
    }
}
