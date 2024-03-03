import {Component, OnInit} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'UI';
  constructor(private http: HttpClient) { }

  downloadZipFile() {
    const url : string = 'http://localhost:8080/download';
    const headers : HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

    // You may need to pass any required data in the request body
    const entities  =  [
        {
          name: "TestEntity",
          fields : [
            {
              modifier: "private",
              dataType: "String",
              name: "TestName"
            }
          ]
        }
    ];

    this.http.post(url, JSON.stringify(entities), {
      headers: headers,
      responseType: 'blob',
    }).subscribe(blob => {
      const downloadLink = document.createElement('a');
      downloadLink.href = window.URL.createObjectURL(blob);
      downloadLink.download = 'file.zip'; // Replace with the desired file name
      document.body.appendChild(downloadLink);
      downloadLink.click();
      document.body.removeChild(downloadLink);
    });
  }

  ngOnInit(): void {
    this.downloadZipFile();
  }
}
