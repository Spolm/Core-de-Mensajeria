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
    timeLineChart = [];
    companiesChart = [];
    campaignsChart = [];
    channelsChart = [];
    integratorsChart = [];

    opcionSeleccionado: string = "0";
    verSeleccion: string = "";
    Date2Capturado: string = "";
    Date1Capturado: string = "";
    opcionDateSleccionado: Date;
    opcionDateSleccionado2: Date;
    paramType: string;

    datos = [];

    constructor(
        public statisticsService: StatisticsServiceService,
        public toastr: ToastrService,
        public dialog: MatDialog
    ) {
        super(statisticsService, toastr);
        this.datos = [
            "Grafico de Barras",
            "Grafico de Linea ",
            "Grafico de Tortas"
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

    ChartTransformation() {
        this.clearChart(this.campaignsChart);
        this.clearChart(this.companiesChart);
        this.clearChart(this.channelsChart);

        if (this.opcionSeleccionado != "0") {
            // Pasamos el valor seleccionado a la variable verSeleccion
            this.verSeleccion = this.opcionSeleccionado;
            console.log("Valor Capturado", this.verSeleccion);
        } else this.toastr.error("Debe seleccionar otra opcion");

        if (this.verSeleccion == "Grafico de Tortas") {
            var TypeChosen: ChartType = ChartType.doughnut;
            console.log("paso por el if", TypeChosen);
        } else if (this.verSeleccion == "Grafico de Barras") {
            var TypeChosen: ChartType = ChartType.bar;
        } else if (this.verSeleccion == "Grafico de Linea") {
            var TypeChosen: ChartType = ChartType.line;
        }

        this.statisticsService
            .getInitialMessagesForCompanies()
            .subscribe(data => {
                this.companiesChart = this.insertInitialDataIntoCharts(
                    data,
                    this.companiesChartElement,
                    "Cantidad de mensajes por compañía",
                    TypeChosen
                );
            });

        this.statisticsService
            .getInitialMessagesForCampaigns()
            .subscribe(data => {
                this.campaignsChart = this.insertInitialDataIntoCharts(
                    data,
                    this.campaignsChartElement,
                    "Cantidad de mensajes por campaña",
                    TypeChosen
                );
            });

        this.statisticsService
            .getInitialMessagesForChannels()
            .subscribe(data => {
                this.channelsChart = this.insertInitialDataIntoCharts(
                    data,
                    this.channelsChartElement,
                    "Cantidad de mensajes por canal",
                    TypeChosen
                );
            });
    }

    clearChart(chart: Chart) {
        chart.destroy();
    }

    ngOnInit() {
        this.getAllCompanies();
        //this.getAllCampaigns();
        this.getAllChannels();

        this.statisticsService
            .getInitialMessagesForCompanies()
            .subscribe(data => {
                this.companiesChart = this.insertInitialDataIntoCharts(
                    data,
                    this.companiesChartElement,
                    "Cantidad de mensajes por compañía",
                    ChartType.bar
                );
            });

        this.statisticsService
            .getInitialMessagesForCampaigns()
            .subscribe(data => {
                this.campaignsChart = this.insertInitialDataIntoCharts(
                    data,
                    this.campaignsChartElement,
                    "Cantidad de mensajes por campaña",
                    ChartType.bar
                );
            });

        this.statisticsService
            .getInitialMessagesForChannels()
            .subscribe(data => {
                this.channelsChart = this.insertInitialDataIntoCharts(
                    data,
                    this.channelsChartElement,
                    "Cantidad de mensajes por canal",
                    ChartType.doughnut
                );
            });
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
                    this.toastr.error("Error de conexión");
                }
            );
    }

    private getAllChannels() {
        this.statisticsService.getAllChannels().subscribe(data => {
            this.insertIntoDropdown(EntityType.channel, data);
        });
    }

    capturar() {
        if (this.opcionSeleccionado != "0") {
            // Pasamos el valor seleccionado a la variable verSeleccion
            this.verSeleccion = this.opcionSeleccionado;
            console.log("Valor Capturado", this.verSeleccion);
        } else this.toastr.error("Debe seleccionar otra opcion");
    }

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
            this.toastr.error("Error en las fechas");
        }
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
        this.verSeleccion = this.opcionSeleccionado;
        console.log("Valor Capturado", this.verSeleccion);

        if (this.verSeleccion == "Grafico de Tortas") {
            var TypeChosen: ChartType = ChartType.doughnut;
            console.log("paso por el if", TypeChosen);
        } else if (this.verSeleccion == "Grafico de Barras") {
            var TypeChosen: ChartType = ChartType.bar;
        } else if (this.verSeleccion == "Grafico de Linea") {
            var TypeChosen: ChartType = ChartType.line;
        }

        var params = this.convertSelectedItemsIntoHttpParams();
        this.statisticsService.getStatistics(params).subscribe(
            data => {
                let companiesJson = data["companies"];
                var companies: Point[] = this.createPointArray(companiesJson);
                let campaingsJson = data["campaigns"];
                var campaigns: Point[] = this.createPointArray(campaingsJson);
                let channelsJson = data["channels"];
                var channels: Point[] = this.createPointArray(channelsJson);

                this.updateChartData(
                    this.companiesChart,
                    companies,
                    ChartType.bar
                );
            },
            error => {
                console.error(error);
            }
        );
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

    convertSelectedItemsIntoHttpParams(): HttpParams {
        var params = new HttpParams();
        params = this.convertselectedCompaniesIdsIntoHttpParams(params);
        params = this.convertselectedCampaignsIdsIntoHttpParams(params);
        params = this.convertselectedChannelsIdsIntoHttpParams(params);
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

    openFilters() {
        const dialogConfig = new MatDialogConfig();
        dialogConfig.autoFocus = false;
        dialogConfig.width = "100%";
        dialogConfig.maxWidth = "100%";
        dialogConfig.height = "80%";
        dialogConfig.data = {
            id: 1,
            companiesDropdown: this.companiesDropdown,
            selectedCompanies: this.selectedCompanies,
            selectedCompaniesIds: this.selectedCompaniesIds,
            companiesDropdownSettings: this.companiesDropdownSettings,
            campaignsDropdown: this.campaignsDropdown,
            selectedCampaigns: this.selectedCampaigns,
            selectedCampaignsIds: this.selectedCampaignsIds,
            campaignsDropdownSettings: this.campaignsDropdownSettings,
            channelsDropdown: this.channelsDropdown,
            selectedChannels: this.selectedChannels,
            selectedChannelsIds: this.selectedChannelsIds,
            channelsDropdownSettings: this.channelsDropdownSettings,
            integratorsDropdown: this.integratorsDropdown,
            selectedIntegrators: this.selectedIntegrators,
            selectedIntegratorsIds: this.selectedIntegratorsIds,
            integratorsDropdownSettings: this.integratorsDropdownSettings
        };
        const dialogRef = this.dialog.open(MoreFiltersComponent, dialogConfig);
        dialogRef.updatePosition({ top: "55px", right: "0px", left: "0px" });
        dialogRef.afterClosed().subscribe(result => {
            console.log("Dialog was closed");
            this.fillCompaniesDropdownsFromMenuData(result);
            this.fillCampaignsDropdownsFromMenuData(result);
            this.fillChannelsDropdownsFromMenuData(result);
            console.log(result);
        });
    }

    fillCompaniesDropdownsFromMenuData(data) {
        this.selectedCompanies = data["companies"]["selectedCompanies"];
        this.selectedCompaniesIds = data["companies"]["selectedCompaniesIds"];
        this.companiesDropdown = data["companies"]["companiesDropdown"];
    }

    fillCampaignsDropdownsFromMenuData(data) {
        this.selectedCampaigns = data["campaigns"]["selectedCampaigns"];
        this.selectedCampaignsIds = data["campaigns"]["selectedCampaignsIds"];
        this.campaignsDropdown = data["campaigns"]["campaignsDropdown"];
    }

    fillChannelsDropdownsFromMenuData(data) {
        this.selectedChannels = data["channels"]["selectedChannels"];
        this.selectedChannelsIds = data["channels"]["selectedChannelsIds"];
        this.channelsDropdown = data["channels"]["channelsDropdown"];
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
        console.log(type.valueOf());
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
}
