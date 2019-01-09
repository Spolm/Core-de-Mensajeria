package DTO.M07_Template;

import DTO.DTO;

import java.sql.Date;

public class DTOPlanning extends DTO {

    private Date _pStartDate;
    private Date _pEndDate;
    private String _pStartTime;
    private String _pEndTime;
    private int _pIdPlanning;

    public DTOPlanning() {
    }

    public DTOPlanning(Date _pStartDate, Date _pEndDate, String _pStartTime, String _pEndTime, int _pIdPlanning) {
        this._pStartDate = _pStartDate;
        this._pEndDate = _pEndDate;
        this._pStartTime = _pStartTime;
        this._pEndTime = _pEndTime;
        this._pIdPlanning = _pIdPlanning;
    }

    public Date get_pStartDate() {
        return _pStartDate;
    }

    public void set_pStartDate(Date _pStartDate) {
        this._pStartDate = _pStartDate;
    }

    public Date get_pEndDate() {
        return _pEndDate;
    }

    public void set_pEndDate(Date _pEndDate) {
        this._pEndDate = _pEndDate;
    }

    public String get_pStartTime() {
        return _pStartTime;
    }

    public void set_pStartTime(String _pStartTime) {
        this._pStartTime = _pStartTime;
    }

    public String get_pEndTime() {
        return _pEndTime;
    }

    public void set_pEndTime(String _pEndTime) {
        this._pEndTime = _pEndTime;
    }

    public int get_pIdPlanning() {
        return _pIdPlanning;
    }

    public void set_pIdPlanning(int _pIdPlanning) {
        this._pIdPlanning = _pIdPlanning;
    }
}
