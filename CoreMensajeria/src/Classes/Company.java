package Classes;

public class Company {
    private int _idCompany;
    private String _name;
    private String _desc;
    private boolean _status;

    public int get_idCompany () {
        return _idCompany;
    }

    public void set_idCompany ( int idCompany ) {
        _idCompany = idCompany;
    }

    public String get_name () {
        return _name;
    }

    public void set_name ( String name ) {
        _name = name;
    }

    public String get_desc() {
        return _desc;
    }

    public void set_desc ( String desc ) {
        _desc = desc;
    }

    public boolean get_status() {
        return _status;
    }

    public void set_status ( boolean status ) {
        _status = status;
    }

    public Company() {

    }


    public Company(String name, String desc, boolean status) {

        _name = name;
        _desc = desc;
        _status = status;

    }

    /*public Company(int id, String name, String desc, boolean status) {

        _idCompany = id;
        _name = name;
        _desc = desc;
        _status = status;

    }*/
}
