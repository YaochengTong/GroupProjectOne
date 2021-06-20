import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HousingPageComponent } from './housing-page/housing-page.component';
import { FacilityReportComponent } from './facility-report/facility-report.component';

const routes: Routes = [
  { path: 'housing-page', component: HousingPageComponent },
  {
    path: 'facility-report',
    component: FacilityReportComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class HousingRoutingModule {}
