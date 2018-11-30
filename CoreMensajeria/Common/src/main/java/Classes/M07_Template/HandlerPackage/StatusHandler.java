package Classes.M07_Template.HandlerPackage;

import Classes.Sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StatusHandler {
    private Sql sql;

    public Sql getSql() {
        return sql;
    }

    public static Boolean postTemplateStatusAprovado(int templateId,int userId){
        Boolean flag=false;
        Connection con = Sql.getConInstance();
        String query="insert into public.template_status (ts_date,ts_template,ts_user_id,ts_status) values (CURRENT_TIMESTAMP," + templateId + "," + userId + ",(select sta_id from public.status where sta_name='Aprobado'))";
        return postTemplateStatus(flag, con, query);
    }

    public static Boolean postTemplateStatusNoAprovado(int templateId){
        Boolean flag=false;
        Connection con = Sql.getConInstance();
        String query="insert into public.template_status (ts_date,ts_template,ts_status) values (CURRENT_TIMESTAMP,"+templateId+",(select sta_id from public.status where sta_name='No Aprobado'))";
        return postTemplateStatus(flag, con, query);
    }

    private static Boolean postTemplateStatus(Boolean flag, Connection con, String query) {
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.executeUpdate();
            flag=true;
        }  catch(SQLException e){
            e.printStackTrace();
            con.rollback();
            flag=false;
        } catch (Exception e){
            e.printStackTrace();
            flag=false;
        } finally {
            if (con != null) {
                Sql.bdClose(con);
            }
            return flag;
        }
    }

}
