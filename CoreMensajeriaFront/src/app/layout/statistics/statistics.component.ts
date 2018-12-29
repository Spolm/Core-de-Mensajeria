import { ProfileComponent } from "./../profile/profile.component";
import { StatisticsServiceService } from "./statistics-service.service";
import {
    Component,
    OnInit,
    ElementRef,
    ViewChild,
    AfterViewInit
} from "@angular/core";
import { ToastrService } from "ngx-toastr";
import { HttpParams } from "@angular/common/http";
import {
    MatDialog,
    MatDialogConfig,
    MatDialogRef,
    MAT_DIALOG_DATA
} from "@angular/material";
import { MoreFiltersComponent } from "./more-filters/more-filters.component";
import { Chart } from "chart.js";
import { Point } from "./point";
import { forEach } from "@angular/router/src/utils/collection";
import { ChartsComponent } from "../charts/charts.component";
import { DropdownComponent } from "../bs-component/components";
import { DropdownMethods } from "./DropdownMethods";
//import { create } from "domain";

interface myData {
    obj: Object;
}

enum EntityType {
    company = 1,
    campaign,
    channel
}

enum ChartType {
    line = "line",
    bar = "bar",
    pie = "pie",
    doughnut = "doughnut"
}

@Component({
    selector: "app-statistics",
    templateUrl: "./statistics.component.html",
    styleUrls: ["./statistics.component.scss"]
})
export class StatisticsComponent extends DropdownMethods implements OnInit {
    userId: string;

    /* =================================
           Charts elements from html
    ==================================== */
    @ViewChild("timeLineChart") canvas: ElementRef;
    @ViewChild("companiesChartElement") companiesChartElement: ElementRef;
    @ViewChild("campaignsChartElement") campaignsChartElement: ElementRef;
    @ViewChild("channelsChartElement") channelsChartElement: ElementRef;
    @ViewChild("integratorsChartElement") integratorsChartElement: ElementRef;

    /* ==============
           Charts
    ================= */
    timeLineChart: Chart = [];
    companiesChart: Chart = [];
    campaignsChart: Chart = [];
    channelsChart: Chart = [];
    integratorsChart: Chart = [];

    verSeleccion: string = "";
    Date2Capturado: string = "";
    Date1Capturado: string = "";
    opcionDateSleccionado: Date;
    opcionDateSleccionado2: Date;
    paramType: string;

    chartTypes = [];

    constructor(
        public statisticsService: StatisticsServiceService,
        public toastr: ToastrService,
        public dialog: MatDialog
    ) {
        super(statisticsService, toastr);
        this.chartTypes = [
            ["bar", "barras", true],
            ["line", "línea", false],
            ["doughnut", "dona", false]
        ];
    }

    ngAfterViewInit() {
        // Refactorizar la creación de esta gráfica
        this.timeLineChart = new Chart(
            this.canvas.nativeElement.getContext("2d"),
            {
                type: "line",
                data: {
                    labels: ["2015", "2016", "2017", "2018", "2019"],
                    datasets: [
                        {
                            data: [
                                { x: new Date("2015-10-5"), y: 10 },
                                { x: new Date("2017-12-12"), y: 20 },
                                { x: new Date("2018-11-09"), y: 15 }
                            ],
                            borderColor: "#3cba9f",
                            fill: true,
                            backgroundColor: "rgba(60,186,159, 0.5)"
                        }
                    ]
                },
                options: {
                    title: {
                        display: true,
                        text: "Línea de tiempo"
                    },
                    responsive: true,
                    legend: {
                        display: false
                    },
                    scales: {
                        xAxes: [
                            {
                                display: true,
                                type: "time",
                                time: {
                                    unit: "month"
                                }
                            }
                        ],
                        yAxes: [
                            {
                                display: true
                            }
                        ]
                    }
                }
            }
        );
    }

    insertInitialDataIntoCharts(
        data: Object,
        chartElement: ElementRef,
        title: String,
        chartType: ChartType
    ): Chart {
        let pointsForChart = this.createPointArray(data);
        var pointsOfChartJson = [];
        let colors = this.getArrayOfRandomColors(pointsForChart.length);

        pointsForChart.forEach(company => {
            pointsOfChartJson.push(company.toJson());
        });

        return this.createChart(
            title,
            Point.getXArray(pointsForChart),
            Point.getYArray(pointsOfChartJson),
            colors,
            chartType,
            chartElement
        );
    }

    changeCompaniesChart(value: string) {
        this.companiesChart = this.changeChartType(
            value,
            this.companiesChart,
            this.companiesChartElement
        );
    }

    changeCampaignsChart(value: string) {
        this.campaignsChart = this.changeChartType(
            value,
            this.campaignsChart,
            this.campaignsChartElement
        );
    }

    changeChannelsChart(value: string) {
        this.channelsChart = this.changeChartType(
            value,
            this.channelsChart,
            this.channelsChartElement
        );
    }

    changeIntegratorsChart(value: string) {
        this.integratorsChart = this.changeChartType(
            value,
            this.integratorsChart,
            this.integratorsChartElement
        );
    }

    changeChartType(value: string, chart: Chart, element: ElementRef): Chart {
        var config = chart.config;
        chart.destroy();
        config.type = value;
        return new Chart(element.nativeElement.getContext("2d"), config);
    }

    ngOnInit() {
        this.getAllCompanies();
        //this.getAllCampaigns();
        this.getAllChannels();

        this.statisticsService.getInitialMessagesForCompanies().subscribe(
            data => {
                this.companiesChart = this.insertInitialDataIntoCharts(
                    data,
                    this.companiesChartElement,
                    "Cantidad de mensajes por compañía",
                    ChartType.bar
                );
            },
            error => {
                this.toastr.error(
                    "Hubo un error obteniendo la cantidad de mensajes por compañía"
                );
            }
        );

        this.statisticsService.getInitialMessagesForCampaigns().subscribe(
            data => {
                this.campaignsChart = this.insertInitialDataIntoCharts(
                    data,
                    this.campaignsChartElement,
                    "Cantidad de mensajes por campaña",
                    ChartType.bar
                );
            },
            error => {
                this.toastr.error(
                    "Hubo un error obteniendo la cantidad de mensajes por campaña"
                );
            }
        );

        this.statisticsService.getInitialMessagesForChannels().subscribe(
            data => {
                this.channelsChart = this.insertInitialDataIntoCharts(
                    data,
                    this.channelsChartElement,
                    "Cantidad de mensajes por canal",
                    ChartType.doughnut
                );
            },
            error => {
                this.toastr.error(
                    "Hubo un error obteniendo la cantidad de mensajes por canales."
                );
            }
        );

        this.statisticsService.getInitialMessagesForIntegrators().subscribe(
            data => {
                this.integratorsChart = this.insertInitialDataIntoCharts(
                    data,
                    this.integratorsChartElement,
                    "Cantidad de mensajes por integrador",
                    ChartType.bar
                );
            },
            error => {
                this.toastr.error(
                    "Hubo un error obteniendo la cantidad de mensajes por integradores."
                );
            }
        );
    }

    private getAllCompanies() {
        this.statisticsService.getAllCompanies(this.userId).subscribe(
            data => {
                this.insertIntoDropdown(EntityType.company, data);
                this.getAllCampaigns();
            },
            error => {
                console.log(
                    "Error getting companies: " + JSON.stringify(error)
                );
                this.toastr.error("Error obteniendo las compañías.");
            }
        );
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
                    this.toastr.error("Error obteniendo las campañas.");
                }
            );
    }

    private getAllChannels() {
        this.statisticsService.getAllChannels().subscribe(data => {
            this.insertIntoDropdown(EntityType.channel, data);
            this.getAllIntegrators();
        });
    }

    // Handle company selecction
    companySelected(company: any) {
        this.selectedCompaniesIds.push(company["company_id"]);
        this.selectedCompanies.push(company);
        this.getCampaignsForCompanies();
    }

    companyDeselected(company: any) {
        this.removeItemFromArray(
            company["company_id"],
            this.selectedCompaniesIds
        );
        this.removeObjectFromArray(
            company["company_id"],
            this.selectedCompanies,
            "company_id"
        );
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
            this.selectedCompanies.push(this.companiesDropdown[index]);
        }
        this.getAllCampaigns();
    }

    deselectAllCompanies() {
        this.selectedCompaniesIds = [];
        this.selectedCompanies = [];
        this.getAllCampaigns();
    }

    // Handle campaign selecction
    campaignSelected(campaign: any) {
        this.selectedCampaignsIds.push(campaign["campaign_id"]);
        this.selectedCampaigns.push(campaign);
    }

    campaignDeselected(campaign: any) {
        this.removeItemFromArray(
            campaign["campaign_id"],
            this.selectedCampaignsIds
        );
        this.removeObjectFromArray(
            campaign["campaign_id"],
            this.selectedCampaigns,
            "campaign_id"
        );
    }

    selectAllCampaigns() {
        for (var index in this.campaignsDropdown) {
            this.selectedCampaignsIds.push(
                this.campaignsDropdown[index]["campaign_id"]
            );
            this.selectedCampaigns.push(this.campaignsDropdown[index]);
        }
    }

    deselectAllCampaigns() {
        this.selectedCampaignsIds = [];
        this.selectedCampaigns = [];
    }

    // Handle channel selection
    channelSelected(channel: any) {
        this.selectedChannelsIds.push(channel["channel_id"]);
        this.selectedChannels.push(channel);
    }

    channelDeselected(channel: any) {
        this.removeItemFromArray(
            channel["channel_id"],
            this.selectedChannelsIds
        );
        this.removeObjectFromArray(
            channel["channel_id"],
            this.selectedChannels,
            "channel_id"
        );
    }

    selectAllChannels() {
        for (var index in this.channelsDropdown) {
            this.selectedChannelsIds.push(
                this.channelsDropdown[index]["channel_id"]
            );
            this.selectedChannels.push(this.channelsDropdown[index]);
        }
    }

    deselectAllChannels() {
        this.selectedChannelsIds = [];
        this.selectedChannels = [];
    }

    getCampaignsForCompanies() {
        this.statisticsService
            .getCampaingsForCompany(this.selectedCompaniesIds)
            .subscribe(data => {
                this.campaignsDropdown = [];
                this.insertIntoDropdown(EntityType.campaign, data);
            });
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

    sendUserRequest() {
        var params = this.convertSelectedItemsIntoHttpParams();
        this.statisticsService.getStatistics(params).subscribe(
            data => {
                let companiesJson = data["companies"];
                var companies: Point[] = this.createPointArray(companiesJson);
                let campaingsJson = data["campaigns"];
                var campaigns: Point[] = this.createPointArray(campaingsJson);
                let channelsJson = data["channels"];
                var channels: Point[] = this.createPointArray(channelsJson);
                let integratorsJson = data["integrators"];
                var integrators: Point[] = this.createPointArray(
                    integratorsJson
                );

                this.updateChartData(
                    this.companiesChart,
                    companies,
                    ChartType.bar
                );

                this.updateChartData(
                    this.campaignsChart,
                    campaigns,
                    ChartType.bar
                );

                this.updateChartData(
                    this.channelsChart,
                    channels,
                    ChartType.doughnut
                );

                this.updateChartData(
                    this.integratorsChart,
                    integrators,
                    ChartType.bar
                );
            },
            error => {
                if (error["status"] == 400) {
                    this.toastr.warning(
                        "Es necesario seleccionar una compañía, campaña, canal o integrador para poder hacer consultas"
                    );
                }
            }
        );
    }

    convertSelectedItemsIntoHttpParams(): HttpParams {
        var params = new HttpParams();
        params = this.convertselectedCompaniesIdsIntoHttpParams(params);
        params = this.convertselectedCampaignsIdsIntoHttpParams(params);
        params = this.convertselectedChannelsIdsIntoHttpParams(params);
        params = this.convertselectedIntegratorsIdsIntoHttpParams(params);
        params = this.convertselectedDaysIdsIntoHttpParams(params);
        params = this.convertselectedDaysOfWeekIdsIntoHttpParams(params);
        params = this.convertselectedDaysOfYearIdsIntoHttpParams(params);
        params = this.convertselectedHoursIdsIntoHttpParams(params);
        params = this.convertselectedMinutesIdsIntoHttpParams(params);
        params = this.convertselectedMonthsIdsIntoHttpParams(params);
        params = this.convertselectedQuartersIdsIntoHttpParams(params);
        params = this.convertselectedSecondsIdsIntoHttpParams(params);
        params = this.convertselectedWeeksOfYearIdsIntoHttpParams(params);
        params = this.convertselectedYearsIdsIntoHttpParams(params);
        console.log(params.toString());
        return params;
    }

    convertselectedCompaniesIdsIntoHttpParams(params: HttpParams) {
        this.selectedCompaniesIds.forEach(companyId => {
            params = params.append("companyId", companyId.toString());
        });
        return params;
    }

    convertselectedCampaignsIdsIntoHttpParams(params: HttpParams) {
        this.selectedCampaignsIds.forEach(campaignId => {
            params = params.append("campaignId", campaignId.toString());
        });
        return params;
    }

    convertselectedChannelsIdsIntoHttpParams(params: HttpParams) {
        this.selectedChannelsIds.forEach(channelId => {
            params = params.append("channelId", channelId.toString());
        });
        return params;
    }

    convertselectedIntegratorsIdsIntoHttpParams(params: HttpParams) {
        this.selectedIntegratorsIds.forEach(integratorId => {
            params = params.append("integratorId", integratorId.toString());
        });
        return params;
    }

    convertselectedYearsIdsIntoHttpParams(params: HttpParams) {
        this.selectedYearsIds.forEach(yearId => {
            params = params.append("yearId", yearId.toString());
        });
        return params;
    }

    convertselectedMonthsIdsIntoHttpParams(params: HttpParams) {
        this.selectedMonthsIds.forEach(monthId => {
            params = params.append("monthId", monthId.toString());
        });
        return params;
    }

    convertselectedDaysOfWeekIdsIntoHttpParams(params: HttpParams) {
        this.selectedDaysOfWeek.forEach(dayOfWeekId => {
            params = params.append("dayofweekId", dayOfWeekId.toString());
        });
        return params;
    }

    convertselectedWeeksOfYearIdsIntoHttpParams(params: HttpParams) {
        this.selectedWeeksIds.forEach(weekofyearId => {
            params = params.append("weekofyearId", weekofyearId.toString());
        });
        return params;
    }

    convertselectedDaysIdsIntoHttpParams(params: HttpParams) {
        this.selectedDaysIds.forEach(dayofmonthId => {
            console.log(dayofmonthId);
            params = params.append("dayofmonthId", dayofmonthId.toString());
        });
        return params;
    }

    convertselectedDaysOfYearIdsIntoHttpParams(params: HttpParams) {
        this.selectedDaysOfYearIds.forEach(dayofyearId => {
            params = params.append("dayofyearId", dayofyearId.toString());
        });
        return params;
    }

    convertselectedHoursIdsIntoHttpParams(params: HttpParams) {
        this.selectedHoursIds.forEach(hourId => {
            params = params.append("hourId", hourId.toString());
        });
        return params;
    }

    convertselectedMinutesIdsIntoHttpParams(params: HttpParams) {
        this.selectedMinutesIds.forEach(minuteId => {
            params = params.append("minuteId", minuteId.toString());
        });
        return params;
    }

    convertselectedSecondsIdsIntoHttpParams(params: HttpParams) {
        this.selectedSecondsIds.forEach(secondId => {
            params = params.append("secondId", secondId.toString());
        });
        return params;
    }

    convertselectedQuartersIdsIntoHttpParams(params: HttpParams) {
        this.selectedQuartersOfYearIds.forEach(quarterId => {
            params = params.append("quarterId", quarterId.toString());
        });
        return params;
    }

    updateChartData(chart: Chart, data: Point[], typeOfChart: ChartType) {
        var dataJsonArray = [];
        var colors = [];
        data.forEach(company => {
            dataJsonArray.push(company.toJson());
            colors.push(this.getRandomColor());
        });

        this.updateChart(
            chart,
            Point.getXArray(dataJsonArray),
            dataJsonArray,
            colors,
            typeOfChart
        );
    }

    createPointArray(pointsJson: any): Point[] {
        var points: Point[] = [];
        if (pointsJson) {
            let xs = pointsJson["x"];
            let ys = pointsJson["y"];

            for (var i = 0; i < xs.length; i++) {
                points.push(new Point(xs[i], ys[i]));
            }
        }
        return points;
    }

    openFilters() {
        const dialogConfig = new MatDialogConfig();
        dialogConfig.autoFocus = false;
        dialogConfig.width = "100%";
        dialogConfig.maxWidth = "100%";
        dialogConfig.height = "80%";
        dialogConfig.minHeight = "400px";
        dialogConfig.data = {
            id: 1,
            // Companies
            companiesDropdown: this.companiesDropdown,
            selectedCompanies: this.selectedCompanies,
            selectedCompaniesIds: this.selectedCompaniesIds,
            companiesDropdownSettings: this.companiesDropdownSettings,
            // Campaigns
            campaignsDropdown: this.campaignsDropdown,
            selectedCampaigns: this.selectedCampaigns,
            selectedCampaignsIds: this.selectedCampaignsIds,
            campaignsDropdownSettings: this.campaignsDropdownSettings,
            // Channels
            channelsDropdown: this.channelsDropdown,
            selectedChannels: this.selectedChannels,
            selectedChannelsIds: this.selectedChannelsIds,
            channelsDropdownSettings: this.channelsDropdownSettings,
            // Integrators
            integratorsDropdown: this.integratorsDropdown,
            selectedIntegrators: this.selectedIntegrators,
            selectedIntegratorsIds: this.selectedIntegratorsIds,
            integratorsDropdownSettings: this.integratorsDropdownSettings,
            // Years
            selectedYears: this.selectedYears,
            selectedYearsIds: this.selectedYearsIds,
            // Months
            selectedMonths: this.selectedMonths,
            selectedMonthsIds: this.selectedMonthsIds,
            // Days
            selectedDays: this.selectedDays,
            selectedDaysIds: this.selectedDaysIds,
            // Weeks
            selectedWeeks: this.selectedWeeks,
            selectedWeeksIds: this.selectedWeeksIds,
            // Days of week
            selectedDaysOfWeek: this.selectedDaysOfWeek,
            selectedDaysOfWeekIds: this.selectedDaysOfWeekIds,
            // Quarters of year
            selectedQuartersOfYear: this.selectedQuartersOfYear,
            selectedQuartersOfYearIds: this.selectedQuartersOfYear,
            // Days of year
            selectedDaysOfYear: this.selectedDaysOfYear,
            selectedDaysOfYearIds: this.selectedDaysOfYearIds,
            // Hours
            selectedHours: this.selectedHours,
            selectedHoursIds: this.selectedHoursIds,
            // Minutes
            selectedMinutes: this.selectedMinutes,
            selectedMinutesIds: this.selectedMinutesIds,
            // Seconds
            selectedSeconds: this.selectedSeconds,
            selectedSecondsIds: this.selectedSecondsIds
        };
        const dialogRef = this.dialog.open(MoreFiltersComponent, dialogConfig);
        dialogRef.updatePosition({ top: "55px", right: "0px", left: "0px" });
        dialogRef.afterClosed().subscribe(result => {
            console.log("Dialog was closed");
            this.fillCompaniesDropdownFromMenuData(result);
            this.fillCampaignsDropdownFromMenuData(result);
            this.fillChannelsDropdownFromMenuData(result);
            this.fillIntegratorsDropdownFromMenuData(result);
            this.fillYearsDropdownFromMenuData(result);
            this.fillMonthsDropdownFromMenuData(result);
            this.fillDaysDropdownFromMenuData(result);
            this.fillWeeksDropdownFromMenuData(result);
            this.fillDaysOfWeekDropdownFromMenuData(result);
            this.fillQuartersOfYearDropdownFromMenuData(result);
            this.fillDaysOfYearDropdownFromMenuData(result);
            this.fillHoursDropdownFromMenuData(result);
            this.fillMinutesDropdownFromMenuData(result);
            this.fillSecondsDropdownFromMenuData(result);
        });
    }

    fillCompaniesDropdownFromMenuData(data) {
        this.selectedCompanies = data["companies"]["selectedCompanies"];
        this.selectedCompaniesIds = data["companies"]["selectedCompaniesIds"];
        this.companiesDropdown = data["companies"]["companiesDropdown"];
    }

    fillCampaignsDropdownFromMenuData(data) {
        this.selectedCampaigns = data["campaigns"]["selectedCampaigns"];
        this.selectedCampaignsIds = data["campaigns"]["selectedCampaignsIds"];
        this.campaignsDropdown = data["campaigns"]["campaignsDropdown"];
    }

    fillChannelsDropdownFromMenuData(data) {
        this.selectedChannels = data["channels"]["selectedChannels"];
        this.selectedChannelsIds = data["channels"]["selectedChannelsIds"];
        this.channelsDropdown = data["channels"]["channelsDropdown"];
    }

    fillIntegratorsDropdownFromMenuData(data) {
        this.selectedIntegrators = data["integrators"]["selectedIntegrators"];
        this.selectedIntegratorsIds =
            data["integrators"]["selectedIntegratorsIds"];
        this.integratorsDropdown = data["integrators"]["integratorsDropdown"];
    }

    fillYearsDropdownFromMenuData(data) {
        this.selectedYears = data["years"]["selectedYears"];
        this.selectedYearsIds = data["years"]["selectedYearsIds"];
    }

    fillMonthsDropdownFromMenuData(data) {
        this.selectedMonths = data["months"]["selectedMonths"];
        this.selectedMonthsIds = data["months"]["selectedMonthsIds"];
    }

    fillDaysDropdownFromMenuData(data) {
        this.selectedDays = data["days"]["selectedDays"];
        this.selectedDaysIds = data["days"]["selectedDaysIds"];
    }

    fillWeeksDropdownFromMenuData(data) {
        this.selectedWeeks = data["weeks"]["selectedWeeks"];
        this.selectedWeeksIds = data["weeks"]["selectedWeeksIds"];
    }

    fillDaysOfWeekDropdownFromMenuData(data) {
        this.selectedDaysOfWeek = data["daysOfWeek"]["selectedDaysOfWeek"];
        this.selectedDaysOfWeekIds =
            data["daysOfWeek"]["selectedDaysOfWeekIds"];
    }

    fillQuartersOfYearDropdownFromMenuData(data) {
        this.selectedQuartersOfYear =
            data["quartersOfYear"]["selectedQuartersOfYear"];
        this.selectedQuartersOfYearIds =
            data["quartersOfYear"]["selectedQuartersOfYearIds"];
    }

    fillDaysOfYearDropdownFromMenuData(data) {
        this.selectedDaysOfYear = data["daysOfYear"]["selectedDaysOfYear"];
        this.selectedDaysOfYearIds =
            data["daysOfYear"]["selectedDaysOfYearIds"];
    }

    fillHoursDropdownFromMenuData(data) {
        this.selectedHours = data["hours"]["selectedHours"];
        this.selectedHoursIds = data["hours"]["selectedHoursIds"];
    }

    fillMinutesDropdownFromMenuData(data) {
        this.selectedMinutes = data["minutes"]["selectedMinutes"];
        this.selectedMinutesIds = data["minutes"]["selectedMinutesIds"];
    }

    fillSecondsDropdownFromMenuData(data) {
        this.selectedSeconds = data["seconds"]["selectedSeconds"];
        this.selectedSecondsIds = data["seconds"]["selectedSecondsIds"];
    }

    getArrayOfRandomColors(length: Number): String[] {
        var colors = [];
        for (var i = 0; i < length; i++) {
            colors.push(this.getRandomColor());
        }
        return colors;
    }

    getRandomColor() {
        var letters = "0123456789ABCDEF";
        var color = "#";
        for (var i = 0; i < 6; i++) {
            color += letters[Math.floor(Math.random() * 16)];
        }
        return color;
    }

    createChart(
        title: String,
        labels: String[],
        data: any[],
        colors: String[],
        type: ChartType,
        element: ElementRef
    ): Chart {
        return new Chart(element.nativeElement.getContext("2d"), {
            type: type.valueOf(),
            data: {
                labels: labels,
                datasets: [
                    {
                        data: data,
                        fill: true,
                        backgroundColor: colors
                    }
                ]
            },
            options: {
                title: {
                    display: true,
                    text: title
                },
                responsive: true,
                legend: {
                    display: false
                },
                scales: {
                    xAxes: [
                        {
                            display: true
                        }
                    ],
                    yAxes: [
                        {
                            display: true
                        }
                    ]
                }
            }
        });
    }

    updateChart(
        chart: Chart,
        labels: String[],
        data: any[],
        colors: String[],
        type: ChartType
    ) {
        chart.type = type.valueOf();
        chart.data.labels = labels;
        chart.data.datasets[0].data = data;
        chart.data.datasets[0].backgroundColor = colors;

        chart.update();
    }

    updateStarSchema() {
        this.statisticsService.updateStarSchema().subscribe(
            data => {
                this.toastr.success(
                    "La base de datos de estadística ha sido actualizada satisfactoriamente."
                );
            },
            error => {
                this.toastr.error(
                    "Hubo un error actualizando la base de datos de estadísticas."
                );
            }
        );
    }
}
