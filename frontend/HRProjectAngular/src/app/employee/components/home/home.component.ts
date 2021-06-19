import { Component, OnInit } from '@angular/core';
import { HTTPReq } from 'src/app/service/HTTPReq/HTTPReq.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit{
  /** Based on the screen size, switch from standard to one column per row */
  
  userName!: string;
  userId!: string;
  isDataAvailable : boolean = false;

  constructor(
    private httpRequestService: HTTPReq,
  ) {}


  ngOnInit(): void {
    this.userId = localStorage.getItem("userId")!;
    this.httpRequestService.getData('/profile/' + this.userId, null, 'http://localhost:8999').subscribe(
      (data: any) => {
        this.userName = data.profile.nameSection.fullName;
        console.log(data);
        this.isDataAvailable = true;
      }
    )
  }

}
