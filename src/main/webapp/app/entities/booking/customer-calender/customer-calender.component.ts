import { Component, ChangeDetectionStrategy } from '@angular/core';
import { CalendarEvent, CalendarView } from 'angular-calendar';

@Component({
  selector: 'jhi-customer-calender',
  templateUrl: './customer-calender.component.html',
  styleUrls: ['./customer-calender.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class CustomerCalenderComponent {
  view: CalendarView = CalendarView.Month;

  viewDate: Date = new Date();

  events: CalendarEvent[] = [];

  clickedDate?: Date;

  clickedColumn?: number;
}
