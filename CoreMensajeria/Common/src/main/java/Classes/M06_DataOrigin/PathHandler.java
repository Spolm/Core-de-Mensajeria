package Classes.M06_DataOrigin;

import Classes.M02_Company.Company;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;
import java.util.ArrayList;

@WebListener
public class PathHandler implements ServletContextListener {

    private static String ORIGIN_HOME = System.getProperty("user.home") + "/Origin";
    private static String ORIGIN_HOME_INBOX = ORIGIN_HOME + "/Inbox";
    private static String ORIGIN_HOME_TRASH = ORIGIN_HOME + "/Trash";
    /*public void FileChooser(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        try {
            fileChooser.setCurrentDirectory(new File(/*System.getProperty("user.home")"C:/"));
            fileChooser.setDialogTitle("Seleccione un directorio.");
            int result = fileChooser.showOpenDialog(new JPanel());
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                System.out.println("Selected path: " + selectedFile.getAbsolutePath());
            }
        } catch (HeadlessException e) {
            e.printStackTrace();
        }
    }*/

    /**
     * Metodo que corre cuando se inicializa el servidor (crea los correspondientes directorios)
     * @param servletContextEvent el ServletContextEvent que esta siendo manejado
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        this.createDirectory(ORIGIN_HOME);
        this.createDirectory(ORIGIN_HOME_INBOX);
        this.createDirectory(ORIGIN_HOME_TRASH);
        System.out.println("--------------->PATHS CREATED");
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
     *
     * @param rootPath direccion raiz de path
     * @param companies Lista (array) de companias
     */
    private void createOriginDirectories(String rootPath, ArrayList<Company> companies){
        for (Company company : companies) {
            //------------------->REPLECE THIS FOR COMPANY PATH
            this.createDirectory(rootPath + "/" + company.get_name());
        }
    }

    /**
     *
     * @param company objeto compania
     * @return direccion de path generada para la compania senalada
     */
    private String generatePath(Company company){
        return company.get_name().replaceAll("\\s","") + "_" + Encrypter.getCurrentTime();
    }

    public static String getInboxPath(){
        return ORIGIN_HOME_INBOX;
    }

    public static String getTrashPath(){
        return ORIGIN_HOME_TRASH;
    }
}
