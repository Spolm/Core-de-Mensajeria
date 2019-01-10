package Logic.M06_Application;

import Entities.Entity;
import Entities.M02_Company.Company;
import Logic.M08_SendMessage.XMLManagment.WatchDirectory;
import Persistence.M02_Company.DAOCompany;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@WebListener
public class PathHandler implements ServletContextListener {

    private static String ORIGIN_HOME = System.getProperty("user.home") + "/CoreMensajeria";
    private static String ORIGIN_HOME_INBOX = ORIGIN_HOME + "/Inbox";
    private static String ORIGIN_HOME_TRASH = ORIGIN_HOME + "/Trash";
    private ArrayList<String> _paths = new ArrayList<>();

    public PathHandler() {
    }

    /**
     * Metodo que corre cuando se inicializa el servidor (crea los correspondientes directorios)
     * @param servletContextEvent el ServletContextEvent que esta siendo manejado
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        DAOCompany companyDAO = new DAOCompany();

        this.createDirectory(ORIGIN_HOME);
        this.createDirectory(ORIGIN_HOME_INBOX);
        this.createDirectory(ORIGIN_HOME_TRASH);

        try {
            this.createOriginDirectories(ORIGIN_HOME_INBOX, companyDAO.allCompanies());
            this.createOriginDirectories(ORIGIN_HOME_TRASH, companyDAO.allCompanies());
            System.out.println("--------------->PATHS CREATED");

            Thread myThread = new Thread(WatchDirectory.getInstance(_paths));
            myThread.setDaemon(true);
            myThread.start();

        } catch (NullPointerException e) {
            System.out.println("Error creating paths");

        }
    }

    /**
     * Metodo que corre cuando se inicializa el servidor (debido a un Listener)
     * @param servletContextEvent el ServletContextEvent que esta siendo manejado
     */
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    /**
     *
     * @param path direccion de path
     */
    private void createDirectory(String path){
        File pathFile = new File(path);
        if (!pathFile.exists()){
            pathFile.mkdir();
        }
    }

    /**
     *  @param rootPath direccion raiz de path
     * @param companies Lista (array) de companias
     */
    private void createOriginDirectories(String rootPath, List<Company> companies){

        for (Company company : companies) {
            _paths.add(rootPath + "/" + company.get_link());
            this.createDirectory(rootPath + "/" + company.get_link());
        }
    }

    public static String getInboxPath(){
        return ORIGIN_HOME_INBOX;
    }

    public static String getTrashPath(){
        return ORIGIN_HOME_TRASH;
    }

    public ArrayList<String> get_paths() {
        return _paths;
    }

    public void set_paths(ArrayList<String> _paths) {
        this._paths = _paths;
    }

}
