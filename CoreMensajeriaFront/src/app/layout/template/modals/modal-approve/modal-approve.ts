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

  message: string;
  activeModal: NgbModalRef;

  constructor(private modalService: NgbModal, private templateService: TemplateService, private toastr: ToastrService) {

  }

  open(content) {
    this.activeModal = this.modalService.open(content);
  }

  closeModal() { 
    this.activeModal.close();
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