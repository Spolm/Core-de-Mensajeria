import { Component, OnInit, Inject } from "@angular/core";
import { MAT_DIALOG_DATA, MatDialogRef } from "@angular/material";
import { StatisticsServiceService } from "../statistics-service.service";
import { ToastrService } from "ngx-toastr";
import { DropdownMethods } from "../DropdownMethods";

@Component({
    selector: "app-more-filters",
    templateUrl: "./more-filters.component.html",
    styleUrls: ["./more-filters.component.scss"]
})
export class MoreFiltersComponent extends DropdownMethods implements OnInit {
    opcionSeleccionado: string = "0";
    verSeleccion: string = "";
    Date2Capturado: string = "";
    Date1Capturado: string = "";
    opcionDateSleccionado: Date;
    opcionDateSleccionado2: Date;
    paramType: string;

    constructor(
        @Inject(MAT_DIALOG_DATA) public data: any,
        public statisticsService: StatisticsServiceService,
        public toastr: ToastrService,
        private dialogRef: MatDialogRef<MoreFiltersComponent>
    ) {
        super(statisticsService, toastr);
        this.initializeDataFromMainFilters(data);
    }

    ngOnInit() {}

    closeMenu() {
        this.dialogRef.close({
            companies: this.includeCompaniesDataOnReturn(),
            campaigns: this.includeCampaignsDataOnReturn(),
            channels: this.includeChannelsDataOnReturn(),
            integrators: this.includeIntegratorsDataOnReturn()
        });
    }

    includeCompaniesDataOnReturn() {
        return {
            selectedCompanies: this.selectedCompanies,
            selectedCompaniesIds: this.selectedCompaniesIds,
            companiesDropdown: this.companiesDropdown
        };
    }

    includeCampaignsDataOnReturn() {
        return {
            selectedCampaigns: this.selectedCampaigns,
            selectedCampaignsIds: this.selectedCampaignsIds,
            campaignsDropdown: this.campaignsDropdown
        };
    }

    includeChannelsDataOnReturn() {
        return {
            selectedChannels: this.selectedChannels,
            selectedChannelsIds: this.selectedChannelsIds,
            channelsDropdown: this.channelsDropdown
        };
    }

    includeIntegratorsDataOnReturn() {
        return {
            selectedIntegrators: this.selectedIntegrators,
            selectedIntegratorsIds: this.selectedIntegratorsIds,
            integratorsDropdown: this.integratorsDropdown
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
        this.setupCompaniesData(data);
        this.setupCampaingsData(data);
        this.setupChannelsData(data);

        if (this.arrayIsEmpty(this.selectedChannels)) {
            this.getAllIntegrators();
        } else {
            this.getIntegratorsForChannels();
        }
    }

    setupCompaniesData(data: any) {
        this.companiesDropdown = data.companiesDropdown;
        this.companiesDropdownSettings = data.companiesDropdownSettings;
        this.selectedCompanies = data.selectedCompanies;
        this.selectedCompaniesIds = data.selectedCompaniesIds;
    }

    setupCampaingsData(data: any) {
        this.campaignsDropdown = data.campaignsDropdown;
        this.campaignsDropdownSettings = data.campaignsDropdownSettings;
        this.selectedCampaigns = data.selectedCampaigns;
        this.selectedCampaignsIds = data.selectedCampaignsIds;
    }

    setupChannelsData(data: any) {
        this.channelsDropdown = data.channelsDropdown;
        this.channelsDropdownSettings = data.channelsDropdownSettings;
        this.selectedChannels = data.selectedChannels;
        this.selectedChannelsIds = data.selectedChannelsIds;
    }

    setupIntegratorsData(data: any) {
        this.integratorsDropdown = data.integratorsDropdown;
        this.integratorsDropdownSettings = data.integratorsDropdownSettings;
        this.selectedIntegrators = data.selectedIntegrators;
        this.selectedIntegratorsIds = data.selectedIntegratorsIds;
    }
}
