import { Component, OnInit, Inject } from "@angular/core";
import { MAT_DIALOG_DATA } from "@angular/material";

@Component({
    selector: "app-more-filters",
    templateUrl: "./more-filters.component.html",
    styleUrls: ["./more-filters.component.scss"]
})
export class MoreFiltersComponent implements OnInit {
    modalTitle: String;
    constructor(@Inject(MAT_DIALOG_DATA) public data: any) {
        this.modalTitle = data.title;
    }

    ngOnInit() {}
}
