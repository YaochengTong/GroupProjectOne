import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HousingPageComponent } from './housing-page/housing-page.component';

const routes: Routes = [
  {
    path: '',
    component: HousingPageComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class HousingRoutingModule {}
