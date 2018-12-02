import { Component, OnInit, Inject } from "@angular/core";
import { MAT_DIALOG_DATA } from "@angular/material";
import { StatisticsServiceService } from "../statistics-service.service";
import { ToastrService } from "ngx-toastr";

@Component({
    selector: "app-more-filters",
    templateUrl: "./more-filters.component.html",
    styleUrls: ["./more-filters.component.scss"]
})
export class MoreFiltersComponent implements OnInit {
    companiesDropdown = [];
    companiesDropdownSettings = {};
    selectedCompaniesIds = [];
    selectedCompanies = [];

    campaignsDropdown = [];
    campaignsDropdownSettings = {};
    selectedCampaignsIds = [];
    selectedCampaigns = [];

    channelsDropdown = [];
    channelsDropdownSettings = {};
    selectedChannelsIds = [];
    selectedChannels = [];
    //--------

    opcionSeleccionado: string = "0";
    verSeleccion: string = "";
    Date2Capturado: string = "";
    Date1Capturado: string = "";
    opcionDateSleccionado: Date;
    opcionDateSleccionado2: Date;
    paramType: string;

    constructor(
        @Inject(MAT_DIALOG_DATA) public data: any,
        private statisticsService: StatisticsServiceService,
        private toastr: ToastrService
    ) {
        this.initializeDataFromMainFilters(data);
    }

    ngOnInit() {
        this.companiesDropdownSettings = {
            singleSelection: false,
            idField: "company_id",
            textField: "company_name",
            selectAllText: "Seleccionar todos",
            unSelectAllText: "Deseleccionar todos",
            itemsShowLimit: 1,
            allowSearchFilter: true
        };

        this.campaignsDropdownSettings = {
            singleSelection: false,
            idField: "campaign_id",
            textField: "campaign_name",
            selectAllText: "Seleccionar todos",
            unSelectAllText: "Deseleccionar todos",
            itemsShowLimit: 1,
            allowSearchFilter: true
        };

        this.channelsDropdownSettings = {
            singleSelection: false,
            idField: "channel_id",
            textField: "channel_name",
            selectAllText: "Seleccionar todos",
            unSelectAllText: "Deseleccionar todos",
            itemsShowLimit: 1,
            allowSearchFilter: true
        };
    }

/*
  capturarDate() {
        if (
          this.opcionDateSleccionado != null &&
            this.opcionDateSleccionado2 != null &&
            this.opcionDateSleccionado < this.opcionDateSleccionado2
        ) {
            this.Date1Capturado =
                "?paramDate1=" + this.opcionDateSleccionado.toString();
            this.Date2Capturado =
                "?paramDate2=" + this.opcionDateSleccionado2.toString();
            this.paramType = "paramType=" + this.verSeleccion;
            console.log(
                "FechaCapturada",
                "Dates1: " + this.Date1Capturado,
                "Dates2: " + this.Date2Capturado,
                +" " + new Date(this.opcionDateSleccionado).getUTCDate(),
                new Date(this.opcionDateSleccionado).getUTCMonth(),
                new Date(this.opcionDateSleccionado).getFullYear(),
                new Date(this.opcionDateSleccionado2).getUTCDate(),
                new Date(this.opcionDateSleccionado2).getUTCMonth(),
                new Date(this.opcionDateSleccionado2).getFullYear()
            );
            this.statisticsService
                .getDataLineChartCompany(
                    // getStatisticsData4 
                    this.Date1Capturado +
                        "&" +
                        this.Date2Capturado +
                        "&" +
                        this.paramType
                )
                .subscribe(data => {
                    console.log(data);
                });
        } else if (
            (this.opcionDateSleccionado == null &&
                this.opcionDateSleccionado2 == null) ||
            (this.opcionDateSleccionado.toString().length == 0 &&
                this.opcionDateSleccionado2.toString().length == 0)
        ) {
            console.log("hols" + this.opcionDateSleccionado.toString().length);

            this.Date1Capturado = "?paramDate1=";
            this.Date2Capturado = "?paramDate2=";
            this.paramType = "paramType=" + this.verSeleccion;
            this.statisticsService
                .getDataLineChartCompany(
                    // getStatisticsData4 
                    this.Date1Capturado +
                        "&" +
                        this.Date2Capturado +
                        "&" +
                        this.paramType
                )
                .subscribe(data => {
                    console.log(data);
                });
        } else {
            this.toastr.error("Error en las fechas");
        }
    }

*/

    initializeDataFromMainFilters(data: any) {
        this.companiesDropdown = data.companiesDropdown;
        this.companiesDropdownSettings = data.companiesDropdownSettings;
        this.selectedCompanies = data.selectedCompanies;
        this.selectedCompaniesIds = data.selectedCompaniesIds;
        this.campaignsDropdown = data.campaignsDropdown;
        this.campaignsDropdownSettings = data.campaignsDropdownSettings;
        this.selectedCampaigns = data.selectedCampaigns;
        this.selectedCampaignsIds = data.selectedCampaignsIds;
        this.channelsDropdown = data.channelsDropdown;
        this.channelsDropdownSettings = data.channelsDropdownSettings;
        this.selectedChannels = data.selectedChannels;
        this.selectedChannelsIds = data.selectedChannelsIds;
    }
}
