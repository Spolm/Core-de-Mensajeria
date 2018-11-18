package Classes.M07_Template.HandlerPackage;

import Classes.M07_Template.StatusPackage.ApprovedStatus;
import Classes.M07_Template.StatusPackage.IStatus;
import Classes.M07_Template.StatusPackage.NotApprovedStatus;
import Classes.M07_Template.Template;
import Classes.Sql;

import java.sql.*;
import java.util.ArrayList;

public class TemplateHandler {
    private Sql sql;

    public TemplateHandler() {
        sql = new Sql();
    }

    public Sql getSql() {
        return sql;
    }

    public ArrayList<Template> getTemplates() {
        ArrayList<Template> templateArrayList = new ArrayList<>();
        try {
            ResultSet resultSet = sql.sqlConn("SELECT * FROM PUBLIC.TEMPLATE");
            while (resultSet.next()){
                Template template = new Template();
                template.setTemplateId(resultSet.getInt("tem_id"));
                template.setCreationDate(resultSet.getString("tem_creation_date"));
                int i = template.getTemplateId();
                ResultSet resultSetAux = sql.sqlConn("SELECT * FROM TEMPLATE_STATUS WHERE TS_TEMPLATE = " +
                        template.getTemplateId() +
                        " ORDER BY TS_ID DESC LIMIT 1");
                resultSetAux.next();
                int statusId = resultSetAux.getInt("ts_status");
                template.setStatusId(statusId);
                /*resultSetAux = sql.sqlConn("SELECT * FROM STATUS WHERE STA_ID = " + statusId);
                String statusName = resultSetAux.getString("sta_name");*/
                templateArrayList.add(template);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return templateArrayList;
        }
    }

    public Template getTemplate(int id){
        Template template = new Template();
        try{
            ResultSet resultSet = sql.sqlConn("SELECT * FROM PUBLIC.TEMPLATE WHERE TEM_ID = "+id);
            resultSet.next();
            template.setTemplateId(resultSet.getInt("tem_id"));
            template.setCreationDate(resultSet.getString("tem_creation_date"));
        } catch(SQLException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            return template;
        }
    }

    public Boolean postTemplateStatus(int id){
        Boolean flag=false;
        Connection con = Sql.getConInstance();
        String query="insert into public.template_status (ts_date,ts_template,ts_status) values (CURRENT_TIMESTAMP,"+id+",(select sta_id from public.status where sta_name='Aprobado'))";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.executeUpdate();
            flag=true;
        }  catch(SQLException e){
            e.printStackTrace();
            flag=false;
        } catch (Exception e){
            e.printStackTrace();
            flag=false;
        }
        Sql.bdClose(con);
        return flag;
    }
}
