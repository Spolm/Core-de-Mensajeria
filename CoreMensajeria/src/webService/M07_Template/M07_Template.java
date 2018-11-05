package webService.M07_Template;

import Classes.Sql;
import com.google.gson.Gson;
import java.sql.Connection;

public class M07_Template {

    public Gson gson = new Gson();
    private Connection conn = Sql.getConInstance();
}
