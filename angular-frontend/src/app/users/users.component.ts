import { Component, OnInit } from '@angular/core';
import {User} from "../models/User";
import {UsersService} from "../services/users.service";
import {FormBuilder, FormGroup, ValidationErrors, Validators} from "@angular/forms";
import {ModalDismissReasons, NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {TemplateRef, ViewChild } from '@angular/core';
import {MatDialog} from "@angular/material/dialog";
import {MessageBoxService} from "../services/message-box.service";
import {PageEvent} from "@angular/material/paginator";


@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  users! : Array<User>;
  errorMsg! : string;
  searchForm! : FormGroup;
  addUserForm! : FormGroup;
  closeResult! : string;
  isEdit : boolean = false;
  totalElements: number = 0;
  isSearchMode : boolean = false;
  @ViewChild('mymodal') private mymodal!: TemplateRef<any>;

  constructor(private usersService : UsersService, private fb : FormBuilder , private fb1 : FormBuilder, private modalService: NgbModal,
              private dialogService : MessageBoxService) {

  }

  ngOnInit(): void {
    this.handleGetAllUsers(0, 5);
    this.searchForm = this.fb.group({
      username : this.fb.control("")
    });
    this.addUserForm = this.fb1.group({
        id: this.fb1.control("", []),
        fullname: this.fb1.control("", [Validators.required, Validators.minLength(5)]),
        username: this.fb1.control("", [Validators.required, Validators.minLength(5)]),
        email: this.fb1.control("", [Validators.required, Validators.email]),
        password: this.fb1.control("", [Validators.required, Validators.minLength(10)]),
        passwordConf: this.fb1.control(""),
      },
    {
      validators : [this.passwordMatchValidator],

           }
    );
  }


  handleGetAllUsers(page : number, size : number)  {
    this.usersService.getAllUsers(page, size).subscribe(
      {
        next : value => {
          //this.users = value;
          this.users = value['content'];
          this.totalElements = value['totalElements'];
        },
        error : err => {
          this.errorMsg = err.message;
        }
      }
    );
  }

  nextPage(event: PageEvent) {
    let page = event.pageIndex;
    let size = event.pageSize;
    if(!this.isSearchMode) this.handleGetAllUsers(page, size);
    else this.handleSearchUsersByUsername(page, size);
  }

  handleSearchUsersByUsername(page : number, size : number) {
    if(!this.isSearchMode)  this.isSearchMode = true;
    let username = this.searchForm.value.username;
    this.usersService.getByUsername(username, page, size).subscribe(
      {
        next : value => {
          this.users = value['content'];
          this.totalElements = value['totalElements'];
        },
        error : err => {
          this.errorMsg = err.message;
        }
      }
    );
  }

  handleDeleteUser(id: number) {
    let conf = confirm("Are you sure ?")
    if(!conf) return ;
    this.usersService.deleteUser(id).subscribe(
      {
        next : value => {
          this.users = this.users.filter(user => user.id !== id);
          console.log('Product deleted successfully!');
        },
        error : err => {
          this.errorMsg = err.message;
        }
      }
    );
  }

  handleAddUser() {
    let user : User = this.addUserForm.value;
    this.usersService.addUser(user).subscribe(
      {
        next : value => {
    //      this.handleGetAllUsers();
        },
        error : err => {
          this.errorMsg = err.message;
          this.dialogService.openDialog('Error', err.error.message);
          console.log(err.error.message);
        }
      }
    );

  }

  handleFillFormForEditUser(user : User) {
    this.isEdit = true;
    this.addUserForm.patchValue({
      id : user.id,
      fullname: user.fullname,
      username: user.username,
      email: user.email,
      password: user.password,
      passwordConf: "",
    });
    this.modalService.open(this.mymodal);
  }
  handleEdit(){
    let user : User = this.addUserForm.value;
    this.usersService.editUser({id : user.id, fullname : user.fullname, username : user.username, email : user.email, password : user.password}).subscribe(
      {
        next : value => {
          this.handleGetAllUsers(0,5);
        },
        error : err => {
          this.errorMsg = err.error.message;
        }
      }
    );
    this.isEdit = false;
  }

  open(content: any) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
      console.log('Closed');
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
      console.log('Dismissed');
    });
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return  `with: ${reason}`;
    }
  }


  passwordMatchValidator(frm : FormGroup) {
    return frm.controls['password'].value === frm.controls['passwordConf'].value ? null : {'mismatch': true};
  }

  getFormErrors(err : ValidationErrors) : string{
    if (err['required']) {
      return  'Required field';
    } else if (err['minlength']) {
      return `Min length is ${err['minlength'].requiredLength}`;
    } else if (err['maxlength']) {
      return `Max length is ${err['maxlength'].requiredLength}`;
    } else if (err['email']) {
      return 'Email is not valid';
    } else if (err['mismatch']) {
      return 'Passwords do not match';
    } else {
      return '';
    }
  }


  handleCancelSearch() {
    this.isSearchMode = false;
    this.handleGetAllUsers(0,5);
  }
}
