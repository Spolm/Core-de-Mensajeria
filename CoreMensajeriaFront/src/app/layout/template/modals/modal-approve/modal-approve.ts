import { Component, Input, Output, EventEmitter } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { TemplateService } from '../../template.service';
import { ToastrService } from 'ngx-toastr';
import { delay } from 'q';

@Component({
  selector: 'ngbd-modal-approve',
  templateUrl: './modal-approve.html',
  styleUrls: ['./modal-approve.scss'],
  providers: [NgbModal]
})
export class NgbdModalApprove {

  @Input('templateId') templateId: number;
  @Output() spread = new EventEmitter<string>();

  userId: string = localStorage.getItem("userid");
  message: string;
  activeModal: NgbModalRef;
  privilegesJson: any = [];
  CTEMPLATE = false;
  RTEMPLATE = false;
  UTEMPLATE = false;
  DTEMPLATE = false;
  ATEMPLATE = false;

  constructor(private modalService: NgbModal, private templateService: TemplateService, private toastr: ToastrService) {
    this.getPrivileges(this.userId, 2);
  }

  open(content) {
    this.activeModal = this.modalService.open(content);
  }

  closeModal() { 
    this.activeModal.close();
  }

  async getPrivileges(userId: string, companyId: number) {
    this.templateService.getPrivilegesByUserAndCompany(userId, companyId).subscribe(data => {
      this.privilegesJson = data;
    });
    await delay(1000);
    this.assignPrivileges(this.privilegesJson);
  }

  assignPrivileges(privileges: Array<any>) {
    privileges.forEach((privilege) => {
      if(privilege._codePrivileges == 'CTEMPLATE'){
        this.CTEMPLATE = true;
      }
      else if(privilege._codePrivileges == 'RTEMPLATE'){
        this.RTEMPLATE = true
      }
      else if(privilege._codePrivileges == 'UTEMPLATE'){
        this.UTEMPLATE = true
      }
      else if(privilege._codePrivileges == 'DTEMPLATE'){
        this.DTEMPLATE = true
      }
      else if(privilege._codePrivileges == 'ATEMPLATE'){
        this.ATEMPLATE = true
      }
    })
  }

  async approveTemplate(templateId: number) {
    this.templateService.approveTemplate(templateId);
    this.closeModal();
    this.toastr.success("Aprobada", "Plantilla id: " + templateId,
      {
        timeOut: 2800,
        progressBar: true
      });
    await delay(1000);
    this.spread.emit(this.message);
  }
}