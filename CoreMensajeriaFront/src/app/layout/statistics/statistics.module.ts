import { FormsModule } from "@angular/forms";
import { StatisticsServiceService } from "./statistics-service.service";
import { HttpClientModule } from "@angular/common/http";
import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { StatisticsComponent } from "./statistics.component";
import { StatisticsRoutingModule } from "./statistics-routing.module";
import { PlotlyModule } from "angular-plotly.js";
//import { NgMultiSelectDropDownModule } from "ng-multiselect-dropdown";

@NgModule({
    imports: [
        CommonModule,
        StatisticsRoutingModule,
        PlotlyModule,
        FormsModule
        //NgMultiSelectDropDownModule.forRoot()
    ],
    providers: [StatisticsServiceService],
    declarations: [StatisticsComponent]
})
export class StatisticsModule {}
