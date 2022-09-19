import {Component, OnInit} from '@angular/core';
import {EmployeeService} from "./employee.service";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Employee} from "./employee";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  public employees: Employee[];
  public myEditEmployee: Employee;
  public deleteEmployee: Employee;

  constructor(private employeeService: EmployeeService) {
    this.employees = [];
  }

  ngOnInit(): void {
    this.getEmployees();
  }

  public getEmployees(): void {
    this.employeeService.getEmployees().subscribe(
      (response: Employee[]) => {
        this.employees = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message)
      }
    )
  }

  public searchEmployees(key: string): void {
    const results: Employee[] = [];
    for(let employee of this.employees) {
      if(employee.firstname.toLowerCase().indexOf(key.toLowerCase()) !== -1
        || employee.lastname.toLowerCase().indexOf(key.toLowerCase()) !== -1
        || employee.email.toLowerCase().indexOf(key.toLowerCase()) !== -1
        || employee.phone.toLowerCase().indexOf(key.toLowerCase()) !== -1){
        results.push(employee);
      }
    }
    this.employees = results;
    if(results.length ===0 || !key) {
      this.getEmployees();
    }
  }


  public onAddEmployee(addForm: NgForm): void {
    const close = document.getElementById('add-employee-form');
    if (close != null) {
      close.click();
    }
    this.employeeService.addEmployee(addForm.value).subscribe(
      (response: Employee) => {
        console.log(response);
        this.getEmployees();
        addForm.reset();
      },
    (error: HttpErrorResponse) => {
        alert(error.message);
        addForm.reset();
    }
    )
  }


  onEditEmployee(employee: Employee): void{
    this.employeeService.updateEmployees(employee).subscribe(
      (response: Employee)=> {
        console.log(response);
        this,this.getEmployees();
      },
      (error: HttpErrorResponse)=>{
        alert(error.message)
      }
    )
  }

  public onDeleteEmployee(id: number): void {
    this.employeeService.deleteEmployees(id).subscribe(
      (response: void) =>{this.getEmployees();},
      (error: HttpErrorResponse) =>{ alert(error.message)}
    )
  }

  public onOpenModel(employee: any, mode: string): void{
    const container = document.getElementById('main-container')
    const button = document.createElement('button');
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal')
    if (mode=='add') {
      button.setAttribute('data-target', '#addEmployeeModal');
    }

    if (mode=='edit') {
      button.setAttribute('data-target', '#updateEmployeeModal');
      this.myEditEmployee = employee;
    }

    if (mode=='delete') {
      button.setAttribute('data-target', '#deleteEmployeeModal');
      this.deleteEmployee = employee;
    }
    if (container != null) {
      container.appendChild(button)
      button.click();
    }
  }
}
