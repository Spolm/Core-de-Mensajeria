import { FormsModule } from "@angular/forms";
import { StatisticsServiceService } from "./statistics-service.service";
import { HttpClientModule } from "@angular/common/http";
import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { StatisticsComponent } from "./statistics.component";
import { StatisticsRoutingModule } from "./statistics-routing.module";
import { PlotlyModule } from "angular-plotly.js";
import { NgMultiSelectDropDownModule } from "ng-multiselect-dropdown";
<<<<<<< HEAD
=======
import { MoreFiltersComponent } from "./more-filters/more-filters.component";
import { MatDialogModule, MatButtonModule } from "@angular/material";
>>>>>>> 877fc3034ba2b20a4758ea00b03d5adcaf62813c

@NgModule({
    imports: [
        CommonModule,
        StatisticsRoutingModule,
        PlotlyModule,
        FormsModule,
<<<<<<< HEAD
        NgMultiSelectDropDownModule.forRoot()
=======
        NgMultiSelectDropDownModule.forRoot(),
        MatDialogModule,
        MatButtonModule
>>>>>>> 877fc3034ba2b20a4758ea00b03d5adcaf62813c
    ],
    providers: [StatisticsServiceService],
    declarations: [StatisticsComponent, MoreFiltersComponent],
    entryComponents: [MoreFiltersComponent]
})
export class StatisticsModule {}
