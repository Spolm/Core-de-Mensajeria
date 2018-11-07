package Classes.M07_Template.MessagePackage;

public class Parameter {
    private String name;
    private String description;

    public Parameter() {
    }

    public Parameter(String _name) {
        this.name = name;
    }

    public Parameter(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}

