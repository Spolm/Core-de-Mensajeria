import { StatisticsServiceService } from "./statistics-service.service";
import { ToastrService } from "ngx-toastr";

enum EntityType {
    company = 1,
    campaign,
    channel
}

export class DropdownMethods {
    constructor(
        public statisticsService: StatisticsServiceService,
        public toastr: ToastrService
    ) {
        this.userId = localStorage.getItem("userid");

        this.setupCompaniesDropdownSettings();
        this.setupCampaignsDropdownSettings();
        this.setupChannelsDropdownSettings();
    }

    userId: String;

    /* ==========================
           Filter dropdowns
    ============================= */
    companiesDropdown = [];
    companiesDropdownSettings = {};
    selectedCompaniesIds: Number[] = [];
    selectedCompanies = [];

    campaignsDropdown = [];
    campaignsDropdownSettings = {};
    selectedCampaignsIds = [];
    selectedCampaigns = [];

    channelsDropdown = [];
    channelsDropdownSettings = {};
    selectedChannelsIds = [];
    selectedChannels = [];

    integratorsDropdown = [];
    integratorsDropdownSettings = {};
    selectedIntegratorsIds = [];
    selectedIntegrators = [];

    public setupCompaniesDropdownSettings() {
        this.companiesDropdownSettings = {
            singleSelection: false,
            idField: "company_id",
            textField: "company_name",
            selectAllText: "Seleccionar todos",
            unSelectAllText: "Deseleccionar todos",
            itemsShowLimit: 1,
            allowSearchFilter: true
        };
    }

    public setupCampaignsDropdownSettings() {
        this.campaignsDropdownSettings = {
            singleSelection: false,
            idField: "campaign_id",
            textField: "campaign_name",
            selectAllText: "Seleccionar todos",
            unSelectAllText: "Deseleccionar todos",
            itemsShowLimit: 1,
            allowSearchFilter: true
        };
    }

    public setupChannelsDropdownSettings() {
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

    // Handle company selecction
    companySelected(company: any) {
        this.selectedCompaniesIds.push(company["company_id"]);
        this.getCampaignsForCompanies();
    }

    companyDeselected(company: any) {
        this.removeItemFromArray(
            company["company_id"],
            this.selectedCompaniesIds
        );
        // this.removeObjectFromArray(
        //     company["company_id"],
        //     this.selectedCompanies,
        //     "company_id"
        // );
        if (this.arrayIsEmpty(this.selectedCompaniesIds)) {
            this.getAllCampaigns();
        } else {
            this.getCampaignsForCompanies();
        }
    }

    selectAllCompanies() {
        for (var index in this.companiesDropdown) {
            this.selectedCompaniesIds.push(
                this.companiesDropdown[index]["company_id"]
            );
            //this.selectedCompanies.push(this.companiesDropdown[index]);
        }
        this.getAllCampaigns();
    }

    deselectAllCompanies() {
        this.selectedCompaniesIds = [];
        //this.selectedCompanies = [];
        this.getAllCampaigns();
    }

    // Handle campaign selecction
    campaignSelected(campaign: any) {
        this.selectedCampaignsIds.push(campaign["campaign_id"]);
        //this.selectedCampaigns.push(campaign);
    }

    campaignDeselected(campaign: any) {
        this.removeItemFromArray(
            campaign["campaign_id"],
            this.selectedCampaignsIds
        );
        // this.removeObjectFromArray(
        //     campaign["campaign_id"],
        //     this.selectedCampaigns,
        //     "campaign_id"
        // );
    }

    selectAllCampaigns() {
        for (var index in this.campaignsDropdown) {
            this.selectedCampaignsIds.push(
                this.campaignsDropdown[index]["campaign_id"]
            );
            //this.selectedCampaigns.push(this.campaignsDropdown[index]);
        }
    }

    deselectAllCampaigns() {
        this.selectedCampaignsIds = [];
        //this.selectedCampaigns = [];
    }

    // Handle channel selection
    channelSelected(channel: any) {
        this.selectedChannelsIds.push(channel["channel_id"]);
        //this.selectedChannels.push(channel);
    }

    channelDeselected(channel: any) {
        this.removeItemFromArray(
            channel["channel_id"],
            this.selectedChannelsIds
        );
        // this.removeObjectFromArray(
        //     channel["channel_id"],
        //     this.selectedChannels,
        //     "channel_id"
        // );
    }

    selectAllChannels() {
        for (var index in this.channelsDropdown) {
            this.selectedChannelsIds.push(
                this.channelsDropdown[index]["channel_id"]
            );
            //this.selectedChannels.push(this.channelsDropdown[index]);
        }
    }

    deselectAllChannels() {
        this.selectedChannelsIds = [];
        //this.selectedChannels = [];
    }

    getCampaignsForCompanies() {
        this.statisticsService
            .getCampaingsForCompany(this.selectedCompaniesIds)
            .subscribe(data => {
                this.campaignsDropdown = [];
                this.insertIntoDropdown(EntityType.campaign, data);
            });
    }

    public insertIntoDropdown(entityType: EntityType, data: Object) {
        switch (entityType) {
            case EntityType.company:
                for (var index in data) {
                    this.companiesDropdown.push({
                        company_id: data[index]["_idCompany"],
                        company_name: data[index]["_name"]
                    });
                }
                console.log(this.companiesDropdown);
                break;
            case EntityType.campaign:
                this.campaignsDropdown = [];
                for (var index in data) {
                    this.campaignsDropdown.push({
                        campaign_id: data[index]["_idCampaign"],
                        campaign_name: data[index]["_nameCampaign"]
                    });
                }
                break;
            case EntityType.channel:
                for (var index in data) {
                    this.channelsDropdown.push({
                        channel_id: data[index]["idChannel"],
                        channel_name: data[index]["nameChannel"]
                    });
                }
                break;
        }
    }

    removeItemFromArray(item: Number, array: Number[]) {
        for (var i = 0; i < array.length; i++) {
            if (item == array[i]) {
                array.splice(i, 1);
            }
        }
    }

    removeObjectFromArray<T>(id: any, array: any[], key: T) {
        for (var i = 0; i < array.length; i++) {
            if (id == array[i][key]) {
                array.splice(i, 1);
            }
        }
    }

    arrayIsEmpty(array) {
        return !Array.isArray(array) || !array.length;
    }

    getAllCampaigns() {
        this.statisticsService
            .getCampaingsForCompany(
                this.getIdsFromDropdown(this.companiesDropdown, "company_id")
            )
            .subscribe(
                data => {
                    this.insertIntoDropdown(EntityType.campaign, data);
                },
                error => {
                    console.log(error);
                    this.toastr.error("Error de conexión");
                }
            );
    }

    public getIdsFromDropdown<T>(dropdown: any[], indexName: T) {
        var ids = [];
        dropdown.forEach(element => {
            ids.push(element[indexName]);
        });
        return ids;
    }
}
