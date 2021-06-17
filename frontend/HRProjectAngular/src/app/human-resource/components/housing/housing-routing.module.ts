import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HousingPageComponent } from './housing-page/housing-page.component';
import { HousingDetailComponent } from './housing-detail/housing-detail.component';

const routes: Routes = [
  {
    path: '',
    component: HousingPageComponent,
  },
  {
    path: 'detail/:id',
    component: HousingDetailComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class HousingRoutingModule {}
