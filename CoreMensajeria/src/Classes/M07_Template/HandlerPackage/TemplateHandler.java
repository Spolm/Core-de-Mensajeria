package Classes.M07_Template.HandlerPackage;

import Classes.M07_Template.Template;
import Classes.Sql;

import java.sql.ResultSet;
import java.sql.SQLException;
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
            ResultSet resultSet = sql.sqlConn("SELECT * FROM PUBLIC.TEMPLATE WHERE ID = "+id);
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
}
