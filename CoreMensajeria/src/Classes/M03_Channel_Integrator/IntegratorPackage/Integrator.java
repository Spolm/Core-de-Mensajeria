package Classes.M03_Channel_Integrator.IntegratorPackage;

import java.util.Objects;

public abstract class Integrator implements IIntegrator {
    private int _idIntegrator;
    private int _threadCapacity;
    private float _messageCost;
    private String _nameIntegrator;
    private String _apiIntegrator;

    public Integrator(int _idIntegrator, int _threadCapacity, float _messageCost,
                      String _nameIntegrator, String _apiIntegrator) {
        this._idIntegrator = _idIntegrator;
        this._threadCapacity = _threadCapacity;
        this._messageCost = _messageCost;
        this._nameIntegrator = _nameIntegrator;
        this._apiIntegrator = _apiIntegrator;
    }

    @Override
    public String toString() {
        return "Integrator{" +
                "_idIntegrator=" + _idIntegrator +
                ", _threadCapacity=" + _threadCapacity +
                ", _messageCost=" + _messageCost +
                ", _nameIntegrator='" + _nameIntegrator + '\'' +
                ", _apiIntegrator='" + _apiIntegrator + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Integrator)) return false;
        Integrator that = (Integrator) o;
        return _idIntegrator == that._idIntegrator &&
                _threadCapacity == that._threadCapacity &&
                Float.compare(that._messageCost, _messageCost) == 0 &&
                Objects.equals(_nameIntegrator, that._nameIntegrator) &&
                Objects.equals(_apiIntegrator, that._apiIntegrator);
    }

    public int get_idIntegrator() {
        return _idIntegrator;
    }

    public void set_idIntegrator(int _idIntegrator) {
        this._idIntegrator = _idIntegrator;
    }

    public int get_threadCapacity() {
        return _threadCapacity;
    }

    public void set_threadCapacity(int _threadCapacity) {
        this._threadCapacity = _threadCapacity;
    }

    public float get_messageCost() {
        return _messageCost;
    }

    public void set_messageCost(float _messageCost) {
        this._messageCost = _messageCost;
    }

    public String get_nameIntegrator() {
        return _nameIntegrator;
    }

    public void set_nameIntegrator(String _nameIntegrator) {
        this._nameIntegrator = _nameIntegrator;
    }

    public String get_apiIntegrator() {
        return _apiIntegrator;
    }

    public void set_apiIntegrator(String _apiIntegrator) {
        this._apiIntegrator = _apiIntegrator;
    }
}
