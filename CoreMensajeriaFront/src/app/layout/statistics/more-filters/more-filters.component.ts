import { Component, OnInit, Inject } from "@angular/core";
import { MAT_DIALOG_DATA } from "@angular/material";
import { StatisticsServiceService } from "../statistics-service.service";

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

    constructor(
        @Inject(MAT_DIALOG_DATA) public data: any,
        private statisticsService: StatisticsServiceService
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
