import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { PendingComponent } from './pending/pending.component';

@NgModule({
  declarations: [PageNotFoundComponent, PendingComponent],
  imports: [CommonModule],
})
export class SharedModule {}
