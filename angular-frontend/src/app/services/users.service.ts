import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "../models/User";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  users! : Array<User>;

  constructor(private http : HttpClient) { }

  getAllUsers(page : number, size : number) : Observable<any>{
    return this.http.get<any>(environment.backendUrl+"/user/"+page+"/"+size);
  }
  getByUsername(username : string, page : number, size : number) : Observable<any>{
    return this.http.get<any>(environment.backendUrl+"/user/search/"+username+"/"+page+"/"+size);
  }
  deleteUser(id : number){
    console.log("Id : "+id);
    return this.http.delete(environment.backendUrl+"/user/"+id);
  }
  addUser(user : User) : Observable<User> {
    return this.http.post<User>(environment.backendUrl+"/user", user);
  }

  editUser(user : User) : Observable<User>{
    return this.http.put<User>(environment.backendUrl+"/user/"+user.id, user);
  }

}
