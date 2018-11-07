package Classes.M07_Template.HandlerPackage;

import Classes.M07_Template.Template;
import Classes.Sql;

import java.sql.*;
import java.util.ArrayList;

public class TemplateHandler {
    public Sql sql;

    public TemplateHandler() {
        sql = new Sql();
    }

    public ArrayList<Template> getTemplates() {
        ArrayList<Template> templateArrayList = new ArrayList<>();
        try {
            ResultSet resultSet = sql.sqlConn("SELECT * FROM PUBLIC.TEMPLATE");
            while (resultSet.next()){
                Template template = new Template();
                template.setTemplateId(resultSet.getInt("tem_id"));
                template.setCreationDate(resultSet.getString("tem_creation_date"));
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

    public void postTemplateStatus(int id){
        Connection con = Sql.getConInstance();
        String query="insert into public.template_status (ts_date,ts_template,ts_status) values (CURRENT_TIMESTAMP,"+id+",(select sta_id from public.status where sta_name='aprobado'))";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.executeUpdate();
        }  catch(SQLException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        Sql.bdClose(con);
    }
}
