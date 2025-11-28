import { Component } from '@angular/core';
import { ReportsService } from '../core/reports.service';
import { AuthService } from '../core/auth.service';

@Component({
  selector: 'app-reports',
  templateUrl: './reports.component.html',
  styleUrls: ['./reports.component.css']
})
export class ReportsComponent {

  status: string = 'COMPLETED';
  startDate: string = '';
  endDate: string = '';

  downloading = false;

  constructor(
    private reportsService: ReportsService,
    public authService: AuthService
  ) {}

  private triggerDownload(blob: Blob, filename: string) {
    const url = window.URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.href = url;
    a.download = filename;
    a.click();
    window.URL.revokeObjectURL(url);
  }

  downloadStatusPdf() {
    this.downloading = true;
    this.reportsService.downloadTasksByStatusPdf(this.status)
      .subscribe({
        next: (blob) => {
          this.triggerDownload(blob, `tasks-${this.status}.pdf`);
          this.downloading = false;
        },
        error: () => this.downloading = false
      });
  }

  downloadStatusExcel() {
    this.downloading = true;
    this.reportsService.downloadTasksByStatusExcel(this.status)
      .subscribe({
        next: (blob) => {
          this.triggerDownload(blob, `tasks-${this.status}.xlsx`);
          this.downloading = false;
        },
        error: () => this.downloading = false
      });
  }

  downloadRangePdf() {
    if (!this.startDate || !this.endDate) return;
    this.downloading = true;
    this.reportsService.downloadTasksByRangePdf(this.startDate, this.endDate)
      .subscribe({
        next: (blob) => {
          this.triggerDownload(blob, `tasks-${this.startDate}_to_${this.endDate}.pdf`);
          this.downloading = false;
        },
        error: () => this.downloading = false
      });
  }

  downloadRangeExcel() {
    if (!this.startDate || !this.endDate) return;
    this.downloading = true;
    this.reportsService.downloadTasksByRangeExcel(this.startDate, this.endDate)
      .subscribe({
        next: (blob) => {
          this.triggerDownload(blob, `tasks-${this.startDate}_to_${this.endDate}.xlsx`);
          this.downloading = false;
        },
        error: () => this.downloading = false
      });
  }
}
