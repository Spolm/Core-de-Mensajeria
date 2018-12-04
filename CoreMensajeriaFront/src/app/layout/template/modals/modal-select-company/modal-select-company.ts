import { Component, Input, Output, EventEmitter } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { TemplateService } from '../../template.service';
import { ToastrService } from 'ngx-toastr';
import { delay } from 'q';
import { Router } from '@angular/router';

@Component({
  selector: 'ngbd-modal-select-company',
  templateUrl: './modal-select-company.html',
  styleUrls: ['./modal-select-company.scss'],
  providers: [NgbModal]
})
export class NgbdModalSelectCompany {

  @Input('userId') userId: string;

  message: string;
  activeModal: NgbModalRef;
  companies: any = []

  constructor(private modalService: NgbModal, private templateService: TemplateService, private router: Router) {

  }

  open(content) {
    this.activeModal = this.modalService.open(content);
    this.templateService.getCompanies(this.userId).subscribe(data => {
      this.companies = data;
    });
  }

  closeModal() {
    this.activeModal.close();
  }

}