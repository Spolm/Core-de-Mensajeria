package Entities.M07_Template.HandlerPackage;

import Entities.Sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * StatusHandler class used for the management of information
 * search in the database with respect to Status.
 */
public class StatusHandler {
    /**
     * connection to the database
     */
    private Sql sql;

    /**
     * constructor without receiving parameters that instantiates
     * the sql attribute of the ParameterHandler class.
     */
    public Sql getSql() {
        return sql;
    }

    /**
     * method that is responsible for saving the approval of a template,
     * specifying user and template.
     * @param templateId template id
     * @param userId user id
     * @return If the template was saved successfully it returns true,
     * therwise it returns false.
     */
    public static Boolean postTemplateStatusAprovado(int templateId,int userId){
        Boolean flag=false;
        Connection con = Sql.getConInstance();
        String query="insert into public.template_status (ts_date,ts_template,ts_user_id,ts_status) values (CURRENT_TIMESTAMP," + templateId + "," + userId + ",(select sta_id from public.status where sta_name='Aprobado'))";
        return postTemplateStatus(flag, con, query);
    }

    /**
     * method that is responsible for saving the disapproval of a template,
     * specifying template.
     * @param templateId template id
     * @return If the template was saved successfully it returns true,
     * therwise it returns false.
     */
    public static Boolean postTemplateStatusNoAprovado(int templateId){
        Boolean flag=false;
        Connection con = Sql.getConInstance();
        String query="insert into public.template_status (ts_date,ts_template,ts_status) values (CURRENT_TIMESTAMP,"+templateId+",(select sta_id from public.status where sta_name='No Aprobado'))";
        return postTemplateStatus(flag, con, query);
    }

    /**
     * method that is responsible for saving the state of a specified template.
     * @param flag storage confirmation flag
     * @param con connection to the database
     * @param query string with the query
     * @return If the template was saved successfully it returns true,
     * herwise it returns false.
     */
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
