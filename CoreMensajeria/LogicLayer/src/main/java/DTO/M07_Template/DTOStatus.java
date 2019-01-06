package DTO.M07_Template;

import DTO.DTO;

public class DTOStatus extends DTO {

    private int _sStatusId;
    private String _sStatusName;

    public DTOStatus() {
    }

    public DTOStatus(int _sStatusId, String _sStatusName) {
        this._sStatusId = _sStatusId;
        this._sStatusName = _sStatusName;
    }

    public int get_sStatusId() {
        return _sStatusId;
    }

    public void set_sStatusId(int _sStatusId) {
        this._sStatusId = _sStatusId;
    }

    public String get_sStatusName() {
        return _sStatusName;
    }

    public void set_sStatusName(String _sStatusName) {
        this._sStatusName = _sStatusName;
    }
}
